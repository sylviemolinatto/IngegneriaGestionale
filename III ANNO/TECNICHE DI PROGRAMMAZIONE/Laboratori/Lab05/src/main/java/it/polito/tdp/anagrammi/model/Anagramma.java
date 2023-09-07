package it.polito.tdp.anagrammi.model;

import java.util.Objects;

public class Anagramma {
	
	private String anagramma;
	private boolean isCorretto;

	public Anagramma() {
		this.anagramma="";
		this.isCorretto=false;
	}
	
	public Anagramma(String anagramma) {
		this.anagramma = anagramma;
	}

	public String getAnagramma() {
		return anagramma;
	}

	public void setAnagramma(String anagramma) {
		this.anagramma = anagramma;
	}

	public boolean isCorretto() {
		return isCorretto;
	}

	public void setCorretto(boolean isCorretto) {
		this.isCorretto = isCorretto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anagramma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anagramma other = (Anagramma) obj;
		return Objects.equals(anagramma, other.anagramma);
	}

	@Override
	public String toString() {
		return "Anagramma [anagramma=" + anagramma + "]";
	}
	
	
	
	
	
	
	
	

}
