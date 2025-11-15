package ProgettoUninaSwap;
//gestione fasciaoraira
import java.util.Date;

public class AnnuncioScambio_entity extends Annuncio_entity{
	private String OggettoRichiesto; 
	
	public AnnuncioScambio_entity(String Titolo, String Descrizione, FasciaOraria FasciaOraria,  String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, Date DataPubblicazione, String OggettoRichiesto) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, DataPubblicazione);
		this.OggettoRichiesto = OggettoRichiesto; 
	}
	
	public String getOggettoRichiesto() {
		return OggettoRichiesto; 
	}
	
	public void setOggettoRichiesto(String OggettoRichiesto) {
		this.OggettoRichiesto = OggettoRichiesto; 
	}
}
