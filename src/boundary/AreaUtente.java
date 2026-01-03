package boundary;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainController.MainController;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Classe che rappresenta l'area utente principale dell'applicazione.
 * Questa è la schermata "home" che viene mostrata dopo il login e contiene
 * tutti i bottoni per accedere alle varie funzionalità del sistema:
 **/
public class AreaUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCreaAnnuncio;
	
	
	public AreaUtente(MainController controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AreaUtente.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("AreaUtente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1100, 600));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 247, 250));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// HEADER PANEL: Pannello superiore contenente: bottone indietro, titolo, report, info utente	
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(50, 132, 188));
		headerPanel.setPreferredSize(new Dimension(0, 100));
		headerPanel.setLayout(new BorderLayout(10, 0));
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		// Pannello sinistro con pulsante back
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(50, 132, 188));
		leftPanel.setPreferredSize(new Dimension(100, 100));
		leftPanel.setBorder(new EmptyBorder(25, 15, 0, 0));
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornaAlLogin(controller);
			}
		});
		btnUndo.setIcon(new ImageIcon(AreaUtente.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBackground(new Color(50, 132, 188));
		btnUndo.setPreferredSize(new Dimension(50, 50));
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		btnUndo.setContentAreaFilled(false);
		
		// Effetto hover per il pulsante back
		btnUndo.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnUndo.setBackground(new Color(70, 152, 208));
				btnUndo.setContentAreaFilled(true);
			}
			public void mouseExited(MouseEvent evt) {
				btnUndo.setBackground(new Color(50, 132, 188));
				btnUndo.setContentAreaFilled(false);
			}
		});
		
		leftPanel.add(btnUndo);
		headerPanel.add(leftPanel, BorderLayout.WEST);
		
		// PANNELLO CENTRALE CON TITOLO 
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(50, 132, 188));
		centerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		JLabel lblTitle = new JLabel("Area Utente");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 24));
		centerPanel.add(lblTitle);
		headerPanel.add(centerPanel, BorderLayout.CENTER);
		
		// PANNELLO DESTRO CON BOTTONE REPORT E INFO UTENTE 
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(50, 132, 188));
		rightPanel.setPreferredSize(new Dimension(500, 100));
		rightPanel.setBorder(new EmptyBorder(10, 0, 0, 15));
		rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		
		// Bottone Report (PRIMA dell'icona utente)
		JButton btnReport = new JButton("Visualizza Report");
		btnReport.setIcon(new ImageIcon(AreaUtente.class.getResource("/icons/icons8-lista-48.png")));
		btnReport.setForeground(Color.WHITE);
		btnReport.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnReport.setBackground(new Color(50, 132, 188));
		btnReport.setPreferredSize(new Dimension(200, 50));
		btnReport.setFocusPainted(false);
		btnReport.setBorderPainted(false);
		
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriReport(controller);
			}
		});
		
		// Effetto hover per il bottone report
		btnReport.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnReport.setBackground(new Color(70, 152, 208));
			}
			public void mouseExited(MouseEvent evt) {
				btnReport.setBackground(new Color(50, 132, 188));
			}
		});
		
		rightPanel.add(btnReport);
		
		// Icona e nome utente (DOPO il bottone report)
		JLabel lblIconaUtente = new JLabel("");
		lblIconaUtente.setIcon(new ImageIcon(AreaUtente.class.getResource("/icons/icons8-utente-uomo-cerchiato-96.png")));
		rightPanel.add(lblIconaUtente);
		
		JLabel lblNomeUtente = new JLabel(controller.getNominativoUtenteLoggato());
		lblNomeUtente.setForeground(Color.WHITE);
		lblNomeUtente.setFont(new Font("Verdana", Font.BOLD, 16));
		rightPanel.add(lblNomeUtente);
		
		headerPanel.add(rightPanel, BorderLayout.EAST);
		//  CONTAINER PRINCIPALE PER I PULSANTI 
		JPanel mainContainer = new JPanel();
		mainContainer.setBackground(new Color(245, 247, 250));
		mainContainer.setBorder(new EmptyBorder(40, 80, 40, 80));
		mainContainer.setLayout(new BorderLayout());
		contentPane.add(mainContainer, BorderLayout.CENTER);
		
		// Button Panel con GridBagLayout
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(245, 247, 250));
		GridBagLayout gbl = new GridBagLayout();
		buttonPanel.setLayout(gbl);
		mainContainer.add(buttonPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		// Prima riga
		gbc.gridx = 0;
		gbc.gridy = 0;
		JButton btnVisualizzaStoricoOfferte = createStyledButton("Visualizza Storico Offerte", 
			"/icons/icons8-lista-48.png");
		btnVisualizzaStoricoOfferte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriStoricoOfferte(controller);
				
			}
		});
		buttonPanel.add(btnVisualizzaStoricoOfferte, gbc);
		
		gbc.gridx = 1;
		JButton btnInserisciRecensione = createStyledButton("Inserisci Recensione", 
			"/icons/icons8-aggiungi-48.png");
		btnInserisciRecensione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriListaTransazioni(controller);
				
			}
		});
		buttonPanel.add(btnInserisciRecensione, gbc);
		
		// Seconda riga
		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton btnVisualizzaOfferteRicevute = createStyledButton("Visualizza Offerte Ricevute", 
			"/icons/icons8-lista-48.png");
		btnVisualizzaOfferteRicevute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriAnnunciPubblicati(controller);
				
			}
		});
		buttonPanel.add(btnVisualizzaOfferteRicevute, gbc);
		
		gbc.gridx = 1;
		btnCreaAnnuncio = createStyledButton("Crea Annuncio", "/icons/icons8-aggiungi-48.png");
		btnCreaAnnuncio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriOggetto(controller);
				
			}
		});
		buttonPanel.add(btnCreaAnnuncio, gbc);
		
		// Terza riga
		gbc.gridx = 0;
		gbc.gridy = 2;
		JButton btnVisualizzaRecensioni = createStyledButton("Visualizza Recensioni", 
			"/icons/icons8-lista-48.png");
		btnVisualizzaRecensioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriListaRecensioni(controller);
				
			}
		});
		buttonPanel.add(btnVisualizzaRecensioni, gbc);
		
		gbc.gridx = 1;
		JButton btnVisualizzaAnnuncio = createStyledButton("Visualizza Annuncio", 
			"/icons/icons8-lista-48.png");
		btnVisualizzaAnnuncio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriListaAnnunci(controller);
				
			}
		});
		buttonPanel.add(btnVisualizzaAnnuncio, gbc);
		
		
	}

	 // Metodo helper per creare bottoni con uno stile uniforme
	private JButton createStyledButton(String text, String iconPath) {
		JButton button = new JButton(text);
		try {
			button.setIcon(new ImageIcon(AreaUtente.class.getResource(iconPath)));
		} catch (Exception e) {
			// Icona non trovata, continua senza
		}
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Verdana", Font.BOLD, 20));
		button.setBackground(new Color(0, 52, 104));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(300, 120));
		button.setMaximumSize(new Dimension(400, 140));
		
		// Aggiungi effetto hover
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				button.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(MouseEvent evt) {
				button.setBackground(new Color(0, 52, 104));
			}
		});
		
		return button;
	}
	
