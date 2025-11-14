package ProgettoUninaSwap;
//gestione fasciaoraira
import java.util.Date;

public class AnnuncioRegalo_entity extends Annuncio_entity{
	private String MotivoCessione; 
	
	public AnnuncioRegalo_entity(String Titolo, String Descrizione, String ModalitàConsegna, 
			String StatoAnnuncio, String Oggetto, String CategoriaOggetto, Date DataPubblicazione ,String MotivoCessione) {
		super(Titolo, Descrizione, ModalitàConsegna, StatoAnnuncio, Oggetto, CategoriaOggetto, DataPubblicazione); 
		this.MotivoCessione = MotivoCessione;
	}
	
	public String getMotivoCessione() {
		return MotivoCessione;
	}
	
	public void setMotivoCessione(String MotivoCessione) {
		this.MotivoCessione = MotivoCessione; 
	}
}
