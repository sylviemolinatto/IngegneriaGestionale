package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiGliStudenti(){
		return this.studenteDAO.getTuttiGliStudenti();
	}
	
	public String getNomeStudente(Integer matricola) {
		return this.studenteDAO.getNomeStudente(matricola);
	}
	
	public String getCognomeStudente(Integer matricola) {
		return this.studenteDAO.getCogomeStudente(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudente(Integer matricola) {
		return this.studenteDAO.getStudente(matricola);
	}
	
	public List<Corso> getCorsiStudente(Integer matricola){
		return this.studenteDAO.getCorsiStudente(matricola);
	}
	
	public boolean studenteIsIscritto(Studente s, Corso c) {
		return this.corsoDAO.studenteIsIscritto(s, c);
	}
	
	public boolean IscriviStudenteACorso(Studente s, Corso c) {
		return this.corsoDAO.inscriviStudenteACorso(s, c);
	}

}
