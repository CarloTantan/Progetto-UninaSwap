package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// classe DAO per recuperare statistiche e informazioni sulle recensioni dei venditori
// ha dei metodi per calcolare le valutazioni medie e informazioni correlate
public class RecensioneVenditoreDAO {
    String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
    String user = "postgres";
    String password = "Database";
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    // Calcola e ritorna la valutazione media di un venditore
    public double getValutazioneMedia(String matricolaVenditore) throws SQLException {
        String query = "SELECT AVG(Punteggio) as media " +
                       "FROM Recensione " +
                       "WHERE MatricolaVenditore = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, matricolaVenditore);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                double media = rs.getDouble("media");
                
                // Se non ci sono recensioni, AVG restituisce NULL e getDouble ritorna 0.0
                return rs.wasNull() ? 0.0 : media;
            }
            
            return 0.0;
        }
    }
    
    // RItorna il numero totale di recensioni ricevute da un venditore
    public int getNumeroRecensioni(String matricolaVenditore) throws SQLException {
        String query = "SELECT COUNT(*) as totale " +
                       "FROM Recensione " +
                       "WHERE MatricolaVenditore = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, matricolaVenditore);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("totale");
            }
            
            return 0;
        }
    }
    
    
    // Ritorna il nominativo di un utente dalla matricola
    public String getNominativoUtente(String matricola) throws SQLException {
        String query = "SELECT Nominativo FROM Utente WHERE Matricola = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, matricola);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("Nominativo");
            }
            
            return null;
        }
    }
    
   // Ritorna il titolo dell'annuncio associato a un'offerta
   public String getTitoloAnnuncioDaOfferta(int idOfferta) throws SQLException {
       String query = "SELECT a.Titolo " +
                      "FROM Annuncio a " +
                      "JOIN Offerta o ON a.idAnnuncio = o.idAnnuncio " +
                      "WHERE o.idOfferta = ?";
       
       try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
           
           pstmt.setInt(1, idOfferta);
           ResultSet rs = pstmt.executeQuery();
           
           if (rs.next()) {
               return rs.getString("Titolo");
           }
           
           return null;
       }
   }
}