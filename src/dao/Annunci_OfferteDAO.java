package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Annuncio_entity;
import entity.OffertaVendita_entity;
import entity.OffertaRegalo_entity;
import entity.OffertaScambio_entity;
import entity.Offerta_entity;
import enumerations.*;

// Classe DAO per la gestione degli annunci e delle relative offerte
// Permette di recuperare annunci di un venditore, visualizzare offerte ricevute
// e gestire l'accettazione/rifiuto delle offerte

public class Annunci_OfferteDAO {

	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
	
	// Recupera tutti gli annunci pubblicati da un venditore specifico
	// ritorna la lista di annunci con informazioni complete e
	// include anche un flag per indicare se ci sono offerte in attesa	
	
	public ArrayList<Annuncio_entity> getAnnunci(String matricola) throws SQLException {
	    ArrayList<Annuncio_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria, " +
	    		"EXISTS (SELECT 1 FROM Offerta O WHERE O.IdAnnuncio = A.IdAnnuncio " +
	    		"AND O.Stato = 'In Attesa') AS VisualizzaOfferta " +
                "FROM Annuncio AS A "+
	    		"JOIN Oggetto AS O ON A.idOggetto = O.idOggetto " +
                "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
                "WHERE MatricolaVenditore = ? ";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Annuncio_entity annunci = new Annuncio_entity(
	            	rs.getInt("IdAnnuncio"),	
	                rs.getString("Titolo"), 
	                rs.getString("Descrizione"),
	                FasciaOraria.fromLabel(rs.getString("FasciaOraria")),
	                rs.getString("ModalitàConsegna"), 
	                StatoAnnuncio.valueOf(rs.getString("StatoAnnuncio")), 
	                rs.getString("idOggetto"),
	                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")), // SOLO "Tipologia"!
	                rs.getDate("DataPubblicazione"),
	                rs.getString("MatricolaVenditore"),
	                rs.getBoolean("VisualizzaOfferta")
	            );
	            Annunci.add(annunci);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Annunci;
	}
	
	// Recupera tutte le offerte in attesa per un annuncio specifico
	// ritorna la lista di offerte (Vendita, Scambio o Regalo) in base alla tipologia	
	
	public ArrayList<Offerta_entity> getOfferte(int IdAnnuncio) throws SQLException {
	    ArrayList<Offerta_entity> Offerte = new ArrayList<>();
	    String query = "SELECT * " +
	                "FROM Offerta  " +
	                "WHERE IdAnnuncio = ? AND Stato = 'In Attesa' ";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, IdAnnuncio);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            String tipologia = rs.getString("Tipologia");
	            Offerta_entity offerta;
	            
	            // crea l'oggetto offerta in base alla tipologia
	            if (tipologia.equalsIgnoreCase("Vendita")) {
	                offerta = new OffertaVendita_entity(
	                    rs.getInt("IdOfferta"),
	                    StatoOfferta.fromLabel(rs.getString("Stato")),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getFloat("ImportoProposto"),
	                    tipologia
	                );
	            } else if (tipologia.equalsIgnoreCase("Scambio")) {
	                offerta = new OffertaScambio_entity(
	                    rs.getInt("IdOfferta"),
	                    StatoOfferta.fromLabel(rs.getString("Stato")),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("OggettoProposto"),
	                    tipologia
	                );
	            } else {
	                offerta = new OffertaRegalo_entity(
	                    rs.getInt("IdOfferta"),
	                    StatoOfferta.fromLabel(rs.getString("Stato")),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("MessaggioMotivazionale"),
	                    tipologia
	                );
	            }
	            
	            Offerte.add(offerta);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Offerte;
	}
	
	// Accetta un'offerta modificando il suo stato da "In Attesa" ad "Accettata"
	// I trigger del database si occupano automaticamente di:
	// Impostare tutte le altre offerte dello stesso annuncio come "Rifiutata"
	// Aggiornare lo stato dell'annuncio a "Chiuso"
	// Ritorna true se l'operazione ha avuto successo, false altrimenti	
	
	public boolean accettaOfferta(int idOfferta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection();
            
            // aggiorna lo stato dell'offerta in "Accettata"
            String query = "UPDATE Offerta SET Stato = 'Accettata' WHERE IdOfferta = ? AND Stato = 'In Attesa'";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idOfferta);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
	
	// Rifiuta un'offerta modificando il suo stato da "In Attesa" a "Rifiutata"
	// Ritorna true se l'operazione ha avuto successo, false altrimenti	
	
	public boolean rifiutaOfferta(int idOfferta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection();
            
            // Aggiorna lo stato dell'offerta a "Rifiutata"
            String query = "UPDATE Offerta SET Stato = 'Rifiutata' WHERE IdOfferta = ? AND Stato = 'In Attesa'";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idOfferta);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
	
	
}
