package entity;

public class Offerta_entity {
	protected int IdOfferta;
	protected String StatoOfferta;
	protected String MatricolaAcquirente;
	protected String TipologiaOfferta ;
	protected int IdAnnuncio;
	protected int NumeroOfferte;
	
	
	public Offerta_entity(int IdOfferta, String StatoOfferta, String MatricolaAcquirente, int IdAnnuncio,String TipologiaOfferta,int NumeroOfferte ) {
		this.IdOfferta = IdOfferta;
		this.StatoOfferta = StatoOfferta;
		this.MatricolaAcquirente = MatricolaAcquirente;
		this.IdAnnuncio = IdAnnuncio;
		this.TipologiaOfferta=TipologiaOfferta;
		this.NumeroOfferte= NumeroOfferte;
	}
	
//Getter	
	public int getIdOfferta() {
		return IdOfferta;
	}
	public int getNumeroOfferte() {
		return NumeroOfferte;
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
	public void set(String StatoOfferta) {
		this.StatoOfferta = StatoOfferta;
	}
	
	public void setMatricolaAcquirente(String MatricolaAcquirente) {
		this.MatricolaAcquirente = MatricolaAcquirente;
	}
	
	public void setIdAnnuncio(int IdAnnuncio) {
		this.IdAnnuncio = IdAnnuncio;
	}
	public void setNumeroOfferte(int NumeroOfferte) {
		this.NumeroOfferte = NumeroOfferte;
	}
	
	
	
	
	
}
