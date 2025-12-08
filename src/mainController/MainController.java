package mainController;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import dao.*;
import entity.*;
import enumerations.*; 

public class MainController {
	protected LoginDAO LoginDAO; 
	protected RegistrazioneDAO RegistrazioneDAO;
	protected Utente_entity User; 
	protected InserimentoAnnunciDAO InsertAnnunciDAO;  
	    protected FotoAnnuncioDAO FotoAnnuncioDAO; 
	    protected OffertaDAO OffertaDAO;
	    protected Oggetto_entity OggettoAnnuncio;
	    protected String titoloAnnuncio;
	    protected String descrizioneAnnuncio;
	    protected String modalitaConsegnaAnnuncio;
	    protected FasciaOraria fasciaOrariaAnnuncio;
	    protected ArrayList<String> percorsiImmaginiAnnuncio;

	public MainController() {
		this.LoginDAO = new LoginDAO();           
	    this.RegistrazioneDAO = new RegistrazioneDAO();
        this.InsertAnnunciDAO =  new InserimentoAnnunciDAO();
        this.FotoAnnuncioDAO = new FotoAnnuncioDAO(); 
        this.OffertaDAO = new OffertaDAO();
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

//Metodi per user loggato
	public String getMatricolaUtenteLoggato() {
	    if (User != null) {
	        return User.getMatricola();
	    }
	    return null;
	}
	
	public String getNominativoUtenteLoggato() {
	    if (User != null) {
	        return User.getNominativo();
	    }
	    return null;
	}
	
	public String getEmailUtenteLoggato() {
	    if (User != null) {
	        return User.getEmail();
	    }
	    return null;
	}
	
	public String getNumeroTelefonoUtenteLoggato() {
	    if (User != null) {
	        return User.getNumeroTelefono();
	    }
	    return null;
	}
	
	public int getNumeroOfferteProposteUtenteLoggato() {
	    if (User != null) {
	        return User.getNumeroOfferteProposte();
	    }
	    return 0;
	}
	
	public int getNumeroOggettiAcquistatiUtenteLoggato() {
	    if (User != null) {
	        return User.getNumeroOggettiAcquistati();
	    }
	    return 0;
	}
	
	public int getNumeroAnnunciPubblicatiUtenteLoggato() {
	    if (User != null) {
	        return User.getNumeroAnnunciPubblicati();
	    }
	    return 0;
	}
	
	public int getNumeroOggettiVendutiUtenteLoggato() {
	    if (User != null) {
	        return User.getNumeroOggettiVenduti();
	    }
	    return 0;
	}
	
	public int getNumeroOfferteRicevuteUtenteLoggato() {
	    if (User != null) {
	        return User.getNumeroOfferteRicevute();
	    }
	    return 0;
	}
	
	
	
	
	// Metodo per impostare i dati dell'annuncio (da chiamare prima di aprire AnnuncioScambio)
	public void impostaInformazioniAnnuncio(Oggetto_entity oggetto, String titolo, 
	                                        String descrizione, String modalitaConsegna,
	                                        FasciaOraria fasciaOraria, 
	                                        ArrayList<String> percorsiImmagini) {
	    this.OggettoAnnuncio = oggetto;
	    this.titoloAnnuncio = titolo;
	    this.descrizioneAnnuncio = descrizione;
	    this.modalitaConsegnaAnnuncio = modalitaConsegna;
	    this.fasciaOrariaAnnuncio = fasciaOraria;
	    this.percorsiImmaginiAnnuncio = percorsiImmagini;
	}

	// Metodo per pubblicare l'annuncio di scambio
	public String PubblicaAnnuncioScambio(String oggettoRichiesto) {
	    return InserimentoAnnuncioScambio(
	        titoloAnnuncio,
	        descrizioneAnnuncio,
	        modalitaConsegnaAnnuncio,
	        fasciaOrariaAnnuncio,
	        oggettoRichiesto,
	        getMatricolaUtenteLoggato(),
	        OggettoAnnuncio.getIdOggetto(),
	        percorsiImmaginiAnnuncio
	    );
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
	// Nel MainController, aggiungi questo getter:
	public Oggetto_entity getOggettoAnnuncio() {
	    return OggettoAnnuncio;
	}
	
	
//Inserimento Annuncio Scambio
				public String InserimentoAnnuncioScambio(
					    String titolo,
					    String descrizione,
					    String modalitaConsegna,
					    FasciaOraria fasciaOraria,
					    String oggettoRichiesto,
					    String matricolaVenditore,
					    int idOggetto,
					    List<String> percorsiImmagini) {

					    // Validazione campi obbligatori
					    if (titolo == null || titolo.trim().isEmpty() ||
					        descrizione == null || descrizione.trim().isEmpty() ||
					        modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
					        fasciaOraria == null ||
					        oggettoRichiesto == null || oggettoRichiesto.trim().isEmpty() ||
					        matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

					        return "Tutti i campi sono obbligatori";
					    }

					    // Validazione matricola
					    if (!matricolaVenditore.matches("[0-9]{10}")) {
					        return "La matricola deve contenere 10 cifre numeriche";
					    }

					    // Validazione immagini
					    if (percorsiImmagini == null || percorsiImmagini.isEmpty()) {
					        return "Devi inserire almeno una foto";
					    }

					    try {
					        // Inserisci l'annuncio
					        InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					        int idAnnuncio = annuncioDAO.inserisciAnnuncioScambio(
					            titolo,
					            descrizione,
					            modalitaConsegna,
					            fasciaOraria,
					            oggettoRichiesto,
					            matricolaVenditore,
					            idOggetto
					        );

					        // Controlla se l'inserimento è riuscito
					        if (idAnnuncio <= 0) {
					            return "Impossibile pubblicare l'annuncio";
					        }

					        // Inserisci le foto
					        FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
					        for (String percorsoImg : percorsiImmagini) {
					            int idFoto = fotoDAO.inserisciFoto(percorsoImg, idAnnuncio);
					            if (idFoto <= 0) {
					                return "Errore durante l'inserimento delle foto";
					            }
					        }

					        return "Annuncio pubblicato con successo";

					    } catch (SQLException e) {
					    	return "Errore durante la pubblicazione: " + e.getMessage();

					    }
					}
				
//InserimentoAnnuncioVendita				
				public String InserimentoAnnuncioVendita(
					    String titolo,
					    String descrizione,
					    String modalitaConsegna,
					    FasciaOraria fasciaOraria,
					    float prezzo,
					    String matricolaVenditore,
					    int idOggetto,
					    List<String> percorsiImmagini) {

					    // Validazione campi obbligatori
					    if (titolo == null || titolo.trim().isEmpty() ||
					        descrizione == null || descrizione.trim().isEmpty() ||
					        modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
					        fasciaOraria == null ||
					        prezzo <= 0 ||  // CORRETTO: float si controlla così
					        matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

					        return "Tutti i campi sono obbligatori e il prezzo deve essere maggiore di zero";
					    }

					    // Validazione matricola
					    if (!matricolaVenditore.matches("[0-9]{10}")) {
					        return "La matricola deve contenere 10 cifre numeriche";
					    }

					    // Validazione immagini
					    if (percorsiImmagini == null || percorsiImmagini.isEmpty()) {
					        return "Devi inserire almeno una foto";
					    }

					    try {
					        // Inserisci l'annuncio
					        InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					        int idAnnuncio = annuncioDAO.inserisciAnnuncioVendita(
					            titolo,
					            descrizione,
					            modalitaConsegna,
					            fasciaOraria,
					            prezzo,
					            matricolaVenditore,
					            idOggetto
					        );

					        // Controlla se l'inserimento è riuscito
					        if (idAnnuncio <= 0) {
					            return "Impossibile pubblicare l'annuncio";
					        }

					        // Inserisci le foto
					        FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
					        for (String percorsoImg : percorsiImmagini) {
					            int idFoto = fotoDAO.inserisciFoto(percorsoImg, idAnnuncio);
					            if (idFoto <= 0) {
					                return "Errore durante l'inserimento delle foto";
					            }
					        }

					        return "Annuncio pubblicato con successo";

					    } catch (SQLException e) {
					        return "Errore durante la pubblicazione: " + e.getMessage();
					    }
					}


//InserimentoAnnuncioRegalo
				
				public String InserimentoAnnuncioRegalo(
					    String titolo,
					    String descrizione,
					    String modalitaConsegna,
					    FasciaOraria fasciaOraria,
					    String motivoCessione,
					    String matricolaVenditore,
					    int idOggetto,
					    List<String> percorsiImmagini) {

					    // Validazione campi obbligatori
					    if (titolo == null || titolo.trim().isEmpty() ||
					        descrizione == null || descrizione.trim().isEmpty() ||
					        modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
					        fasciaOraria == null ||
					        		motivoCessione == null || motivoCessione.trim().isEmpty() ||  
					        matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

					        return "Tutti i campi sono obbligatori e il prezzo deve essere maggiore di zero";
					    }

					    // Validazione matricola
					    if (!matricolaVenditore.matches("[0-9]{10}")) {
					        return "La matricola deve contenere 10 cifre numeriche";
					    }

					    // Validazione immagini
					    if (percorsiImmagini == null || percorsiImmagini.isEmpty()) {
					        return "Devi inserire almeno una foto";
					    }

					    try {
					        // Inserisci l'annuncio
					        InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					        int idAnnuncio = annuncioDAO.inserisciAnnuncioRegalo(
					            titolo,
					            descrizione,
					            modalitaConsegna,
					            fasciaOraria,
					            motivoCessione,
					            matricolaVenditore,
					            idOggetto
					        );

					        // Controlla se l'inserimento è riuscito
					        if (idAnnuncio <= 0) {
					            return "Impossibile pubblicare l'annuncio";
					        }

					        // Inserisci le foto
					        FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
					        for (String percorsoImg : percorsiImmagini) {
					            int idFoto = fotoDAO.inserisciFoto(percorsoImg, idAnnuncio);
					            if (idFoto <= 0) {
					                return "Errore durante l'inserimento delle foto";
					            }
					        }

					        return "Annuncio pubblicato con successo";

					    } catch (SQLException e) {
					        return "Errore durante la pubblicazione: " + e.getMessage();
					    }
					}


//InvioOffertaRegalo
				public String InviaOffertaRegalo(
				    String messaggioMotivazionale,
				    String matricolaAcquirente,
				    int idAnnuncio) {
				    
				    // Validazione campi obbligatori
				    if (messaggioMotivazionale == null || messaggioMotivazionale.trim().isEmpty()) {
				        return "Il messaggio motivazionale è obbligatorio";
				    }
				    
				    if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
				        return "La matricola dell'acquirente è obbligatoria";
				    }
				    
				    // Validazione matricola
				    if (!matricolaAcquirente.matches("[0-9]{10}")) {
				        return "La matricola deve contenere 10 cifre numeriche";
				    }
				    
				    // Validazione ID annuncio
				    if (idAnnuncio <= 0) {
				        return "ID annuncio non valido";
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        boolean offertaInviata = offertaDAO.inserimentoOffertaRegalo(
				            messaggioMotivazionale,
				            matricolaAcquirente,
				            idAnnuncio
				        );
				        
				        if (!offertaInviata) {
				            return "Impossibile inviare l'offerta";
				        }
				        
				        return "Offerta inviata con successo";
				        
				    } catch (SQLException e) {
				        return "Errore durante l'invio dell'offerta: " + e.getMessage();
				    }
				}

//Update offerta regalo
				public String AggiornaOffertaRegalo(
				    String messaggioMotivazionale,
				    String matricolaAcquirente,
				    int idAnnuncio,
				    int idOfferta) {
				    
				    // Validazione campi obbligatori
				    if (messaggioMotivazionale == null || messaggioMotivazionale.trim().isEmpty()) {
				        return "Il messaggio motivazionale è obbligatorio";
				    }
				    
				    if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
				        return "La matricola dell'acquirente è obbligatoria";
				    }
				    
				    // Validazione matricola
				    if (!matricolaAcquirente.matches("[0-9]{10}")) {
				        return "La matricola deve contenere 10 cifre numeriche";
				    }
				    
				    // Validazione ID
				    if (idAnnuncio <= 0 || idOfferta <= 0) {
				        return "ID annuncio o offerta non validi";
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        boolean offertaAggiornata = offertaDAO.aggiornaOffertaRegalo(
				            messaggioMotivazionale,
				            matricolaAcquirente,
				            idAnnuncio,
				            idOfferta
				        );
				        
				        if (!offertaAggiornata) {
				            return "Impossibile aggiornare l'offerta";
				        }
				        
				        return "Offerta aggiornata con successo";
				        
				    } catch (SQLException e) {
				        return "Errore durante l'aggiornamento dell'offerta: " + e.getMessage();
				    }
				}

//carica offerta esistenza
				public OffertaRegalo_entity CaricaOfferta(int idOfferta) {
				    // Validazione ID
				    if (idOfferta <= 0) {
				        return null;
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        return offertaDAO.caricaOfferta(idOfferta);
				        
				    } catch (SQLException e) {
				        System.err.println("Errore nel caricamento dell'offerta: " + e.getMessage());
				        e.printStackTrace();
				        return null;
				    }
				}

//invia offerta scambio
				public String InviaOffertaScambio( 
				    String oggettoProposto,
				    String matricolaAcquirente,
				    int idAnnuncio) {
				    
				    // Validazione campi obbligatori
				    if (oggettoProposto == null || oggettoProposto.trim().isEmpty()) {
				        return "L'oggetto proposto è obbligatorio";
				    }
				    
				    if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
				        return "La matricola dell'acquirente è obbligatoria";
				    }
				    
