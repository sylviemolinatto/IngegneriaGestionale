package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.nyc.model.Edge;
import it.polito.tdp.nyc.model.Hotspot;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getProvider(){
		
		String sql = "SELECT distinct l.Provider as l "
				+ "FROM  nyc_wifi_hotspot_locations l";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("l"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getVertici(String provider){
		
		String sql="SELECT DISTINCT l.City as c "
				+ "FROM  nyc_wifi_hotspot_locations l "
				+ "WHERE l.Provider=? ";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("c"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Edge> getArchi(String provider){
		
		String sql = "SELECT l1.City as c1, l2.City as c2, AVG(l1.Latitude) AS lat_l1, AVG(l1.Longitude) AS long_l1, AVG(l2.Latitude) AS lat_l2, AVG(l2.Longitude) AS long_l2 "
				+ "FROM  nyc_wifi_hotspot_locations l1, nyc_wifi_hotspot_locations l2 "
				+ "WHERE l1.Provider=? AND l2.Provider=l1.Provider AND l1.City>l2.City "
				+ "GROUP BY l1.City,l2.City ";
		List<Edge> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Edge(res.getString("c1"),res.getString("c2"), res.getDouble("lat_l1"),res.getDouble("long_l1"),res.getDouble("lat_l2"),res.getDouble("long_l2")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getHotspotByProviderAndCity(String provider, String city){
		
		String sql = "SELECT DISTINCT l.OBJECTID as hotspot "
				+ "FROM  nyc_wifi_hotspot_locations l "
				+ "WHERE l.City=? AND l.Provider=? ";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, city);
			st.setString(2, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("hotspot"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
}
