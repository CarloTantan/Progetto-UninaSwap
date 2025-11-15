package mainController;

import dao.ListaAnnunciDAO;
import dao.LoginDAO;

public class MainController {
	private ListaAnnunciDAO ListaA; 
	private LoginDAO Login; 
	//private UtenteDAO User; 
	//private OggettoDAO Oggetto;
	
	
	public MainController(ListaAnnunciDAO ListaA, LoginDAO Login) {
		this.ListaA = ListaA;
		this.Login = Login;
	}
	
	
}