				    // Validazione matricola
				    if (!matricolaAcquirente.matches("[0-9]{10}")) {
				        return "La matricola deve contenere 10 cifre numeriche";
				    }
				    
				    // Validazione ID annuncio
				    if (idAnnuncio <= 0) {
				        return "ID annuncio non valido";
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        boolean offertaInviata = offertaDAO.inserimentoOffertaScambio(
				            oggettoProposto,
				            matricolaAcquirente,
				            idAnnuncio
				        );
				        
				        if (!offertaInviata) {
				            return "Impossibile inviare l'offerta";
				        }
				        
				        return "Offerta inviata con successo";
				        
				    } catch (SQLException e) {
				        return "Errore durante l'invio dell'offerta: " + e.getMessage();
				    }
				}

//update offerta scambio 
				public String AggiornaOffertaScambio(
				    String oggettoProposto,
				    String matricolaAcquirente,
				    int idAnnuncio,
				    int idOfferta) {
				    
				    // Validazione campi obbligatori
				    if (oggettoProposto == null || oggettoProposto.trim().isEmpty()) {
				        return "L'oggetto proposto è obbligatorio";
				    }
				    
				    if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
				        return "La matricola dell'acquirente è obbligatoria";
				    }
				    
				    // Validazione matricola
				    if (!matricolaAcquirente.matches("[0-9]{10}")) {
				        return "La matricola deve contenere 10 cifre numeriche";
				    }
				    
				    // Validazione ID
				    if (idAnnuncio <= 0 || idOfferta <= 0) {
				        return "ID annuncio o offerta non validi";
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        boolean offertaAggiornata = offertaDAO.aggiornaOffertaScambio(
				            oggettoProposto,
				            matricolaAcquirente,
				            idAnnuncio,
				            idOfferta
				        );
				        
				        if (!offertaAggiornata) {
				            return "Impossibile aggiornare l'offerta";
				        }
				        
				        return "Offerta aggiornata con successo";
				        
				    } catch (SQLException e) {
				        return "Errore durante l'aggiornamento dell'offerta: " + e.getMessage();
				    }
				}

