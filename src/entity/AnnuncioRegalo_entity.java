package entity;
import java.util.Date;
import enumerations.FasciaOraria;
import enumerations.TipologiaCategoria;
import enumerations.StatoAnnuncio;

public class AnnuncioRegalo_entity extends Annuncio_entity{
	private String MotivoCessione; 
	
	public AnnuncioRegalo_entity(int IdAnnuncio, String Titolo, String Descrizione, FasciaOraria FasciaOraria, String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, TipologiaCategoria categoria, Date DataPubblicazione , String MatricolaVenditore, String MotivoCessione,boolean VisualizzaOfferta) {
		super(IdAnnuncio, Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, categoria, DataPubblicazione, MatricolaVenditore,VisualizzaOfferta); 
		this.MotivoCessione = MotivoCessione;
	}
	
	public String getMotivoCessione() {
		return MotivoCessione;
	}
	
	public void setMotivoCessione(String MotivoCessione) {
		this.MotivoCessione = MotivoCessione; 
	}
}