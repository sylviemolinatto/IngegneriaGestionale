package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Simulatore {
	
	// dati in input
	int numReporter;
	int sogliaX;
	
	// dati in output
	double mediaReporterPerPartita;
	int numPartiteSottoSoglia;
	
	// coda degli eventi
	PriorityQueue<Evento> coda;
	
	// stato del mondo
	Map<Team,Integer> numReporterAssegnati;
	Map<Integer,Team> squadre;
	List<Match> partite;
	
	public Simulatore(int numReporter, int sogliaX) {
		this.numReporter = numReporter;
		this.sogliaX = sogliaX;
	}
	
	public void init(List<Match> partite, Map<Integer,Team> squadre,Map<Team,Integer> classifica) {
		this.numReporterAssegnati = new HashMap<Team,Integer>();
		this.squadre = squadre;
		this.partite = partite;
	
		// inizialmente ogni squadra ha N reporter
		for(Team t : squadre.values()) {
			this.numReporterAssegnati.put(t, this.numReporter);
		}
		
		// inizializzo e carico la coda
		this.coda = new PriorityQueue<Evento>();
		for(Match m : partite) {
			this.coda.add(new Evento(m));
		}
		
		this.mediaReporterPerPartita=0.0;
		this.numPartiteSottoSoglia=0;
	}
	
	public void run() {
		
		while(!this.coda.isEmpty()) {
			Evento e = this.coda.poll();
			processEvent(e);
		}
	}

	private void processEvent(Evento e) {
		
		this.mediaReporterPerPartita += this.numReporterAssegnati.get(this.squadre.get(e.getMatch().getTeamHomeID())); 
		this.mediaReporterPerPartita += this.numReporterAssegnati.get(this.squadre.get(e.getMatch().getTeamAwayID())); 
		
		if(this.numReporterAssegnati.get(this.squadre.get(e.getMatch().getTeamHomeID()))+this.numReporterAssegnati.get(this.squadre.get(e.getMatch().getTeamAwayID()))<this.sogliaX) {
			this.numPartiteSottoSoglia++;
		}
				
		int risultato = e.getMatch().resultOfTeamHome;
		Team vincente = null;
		Team perdente = null;
		
		if(risultato==1) {
			vincente = this.squadre.get(e.getMatch().getTeamHomeID());	
			perdente = this.squadre.get(e.getMatch().getTeamAwayID());
		}
		else if(risultato==-1) {
			vincente = this.squadre.get(e.getMatch().getTeamAwayID());
			perdente = this.squadre.get(e.getMatch().getTeamHomeID());
		}
		else if(risultato==0) {
			// non fare nulla
		}
		
		
		if(Math.random()<0.5 && vincente!=null && perdente!=null) {
			Team teamPiuBlasonato = cercaTeamPiuBlasonato(vincente);
			int num = this.numReporterAssegnati.get(vincente);
			if(num>0 && teamPiuBlasonato!=null) {
				// diminuisco il numero di reporter per quella squadra e lo aumento per quella più blasonata
				this.numReporterAssegnati.replace(vincente, num-1);
				this.numReporterAssegnati.replace(teamPiuBlasonato,this.numReporterAssegnati.get(teamPiuBlasonato)+1);
			}
		}
		
		else if(Math.random()<0.2 && vincente!=null && perdente!=null) {
			Team teamMenoBlasonato = cercaTeamMenoBlasonato(perdente);
			int num = this.numReporterAssegnati.get(perdente);
			if(num>0 && teamMenoBlasonato!=null) {
				// calcolo quanti reporter vengono assegnati a un altra squadra
				int numCambi = (int) (Math.random()*this.numReporterAssegnati.get(teamMenoBlasonato));
				this.numReporterAssegnati.replace(vincente, num-numCambi);
				this.numReporterAssegnati.replace(teamMenoBlasonato,this.numReporterAssegnati.get(teamMenoBlasonato)+numCambi);
			}
		}
		
	}

	private Team cercaTeamMenoBlasonato(Team perdente) {
		Team t = null;
		for(Team team : this.squadre.values()) {
			if(team.getPunteggioInClassifica()<perdente.getPunteggioInClassifica()) {
				t = team;
				break;
			}
		}
		return t;
	}

	private Team cercaTeamPiuBlasonato(Team vincente) {
	    
		Team t = null;
		for(Team team : this.squadre.values()) {
			if(team.getPunteggioInClassifica()>vincente.getPunteggioInClassifica()) {
				t = team;
				break;
			}
		}
		return t;
	}
	
	public int getNumPartiteSottoSoglia() {
		return this.numPartiteSottoSoglia;
	}
	
	public double getMediaReporter() {
		
		return this.mediaReporterPerPartita = this.mediaReporterPerPartita/this.partite.size();
	}

}
