package dao;
import java.sql.*;

public class LoginDAO {
		String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
		String user= "postgres";
		String password = "Database";
		
		private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url, user, password);
	    }
		
		 public boolean verificaLogin(String matricola, String password) {
		        String query = "SELECT * FROM acquirente WHERE matricolaacquirente = ? AND password = ?";
		        
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
}