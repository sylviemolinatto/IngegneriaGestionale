package it.polito.tdp.PremierLeague.model;

public abstract class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo(0.3);
		model.getTopPlayer();
		model.getAvversariBattuti();

	}

}
