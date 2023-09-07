package it.polito.tdp.parole.model;

import java.util.List;
import java.util.*;

public class Parole {
	
	List<String> listaParole;
	
	public Parole() {
		//TODO
		this.listaParole = new LinkedList<String>();
	}
	
	public Parole(List l) {
		this.listaParole= new LinkedList<String>(l);
	}
	
	public void addParola(String p) {
		this.listaParole.add(p);
		//TODO
	}
	
	public void removeParola(String p) {

		/* for(int i=0;i<this.listaParole.size();i++) {
			if(this.listaParole.get(i).equals(p)) {
			    this.listaParole.remove(i);
			}
		} */
		
		this.listaParole.remove(p);
	}
	
	public List<String> getElenco() {
		//TODO
		
		Collections.sort(this.listaParole);
		return this.listaParole;
	}
	
	public void reset() {
		// TODO
		this.listaParole.clear();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ss="";
		for(String s : this.listaParole) {
			ss+="\n"+s;
		}
		return ss;
	}
	
	

}
