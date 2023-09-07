package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {

		final String sql = "SELECT * "
				+ "FROM situazione s "
				+ "WHERE s.Localita=? AND MONTH(Data)=?";
		
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,localita);
            st.setString(2,Integer.toString(mese));
           
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		}
		
		
	}
	
	public Double getAvgRilevamentiLocalitaMese(int mese,String localita) {
	
		final String sql = "SELECT AVG(Umidita) AS avg_umidita "
				+ "FROM situazione s "
				+ "WHERE s.Localita=? AND MONTH(Data)=?";
		
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,localita);
            st.setString(2,Integer.toString(mese));
           
			ResultSet rs = st.executeQuery();

			rs.next();
			double avg = rs.getDouble("avg_umidita");
			
			conn.close();
			return avg;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		}
		
	}
	
	public List<Citta> getAllCitta(){
		
		final String sql = "SELECT DISTINCT Localita "
				+ "FROM situazione s ";
				
		List<Citta> citta = new ArrayList<Citta>();

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				Citta c = new Citta( rs.getString("localita"));
				citta.add(c);
			}
			
			conn.close();
			return citta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}


}
