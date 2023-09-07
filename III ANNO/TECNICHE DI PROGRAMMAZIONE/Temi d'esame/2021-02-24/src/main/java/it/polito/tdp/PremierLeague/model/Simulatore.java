package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.PremierLeague.model.Evento.EventType;

public class Simulatore {
	
	// dati in ingresso
	private Match m;
	private int numAzioniSalienti;
	
	// dati in uscita
	private TeamSimulator squadra1;
	private TeamSimulator squadra2;
	
	// coda degli eventi
	private PriorityQueue<Evento> coda;
	
	// stato del mondo simulato
	private Graph<Player,DefaultWeightedEdge> grafo;
	
	public Simulatore(Graph<Player,DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
		
	}
	
	public void init(Match m, int numAzioniSalienti, int teamPlayerMigliore) {
		this.m=m;
		this.numAzioniSalienti=numAzioniSalienti;
		
	    this.squadra1 = new TeamSimulator(m.getTeamHomeID(),m.getTeamHomeNAME(),11);
	    this.squadra2 = new TeamSimulator(m.getTeamAwayID(),m.getTeamAwayNAME(),11);
		
	    if(teamPlayerMigliore==(squadra1.getTeamID())) {
	    	squadra1.setPlayerMigliore(true);
	    }
	    else if(teamPlayerMigliore==(squadra2.getTeamID())) {
	    	squadra2.setPlayerMigliore(true);
	    }
		this.coda = new PriorityQueue<Evento>();
		
		// carico gli eventi nella coda
		this.creaEventi(m, numAzioniSalienti, coda);
		this.run();
	}
	
	private void creaEventi(Match m2, int numAzioniSalienti,PriorityQueue<Evento> coda) {
		
		for(int i=0 ;i<numAzioniSalienti; i++) {
			double numCasuale = Math.random();
			
			if(numCasuale<=0.5) {
				coda.add(new Evento(EventType.GOAL,i));
			}
			else if(numCasuale>0.5 && numCasuale<=0.8) {
				coda.add(new Evento(EventType.ESPULSIONE,i));
			}
			else if(numCasuale>0.8) {
				coda.add(new Evento(EventType.INFORTUNIO,i));
			}
		}
		
	}

	public void run() {
		
		while(!this.coda.isEmpty()) {
			Evento e = this.coda.poll();
			this.processEvent(e);
		}
	}

	private void processEvent(Evento e) {
		
		switch(e.getType()){
		
		case GOAL:
			if(this.squadra1.getNumGiocatori()>this.squadra2.getNumGiocatori()) {
				this.squadra1.incrementaNumGoal();
			}
			else if(this.squadra1.getNumGiocatori()<this.squadra2.getNumGiocatori()) {
				this.squadra2.incrementaNumGoal();;
			}
			else {
				if(squadra1.isPlayerMigliore()) {
					this.squadra1.incrementaNumGoal();;
				}
				else if(squadra2.isPlayerMigliore()) {
					this.squadra2.incrementaNumGoal();;
				}
			}
			break;
			
		case ESPULSIONE:
			
			double random = Math.random();
			if(random<=0.6) {
				if(squadra1.isPlayerMigliore()) {
					squadra1.decrementaNumGiocatori();
					squadra1.incrementaNumEspulsi();
				}
				else if(squadra2.isPlayerMigliore()) {
					squadra2.decrementaNumGiocatori();
					squadra2.incrementaNumEspulsi();
				}
			}
			else {
				if(!squadra1.isPlayerMigliore()) {
					squadra1.decrementaNumGiocatori();
					squadra1.incrementaNumEspulsi();
				}
				else if(!squadra2.isPlayerMigliore()) {
					squadra2.decrementaNumGiocatori();
					squadra2.incrementaNumEspulsi();
				
			    }
			}
			break;
		
		case INFORTUNIO:
			
			double num = Math.random();
			if(num<0.5) {
				this.creaEventi(m,2, coda);
			}
			if(num>=0.5) {
				this.creaEventi(m, 3, coda);
			}
			break;
			
		}
	}

	public TeamSimulator getSquadra1() {
		return squadra1;
	}

	public TeamSimulator getSquadra2() {
		return squadra2;
	}
	

}
