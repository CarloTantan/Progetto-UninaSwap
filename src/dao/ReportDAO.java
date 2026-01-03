package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CLasse DAO per la generazione di report e statistiche sulle offerte di un utente
// ha dei metodi per calcolare il numero di offerte inviate e accettate per tipologia
// e per calcolare i prezzi minimi, massimi e medi per le offerte di vendita
public class ReportDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
	
	// Calcola e ritorna il numero totale di offerte inviate da un utente
	public int VisualizzaOfferteTotali(String matricola) throws SQLException {
	    int offerteTotali = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteInviateTOT " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? ";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola); 

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            offerteTotali = rs.getInt("OfferteInviateTOT");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteTotali;
	}
	
	// Calcola e ritorna il numero di offerte di regalo inviate da un utente
	public int VisualizzaOfferteRegalo(String matricola) throws SQLException {
	    int offerteRegalo = 0; 
	    String query = "SELECT COUNT(*) as OfferteInviateRegalo " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Regalo'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	offerteRegalo = rs.getInt("OfferteInviateRegalo");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteRegalo;
	}
	
	// Calcola e ritorna il numero di offerte di scambio inviate da un utente
	public int VisualizzaOfferteScambio(String matricola) throws SQLException {
	    int offerteScambio = 0;  // Variabile per memorizzare il totale delle offerte
	    String query = "SELECT COUNT(*) as OfferteInviateScambio " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Scambio'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	offerteScambio = rs.getInt("OfferteInviateScambio");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteScambio;
	}
	
	// Calcola e ritorna il numero di offerte di vendita inviate da un utente
	public int VisualizzaOfferteVendita(String matricola) throws SQLException {
	    int offerteVendita = 0; 
	    String query = "SELECT COUNT(*) as OfferteInviateVendita " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Vendita'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);

	        // Esegui la query
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	offerteVendita = rs.getInt("OfferteInviateVendita");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteVendita; 
	}
	
	// Calcola e ritorna il numero di offerte di regalo inviate ed accettate di un utente
	public int VisualizzaOfferteRegaloAccettata(String matricola) throws SQLException {
	    int offerteRegaloAccettata = 0; 
	    String query = "SELECT COUNT(*) as OfferteRegaloAccettate " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? AND Tipologia= 'Regalo' AND Stato='Accettata'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola); 

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	offerteRegaloAccettata = rs.getInt("OfferteRegaloAccettate");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteRegaloAccettata; 
	}
	
	// Calcola e ritorna il numero di offerte di scambio inviate ed accettate di un utente
	public int VisualizzaOfferteScambioAccettata(String matricola) throws SQLException {
	    int offerteScambioAccettata = 0;
	    String query = "SELECT COUNT(*) as OfferteScambioAccettate " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Scambio' and Stato='Accettata'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	offerteScambioAccettata = rs.getInt("OfferteScambioAccettate");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteScambioAccettata; 
	}
	
	// Calcola e ritorna il numero di offerte di vendita inviate ed accettate di un utente
	public int VisualizzaOfferteVenditaAccettata(String matricola) throws SQLException {
	    int offerteVenditaAccettata = 0; 
	    String query = "SELECT COUNT(*) as OfferteVenditaAccettate " +
	                   "FROM Offerta " +
	                   "WHERE MatricolaAcquirente = ? and Tipologia= 'Vendita' and Stato='Accettata'";

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);  

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	offerteVenditaAccettata = rs.getInt("OfferteVenditaAccettate");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return offerteVenditaAccettata; 
	}
	
	// Calcola le statistiche sui prezzi delle offerte accettate di un utente
	// ritorna un array con i prezzi minimo, massimo e medio (formattati con €)
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

	            // Gestisce il caso in cui non ci sono risultati (tutti null)
	            if (prezzoMinimo == null) {
	                return new String[]{"NonEsistePrezzoMinimo", "NonEsistePrezzoMassimo", "NonEsistePrezzoMedio"};
	            }

	            // Formatta con il simbolo €
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
