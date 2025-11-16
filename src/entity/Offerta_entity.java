package entity;

public class Offerta_entity {
	protected int IdOfferta;
	protected String StatoOfferta;
	protected String MatricolaAcquirente;
	protected int IdAnnuncio;
	 
	
	public Offerta_entity(int IdOfferta, String StatoOfferta, String MatricolaAcquirente, int IdAnnuncio) {
		this.IdOfferta = IdOfferta;
		this.StatoOfferta = StatoOfferta;
		this.MatricolaAcquirente = MatricolaAcquirente;
		this.IdAnnuncio = IdAnnuncio;
	}
	
//Getter	
	public int getIdOfferta() {
		return IdOfferta;
	}
	
	public String getStatoOfferta() {
		return StatoOfferta;
	}
	
	public String getMatricolaAcquirente() {
		return MatricolaAcquirente;
	}
	
	public int getIdAnnuncio() {
		return IdAnnuncio;
	}
	
//Setter
	public void setIdOfferta(int IdOfferta) {
		this.IdOfferta = IdOfferta;
	}
	
	public void setStatoOfferta(String StatoOfferta) {
		this.StatoOfferta = StatoOfferta;
	}
	
	public void setMatricolaAcquirente(String MatricolaAcquirente) {
		this.MatricolaAcquirente = MatricolaAcquirente;
	}
	
	public void setIdAnnuncio(int IdAnnuncio) {
		this.IdAnnuncio = IdAnnuncio;
	}
}
