package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dao.FotoAnnuncioDAO;
import dao.InserimentoAnnunciDAO;
import entity.Oggetto_entity;
import entity.Utente_entity;
import enumerations.FasciaOraria;
import mainController.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Toolkit;

public class AnnuncioScambio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private Oggetto_entity OggettoAnnuncio;
	private String titolo;
	private String descrizione;
	private String modalitaConsegna;
	private FasciaOraria fasciaOraria;
	private ArrayList<String> percorsiImmagini;	
	private MainController controller;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AnnuncioScambio frame = new AnnuncioScambio();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AnnuncioScambio(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio,
            String titolo, String descrizione, String modalitaConsegna,
            FasciaOraria fasciaOraria, ArrayList<String> percorsiImmagini, MainController controller) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.modalitaConsegna = modalitaConsegna;
		this.fasciaOraria = fasciaOraria;
		this.percorsiImmagini = percorsiImmagini;		
		this.controller = controller;
		
		setTitle("Scambio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnuncioScambio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(800, 600));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 247, 250));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		// Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(45, 134, 192));
		headerPanel.setPreferredSize(new Dimension(0, 80));
		headerPanel.setLayout(new BorderLayout());
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setIcon(new ImageIcon(AnnuncioScambio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		ButtonAnnulla.setPreferredSize(new Dimension(60, 80));
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(UtenteLoggato, OggettoAnnuncio, controller); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(45, 134, 192));
		leftPanel.add(ButtonAnnulla);
		headerPanel.add(leftPanel, BorderLayout.WEST);
		
		JLabel lblTitolo = new JLabel("Annuncio di scambio");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
		JPanel centerHeaderPanel = new JPanel();
		centerHeaderPanel.setBackground(new Color(45, 134, 192));
		centerHeaderPanel.setBorder(new EmptyBorder(25, 0, 0, 0));
		centerHeaderPanel.add(lblTitolo);
		headerPanel.add(centerHeaderPanel, BorderLayout.CENTER);
		
		// Main Content Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(245, 247, 250));
		mainPanel.setBorder(new EmptyBorder(60, 100, 60, 100));
		mainPanel.setLayout(new GridBagLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Label Oggetto richiesto
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel lblOggettoRichiesto = new JLabel("Oggetto richiesto");
		lblOggettoRichiesto.setFont(new Font("Verdana", Font.BOLD, 18));
		mainPanel.add(lblOggettoRichiesto, gbc);
		
		// TextArea Oggetto richiesto
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		JTextArea textAreaOggettoRichiesto = new JTextArea();
		textAreaOggettoRichiesto.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaOggettoRichiesto.setLineWrap(true);
		textAreaOggettoRichiesto.setWrapStyleWord(true);
		textAreaOggettoRichiesto.setRows(6);
		JScrollPane scrollPane = new JScrollPane(textAreaOggettoRichiesto);
		scrollPane.setPreferredSize(new Dimension(500, 150));
		mainPanel.add(scrollPane, gbc);
		
		// Bottone Pubblica
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 15, 15, 15);
		JButton ButtonPubblica = new JButton("Pubblica");
		ButtonPubblica.setBackground(new Color(0, 52, 102));
		ButtonPubblica.setForeground(Color.WHITE);
		ButtonPubblica.setFont(new Font("Verdana", Font.BOLD, 18));
		ButtonPubblica.setPreferredSize(new Dimension(200, 50));
		ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		mainPanel.add(ButtonPubblica, gbc);
		
		ButtonPubblica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textAreaOggettoRichiesto.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, 
						"Il campo oggetto richiesto è obbligatorio", 
						"Campo mancante", 
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					String oggettoRichiesto = textAreaOggettoRichiesto.getText().trim();
					
					InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					int idAnnuncio = annuncioDAO.inserisciAnnuncioScambio(
						titolo, 
						descrizione, 
						modalitaConsegna, 
						fasciaOraria, 
						oggettoRichiesto, 
						UtenteLoggato.getMatricola(), 
						OggettoAnnuncio.getIdOggetto()
					);
					
					FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
					for (String percorsoImg : percorsiImmagini) {
						fotoDAO.inserisciFoto(percorsoImg, idAnnuncio);
					}
					
					setVisible(false);
					JOptionPane.showMessageDialog(null, 
						"Pubblicazione avvenuta con successo", 
						"Annuncio pubblicato", 
						JOptionPane.INFORMATION_MESSAGE);
					AreaUtente utenteFrame = new AreaUtente(UtenteLoggato, controller); 
					utenteFrame.setVisible(true);
					
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, 
						"Errore durante la pubblicazione: " + ex.getMessage(), 
						"Errore Database", 
						JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		// Hover effect
		ButtonPubblica.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ButtonPubblica.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				ButtonPubblica.setBackground(new Color(0, 52, 102));
			}
		});
	}
}