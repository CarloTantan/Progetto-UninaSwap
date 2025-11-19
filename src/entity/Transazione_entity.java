package entity;


public class Transazione_entity {
	private int IdAnnuncio;
	private int IdOfferta;
	private String MatricolaAcquirente;
	private String MatricolaVenditore;
	
	public Transazione_entity(int IdAnnuncio, int IdOfferta, String MatricolaAcquirente, String MatricolaVenditore) {
		this.IdAnnuncio = IdAnnuncio;
		this.IdOfferta = IdOfferta;
		this.MatricolaAcquirente = MatricolaAcquirente;
		this.MatricolaVenditore = MatricolaVenditore;
	}
	
	//getter
	
	public int getIdAnnuncio() {
		return IdAnnuncio;
	}
	
	public int getIdOfferta() {
		return IdOfferta;
	}
	
	
	public String getMatricolaAcquirente() {
		return MatricolaAcquirente;
	}
	
	public String getMatricolaVenditore() {
		return MatricolaVenditore;
	}
	
	//setter
	
	public void setIdAnnuncio(int IdAnnuncio) {
		this.IdAnnuncio = IdAnnuncio;
	}
	
	public void setIdOfferta(int IdOfferta) {
		this.IdOfferta = IdOfferta;
	}
		
	public void setMatricolaAcquirente(String MatricolaAcquirente) {
		this.MatricolaAcquirente = MatricolaAcquirente;
	}
	
	public void setMatricolaVenditore(String MatricolaVenditore) {
		this.MatricolaVenditore = MatricolaVenditore;
	}
	
	

}
