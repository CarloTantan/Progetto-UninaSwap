package entity;

import java.util.Date;
import enumerations.FasciaOraria;
import enumerations.TipologiaCategoria;
import enumerations.StatoAnnuncio;

public class Annuncio_entity {
	protected String Titolo; 
	protected String Descrizione; 
	protected FasciaOraria FasciaOraria; 
	protected String ModalitàConsegna; 
	protected StatoAnnuncio StatoAnnuncio; 
	protected String idOggetto; 
	protected Date DataPubblicazione; 
	protected TipologiaCategoria categoria; 
	
	public Annuncio_entity(String Titolo, String Descrizione, FasciaOraria FasciaOraria, String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto,TipologiaCategoria categoria ,Date DataPubblicazione ) {
		this.Titolo = Titolo; 
		this.Descrizione = Descrizione; 
		this.FasciaOraria = FasciaOraria;
		this.ModalitàConsegna = ModalitàConsegna; 
		this.StatoAnnuncio = StatoAnnuncio; 
		this.idOggetto = idOggetto;
		this.DataPubblicazione = DataPubblicazione; 
		this.categoria=categoria;
	}
	
//Getter 
	public String getTitolo() {
		return Titolo; 
	}
	
	public String getDescrizione() {
		return Descrizione; 
	}
	
	public FasciaOraria getFasciaOraria() {
		return FasciaOraria; 
	}
	
	public String getModalitàConsegna() {
		return ModalitàConsegna;
	}
	
	public StatoAnnuncio getStatoAnnuncio() {
		return StatoAnnuncio; 
	}
	
	public String getidOggetto() {
		return idOggetto; 
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
	
	public void setFasciaOraria(FasciaOraria FasciaOraria) {
		this.FasciaOraria = FasciaOraria;
	}
	
	public void setModalitàConsegna(String ModalitàConsegna) {
		this.ModalitàConsegna = ModalitàConsegna; 
	}
	
	public void setStatoAnnuncio(StatoAnnuncio StatoAnnuncio) {
		this.StatoAnnuncio = StatoAnnuncio;
	}
	
	public void setOggetto(String idOggetto) {
		this.idOggetto = idOggetto; 
	}
	
	public void setDataPubblicazione(Date DataPubblicazione) {
		this.DataPubblicazione = DataPubblicazione; 
	}
	
	
	 public TipologiaCategoria getTipologiaCategoria() {
	        return categoria;
	    }
	    
	    public void setTipologiaCategoria(TipologiaCategoria categoria) {
	        this.categoria = categoria;
	    }
	
	
	
	
}