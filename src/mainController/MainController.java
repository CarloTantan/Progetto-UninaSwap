package mainController;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.File;
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
    protected InserimentoRecensioneDAO InserimentoRecensioneDAO;
    protected ArrayList<AnnuncioVendita_entity> annunciVenditaCaricati;
    protected ArrayList<AnnuncioScambio_entity> annunciScambioCaricati;
    protected ArrayList<AnnuncioRegalo_entity> annunciRegaloCaricati;

    protected Oggetto_entity OggettoAnnuncio;
    protected String titoloAnnuncio;
    protected String descrizioneAnnuncio;
    protected String modalitaConsegnaAnnuncio;
    protected FasciaOraria fasciaOrariaAnnuncio;
    protected ArrayList<String> percorsiImmaginiAnnuncio;

    protected Oggetto_entity UltimoOggettoCreato;

    public MainController() {
        this.LoginDAO = new LoginDAO();           
        this.RegistrazioneDAO = new RegistrazioneDAO();
        this.InsertAnnunciDAO = new InserimentoAnnunciDAO();
        this.FotoAnnuncioDAO = new FotoAnnuncioDAO(); 
        this.OffertaDAO = new OffertaDAO();
        this.InserimentoRecensioneDAO = new InserimentoRecensioneDAO();
    }

    // ==================== METODI LOGIN ====================
    
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

    // ==================== METODI REGISTRAZIONE ====================
    
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

    // ==================== METODI TRANSAZIONI ====================
    
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

    // ==================== METODI OFFERTE ====================
    
    public String verificaOfferta(int idAnnuncio, String matricolaVenditore, StatoAnnuncio stato, String tipologia) {
        try {
            if (stato == StatoAnnuncio.Chiuso) {
                return "Annuncio chiuso, non è possibile fare un'offerta.";
            }

            if (matricolaVenditore.equals(User.getMatricola())) {
                return "Non puoi fare un'offerta sul tuo annuncio.";
            }

            return null;

        } catch (Exception e) {
            return "Si è verificato un errore: " + e.getMessage();
        }
    }

    public String InviaOffertaRegalo(String messaggioMotivazionale, String matricolaAcquirente, int idAnnuncio) {
        if (messaggioMotivazionale == null || messaggioMotivazionale.trim().isEmpty()) {
            return "Il messaggio motivazionale è obbligatorio";
        }
        
        if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
            return "La matricola dell'acquirente è obbligatoria";
        }
        
        if (!matricolaAcquirente.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
        }
        
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

    public String AggiornaOffertaRegalo(String messaggioMotivazionale, String matricolaAcquirente, int idAnnuncio, int idOfferta) {
        if (messaggioMotivazionale == null || messaggioMotivazionale.trim().isEmpty()) {
            return "Il messaggio motivazionale è obbligatorio";
        }
        
        if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
            return "La matricola dell'acquirente è obbligatoria";
        }
        
        if (!matricolaAcquirente.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
        }
        
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

    public OffertaRegalo_entity CaricaOfferta(int idOfferta) {
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

    public String InviaOffertaScambio(String oggettoProposto, String matricolaAcquirente, int idAnnuncio) {
        if (oggettoProposto == null || oggettoProposto.trim().isEmpty()) {
            return "L'oggetto proposto è obbligatorio";
        }
        
        if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
            return "La matricola dell'acquirente è obbligatoria";
        }
        
        if (!matricolaAcquirente.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
        }
        
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

    public String AggiornaOffertaScambio(String oggettoProposto, String matricolaAcquirente, int idAnnuncio, int idOfferta) {
        if (oggettoProposto == null || oggettoProposto.trim().isEmpty()) {
            return "L'oggetto proposto è obbligatorio";
        }
        
        if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
            return "La matricola dell'acquirente è obbligatoria";
        }
        
        if (!matricolaAcquirente.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
        }
        
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

    public OffertaScambio_entity CaricaOffertaScambio(int idOfferta) {
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

    public String InviaOffertaVendita(float importoProposto, String matricolaAcquirente, int idAnnuncio) {
        if (importoProposto <= 0) {
            return "L'importo proposto deve essere maggiore di zero";
        }
        
        if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
            return "La matricola dell'acquirente è obbligatoria";
        }
        
        if (!matricolaAcquirente.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
        }
        
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

    public String AggiornaOffertaVendita(float importoProposto, String matricolaAcquirente, int idAnnuncio, int idOfferta) {
        if (importoProposto <= 0) {
            return "L'importo proposto deve essere maggiore di zero";
        }
        
        if (matricolaAcquirente == null || matricolaAcquirente.trim().isEmpty()) {
            return "La matricola dell'acquirente è obbligatoria";
        }
        
        if (!matricolaAcquirente.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
        }
        
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

    public OffertaVendita_entity CaricaOffertaVendita(int idOfferta) {
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

    // ==================== METODI ANNUNCI ====================
    
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

    public ArrayList<AnnuncioVendita_entity> getAnnunciVenditaCaricati() {
        return annunciVenditaCaricati != null ? annunciVenditaCaricati : new ArrayList<>();
    }

    public ArrayList<AnnuncioScambio_entity> getAnnunciScambioCaricati() {
        return annunciScambioCaricati != null ? annunciScambioCaricati : new ArrayList<>();
    }

    public ArrayList<AnnuncioRegalo_entity> getAnnunciRegaloCaricati() {
        return annunciRegaloCaricati != null ? annunciRegaloCaricati : new ArrayList<>();
    }

    public String InserimentoAnnuncioScambio(String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, String oggettoRichiesto, String matricolaVenditore, int idOggetto, List<String> percorsiImmagini) {
        if (titolo == null || titolo.trim().isEmpty() ||
            descrizione == null || descrizione.trim().isEmpty() ||
            modalitaConsegna == null || modalitaConsegna.trim().isEmpty() ||
            fasciaOraria == null ||
            oggettoRichiesto == null || oggettoRichiesto.trim().isEmpty() ||
            matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {

            return "Tutti i campi sono obbligatori";
        }

        if (!matricolaVenditore.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
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

        if (!matricolaVenditore.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
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

            return "Tutti i campi sono obbligatori e il prezzo deve essere maggiore di zero";
        }

        if (!matricolaVenditore.matches("[0-9]{10}")) {
            return "La matricola deve contenere 10 cifre numeriche";
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

    public void impostaInformazioniAnnuncio(Oggetto_entity oggetto, String titolo, String descrizione, String modalitaConsegna, FasciaOraria fasciaOraria, ArrayList<String> percorsiImmagini) {
        this.OggettoAnnuncio = oggetto;
        this.titoloAnnuncio = titolo;
        this.descrizioneAnnuncio = descrizione;
        this.modalitaConsegnaAnnuncio = modalitaConsegna;
        this.fasciaOrariaAnnuncio = fasciaOraria;
        this.percorsiImmaginiAnnuncio = percorsiImmagini;
    }

    // ==================== METODI OGGETTI ====================
    
    public String InserisciOggetto(String nome, String descrizione, String categoriaSelezionata) {
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
            TipologiaCategoria categoria = TipologiaCategoria.fromNome(categoriaSelezionata);
            int idCategoria = categoria.getId();
            
            OggettoDAO oggettoDAO = new OggettoDAO();
            int idOggetto = oggettoDAO.inserisciOggetto(nome, descrizione, idCategoria);
            
            if (idOggetto <= 0) {
                return "Impossibile inserire l'oggetto";
            }
            
            this.UltimoOggettoCreato = new Oggetto_entity(idOggetto, nome, descrizione, idCategoria);
            
            return "Oggetto inserito con successo";
            
        } catch (IllegalArgumentException ex) {
            return "Categoria non valida";
        } catch (SQLException ex) {
            return "Errore durante l'inserimento dell'oggetto: " + ex.getMessage();
        }
    }

    public Oggetto_entity getUltimoOggettoCreato() {
        return UltimoOggettoCreato;
    }

    public Oggetto_entity getOggettoAnnuncio() {
        return OggettoAnnuncio;
    }

    // ==================== METODI RECENSIONI ====================
    
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

    public String getTitoloAnnuncioDaOfferta(int idOfferta) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getTitoloAnnuncioDaOfferta(idOfferta);
        } catch (SQLException e) {
            System.err.println("Errore nel recupero titolo annuncio: " + e.getMessage());
            return null;
        }
    }

    public String getNominativoUtente(String matricola) {
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            return dao.getNominativoUtente(matricola);
        } catch (SQLException e) {
            System.err.println("Errore nel recupero nominativo utente: " + e.getMessage());
            return null;
        }
    }
    
    public String inserisciRecensione(String matricolaRecensore, String matricolaRecensito, 
            int idOfferta, int valutazione, String commento) {
    	if (valutazione < 1 || valutazione > 5) {
    		return "La valutazione deve essere tra 1 e 5";
    	}

    	if (commento == null || commento.trim().isEmpty()) {
    		return "Il commento è obbligatorio";
    	}

    	try {
    		// Verifica se esiste già una recensione
    		if (InserimentoRecensioneDAO.esisteRecensione(idOfferta)) {
    			return "Hai già inserito una recensione per questa transazione";
    		}

    		// Inserisci la recensione
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

    
 // ==================== METODI TRANSAZIONI (ACCESSO AI DATI) ====================

    private ArrayList<Transazione_entity> transazioniCaricate;

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


    // ==================== METODI FOTO E IMMAGINI ====================
    
    public ArrayList<String> getFotoAnnuncio(int idAnnuncio) {
        try {
            FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
            return fotoDAO.getFotoByAnnuncio(idAnnuncio);
        } catch (SQLException e) {
            System.err.println("Errore nel caricamento delle foto: " + e.getMessage());
            return null;
        }
    }

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

    // ==================== GETTER UTENTE LOGGATO ====================
    
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
           
           // Gestisci errori specifici
           if (errorMessage != null && errorMessage.contains("Impossibile ritirare un'offerta accettata")) {
               return "Non puoi ritirare un'offerta già accettata";
           }
           
           return "Errore durante il ritiro dell'offerta: " + errorMessage;
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
        
        // Logica di business tramite DAO
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
        
        // Logica di business tramite DAO
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

    public String caricaOffertaRegaloPerModifica(int idOfferta) {
        if (idOfferta <= 0) {
            return "ID offerta non valido";
        }
        
        try {
            OffertaDAO offertaDAO = new OffertaDAO();
            OffertaRegalo_entity offerta = offertaDAO.caricaOfferta(idOfferta);
            
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
        
        // Logica di business tramite DAO
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

    public String aggiornaOffertaScambio(String oggettoProposto, int idAnnuncio, int idOfferta) {
        // Validazione input
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
        
        // Logica di business tramite DAO
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
        
        // Logica di business tramite DAO
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
            return "Errore durante l'invio dell'offerta: " + e.getMessage();
        }
    }

    public String aggiornaOffertaVendita(String importoPropostoString, int idAnnuncio, int idOfferta) {
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
        
        if (idAnnuncio <= 0 || idOfferta <= 0) {
            return "ID annuncio o offerta non validi";
        }
        
        // Logica di business tramite DAO
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
}