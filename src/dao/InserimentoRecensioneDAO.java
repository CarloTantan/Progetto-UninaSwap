package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InserimentoRecensioneDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaSwapDefinitivo", "postgres", "Database");
    }

    public void inserisciRecensione(String matricolaVenditore, String matricolaAcquirente, int punteggio, String commento, int idOfferta) throws SQLException {
        String query = "INSERT INTO Recensione (matricolaVenditore, matricolaAcquirente, "
        		+ "punteggio, commento, data, idOfferta) VALUES (?, ?, ?, ?, CURRENT_DATE, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, matricolaVenditore);
            pstmt.setString(2, matricolaAcquirente);
            pstmt.setInt(3, punteggio);
            pstmt.setString(4, commento);
            pstmt.setInt(5, idOfferta);

            pstmt.executeUpdate();
        }
    }
    
    public boolean esisteRecensione(int idOfferta) throws SQLException {
        String query = "SELECT COUNT(*) FROM Recensione WHERE idOfferta = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idOfferta);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    
}


