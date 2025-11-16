package entity;

public class OffertaVendita_entity extends Offerta_entity{
	private float PropostaEconomica;
	
	public OffertaVendita_entity(int IdOfferta, String StatoOfferta, String MatricolaAcquirente, int IdAnnuncio, float PropostaEconomica) {
		super(IdOfferta, StatoOfferta, MatricolaAcquirente, IdAnnuncio);
		this.PropostaEconomica = PropostaEconomica;
	}
	
	public float getPropostaEconomica() {
		return PropostaEconomica;
	}
	
	public void setPropostaEconomica(float PropostaEconomica) {
		this.PropostaEconomica = PropostaEconomica; 
	}
}
