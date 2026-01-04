package mainController;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;

import dao.*;
import entity.*;
import enumerations.*; 
import boundary.*;
import  java.text.SimpleDateFormat;

public class MainController {
	
    protected LoginDAO LoginDAO; 
    protected RegistrazioneDAO RegistrazioneDAO;
    protected InserimentoAnnunciDAO InsertAnnunciDAO;  
    protected FotoAnnuncioDAO FotoAnnuncioDAO; 
    protected OffertaDAO OffertaDAO;
    protected InserimentoRecensioneDAO InserimentoRecensioneDAO;
    
    protected Utente_entity User; 
    protected Oggetto_entity OggettoAnnuncio;
    protected Oggetto_entity UltimoOggettoCreato;
    
    protected String titoloAnnuncio;
    protected String descrizioneAnnuncio;
    protected String modalitaConsegnaAnnuncio;
    protected FasciaOraria fasciaOrariaAnnuncio;
    protected ArrayList<String> percorsiImmaginiAnnuncio;
    
    protected ArrayList<AnnuncioVendita_entity> annunciVenditaCaricati;
    protected ArrayList<AnnuncioScambio_entity> annunciScambioCaricati;
    protected ArrayList<AnnuncioRegalo_entity> annunciRegaloCaricati;
    private ArrayList<Integer> idsAnnunciCaricati;
    private ArrayList<Integer> idsOfferteCaricate;
    private ArrayList<Transazione_entity> transazioniCaricate;
    private String matricolaVenditoreCorrente;
    private ArrayList<Recensione_entity> recensioniInviateCache;
    private ArrayList<Recensione_entity> recensioniRicevuteCache;
    private JFrame frameCorrente;
    
    public MainController() {
        this.LoginDAO = new LoginDAO();           
        this.RegistrazioneDAO = new RegistrazioneDAO();
        this.InsertAnnunciDAO = new InserimentoAnnunciDAO();
        this.FotoAnnuncioDAO = new FotoAnnuncioDAO(); 
        this.OffertaDAO = new OffertaDAO();
        this.InserimentoRecensioneDAO = new InserimentoRecensioneDAO();
    }
    
    // =============== METODI DI NAVIGAZIONE ================
    
    // chiude il frame corrente
    private void chiudiFrameCorrente() {
        if (frameCorrente != null) {
            frameCorrente.dispose();
            frameCorrente = null;
        }
    }
    
