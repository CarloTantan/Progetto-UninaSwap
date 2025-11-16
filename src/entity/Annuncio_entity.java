package entity;

import java.util.Date;
import enumerations.FasciaOraria;
import enumerations.TipologiaCategoria;
import enumerations.StatoAnnuncio;

public class Annuncio_entity {
	protected int IdAnnuncio;
	protected String Titolo; 
	protected String Descrizione; 
	protected FasciaOraria FasciaOraria; 
	protected String ModalitàConsegna; 
	protected StatoAnnuncio StatoAnnuncio; 
	protected String idOggetto; 
	protected Date DataPubblicazione; 
	protected TipologiaCategoria categoria;
	protected String MatricolaVenditore;
	
	public Annuncio_entity(int IdAnnuncio, String Titolo, String Descrizione, FasciaOraria FasciaOraria, String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto,TipologiaCategoria categoria ,Date DataPubblicazione, String MatricolaVenditore) {
		this.IdAnnuncio = IdAnnuncio;
		this.Titolo = Titolo; 
		this.Descrizione = Descrizione; 
		this.FasciaOraria = FasciaOraria;
		this.ModalitàConsegna = ModalitàConsegna; 
		this.StatoAnnuncio = StatoAnnuncio; 
		this.idOggetto = idOggetto;
		this.DataPubblicazione = DataPubblicazione; 
		this.categoria=categoria;
		this.MatricolaVenditore = MatricolaVenditore;
	}
	
//Getter
	public int getIdAnnuncio() {
		return IdAnnuncio;
	}
	
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
	
	public TipologiaCategoria getTipologiaCategoria() {
	        return categoria;
	}
	
	public String getMatricolaVenditore() {
		return MatricolaVenditore;
	}
	
//Setter
	
	public void setIdAnnuncio(int IdAnnuncio) {
		this.IdAnnuncio = IdAnnuncio;
	}
	
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
	
	public void setTipologiaCategoria(TipologiaCategoria categoria) {
	        this.categoria = categoria;
	}
	
	public void setMatricolaVenditore(String MatricolaVenditore) {
		this.MatricolaVenditore = MatricolaVenditore;
	}
	
	
}