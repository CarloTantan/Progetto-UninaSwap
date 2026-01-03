package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.OffertaRegalo_entity;
import entity.OffertaScambio_entity;
import entity.OffertaVendita_entity;

import enumerations.*;

// Classe DAO per la gestione delle offerte
// permette di inserire, aggiornare e caricare offerte di varie tipologie
public class OffertaDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	// INserisce un'offerta di vendita nel database
	// ritorna true se l'inserimento va a buon fine
	public boolean inserimentoOffertaVendita(float ImportoProposto, String MatricolaAcquirente, int IdAnnuncio) throws SQLException{
		String query = "INSERT INTO Offerta (ImportoProposto, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setFloat(1, ImportoProposto);
			pstmt.setString(2, MatricolaAcquirente);
			pstmt.setInt(3, IdAnnuncio);
			
			pstmt.executeUpdate();
			
			return true;
		}
	}
		
	// Inserisce un'offerta di scambio nel database
	// ritorna true se l'inserimento va a buon fine
	public boolean inserimentoOffertaScambio(String OggettoProposto, String MatricolaAcquirente, int IdAnnuncio) throws SQLException {
		String query = "INSERT INTO Offerta (OggettoProposto, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, OggettoProposto);
			pstmt.setString(2, MatricolaAcquirente);
			pstmt.setInt(3, IdAnnuncio);
			
			pstmt.executeUpdate();
			
			return true;
            
            
        } 
	}
	
	// Inserisce un'offerta di regalo nel database
	// ritorna true se l'inserimento va a buon fine
	public boolean inserimentoOffertaRegalo(String MessaggioMotivazionale, String MatricolaAcquirente, int IdAnnuncio) throws SQLException {
		String query = "INSERT INTO Offerta (MessaggioMotivazionale, MatricolaAcquirente, IdAnnuncio) VALUES (?, ?, ?)";
		
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, MessaggioMotivazionale);
			pstmt.setString(2, MatricolaAcquirente);
			pstmt.setInt(3, IdAnnuncio);
			
			pstmt.executeUpdate();
			
			return true;
		}
	}
	
	// Aggiorna un'offerta di regalo esistente
	// ritorna true se l'aggiornamento va a buon fine
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
	        
	    }
	}
	
	// Aggiorna un'offerta di scambio esistente
	// ritorna true se l'aggiornamento va a buon fine
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
	    }
	}
	
	// Aggiorna un'offerta di vendita esistente
	// ritorna true se l'aggiornamento va a buon fine
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

	    } 
	}

	// Carica i dati di un'offerta di regalo
	// ritorna un oggetto OffertaRegalo_entity se l'offerta esiste, altrimenti null
	public OffertaRegalo_entity caricaOfferta(int IdOfferta) throws SQLException {
	    String query = "SELECT * FROM Offerta WHERE IdOfferta = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        
	        pstmt.setInt(1, IdOfferta);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new OffertaRegalo_entity(
	                    rs.getInt("IdOfferta"),
	                    StatoOfferta.fromLabel(rs.getString("Stato")),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("MessaggioMotivazionale"),
	                    rs.getString("Tipologia")
	                );
	            }
	        }
	    }
	    return null;
	}
	
	// Carica i dati di un'offerta di vendita
	// ritorna un oggetto OffertaVendita_entity se l'offerta esiste, altrimenti null
	public OffertaVendita_entity caricaOffertaVendita(int IdOfferta) throws SQLException {
	    String query = "SELECT * FROM Offerta WHERE IdOfferta = ? AND Tipologia = 'Vendita'";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, IdOfferta);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new OffertaVendita_entity(
	                    rs.getInt("IdOfferta"),
	                    StatoOfferta.fromLabel(rs.getString("Stato")),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getFloat("ImportoProposto"),
	                    rs.getString("Tipologia")
	                );
	            }
	        }
	    }
	    return null;
	}

	// Carica i dati di un'offerta di scambio
	// ritorna un oggetto OffertaScambio_entity se l'offerta esiste, altrimenti null
	public OffertaScambio_entity caricaOffertaScambio(int IdOfferta) throws SQLException {
	    String query = "SELECT * FROM Offerta WHERE IdOfferta = ? AND Tipologia = 'Scambio'";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {

	        pstmt.setInt(1, IdOfferta);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new OffertaScambio_entity(
	                    rs.getInt("IdOfferta"),
	                    StatoOfferta.fromLabel(rs.getString("Stato")),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("OggettoProposto"),
	                    rs.getString("Tipologia")
	                );
	            }
	        }
	    }
	    return null;
	}
}
