package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	String parolaInput;
	boolean isCorrect;
	
	public RichWord(String parolaInput, boolean isCorrect) {
		this.parolaInput = parolaInput;
		this.isCorrect = isCorrect;
	}
	
	public String getParolaInput() {
		return parolaInput;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	

}
