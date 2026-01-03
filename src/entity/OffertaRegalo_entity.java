package entity;

import enumerations.*; 

public class OffertaRegalo_entity extends Offerta_entity{
	private String MessaggioMotivazionale;
	
	public OffertaRegalo_entity(int IdOfferta, StatoOfferta StatoOfferta, String MatricolaAcquirente, int IdAnnuncio, String MessaggioMotivazionale,String TipologiaOfferta) {
		super(IdOfferta, StatoOfferta, MatricolaAcquirente, IdAnnuncio, TipologiaOfferta); 
		this.MessaggioMotivazionale = MessaggioMotivazionale; 
	}
	
	public String getMessaggioMotivazionale() {
		return MessaggioMotivazionale; 
	}
	
	public void setMessaggioMotivazionale(String MessaggioMotivazionale) {
		this.MessaggioMotivazionale = MessaggioMotivazionale;
	}
}
