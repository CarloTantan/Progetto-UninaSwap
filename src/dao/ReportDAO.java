package dao;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Annuncio_entity;
import entity.Offerta_entity;
import entity.Recensione_entity;
import enumerations.FasciaOraria;
import enumerations.StatoAnnuncio;
import enumerations.TipologiaCategoria;

public class ReportDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
		
	public int VisualizzaOfferteTotali(String matricola) throws SQLException {
	    int offerteTotali = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteInviateTOT " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? ";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	            offerteTotali = rs.getInt("OfferteInviateTOT");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteTotali;  // Restituisci il numero totale delle offerte
	}
	
	
	public int VisualizzaOfferteRegalo(String matricola) throws SQLException {
	    int offerteRegalo = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteInviateRegalo " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and TipologiaOfferta= Regalo";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	        	offerteRegalo = rs.getInt("OfferteRegalo");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteRegalo;  // Restituisci il numero totale delle offerte
	}
	public int VisualizzaOfferteScambio(String matricola) throws SQLException {
	    int offerteScambio = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteInviateScambio " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and TipologiaOfferta= Scambio";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	        	offerteScambio = rs.getInt("OfferteScambio");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteScambio;  // Restituisci il numero totale delle offerte
	}
	
	public int VisualizzaOfferteVendita(String matricola) throws SQLException {
	    int offerteVendita = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteInviateScambio " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and TipologiaOfferta= Vendita";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	        	offerteVendita = rs.getInt("OfferteVendita");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteVendita;  // Restituisci il numero totale delle offerte
	}
	public int VisualizzaOfferteRegaloAccettata(String matricola) throws SQLException {
	    int offerteRegaloAccettata = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteRegaloAccettate " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Regalo' and Stato='Accettata'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	        	offerteRegaloAccettata = rs.getInt("OfferteRegaloA");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteRegaloAccettata;  // Restituisci il numero totale delle offerte
	}
	
	public int VisualizzaOfferteScambioAccettata(String matricola) throws SQLException {
	    int offerteScambioAccettata = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteRegaloAccettate " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Scambio' and Stato='Accettata'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	        	offerteScambioAccettata = rs.getInt("OfferteScambioA");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteScambioAccettata;  // Restituisci il numero totale delle offerte
	}
	
	
	public int VisualizzaOfferteVenditaAccettata(String matricola) throws SQLException {
	    int offerteVenditaAccettata = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteRegaloAccettate " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Vendita' and Stato='Accettata'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        // Ottieni la connessione
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  // Imposta il parametro 'matricola'

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        // Se il risultato è presente, leggi il conteggio
	        if (rs.next()) {
	        	offerteVenditaAccettata = rs.getInt("OfferteScambioA");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteVenditaAccettata;  // Restituisci il numero totale delle offerte
	}
	
	public String[] getPrezziAnnunci() throws SQLException {
	    String query = "SELECT CONCAT('€', FORMAT(MIN(PrezzoVendita), 2)) AS PrezzoMinimo, "
	                 + "       CONCAT('€', FORMAT(MAX(PrezzoVendita), 2)) AS PrezzoMassimo, "
	                 + "       CONCAT('€', FORMAT(AVG(PrezzoVendita), 2)) AS PrezzoMedio "
	                 + "FROM Annuncio AS A "
	                 + "JOIN Offerta AS O ON O.IdAnnuncio = A.IdAnnuncio "
	                 + "WHERE TipologiaAnnuncio = 'Vendita'";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String prezzoMinimo = rs.getString("PrezzoMinimo");
	            String prezzoMassimo = rs.getString("PrezzoMassimo");
	            String prezzoMedio = rs.getString("PrezzoMedio");

	            return new String[]{prezzoMinimo, prezzoMassimo, prezzoMedio};
	        }
	        
	        // Nessun risultato trovato
	        return new String[]{"NonEsistePrezzoMinimo", "NonEsistePrezzoMassimo", "NonEsistePrezzoMedio"};
	        
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}
