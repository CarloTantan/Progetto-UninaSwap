package entity;

import java.util.Date;

public class Recensione_entity {
	private int IdRecensione;
	private String Commento; 
	private int Punteggio;
	private Date Data;
	private String MatricolaAcquirente;
	private String MatricolaVenditore;
	private int IdOfferta;
	
	public Recensione_entity(int IdRecensione, String Commento, int Punteggio, Date Data, String MatricolaAcquirente, String MatricolaVenditore, int IdOfferta) {
		this.IdRecensione = IdRecensione;
		this.Commento = Commento;
		this.Punteggio = Punteggio;
		this.Data = Data;
		this.MatricolaAcquirente= MatricolaAcquirente;
		this.MatricolaVenditore = MatricolaVenditore;
		this.IdOfferta = IdOfferta;
	}
	
//Getter
	public int getIdRecensione() {
		return IdRecensione;
	}
	
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
	
	public Integer getIdOfferta() {
        return IdOfferta;
    }
	
//Setter
	public void setIdRecensione(int IdRecensione) {
		this.IdRecensione = IdRecensione;
	}
	
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
	
	public void setIdOfferta(Integer IdOfferta) {
        this.IdOfferta = IdOfferta;
    }
}

