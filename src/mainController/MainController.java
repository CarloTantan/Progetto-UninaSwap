package mainController;

import dao.Annunci_OfferteDAO;
import dao.FotoAnnuncioDAO; 
import dao.InserimentoAnnunciDAO;
import dao.InserimentoRecensioneDAO; 
import dao.ListaAnnunciDAO;
import dao.ListaRecensioniDao; 
import dao.LoginDAO;
import dao.OffertaDAO; 
import dao.OggettoDAO; 
import dao.RecensioneVenditoreDAO; 
import dao.RegistrazioneDAO;
import dao.ReportDAO;
import dao.StoricoOfferteDAO; 
import dao.TransazioniDAO;

public class MainController {
	protected Annunci_OfferteDAO Annunci_Offerte; 
	protected FotoAnnuncioDAO FotoAnnuncio;
	protected InserimentoAnnunciDAO InsertAnnuncio;
	protected InserimentoRecensioneDAO InsertRecensione; 
	protected ListaAnnunciDAO ListaA; 
	protected ListaRecensioniDao ListaR; 
	protected LoginDAO Login; 
	protected OffertaDAO Offerta; 
	protected OggettoDAO Oggetto; 
	protected RecensioneVenditoreDAO RecensioneV; 
	protected RegistrazioneDAO Registrazione;
	protected ReportDAO Report;
	protected StoricoOfferteDAO StoricoOfferte; 
	protected TransazioniDAO Transazioni;
	
	
	public MainController(ListaAnnunciDAO ListaA, LoginDAO Login) {
		this.ListaA = ListaA;
		this.Login = Login;
	}
	
	
}
