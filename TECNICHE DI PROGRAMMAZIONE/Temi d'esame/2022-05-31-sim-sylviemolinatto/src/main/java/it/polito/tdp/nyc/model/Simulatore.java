package it.polito.tdp.nyc.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.nyc.db.NYCDao;
import it.polito.tdp.nyc.model.Evento.EventType;

public class Simulatore {

	private NYCDao dao;
	
	// parametri della simulazione - dati in ingresso
	
	private int numTecnici; // stabiliti dall'utente
	private String provider; // stabilito dall'utente
	private String quartiereIniziale; // stabilito dall'utente
	
	private int durataRevisioneMedia = 10; // min 
	private int tempoPassaggioHotspotStessoQuartiere=10; // dai 10 ai 20 min
	private int velocitaSquadra = 50; // km/h
	private int tempoCambioQuartiere; // in funzione della distanza tra i quartieri
	private List<String> quartieriDaVisitare;
	
	// coda
	private PriorityQueue<Evento> coda;
	
	// output della simulazione
	private Duration durataTotaleProcesso;
	private Map<Integer,Operatore> operatori; // numero di hotspost revisionati da ciascun tecnico
	
	// stato del mondo simulato
	private List<String> hotspotDaRevisionare;
	private Graph<String,DefaultWeightedEdge> grafo; 
	private int operatoriOccupati;
	
	public Simulatore(Graph<String,DefaultWeightedEdge> grafo) {
		this.grafo=grafo;
	}
	
	public void init(int numTecnici, String provider, String quartiereIniziale, List<String> quartieriAdiacenti) {
		
		this.numTecnici=numTecnici;
		this.provider=provider;
		this.quartiereIniziale=quartiereIniziale;
		this.quartieriDaVisitare=new ArrayList<String>(quartieriAdiacenti);
		this.quartieriDaVisitare.remove(quartiereIniziale);
		
		this.durataTotaleProcesso = Duration.ofMinutes(0);
		this.operatori = new HashMap<Integer,Operatore>();
		for(int i=0; i<this.numTecnici;i++) {
			this.operatori.put(i+1,new Operatore(i+1)); 
		}
		
		this.dao=new NYCDao();
		this.coda = new PriorityQueue<Evento>();
		this.hotspotDaRevisionare = dao.getHotspotByProviderAndCity(provider, quartiereIniziale);
		creaEventi(Duration.ofMinutes(0),this.hotspotDaRevisionare,this.quartiereIniziale);
	}
	
	private void creaEventi(Duration inizio, List<String> hotspot, String quartiere) {
		for(Operatore o : operatori.values()) {
			if(!this.hotspotDaRevisionare.isEmpty()) {
				o.incrementaNumHotspotRevisionati();
				this.operatoriOccupati++;
				Evento e = new Evento(EventType.ASSEGNAZIONE_LAVORO,inizio,o.getId(),this.hotspotDaRevisionare.get(0),quartiere);
				this.coda.add(e);
				this.hotspotDaRevisionare.remove(0);
				
			}
				
			
		}
		
	}

	public void run() {
		
		while(!this.coda.isEmpty()) {
			Evento e = this.coda.poll();
			processEvent(e);
			this.durataTotaleProcesso=e.getTime(); 
		}
		
	}
	
	public void processEvent(Evento e) {
		
		EventType type = e.getType();
		Duration time = e.getTime();
		String hotspot = e.getHotspot();
		String quartiere = e.getQuartiere();
		Integer tecnico = e.getIdTecnico();
		
		switch(type) {
	
		case ASSEGNAZIONE_LAVORO:
			if(Math.random()<0.1) {
				this.coda.add(new Evento(EventType.TERMINE_LAVORO,time.plusMinutes(25),tecnico, hotspot, quartiere));
			}
			else {
				this.coda.add(new Evento(EventType.TERMINE_LAVORO,time.plusMinutes(10),tecnico, hotspot, quartiere));
			}
		
			break;
			
		case TERMINE_LAVORO:
			this.operatoriOccupati--;
			if(!this.hotspotDaRevisionare.isEmpty()) {
				this.operatori.get(tecnico).incrementaNumHotspotRevisionati();
				this.operatoriOccupati++;
				Evento evento;
				int tempoTrasferimento = (int)(Math.random()*this.tempoPassaggioHotspotStessoQuartiere+10);
				evento = new Evento(EventType.ASSEGNAZIONE_LAVORO,time.plusMinutes(tempoTrasferimento),tecnico,this.hotspotDaRevisionare.get(0),quartiere);
				this.hotspotDaRevisionare.remove(0);
				this.coda.add(evento);
		     }
			else if(this.operatoriOccupati>0) {
				// non faccio nulla
			}
			else if(this.quartieriDaVisitare.size()>0) {
				String quartiereScelto = cercaQuartierePiuVicino(this.quartieriDaVisitare,e.getQuartiere()) ;
				this.quartieriDaVisitare.remove(quartiereScelto);
				this.hotspotDaRevisionare = new ArrayList<String>();
				this.hotspotDaRevisionare = dao.getHotspotByProviderAndCity(this.provider,quartiereScelto);
				this.tempoCambioQuartiere= (int) ((this.grafo.getEdgeWeight(this.grafo.getEdge(e.getQuartiere(),quartiereScelto))/this.velocitaSquadra*60));
				if(!this.hotspotDaRevisionare.isEmpty()) {
					this.coda.add(new Evento(EventType.NUOVO_QUARTIERE,time.plusMinutes(tempoCambioQuartiere),-1,null,quartiereScelto));
				}
			}
			else {
				// termine simulazione
			}
			
			break;
			
		case NUOVO_QUARTIERE:
			this.creaEventi(time, this.hotspotDaRevisionare, quartiere);
			break;
	  }   
   }
	
	private String cercaQuartierePiuVicino(List<String> quartieriDaVisitare, String quartiere) {
		
		String quartiereScelto=null;
		double distanzaMin=Integer.MAX_VALUE;
			for(String q : quartieriDaVisitare) {
				double distanza=this.grafo.getEdgeWeight(this.grafo.getEdge(quartiere, q));
				if(distanza<distanzaMin) {
					distanzaMin = distanza;
					quartiereScelto = q;
				}
			
		}
			
		return quartiereScelto;
	}

	public long getDurata() {
		return this.durataTotaleProcesso.toMinutes();
	}
	
	public Map<Integer,Operatore> getHotspotRevisionatiDaOperatori(){
		return this.operatori;
	}
}
