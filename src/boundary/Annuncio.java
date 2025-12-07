package boundary;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entity.Oggetto_entity;
import entity.Utente_entity;
import enumerations.FasciaOraria;
import mainController.MainController;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JTree;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class Annuncio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private Oggetto_entity OggettoAnnuncio;
	private ArrayList<String> PercorsiImmagini; // Lista per memorizzare i percorsi delle immagini
	private DefaultListModel<String> listModelFoto;
	private String percorsoImg;
	private JList<String> listFoto;
	private MainController controller;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Annuncio frame = new Annuncio();
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
	public Annuncio(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio, MainController controller) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		this.controller = controller;
		this.PercorsiImmagini = new ArrayList<>();
		this.listModelFoto = new DefaultListModel<>();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Annuncio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Crea la tua inserzione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(900, 700));
		
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
		ButtonAnnulla.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		ButtonAnnulla.setPreferredSize(new Dimension(60, 80));
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				AreaUtente utenteFrame = new AreaUtente(UtenteLoggato, controller); 
				utenteFrame.setVisible(true);
			}
		});
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(45, 134, 192));
		leftPanel.add(ButtonAnnulla);
		headerPanel.add(leftPanel, BorderLayout.WEST);
		
		JLabel lblTitolo = new JLabel("Crea il tuo annuncio");
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
		mainPanel.setBorder(new EmptyBorder(40, 80, 40, 80));
		mainPanel.setLayout(new GridBagLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Sezione Caricamento Foto
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel lblFoto = new JLabel("Foto dell'oggetto");
		lblFoto.setFont(new Font("Verdana", Font.BOLD, 16));
		mainPanel.add(lblFoto, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		JButton ButtonImgOggetto = new JButton("Carica immagine");
		ButtonImgOggetto.setForeground(Color.WHITE);
		ButtonImgOggetto.setBackground(new Color(0, 52, 102));
		ButtonImgOggetto.setFont(new Font("Verdana", Font.BOLD, 14));
		ButtonImgOggetto.setFocusPainted(false);
		ButtonImgOggetto.setBorderPainted(false);
		ButtonImgOggetto.setPreferredSize(new Dimension(200, 40));
		ButtonImgOggetto.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				caricaImmagini();
		        }
		});
		mainPanel.add(ButtonImgOggetto, gbc);
		
		// Lista foto
		listFoto = new JList<>(listModelFoto);
		listFoto.setFont(new Font("Verdana", Font.PLAIN, 12));
		JScrollPane scrollPaneFoto = new JScrollPane(listFoto);
		scrollPaneFoto.setPreferredSize(new Dimension(300, 80));
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(scrollPaneFoto, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		JButton ButtonRimuoviFoto = new JButton("Rimuovi foto selezionata");
		ButtonRimuoviFoto.setForeground(Color.WHITE);
		ButtonRimuoviFoto.setBackground(new Color(0, 52, 102));
		ButtonRimuoviFoto.setFont(new Font("Verdana", Font.PLAIN, 12));
		ButtonRimuoviFoto.setFocusPainted(false);
		ButtonRimuoviFoto.setBorderPainted(false);
		ButtonRimuoviFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = listFoto.getSelectedIndex();
				if (selectedIndex != -1) {
					PercorsiImmagini.remove(selectedIndex);
					listModelFoto.remove(selectedIndex);
				}
			}
		});
		mainPanel.add(ButtonRimuoviFoto, gbc);
		
		// Titolo
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		JLabel lblTitoloAnnuncio = new JLabel("Titolo");
		lblTitoloAnnuncio.setFont(new Font("Verdana", Font.BOLD, 16));
		mainPanel.add(lblTitoloAnnuncio, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		JTextField textfieldTitolo = new JTextField();
		textfieldTitolo.setFont(new Font("Verdana", Font.PLAIN, 14));
		textfieldTitolo.setPreferredSize(new Dimension(300, 35));
		mainPanel.add(textfieldTitolo, gbc);
		
		// Descrizione
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setFont(new Font("Verdana", Font.BOLD, 16));
		mainPanel.add(lblDescrizione, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.5;
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaDescrizione.setLineWrap(true);
		textAreaDescrizione.setWrapStyleWord(true);
		textAreaDescrizione.setRows(4);
		JScrollPane scrollPane = new JScrollPane(textAreaDescrizione);
		scrollPane.setPreferredSize(new Dimension(300, 100));
		mainPanel.add(scrollPane, gbc);
		
		// Fascia Oraria
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel lblFasciaOraria = new JLabel("Fascia Oraria");
		lblFasciaOraria.setFont(new Font("Verdana", Font.BOLD, 16));
		mainPanel.add(lblFasciaOraria, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.weightx = 1.0;
		String[] fasciaOraria = {"Seleziona una fascia oraria", "8:00 - 10:00", "10:00 - 12:00","12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00", "20:00 - 22:00"};
		JComboBox<String> comboBoxFasciaOraria = new JComboBox<>(fasciaOraria);
		comboBoxFasciaOraria.setFont(new Font("Verdana", Font.PLAIN, 14));
		comboBoxFasciaOraria.setPreferredSize(new Dimension(300, 35));
		mainPanel.add(comboBoxFasciaOraria, gbc);
		
		// Modalità di consegna
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weightx = 0;
		JLabel lblModConsegna = new JLabel("Modalità di consegna");
		lblModConsegna.setFont(new Font("Verdana", Font.BOLD, 16));
		mainPanel.add(lblModConsegna, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weightx = 1.0;
		JTextField textAreaModConsegna = new JTextField();
		textAreaModConsegna.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaModConsegna.setPreferredSize(new Dimension(300, 35));
		mainPanel.add(textAreaModConsegna, gbc);
		
		// Panel bottoni azione
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(30, 10, 10, 10);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(245, 247, 250));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		JButton JScambio = new JButton("Scambio");
		JScambio.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-exchange-32.png")));
		JScambio.setForeground(Color.WHITE);
		JScambio.setBackground(new Color(0, 52, 102));
		JScambio.setFont(new Font("Verdana", Font.BOLD, 16));
		JScambio.setPreferredSize(new Dimension(180, 50));
		JScambio.setFocusPainted(false);
		JScambio.setBorderPainted(false);
		
		JButton JRegalo = new JButton("Regalo");
		JRegalo.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/—Pngtree—gift icon_7390276.png")));
		JRegalo.setForeground(Color.WHITE);
		JRegalo.setBackground(new Color(0, 52, 102));
		JRegalo.setFont(new Font("Verdana", Font.BOLD, 16));
		JRegalo.setPreferredSize(new Dimension(180, 50));
		JRegalo.setFocusPainted(false);
		JRegalo.setBorderPainted(false);
		
		JButton JVendita = new JButton("Vendita");
		JVendita.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-sale-32.png")));
		JVendita.setForeground(Color.WHITE);
		JVendita.setBackground(new Color(0, 52, 102));
		JVendita.setFont(new Font("Verdana", Font.BOLD, 16));
		JVendita.setPreferredSize(new Dimension(180, 50));
		JVendita.setFocusPainted(false);
		JVendita.setBorderPainted(false);
		
		buttonPanel.add(JScambio);
		buttonPanel.add(JRegalo);
		buttonPanel.add(JVendita);
		mainPanel.add(buttonPanel, gbc);
		
		// Validazione e navigazione comune
		ActionListener validazioneComune = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!validaCampi(textfieldTitolo, textAreaDescrizione, textAreaModConsegna, comboBoxFasciaOraria)) {
					return;
				}
				
				String fasciaSelezionata = (String) comboBoxFasciaOraria.getSelectedItem();
				FasciaOraria fasciaOraria = FasciaOraria.fromLabel(fasciaSelezionata);
				
				JButton sourceButton = (JButton) e.getSource();
				setVisible(false);
				
				if (sourceButton == JScambio) {
					AnnuncioScambio annuncioScambioFrame = new AnnuncioScambio(
						UtenteLoggato, OggettoAnnuncio,
						textfieldTitolo.getText().trim(),
						textAreaDescrizione.getText().trim(),
						textAreaModConsegna.getText().trim(),
						fasciaOraria,
						PercorsiImmagini,
						controller
					);
					annuncioScambioFrame.setVisible(true);
				} else if (sourceButton == JRegalo) {
					AnnuncioRegalo annuncioRegaloFrame = new AnnuncioRegalo(
						UtenteLoggato, OggettoAnnuncio,
						textfieldTitolo.getText().trim(),
						textAreaDescrizione.getText().trim(),
						textAreaModConsegna.getText().trim(),
						fasciaOraria,
						PercorsiImmagini,
						controller
					);
					annuncioRegaloFrame.setVisible(true);
				} else if (sourceButton == JVendita) {
					AnnuncioVendita annuncioVenditaFrame = new AnnuncioVendita(
						UtenteLoggato, OggettoAnnuncio,
						textfieldTitolo.getText().trim(),
						textAreaDescrizione.getText().trim(),
						textAreaModConsegna.getText().trim(),
						fasciaOraria,
						PercorsiImmagini,
						controller
					);
					annuncioVenditaFrame.setVisible(true);
				}
			}
		};
		
		JScambio.addActionListener(validazioneComune);
		JRegalo.addActionListener(validazioneComune);
		JVendita.addActionListener(validazioneComune);
	}
	
	private void caricaImmagini() {
		JFileChooser scegliImg = new JFileChooser();
        scegliImg.setDialogTitle("Seleziona immagini");
        scegliImg.setFileFilter(new FileNameExtensionFilter("Tutte le immagini", "png", "jpg", "jpeg"));
        scegliImg.setMultiSelectionEnabled(true);

        if (scegliImg.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	java.io.File[] files = scegliImg.getSelectedFiles();
        	for (java.io.File file : files) {
        		String percorso = file.getAbsolutePath();
        		PercorsiImmagini.add(percorso);
        		listModelFoto.addElement(file.getName());
        	}
        }
	}
	
	private boolean validaCampi(JTextField titolo, JTextArea descrizione, 
	                           JTextField modConsegna, JComboBox<String> fasciaOraria) {
		if (titolo.getText().trim().isEmpty() || 
			descrizione.getText().trim().isEmpty() || 
			modConsegna.getText().trim().isEmpty() || 
			PercorsiImmagini.isEmpty()) {
			JOptionPane.showMessageDialog(null, 
				"Tutti i campi sono obbligatori e devi caricare almeno una foto", 
				"Campi mancanti", 
				JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		String fasciaSelezionata = (String) fasciaOraria.getSelectedItem();
		if (fasciaSelezionata == null || fasciaSelezionata.equals("Seleziona una fascia oraria")) {
			JOptionPane.showMessageDialog(null, 
				"Seleziona una fascia oraria valida", 
				"Fascia oraria non valida", 
				JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
}