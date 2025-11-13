package ProgettoUninaSwap;

import java.util.Date;

public class AnnuncioRegalo_entity extends Annuncio_entity{
	private String MotivoCessione; 
	
	public AnnuncioRegalo_entity(String Titolo, String Descrizione, Enum FasciaOraria, String ModalitàConsegna, 
			String StatoAnnuncio, String Oggetto, String CategoriaOggetto, Date DataPubblicazione ,String MotivoCessione) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, Oggetto, CategoriaOggetto, DataPubblicazione); 
		this.MotivoCessione = MotivoCessione;
	}
	
	public String getMotivoCessione() {
		return MotivoCessione;
	}
	
	public void setMotivoCessione(String MotivoCessione) {
		this.MotivoCessione = MotivoCessione; 
	}
}
