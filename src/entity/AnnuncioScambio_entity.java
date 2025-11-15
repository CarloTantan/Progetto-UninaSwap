package entity;
//gestione fasciaoraira
import java.util.Date;

import enumerations.StatoAnnuncio;
import enumerations.TipologiaCategoria;
import enumerations.FasciaOraria;

public class AnnuncioScambio_entity extends Annuncio_entity{
	private String OggettoRichiesto; 
	
	public AnnuncioScambio_entity(String Titolo, String Descrizione, FasciaOraria FasciaOraria,  String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, TipologiaCategoria categoria, Date DataPubblicazione, String OggettoRichiesto) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, categoria, DataPubblicazione);
		this.OggettoRichiesto = OggettoRichiesto; 
	}
	
	public String getOggettoRichiesto() {
		return OggettoRichiesto; 
	}
	
	public void setOggettoRichiesto(String OggettoRichiesto) {
		this.OggettoRichiesto = OggettoRichiesto; 
	}
}