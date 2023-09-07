package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.genes.model.Edge;
import it.polito.tdp.genes.model.Genes;


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
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genes> getAllNecessaryGenes(){
		
		final String sql = "SELECT DISTINCT GeneID, Essential, Chromosome "
				+ "FROM genes g "
				+ "WHERE g.Essential='Essential'";
		
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
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<Edge> getEdges(){
		
		final String sql = "SELECT distinct GeneID1, GeneID2, g1.Chromosome as cr1, g2.Chromosome as cr2, g1.Essential as e1, g2.Essential as e2, i.Expression_Corr as expr "
				+ "FROM interactions i, genes g1, genes g2 "
				+ "WHERE i.GeneID1=g1.GeneID AND i.GeneID2=g2.GeneID AND g1.Essential='Essential' AND g2.Essential='Essential' ";
		
		List<Edge> result = new ArrayList<Edge>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				double weight;
				Genes g1 = new Genes(res.getString("GeneID1"),res.getString("e1"),res.getInt("cr1"));
				Genes g2 = new Genes(res.getString("GeneID2"),res.getString("e2"),res.getInt("cr2"));
				
				if(g1.getChromosome()==g2.getChromosome()) {
					weight = 2 * Math.abs(res.getDouble("expr"));
				}
				else {
					weight = Math.abs(res.getDouble("expr"));
				}
				
				Edge e = new Edge(g1,g2,weight);
				result.add(e);
				
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		

	}
	


	
}
