package it.polito.tdp.genes.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		model.creaGrafo();
		System.out.println(model.getAdiacenze("ER"));
		
		System.out.println(model.init("ER"));

	}

}
