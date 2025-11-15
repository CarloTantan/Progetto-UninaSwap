package ProgettoUninaSwap;

import java.util.Date;

public class Recensione_entity {
	private int IdRecensione;
	private String Commento; 
	private int Punteggio;
	private Date Data;
	private String MatricolaAcquirente;
	private String MatricolaVenditore;
	
	
	
	public Recensione_entity(String Commento, int Punteggio, Date Data, String MatricolaAcquirente, String MatricolaVenditore) {
		this.Commento = Commento;
		this.Punteggio = Punteggio;
		this.Data = Data;
		this.MatricolaAcquirente = MatricolaAcquirente;
		this.MatricolaVenditore = MatricolaVenditore;
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
	
	public String getMatricolaAcquirente() {
		return MatricolaAcquirente;
	}
	
	public String getMatricolaVenditore() {
		return MatricolaVenditore;
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
	
	public void setMatricolaAcquirente(String MatricolaAcquirente) {
		this.MatricolaAcquirente = MatricolaAcquirente;
	}
	
	public void setMatricolaVenditore(String MatricolaVenditore) {
		this.MatricolaVenditore = MatricolaVenditore;
	}
}

