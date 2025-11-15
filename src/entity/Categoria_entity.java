package entity;

import enumerations.TipologiaCategoria;

public class Categoria_entity {
	protected int IdCategoria;
	protected TipologiaCategoria Tipologia;
	
	public Categoria_entity(int IdCategoria, TipologiaCategoria Tipologia) {
		this.IdCategoria = IdCategoria;
		this.Tipologia = Tipologia;
	}
	
	//Getter
	public int getIdCategoria() {
		return IdCategoria;
	}
	
	public TipologiaCategoria getTipologia() {
		return Tipologia;
	}
	
	//Setter
	public void setIdCategoria(int IdCategoria) {
		this.IdCategoria = IdCategoria;
	}
	
	public void setTipologia(TipologiaCategoria Tipologia) {
		this.Tipologia = Tipologia;
	}

}
