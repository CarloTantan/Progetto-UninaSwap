package ProgettoUninaSwap;

public class Oggetto_entity {
	protected int IdOggetto;
	protected String Nome;
	protected String Descrizione;
	protected int IdCategoria;
	
	public Oggetto_entity(int IdOggetto, String Nome, String Descrizione, int IdCategoria) {
		this.IdOggetto= IdOggetto;
		this.Nome = Nome;
		this.Descrizione = Descrizione;
		this.IdCategoria = IdCategoria;
	}
	
	//Getter
	public int getidOggetto() {
		return IdOggetto;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public String getDescrizione() {
		return Descrizione;
	}
	
	public int getidCategoria() {
		return IdCategoria;
	}
	
	//Setter
	public void setIdOggetto(int IdOggetto) {
		this.IdOggetto = IdOggetto;
	}
	
	public void setNome(String Nome) {
		this.Nome = Nome;
	}
	
	public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione;
	}
	
	public void setIdCategoria(int IdCategoria) {
		this.IdCategoria = IdCategoria;
	}

}
