package entity;

public class OffertaRegalo_entity extends Offerta_entity{
	private String MessaggioMotivazionale;
	
	public OffertaRegalo_entity(int IdOfferta, String StatoOfferta, String MatricolaAcquirente, int IdAnnuncio, String MessaggioMotivazionale) {
		super(IdOfferta, StatoOfferta, MatricolaAcquirente, IdAnnuncio); 
		this.MessaggioMotivazionale = MessaggioMotivazionale; 
	}
	
	public String getMessaggioMotivazionale() {
		return MessaggioMotivazionale; 
	}
	
	public void setMessaggioMotivazionale(String MessaggioMotivazionale) {
		this.MessaggioMotivazionale = MessaggioMotivazionale;
	}
}
