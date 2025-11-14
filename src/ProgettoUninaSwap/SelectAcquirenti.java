package ProgettoUninaSwap;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAcquirenti {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
		
	public ArrayList<Utente_entity> getUtenti() throws SQLException {
		ArrayList<Utente_entity> utenti = new ArrayList<>();
	    String query= "SELECT * FROM acquirente";
	    PreparedStatement pstmt= null;
	    ResultSet rs= null;
	    Connection conn = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				Utente_entity utente= new Utente_entity(
						rs.getString("matricolaacquirente"), 
						rs.getString("nominativo"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getString("numerotelefono")
					);
				utenti.add(utente);
			} } finally {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	        }

	        return utenti;
	}
		
}



