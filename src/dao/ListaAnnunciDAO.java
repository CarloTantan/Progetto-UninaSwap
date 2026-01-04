package dao;

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

// CLasse DAO per la gestione e il recupero degli annunci pubblicati
// ha dei metodi per visualizzare, filtrare e cercare annunci
public class ListaAnnunciDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
	
	// Recupera tutti gli annunci presenti nel sistema e ritorna una lista
	
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
	

	// Recupera tutti gli annunci di vendita e ritorna una lista
	
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

	// Recupera tutti gli annunci di regalo e ritorna una lista
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

	// Recupera tutti gli annunci di scambio e ritorna una lista
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
	
	// Filtra per categoria gli annunci di vendita
	// ritorna la lista di annunci di vendita con la categoria specificata
	public ArrayList<AnnuncioVendita_entity> getAnnunciVenditaCategoria(Integer idCategoria) throws SQLException {
	    ArrayList<AnnuncioVendita_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Vendita' AND C.idCategoria=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, idCategoria);
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
		                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")), 
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
	
	// Filtra per categoria gli annunci di scambio
	// ritorna la lista di annunci di scambio con la categoria specificata
	public ArrayList<AnnuncioScambio_entity> getAnnunciScambioCategoria(Integer idCategoria) throws SQLException {
	    ArrayList<AnnuncioScambio_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Scambio' AND C.idCategoria=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, idCategoria);
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
		                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")), 
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
	
	// Filtra per categoria gli annunci di regalo
	// ritorna la lista di annunci di regalo con la categoria specificata
	public ArrayList<AnnuncioRegalo_entity> getAnnunciRegaloCategoria(Integer idCategoria) throws SQLException {
	    ArrayList<AnnuncioRegalo_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE A.tipologia='Regalo' AND C.idCategoria=?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, idCategoria);
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
		                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")), 
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
	
	// Cerca annunci in base al testo inserito (nel titolo e nella descrizione)
	// ritorna la lista di annunci che contengono il testo specificato
	public ArrayList<Annuncio_entity> cercaAnnunci(String testoRicerca) throws SQLException {
	    ArrayList<Annuncio_entity> Annunci = new ArrayList<>();
	    String query = "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	                   "FROM Annuncio AS A " +
	                   "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	                   "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	                   "WHERE LOWER(A.Titolo) LIKE LOWER(?) OR LOWER(A.Descrizione) LIKE LOWER(?)";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        String searchPattern = "%" + testoRicerca + "%";
	        pstmt.setString(1, searchPattern);
	        pstmt.setString(2, searchPattern);
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
	                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")),
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

	// cerca annunci di vendita per testo e per categoria
	// ritorna la lista di annunci di vendita che soddisfano i criteri di ricerca
	public ArrayList<AnnuncioVendita_entity> cercaAnnunciVendita(String testoRicerca, Integer idCategoria) throws SQLException {
	    ArrayList<AnnuncioVendita_entity> Annunci = new ArrayList<>();
	    StringBuilder query = new StringBuilder(
	        "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	        "FROM Annuncio AS A " +
	        "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	        "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	        "WHERE A.tipologia='Vendita' AND (LOWER(A.Titolo) LIKE LOWER(?) OR LOWER(A.Descrizione) LIKE LOWER(?))");
	    
	    // aggiunge il filtro per categoria se specificata
	    if (idCategoria != null) {  
	        query.append(" AND C.idCategoria=?");  
	    }
	    
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query.toString());
	        String searchPattern = "%" + testoRicerca + "%";
	        pstmt.setString(1, searchPattern);
	        pstmt.setString(2, searchPattern);
	        
	        if (idCategoria != null) {
	            pstmt.setInt(3, idCategoria); 
	        }
	        
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
	                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")),
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

	// cerca annunci di regalo per testo e per categoria
	// ritorna la lista di annunci di regalo che soddisfano i criteri di ricerca
	public ArrayList<AnnuncioRegalo_entity> cercaAnnunciRegalo(String testoRicerca, Integer idCategoria) throws SQLException {
	    ArrayList<AnnuncioRegalo_entity> Annunci = new ArrayList<>();
	    StringBuilder query = new StringBuilder(
	        "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	        "FROM Annuncio AS A " +
	        "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	        "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	        "WHERE A.tipologia='Regalo' AND (LOWER(A.Titolo) LIKE LOWER(?) OR LOWER(A.Descrizione) LIKE LOWER(?))");
	    
	    // aggiunge il filtro per categoria se specificata
	    if (idCategoria != null) {  
	        query.append(" AND C.idCategoria=?");  
	    }
	    
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query.toString());
	        String searchPattern = "%" + testoRicerca + "%";
	        pstmt.setString(1, searchPattern);
	        pstmt.setString(2, searchPattern);
	        
	        if (idCategoria != null) {
	            pstmt.setInt(3, idCategoria);  
	        }
	        
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
	                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")),
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

	// cerca annunci di scambio per testo e per categoria
	// ritorna la lista di annunci di regalo che soddisfano i criteri di ricerca
	public ArrayList<AnnuncioScambio_entity> cercaAnnunciScambio(String testoRicerca, Integer idCategoria) throws SQLException {
	    ArrayList<AnnuncioScambio_entity> Annunci = new ArrayList<>();
	    StringBuilder query = new StringBuilder(
	        "SELECT A.*, C.Tipologia AS TipologiaCategoria " +
	        "FROM Annuncio AS A " +
	        "JOIN oggetto AS O ON A.Idoggetto = O.idoggetto " +
	        "JOIN Categoria AS C ON C.idCategoria = O.idCategoria " +
	        "WHERE A.tipologia='Scambio' AND (LOWER(A.Titolo) LIKE LOWER(?) OR LOWER(A.Descrizione) LIKE LOWER(?))");
	    
	    // aggiunge il filtro per categoria se specificata
	    if (idCategoria != null) {  
	        query.append(" AND C.idCategoria=?");  
	    }
	    
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query.toString());
	        String searchPattern = "%" + testoRicerca + "%";
	        pstmt.setString(1, searchPattern);
	        pstmt.setString(2, searchPattern);
	        
	        if (idCategoria != null) {
	            pstmt.setInt(3, idCategoria);  // 
	        }
	        
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
	                TipologiaCategoria.fromNome(rs.getString("TipologiaCategoria")),
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
	
}	
