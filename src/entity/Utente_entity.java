package entity;

public class Utente_entity {
	private String Nominativo;
	private String Email; 
	private String Matricola;
	private String NumeroTelefono;
	private String Password;
	
	public Utente_entity(String Nominativo, String Email, String Matricola, String NumeroTelefono, String Password) {
		this.Nominativo = Nominativo; 
		this.Email = Email; 
		this.Matricola = Matricola; 
		this.NumeroTelefono = NumeroTelefono;
		this.Password= Password;
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
	
}
