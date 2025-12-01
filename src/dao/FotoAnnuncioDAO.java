package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FotoAnnuncioDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
	}
	
	//inserisce una foto per un annuncio
    public int inserisciFoto(String url, int idAnnuncio) throws SQLException {
        String query = "INSERT INTO FotoAnnuncio (url, idAnnuncio) VALUES (?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, url);
            pstmt.setInt(2, idAnnuncio);
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserimento foto fallito, nessun ID ottenuto.");
                }
            }
        }
    }
    
    //recupera le foto associate ad un annuncio
    public ArrayList<String> getFotoByAnnuncio(int idAnnuncio) throws SQLException {
        String query = "SELECT url FROM FotoAnnuncio WHERE idAnnuncio = ?";
        ArrayList<String> foto = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idAnnuncio);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    foto.add(rs.getString("url"));
                }
            }
        }
        
        return foto;
    }
    
    //elimina una foto
    public void eliminaFoto(int idFoto) throws SQLException {
        String query = "DELETE FROM FotoAnnuncio WHERE idFoto = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idFoto);
            pstmt.executeUpdate();
        }
    }
    
    //elimina tutte le foto
    public void eliminaFotoByAnnuncio(int idAnnuncio) throws SQLException {
        String query = "DELETE FROM FotoAnnuncio WHERE idAnnuncio = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idAnnuncio);
            pstmt.executeUpdate();
        }
    }

}
