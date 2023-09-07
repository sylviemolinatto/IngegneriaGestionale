package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 * Ottengo tutti gli studenti salvati nel Db
	 */
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				// System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				// Crea un nuovo JAVA Bean Studente
				// Aggiungi il nuovo oggetto Studente alla lista studenti
				studenti.add(new Studente(matricola,cognome,nome,CDS));
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public String getNomeStudente(Integer matricola) {
		
		String nome=null;
		boolean trovato = false;
		List<Studente> studenti = new LinkedList<Studente>(this.getTuttiGliStudenti());
	    for(Studente s : studenti) {
	    	if(trovato==false) {
	    		if(s.getMatricola().equals(matricola)) {
		    		nome = s.getNome();
		    		trovato=true;
		    	}
	    	}
	    	
	    }
	    
	    return nome;
	}
	
	public String getCogomeStudente(Integer matricola) {
		
		String cognome=null;
		boolean trovato = false;
		List<Studente> studenti = new LinkedList<Studente>(this.getTuttiGliStudenti());
	    for(Studente s : studenti) {
	    	if(trovato==false) {
	    		if(s.getMatricola().equals(matricola)) {
		    		cognome = s.getCognome();
		    		trovato=true;
		    	}
	    	}
	    	
	    }
	    
	    return cognome;
	}
	/*
	 * Data una matricola ottengo l'oggetto di classe studente
	 */
	public Studente getStudente(Integer matricola) {
		
		Studente studente=null;
		boolean trovato=false;
		List<Studente> studenti = this.getTuttiGliStudenti();
		
		for(Studente s : studenti) {
			if(s.getMatricola().equals(matricola) && trovato==false) {
				studente=s;
				trovato=true;
			}
		}
		return studente;
	}
	
	/*
	 * Data una matricola ottengo tutti i corsi a cui è iscritta
	 */
	
	public List<Corso> getCorsiStudente(Integer matricola){
		
		List<Corso> corsiStudente = new LinkedList<Corso>();
		
		final String sql = "SELECT * "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins=i.codins && i.matricola=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				Integer crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				Integer pd = rs.getInt("pd");

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista CorsiStudente
				corsiStudente.add(new Corso(codins, crediti, nome, pd));
			}

			conn.close();
			
			return corsiStudente;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	

}
