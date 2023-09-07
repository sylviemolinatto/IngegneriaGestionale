package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

public class Anagramma_Ricorsiva {
	
	List<Anagramma> tutteLeSoluzioni;
	
	public Anagramma_Ricorsiva() {
		
	}

	public List<Anagramma> anagramma(String s) {
		
		this.tutteLeSoluzioni = new ArrayList<Anagramma>();
		Anagramma parziale = new Anagramma();
		this.anagrammaRicorsiva(parziale.getAnagramma(), 0, s);
		return this.tutteLeSoluzioni;
		
	}
	
	public void anagrammaRicorsiva(String parziale, int livello, String rimanenti){
		
		//caso terminale
		if(rimanenti.length()==0) {
			this.tutteLeSoluzioni.add(new Anagramma(parziale));
		}
		else {
			for (int pos = 0; pos < rimanenti.length(); pos++) {
				String nuova_parziale = parziale + rimanenti.charAt(pos);
				String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1);
				anagrammaRicorsiva(nuova_parziale, livello + 1, nuova_rimanenti);
			}
		}
	}
}
