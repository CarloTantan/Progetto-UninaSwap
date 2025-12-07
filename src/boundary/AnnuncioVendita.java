package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.util.List;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Toolkit;

public class AnnuncioVendita extends JFrame {

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
//					AnnuncioVendita frame = new AnnuncioVendita();
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
	public AnnuncioVendita(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio, String titolo, String descrizione, String modalitaConsegna,
            FasciaOraria fasciaOraria, ArrayList<String> percorsiImmagini, MainController controller) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.modalitaConsegna = modalitaConsegna;
		this.fasciaOraria = fasciaOraria;
		this.percorsiImmagini = percorsiImmagini;
		this.controller = controller;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnuncioVendita.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Vendita");
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
		ButtonAnnulla.setIcon(new ImageIcon(AnnuncioVendita.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
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
		
		JLabel lblTitolo = new JLabel("Annuncio di vendita");
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
		
		// Label Prezzo
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel lblPrezzo = new JLabel("Prezzo (€)");
		lblPrezzo.setFont(new Font("Verdana", Font.BOLD, 18));
		mainPanel.add(lblPrezzo, gbc);
		
		// TextField Prezzo
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		JTextField textFieldPrezzo = new JTextField();
		textFieldPrezzo.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldPrezzo.setPreferredSize(new Dimension(300, 40));
		mainPanel.add(textFieldPrezzo, gbc);
		
		// Bottone Pubblica
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 15, 15, 15);
		JButton ButtonPubblica = new JButton("Pubblica");
		ButtonPubblica.setForeground(Color.WHITE);
		ButtonPubblica.setBackground(new Color(0, 52, 102));
		ButtonPubblica.setFont(new Font("Verdana", Font.BOLD, 18));
		ButtonPubblica.setPreferredSize(new Dimension(200, 50));
        ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		mainPanel.add(ButtonPubblica, gbc);
		
		ButtonPubblica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldPrezzo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, 
						"Il campo prezzo è obbligatorio", 
						"Campo mancante", 
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					float prezzo = Float.parseFloat(textFieldPrezzo.getText().trim());
					
					if (prezzo <= 0) {
						JOptionPane.showMessageDialog(null, 
							"Il prezzo deve essere maggiore di zero", 
							"Prezzo non valido", 
							JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					int idAnnuncio = annuncioDAO.inserisciAnnuncioVendita(
						titolo, 
						descrizione, 
						modalitaConsegna, 
						fasciaOraria, 
						prezzo, 
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
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, 
						"Inserisci un prezzo valido", 
						"Formato non valido", 
						JOptionPane.ERROR_MESSAGE);
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