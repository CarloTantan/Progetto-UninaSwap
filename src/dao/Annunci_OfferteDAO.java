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
import enumerations.FasciaOraria;
import enumerations.StatoAnnuncio;
import enumerations.TipologiaCategoria;

public class Annunci_OfferteDAO {

	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
	
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
	            
	            if (tipologia.equalsIgnoreCase("Vendita")) {
	                offerta = new OffertaVendita_entity(
	                    rs.getInt("IdOfferta"),
	                    rs.getString("Stato"),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getFloat("ImportoProposto"),
	                    tipologia
	                );
	            } else if (tipologia.equalsIgnoreCase("Scambio")) {
	                offerta = new OffertaScambio_entity(
	                    rs.getInt("IdOfferta"),
	                    rs.getString("Stato"),
	                    rs.getString("MatricolaAcquirente"),
	                    rs.getInt("IdAnnuncio"),
	                    rs.getString("OggettoProposto"),
	                    tipologia
	                );
	            } else { // Regalo
	                offerta = new OffertaRegalo_entity(
	                    rs.getInt("IdOfferta"),
	                    rs.getString("Stato"),
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
	
	public boolean accettaOfferta(int idOfferta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = getConnection();
            
            // Basta aggiornare lo stato dell'offerta, i trigger fanno il resto!
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
