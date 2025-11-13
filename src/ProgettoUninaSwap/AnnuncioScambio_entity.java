package ProgettoUninaSwap;

import java.util.Date;

public class AnnuncioScambio_entity extends Annuncio_entity{
	private String OggettoRichiesto; 
	
	public AnnuncioScambio_entity(String Titolo, String Descrizione, Enum FasciaOraria, String ModalitàConsegna, 
			String StatoAnnuncio, String Oggetto, String CategoriaOggetto, Date DataPubblicazione, String OggettoRichiesto) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, Oggetto, CategoriaOggetto, DataPubblicazione);
		this.OggettoRichiesto = OggettoRichiesto; 
	}
	
	public String getOggettoRichiesto() {
		return OggettoRichiesto; 
	}
	
	public void setOggettoRichiesto(String OggettoRichiesto) {
		this.OggettoRichiesto = OggettoRichiesto; 
	}
}
