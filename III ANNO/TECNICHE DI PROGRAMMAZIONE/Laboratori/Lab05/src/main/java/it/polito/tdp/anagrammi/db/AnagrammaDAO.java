package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class AnagrammaDAO {
	
	public boolean isCorrect(String anagramma) {
		
		final String sql = "SELECT COUNT(*) AS contatore "
				+ "FROM parola p "
				+ "WHERE p.nome=? "
				+ "GROUP BY p.id ";
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, anagramma);
			int count = 0;
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
            	 count = rs.getInt("contatore");
            }
            st.close();
            conn.close();
            if(count==0) {
            	return false;
            }
            return true;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
