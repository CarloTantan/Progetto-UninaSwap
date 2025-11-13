package ProgettoUninaSwap;

public class Utente {
	private String Nominativo;
	private String Email; 
	private String Matricola;
	private String NumeroTelefono; 
	
	public Utente(String Nominativo, String Email, String Matricola, String NumeroTelefono) {
		this.Nominativo = Nominativo; 
		this.Email = Email; 
		this.Matricola = Matricola; 
		this.NumeroTelefono = NumeroTelefono; 
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
	
}
