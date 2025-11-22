package entity;
//gestione fasciaoraira
import java.util.Date;
import enumerations.FasciaOraria;
import enumerations.TipologiaCategoria;
import enumerations.StatoAnnuncio;

public class AnnuncioScambio_entity extends Annuncio_entity{
	private String OggettoRichiesto; 
	
	public AnnuncioScambio_entity(int IdAnnuncio, String Titolo, String Descrizione, FasciaOraria FasciaOraria, String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, TipologiaCategoria categoria, Date DataPubblicazione , String MatricolaVenditore, String OggettoRichiesto,boolean VisualizzaOfferta) {
		super(IdAnnuncio, Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, categoria, DataPubblicazione, MatricolaVenditore,VisualizzaOfferta);
		this.OggettoRichiesto = OggettoRichiesto; 
	}
	
	public String getOggettoRichiesto() {
		return OggettoRichiesto; 
	}
	
	public void setOggettoRichiesto(String OggettoRichiesto) {
		this.OggettoRichiesto = OggettoRichiesto; 
	}
}