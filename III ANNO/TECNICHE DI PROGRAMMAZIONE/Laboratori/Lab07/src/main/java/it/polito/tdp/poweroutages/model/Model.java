package it.polito.tdp.poweroutages.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<PowerOutage> migliore;
	private int numCoinvolti;
	private List<PowerOutage> eventi;
	
	public Model() {
		this.podao = new PowerOutageDAO();
	}
	
	
	public int getNumCoinvolti() {
		return numCoinvolti;
	}

	public void setNumCoinvolti(int numCoinvolti) {
		this.numCoinvolti = numCoinvolti;
	}


	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> getPowerOutagebyNerc(int nerc_id){
		return podao.getPOListbyNerc(nerc_id);
	}
	
	/*
	 * Metodo che cerca il sottoinsieme di eventi di blackout che massimizza il numero di persone coinvolte
	 * nel corso di un massimo di X anni, per un totale di Y ore di disservizio
	 */
	
	public List<PowerOutage> cercaSottoinsieme(Nerc nerc,int maxYears,double maxHours){
		
		this.migliore = new LinkedList<PowerOutage>();
		this.eventi = podao.getPOListbyNerc(nerc.getId());
		Collections.sort(eventi);
		this.numCoinvolti = 0;
		List<PowerOutage> parziale = new LinkedList<PowerOutage>();
		
		cerca(0,parziale,maxYears,maxHours);
		
		return migliore;
	}
	
	public void cerca(int livello, List<PowerOutage> parziale,int maxYears, double maxHours) {
		
		// caso terminale 
		if(this.calcolaPersoneCoinvolte(parziale)>this.numCoinvolti) {
			this.numCoinvolti = this.calcolaPersoneCoinvolte(parziale);
			this.migliore=new LinkedList<PowerOutage>(parziale);
		}
		

		for(PowerOutage prova : this.eventi) {
			if(!parziale.contains(prova) && aggiuntaValida(parziale,prova,maxHours,maxYears)) {
				parziale.add(prova);
				cerca(livello+1,parziale,maxYears,maxHours);
				parziale.remove(parziale.size()-1);
				
			}
		}
	}


	private boolean aggiuntaValida(List<PowerOutage> parziale, PowerOutage prova, double maxHours,int maxYears) {
		
		// per aggiungere un nuovo evento devo controllare che non venga superato il massimo 
		// numero di ore di disservizio e che non vengano coinvolti più di Y anni
		int anno=0;
		
		double oreDisservizio = prova.getOutageDuration();
		if(parziale.size()==1) {
			anno = parziale.get(0).getDate_event_began().getYear()+1900;
		}
		if(calcolaOreTotaliDisservizio(parziale)+ oreDisservizio <= maxHours) { 
			// se non supero le ore massime di disservizio
			// controllo di non superare il numero massimo di anni
			if(parziale.size()>=1 && (prova.getDate_event_finished().getYear()+1900)<=(parziale.get(0).getDate_event_began().getYear()+1900)&&(prova.getDate_event_finished().getYear()+1900)>=((parziale.get(0).getDate_event_began().getYear()+1900)-maxYears)) {
				return true;
			}
			if(parziale.size()==0)
				return true;
		}
		
		return false;
	}


	private int calcolaPersoneCoinvolte(List<PowerOutage> parziale) {
		
		int personeCoinvolte=0;
		for(PowerOutage p : parziale) {
			personeCoinvolte+=p.getCustomers_affected();
		}
		return personeCoinvolte;
	}


	public double calcolaOreTotaliDisservizio(List<PowerOutage> parziale) {
		
		double oreDisservizio=0;
		
		if(parziale.size()==0) {
			return 0;
		}
		else {
		for(PowerOutage p : parziale) {
			oreDisservizio+=p.getOutageDuration();

		}
		return oreDisservizio;
		}
	}

}
