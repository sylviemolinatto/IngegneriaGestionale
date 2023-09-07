package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Edge;
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
	
	public List<String> getOffenseCategories(){
		
		String sql = "SELECT DISTINCT e.offense_category_id "
				+ "FROM events e "
				+ "ORDER BY e.offense_category_id";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<String> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getString("offense_category_id"));
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
	
	public List<LocalDate> getDays(){
		
		String sql = "SELECT DISTINCT DATE(e.reported_date) as date "
				+ "FROM events e "
				+ "ORDER BY DATE(e.reported_date)";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<LocalDate> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getDate("date").toLocalDate());
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
	
	public List<String> getVertici(String categoria, LocalDate data){
		
		String sql = "SELECT DISTINCT e.offense_type_id "
				+ "FROM events e "
				+ "WHERE e.offense_category_id=? AND YEAR(e.reported_date)=? AND MONTH(e.reported_date)=? AND DAY(e.reported_date)=?";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, categoria);
			st.setInt(2,data.getYear());
			st.setInt(3, data.getMonthValue());
			st.setInt(4, data.getDayOfYear());
			
			List<String> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getString("offense_type_id"));
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
	
	public List<Edge> getArchi( String categoria, LocalDate data){
		
		String sql ="SELECT e1.offense_type_id as o1, e2.offense_type_id as o2, COUNT(DISTINCT e1.precinct_id) as weight "
				+ "FROM events e1, events e2 "
				+ "WHERE e1.offense_type_id>e2.offense_type_id AND e1.precinct_id=e2.precinct_id "
				+ "AND e1.offense_category_id=? AND e2.offense_category_id=e1.offense_category_id AND YEAR(e1.reported_date)=? AND MONTH(e1.reported_date)=? AND DAY(e1.reported_date)=? "
				+ "AND YEAR(e2.reported_date)=YEAR(e1.reported_date) AND MONTH(e2.reported_date)=MONTH(e1.reported_date) AND DAY(e2.reported_date)=DAY(e1.reported_date) "
				+ "GROUP BY e1.offense_type_id,e2.offense_type_id";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, categoria);
			st.setInt(2,data.getYear());
			st.setInt(3, data.getMonthValue());
			st.setInt(4, data.getDayOfYear());
			
			List<Edge> result = new ArrayList<Edge>();
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(new Edge(res.getString("o1"), res.getString("o2"),res.getInt("weight")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
		    
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