//carica offerta scambio esistente 
				public OffertaScambio_entity CaricaOffertaScambio(int idOfferta) {
				    // Validazione ID
				    if (idOfferta <= 0) {
				        return null;
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        return offertaDAO.caricaOffertaScambio(idOfferta);
				        
				    } catch (SQLException e) {
				        System.err.println("Errore nel caricamento dell'offerta: " + e.getMessage());
				        e.printStackTrace();
				        return null;
				    }
				}
				
//invia offerta vendita
				public String InviaOffertaVendita(
				    float importoProposto,
				    String matricolaAcquirente,
				    int idAnnuncio) {
				    
				    // Validazione importo
				    if (importoProposto <= 0) {
				        return "L'importo proposto deve essere maggiore di zero";
				    }
				    
				    // Validazione matricola
				    if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
				        return "La matricola dell'acquirente è obbligatoria";
				    }
				    
				    if (!matricolaAcquirente.matches("[0-9]{10}")) {
				        return "La matricola deve contenere 10 cifre numeriche";
				    }
				    
				    // Validazione ID annuncio
				    if (idAnnuncio <= 0) {
				        return "ID annuncio non valido";
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        boolean offertaInviata = offertaDAO.inserimentoOffertaVendita(
				            importoProposto,
				            matricolaAcquirente,
				            idAnnuncio
				        );
				        
				        if (!offertaInviata) {
				            return "Impossibile inviare l'offerta";
				        }
				        
				        return "Offerta inviata con successo";
				        
				    } catch (SQLException e) {
				        return "Errore durante l'invio dell'offerta: " + e.getMessage();
				    }
				}

