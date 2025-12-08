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

public class AreaUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCreaAnnuncio;

	
	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				//try {
		//			AreaUtente frame = new AreaUtente();
		//			frame.setVisible(true);
	//				frame.setLocationRelativeTo(null);
	//		} catch (Exception e) {
	//				e.printStackTrace();
		//		}
	//		}
//	});
//}

	/**
	 * Create the frame.
	 */
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
		
		// Header Panel
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
		btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnUndo.setBackground(new Color(70, 152, 208));
				btnUndo.setContentAreaFilled(true);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnUndo.setBackground(new Color(50, 132, 188));
				btnUndo.setContentAreaFilled(false);
			}
		});
		
		leftPanel.add(btnUndo);
		headerPanel.add(leftPanel, BorderLayout.WEST);
		
		// Pannello centrale con titolo
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(50, 132, 188));
		centerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		JLabel lblTitle = new JLabel("Area Utente");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 24));
		centerPanel.add(lblTitle);
		headerPanel.add(centerPanel, BorderLayout.CENTER);
		
		// Pannello destro con bottone report e info utente
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
		btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnReport.setBackground(new Color(70, 152, 208));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
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
		
		// Container principale per i pulsanti con padding
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
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 52, 104));
			}
		});
		
		return button;
	}
	
	private void tornaAlLogin(MainController controller) {
		dispose();
		Login LoginFrame = new Login(controller);
		LoginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		LoginFrame.setVisible(true);
	}
	
	private void apriReport(MainController controller) {
		dispose();
		Report ReportFrame = new Report(controller);
		ReportFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ReportFrame.setVisible(true);
	}
	
	private void apriStoricoOfferte(MainController controller) {
		dispose();
		StoricoOfferte StoricoOfferteFrame = new StoricoOfferte(controller);
		StoricoOfferteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		StoricoOfferteFrame.setVisible(true);
	}
	
	private void apriListaTransazioni(MainController controller) {
		dispose();
		ListaTransazioni ListaTransazioniFrame = new ListaTransazioni(controller);
		ListaTransazioniFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		ListaTransazioniFrame.setVisible(true);
	}
	
	private void apriAnnunciPubblicati(MainController controller) {
		dispose();
		AnnunciPubblicati AnnunciPubblicatiFrame = new AnnunciPubblicati(controller);
		AnnunciPubblicatiFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		AnnunciPubblicatiFrame.setVisible(true);
	}
	
	private void apriOggetto(MainController controller) {
		dispose();
		Oggetto OggettoFrame = new Oggetto(controller);
		OggettoFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		OggettoFrame.setVisible(true);
	}
	
	private void apriListaRecensioni(MainController controller) {
		dispose();
		ListaRecensioni ListaRecensioniFrame = new ListaRecensioni(controller);
		ListaRecensioniFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		ListaRecensioniFrame.setVisible(true);
	}
	
	private void apriListaAnnunci(MainController controller) {
		dispose();
		ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(controller);
		ListaAnnunciFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		ListaAnnunciFrame.setVisible(true);
	}
}