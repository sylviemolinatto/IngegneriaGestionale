package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country implements Comparable<Country>{
	
	private int cCode;
	private String stateAbb;
	private String stateNme;
	
	public Country(int cCode, String stateAbb, String stateNme) {
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateNme = stateNme;
	}

	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	public String getStateNme() {
		return stateNme;
	}

	public void setStateNme(String stateNme) {
		this.stateNme = stateNme;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return cCode == other.cCode;
	}

	@Override
	public String toString() {
		return this.stateNme;
	}

	@Override
	public int compareTo(Country o) {
		// TODO Auto-generated method stub
		return this.stateNme.compareTo(o.stateNme);
	}

	
	
	
	
	
}
