package mainController;

import java.sql.SQLException;

import dao.*;
import entity.*;
import enumerations.*; 

public class MainController {
	protected LoginDAO LoginDAO; 
	protected RegistrazioneDAO RegistrazioneDAO;
	protected Utente_entity User; 
	protected InserimentoAnnunciDAO InsertAnnunciDAO; 
	
	public MainController() {
		this.LoginDAO = new LoginDAO();           
	    this.RegistrazioneDAO = new RegistrazioneDAO();
        this.InsertAnnunciDAO = InsertAnnunciDAO;
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
	
	
	//Inserimento Annuncio Scambio
//	public String InserimentoAnnuncioScambio(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String oggettoRichiesto, String matricolaVenditore, int idOggetto) {
//		if (titolo == null || titolo.trim().isEmpty() ||
//	        descrizione == null || descrizione.trim().isEmpty() ||
//	        modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
//	        fasciaOraria == null ||
//	        oggettoRichiesto == null || oggettoRichiesto.trim().isEmpty() ||
//	        matricolaVenditore == null || matricolaVenditore.trim().isEmpty()
//	        ) {
//		        
//		        return "Tutti i campi sono obbligatori";
//		    }
//		if (!matricolaVenditore.matches("[0-9]{10}")) {
//	        return "La matricola deve contenere 10 cifre numeriche";
//	    }
//		
//		boolean AnnuncioPubblicato = InserimentoAnnunciDAO.inserisciAnnuncioScambio(titolo, descrizione, modalitaConsegna, fasciaOraria, oggettoRichiesto, matricolaVenditore, idOggetto);
//		
//		if(!AnnuncioPubblicato) {
//			return "Impossibile pubblicare l'annuncio"; 
//		}
//		
//		return "Annuncio di Scambio inserito correttamnete";
//		
//	}
}
