package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Offerta_entity;

public class StoricoOfferteDAO {
	String url = "jdbc:postgresql://localhost:5432/UninaSwapDefinitivo";
	String user= "postgres";
	String password = "Database";
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
	
	public ArrayList<Offerta_entity> getOfferte(String matricola) throws SQLException {
		ArrayList<Offerta_entity> ListaOfferte = new ArrayList<>();
		String query = "SELECT * FROM Offerta WHERE MatricolaAcquirente = ? ORDER BY IdOfferta DESC";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, matricola);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Offerta_entity Offerte = new Offerta_entity(
						rs.getInt("IdOfferta"),
						rs.getString("Stato"),
						rs.getString("MatricolaAcquirente"),
						rs.getInt("IdAnnuncio"),
						rs.getString("Tipologia")
					);
					ListaOfferte.add(Offerte);
				}
			}
		}
		
		return ListaOfferte;
	}
	
	public boolean DeleteOfferte(int IdOfferta) throws SQLException {
		String query = "DELETE FROM Offerta WHERE IdOfferta = ?";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, IdOfferta);
			int righeEliminate = pstmt.executeUpdate();
			return righeEliminate > 0;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public int getIdAnnuncioFromOfferta(int IdOfferta) throws SQLException {
		String query = "SELECT IdAnnuncio FROM Offerta WHERE IdOfferta = ?";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setInt(1, IdOfferta);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("IdAnnuncio");
				}
			}
		}
		return -1;
	}
	
	
	 //Recupera il titolo dell'annuncio associato all'offerta
	 
	public String getTitoloAnnuncio(int IdOfferta) throws SQLException {
		String query = """
			SELECT A.Titolo 
			FROM Annuncio A 
			JOIN Offerta O ON A.IdAnnuncio = O.IdAnnuncio 
			WHERE O.IdOfferta = ?
		""";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, IdOfferta);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("Titolo");
				}
			}
		}
		return null;
	}
	
	 //Recupera la matricola del venditore dall'annuncio associato all'offerta
	 
	public String getMatricolaVenditore(int IdOfferta) throws SQLException {
		String query = """
			SELECT A.MatricolaVenditore 
			FROM Annuncio A 
			JOIN Offerta O ON A.IdAnnuncio = O.IdAnnuncio 
			WHERE O.IdOfferta = ?
		""";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, IdOfferta);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("MatricolaVenditore");
				}
			}
		}
		return null;
	}
	
	
	 //Recupera il dettaglio dell'offerta basato sulla tipologia
	
	public String getDettaglioOfferta(int IdOfferta) throws SQLException {
		String query = """
			SELECT Tipologia, ImportoProposto, OggettoProposto, MessaggioMotivazionale
			FROM Offerta 
			WHERE IdOfferta = ?
		""";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, IdOfferta);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String tipologia = rs.getString("Tipologia");
					
					switch (tipologia.toLowerCase()) {
						case "vendita":
							double importo = rs.getDouble("ImportoProposto");
							return String.format("€ %.2f", importo);
						
						case "scambio":
							String oggetto = rs.getString("OggettoProposto");
							return oggetto != null ? oggetto : "N/D";
						
						case "regalo":
							String messaggio = rs.getString("MessaggioMotivazionale");
							return messaggio != null ? messaggio : "Nessun messaggio";
						
						default:
							return "N/D";
					}
				}
			}
		}
		return null;
	}
	
	
	 //Verifica se un'offerta è modificabile (solo se è "In Attesa")
	 
	public boolean isOffertaModificabile(int IdOfferta) throws SQLException {
		String query = "SELECT Stato FROM Offerta WHERE IdOfferta = ?";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, IdOfferta);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String stato = rs.getString("Stato");
					return "In Attesa".equalsIgnoreCase(stato);
				}
			}
		}
		return false;
	}
}