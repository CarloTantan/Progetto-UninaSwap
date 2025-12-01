package entity;

public class Offerta_entity {
	protected int IdOfferta;
	protected String StatoOfferta;
	protected String MatricolaAcquirente;
	protected String TipologiaOfferta ;
	protected int IdAnnuncio;

	
	
	public Offerta_entity(int IdOfferta, String StatoOfferta, String MatricolaAcquirente, int IdAnnuncio,String TipologiaOfferta ) {
		this.IdOfferta = IdOfferta;
		this.StatoOfferta = StatoOfferta;
		this.MatricolaAcquirente = MatricolaAcquirente;
		this.IdAnnuncio = IdAnnuncio;
		this.TipologiaOfferta=TipologiaOfferta;
		
	}
	
//Getter	
	public int getIdOfferta() {
		return IdOfferta;
	}
	
	
	public String getStatoOfferta() {
		return StatoOfferta;
	}
	public String getTipologiaOfferta() {
		return TipologiaOfferta;
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
	
	public void setTipologiaOfferta(String TipologiaOfferta) {
		this.TipologiaOfferta = TipologiaOfferta;
	}
	public void setStato(String StatoOfferta) {
		this.StatoOfferta = StatoOfferta;
	}
	
	public void setMatricolaAcquirente(String MatricolaAcquirente) {
		this.MatricolaAcquirente = MatricolaAcquirente;
	}
	
	public void setIdAnnuncio(int IdAnnuncio) {
		this.IdAnnuncio = IdAnnuncio;
	}
	
	
	
	
	
	
}
