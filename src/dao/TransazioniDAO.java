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
		String query = "SELECT A.MatricolaVenditore, O.MatricolaAcquirente, O.idOfferta, O.idAnnuncio "
				+ "FROM Offerta AS O JOIN Annuncio AS A ON O.idannuncio = A.idannuncio "
				+ "WHERE O.stato = 'Accettata' AND O.MatricolaAcquirente = ?";
		
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;
	    
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, matricola);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Transazione_entity Transazione = new Transazione_entity(
	            	rs.getInt("IdAnnuncio"),
	            	rs.getInt("IdOfferta"),
	            	rs.getString("MatricolaAcquirente"),
	            	rs.getString("MatricolaVenditore")
	            );
	            ListaTransazioni.add(Transazione);
	        }
	    } finally {
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
	        if(conn != null) conn.close();
	    }
	    
	    return ListaTransazioni; 
	}
}
	


