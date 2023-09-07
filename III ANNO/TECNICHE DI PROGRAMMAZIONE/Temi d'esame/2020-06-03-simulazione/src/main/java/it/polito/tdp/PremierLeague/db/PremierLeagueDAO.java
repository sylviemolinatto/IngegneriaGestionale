package it.polito.tdp.PremierLeague.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.PremierLeague.model.Action;
import it.polito.tdp.PremierLeague.model.Edge;
import it.polito.tdp.PremierLeague.model.Player;

public class PremierLeagueDAO {
	
	public List<Player> listAllPlayers(){
		String sql = "SELECT * FROM Players";
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Player player = new Player(res.getInt("PlayerID"), res.getString("Name"));
				
				result.add(player);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Action> listAllActions(){
		String sql = "SELECT * FROM Actions";
		List<Action> result = new ArrayList<Action>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Action action = new Action(res.getInt("PlayerID"),res.getInt("MatchID"),res.getInt("TeamID"),res.getInt("Starts"),res.getInt("Goals"),
						res.getInt("TimePlayed"),res.getInt("RedCards"),res.getInt("YellowCards"),res.getInt("TotalSuccessfulPassesAll"),res.getInt("totalUnsuccessfulPassesAll"),
						res.getInt("Assists"),res.getInt("TotalFoulsConceded"),res.getInt("Offsides"));
				
				result.add(action);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Player> getVertici(double goalFatti){
		
		final String sql = "SELECT p.PlayerID, p.Name "
				+ "FROM actions a, players p "
				+ "where a.PlayerID=p.PlayerID "
				+ "group BY a.PlayerID "
				+ "having AVG(a.Goals)>?";
		
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, goalFatti);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(new Player(res.getInt("PlayerID"),res.getString("Name")));

			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Edge> getArchi(double goalFatti){
		
		final String sql = "SELECT distinct a1.PlayerID as p1, a2.PlayerID as p2, SUM(a1.TimePlayed-a2.TimePlayed) as peso "
				+ "FROM actions a1, actions a2 "
				+ "WHERE a1.`Starts`=1 AND a2.`Starts`=1 AND a1.MatchID=a2.MatchID AND a1.TeamID<>a2.TeamID AND a1.PlayerID<>a2.PlayerID AND "
				+ "a1.PlayerID IN (SELECT a.PlayerID "
				+ "                FROM actions a "
				+ "                group BY a.PlayerID "
				+ "                having AVG(a.Goals)>? "
				+ "                ORDER BY a.PlayerID) "
				+ "AND a2.PlayerID IN (SELECT a.PlayerID "
				+ "                FROM actions a "
				+ "                group BY a.PlayerID "
				+ "                having AVG(a.Goals)>? "
				+ "                ORDER BY a.PlayerID) "
				+ "GROUP BY a1.PlayerID,a2.PlayerID "
				+ "HAVING SUM(a1.TimePlayed-a2.TimePlayed)>0";
		
		List<Edge> result = new ArrayList<Edge>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, goalFatti);
			st.setDouble(2, goalFatti);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Edge edge = new Edge(res.getInt("p1"),res.getInt("p2"),res.getInt("peso"));
				result.add(edge);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
