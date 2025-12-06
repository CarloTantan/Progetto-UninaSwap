package mainController;

import java.sql.SQLException;

import dao.*;
import entity.*;

public class MainController {
	protected LoginDAO LoginDAO; 
	protected RegistrazioneDAO RegistrazioneDAO;
	protected Utente_entity User; 
	
	public MainController() {
		this.LoginDAO = new LoginDAO();           
	    this.RegistrazioneDAO = new RegistrazioneDAO();
	}
	
	//Effettua Login
	public String EffettuaLogin(String matricola, String password) {
		if (matricola == null || matricola.trim().isEmpty()) {
            return "Il campo matricola non può esser vuoto, inserisci la matricola";
        }
        
        if (password == null || password.trim().isEmpty()) {
            return "Il campo password non può esser vuoto, inserisci la password";
        }
        
		try {
	        boolean valido = LoginDAO.verificaLogin(matricola, password);
	        
	        if (!valido) {
	            return "Credenziali non valide";
	        }
	        
	        Utente_entity Utente = LoginDAO.getUtente(matricola);
	        this.User = Utente;
	        
	        return "Login effettuato con successo";
	        
	    } catch (SQLException e) {
	        return "Impossibile connettersi al database: " + e.getMessage();
	    }
	}
	
	public Utente_entity getUser() {
        return User;
    } 
	
	//Effettua Registrazione 
	public String EffettuaRegistrazione(String nome, String cognome, String matricola, String telefono, String email, String password, String confermaPassword) {
	    
	    if (nome == null || nome.trim().isEmpty() ||
	        cognome == null || cognome.trim().isEmpty() ||
	        matricola == null || matricola.trim().isEmpty() ||
	        telefono == null || telefono.trim().isEmpty() ||
	        email == null || email.trim().isEmpty() ||
	        password == null || password.trim().isEmpty() ||
	        confermaPassword == null || confermaPassword.trim().isEmpty()) {
	        
	        return "Tutti i campi sono obbligatori";
	    }
	   
	    if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
	        return "Formato email non valido";
	    }
	    
	    if (!matricola.matches("[0-9]{10}")) {
	        return "La matricola deve contenere 10 cifre numeriche";
	    }
	    
	    if (!telefono.matches("[0-9]{10}")) {
	        return "Il numero di telefono deve contenere 10 cifre";
	    }
	    
	    if (!password.equals(confermaPassword)) {
	        return "Le password non corrispondono";
	    }
	    
	    boolean registrazioneRiuscita = RegistrazioneDAO.effettuaRegistrazione(
	        nome, cognome, matricola, telefono, email, password
	    );
	    
	    if (!registrazioneRiuscita) {
	        return "Impossibile effettuare la registrazione, Utente già registrato";
	    }
	    
	    return "Registrazione effettuata con successo"; 
	}
	
	
	
}