//update offerta vendita 
				public String AggiornaOffertaVendita(
				    float importoProposto,
				    String matricolaAcquirente,
				    int idAnnuncio,
				    int idOfferta) {
				    
				    // Validazione importo
				    if (importoProposto <= 0) {
				        return "L'importo proposto deve essere maggiore di zero";
				    }
				    
				    // Validazione matricola
				    if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
				        return "La matricola dell'acquirente è obbligatoria";
				    }
				    
				    if (!matricolaAcquirente.matches("[0-9]{10}")) {
				        return "La matricola deve contenere 10 cifre numeriche";
				    }
				    
				    // Validazione ID
				    if (idAnnuncio <= 0 || idOfferta <= 0) {
				        return "ID annuncio o offerta non validi";
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        boolean offertaAggiornata = offertaDAO.aggiornaOffertaVendita(
				            importoProposto,
				            matricolaAcquirente,
				            idAnnuncio,
				            idOfferta
				        );
				        
				        if (!offertaAggiornata) {
				            return "Impossibile aggiornare l'offerta";
				        }
				        
				        return "Offerta aggiornata con successo";
				        
				    } catch (SQLException e) {
				        return "Errore durante l'aggiornamento dell'offerta: " + e.getMessage();
				    }
				}

//carica offerta vendita modificato
				public OffertaVendita_entity CaricaOffertaVendita(int idOfferta) {
				    // Validazione ID
				    if (idOfferta <= 0) {
				        return null;
				    }
				    
				    try {
				        OffertaDAO offertaDAO = new OffertaDAO();
				        return offertaDAO.caricaOffertaVendita(idOfferta);
				        
				    } catch (SQLException e) {
				        System.err.println("Errore nel caricamento dell'offerta: " + e.getMessage());
				        e.printStackTrace();
				        return null;
				    }
				}
				public String PubblicaAnnuncioVendita(String prezzoStr) {
				    // Validazione del formato del prezzo
				    if (prezzoStr == null || prezzoStr.trim().isEmpty()) {
				        return "Il campo prezzo è obbligatorio";
				    }
				    
				    float prezzo;
				    try {
				        prezzo = Float.parseFloat(prezzoStr);
				    } catch (NumberFormatException e) {
				        return "Inserisci un prezzo valido";
				    }
				    
				    // Chiama il metodo esistente
				    return InserimentoAnnuncioVendita(
				        titoloAnnuncio,
				        descrizioneAnnuncio,
				        modalitaConsegnaAnnuncio,
				        fasciaOrariaAnnuncio,
				        prezzo,
				        getMatricolaUtenteLoggato(),
				        OggettoAnnuncio.getIdOggetto(),
				        percorsiImmaginiAnnuncio
				    );
				}

				// Metodo per pubblicare l'annuncio di regalo
				public String PubblicaAnnuncioRegalo(String motivoCessione) {
				    return InserimentoAnnuncioRegalo(
				        titoloAnnuncio,
				        descrizioneAnnuncio,
				        modalitaConsegnaAnnuncio,
				        fasciaOrariaAnnuncio,
				        motivoCessione,
				        getMatricolaUtenteLoggato(),
				        OggettoAnnuncio.getIdOggetto(),
				        percorsiImmaginiAnnuncio
				    );
				}

























}
