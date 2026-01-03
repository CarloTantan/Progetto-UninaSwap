package entity;

import enumerations.*; 

public class OffertaScambio_entity extends Offerta_entity{
	private String OggettoProposto; 
	
	public OffertaScambio_entity(int IdOfferta, StatoOfferta StatoOfferta, String MatricolaAcquirente, int IdAnnuncio, String OggettoProposto,String TipologiaOfferta) {
		super(IdOfferta, StatoOfferta, MatricolaAcquirente, IdAnnuncio, TipologiaOfferta);
		this.OggettoProposto = OggettoProposto; 
	}
	
	public String getOggettoProposto() {
		return OggettoProposto;
	}

	public void setOggettoProposto(String OggettoProposto) {
		this.OggettoProposto = OggettoProposto; 
	}
}
