package entity;


public class Transazione_entity {
	private String TitoloAnnuncio;
	private int IdAnnuncio;
	private int IdOfferta;
	private String MatricolaAcquirente;
	private String MatricolaVenditore;
	private boolean HasRecensione;
	
	public Transazione_entity(String TitoloAnnuncio, int IdAnnuncio, int IdOfferta, String MatricolaAcquirente, String MatricolaVenditore, boolean HasRecensione) {
		this.TitoloAnnuncio = TitoloAnnuncio;
		this.IdAnnuncio = IdAnnuncio;
		this.IdOfferta = IdOfferta;
		this.MatricolaAcquirente = MatricolaAcquirente;
		this.MatricolaVenditore = MatricolaVenditore;
		this.HasRecensione = HasRecensione;
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
	
	public String getTitoloAnnuncio() {
		return TitoloAnnuncio;
	}
	
	public boolean hasRecensione() { 
		return HasRecensione;
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
	
	public void setTitoloAnnuncio(String TitoloAnnuncio) {
		this.TitoloAnnuncio = TitoloAnnuncio;
	}
	
	public void setHasRecensione(boolean HasRecensione) { 
        this.HasRecensione = HasRecensione; 
    }

}
