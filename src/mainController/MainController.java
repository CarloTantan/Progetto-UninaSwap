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
	private ListaAnnunciDAO ListaA; 
	private LoginDAO Login; 
	
	
	public MainController(ListaAnnunciDAO ListaA, LoginDAO Login) {
		this.ListaA = ListaA;
		this.Login = Login;
	}
	
	
}
