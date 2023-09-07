package it.polito.tdp.alien.model;

import java.util.LinkedList;
import java.util.List;

public class Traduzione {
	
	List<String> traduzioni;
	
	public Traduzione() {
		traduzioni = new LinkedList<String>();
	}
	
	public Traduzione(String traduzione) {
		traduzioni = new LinkedList<String>();
		this.traduzioni.add(traduzione);
	}
	
	public void addTraduzione(String traduzione) {
		this.traduzioni.add(traduzione);
	}
	
	public List<String> getTraduzioni(){
		return this.traduzioni;
	}

	@Override
	public String toString() {
		String s="";
		for(String traduzione: this.traduzioni) {
			if(s=="") {
				s+=traduzione;
			}
			else {
				s+="\n"+traduzione;
			}
		}
		return s;
	}
	
	

}
