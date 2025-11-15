package ProgettoUninaSwap;
//gestione fasciaoraira
import java.util.Date;

public class AnnuncioRegalo_entity extends Annuncio_entity{
	private String MotivoCessione; 
	
	public AnnuncioRegalo_entity(String Titolo, String Descrizione, FasciaOraria FasciaOraria, String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, TipologiaCategoria categoria, Date DataPubblicazione ,String MotivoCessione) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, categoria, DataPubblicazione); 
		this.MotivoCessione = MotivoCessione;
	}
	
	public String getMotivoCessione() {
		return MotivoCessione;
	}
	
	public void setMotivoCessione(String MotivoCessione) {
		this.MotivoCessione = MotivoCessione; 
	}
}