    // apre la schermata iniziale, cioè l'Homepage
    public void apriHomepage() {
        chiudiFrameCorrente();
        frameCorrente = new Homepage(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di registrazione
    public void apriRegistrazione() {
        chiudiFrameCorrente();
        frameCorrente = new Registrazione(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di login
    public void apriLogin() {
        chiudiFrameCorrente();
        frameCorrente = new Login(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre l'area utente
    public void apriAreaUtente() {
        chiudiFrameCorrente();
        frameCorrente = new AreaUtente(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di inserimento oggetto
    public void apriInserimentoOggetto() {
        chiudiFrameCorrente();
        frameCorrente = new Oggetto(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di crezione annuncio
    public void apriCreazioneAnnuncio() {
        chiudiFrameCorrente();
        frameCorrente = new Annuncio(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di annuncio vendita
    public void apriAnnuncioVendita() {
        chiudiFrameCorrente();
        frameCorrente = new AnnuncioVendita(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la schermata di annuncio scambio
    public void apriAnnuncioScambio() {
        chiudiFrameCorrente();
        frameCorrente = new AnnuncioScambio(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la schermata di annuncio regalo
    public void apriAnnuncioRegalo() {
        chiudiFrameCorrente();
        frameCorrente = new AnnuncioRegalo(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la lista degli annunci
    public void apriListaAnnunci() {
        chiudiFrameCorrente();
        frameCorrente = new ListaAnnunci(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre una finestra secondaria per visualizzare le recensioni di un venditore
    public void apriVisualizzaRecensioniVenditore(String matricolaVenditore) {
        impostaVenditorePerVisualizzazione(matricolaVenditore);
        
        VisualizzaRecensioniVenditore frameRecensioni = new VisualizzaRecensioniVenditore(this);
        frameRecensioni.setVisible(true);
    }
    
    // Apre la schermata degli annunci pubblicati
    public void apriAnnunciPubblicati() {
        chiudiFrameCorrente();
        frameCorrente = new AnnunciPubblicati(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la schermata dello storico offerte
    public void apriStoricoOfferte() {
        chiudiFrameCorrente();
        frameCorrente = new StoricoOfferte(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la lista delle transazioni
    public void apriListaTransazioni() {
        chiudiFrameCorrente();
        frameCorrente = new ListaTransazioni(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la lista delle recensioni
    public void apriListaRecensioni() {
        chiudiFrameCorrente();
        frameCorrente = new ListaRecensioni(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la schermata di inserimento recensione
    public void apriInserimentoRecensione(String matricolaAcquirente, String matricolaVenditore, int idOfferta) {
        chiudiFrameCorrente();
        frameCorrente = new InserimentoRecensione(matricolaAcquirente, matricolaVenditore, idOfferta, this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre il report statistiche
    public void apriReport() {
        chiudiFrameCorrente();
        frameCorrente = new Report(this);
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
   
    // Apre la schermata di offerta vendita
    public void apriOffertaVendita(int idAnnuncio) {
        chiudiFrameCorrente();
        OffertaVendita frame = new OffertaVendita(this);
        frame.setIdAnnuncio(idAnnuncio);
        frameCorrente = frame;
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la schermata di offerta scambio
    public void apriOffertaScambio(int idAnnuncio) {
        chiudiFrameCorrente();
        OffertaScambio frame = new OffertaScambio(this);
        frame.setIdAnnuncio(idAnnuncio);
        frameCorrente = frame;
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // Apre la schermata di offerta regalo
    public void apriOffertaRegalo(int idAnnuncio) {
        chiudiFrameCorrente();
        OffertaRegalo frame = new OffertaRegalo(this);
        frame.setIdAnnuncio(idAnnuncio);
        frameCorrente = frame;
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di offerta regalo in modalità modifica
    public void apriOffertaRegaloPerModifica(int idOfferta) {
        chiudiFrameCorrente();
        OffertaRegalo frame = new OffertaRegalo(this);
        frame.caricaOffertaPerModifica(idOfferta);
        frameCorrente = frame;
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di offerta regalo in modalità modifica
    public void apriOffertaScambioPerModifica(int idOfferta) {
        chiudiFrameCorrente();
        OffertaScambio frame = new OffertaScambio(this);
        frame.caricaOffertaPerModifica(idOfferta);
        frameCorrente = frame;
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // apre la schermata di offerta regalo in modalità modifica
    public void apriOffertaVenditaPerModifica(int idOfferta) {
        chiudiFrameCorrente();
        OffertaVendita frame = new OffertaVendita(this);
        frame.caricaOffertaPerModifica(idOfferta);
        frameCorrente = frame;
        frameCorrente.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCorrente.setVisible(true);
    }
    
    // ==================== METODI LOGIN ====================
    
    // Effettua il login dell'utente: valida le credenziali e carica i dati dell'utente se valide
    
    public String EffettuaLogin(String matricola, String password) {
    
    	// validazione campi vuoti
        if (matricola == null || matricola.trim().isEmpty()) {
            return "Il campo matricola non può esser vuoto, inserisci la matricola";
        }
        
        if (password == null || password.trim().isEmpty()) {
            return "Il campo password non può esser vuoto, inserisci la password";
        }
        
        try {
 
        	// verifica credenziali tramite DAO
            boolean valido = LoginDAO.verificaLogin(matricola, password);
            
            if (!valido) {
                return "Credenziali non valide";
            }
            
    
            // carica i dati dell'utente loggato
            Utente_entity Utente = LoginDAO.getUtente(matricola);
            this.User = Utente;
            
            return "Login effettuato con successo";
            
        } catch (SQLException e) {
            return "Impossibile connettersi al database: " + e.getMessage();
        }
    }

    // ==================== METODI REGISTRAZIONE ====================
    
    // Registra un nuovo utente : valida i campi e verifica che l'utente non sia già registrato 
    public String EffettuaRegistrazione(String nome, String cognome, String matricola, String telefono, String email, String password, String confermaPassword) {
  
    	// validazione dei campi obbligatori
    	if (nome == null || nome.trim().isEmpty() ||
            cognome == null || cognome.trim().isEmpty() ||
            matricola == null || matricola.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confermaPassword == null || confermaPassword.trim().isEmpty()) {
            
            return "Tutti i campi sono obbligatori";
        }
    	
    	// validazione formato email e telefono
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Formato email non valido";
        }
        
        
        if (!telefono.matches("[0-9]{10}")) {
            return "Il numero di telefono deve contenere 10 cifre";
        }
        
        // verifica corrispondenza password
        if (!password.equals(confermaPassword)) {
            return "Le password non corrispondono";
        }
        
        try {
        	// effettua la registrazione tramite DAO
        	boolean registrazioneRiuscita = RegistrazioneDAO.effettuaRegistrazione(
        			nome, cognome, matricola, telefono, email, password
        			);
        
        	
        	if (!registrazioneRiuscita) {
        		return "Impossibile effettuare la registrazione, Utente già registrato";
        	}
        
        	return "Registrazione effettuata con successo";
    	
        } catch (SQLException e) {
        	return "Impossibile effettuare la registrazione: " + e.getMessage();
    
        }
    }
    

    // ==================== METODI AREA UTENTE ====================
    
    // recupera i dati dell'utente loggato
    public Utente_entity getUser() {
        return User;
    } 

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
    
    // ==================== METODI OGGETTI ====================
    
    // carica un nuovo oggetto nel sistema che verrà usato per creare un annuncio
    public String InserisciOggetto(String nome, String descrizione, String categoriaSelezionata) {
    	
    	// validazione campi obbligatori
        if (nome == null || nome.trim().isEmpty()) {
            return "Il nome è obbligatorio";
        }
        
        if (descrizione == null || descrizione.trim().isEmpty()) {
            return "La descrizione è obbligatoria";
        }
        
        if (categoriaSelezionata == null || categoriaSelezionata.equals("Seleziona una categoria")) {
            return "Seleziona una categoria valida";
        }
        
        try {
        	
        	// converte la categoria selezionata in enum TipologiaCategoria e recupera l'ID
            TipologiaCategoria categoria = TipologiaCategoria.fromNome(categoriaSelezionata);
            int idCategoria = categoria.getId();
            
            //inserimento oggetto tramite DAO
            OggettoDAO oggettoDAO = new OggettoDAO();
            int idOggetto = oggettoDAO.inserisciOggetto(nome, descrizione, idCategoria);
            
            if (idOggetto <= 0) {
                return "Impossibile inserire l'oggetto";
            }
            
            // memorizza l'oggetto creato per usarlo successivamente nell'annuncio
            this.UltimoOggettoCreato = new Oggetto_entity(idOggetto, nome, descrizione, idCategoria);
            
            return "Oggetto inserito con successo";
            
        } catch (IllegalArgumentException ex) {
            return "Categoria non valida";
        } catch (SQLException ex) {
            return "Errore durante l'inserimento dell'oggetto: " + ex.getMessage();
        }
    }
    
    //imposta l'oggetto da usare per creare l'annuncio
    public void impostaOggettoPerAnnuncio(Oggetto_entity oggetto) {
        this.OggettoAnnuncio = oggetto;
    }

    public Oggetto_entity getUltimoOggettoCreato() {
        return UltimoOggettoCreato;
    }

    public Oggetto_entity getOggettoAnnuncio() {
        return OggettoAnnuncio;
    }    

    // ==================== METODI ANNUNCIO ====================
    
    // memorizza le informazioni generali dell'annuncio da creare per poi pubblicare quello specifico
    public void impostaInformazioniAnnuncio(Oggetto_entity oggetto, String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, ArrayList<String> percorsiImmagini) {
        this.OggettoAnnuncio = oggetto;
        this.titoloAnnuncio = titolo;
        this.descrizioneAnnuncio = descrizione;
        this.modalitaConsegnaAnnuncio = modalitaConsegna;
        this.fasciaOrariaAnnuncio = fasciaOraria;
        this.percorsiImmaginiAnnuncio = percorsiImmagini;
    }
    
    //metodi per la gestione delle immagini dell'annuncio
    public void inizializzaListaImmaginiAnnuncio() {
        if (percorsiImmaginiAnnuncio == null) {
            percorsiImmaginiAnnuncio = new ArrayList<>();
        }
    }

    public void aggiungiImmagineAnnuncio(String percorso) {
        if (percorsiImmaginiAnnuncio == null) {
            percorsiImmaginiAnnuncio = new ArrayList<>();
        }
        percorsiImmaginiAnnuncio.add(percorso);
    }

    public void rimuoviImmagineAnnuncio(int index) {
        if (percorsiImmaginiAnnuncio != null && index >= 0 && index < percorsiImmaginiAnnuncio.size()) {
            percorsiImmaginiAnnuncio.remove(index);
        }
    }

    public ArrayList<String> getPercorsiImmaginiAnnuncio() {
        return percorsiImmaginiAnnuncio != null ? percorsiImmaginiAnnuncio : new ArrayList<>();
    }

    public int getNumeroImmaginiAnnuncio() {
        return percorsiImmaginiAnnuncio != null ? percorsiImmaginiAnnuncio.size() : 0;
    }

    public String getNomeImmagineAnnuncio(int index) {
        if (percorsiImmaginiAnnuncio != null && index >= 0 && index < percorsiImmaginiAnnuncio.size()) {
            String percorso = percorsiImmaginiAnnuncio.get(index);
            return new java.io.File(percorso).getName();
        }
        return "";
    }
    
    // ==================== METODI ANNUNCIO SCAMBIO, ANNUNCIO VENDITA, ANNUNCIO REGALO =======================

    // pubblica l'annuncio specifico in base alla tipologia usando le informazioni generali memorizzate
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

    public String PubblicaAnnuncioVendita(String prezzoStr) {
    	
    	// validazione prezzo
        if (prezzoStr == null || prezzoStr.trim().isEmpty()) {
            return "Il campo prezzo è obbligatorio";
        }
        
        float prezzo;
        try {
            prezzo = Float.parseFloat(prezzoStr);
        } catch (NumberFormatException e) {
            return "Inserisci un prezzo valido";
        }
        
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

    // metodi che inseriscono l'annuncio nel database e le relative foto:
    // validano i campi obbligatori e inseriscono l'annuncio e le relative foto tramite le rispettive DAO
    
    public String InserimentoAnnuncioScambio(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String oggettoRichiesto, String matricolaVenditore, int idOggetto, List<String> percorsiImmagini) {
        if (titolo == null || titolo.trim().isEmpty() ||
            descrizione == null || descrizione.trim().isEmpty() ||
            modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
            fasciaOraria == null ||
            oggettoRichiesto == null || oggettoRichiesto.trim().isEmpty() ||
            matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

            return "Tutti i campi sono obbligatori";
        }


        if (percorsiImmagini == null || percorsiImmagini.isEmpty()) {
            return "Devi inserire almeno una foto";
        }

        try {
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

            if (idAnnuncio <= 0) {
                return "Impossibile pubblicare l'annuncio";
            }

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

    public String InserimentoAnnuncioVendita(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, float prezzo, String matricolaVenditore, int idOggetto, List<String> percorsiImmagini) {
        if (titolo == null || titolo.trim().isEmpty() ||
            descrizione == null || descrizione.trim().isEmpty() ||
            modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
            fasciaOraria == null ||
            prezzo <= 0 ||
            matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

            return "Tutti i campi sono obbligatori e il prezzo deve essere maggiore di zero";
        }


        if (percorsiImmagini == null || percorsiImmagini.isEmpty()) {
            return "Devi inserire almeno una foto";
        }

        try {
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

            if (idAnnuncio <= 0) {
                return "Impossibile pubblicare l'annuncio";
            }

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

    public String InserimentoAnnuncioRegalo(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String motivoCessione, String matricolaVenditore, int idOggetto, List<String> percorsiImmagini) {
        if (titolo == null || titolo.trim().isEmpty() ||
            descrizione == null || descrizione.trim().isEmpty() ||
            modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
            fasciaOraria == null ||
            motivoCessione == null || motivoCessione.trim().isEmpty() ||
            matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

            return "Tutti i campi sono obbligatori";
        }


        if (percorsiImmagini == null || percorsiImmagini.isEmpty()) {
            return "Devi inserire almeno una foto";
        }

        try {
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

            if (idAnnuncio <= 0) {
                return "Impossibile pubblicare l'annuncio";
            }

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
    
    
    // ==================== METODI LISTA ANNUNCI ====================
    
    // carica gli annunci dal database in base ai filtri selezionati (tipologia e categoria) e all'eventuale testo di ricerca
    public String caricaAnnunci(String tipologia, String categoria, String testoRicerca) {
        try {
            ListaAnnunciDAO dao = new ListaAnnunciDAO();
            int count = 0;
            boolean ricercaAttiva = !testoRicerca.isEmpty();

            if (tipologia.equals("Vendita")) {
                if (ricercaAttiva) {
                    annunciVenditaCaricati = dao.cercaAnnunciVendita(testoRicerca, categoria);
                } else {
                    annunciVenditaCaricati = categoria.equals("Seleziona una categoria") 
                        ? dao.getAnnunciVendita() 
                        : dao.getAnnunciVenditaCategoria(categoria);
                }
                count = annunciVenditaCaricati.size();
                
            } else if (tipologia.equals("Scambio")) {
                if (ricercaAttiva) {
                    annunciScambioCaricati = dao.cercaAnnunciScambio(testoRicerca, categoria);
                } else {
                    annunciScambioCaricati = categoria.equals("Seleziona una categoria") 
                        ? dao.getAnnunciScambio() 
                        : dao.getAnnunciScambioCategoria(categoria);
                }
                count = annunciScambioCaricati.size();
                
            } else if (tipologia.equals("Regalo")) {
                if (ricercaAttiva) {
                    annunciRegaloCaricati = dao.cercaAnnunciRegalo(testoRicerca, categoria);
                } else {
                    annunciRegaloCaricati = categoria.equals("Seleziona una categoria") 
                        ? dao.getAnnunciRegalo() 
                        : dao.getAnnunciRegaloCategoria(categoria);
                }
                count = annunciRegaloCaricati.size();
            }
            
            // gestione messaggio di riepilogo 
            String messaggio = ricercaAttiva 
                ? "Trovati " + count + " Annunci per \"" + testoRicerca + "\""
                : "Caricati " + count + " Annunci";
                
            return messaggio;

        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento degli Annunci: " + e.getMessage());
            e.printStackTrace();
            return "ERRORE: Errore nel caricamento dei dati: " + e.getMessage();
        }
    }

    
    //metodi per accedere agli annunci caricati
    public ArrayList<AnnuncioVendita_entity> getAnnunciVenditaCaricati() {
        return annunciVenditaCaricati != null ? annunciVenditaCaricati : new ArrayList<>();
    }

    public ArrayList<AnnuncioScambio_entity> getAnnunciScambioCaricati() {
        return annunciScambioCaricati != null ? annunciScambioCaricati : new ArrayList<>();
    }

    public ArrayList<AnnuncioRegalo_entity> getAnnunciRegaloCaricati() {
        return annunciRegaloCaricati != null ? annunciRegaloCaricati : new ArrayList<>();
    }
    
    // recupera le foto di un annuncio
    public ArrayList<String> getFotoAnnuncio(int idAnnuncio) {
        try {
            FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
            return fotoDAO.getFotoByAnnuncio(idAnnuncio);
        } catch (SQLException e) {
            System.err.println("Errore nel caricamento delle foto: " + e.getMessage());
            return null;
        }
    }

    // carica e ridimensiona un'immagine da un percorso specificato
    public ImageIcon caricaImmagine(String percorso, int larghezza, int altezza) {
        File file = new File(percorso);
        
        if (file.exists()) {
            ImageIcon originalIcon = new ImageIcon(percorso);
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                larghezza, altezza, Image.SCALE_SMOOTH
            );
            return new ImageIcon(scaledImage);
        }
        
        return null;
    }
    
    // verifica se un'offerta può essere fatta su un annuncio
    public String verificaOfferta(int idAnnuncio, String matricolaVenditore, StatoAnnuncio stato, String tipologia) {
        try {
        	
        	// controlla se lo stato dell'annuncio è chiuso
            if (stato == StatoAnnuncio.Chiuso) {
                return "Annuncio chiuso, non è possibile fare un'offerta.";
            }
            
            // controlla se l'annuncio non sia uno dei propri
            if (matricolaVenditore.equals(User.getMatricola())) {
                return "Non puoi fare un'offerta sul tuo annuncio.";
            }

            return null;

        } catch (Exception e) {
            return "Si è verificato un errore: " + e.getMessage();
        }
    }
    
    // metodi per informazioni sui venditori 
    public String getNominativoVenditore(String matricolaVenditore) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getNominativoUtente(matricolaVenditore);
        } catch (SQLException e) {
            System.err.println("Errore nel caricamento nominativo venditore: " + e.getMessage());
            return null;
        }
    }

    public double getValutazioneMediaVenditore(String matricolaVenditore) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getValutazioneMedia(matricolaVenditore);
        } catch (SQLException e) {
            System.err.println("Errore nel caricamento valutazione venditore: " + e.getMessage());
            return 0.0;
        }
    }

    public int getNumeroRecensioniVenditore(String matricolaVenditore) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getNumeroRecensioni(matricolaVenditore);
        } catch (SQLException e) {
            System.err.println("Errore nel caricamento numero recensioni: " + e.getMessage());
            return 0;
        }
    }
    
    // ==================== METODI OFFERTE REGALO ====================

    public String inviaOffertaRegalo(String messaggioMotivazionale, int idAnnuncio) {
        // Validazione input
        if (messaggioMotivazionale == null || messaggioMotivazionale.trim().isEmpty()) {
            return "Il messaggio motivazionale è obbligatorio";
        }
        
        String matricolaAcquirente = getMatricolaUtenteLoggato();
        if (matricolaAcquirente == null) {
            return "Errore: utente non loggato";
        }
        
        if (idAnnuncio <= 0) {
            return "ID annuncio non valido";
        }
        
        // inserimento tramite DAO
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
            
            return "SUCCESS:Offerta inviata con successo";
            
        } catch (SQLException e) {
            return "Errore durante l'invio dell'offerta: " + e.getMessage();
        }
    }
    
    //aggiorna un'offerta regalo esistente
    public String aggiornaOffertaRegalo(String messaggioMotivazionale, int idAnnuncio, int idOfferta) {
        // Validazione input
        if (messaggioMotivazionale == null || messaggioMotivazionale.trim().isEmpty()) {
            return "Il messaggio motivazionale è obbligatorio";
        }
        
        String matricolaAcquirente = getMatricolaUtenteLoggato();
        if (matricolaAcquirente == null) {
            return "Errore: utente non loggato";
        }
        
        if (idAnnuncio <= 0 || idOfferta <= 0) {
            return "ID annuncio o offerta non validi";
        }
        
        // Aggiornamento tramite DAO
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
            
            return "SUCCESS:Offerta aggiornata con successo";
            
        } catch (SQLException e) {
            return "Errore durante l'aggiornamento dell'offerta: " + e.getMessage();
        }
    }
    
    // carica i dati di un'offerta regalo per modificarla
    public String caricaOffertaRegaloPerModifica(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            OffertaDAO offertaDAO = new OffertaDAO();
            OffertaRegalo_entity offerta = offertaDAO.caricaOffertaRegalo(idOfferta);
            
            if (offerta == null) {
                return "Offerta non trovata";
            }
            
            // Restituisce i dati nel formato SUCCESS:dato
            return "SUCCESS:" + offerta.getMessaggioMotivazionale();
            
        } catch (SQLException e) {
            return "Errore nel caricamento dell'offerta: " + e.getMessage();
        }
    }

    // ==================== METODI OFFERTE SCAMBIO ====================

    public String inviaOffertaScambio(String oggettoProposto, int idAnnuncio) {
        // Validazione input
        if (oggettoProposto == null || oggettoProposto.trim().isEmpty()) {
            return "L'oggetto proposto è obbligatorio";
        }
        
        String matricolaAcquirente = getMatricolaUtenteLoggato();
        if (matricolaAcquirente == null) {
            return "Errore: utente non loggato";
        }
        
        if (idAnnuncio <= 0) {
            return "ID annuncio non valido";
        }
        
        // inserimento tramite DAO
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
            
            return "SUCCESS:Offerta inviata con successo";
            
        } catch (SQLException e) {
            return "Errore durante l'invio dell'offerta: " + e.getMessage();
        }
    }
    
    //aggiorna un'offerta di scambio esistente
    public String aggiornaOffertaScambio(String oggettoProposto, int idAnnuncio, int idOfferta) {
        // validazione input
        if (oggettoProposto == null || oggettoProposto.trim().isEmpty()) {
            return "L'oggetto proposto è obbligatorio";
        }
        
        String matricolaAcquirente = getMatricolaUtenteLoggato();
        if (matricolaAcquirente == null) {
            return "Errore: utente non loggato";
        }
        
        if (idAnnuncio <= 0 || idOfferta <= 0) {
            return "ID annuncio o offerta non validi";
        }
        
        // aggiornamento tramite DAO
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
            
            return "SUCCESS:Offerta aggiornata con successo";
            
        } catch (SQLException e) {
            return "Errore durante l'aggiornamento dell'offerta: " + e.getMessage();
        }
    }
    
    // carica i dati di un'offerta di scambio per modificarla
    public String caricaOffertaScambioPerModifica(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            OffertaDAO offertaDAO = new OffertaDAO();
            OffertaScambio_entity offerta = offertaDAO.caricaOffertaScambio(idOfferta);
            
            if (offerta == null) {
                return "Offerta non trovata";
            }
            
            // Restituisce i dati nel formato SUCCESS:dato
            return "SUCCESS:" + offerta.getOggettoProposto();
            
        } catch (SQLException e) {
            return "Errore nel caricamento dell'offerta: " + e.getMessage();
        }
    }

    // ==================== METODI OFFERTE VENDITA ====================

    public String inviaOffertaVendita(String importoPropostoString, int idAnnuncio) {
        // Validazione input
        if (importoPropostoString == null || importoPropostoString.trim().isEmpty()) {
            return "L'importo proposto è obbligatorio";
        }
        
        float importoProposto;
        try {
            importoProposto = Float.parseFloat(importoPropostoString);
        } catch (NumberFormatException e) {
            return "L'importo deve essere un numero valido";
        }
        
        if (importoProposto <= 0) {
            return "L'importo proposto deve essere maggiore di zero";
        }
        
        String matricolaAcquirente = getMatricolaUtenteLoggato();
        if (matricolaAcquirente == null) {
            return "Errore: utente non loggato";
        }
        
        if (idAnnuncio <= 0) {
            return "ID annuncio non valido";
        }
        
        // Inserimento tramite DAO
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
            
            return "SUCCESS:Offerta inviata con successo";
            
        } catch (SQLException e) {
        	String errorMessage = e.getMessage();
            
            // gestione prezzo proposto superiore al prezzo di vendita
            if (errorMessage != null && errorMessage.contains("Il prezzo proposto deve essere inferiore o uguale al prezzo di vendita")) {
                return "Il prezzo proposto dev'essere inferiore o uguale al prezzo di vendita";
            
            }
            
            return "Errore durante l'invio dell'offerta: " + e.getMessage();
        }
    }

    // aggiorna un'offerta di vendita esistente	
    public String aggiornaOffertaVendita(String importoPropostoString, int idAnnuncio, int idOfferta) {
        // validazione input
        if (importoPropostoString == null || importoPropostoString.trim().isEmpty()) {
            return "L'importo proposto è obbligatorio";
        }
        
        float importoProposto;
        try {
            importoProposto = Float.parseFloat(importoPropostoString);
        } catch (NumberFormatException e) {
            return "L'importo deve essere un numero valido";
        }
        
        if (importoProposto <= 0) {
            return "L'importo proposto deve essere maggiore di zero";
        }
        
        String matricolaAcquirente = getMatricolaUtenteLoggato();
        if (matricolaAcquirente == null) {
            return "Errore: utente non loggato";
        }
        
        if (idAnnuncio <= 0 || idOfferta <= 0) {
            return "ID annuncio o offerta non validi";
        }
        
        // Aggiornamento tramite DAO
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
            
            return "SUCCESS:Offerta aggiornata con successo";
            
        } catch (SQLException e) {
            return "Errore durante l'aggiornamento dell'offerta: " + e.getMessage();
        }
    }

    // carica i dati di un'offerta di vendita per modificarla
    public String caricaOffertaVenditaPerModifica(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            OffertaDAO offertaDAO = new OffertaDAO();
            OffertaVendita_entity offerta = offertaDAO.caricaOffertaVendita(idOfferta);
            
            if (offerta == null) {
                return "Offerta non trovata";
            }
            
            // Restituisce i dati nel formato SUCCESS:dato
            return "SUCCESS:" + String.valueOf(offerta.getImportoProposto());
            
        } catch (SQLException e) {
            return "Errore nel caricamento dell'offerta: " + e.getMessage();
        }
    }

    // ==================== METODI STORICO OFFERTE ====================
    
    //carica le offerte fatte dall'utente loggato
    public ArrayList<Offerta_entity> caricaOfferteUtente() {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null) {
                System.err.println("Nessun utente loggato");
                return new ArrayList<>();
            }
            
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            return dao.getOfferte(matricola);
            
        } catch (Exception e) {
            System.err.println("Errore durante il caricamento delle offerte: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }    
   
    // metodi per accedere ai dati delle offerte caricate tramite indice
    public int getNumeroOfferteUtente() {
        ArrayList<Offerta_entity> offerte = caricaOfferteUtente();
        return offerte != null ? offerte.size() : 0;
    }

    public String getStatoOffertaByIndex(int index) {
        ArrayList<Offerta_entity> offerte = caricaOfferteUtente();
        if (offerte != null && index >= 0 && index < offerte.size()) {
            return offerte.get(index).getStatoOfferta().toString();
        }
        return "";
    }

    public String getTipologiaOffertaByIndex(int index) {
        ArrayList<Offerta_entity> offerte = caricaOfferteUtente();
        if (offerte != null && index >= 0 && index < offerte.size()) {
            return offerte.get(index).getTipologiaOfferta();
        }
        return "";
    }

    public int getIdOffertaByIndex(int index) {
        ArrayList<Offerta_entity> offerte = caricaOfferteUtente();
        if (offerte != null && index >= 0 && index < offerte.size()) {
            return offerte.get(index).getIdOfferta();
        }
        return -1;
    }

    public int getIdAnnuncioOffertaByIndex(int index) {
        ArrayList<Offerta_entity> offerte = caricaOfferteUtente();
        if (offerte != null && index >= 0 && index < offerte.size()) {
            return offerte.get(index).getIdAnnuncio();
        }
        return -1;
    }
    
    // metodi che recuperano dettagli aggiuntivi di un'offerta
    public String getDettaglioOfferta(int idOfferta) {
        if (idOfferta <= 0) {
            return null;
        }
        
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            return dao.getDettaglioOfferta(idOfferta);
        } catch (SQLException e) {
            System.err.println("Errore nel recupero dettaglio offerta: " + e.getMessage());
            return null;
        }
    }
    
    public String getTitoloAnnuncioOfferta(int idOfferta) {
        if (idOfferta <= 0) {
            return null;
        }
        
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            return dao.getTitoloAnnuncio(idOfferta);
            
        } catch (SQLException e) {
            System.err.println("Errore nel recupero titolo annuncio: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getMatricolaVenditoreOfferta(int idOfferta) {
        if (idOfferta <= 0) {
            return null;
        }
        
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            return dao.getMatricolaVenditore(idOfferta);
            
        } catch (SQLException e) {
            System.err.println("Errore nel recupero matricola venditore: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public int getIdAnnuncioDaOfferta(int idOfferta) {
        if (idOfferta <= 0) {
            return -1;
        }
        
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            return dao.getIdAnnuncioFromOfferta(idOfferta);
        } catch (SQLException e) {
            System.err.println("Errore nel recupero ID annuncio: " + e.getMessage());
            return -1;
        }
    }
    
    // verifica se un offerta può essere modificata
    public boolean isOffertaModificabile(int idOfferta) {
        if (idOfferta <= 0) {
            return false;
        }
        
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            return dao.isOffertaModificabile(idOfferta);
            
        } catch (SQLException e) {
            System.err.println("Errore nella verifica modificabilità: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // ritira (elimina) un'offerta fatta dall'utente loggato
    public String ritiraOfferta(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            boolean eliminato = dao.DeleteOfferte(idOfferta);
            
            if (eliminato) {
                return "Offerta ritirata con successo";
            } else {
                return "Impossibile ritirare l'offerta";
            }
            
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            
            // gestione errore offerta accettata
            if (errorMessage != null && errorMessage.contains("Impossibile ritirare un'offerta accettata")) {
                return "Non puoi ritirare un'offerta già accettata";
            }
            
            return "Errore durante il ritiro dell'offerta: " + errorMessage;
        }
    }
    
    // ==================== METODI ANNUNCI PUBBLICATI ====================

    // carica tutti gli annunci pubblicati dall'utente loggato
    public int getNumeroAnnunciPubblicati() {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null) {
                return 0;
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            // Salva gli ID per uso successivo
            idsAnnunciCaricati = new ArrayList<>();
            for (Annuncio_entity ann : annunci) {
                idsAnnunciCaricati.add(ann.getIdAnnuncio());
            }
            
            return annunci.size();
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento degli annunci: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    // metodi per accedere ai dati degli annunci pubblicati tramite indice
    public String getTitoloAnnuncioPubblicato(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getTitolo();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getDescrizioneAnnuncioPubblicato(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getDescrizione();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getCategoriaAnnuncioPubblicato(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getTipologiaCategoria().toString();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getModalitaConsegnaAnnuncioPubblicato(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getModalitàConsegna();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getFasciaOrariaAnnuncioPubblicato(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null ||  
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }

            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);

            if (index < annunci.size()) {
                FasciaOraria fasciaOraria = annunci.get(index).getFasciaOraria();
                return fasciaOraria != null ? fasciaOraria.toString() : "";
            }

            return "";

        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getDataPubblicazioneAnnuncio(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getDataPubblicazione().toString();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getStatoAnnuncioPubblicato(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getStatoAnnuncio().toString();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public boolean hasNuoveOfferteAnnuncio(int index) {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null || idsAnnunciCaricati == null || 
                index < 0 || index >= idsAnnunciCaricati.size()) {
                return false;
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Annuncio_entity> annunci = dao.getAnnunci(matricola);
            
            if (index < annunci.size()) {
                return annunci.get(index).getVisualizzaOfferte();
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return false;
        }
    }

    public int getIdAnnuncioPubblicato(int index) {
        if (idsAnnunciCaricati != null && index >= 0 && index < idsAnnunciCaricati.size()) {
            return idsAnnunciCaricati.get(index);
        }
        return -1;
    }

    // carica le offerte ricevute per un annuncio
    public int getNumeroOfferteRicevute(int idAnnuncio) {
        try {
            if (idAnnuncio <= 0) {
                return 0;
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            // Salva gli ID per uso successivo
            idsOfferteCaricate = new ArrayList<>();
            for (Offerta_entity off : offerte) {
                idsOfferteCaricate.add(off.getIdOfferta());
            }
            
            return offerte.size();
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle offerte: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    // metodi per accedere ai dati delle offerte ricevute tramite indice
    public String getTipologiaOffertaRicevuta(int idAnnuncio, int index) {
        try {
            if (idAnnuncio <= 0 || idsOfferteCaricate == null || 
                index < 0 || index >= idsOfferteCaricate.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            if (index < offerte.size()) {
                return offerte.get(index).getTipologiaOfferta();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getMatricolaAcquirenteOfferta(int idAnnuncio, int index) {
        try {
            if (idAnnuncio <= 0 || idsOfferteCaricate == null || 
                index < 0 || index >= idsOfferteCaricate.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            if (index < offerte.size()) {
                return offerte.get(index).getMatricolaAcquirente();
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public int getIdOffertaRicevuta(int idAnnuncio, int index) {
        try {
            if (idAnnuncio <= 0 || idsOfferteCaricate == null || 
                index < 0 || index >= idsOfferteCaricate.size()) {
                return -1;
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            if (index < offerte.size()) {
                return offerte.get(index).getIdOfferta();
            }
            
            return -1;
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return -1;
        }
    }

    // metodi per recuperare i dettagli delle offerte
    public String getDettaglioOffertaVendita(int idAnnuncio, int index) {
        try {
            if (idAnnuncio <= 0 || idsOfferteCaricate == null || 
                index < 0 || index >= idsOfferteCaricate.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            if (index < offerte.size()) {
                Offerta_entity offerta = offerte.get(index);
                if (offerta instanceof OffertaVendita_entity) {
                    OffertaVendita_entity offertaVendita = (OffertaVendita_entity) offerta;
                    return String.format("%.2f", offertaVendita.getImportoProposto());
                }
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getDettaglioOffertaScambio(int idAnnuncio, int index) {
        try {
            if (idAnnuncio <= 0 || idsOfferteCaricate == null || 
                index < 0 || index >= idsOfferteCaricate.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            if (index < offerte.size()) {
                Offerta_entity offerta = offerte.get(index);
                if (offerta instanceof OffertaScambio_entity) {
                    OffertaScambio_entity offertaScambio = (OffertaScambio_entity) offerta;
                    return offertaScambio.getOggettoProposto() != null ? 
                           offertaScambio.getOggettoProposto() : "";
                }
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    public String getDettaglioOffertaRegalo(int idAnnuncio, int index) {
        try {
            if (idAnnuncio <= 0 || idsOfferteCaricate == null || 
                index < 0 || index >= idsOfferteCaricate.size()) {
                return "";
            }
            
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            ArrayList<Offerta_entity> offerte = dao.getOfferte(idAnnuncio);
            
            if (index < offerte.size()) {
                Offerta_entity offerta = offerte.get(index);
                if (offerta instanceof OffertaRegalo_entity) {
                    OffertaRegalo_entity offertaRegalo = (OffertaRegalo_entity) offerta;
                    return offertaRegalo.getMessaggioMotivazionale() != null ? 
                           offertaRegalo.getMessaggioMotivazionale() : "";
                }
            }
            
            return "";
            
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
            return "";
        }
    }

    // accetta un'offerta ricevuta, chiude l'annuncio e rifiuta tutte le altre
    public String accettaOffertaRicevuta(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            boolean successo = dao.accettaOfferta(idOfferta);
            
            if (successo) {
                return "SUCCESS:Offerta accettata con successo";
            } else {
                return "Errore: offerta non trovata o già gestita";
            }
            
        } catch (SQLException e) {
            System.err.println("Errore durante l'accettazione dell'offerta: " + e.getMessage());
            e.printStackTrace();
            return "Errore durante l'accettazione: " + e.getMessage();
        }
    }

    // rifiuta un'offerta ricevuta
    public String rifiutaOffertaRicevuta(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            boolean successo = dao.rifiutaOfferta(idOfferta);
            
            if (successo) {
                return "SUCCESS:Offerta rifiutata con successo";
            } else {
                return "Errore: offerta non trovata o già gestita";
            }
            
        } catch (SQLException e) {
            System.err.println("Errore durante il rifiuto dell'offerta: " + e.getMessage());
            e.printStackTrace();
            return "Errore durante il rifiuto: " + e.getMessage();
        }
    }
    
    // ==================== METODI LISTA TRANSAZIONI ====================
    
    // carica le transazioni completate dall'utente (offerte accettate)
    public ArrayList<Transazione_entity> caricaTransazioni() {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null) {
                return new ArrayList<>();
            }
            
            TransazioniDAO dao = new TransazioniDAO();
            return dao.getTransazioni(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle transazioni: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    // carica le transazioni e restituisce il conteggio
    public int getNumeroTransazioni() {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null) {
                return 0;
            }
            
            TransazioniDAO dao = new TransazioniDAO();
            transazioniCaricate = dao.getTransazioni(matricola);
            return transazioniCaricate.size();
            
        } catch (Exception e) {
            System.err.println("Errore durante il caricamento delle transazioni: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    // metpoi per accedere ai dati delle transazioni tramite indice
    public String getTitoloAnnuncioTransazione(int index) {
        if (transazioniCaricate != null && index >= 0 && index < transazioniCaricate.size()) {
            return transazioniCaricate.get(index).getTitoloAnnuncio();
        }
        return "";
    }

    public String getMatricolaVenditoreTransazione(int index) {
        if (transazioniCaricate != null && index >= 0 && index < transazioniCaricate.size()) {
            return transazioniCaricate.get(index).getMatricolaVenditore();
        }
        return "";
    }

    public String getMatricolaAcquirenteTransazione(int index) {
        if (transazioniCaricate != null && index >= 0 && index < transazioniCaricate.size()) {
            return transazioniCaricate.get(index).getMatricolaAcquirente();
        }
        return "";
    }

    public int getIdOffertaTransazione(int index) {
        if (transazioniCaricate != null && index >= 0 && index < transazioniCaricate.size()) {
            return transazioniCaricate.get(index).getIdOfferta();
        }
        return -1;
    }

    public boolean hasRecensioneTransazione(int index) {
        if (transazioniCaricate != null && index >= 0 && index < transazioniCaricate.size()) {
            return transazioniCaricate.get(index).hasRecensione();
        }
        return false;
    }

    

    // ==================== METODI LISTA RECENSIONI E INSERIMENTO RECENSIONE ====================
    

    // carica le recensioni inviate dall'utente loggato
    public ArrayList<Recensione_entity> caricaRecensioniInviate() {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null) {
                return new ArrayList<>();
            }
            
            ListaRecensioniDao dao = new ListaRecensioniDao();
            return dao.VisualizzaRecensioniInviate(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle recensioni inviate: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // carica le recensioni ricevute dall'utente loggato
    public ArrayList<Recensione_entity> caricaRecensioniRicevute() {
        try {
            String matricola = getMatricolaUtenteLoggato();
            if (matricola == null) {
                return new ArrayList<>();
            }
            
            ListaRecensioniDao dao = new ListaRecensioniDao();
            return dao.VisualizzaRecensioniRicevute(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle recensioni ricevute: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // recupera il titolo dell'annuncio associato ad un'offerta
    public String getTitoloAnnuncioDaOfferta(int idOfferta) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getTitoloAnnuncioDaOfferta(idOfferta);
        } catch (SQLException e) {
            System.err.println("Errore nel recupero titolo annuncio: " + e.getMessage());
            return null;
        }
    }
    
    // recupera il nominativo dell'utente dato la sua matricola
    public String getNominativoUtente(String matricola) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getNominativoUtente(matricola);
        } catch (SQLException e) {
            System.err.println("Errore nel recupero nominativo utente: " + e.getMessage());
            return null;
        }
    }
    
    // inserisce una nuova recensione nel sistema e verifica che non esista già una recensione per la stessa transazione
    public String inserisciRecensione(String matricolaRecensore, String matricolaRecensito, 
            int idOfferta, int valutazione, String commento) {
    	// validazione punteggio
    	if (valutazione < 1 || valutazione > 5) {
    		return "La valutazione deve essere tra 1 e 5";
    	}
    	
    	// validazione commento
    	if (commento == null || commento.trim().isEmpty()) {
    		return "Il commento è obbligatorio";
    	}

    	try {
    		// Verifica se esiste già una recensione per la transazione tramite la DAO
    		if (InserimentoRecensioneDAO.esisteRecensione(idOfferta)) {
    			return "Hai già inserito una recensione per questa transazione";
    		}

    		// Inserisci la recensione tramite DAO
    		InserimentoRecensioneDAO.inserisciRecensione(
    				matricolaRecensito, 
    				matricolaRecensore, 
    				valutazione, 
    				commento, 
    				idOfferta
    				);

    		return "Recensione inserita con successo";

    	} catch (SQLException e) {
    		return "Errore durante l'inserimento della recensione: " + e.getMessage();
    	}
    }

    // ==================== METODI VISUALIZZA RECENSIONI VENDITORE ====================    

    // imposta il venditore di cui visualizzare le recensioni
    public void impostaVenditorePerVisualizzazione(String matricolaVenditore) {
        this.matricolaVenditoreCorrente = matricolaVenditore;
    }

    public String getMatricolaVenditoreCorrente() {
        return matricolaVenditoreCorrente;
    }
    
    // carica le recensioni di un venditore specifico
    public ArrayList<Recensione_entity> caricaRecensioniVenditore(String matricolaVenditore) {
        try {
            if (matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {
                return new ArrayList<>();
            }
            
            ListaRecensioniDao dao = new ListaRecensioniDao();
            return dao.VisualizzaRecensioniRicevute(matricolaVenditore);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle recensioni del venditore: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    // Metodi per accedere ai dati delle recensioni tramite indice
    public int getIdRecensioneVenditore(String matricolaVenditore, int index) {
        ArrayList<Recensione_entity> recensioni = caricaRecensioniVenditore(matricolaVenditore);
        if (recensioni != null && index >= 0 && index < recensioni.size()) {
            return recensioni.get(index).getIdRecensione();
        }
        return -1;
    }

    public String getCommentoRecensioneVenditore(String matricolaVenditore, int index) {
        ArrayList<Recensione_entity> recensioni = caricaRecensioniVenditore(matricolaVenditore);
        if (recensioni != null && index >= 0 && index < recensioni.size()) {
            return recensioni.get(index).getCommento();
        }
        return "";
    }

    public int getPunteggioRecensioneVenditore(String matricolaVenditore, int index) {
        ArrayList<Recensione_entity> recensioni = caricaRecensioniVenditore(matricolaVenditore);
        if (recensioni != null && index >= 0 && index < recensioni.size()) {
            return recensioni.get(index).getPunteggio();
        }
        return 0;
    }

    public String getDataRecensioneVenditore(String matricolaVenditore, int index) {
        ArrayList<Recensione_entity> recensioni = caricaRecensioniVenditore(matricolaVenditore);
        if (recensioni != null && index >= 0 && index < recensioni.size()) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(recensioni.get(index).getData());
        }
        return "";
    }

    public String getMatricolaAcquirenteRecensioneVenditore(String matricolaVenditore, int index) {
        ArrayList<Recensione_entity> recensioni = caricaRecensioniVenditore(matricolaVenditore);
        if (recensioni != null && index >= 0 && index < recensioni.size()) {
            return recensioni.get(index).getMatricolaAcquirente();
        }
        return "";
    }

    public int getIdOffertaRecensioneVenditore(String matricolaVenditore, int index) {
        ArrayList<Recensione_entity> recensioni = caricaRecensioniVenditore(matricolaVenditore);
        if (recensioni != null && index >= 0 && index < recensioni.size()) {
            return recensioni.get(index).getIdOfferta();
        }
        return -1;
    }
    
    // Metodi per accedere ai dati delle recensioni del venditore corrente tramite indice
    public int getPunteggioRecensioneVenditoreCorrente(int index) {
        if (matricolaVenditoreCorrente == null) return 0;
        return getPunteggioRecensioneVenditore(matricolaVenditoreCorrente, index);
    }

    public String getDataRecensioneVenditoreCorrente(int index) {
        if (matricolaVenditoreCorrente == null) return "";
        return getDataRecensioneVenditore(matricolaVenditoreCorrente, index);
    }

    public String getCommentoRecensioneVenditoreCorrente(int index) {
        if (matricolaVenditoreCorrente == null) return "";
        return getCommentoRecensioneVenditore(matricolaVenditoreCorrente, index);
    }

    public int getIdOffertaRecensioneVenditoreCorrente(int index) {
        if (matricolaVenditoreCorrente == null) return -1;
        return getIdOffertaRecensioneVenditore(matricolaVenditoreCorrente, index);
    }

    public String getMatricolaAcquirenteRecensioneVenditoreCorrente(int index) {
        if (matricolaVenditoreCorrente == null) return "";
        return getMatricolaAcquirenteRecensioneVenditore(matricolaVenditoreCorrente, index);
    }

    public int getNumeroRecensioniVenditoreCorrente() {
        if (matricolaVenditoreCorrente == null) return 0;
        return getNumeroRecensioniVenditore(matricolaVenditoreCorrente);
    }

    public String getNominativoVenditoreCorrente() {
        if (matricolaVenditoreCorrente == null) return null;
        return getNominativoUtente(matricolaVenditoreCorrente);
    }

    public double getValutazioneMediaVenditoreCorrente() {
        if (matricolaVenditoreCorrente == null) return 0.0;
        return getValutazioneMediaVenditore(matricolaVenditoreCorrente);
    }
    
    
    // ==================== METODI REPORT ====================

    // carica i prezzi degli annunci di vendita dell'utente 
    public String[] caricaPrezziAnnunci() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return new String[]{"Errore", "Errore", "Errore"};
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.getPrezziAnnunci(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento dei prezzi: " + e.getMessage());
            e.printStackTrace();
            return new String[]{
                "Non Esiste Prezzo Minimo", 
                "Non Esiste Prezzo Massimo", 
                "Non Esiste Prezzo Medio"
            };
        }
    }

    // metodi per le statistiche delle offerte per i grafici
    public int getOfferteTotali() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteTotali(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte totali: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int getOfferteRegalo() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteRegalo(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte regalo: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int getOfferteScambio() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteScambio(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte scambio: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int getOfferteVendita() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteVendita(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte vendita: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int getOfferteRegaloAccettate() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteRegaloAccettata(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte regalo accettate: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int getOfferteScambioAccettate() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteScambioAccettata(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte scambio accettate: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int getOfferteVenditaAccettate() {
        String matricola = getMatricolaUtenteLoggato();
        
        if (matricola == null) {
            return 0;
        }
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            return reportDAO.VisualizzaOfferteVenditaAccettata(matricola);
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento offerte vendita accettate: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
        
    }
    
 // ==================== METODI LISTA ANNUNCI - CORRETTI ====================

    
     // Restituisce il numero di annunci caricati per una data tipologia
         public int getNumeroAnnunciCaricati(String tipologia) {
        if (tipologia == null) {
            return 0;
        }
        
        switch (tipologia) {
            case "Vendita":
                return annunciVenditaCaricati != null ? annunciVenditaCaricati.size() : 0;
            case "Scambio":
                return annunciScambioCaricati != null ? annunciScambioCaricati.size() : 0;
            case "Regalo":
                return annunciRegaloCaricati != null ? annunciRegaloCaricati.size() : 0;
            default:
                return 0;
        }
    }

    
    //  Restituisce l'ID di un annuncio dato tipologia e indice
     
    public int getIdAnnuncioByIndex(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        return annunciVenditaCaricati.get(index).getIdAnnuncio();
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getIdAnnuncio();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getIdAnnuncio();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero ID annuncio: " + e.getMessage());
        }
        return -1;
    }

    
     // Restituisce la matricola del venditore di un annuncio dato tipologia e indice
    
    public String getMatricolaVenditoreAnnuncio(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        return annunciVenditaCaricati.get(index).getMatricolaVenditore();
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getMatricolaVenditore();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getMatricolaVenditore();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero matricola venditore: " + e.getMessage());
        }
        return "";
    }

    
     // Restituisce il titolo di un annuncio dato tipologia e indice
     
    public String getTitoloAnnuncio(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        return annunciVenditaCaricati.get(index).getTitolo();
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getTitolo();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getTitolo();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero titolo: " + e.getMessage());
        }
        return "";
    }

    
     // Restituisce la descrizione di un annuncio dato tipologia e indice
     
    public String getDescrizioneAnnuncio(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        return annunciVenditaCaricati.get(index).getDescrizione();
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getDescrizione();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getDescrizione();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero descrizione: " + e.getMessage());
        }
        return "";
    }

    
     // Restituisce la categoria di un annuncio dato tipologia e indice
     
    public String getCategoriaAnnuncio(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        return annunciVenditaCaricati.get(index).getTipologiaCategoria().toString();
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getTipologiaCategoria().toString();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getTipologiaCategoria().toString();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero categoria: " + e.getMessage());
        }
        return "";
    }

    
     // Restituisce lo stato di un annuncio dato tipologia e indice (come stringa)
     
    public String getStatoAnnuncioString(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        return annunciVenditaCaricati.get(index).getStatoAnnuncio().toString();
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getStatoAnnuncio().toString();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getStatoAnnuncio().toString();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero stato: " + e.getMessage());
        }
        return "Chiuso"; // Default safe
    }

         //Restituisce l'informazione extra di un annuncio (prezzo, oggetto richiesto, motivo cessione)
     	//in base alla tipologia
     
    public String getInfoExtraAnnuncio(String tipologia, int index) {
        try {
            switch (tipologia) {
                case "Vendita":
                    if (annunciVenditaCaricati != null && index >= 0 && index < annunciVenditaCaricati.size()) {
                        float prezzo = annunciVenditaCaricati.get(index).getPrezzoVendita();
                        return "€ " + String.format("%.2f", prezzo);
                    }
                    break;
                case "Scambio":
                    if (annunciScambioCaricati != null && index >= 0 && index < annunciScambioCaricati.size()) {
                        return annunciScambioCaricati.get(index).getOggettoRichiesto();
                    }
                    break;
                case "Regalo":
                    if (annunciRegaloCaricati != null && index >= 0 && index < annunciRegaloCaricati.size()) {
                        return annunciRegaloCaricati.get(index).getMotivoCessione();
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero info extra: " + e.getMessage());
        }
        return "";
    }

    
    //  Verifica se un'offerta può essere fatta su un annuncio
     // Restituisce null se ok, altrimenti il messaggio di errore
    
    public String verificaOffertaPossibile(int idAnnuncio, String matricolaVenditore, String stato) {
        try {
            // Controlla se lo stato dell'annuncio è chiuso
            if (stato.equalsIgnoreCase("Chiuso")) {
                return "Annuncio chiuso, non è possibile fare un'offerta.";
            }
            
            // Controlla se l'annuncio non sia uno dei propri
            if (matricolaVenditore.equals(User.getMatricola())) {
                return "Non puoi fare un'offerta sul tuo annuncio.";
            }

            return null; // Tutto ok

        } catch (Exception e) {
            return "Si è verificato un errore: " + e.getMessage();
        }
    }
    
    
  //  ==================== METODI LISTA RECENSIONI - DA AGGIUNGERE/MODIFICARE A MainController ====================

    		// NOTA: I metodi caricaRecensioniInviate() e caricaRecensioniRicevute() che restituiscono 
    		// ArrayList<Recensione_entity> devono rimanere PRIVATI e usati solo internamente.
    		// Aggiungiamo una cache per evitare chiamate multiple al DB.

    		
    		 // Carica le recensioni inviate dall'utente loggato
    		 //Restituisce il numero di recensioni caricate
    		public int getNumeroRecensioniInviate() {
    		    recensioniInviateCache = caricaRecensioniInviate();
    		    return recensioniInviateCache != null ? recensioniInviateCache.size() : 0;
    		}

    		 // Carica le recensioni ricevute dall'utente loggato
    		 //Restituisce il numero di recensioni caricate
    		 
    		public int getNumeroRecensioniRicevute() {
    		    recensioniRicevuteCache = caricaRecensioniRicevute();
    		    return recensioniRicevuteCache != null ? recensioniRicevuteCache.size() : 0;
    		}

    		
    		 // Restituisce il punteggio di una recensione inviata dato l'indice
    		 
    		public int getPunteggioRecensioneInviata(int index) {
    		    if (recensioniInviateCache != null && index >= 0 && index < recensioniInviateCache.size()) {
    		        return recensioniInviateCache.get(index).getPunteggio();
    		    }
    		    return 0;
    		}

    		
    		 //Restituisce la data di una recensione inviata dato l'indice
    		 
    		public String getDataRecensioneInviata(int index) {
    		    if (recensioniInviateCache != null && index >= 0 && index < recensioniInviateCache.size()) {
    		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		        return sdf.format(recensioniInviateCache.get(index).getData());
    		    }
    		    return "";
    		}

    		
    		// Restituisce il commento di una recensione inviata dato l'indice
    		
    		public String getCommentoRecensioneInviata(int index) {
    		    if (recensioniInviateCache != null && index >= 0 && index < recensioniInviateCache.size()) {
    		        String commento = recensioniInviateCache.get(index).getCommento();
    		        return commento != null ? commento : "Nessun commento";
    		    }
    		    return "";
    		}

    		
    		 // Restituisce l'ID offerta di una recensione inviata dato l'indice
    		 
    		public int getIdOffertaRecensioneInviata(int index) {
    		    if (recensioniInviateCache != null && index >= 0 && index < recensioniInviateCache.size()) {
    		        return recensioniInviateCache.get(index).getIdOfferta();
    		    }
    		    return -1;
    		}

    		
    		 // Restituisce la matricola del venditore di una recensione inviata dato l'indice
    		 
    		public String getMatricolaVenditoreRecensioneInviata(int index) {
    		    if (recensioniInviateCache != null && index >= 0 && index < recensioniInviateCache.size()) {
    		        return recensioniInviateCache.get(index).getMatricolaVenditore();
    		    }
    		    return "";
    		}

    		
    		 // Restituisce il punteggio di una recensione ricevuta dato l'indice
    		 
    		public int getPunteggioRecensioneRicevuta(int index) {
    		    if (recensioniRicevuteCache != null && index >= 0 && index < recensioniRicevuteCache.size()) {
    		        return recensioniRicevuteCache.get(index).getPunteggio();
    		    }
    		    return 0;
    		}

    		
    		 //Restituisce la data di una recensione ricevuta dato l'indice
    		 
    		public String getDataRecensioneRicevuta(int index) {
    		    if (recensioniRicevuteCache != null && index >= 0 && index < recensioniRicevuteCache.size()) {
    		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		        return sdf.format(recensioniRicevuteCache.get(index).getData());
    		    }
    		    return "";
    		}

    		
    		 // Restituisce il commento di una recensione ricevuta dato l'indice
    		
    		public String getCommentoRecensioneRicevuta(int index) {
    		    if (recensioniRicevuteCache != null && index >= 0 && index < recensioniRicevuteCache.size()) {
    		        String commento = recensioniRicevuteCache.get(index).getCommento();
    		        return commento != null ? commento : "Nessun commento";
    		    }
    		    return "";
    		}

    		
    		 //Restituisce l'ID offerta di una recensione ricevuta dato l'indice
    		 
    		public int getIdOffertaRecensioneRicevuta(int index) {
    		    if (recensioniRicevuteCache != null && index >= 0 && index < recensioniRicevuteCache.size()) {
    		        return recensioniRicevuteCache.get(index).getIdOfferta();
    		    }
    		    return -1;
    		}

    		
    		 //Restituisce la matricola dell'acquirente di una recensione ricevuta dato l'indice
    		 
    		public String getMatricolaAcquirenteRecensioneRicevuta(int index) {
    		    if (recensioniRicevuteCache != null && index >= 0 && index < recensioniRicevuteCache.size()) {
    		        return recensioniRicevuteCache.get(index).getMatricolaAcquirente();
    		    }
    		    return "";
    		}

    		// NOTA: I metodi caricaRecensioniInviate() e caricaRecensioniRicevute() esistenti 
    		// rimangono INVARIATI. Sono già presenti nel tuo MainController e restituiscono ArrayList<Recensione_entity>.

}