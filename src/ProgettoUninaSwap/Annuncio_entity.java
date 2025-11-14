package ProgettoUninaSwap;

import java.util.Date;

public class Annuncio_entity {
	//REMINDER PER LA FASCIA ORARIA SERVE ENTITà APPOSTA PER ENUM PER TANTO LA LEVO IN ANNUNCIO PER FARE LA SELECT POI VEDREMO COME FARE  tOLTA DAL COSTRUTTORE
	protected String Titolo; 
	protected String Descrizione; 
	protected Enum FasciaOraria; 
	protected String ModalitàConsegna; 
	protected String StatoAnnuncio; 
	protected String Oggetto; 
	protected String CategoriaOggetto; 
	protected Date DataPubblicazione; 
	
	public Annuncio_entity(String Titolo, String Descrizione,  String ModalitàConsegna, 
			String StatoAnnuncio, String Oggetto, String CategoriaOggetto, Date DataPubblicazione ) {
		this.Titolo = Titolo; 
		this.Descrizione = Descrizione; 
	
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
