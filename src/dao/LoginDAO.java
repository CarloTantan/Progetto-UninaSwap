package dao;
import java.sql.*;

import entity.Utente_entity;

// Classe DAO per la gestione dell'autenticazione e recupero dei dati dell'utente
// ha dei metodi per verificare le credenziali al momento del login e per recuperare i dati dell'utente
public class LoginDAO {
		String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
		String user= "postgres";
		String password = "Database";
		
		private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url, user, password);
	    }
		
		// verifica le credenziali di accesso dell'utente
		// ritorna true se le credenziali sono valide, false altrimenti
		 public boolean verificaLogin(String matricola, String password) throws SQLException {
		        String query = "SELECT * FROM utente WHERE matricola = ? AND password = ?";
		        
		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(query)) {
		            
		            pstmt.setString(1, matricola);
		            pstmt.setString(2, password);
		            
		            ResultSet rs = pstmt.executeQuery();
		            
		            return rs.next();
		            
		        } 
		 }
		 
		 // recupera i dati di un utente dalla matricola
		 // restituisce un oggetto Utente_entity con i dati dell'utente, null se non viene trovato
		 public Utente_entity getUtente(String matricola) {
			 String query = "SELECT * FROM utente WHERE matricola = ?";
			 try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(query)) {
		            
		            pstmt.setString(1, matricola);
		            
		            ResultSet rs = pstmt.executeQuery();
		            
		            if (rs.next()) {
		            	Utente_entity utente = new Utente_entity(
		            
		            		rs.getString("nominativo"),
		            		rs.getString("email"),
		            		rs.getString("matricola"),
		            		rs.getString("numerotelefono"),
		            		rs.getString("password"),
		            		rs.getInt("numeroofferteproposte"),
		            		rs.getInt("numerooggettiacquistati"),
		            		rs.getInt("numeroannuncipubblicati"),
		            		rs.getInt("numerooggettivenduti"),
		            		rs.getInt("numerooffertericevute")
		            		);
			 
				    
				    return utente;
			        			
			 } else {
				 return null; 
		        }
			 } catch (SQLException e) {
		            System.err.println("Errore durante il reperimento dell'utente " + e.getMessage());
		            e.printStackTrace();
		            return null;
		        }
		 }
}