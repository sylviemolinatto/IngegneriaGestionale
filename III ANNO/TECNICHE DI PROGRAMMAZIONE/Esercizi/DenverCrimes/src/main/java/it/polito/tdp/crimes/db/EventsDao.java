package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Adiacenza;
import it.polito.tdp.crimes.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<String> getCategorieReato(){
		
		final String sql = "SELECT distinct e.offense_category_id as cat FROM events e";
	
		List<String> categorie = new ArrayList<String>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
            PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				categorie.add(res.getString("cat"));
			}
			
			res.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return categorie;
		
	}
	
	public List<String> getVertici(String categoria, int mese){
		
		final String sql = "SELECT distinct e.offense_type_id "
				+ "FROM EVENTS e "
				+ "WHERE e.offense_category_id=? AND MONTH(e.reported_date)=?";
		
		List<String> vertici = new ArrayList<String>();
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1,categoria);
			st.setInt(2, mese);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				vertici.add(res.getString("offense_type_id"));
			}
			res.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return vertici;
	}
	
	public List<Adiacenza> getArchi(String categoria, int mese){
		
		final String sql = "SELECT e1.offense_type_id as v1,e2.offense_type_id as v2, COUNT(distinct e1.neighborhood_id) as peso "
				+ "FROM EVENTS e1, EVENTS e2 "
				+ "WHERE e1.offense_type_id>e2.offense_type_id AND e1.offense_category_id=? "
				+ "AND e1.offense_category_id=e2.offense_category_id\r\n"
				+ "AND MONTH(e1.reported_date)=? AND MONTH(e1.reported_date)=MONTH(e2.reported_date) "
				+ "AND e1.neighborhood_id = e2.neighborhood_id "
				+ "GROUP BY e1.offense_type_id,e2.offense_type_id";
		
		List<Adiacenza> archi = new ArrayList<Adiacenza>();
		try {
			
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1,categoria);
			st.setInt(2, mese);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				archi.add(new Adiacenza(res.getString("v1"),res.getString("v2"),res.getInt("peso")));
			}
			res.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return archi;
			
	}

}
