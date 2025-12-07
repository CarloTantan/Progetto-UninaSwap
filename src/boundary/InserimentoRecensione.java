package boundary;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dao.InserimentoRecensioneDAO;
import entity.Utente_entity;
import mainController.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Toolkit;

public class InserimentoRecensione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private String matricolaVenditore;
	private String matricolaAcquirente;
	private int idOfferta;
	private JTextArea textAreaCommento;
	private InserimentoRecensioneDAO recensioneDAO;
	private MainController controller;
	private JLabel[] lblStelle = new JLabel[5];
	private int punteggioSelezionato = 0;
	private ImageIcon stellaPiena;
	private ImageIcon stellaVuota;
	private ImageIcon stellaHover;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InserimentoRecensione frame = new InserimentoRecensione();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public InserimentoRecensione(Utente_entity UtenteLoggato, String matricolaAcquirente, String matricolaVenditore, int idOfferta, MainController controller) {
		this.UtenteLoggato = UtenteLoggato;
		this.matricolaAcquirente = matricolaAcquirente;
		this.matricolaVenditore = matricolaVenditore;
		this.idOfferta = idOfferta;
		this.controller = controller;
		
		// Carica le icone delle stelle
		caricaIconeStelle();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(
			InserimentoRecensione.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Scrivi la tua recensione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(900, 650));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(new Color(245, 247, 250));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// ============ HEADER ============
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
		
		JButton btnIndietro = new JButton("");
		btnIndietro.setIcon(new ImageIcon(
			InserimentoRecensione.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnIndietro.setBackground(new Color(50, 132, 188));
		btnIndietro.setPreferredSize(new Dimension(50, 50));
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBorderPainted(false);
		btnIndietro.setContentAreaFilled(false);
		
		btnIndietro.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnIndietro.setBackground(new Color(70, 152, 208));
				btnIndietro.setContentAreaFilled(true);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnIndietro.setBackground(new Color(50, 132, 188));
				btnIndietro.setContentAreaFilled(false);
			}
		});
		
		btnIndietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tornaAreaUtente();
			}
		});
		
		leftPanel.add(btnIndietro);
		headerPanel.add(leftPanel, BorderLayout.WEST);
		
		// Pannello centrale con titolo
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(50, 132, 188));
		centerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		JLabel lblTitolo = new JLabel("Lascia una Recensione");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
		centerPanel.add(lblTitolo);
		headerPanel.add(centerPanel, BorderLayout.CENTER);
		
		// ============ CONTAINER PRINCIPALE ============
		JPanel mainContainer = new JPanel();
		mainContainer.setBackground(Color.WHITE);
		mainContainer.setBorder(new EmptyBorder(50, 100, 50, 100));
		mainContainer.setLayout(new GridBagLayout());
		contentPane.add(mainContainer, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 10, 20, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// ============ SEZIONE PUNTEGGIO ============
		JLabel lblPunteggio = new JLabel("Valuta la tua esperienza");
		lblPunteggio.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPunteggio.setForeground(new Color(0, 52, 104));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		mainContainer.add(lblPunteggio, gbc);
		
		// Pannello stelle
		JPanel stellePanel = creaPannelloStelle();
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 30, 10);
		mainContainer.add(stellePanel, gbc);
		
		// Label feedback punteggio
		JLabel lblFeedbackPunteggio = new JLabel("Seleziona un punteggio da 1 a 5 stelle");
		lblFeedbackPunteggio.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblFeedbackPunteggio.setForeground(Color.GRAY);
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 30, 10);
		mainContainer.add(lblFeedbackPunteggio, gbc);
		
		// ============ SEZIONE COMMENTO ============
		JLabel lblCommento = new JLabel("Racconta la tua esperienza (opzionale)");
		lblCommento.setFont(new Font("Verdana", Font.BOLD, 18));
		lblCommento.setForeground(new Color(0, 52, 104));
		gbc.gridy = 3;
		gbc.insets = new Insets(20, 10, 10, 10);
		mainContainer.add(lblCommento, gbc);
		
		// TextArea Commento con ScrollPane
		textAreaCommento = new JTextArea();
		textAreaCommento.setForeground(new Color(0, 0, 0));
		textAreaCommento.setWrapStyleWord(true);
		textAreaCommento.setLineWrap(true);
		textAreaCommento.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaCommento.setRows(6);
		textAreaCommento.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JScrollPane scrollPane = new JScrollPane(textAreaCommento);
		scrollPane.setPreferredSize(new Dimension(600, 150));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 10, 30, 10);
		mainContainer.add(scrollPane, gbc);
		
		// ============ BOTTONE INVIA ============
		JButton btnInvia = new JButton("Invia Recensione");
		btnInvia.setFocusPainted(false);
		btnInvia.setBorderPainted(false);
		btnInvia.setForeground(Color.WHITE);
		btnInvia.setBackground(new Color(40, 167, 69));
		btnInvia.setFont(new Font("Verdana", Font.BOLD, 16));
		btnInvia.setPreferredSize(new Dimension(250, 55));
		
		btnInvia.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnInvia.setBackground(new Color(33, 136, 56));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnInvia.setBackground(new Color(40, 167, 69));
			}
		});
		
		btnInvia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inviaRecensione();
			}
		});
		
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 0;
		gbc.insets = new Insets(20, 10, 20, 10);
		mainContainer.add(btnInvia, gbc);
	}
	
	private void caricaIconeStelle() {
		try {
			ImageIcon iconaPiena = new ImageIcon(
				InserimentoRecensione.class.getResource("/icons/icons8-stella-32.png"));
			ImageIcon iconaVuota = new ImageIcon(
				InserimentoRecensione.class.getResource("/icons/icons8-stella-vuota-32.png"));
			
			// Ridimensiona le icone
			Image imgPiena = iconaPiena.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
			Image imgVuota = iconaVuota.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
			
			stellaPiena = new ImageIcon(imgPiena);
			stellaVuota = new ImageIcon(imgVuota);
			
			// Crea versione hover (leggermente più grande)
			Image imgHover = iconaPiena.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH);
			stellaHover = new ImageIcon(imgHover);
			
		} catch (Exception e) {
			System.err.println("Errore nel caricamento delle icone stelle: " + e.getMessage());
		}
	}
	
	private JPanel creaPannelloStelle() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panel.setBackground(Color.WHITE);
		
		for (int i = 0; i < 5; i++) {
			final int indice = i;
			lblStelle[i] = new JLabel(stellaVuota);
			lblStelle[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			
			// Mouse listener per hover effect
			lblStelle[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					aggiornaStelle(indice + 1, true);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					aggiornaStelle(punteggioSelezionato, false);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					punteggioSelezionato = indice + 1;
					aggiornaStelle(punteggioSelezionato, false);
				}
			});
			
			panel.add(lblStelle[i]);
		}
		
		return panel;
	}
	
	private void aggiornaStelle(int punteggio, boolean isHover) {
		for (int i = 0; i < 5; i++) {
			if (i < punteggio) {
				lblStelle[i].setIcon(isHover ? stellaHover : stellaPiena);
			} else {
				lblStelle[i].setIcon(stellaVuota);
			}
		}
	}
	
	private boolean validaCampi() {
		if (punteggioSelezionato == 0) {
			JOptionPane.showMessageDialog(this,
				"Seleziona un punteggio cliccando sulle stelle",
				"Punteggio mancante",
				JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void inviaRecensione() {
		if (!validaCampi()) {
			return;
		}
		
		String commento = textAreaCommento.getText().trim();
		
		try {
			InserimentoRecensioneDAO dao = new InserimentoRecensioneDAO();
			
			if (dao.esisteRecensione(idOfferta)) {
				JOptionPane.showMessageDialog(this,
					"Hai già inserito una recensione per questa transazione!",
					"Recensione già presente",
					JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			dao.inserisciRecensione(
				matricolaVenditore,
				matricolaAcquirente,
				punteggioSelezionato,
				commento.isEmpty() ? null : commento,
				idOfferta
			);

			JOptionPane.showMessageDialog(this,
				"Recensione inviata con successo!",
				"Successo",
				JOptionPane.INFORMATION_MESSAGE);
			
			tornaAreaUtente();

		} catch (SQLException ex) {
			ex.printStackTrace();
			
			if (ex.getMessage().contains("unique")) {
				JOptionPane.showMessageDialog(this,
					"Hai già inserito una recensione per questa transazione!",
					"Recensione duplicata",
					JOptionPane.ERROR_MESSAGE);
			} else if (ex.getMessage().contains("autorecensione")) {
				JOptionPane.showMessageDialog(this,
					"Non puoi recensire te stesso!",
					"Errore",
					JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
					"Errore durante il salvataggio della recensione: " + ex.getMessage(),
					"Errore",
					JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Errore imprevisto durante il salvataggio della recensione",
				"Errore",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void tornaAreaUtente() {
		this.dispose();
		AreaUtente areaUtenteFrame = new AreaUtente(UtenteLoggato, controller);
		areaUtenteFrame.setVisible(true);
	}
}
