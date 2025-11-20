package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import entity.Transazione_entity;


public class TransazioniDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	public ArrayList<Transazione_entity> getTransazioni(String matricola) throws SQLException {
		ArrayList<Transazione_entity> ListaTransazioni = new ArrayList<>();
		String query = "SELECT A.Titolo, A.MatricolaVenditore, O.MatricolaAcquirente, " +
                "O.idOfferta, O.idAnnuncio, " +
                "CASE WHEN R.idRecensione IS NOT NULL THEN true ELSE false END as HasRecensione " +
                "FROM Offerta AS O " +
                "JOIN Annuncio AS A ON O.idAnnuncio = A.idAnnuncio " +
                "LEFT JOIN Recensione AS R ON R.idOfferta = O.idOfferta " +
                "WHERE O.stato = 'Accettata' AND O.MatricolaAcquirente = ?";
	    
		try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, matricola);
	        
	        try (ResultSet rs = pstmt.executeQuery()){
	        	while (rs.next()) {
		            Transazione_entity Transazione = new Transazione_entity(
		            	rs.getString("Titolo"),
		            	rs.getInt("IdAnnuncio"),
		            	rs.getInt("IdOfferta"),
		            	rs.getString("MatricolaAcquirente"),
		            	rs.getString("MatricolaVenditore"),
		            	rs.getBoolean("HasRecensione")      
	            );
	            ListaTransazioni.add(Transazione);
	        	}
	        }
		}
	    
	    return ListaTransazioni; 
	}
}
	


