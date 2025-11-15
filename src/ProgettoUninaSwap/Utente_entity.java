package ProgettoUninaSwap;

public class Utente_entity {
	private String Nominativo;
	private String Email; 
	private String Matricola;
	private String NumeroTelefono;
	private String Password;
	private int NumeroAnnunciPubblicati;
	private int NumeroOggettiVenduti;
	private int NumeroOfferteRicevute;
	private int NumeroOfferteProposte;
	private int NumeroOggettiAcquistati;
	
	public Utente_entity(String Nominativo, String Email, String Matricola, String NumeroTelefono, String Password, int NumeroAnnunciPubblicati,
			int NumeroOggettiVenduti, int NumeroOfferteRicevute, int NumeroOfferteProposte, int NumeroOggettiAcquistati) {
		this.Nominativo = Nominativo; 
		this.Email = Email; 
		this.Matricola = Matricola; 
		this.NumeroTelefono = NumeroTelefono;
		this.Password= Password;
		this.NumeroAnnunciPubblicati = NumeroAnnunciPubblicati;
		this.NumeroOggettiVenduti = NumeroOggettiVenduti;
		this.NumeroOfferteRicevute= NumeroOfferteRicevute;
		this.NumeroOfferteProposte = NumeroOfferteProposte;
		this.NumeroOggettiAcquistati = NumeroOggettiAcquistati;
	}
	
//Getter
	public String getNominativo() {
		return Nominativo;
	}
	
	public String getEmail() {
		return Email; 
	}
	
	public String getMatricola() {
		return Matricola; 
	}
	
	public String getNumeroTelefono() {
		return NumeroTelefono; 
	}
	
	public String getPassword() {
		return Password;
	}
	
	public int getNumeroAnnunciPubblicati() {
		return NumeroAnnunciPubblicati;
	}
	
	public int getNumeroOggettiVenduti() {
		return NumeroOggettiVenduti;
	}
	
	public int getNumeroOfferteRicevute() {
		return NumeroOfferteRicevute;
	}
	
	public int getNumeroOfferteProposte() {
		return NumeroOfferteProposte;
	}
	
	public int getNumeroOggettiAcquistati() {
		return NumeroOggettiAcquistati;
	}

//Setter
	public void setNominativo(String Nominativo) {
		this.Nominativo = Nominativo; 
	}
	
	public void setEmail(String Email) {
		this.Email = Email; 
	}
	
	public void setMatricola(String Matricola) {
		this.Matricola = Matricola; 
	}
	
	public void setNumeroTelefono(String NumeroTelefono) {
		this.NumeroTelefono = NumeroTelefono; 
	}
	
	public void setPassword(String Password) {
		this.Password= Password;
	}
	
	public void setNumeroAnnunciPubblicati(int NumeroAnnunciPubblicati) {
		this.NumeroAnnunciPubblicati = NumeroAnnunciPubblicati;
	}
	
	public void setNumeroOggettiVenduti(int NumeroOggettiVenduti) {
		this.NumeroOggettiVenduti = NumeroOggettiVenduti;
	}
	
	public void setNumeroOfferteRicevute(int NumeroOfferteRicevute) {
		this.NumeroOfferteRicevute = NumeroOfferteRicevute;
	}
	
	public void setNumeroOfferteProposte(int NumeroOfferteProposte) {
		this.NumeroOfferteProposte = NumeroOfferteProposte;
	}
	
	public void setNumeroOggettiAcquistati(int NumeroOggettiAcquistati) {
		this.NumeroOggettiAcquistati = NumeroOggettiAcquistati;
	}
	
}
