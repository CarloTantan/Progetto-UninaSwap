package entity;

import enumerations.*; 

public class OffertaVendita_entity extends Offerta_entity{
	private float ImportoProposto;
	
	
	public OffertaVendita_entity(int IdOfferta, StatoOfferta StatoOfferta, String MatricolaAcquirente, int IdAnnuncio, float ImportoProposto,String TipologiaOfferta) {
		super(IdOfferta, StatoOfferta, MatricolaAcquirente, IdAnnuncio, TipologiaOfferta);
		this.ImportoProposto = ImportoProposto;
	}
	
	public float getImportoProposto() {
		return ImportoProposto;
	}
	
	public void setImportoProposto(float ImportoProposto) {
		this.ImportoProposto = ImportoProposto; 
	}
}
