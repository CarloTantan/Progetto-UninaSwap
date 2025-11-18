package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrazioneDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	 public boolean effettuaRegistrazione(String nome, String cognome, String matricola, String telefono, String email, String password) {
		 	String nominativo = nome + " " + cognome;
	        String query = "INSERT INTO utente (nominativo, matricola, numerotelefono, email, password) VALUES (?, ?, ?, ?, ?)";
	        
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            
	            // Imposta i parametri della query
	            pstmt.setString(1, nominativo);
	            pstmt.setString(2, matricola);
	            pstmt.setString(3, telefono);
	            pstmt.setString(4, email);
	            pstmt.setString(5,  password);
	            
	            // Esegui la query
	            pstmt.executeUpdate();
	            
	            return true;
	            
	            
	        } catch (SQLException e) {
	            System.err.println("Errore durante la registrazione: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	 }

}
