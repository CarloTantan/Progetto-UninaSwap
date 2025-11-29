package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.OffertaRegalo_entity;
import entity.OffertaScambio_entity;
import entity.OffertaVendita_entity;

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
	
	public boolean inserimentoOffertaScambio(String OggettoProposto, String MatricolaAcquirente, int IdAnnuncio) throws SQLException {
		String query = "INSERT INTO Offerta (OggettoProposto, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, OggettoProposto);
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
		String query = "INSERT INTO Offerta (MessaggioMotivazionale, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
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
	
	
	
	public boolean aggiornaOffertaRegalo(String MessaggioMotivazionale, String MatricolaAcquirente, int IdAnnuncio, int IdOfferta) throws SQLException {
	    String query = "UPDATE Offerta SET MessaggioMotivazionale = ?, MatricolaAcquirente = ?, IdAnnuncio = ? WHERE IdOfferta = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        
	        pstmt.setString(1, MessaggioMotivazionale);
	        pstmt.setString(2, MatricolaAcquirente);
	        pstmt.setInt(3, IdAnnuncio);
	        pstmt.setInt(4, IdOfferta);
	        
	        pstmt.executeUpdate();
	        return true;
	        
	    } catch (SQLException e) {
	        System.err.println("Errore durante l'aggiornamento dell'offerta: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	
	
	
	
	public OffertaRegalo_entity caricaOfferta(int IdOfferta) throws SQLException {
	    String query = "SELECT * FROM Offerta WHERE IdOfferta = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        
	        pstmt.setInt(1, IdOfferta);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new OffertaRegalo_entity(
	                    rs.getInt("IdOfferta"),
	                    rs.getString("StatoOfferta"),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("MessaggioMotivazionale"),
	                    rs.getString("TipologiaOfferta")
	                );
	            }
	        }
	    }
	    return null;
	}
	
	
	public OffertaVendita_entity caricaOffertaVendita(int IdOfferta) throws SQLException {
	    String query = "SELECT * FROM Offerta WHERE IdOfferta = ? AND TipologiaOfferta = 'Vendita'";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, IdOfferta);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new OffertaVendita_entity(
	                    rs.getInt("IdOfferta"),
	                    rs.getString("StatoOfferta"),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getFloat("ImportoProposto"),
	                    rs.getString("TipologiaOfferta")
	                );
	            }
	        }
	    }
	    return null;
	}

	public OffertaScambio_entity caricaOffertaScambio(int IdOfferta) throws SQLException {
	    String query = "SELECT * FROM Offerta WHERE IdOfferta = ? AND TipologiaOfferta = 'Scambio'";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, IdOfferta);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new OffertaScambio_entity(
	                    rs.getInt("IdOfferta"),
	                    rs.getString("StatoOfferta"),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("OggettoProposto"),
	                    rs.getString("TipologiaOfferta")
	                );
	            }
	        }
	    }
	    return null;
	}
	public boolean aggiornaOffertaScambio(String OggettoProposto, String MatricolaAcquirente, int IdAnnuncio, int IdOfferta) throws SQLException {
	    String query = "UPDATE Offerta SET OggettoProposto = ?, MatricolaAcquirente = ?, IdAnnuncio = ? WHERE IdOfferta = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setString(1, OggettoProposto);
	        pstmt.setString(2, MatricolaAcquirente);
	        pstmt.setInt(3, IdAnnuncio);
	        pstmt.setInt(4, IdOfferta);

	        pstmt.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        System.err.println("Errore durante l'aggiornamento dell'offerta: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean aggiornaOffertaVendita(float ImportoProposto, String MatricolaAcquirente, int IdAnnuncio, int IdOfferta) throws SQLException {
	    String query = "UPDATE Offerta SET ImportoProposto = ?, MatricolaAcquirente = ?, IdAnnuncio = ? WHERE IdOfferta = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setFloat(1, ImportoProposto);
	        pstmt.setString(2, MatricolaAcquirente);
	        pstmt.setInt(3, IdAnnuncio);
	        pstmt.setInt(4, IdOfferta);

	        pstmt.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        System.err.println("Errore durante l'aggiornamento dell'offerta: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
}
