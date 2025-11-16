package entity;

import java.util.Date;
import enumerations.FasciaOraria;
import enumerations.TipologiaCategoria;
import enumerations.StatoAnnuncio;

public class AnnuncioVendita_entity extends Annuncio_entity {
	private float PrezzoVendita; 

	public AnnuncioVendita_entity(int IdAnnuncio, String Titolo, String Descrizione, FasciaOraria FasciaOraria, String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, TipologiaCategoria categoria, Date DataPubblicazione , String MatricolaVenditore, float PrezzoVendita) {
		super(IdAnnuncio, Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, categoria, DataPubblicazione, MatricolaVenditore);
		this.PrezzoVendita = PrezzoVendita; 
	}

	public float getPrezzoVendita() {
		return PrezzoVendita; 
	}
	
	public void setPrezzoVendita(float PrezzoVendita) {
		this.PrezzoVendita = PrezzoVendita; 
	}
}