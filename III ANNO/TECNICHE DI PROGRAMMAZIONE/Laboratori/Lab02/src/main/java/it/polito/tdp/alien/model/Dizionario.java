package it.polito.tdp.alien.model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dizionario {
	
	Map<String,Traduzione> listaParoleTraduzioni;

	public Dizionario() {
		this.listaParoleTraduzioni = new HashMap<String,Traduzione>();
	}
	
	public void addParola(String parola, String traduzione) {
		this.listaParoleTraduzioni.put(parola,new Traduzione(traduzione));
	}
	
	public Traduzione cercaTraduzione(String parola) {
		return this.listaParoleTraduzioni.get(parola);
	}
	
	public String cercaTraduzioneWildCard(String parola) {
		
		parola = parola.replaceAll("\\?", ".");
		int matchCounter = 0;
		StringBuilder sb = new StringBuilder();
		
		for(String s : this.listaParoleTraduzioni.keySet()) {
			if(s.matches(parola)) {
				matchCounter++;
				sb.append(this.listaParoleTraduzioni.get(s).getTraduzioni()+"\n");
			}
		}
		if(matchCounter!=0) {
			return sb.toString();
		}
		else
			return null; 
	}
	
	public void aggiungiTraduzione(String parola,String traduzione) {
		this.listaParoleTraduzioni.get(parola).addTraduzione(traduzione);
	}
	
	public boolean parolaIsLegit(String parola,String traduzione) {
		
		String pattern = "[a-zA-Z]*";
		if(parola.matches(pattern) && traduzione.matches(pattern)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean parolaIsLegit(String parola) {
		
		String pattern = "[a-zA-Z]*";
		if(parola.matches(pattern)) {
			return true;
		}
		else {
			return false;
		}
	}

	public Map<String, Traduzione> getListaParoleTraduzioni() {
		return listaParoleTraduzioni;
	}
	
	

}
