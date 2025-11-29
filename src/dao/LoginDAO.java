package dao;
import java.sql.*;

import entity.Utente_entity;

public class LoginDAO {
		String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
		String user= "postgres";
		String password = "Database";
		
		private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url, user, password);
	    }
		
		 public boolean verificaLogin(String matricola, String password) throws SQLException {
		        String query = "SELECT * FROM utente WHERE matricola = ? AND password = ?";
		        
		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(query)) {
		            
		            // Imposta i parametri della query
		            pstmt.setString(1, matricola);
		            pstmt.setString(2, password);
		            
		            // Esegui la query
		            ResultSet rs = pstmt.executeQuery();
		            
		            // Se trova almeno un risultato, il login è valido
		            return rs.next();
		            
		        } catch (SQLException e) {
		            System.err.println("Errore durante la verifica del login: " + e.getMessage());
		            e.printStackTrace();
		            return false;
		        }
		 }
		 
		 public Utente_entity getUtente(String matricola) {
			 String query = "SELECT * FROM utente WHERE matricola = ?";
			 try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(query)) {
		            
		            // Imposta i parametri della query
		            pstmt.setString(1, matricola);
		            
		            // Esegui la query
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
				 return null; // Utente non trovato (anche se verificaLogin dovrebbe averlo garantito)
		        }
			 } catch (SQLException e) {
		            System.err.println("Errore durante il reperimento dell'utente " + e.getMessage());
		            e.printStackTrace();
		            return null;
		        }
		 }
}