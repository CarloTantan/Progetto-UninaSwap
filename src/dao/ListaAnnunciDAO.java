package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.AnnuncioRegalo_entity;
import entity.AnnuncioScambio_entity;
import entity.AnnuncioVendita_entity;
import entity.Annuncio_entity;
import enumerations.FasciaOraria;
import enumerations.StatoAnnuncio;

public class ListaAnnunciDAO { //lista AnnunciDAO
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
		
	public ArrayList<Annuncio_entity> getAnnunci() throws SQLException {
		ArrayList<Annuncio_entity> Annunci = new ArrayList<>();
	    String query= "SELECT * FROM Annuncio";
	    PreparedStatement pstmt= null;
	    ResultSet rs= null;
	    Connection conn = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				Annuncio_entity annunci= new Annuncio_entity(
						rs.getString("Titolo"), 
						rs.getString("Descrizione"),
						FasciaOraria.fromLabel(rs.getString("FasciaOraria")),
						rs.getString("ModalitàConsegna"), 
						StatoAnnuncio.valueOf(rs.getString("StatoAnnuncio")), 
						rs.getString("idOggetto"),
						rs.getDate("DataPubblicazione")
					);
				Annunci.add(annunci);
			} } finally {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	        }

	        return Annunci;
	}
	public ArrayList<AnnuncioRegalo_entity> getAnnunciRegalo() throws SQLException {
		ArrayList<AnnuncioRegalo_entity> Annunci = new ArrayList<>();
	    String query= "SELECT * FROM Annuncio WHERE tipologia='Regalo'";
	    PreparedStatement pstmt= null;
	    ResultSet rs= null;
	    Connection conn = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				AnnuncioRegalo_entity annunciR= new AnnuncioRegalo_entity(
						rs.getString("Titolo"), 
						rs.getString("Descrizione"),
						FasciaOraria.fromLabel(rs.getString("FasciaOraria")),
						rs.getString("ModalitàConsegna"), 
						StatoAnnuncio.valueOf(rs.getString("StatoAnnuncio")), 
						rs.getString("idOggetto"),
						rs.getDate("DataPubblicazione"), 
						rs.getString("MotivoCessione")
					);
				Annunci.add(annunciR);
			} } finally {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	        }

	        return Annunci;
		
		
		
	}
	
	
	public ArrayList<AnnuncioScambio_entity> getAnnunciScambio() throws SQLException {
		ArrayList<AnnuncioScambio_entity> Annunci = new ArrayList<>();
	    String query= "SELECT * FROM Annuncio WHERE tipologia='Scambio'";
	    PreparedStatement pstmt= null;
	    ResultSet rs= null;
	    Connection conn = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				AnnuncioScambio_entity annunciS= new AnnuncioScambio_entity(
						rs.getString("Titolo"), 
						rs.getString("Descrizione"),
						FasciaOraria.fromLabel(rs.getString("FasciaOraria")),
						rs.getString("ModalitàConsegna"), 
						StatoAnnuncio.valueOf(rs.getString("StatoAnnuncio")), 
						rs.getString("idOggetto"),
						rs.getDate("DataPubblicazione"), 
						rs.getString("OggettoRichiesto")
					
					);
				Annunci.add(annunciS);
			} } finally {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	        }

	        return Annunci;
		
		
		
	}
	
	public ArrayList<AnnuncioVendita_entity> getAnnunciVendita() throws SQLException {
		ArrayList<AnnuncioVendita_entity> Annunci = new ArrayList<>();
	    String query= "SELECT * FROM Annuncio WHERE tipologia='Vendita'";
	    PreparedStatement pstmt= null;
	    ResultSet rs= null;
	    Connection conn = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				AnnuncioVendita_entity annunciV= new AnnuncioVendita_entity(
						rs.getString("Titolo"), 
						rs.getString("Descrizione"),
						FasciaOraria.fromLabel(rs.getString("FasciaOraria")),
						rs.getString("ModalitàConsegna"), 
						StatoAnnuncio.valueOf(rs.getString("StatoAnnuncio")), 
						rs.getString("idOggetto"), 
						rs.getDate("DataPubblicazione"), 
						rs.getFloat("PrezzoVendita")
					
					);
				Annunci.add(annunciV);
			} } finally {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	        }

	        return Annunci;
		
		
		
	}
	
	
}



