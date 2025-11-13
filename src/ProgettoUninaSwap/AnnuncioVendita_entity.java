package ProgettoUninaSwap;

import java.util.Date;

public class AnnuncioVendita_entity extends Annuncio_entity {
	private float PrezzoProposto; 
	
	public AnnuncioVendita_entity(String Titolo, String Descrizione, Enum FasciaOraria, String ModalitàConsegna, 
			String StatoAnnuncio, String Oggetto, String CategoriaOggetto, Date DataPubblicazione ,float PrezzoProposto) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, Oggetto, CategoriaOggetto, DataPubblicazione);
		this.PrezzoProposto = PrezzoProposto; 
	}

	public float getPrezzoProposto() {
		return PrezzoProposto; 
	}
	
	public void setPrezzoProposto(float PrezzoProposto) {
		this.PrezzoProposto = PrezzoProposto; 
	}
}
