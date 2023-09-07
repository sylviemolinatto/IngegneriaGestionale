package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.Adiacenza;
import it.polito.tdp.artsmia.model.ArtObject;

public class ArtsmiaDAO {

	public void listObjects(Map<Integer,ArtObject> idMap) {
		
		String sql = "SELECT * from objects";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				if(!idMap.containsKey(res.getInt("object_id"))) {
					
				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				idMap.put(artObj.getId(), artObj);
				}
			}
				
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPeso(ArtObject a1, ArtObject a2) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) as peso "
				+ "FROM exhibition_objects o1, exhibition_objects o2 "
				+ "WHERE o1.exhibition_id=o2.exhibition_id AND o1.object_id=? AND o2.object_id=?";
		
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setInt(2, a2.getId());
			ResultSet res = st.executeQuery();
			
			int peso=0;
			if(res.next()) {
				peso = res.getInt("peso");
			}
				
			conn.close();
			return peso;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Adiacenza> getAdiacenza(Map<Integer,ArtObject> idMap){
		
		String sql = "SELECT o1.object_id AS o1,o2.object_id AS o2,COUNT(*) AS peso "
				+ "FROM exhibition_objects o1, exhibition_objects o2 "
				+ "WHERE o1.exhibition_id=o2.exhibition_id AND o1.object_id>o2.object_id "
				+ "GROUP BY o1.object_id, o2.object_id";
		
		Connection conn = DBConnect.getConnection();
		List<Adiacenza> result = new ArrayList<Adiacenza>();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			int peso=0;
			while(res.next()) {
				Adiacenza a = new Adiacenza(idMap.get(res.getInt("o1")),idMap.get(res.getInt("o2")),res.getInt("peso"));
				result.add(a);
			}
				
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
