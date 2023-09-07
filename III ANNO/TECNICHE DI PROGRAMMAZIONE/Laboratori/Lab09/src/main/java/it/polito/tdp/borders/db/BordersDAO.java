package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				// System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				result.add(new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		String sql = "SELECT c.state1no,c.state2no,c.year,c.conttype "
				+ "FROM contiguity c "
				+ "WHERE c.year<=? AND c.conttype=1 "
				+ "GROUP BY c.dyad";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				result.add(new Border(rs.getInt("state1no"),rs.getInt("state2no"),rs.getInt("year"),rs.getInt("conttype")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
		// System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		// return new ArrayList<Border>();
	}
	
	public List<Border> getCountryPairs() {
		
		String sql = "SELECT c.state1no,c.state2no,c.year,c.conttype "
				+ "FROM contiguity c "
				+ "WHERE c.conttype=1 "
				+ "GROUP BY c.dyad";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				result.add(new Border(rs.getInt("state1no"),rs.getInt("state2no"),rs.getInt("year"),rs.getInt("conttype")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
		
}
