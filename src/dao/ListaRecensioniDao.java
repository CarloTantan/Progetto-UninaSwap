package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Recensione_entity;

public class ListaRecensioniDao {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
        
        
        
	}
	
	
	public ArrayList<Recensione_entity> VisualizzaRecensioniInviate(String matricola)throws SQLException  {
		  ArrayList<Recensione_entity> Recensioni = new ArrayList<>();
	    String query = "SELECT * " +
	                   "FROM Recesioni  " +
	                   "WHERE MatricolaAcquirente= matricola AND MatricolaVenditore <> matricola   ";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	Recensione_entity recensioni = new Recensione_entity(
	            	rs.getInt("IdRecensione"),	
	                rs.getString("Commento"), 
	                rs.getInt("Punteggio"),
	                rs.getDate("Data"), 
	                rs.getString("MatricolaVenditore"),
	                rs.getString("MatricolaAcquirente")
	                
	            );
	        	Recensioni.add(recensioni);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Recensioni;
	}
	
	
	
	

	public ArrayList<Recensione_entity> VisualizzaRecensioniRicevute(String matricola)throws SQLException  {
		  ArrayList<Recensione_entity> Recensioni = new ArrayList<>();
	    String query = "SELECT * " +
	                   "FROM Recesioni  " +
	                   "WHERE MatricolaAcquirente<> matricola AND MatricolaVenditore = matricola   ";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	Recensione_entity recensioni = new Recensione_entity(
	            	rs.getInt("IdRecensione"),	
	                rs.getString("Commento"), 
	                rs.getInt("Punteggio"),
	                rs.getDate("Data"), 
	                rs.getString("MatricolaVenditore"),
	                rs.getString("MatricolaAcquirente")
	                
	            );
	        	Recensioni.add(recensioni);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Recensioni;
	}

	
	
	

	 }
		
	
	
	
	
	