//	  Metodo che gestisce il ritorno alla schermata di login (logout).
  
	 
	private void tornaAlLogin(MainController controller) {
		controller.apriLogin();
	}
	
	//  Metodo che apre la schermata dei report statistici dell'utente.
	 
	
	private void apriReport(MainController controller) {
		controller.apriReport();
	}
	
	
	 // Metodo che apre la schermata dello storico delle offerte effettuate.
	 
	 private void apriStoricoOfferte(MainController controller) {
		 controller.apriStoricoOfferte();
	}
	 //Metodo che apre la schermata della lista delle transazioni completate.
	 
	private void apriListaTransazioni(MainController controller) {
		controller.apriListaTransazioni();
	}
	
	//  Metodo che apre la schermata degli annunci pubblicati dall'utente.
	 
	
	private void apriAnnunciPubblicati(MainController controller) {
		controller.apriAnnunciPubblicati();
	}
	
	//Metodo che apre la schermata di creazione di un nuovo oggetto.
	private void apriOggetto(MainController controller) {
		controller.apriInserimentoOggetto();
	}
	 // Metodo che apre la schermata delle recensioni ricevute.
	 
	private void apriListaRecensioni(MainController controller) {
		controller.apriListaRecensioni();
	}
	
	//  Metodo che apre la schermata della lista di tutti gli annunci disponibili.
	 private void apriListaAnnunci(MainController controller) {
		 controller.apriListaAnnunci();
	}
}