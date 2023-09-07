package it.polito.tdp.anagrammi.model;

import java.util.List;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {
    
	AnagrammaDAO anagrammaDAO;
	Anagramma_Ricorsiva anagrammaRicorsiva;
	
	public Model() {
		this.anagrammaDAO=new AnagrammaDAO();
		this.anagrammaRicorsiva = new Anagramma_Ricorsiva();
	}
	
	public boolean isCorrect(String anagramma) {
		return this.anagrammaDAO.isCorrect(anagramma);
	}
	
	public List<Anagramma> anagramma(String s){
		return this.anagrammaRicorsiva.anagramma(s);
	}
	
	
}
