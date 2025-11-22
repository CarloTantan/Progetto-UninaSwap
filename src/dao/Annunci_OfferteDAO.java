package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Annuncio_entity;
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
	    String query = "SELECT * " +
                "FROM Annuncio  " +
                "WHERE MatricolaVenditore = ? ";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        pstmt.setString(1, matricola);
	        while (rs.next()) {
	            Annuncio_entity annunci = new Annuncio_entity(
	            	rs.getInt("IdAnnuncio"),	
	                rs.getString("Titolo"), 
	                rs.getString("Descrizione"),
	                FasciaOraria.fromLabel(rs.getString("FasciaOraria")),
	                rs.getString("ModalitàConsegna"), 
	                StatoAnnuncio.valueOf(rs.getString("StatoAnnuncio")), 
	                rs.getString("idOggetto"),
	                TipologiaCategoria.valueOf(rs.getString("TipologiaCategoria")), // SOLO "Tipologia"!
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
                "WHERE IdAnnuncio = ? AND StatoOfferta= 'In Attesa' ";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        pstmt.setInt(1, IdAnnuncio);
	        while (rs.next()) {
	        	Offerta_entity offerte = new Offerta_entity(
	            	rs.getInt("IdOfferta"),	
	                rs.getString("StatoOfferta"), 
	                rs.getString("MatricolaAcquirente"),
	                rs.getInt("IdAnnuncio"),	
	                rs.getString("TipologiaOfferta")
	                
	        			);
	        	Offerte.add(offerte);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Offerte;
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
