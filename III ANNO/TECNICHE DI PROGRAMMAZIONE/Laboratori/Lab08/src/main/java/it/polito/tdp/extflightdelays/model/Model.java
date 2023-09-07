package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport,DefaultWeightedEdge> grafo;
	
	public Graph creaGrafo(double distanzaMinima) {
		
		// grafo semplice, non orientato e pesato
		this.grafo = new SimpleWeightedGraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List<Airport> aeroporti = dao.loadAllAirports();
		Map<Integer,Airport> aeroportiMap = new HashMap<Integer,Airport>();
		
		for(Airport a : aeroporti) {
			aeroportiMap.put(a.getId(), a);
		}
		
		Graphs.addAllVertices(this.grafo,aeroporti);
		
		List<CoppiaID> aeroportiDistanzaMinima = dao.getAllAirportsbyDistance(distanzaMinima);
		
		for(CoppiaID c : aeroportiDistanzaMinima) {
			
			int originAirportId = c.getOriginAirportId();
			int destinationAirportId = c.getDestinationAirportId();
			Airport origin = aeroportiMap.get(originAirportId);
			Airport destination = aeroportiMap.get(destinationAirportId);
			// this.grafo.addVertex(origin);
			// this.grafo.addVertex(destination);
			this.grafo.addEdge(origin, destination);
			this.grafo.setEdgeWeight(origin, destination, c.getAvdDistance());
		}
		
		/* System.out.println(this.grafo);
		System.out.println("Vertici = "+this.grafo.vertexSet().size());
		System.out.println("Archi = "+this.grafo.edgeSet().size());
		for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
			System.out.println(this.grafo.getEdgeWeight(d)+"\n");
		} */
		
		return this.grafo;
	}
	
	
}
