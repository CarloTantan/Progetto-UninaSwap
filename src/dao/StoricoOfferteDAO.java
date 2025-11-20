package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Offerta_entity;

public class StoricoOfferteDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	public ArrayList<Offerta_entity> getOfferte(String matricola) throws SQLException {
		ArrayList<Offerta_entity> ListaOfferte = new ArrayList<>();
		String query = "SELECT  * FROM Offerte WHERE MatricolaAcquirente = ?";
	    
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, matricola);
	        
	        try (ResultSet rs = pstmt.executeQuery()){
	        	while (rs.next()) {
	        		Offerta_entity Offerte = new Offerta_entity(
	        				rs.getInt("IdOfferta"),
	        				rs.getString("StatoOfferta"),
		            	rs.getString("MatricolaAcquirente"),
		            	rs.getInt("IdAnnuncio"),
		            	rs.getString("TipologiaOfferta")    
	            );
	        		ListaOfferte.add(Offerte);
	        	}
	        }
		}
	    
	    return ListaOfferte; 
	}
	
	
	public ArrayList<Offerta_entity> UpdateOfferte(String matricola) throws SQLException {
		ArrayList<Offerta_entity> ListaOfferte = new ArrayList<>();
		String query = "SELECT  * FROM Offerte WHERE MatricolaAcquirente = ?";
	    
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, matricola);
	        
	        try (ResultSet rs = pstmt.executeQuery()){
	        	while (rs.next()) {
	        		Offerta_entity Offerte = new Offerta_entity(
	        				rs.getInt("IdOfferta"),
	        				rs.getString("StatoOfferta"),
		            	rs.getString("MatricolaAcquirente"),
		            	rs.getInt("IdAnnuncio"),
		            	rs.getString("TipologiaOfferta")    
	            );
	        		ListaOfferte.add(Offerte);
	        	}
	        }
		}
	    
	    return ListaOfferte; 
	}
	
	
	
	
	
	

}
