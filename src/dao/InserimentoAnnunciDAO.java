package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enumerations.FasciaOraria;

// Classe DAO per l'inserimento di nuovi annunci nel database
public class InserimentoAnnunciDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaSwapDefinitivo", "postgres", "Database");
    }

	// Inserisce un nuovo annuncio di tipo scambio nel database
	// ritorna l'id dell'annuncio creato
	
    public int inserisciAnnuncioScambio(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String oggettoRichiesto, String matricolaVenditore, int idOggetto) throws SQLException {
        String query = "INSERT INTO Annuncio (titolo, descrizione, "
        		+ "ModalitàConsegna, fasciaOraria, oggettoRichiesto, matricolaVenditore, idOggetto ) VALUES (?, ?, ?, ?::FasciaOraria, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setString(3, modalitaConsegna);
            pstmt.setString(4, fasciaOraria.getFascia());  
            pstmt.setString(5, oggettoRichiesto);          
            pstmt.setString(6, matricolaVenditore);
            pstmt.setInt(7, idOggetto);

            pstmt.executeUpdate();
            
            // Recupera l'ID generato dal database
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserimento annuncio fallito, nessun ID ottenuto.");
                }
            }            
        }
    }
    
	// Inserisce un nuovo annuncio di tipo regalo nel database
	// ritorna l'id dell'annuncio creato
    
    public int inserisciAnnuncioRegalo(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String motivoCessione, String matricolaVenditore, int idOggetto) throws SQLException {
        String query = "INSERT INTO Annuncio (titolo, descrizione, "
        		+ "ModalitàConsegna, fasciaOraria, motivoCessione, matricolaVenditore, idOggetto ) VALUES (?, ?, ?, ?::FasciaOraria, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setString(3, modalitaConsegna);
            pstmt.setString(4, fasciaOraria.getFascia()); 
            pstmt.setString(5, motivoCessione);                       
            pstmt.setString(6, matricolaVenditore);
            pstmt.setInt(7, idOggetto);

            pstmt.executeUpdate();
            
            // Recupera l'ID generato dal database
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserimento annuncio fallito, nessun ID ottenuto.");
                }
            }            
        }
    }
 
	// Inserisce un nuovo annuncio di tipo vendita nel database
	// ritorna l'id dell'annuncio creato
    
    public int inserisciAnnuncioVendita(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, float prezzoVendita, String matricolaVenditore, int idOggetto) throws SQLException {
        String query = "INSERT INTO Annuncio (titolo, descrizione, "
        		+ "ModalitàConsegna, fasciaOraria, prezzoVendita, matricolaVenditore, idOggetto ) VALUES (?, ?, ?, ?::FasciaOraria, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setString(3, modalitaConsegna);
            pstmt.setString(4, fasciaOraria.getFascia());
            pstmt.setFloat(5, prezzoVendita);            
            pstmt.setString(6, matricolaVenditore);
            pstmt.setInt(7, idOggetto);

            pstmt.executeUpdate();
            
            // Recupera l'ID generato dal database
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserimento annuncio fallito, nessun ID ottenuto.");
                }
            }            
        }
    }
    
}
