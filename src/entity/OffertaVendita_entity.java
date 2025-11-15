package entity;

public class OffertaVendita_entity extends Offerta_entity{
	private float PropostaEconomica;
	
	public OffertaVendita_entity(String StatoOfferta, float PropostaEconomica) {
		super(StatoOfferta);
		this.PropostaEconomica = PropostaEconomica;
	}
	
	public float getPropostaEconomica() {
		return PropostaEconomica;
	}
	
	public void setPropostaEconomica(float PropostaEconomica) {
		this.PropostaEconomica = PropostaEconomica; 
	}
}
