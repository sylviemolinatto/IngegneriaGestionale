package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Edge;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Integer> getAllVertex(){
		
		final String sql = "SELECT DISTINCT g.Chromosome as chromosome "
				+ "FROM genes g "
				+ "WHERE g.Chromosome!=0";
		
		List<Integer> vertex = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				vertex.add(res.getInt("chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			return vertex;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Edge> getAllEdges(){
		
		final String sql = "SELECT distinct g1.Chromosome as cr1,g2.Chromosome as cr2,SUM(distinct i.Expression_Corr) as weight "
				+ "FROM genes g1,genes g2, interactions i "
				+ "WHERE g1.Chromosome!=g2.Chromosome AND g1.Chromosome!=0 AND g2.Chromosome!=0 AND g1.GeneID=i.GeneID1 AND g2.GeneID=i.GeneID2 "
				+ "GROUP BY g1.Chromosome,g2.Chromosome "
				+ "ORDER BY g1.Chromosome,g2.Chromosome";
		List<Edge> edges = new ArrayList<Edge>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				edges.add(new Edge(res.getInt("cr1"),res.getInt("cr2"),res.getDouble("weight")));
				
			}
			res.close();
			st.close();
			conn.close();
			return edges;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
		
	}
	


	
}
