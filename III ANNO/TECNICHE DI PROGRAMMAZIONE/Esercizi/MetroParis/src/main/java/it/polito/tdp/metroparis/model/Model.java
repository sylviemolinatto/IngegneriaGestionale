package it.polito.tdp.metroparis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
    private List<Fermata> fermate;
	private Graph<Fermata,DefaultEdge> grafo;
	private Map<Integer,Fermata> fermateIdMap;
	
	public List<Fermata> getFermate(){
		if(this.fermate==null) {
			MetroDAO dao = new MetroDAO();
			this.fermate = dao.getAllFermate();
			
			this.fermateIdMap= new HashMap<Integer,Fermata>();
			for(Fermata f : this.fermate) {
				this.fermateIdMap.put(f.getIdFermata(), f);
			}
			
		}
		return fermate;
	}
	
	private void creaGrafo() {
		// grafo semplice orientato e non pesato SimpleDirectedGraph
		this.grafo = new SimpleDirectedGraph<Fermata,DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(this.grafo, getFermate());
		MetroDAO dao = new MetroDAO();
		
		// METODO 1 : itero su ogni coppia di vertici
		/* for(Fermata partenza : fermate) {
			for(Fermata arrivo : fermate) {
				// esiste almeno una connessione tra partenza e arrivo
				if(dao.isFermateConnesse(partenza, arrivo)) {
					this.grafo.addEdge(partenza, arrivo);
				}
			}
		} */
		
		// METODO 2 : dato ciascun vertice, trova i vertici ad esso adiacenti
		// Variante 2a: il DAO restituisce un elenco di ID numerici
		
		//Nota: posso iterare su 'fermate' oppure du 'this.grafo.vertexSet()'
		/* for(Fermata partenza: fermate) {
			List<Integer> idConnesse = dao.getIdFermateConnesse(partenza);
			for(Integer id : idConnesse) {
				 // Fermata arrivo = fermata che possiede questo 'id'
				
				Fermata arrivo = null;
				for(Fermata f : fermate) {
					if(f.getIdFermata()==id) {
						arrivo = f;
						break;
					}
				}
				
				this.grafo.addEdge(partenza, arrivo);
			}
		} */
		
		// METODO 2 : dato ciascun vertice, trova i vertici ad esso adiacenti
		// Variante 2b: il DAO restituisce un elenco di oggetti Fermata
		/* for(Fermata partenza: fermate) {
			List<Fermata> arrivi = dao.getFermateConnesse(partenza);
			for(Fermata arrivo : arrivi) {
				this.grafo.addEdge(partenza,arrivo);
			}
		} */
		
		// METODO 2 : dato ciascun vertice, trova i vertici ad esso adiacenti
	    // Variante 2c: il DAO restituisce un elenco di ID numerici, che converto in oggetti
	    // tramite una Map<Integer,Fermata> - "Identity Map"
		/* for(Fermata partenza : fermate) {
			List<Integer> idConnesse = dao.getIdFermateConnesse(partenza);
			for(int id : idConnesse) {
				Fermata arrivo = fermateIdMap.get(id);
				this.grafo.addEdge(partenza,arrivo);
			}
		} */
		
		// METODO 3 : faccio una sola query che mi restituisca le coppie di fermate da collegare
		// (variante preferita: 3c: usare Identify Map)
		List<CoppiaID> fermateDaCollegare = dao.getAllFermateConnesse();
		for(CoppiaID coppia : fermateDaCollegare) {
			this.grafo.addEdge(fermateIdMap.get(coppia.getIdPartenza()),fermateIdMap.get(coppia.getIdArrivo()));
		}
		
		// System.out.println(this.grafo);
		// System.out.println("Vertici = "+this.grafo.vertexSet().size());
		// System.out.println("Archi = "+this.grafo.edgeSet().size());
		// visitaGrafo(fermate.get(0));
	}
	
	public List<Fermata> calcolaPercorso(Fermata partenza, Fermata arrivo){
		creaGrafo();
		Map<Fermata,Fermata> alberoInverso = visitaGrafo(partenza);
		
		Fermata corrente = arrivo;
		List<Fermata> percorso = new ArrayList();
		
		while(corrente!=null) {
			percorso.add(0,corrente);
			corrente = alberoInverso.get(corrente);
		}
		return percorso;
	}
	
	public Map<Fermata,Fermata> visitaGrafo(Fermata partenza) {
		GraphIterator<Fermata,DefaultEdge> visita = new BreadthFirstIterator<>(this.grafo,partenza);
		
		Map<Fermata,Fermata> alberoInverso = new HashMap<Fermata,Fermata>();
		alberoInverso.put(partenza, null);
		
		visita.addTraversalListener(new RegistraAlberoDiVisita(alberoInverso,this.grafo));
		
		while(visita.hasNext()) {
			Fermata f = visita.next();
			// System.out.println(f);
		}
		
		return alberoInverso;
	}
}
