package ProgettoUninaSwap;

import java.util.Date;

public class Recensione {
	private String Commento; 
	private int Punteggio;
	private Date Data; 
	
	public Recensione(String Commento, int Punteggio, Date Data) {
		this.Commento = Commento;
		this.Punteggio = Punteggio;
		this.Data = Data; 
	}
	
//Getter
	public String getCommento() {
		return Commento; 
	}
	
	public int getPunteggio() {
		return Punteggio;
	}
	
	public Date getData() {
		return Data; 
	}
	
//Setter
	public void setCommento(String Commento) {
		this.Commento = Commento; 
	}
	
	public void setPunteggio(int Punteggio) {
		this.Punteggio = Punteggio;
	}
	
	public void setData(Date Data) {
		this.Data = Data; 
	}
}

