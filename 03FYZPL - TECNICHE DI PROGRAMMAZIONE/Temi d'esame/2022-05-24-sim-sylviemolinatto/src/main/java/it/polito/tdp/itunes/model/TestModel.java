package it.polito.tdp.itunes.model;

public abstract class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo(new Genre(2,"Rock"));

	}

}
