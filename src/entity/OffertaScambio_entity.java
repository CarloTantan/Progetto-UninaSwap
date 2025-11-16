package entity;

public class OffertaScambio_entity extends Offerta_entity{
	private String OggettoProposto; 
	
	public OffertaScambio_entity(int IdOfferta, String StatoOfferta, String MatricolaAcquirente, int IdAnnuncio, String OggettoProposto) {
		super(IdOfferta, StatoOfferta, MatricolaAcquirente, IdAnnuncio);
		this.OggettoProposto = OggettoProposto; 
	}
	
	public String getOggettoRichiesto() {
		return OggettoProposto;
	}

	public void setOggettoProposto(String OggettoProposto) {
		this.OggettoProposto = OggettoProposto; 
	}
}
