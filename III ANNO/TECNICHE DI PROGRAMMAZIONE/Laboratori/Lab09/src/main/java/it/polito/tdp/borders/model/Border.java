package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {
	
	private int cCodeState1;
	private int cCodeState2;
	private int year;
	private int conttype;
	
	public Border(int cCodeState1, int cCodeState2, int year, int conttype) {
		this.cCodeState1 = cCodeState1;
		this.cCodeState2 = cCodeState2;
		this.year = year;
		this.conttype = conttype;
	}

	public int getcCodeState1() {
		return cCodeState1;
	}

	public void setcCodeState1(int cCodeState1) {
		this.cCodeState1 = cCodeState1;
	}

	public int getcCodeState2() {
		return cCodeState2;
	}

	public void setcCodeState2(int cCodeState2) {
		this.cCodeState2 = cCodeState2;
	}

	public int getYear() {
		return year;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(cCodeState1, cCodeState2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		return cCodeState1 == other.cCodeState1 && cCodeState2 == other.cCodeState2;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getConttype() {
		return conttype;
	}

	public void setConttype(int conttype) {
		this.conttype = conttype;
	}

	@Override
	public String toString() {
		return "Border [cCodeState1=" + cCodeState1 + ", cCodeState2=" + cCodeState2 + ", year=" + year + ", conttype="
				+ conttype + "]";
	}
	
}
