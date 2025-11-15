package entity;

public class OffertaRegalo_entity extends Offerta_entity{
	private String MessaggioMotivazionale;
	
	public OffertaRegalo_entity(String StatoOfferta, String MessaggioMotivazionale) {
		super(StatoOfferta); 
		this.MessaggioMotivazionale = MessaggioMotivazionale; 
	}
	
	public String getMessaggioMotivazionale() {
		return MessaggioMotivazionale; 
	}
	
	public void setMessaggioMotivazionale(String MessaggioMotivazionale) {
		this.MessaggioMotivazionale = MessaggioMotivazionale;
	}
}
