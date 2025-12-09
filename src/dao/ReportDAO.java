package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Regalo'";

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
	        	offerteRegalo = rs.getInt("OfferteInviateRegalo");
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
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Scambio'";

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
	        	offerteScambio = rs.getInt("OfferteInviateScambio");
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
	    String query = "SELECT COUNT(*) as OfferteInviateVendita " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Vendita'";

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
	        	offerteVendita = rs.getInt("OfferteInviateVendita");
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
	                   "WHERE MatricolaAcquirente = ? AND Tipologia= 'Regalo' AND Stato='Accettata'";

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
	        	offerteRegaloAccettata = rs.getInt("OfferteRegaloAccettate");
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
	    String query = "SELECT COUNT(*) as OfferteScambioAccettate " +
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
	        	offerteScambioAccettata = rs.getInt("OfferteScambioAccettate");
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
	    String query = "SELECT COUNT(*) as OfferteVenditaAccettate " +
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
	        	offerteVenditaAccettata = rs.getInt("OfferteVenditaAccettate");
	        }
	    } finally {
	        // Chiudi risorse
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteVenditaAccettata;  // Restituisci il numero totale delle offerte
	}
	
	public String[] getPrezziAnnunci(String MatricolaUtente) throws SQLException {
	    String query = "SELECT ROUND(MIN(ImportoProposto), 2) AS PrezzoMinimo, "
	                 + "       ROUND(MAX(ImportoProposto), 2) AS PrezzoMassimo, "
	                 + "       ROUND(AVG(ImportoProposto), 2) AS PrezzoMedio "
	                 + "FROM Offerta "
	                 + "WHERE Tipologia = 'Vendita' AND MatricolaAcquirente = ? AND ImportoProposto IS NOT NULL AND Stato = 'Accettata'";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, MatricolaUtente);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            BigDecimal prezzoMinimo = rs.getBigDecimal("PrezzoMinimo");
	            BigDecimal prezzoMassimo = rs.getBigDecimal("PrezzoMassimo");
	            BigDecimal prezzoMedio = rs.getBigDecimal("PrezzoMedio");

	            // Gestisci il caso in cui non ci sono risultati (tutti NULL)
	            if (prezzoMinimo == null) {
	                return new String[]{"NonEsistePrezzoMinimo", "NonEsistePrezzoMassimo", "NonEsistePrezzoMedio"};
	            }

	            // Formatta in Java con il simbolo €
	            return new String[]{
	                "€" + prezzoMinimo.toString(),
	                "€" + prezzoMassimo.toString(),
	                "€" + prezzoMedio.toString()
	            };
	        }

	        return new String[]{"Non Esiste Prezzo Minimo", "Non Esiste Prezzo Massimo", "Non Esiste Prezzo Medio"};

	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }
	}
	
	
}
