package it.polito.tdp.meteo.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		System.out.println(m.getUmiditaMedia(12,"Genova"));
		
		System.out.println(m.trovaSequenza(5));
		

	}

}
