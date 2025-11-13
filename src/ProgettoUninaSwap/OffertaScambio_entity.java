package ProgettoUninaSwap;

public class OffertaScambio_entity extends Offerta_entity{
	private String OggettoProposto; 
	
	public OffertaScambio_entity(String StatoOfferta, String OggettoProposto) {
		super(StatoOfferta);
		this.OggettoProposto = OggettoProposto; 
	}
	
	public String getOggettoRichiesto() {
		return OggettoProposto;
	}

	public void setOggettoProposto(String OggettoProposto) {
		this.OggettoProposto = OggettoProposto; 
	}
}
