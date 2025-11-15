package ProgettoUninaSwap;

import java.util.Date;

public class AnnuncioVendita_entity extends Annuncio_entity {
	private float PrezzoVendita; 
	 private String tipologiaCategoria;
	public AnnuncioVendita_entity(String Titolo, String Descrizione, FasciaOraria FasciaOraria,  String ModalitàConsegna, 
			StatoAnnuncio StatoAnnuncio, String idOggetto, Date DataPubblicazione ,float PrezzoVendita,String tipologiaCategoria) {
		super(Titolo, Descrizione, FasciaOraria, ModalitàConsegna, StatoAnnuncio, idOggetto, tipologiaCategoria, DataPubblicazione);
		this.PrezzoVendita = PrezzoVendita; 
	    this.tipologiaCategoria = tipologiaCategoria;
	}

	public float getPrezzoVendita() {
		return PrezzoVendita; 
	}
	
	public void setPrezzoVendita(float PrezzoVendita) {
		this.PrezzoVendita = PrezzoVendita; 
	}
}
