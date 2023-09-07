package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class ReginePrimaSoluzione {
	
	private List<Integer> soluzione;

	public List<Integer> cercaRegine(int N) {
		
		this.soluzione=null;
		List<Integer> parziale = new ArrayList<Integer>();
		regine_ricorsiva(parziale,0,N);
		return this.soluzione;
	}
	
	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N){
		
		if(livello==N) { // caso terminale
			//System.out.println(parziale);
			this.soluzione = new ArrayList<Integer>(parziale);
			return false; //non continuare!
		}
		else {
			// ho già parziale[0] fino a parziale[livello-1] già decise
			// devo decidere parziale[livello] tra tutti i valori possibili
			// da 0 a N-1 (colonne), purchè compatibili
			
			for(int col=0;col<N;col++) {
				if(compatibile(livello, col, parziale)) { // if(col è compatibile con paziale)
					parziale.add(col);
					boolean continua = regine_ricorsiva(parziale, livello+1, N);
					if(!continua) {
						return false;
					}
					parziale.remove(parziale.size()-1); // backtracking (ritornare sui propri passi)
				}
			}
			return true;
		}
	}
	
	private boolean compatibile(int livello, int col, List<Integer> parziale) {
		
		if(parziale.indexOf(col)!=-1) {
			return false;
		}
		
		for(int riga=0; riga<parziale.size();riga++) {
			// regina alle coordinate (R,C)=(riga,parziale.get(riga))
			// confrontare con (R,C)=(livello,col)
			if(riga+parziale.get(riga)==livello+col)
				return false;
			if(riga-parziale.get(riga)==livello-col)
				return false;
		}
		return true;
	}
}
