package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OffertaDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	public boolean inserimentoOffertaVendita(float ImportoProposto, String MatricolaAcquirente, int IdAnnuncio) throws SQLException{
		String query = "INSERT INTO Offerta (ImportoProposto, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setFloat(1, ImportoProposto);
			pstmt.setString(2, MatricolaAcquirente);
			pstmt.setInt(3, IdAnnuncio);
			
			pstmt.executeUpdate();
			
			return true;
            
            
        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'offerta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean inserimentoOffertaScambio(String OggettoRichiesto, String MatricolaAcquirente, int IdAnnuncio) throws SQLException {
		String query = "INSERT INTO Offerta (OggettoRichiesto, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, OggettoRichiesto);
			pstmt.setString(2, MatricolaAcquirente);
			pstmt.setInt(3, IdAnnuncio);
			
			pstmt.executeUpdate();
			
			return true;
            
            
        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'offerta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean inserimentoOffertaRegalo(String MessaggioMotivazionale, String MatricolaAcquirente, int IdAnnuncio) throws SQLException {
		String query = "INSERT INTO Offerta (OggettoRichiesto, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, MessaggioMotivazionale);
			pstmt.setString(2, MatricolaAcquirente);
			pstmt.setInt(3, IdAnnuncio);
			
			pstmt.executeUpdate();
			
			return true;
            
            
        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'offerta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
	}
}
