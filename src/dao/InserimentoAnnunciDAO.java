package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import enumerations.FasciaOraria;

public class InserimentoAnnunciDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaSwapDefinitivo", "postgres", "Database");
    }

//Scambio
    public void inserisciAnnuncioScambio(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String oggettoRichiesto, String matricolaVenditore, int idOggetto) throws SQLException {
        String query = "INSERT INTO Annuncio (titolo, descrizione, "
        		+ "modalitaConsegna, fasciaOraria, oggettoRichiesto, matricolaVenditore, idOggetto ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setString(3, modalitaConsegna);
            pstmt.setString(4, oggettoRichiesto);
            pstmt.setString(5, fasciaOraria.getFascia());            
            pstmt.setString(6, matricolaVenditore);
            pstmt.setInt(7, idOggetto);

            pstmt.executeUpdate();
        }
    }
    
    
//Regalo    
    public void inserisciAnnuncioRegalo(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String motivoCessione, String matricolaVenditore, int idOggetto) throws SQLException {
        String query = "INSERT INTO Annuncio (titolo, descrizione, "
        		+ "modalitaConsegna, fasciaOraria, motivoCessione, matricolaVenditore, idOggetto ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setString(3, modalitaConsegna);
            pstmt.setString(4, motivoCessione);
            pstmt.setString(5, fasciaOraria.getFascia());            
            pstmt.setString(6, matricolaVenditore);
            pstmt.setInt(7, idOggetto);

            pstmt.executeUpdate();
        }
    }
 
    
//Vendita
    public void inserisciAnnuncioVendita(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, float prezzoVendita, String matricolaVenditore, int idOggetto) throws SQLException {
        String query = "INSERT INTO Annuncio (titolo, descrizione, "
        		+ "modalitaConsegna, fasciaOraria, prezzoVendita, matricolaVenditore, idOggetto ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, titolo);
            pstmt.setString(2, descrizione);
            pstmt.setString(3, modalitaConsegna);
            pstmt.setFloat(4, prezzoVendita);
            pstmt.setString(5, fasciaOraria.getFascia());            
            pstmt.setString(6, matricolaVenditore);
            pstmt.setInt(7, idOggetto);

            pstmt.executeUpdate();
        }
    }
    
}
