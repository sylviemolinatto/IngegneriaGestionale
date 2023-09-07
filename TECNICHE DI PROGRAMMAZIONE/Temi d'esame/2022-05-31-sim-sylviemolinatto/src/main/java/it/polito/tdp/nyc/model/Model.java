package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private Graph<String,DefaultWeightedEdge> grafo;
	
	public Model() {
		this.dao = new NYCDao();
	}
	
	public Graph<String,DefaultWeightedEdge> creaGrafo(String provider) {
		
		this.grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(provider));
		
		// aggiungo gli archi
		for(Edge e : this.dao.getArchi(provider)) {
			LatLng latlong1 = new LatLng(e.getLat_l1(),e.getLong_l1());
			LatLng latlong2 = new LatLng(e.getLat_l2(),e.getLong_l2());
			e.setWeight(LatLngTool.distance(latlong1, latlong2,LengthUnit.KILOMETER));
			Graphs.addEdgeWithVertices(this.grafo, e.getCity1(), e.getCity2(), e.getWeight());
			
		}
		
		System.out.println("GRAFO CREATO!\n");
		System.out.println("#VERTICI : "+this.grafo.vertexSet().size()+"\n");
		System.out.println("#ARCHI : "+this.grafo.edgeSet().size()+"\n");
		return this.grafo;
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<String> getVertici(String provider){
		return this.dao.getVertici(provider);
	}
	
	public List<String> getProvider(){
		return this.dao.getProvider();
	}
	
	public List<Edge> getQuartieriAdiacenti(String quartiere){
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, quartiere);
		List<Edge> adiacenti = new ArrayList<Edge>();
		for(String s : vicini) {
			adiacenti.add(new Edge(quartiere,s,this.grafo.getEdgeWeight(this.grafo.getEdge(quartiere, s))));
		}
		return adiacenti;
	}
	
	public List<String> getListQuartieriAdiacenti(String quartiere){
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, quartiere);
		return vicini;
	}
	
	public boolean grafoCreato() {
		if(this.grafo == null)
			return false;
		else 
			return true;
	}

	public long simula(Integer numTecnici, String quartiere, String provider) {
		
		Simulatore sim = new Simulatore(this.grafo);
		sim.init(numTecnici, provider, quartiere, this.getListQuartieriAdiacenti(quartiere));
		sim.run();
		return sim.getDurata();
		
	}
	
	public Map<Integer,Operatore> getHotspotRevisionatiDaOperatori(Integer numTecnici, String quartiere, String provider){
		Simulatore sim = new Simulatore(this.grafo);
		sim.init(numTecnici, provider, quartiere, this.getListQuartieriAdiacenti(quartiere));
		sim.run();
		return sim.getHotspotRevisionatiDaOperatori();
	}
	
	
	
}
