package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Recensione_entity;

// classe DAO per la visualizzazione delle recensioni 
// fornisce metodi per recuperare le recensioni inviate e ricevute da un singolo utente
public class ListaRecensioniDao {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
	
	// recupera e restituisce la lista delle recensioni inviate da un utente
	public ArrayList<Recensione_entity> VisualizzaRecensioniInviate(String matricola)throws SQLException  {
		  ArrayList<Recensione_entity> Recensioni = new ArrayList<>();
	    String query = "SELECT * " +
	                   "FROM Recensione  " +
	                   "WHERE MatricolaAcquirente = ? AND MatricolaVenditore <> ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);
	        pstmt.setString(2, matricola);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	Recensione_entity recensioni = new Recensione_entity(
	            	rs.getInt("IdRecensione"),	
	                rs.getString("Commento"), 
	                rs.getInt("Punteggio"),
	                rs.getDate("Data"), 
	                rs.getString("MatricolaAcquirente"),
	                rs.getString("MatricolaVenditore"),
	                rs.getInt("IdOfferta")
	                
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

	// recupera e restituisce la lista delle recensioni ricevute da un utente
	public ArrayList<Recensione_entity> VisualizzaRecensioniRicevute(String matricola)throws SQLException  {
		  ArrayList<Recensione_entity> Recensioni = new ArrayList<>();
	    String query = "SELECT * " +
	                   "FROM Recensione  " +
	                   "WHERE MatricolaAcquirente <> ? AND MatricolaVenditore = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);
	        pstmt.setString(2, matricola);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	Recensione_entity recensioni = new Recensione_entity(
	            	rs.getInt("IdRecensione"),	
	                rs.getString("Commento"), 
	                rs.getInt("Punteggio"),
	                rs.getDate("Data"), 
	                rs.getString("MatricolaAcquirente"),
	                rs.getString("MatricolaVenditore"),
	                rs.getInt("IdOfferta")
	                
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
