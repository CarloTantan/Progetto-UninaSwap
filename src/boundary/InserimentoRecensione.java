package boundary;

import java.awt.EventQueue;

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
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Toolkit;

public class InserimentoRecensione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private String matricolaVenditore;
	private String matricolaAcquirente;
	private int idOfferta;
	private JComboBox<String> comboBoxPunteggi;
    private JTextArea textAreaCommento;
    private InserimentoRecensioneDAO recensioneDAO;
    private MainController controller;

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
	    
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoRecensione.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Scrivi la tua recensione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(800, 600));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(new Color(245, 247, 250));
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
		
		JButton btnIndietro = new JButton("");
		btnIndietro.setIcon(new ImageIcon(InserimentoRecensione.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnIndietro.setBackground(new Color(50, 132, 188));
		btnIndietro.setPreferredSize(new Dimension(50, 50));
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBorderPainted(false);
		btnIndietro.setContentAreaFilled(false);
		
		// Effetto hover
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
		JLabel lblTitolo = new JLabel("Lascia una recensione");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
		centerPanel.add(lblTitolo);
		headerPanel.add(centerPanel, BorderLayout.CENTER);
		
		// Container principale con padding
		JPanel mainContainer = new JPanel();
		mainContainer.setBackground(new Color(245, 247, 250));
		mainContainer.setBorder(new EmptyBorder(50, 100, 50, 100));
		mainContainer.setLayout(new GridBagLayout());
		contentPane.add(mainContainer, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 10, 15, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Label Punteggio
		JLabel lblPunteggio = new JLabel("Punteggio");
		lblPunteggio.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0.3;
		mainContainer.add(lblPunteggio, gbc);
		
		// ComboBox Punteggio
		String[] punteggi = {"Seleziona punteggio", "1", "2", "3", "4", "5"};
		comboBoxPunteggi = new JComboBox<>(punteggi);
		comboBoxPunteggi.setForeground(new Color(0, 0, 0));
		comboBoxPunteggi.setBackground(new Color(255, 255, 255));
		comboBoxPunteggi.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxPunteggi.setPreferredSize(new Dimension(300, 40));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.7;
		mainContainer.add(comboBoxPunteggi, gbc);
		
		// Label Commento
		JLabel lblCommento = new JLabel("Commento");
		lblCommento.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 0.3;
		mainContainer.add(lblCommento, gbc);
		
		// TextArea Commento con ScrollPane
		textAreaCommento = new JTextArea();
		textAreaCommento.setForeground(new Color(0, 0, 0));
		textAreaCommento.setWrapStyleWord(true);
		textAreaCommento.setLineWrap(true);
		textAreaCommento.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaCommento.setRows(8);
		
		JScrollPane scrollPane = new JScrollPane(textAreaCommento);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.7;
		gbc.weighty = 1.0;
		mainContainer.add(scrollPane, gbc);
		
		// Bottone Invia
		JButton btnInvia = new JButton("Invia");
		btnInvia.setFocusPainted(false);
		btnInvia.setBorderPainted(false);
		btnInvia.setForeground(new Color(255, 255, 255));
		btnInvia.setBackground(new Color(0, 52, 104));
		btnInvia.setFont(new Font("Verdana", Font.BOLD, 16));
		btnInvia.setPreferredSize(new Dimension(200, 50));
		
		// Effetto hover
		btnInvia.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnInvia.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnInvia.setBackground(new Color(0, 52, 104));
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 0;
		gbc.insets = new Insets(30, 10, 20, 10);
		mainContainer.add(btnInvia, gbc);
        
		btnInvia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inviaRecensione();
			}
		});
	}
	
	// Validazione campi
	private boolean validaCampi() {
		// Verifica che sia selezionato un punteggio
		if (comboBoxPunteggi.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this,
				"Seleziona un punteggio",
				"Punteggio mancante",
				JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	// Invia la recensione al database
	private void inviaRecensione() {
		// Validazione campi
		if (!validaCampi()) {
			return;
		}
		
		// Recupera i valori dai campi
		int punteggio = Integer.parseInt(comboBoxPunteggi.getSelectedItem().toString());
		String commento = textAreaCommento.getText().trim();
		
		try {
			InserimentoRecensioneDAO InserimentoRecensioneDAO = new InserimentoRecensioneDAO();
			
			// Verifica se esiste già una recensione per questa offerta
			if (InserimentoRecensioneDAO.esisteRecensione(idOfferta)) {
				JOptionPane.showMessageDialog(this,
					"Hai già inserito una recensione per questa transazione!",
					"Recensione già presente",
					JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Inserisci la recensione
			InserimentoRecensioneDAO.inserisciRecensione(
				matricolaVenditore,
				matricolaAcquirente,
				punteggio,
				commento,
				idOfferta
			);

			// Mostra messaggio di successo
			JOptionPane.showMessageDialog(this,
				"Recensione inviata con successo!",
				"Successo",
				JOptionPane.INFORMATION_MESSAGE);
			
			// Torna all'area utente
			tornaAreaUtente();

		} catch (SQLException ex) {
			ex.printStackTrace();
			
			// Gestione errori specifici
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
