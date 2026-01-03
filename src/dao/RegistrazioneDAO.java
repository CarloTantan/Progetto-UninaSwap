package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe DAO per la gestione della registrazione degli utenti
// fornisce metodi per inserire nuovi utenti nel sistema
public class RegistrazioneDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	// Registra un nuovo utente nel database
	// ritorna true se la registrazione va a buon fine
	public boolean effettuaRegistrazione(String nome, String cognome, String matricola, String telefono, String email, String password) throws SQLException {
		 	String nominativo = nome + " " + cognome; // nominativo creato concatenando nome e cognome
	        String query = "INSERT INTO utente (nominativo, matricola, numerotelefono, email, password) VALUES (?, ?, ?, ?, ?)";
	        
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            
	            pstmt.setString(1, nominativo);
	            pstmt.setString(2, matricola);
	            pstmt.setString(3, telefono);
	            pstmt.setString(4, email);
	            pstmt.setString(5,  password);
	            
	            pstmt.executeUpdate();
	            
	            return true;

	        }
	 }

}
