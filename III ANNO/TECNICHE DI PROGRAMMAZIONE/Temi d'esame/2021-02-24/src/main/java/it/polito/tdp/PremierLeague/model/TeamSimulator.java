package it.polito.tdp.PremierLeague.model;

import java.util.List;

public class TeamSimulator {

	private int TeamID;
	private String name;
	private int numGiocatori;
	private int numGoal;
	private int numEspulsi;
	private boolean playerMigliore;
	
	public TeamSimulator(int teamID, String name, int numGiocatori) {
		TeamID = teamID;
		this.name = name;
		this.numGiocatori = numGiocatori;
	}

	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumGiocatori() {
		return numGiocatori;
	}

	public void decrementaNumGiocatori() {
		this.numGiocatori = this.numGiocatori-1;
	}

	public int getNumGoal() {
		return numGoal;
	}

	public void incrementaNumGoal() {
		this.numGoal = this.numGoal+1;
	}

	public int getNumEspulsi() {
		return numEspulsi;
	}

	public void incrementaNumEspulsi() {
		this.numEspulsi = this.numEspulsi+1;
	}

	public boolean isPlayerMigliore() {
		return playerMigliore;
	}

	public void setPlayerMigliore(boolean playerMigliore) {
		this.playerMigliore = playerMigliore;
	}
	
	

	
	
	
	
	
	
	
}
