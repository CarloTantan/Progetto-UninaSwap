package entity;

public class Offerta_entity {
	protected String StatoOfferta; 
	
	public Offerta_entity(String StatoOfferta) {
		this.StatoOfferta = StatoOfferta; 
	}
	
//Getter	
	public String getStatoOfferta() {
		return StatoOfferta;
	}
	
//Setter
	public void setStatoOfferta(String StatoOfferta) {
		this.StatoOfferta = StatoOfferta;
	}
}
