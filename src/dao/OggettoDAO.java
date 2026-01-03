package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe DAO per la gestione degli oggetti
public class OggettoDAO {
	
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	// inserisce un nuovo oggetto nel database e ritorna l'id generato
	public int inserisciOggetto(String Nome, String Descrizione, int IdCategoria) throws SQLException {
		String query = "INSERT INTO Oggetto (Nome, Descrizione, IdCategoria) VALUES (?, ?, ?) RETURNING IdOggetto";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, Nome);
			pstmt.setString(2, Descrizione);
			pstmt.setInt(3, IdCategoria);
			
			ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("idOggetto");
	        }
	        throw new SQLException("Inserimento oggetto fallito, nessun ID ottenuto.");
	    }
	}
}