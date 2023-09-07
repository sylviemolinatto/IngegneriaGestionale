package it.polito.tdp.nyc.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo("SPECTRUM");
		
		//Simulatore sim = new Simulatore(model.creaGrafo("SPECTRUM"));
		//sim.init(15,"Spot On Networks","Queens");
		//System.out.println(sim.getDurata());
		//System.out.println(sim.getHotspotRevisionatiDaOperatori());
	}
}
