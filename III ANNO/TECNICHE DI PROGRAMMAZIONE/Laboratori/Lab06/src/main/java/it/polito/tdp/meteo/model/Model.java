package it.polito.tdp.meteo.model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	private List<Citta> allCitta;
	private List<Citta> best;

	public Model() {
		MeteoDAO dao = new MeteoDAO();
		this.allCitta = dao.getAllCitta();
	}

	// of course you can change the String output with what you think works best
	public Double getUmiditaMedia(int mese, String localita) {
		MeteoDAO dao = new MeteoDAO();
		return dao.getAvgRilevamentiLocalitaMese(mese, localita);
	}
	
	public List<Citta> getAllCitta(){
		MeteoDAO dao = new MeteoDAO();
		return dao.getAllCitta();
	}
	
	// of course you can change the String output with what you think works best
	public List<Citta> trovaSequenza(int mese) {
	 
		List<Citta> parziale = new ArrayList<Citta>();
		this.best=null;
		
		MeteoDAO dao = new MeteoDAO();
		
		for(Citta c : allCitta) {
			c.setRilevamenti(dao.getAllRilevamentiLocalitaMese(mese, c.getNome()));
		}
		ricorsiva(parziale,0);
		return best;
	}
	
	public void ricorsiva(List<Citta> parziale, int L) {
		
		// caso terminale
		if(L==this.NUMERO_GIORNI_TOTALI) {
			double costo = calcolaCosto(parziale);
			
			if(best==null || costo < calcolaCosto(best)) {
				best = new ArrayList<Citta>(parziale);
			}
		}
		
		else {
			
			for(Citta prova : allCitta) {
				if(aggiuntaValida(parziale,prova)) {
					parziale.add(prova);
					ricorsiva(parziale,L+1);
					parziale.remove(parziale.size()-1); //backtracking
					
				}
			}
		}
		
	}
	
	private boolean aggiuntaValida(List<Citta> parziale, Citta prova) {
		
		int contatore=0;
		for(Citta c : parziale) {
			if(c.equals(prova)) {
				contatore+=1;
			}
		}
		
		// se la città che vogliamo inserire è già stat visitata almeno 6 volte non possso più inserirla
		if(contatore>=this.NUMERO_GIORNI_CITTA_MAX) {
			return false;
		}
		
		// se stiamo decidendo la città del primo giorno non c'è alcun limite
		if(parziale.size()==0) {
			return true;
		}
		
		// se stiamo cercando di aggiungere la seconda o terza città dobbiamo controllare che le
		// due città precedenti siano uguali
		if(parziale.size()==1 || parziale.size()==2) {
			return parziale.get(parziale.size()-1).equals(prova);
		}
		
		// se stiamo cercando di aggiungere una città uguale alla precedente non ci sono problemi
		if(parziale.get(parziale.size()-1).equals(prova)) {
			return true;
		}
		
		// se stiamo cercando di cambiare città dobbiamo controllare che vi sia la stessa città nei 3 giorni precedenti
		if(parziale.get(parziale.size()-1).equals(parziale.get(parziale.size()-2)) && parziale.get(parziale.size()-2).equals(parziale.get(parziale.size()-3))){
			return true;
		}
		
		return false;
	}

	private Double calcolaCosto(List<Citta> parziale) {
		
		double costo=0.0;
		for(int giorno=1;giorno<=this.NUMERO_GIORNI_TOTALI;giorno++) {
			Citta c = parziale.get(giorno-1);
			double umidita = c.getRilevamenti().get(giorno-1).getUmidita();
			costo+=umidita;
		}
		
		for(int giorno=2;giorno<=this.NUMERO_GIORNI_TOTALI;giorno++) {
			if(!parziale.get(giorno-1).equals(parziale.get(giorno-2))) {
				costo+=this.COST;
			}
		}
		
		return costo;
		
	}
	
	

}
