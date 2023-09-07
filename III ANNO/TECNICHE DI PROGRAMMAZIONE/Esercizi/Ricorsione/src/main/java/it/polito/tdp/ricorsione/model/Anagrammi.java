package it.polito.tdp.ricorsione.model;

public class Anagrammi {

	/**
	 * Riceve una stringa e stampa tutti i suoi anagrammi
	 * 
	 * @param s
	 */
	public void anagramma(String s) {
		anagramma_ricorsiva("", 0, s);
	}

	/**
	 * Data una soluzione parziale, stampa TUTTI gli anagrammi che iniziano in quel
	 * modo.
	 * 
	 * @param parziale  Soluzione parziale, iniziale, del mio anagramma. Avrà
	 *                  'livello' caratteri.
	 * @param livello   Livello della ricorsione
	 * @param rimanenti I caratteri ancora da sistemare
	 */
	private void anagramma_ricorsiva(String parziale, int livello, String rimanenti) {
		if (rimanenti.length() == 0) { /* caso terminale */
			System.out.println(parziale);
		} else {
			/* caso normale */
			// es: parziale="AC", livello=2, rimanenti="BD"
			for (int pos = 0; pos < rimanenti.length(); pos++) {
				String nuova_parziale = parziale + rimanenti.charAt(pos);
				String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1);
				anagramma_ricorsiva(nuova_parziale, livello + 1, nuova_rimanenti);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagrammi main = new Anagrammi();
		main.anagramma("ABCD");
	}

}
