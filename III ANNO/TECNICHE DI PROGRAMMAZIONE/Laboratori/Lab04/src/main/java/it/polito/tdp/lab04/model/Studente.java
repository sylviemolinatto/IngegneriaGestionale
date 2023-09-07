package it.polito.tdp.lab04.model;

import java.util.Objects;

public class Studente {
	
	Integer matricola;
	String cognome;
	String nome;
	String CDS;
	
	public Studente(Integer matricola, String cognome, String nome, String cDS) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		CDS = cDS;
	}

	public Integer getMatricola() {
		return this.matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCDS() {
		return this.CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricola);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		return Objects.equals(matricola, other.matricola);
	}

	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", cognome=" + cognome + ", nome=" + nome + ", CDS=" + CDS + "]";
	}
	
	
	
	

}
