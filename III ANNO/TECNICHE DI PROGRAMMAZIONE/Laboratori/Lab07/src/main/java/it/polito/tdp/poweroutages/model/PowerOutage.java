package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class PowerOutage implements Comparable<PowerOutage> {

	private int PO_id;
	private int nerc_id;
	private int customers_affected;
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	private long outageDuration;
	
	public PowerOutage(int pO_id, int nerc_id, int customers_affected, LocalDateTime date_event_began,
			LocalDateTime date_event_finished) {
		super();
		PO_id = pO_id;
		this.nerc_id = nerc_id;
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		
		LocalDateTime tempDateTime = LocalDateTime.from(date_event_began);
		this.outageDuration = tempDateTime.until(date_event_finished, ChronoUnit.HOURS);
		
	}
	public int getPO_id() {
		return PO_id;
	}
	public void setPO_id(int pO_id) {
		PO_id = pO_id;
	}
	public int getNerc_id() {
		return nerc_id;
	}
	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}
	public int getCustomers_affected() {
		return customers_affected;
	}
	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}
	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}
	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}
	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}
	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}
	
	public long getOutageDuration() {
		return outageDuration;
	}
	public void setOutageDuration(long outageDuration) {
		this.outageDuration = outageDuration;
	}
	@Override
	public int hashCode() {
		return Objects.hash(PO_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		return PO_id == other.PO_id;
	}
	
	@Override
	public String toString() {
		return this.getDate_event_began().getYear()+" "+this.getDate_event_began()+" "+this.getDate_event_finished()+" "+this.outageDuration+" "+this.customers_affected;
	}
	@Override
	public int compareTo(PowerOutage o) {
		// TODO Auto-generated method stub
		return this.getDate_event_began().compareTo(o.getDate_event_began());
	}
	
	
	
	
	
}
