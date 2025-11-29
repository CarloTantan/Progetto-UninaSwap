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
import enumerations.TipologiaCategoria;

public class ListaAnnunciDAO { //lista AnnunciDAO
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
		
	public ArrayList<Annuncio_entity> getAnnunci() throws SQLException {
	    ArrayList<Annuncio_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
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
	                false
	                
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
	
	//Vendita
		public ArrayList<AnnuncioVendita_entity> getAnnunciVendita() throws SQLException {
		    ArrayList<AnnuncioVendita_entity> Annunci = new ArrayList<>();
		    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
		                   "FROM Annuncio AS A " +
		                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
		                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
		                   "WHERE A.tipologia='Vendita'";
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    Connection conn = null;
		    
		    try {
		        conn = getConnection();
		        pstmt = conn.prepareStatement(query);
		        rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            AnnuncioVendita_entity annunciV = new AnnuncioVendita_entity(
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
			                rs.getFloat("PrezzoVendita"),
			                false
			                );
		            Annunci.add(annunciV);
		        }
		    } finally {
		        if(rs != null) rs.close();
		        if(pstmt != null) pstmt.close();
		        if(conn != null) conn.close();
		    }
		    
		    return Annunci;
		}

		//regalo
	public ArrayList<AnnuncioRegalo_entity> getAnnunciRegalo() throws SQLException {
	    ArrayList<AnnuncioRegalo_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Regalo'";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            AnnuncioRegalo_entity annunciR = new AnnuncioRegalo_entity(
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
	                rs.getString("MotivoCessione"),
	                false
	            );
	            Annunci.add(annunciR);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Annunci; 
	}

	//scambio
	public ArrayList<AnnuncioScambio_entity> getAnnunciScambio() throws SQLException {
	    ArrayList<AnnuncioScambio_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Scambio'";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            AnnuncioScambio_entity annunciS = new AnnuncioScambio_entity(
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
		                rs.getString("OggettoRichiesto"),
		                false
	            );
	            Annunci.add(annunciS);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Annunci;
	}
	
	//vendita categoria
	public ArrayList<AnnuncioVendita_entity> getAnnunciVenditaCategoria(String categoria) throws SQLException {
	    ArrayList<AnnuncioVendita_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Vendita' AND C.tipologia=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, categoria);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            AnnuncioVendita_entity annunciV = new AnnuncioVendita_entity(
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
		                rs.getFloat("PrezzoVendita"),
		                false
		                );
	            Annunci.add(annunciV);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Annunci;
	}
	
	//scambio categoria
	public ArrayList<AnnuncioScambio_entity> getAnnunciScambioCategoria(String categoria) throws SQLException {
	    ArrayList<AnnuncioScambio_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Scambio' AND C.tipologia=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, categoria);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	AnnuncioScambio_entity annunciS = new AnnuncioScambio_entity(
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
		                rs.getString("OggettoRichiesto"),
		                false
		                );
	            Annunci.add(annunciS);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Annunci;
	}
	
	//regalo categoria
	public ArrayList<AnnuncioRegalo_entity> getAnnunciRegaloCategoria(String categoria) throws SQLException {
	    ArrayList<AnnuncioRegalo_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Regalo' AND C.tipologia=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, categoria);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	AnnuncioRegalo_entity annunciR = new AnnuncioRegalo_entity(
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
		                rs.getString("MotivoCessione"),
		                false
		                );
	            Annunci.add(annunciR);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return Annunci;
	}
	
}	
