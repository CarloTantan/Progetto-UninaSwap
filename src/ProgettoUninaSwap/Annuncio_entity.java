package ProgettoUninaSwap;

import java.util.Date;

public class Annuncio_entity {
	private String Titolo; 
	private String Descrizione; 
	private Enum FasciaOraria; 
	private String ModalitàConsegna; 
	private String StatoAnnuncio; 
	private String Oggetto; 
	private String CategoriaOggetto; 
	private Date DataPubblicazione; 
	
	public Annuncio_entity(String Titolo, String Descrizione, Enum FasciaOraria, String ModalitàConsegna, 
			String StatoAnnuncio, String Oggetto, String CategoriaOggetto, Date DataPubblicazione ) {
		this.Titolo = Titolo; 
		this.Descrizione = Descrizione; 
		this.FasciaOraria = FasciaOraria; 
		this.ModalitàConsegna = ModalitàConsegna; 
		this.StatoAnnuncio = StatoAnnuncio; 
		this.Oggetto = Oggetto;
		this.CategoriaOggetto = CategoriaOggetto; 
		this.DataPubblicazione = DataPubblicazione; 
	}
	
//Getter 
	public String getTitolo() {
		return Titolo; 
	}
	
	public String getDescrizione() {
		return Descrizione; 
	}
	
	public Enum getFasciaOraria() {
		return FasciaOraria; 
	}
	
	public String getModalitàConsegna() {
		return ModalitàConsegna;
	}
	
	public String getStatoAnnuncio() {
		return StatoAnnuncio; 
	}
	
	public String getOggetto() {
		return Oggetto; 
	}
	
	public String getCategoriaOggetto() {
		return CategoriaOggetto;
	}
	
	public Date getDataPubblicazione() {
		return DataPubblicazione; 
	}
	
//Setter

	public void setTitolo(String Titolo) {
		this.Titolo = Titolo; 
	}
	
	public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione; 
	}
	
	public void setFasciaOraria(Enum FasciaOraria) {
		this.FasciaOraria = FasciaOraria;
	}
	
	public void setModalitàConsegna(String ModalitàConsegna) {
		this.ModalitàConsegna = ModalitàConsegna; 
	}
	
	public void setStatoAnnuncio(String StatoAnnuncio) {
		this.StatoAnnuncio = StatoAnnuncio;
	}
	
	public void setOggetto(String Oggetto) {
		this.Oggetto = Oggetto; 
	}
	
	public void setCategoriaOggetto(String CategoriaOggetto) {
		this.CategoriaOggetto = CategoriaOggetto; 
	}
	
	public void setDataPubblicazione(Date DataPubblicazione) {
		this.DataPubblicazione = DataPubblicazione; 
	}
}
