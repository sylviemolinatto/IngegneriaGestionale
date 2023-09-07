package it.polito.tdp.lab04.DAO;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		/* List<Corso> corsi = new LinkedList<Corso>(cdao.getTuttiICorsi());
		List<String> codiciCorsi = new LinkedList<String>();
		
		for(Corso c : corsi) {
			codiciCorsi.add(c.getCodins());
			System.out.println(c.getCodins());
		} */
		StudenteDAO sdao = new StudenteDAO();
		String nome = sdao.getNomeStudente(146101);
		System.out.println(nome);
		
		
	}

}
