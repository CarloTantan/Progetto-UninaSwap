package boundary;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entity.Oggetto_entity;
import entity.Utente_entity;
import enumerations.FasciaOraria;

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
import java.awt.Image;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
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
	public Annuncio(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		this.PercorsiImmagini = new ArrayList<>();
		this.listModelFoto = new DefaultListModel<>();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Annuncio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Crea la tua inserzione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 
		setBounds(100, 100, 548, 638);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane); 
		
		JTextField textfieldTitolo = new JTextField();
		textfieldTitolo.setFont(new Font("Verdana", Font.PLAIN, 16));
		textfieldTitolo.setForeground(new Color(255, 255, 255));
		textfieldTitolo.setBackground(new Color(0, 52, 102));
		textfieldTitolo.setBounds(251, 198, 203, 22);
		contentPane.add(textfieldTitolo);
		
		JTextField textAreaModConsegna = new JTextField();
		textAreaModConsegna.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaModConsegna.setForeground(new Color(255, 255, 255));
		textAreaModConsegna.setBackground(new Color(0, 52, 102));
		textAreaModConsegna.setBounds(251, 399, 203, 22);
		contentPane.add(textAreaModConsegna);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(36, 194, 179, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setBounds(36, 266, 170, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fascia Oraria");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_2.setBounds(36, 350, 135, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Modalità di consegna");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_3.setBounds(36, 398, 179, 22);
		contentPane.add(lblNewLabel_3);
	
		
		JButton JRegalo = new JButton("Regalo");
		JRegalo.setHorizontalAlignment(SwingConstants.LEFT);
		JRegalo.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/—Pngtree—gift icon_7390276.png")));
		JRegalo.setForeground(new Color(255, 255, 255));
		JRegalo.setBackground(new Color(0, 52, 102));
		JRegalo.setFont(new Font("Verdana", Font.BOLD, 16));
		JRegalo.setBounds(195, 514, 151, 56);
		contentPane.add(JRegalo);
		JRegalo.setFocusPainted(false);
		JRegalo.setBorderPainted(false);
		
		JButton JVendita = new JButton("Vendita");
		JVendita.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-sale-32.png")));
		JVendita.setHorizontalAlignment(SwingConstants.LEFT);
		JVendita.setForeground(new Color(255, 255, 255));
		JVendita.setBackground(new Color(0, 52, 102));
		JVendita.setFont(new Font("Verdana", Font.BOLD, 16));
		JVendita.setBounds(356, 514, 145, 56);
		contentPane.add(JVendita);
		JVendita.setFocusPainted(false);
		JVendita.setBorderPainted(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 536, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBounds(0, 0, 46, 58);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				AreaUtente utenteFrame = new AreaUtente(UtenteLoggato); 
				utenteFrame.setVisible(true);
			}
		}); 
		
		JLabel lblNewLabel_6 = new JLabel("Annuncio");
		lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_6.setBounds(234, 0, 100, 58);
		panel.add(lblNewLabel_6);
		
		// Lista per visualizzare le foto caricate
		listFoto = new JList<>(listModelFoto);
		listFoto.setFont(new Font("Verdana", Font.PLAIN, 10));
		JScrollPane scrollPaneFoto = new JScrollPane(listFoto);
		scrollPaneFoto.setBounds(251, 77, 203, 77);
		contentPane.add(scrollPaneFoto);
		
		JButton ButtonImgOggetto = new JButton("Carica immagine");
		ButtonImgOggetto.setForeground(new Color(255, 255, 255));
		ButtonImgOggetto.setBackground(new Color(0, 52, 102));
		ButtonImgOggetto.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonImgOggetto.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				caricaImmagini(); // Chiama il metodo caricaImg()
		        }
		});
		
		
		ButtonImgOggetto.setFont(new Font("Tahoma", Font.BOLD, 16));
		ButtonImgOggetto.setBounds(21, 77, 194, 36);
		contentPane.add(ButtonImgOggetto);
		ButtonImgOggetto.setFocusPainted(false);
		ButtonImgOggetto.setBorderPainted(false);
		
		// Pulsante per rimuovere foto selezionata
		JButton ButtonRimuoviFoto = new JButton("Rimuovi");
		ButtonRimuoviFoto.setForeground(new Color(255, 255, 255));
		ButtonRimuoviFoto.setBackground(new Color(0, 52, 102));
		ButtonRimuoviFoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		ButtonRimuoviFoto.setBounds(21, 124, 194, 30);
		ButtonRimuoviFoto.setFocusPainted(false);
		ButtonRimuoviFoto.setBorderPainted(false);
		contentPane.add(ButtonRimuoviFoto);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(251, 242, 203, 77);
		contentPane.add(scrollPane);
		
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setForeground(new Color(255, 255, 255));
		textAreaDescrizione.setBackground(new Color(0, 52, 102));
		textAreaDescrizione.setFont(new Font("Verdana", Font.PLAIN, 16));
		textAreaDescrizione.setLineWrap(true);  
		textAreaDescrizione.setWrapStyleWord(true);
		textAreaDescrizione.setRows(4);
		textAreaDescrizione.setColumns(20);
		scrollPane.setViewportView(textAreaDescrizione);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		String[] categorie= {"Seleziona una categoria", "Libri", "Cancelleria", "Vestiti", "Elettronica", "Musica", "Giochi", "Sport", "Casa", "Altro"};
		
		String[] fasciaOraria = {"Seleziona una fascia oraria", "8:00 - 10:00", "10:00 - 12:00","12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00", "20:00 - 22:00"};
		JComboBox comboBoxFasciaOraria = new JComboBox<>(fasciaOraria);
		comboBoxFasciaOraria.setForeground(new Color(255, 255, 255));
		comboBoxFasciaOraria.setBackground(new Color(0, 52, 102));
		comboBoxFasciaOraria.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxFasciaOraria.setBounds(251, 347, 203, 22);
		contentPane.add(comboBoxFasciaOraria);
		
		JButton JScambio = new JButton("Scambio");
		JScambio.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-exchange-32.png")));
		JScambio.setHorizontalAlignment(SwingConstants.LEFT);
		JScambio.setForeground(new Color(255, 255, 255));
		JScambio.setToolTipText("");
		JScambio.setBackground(new Color(0, 52, 102));
		JScambio.setFont(new Font("Verdana", Font.BOLD, 16));
		JScambio.setBounds(36, 514, 151, 56);
		contentPane.add(JScambio);
		JScambio.setFocusPainted(false);
		JScambio.setBorderPainted(false);

		// Validazione e navigazione comune
				ActionListener validazioneComune = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!validaCampi(textfieldTitolo, textAreaDescrizione, textAreaModConsegna, comboBoxFasciaOraria)) {
							return;
						}
						
						// Recupera la fascia oraria selezionata
						String fasciaSelezionata = (String) comboBoxFasciaOraria.getSelectedItem();
						FasciaOraria fasciaOraria = FasciaOraria.fromLabel(fasciaSelezionata);
						
						// Determina quale pulsante è stato premuto
						JButton sourceButton = (JButton) e.getSource();
						setVisible(false);
						
						if (sourceButton == JScambio) {
							AnnuncioScambio annuncioScambioFrame = new AnnuncioScambio(
								UtenteLoggato, OggettoAnnuncio,
								textfieldTitolo.getText().trim(),
								textAreaDescrizione.getText().trim(),
								textAreaModConsegna.getText().trim(),
								fasciaOraria,
								PercorsiImmagini
							);
							annuncioScambioFrame.setVisible(true);
							annuncioScambioFrame.setLocationRelativeTo(null);
						} else if (sourceButton == JRegalo) {
							AnnuncioRegalo annuncioRegaloFrame = new AnnuncioRegalo(
								UtenteLoggato, OggettoAnnuncio,
								textfieldTitolo.getText().trim(),
								textAreaDescrizione.getText().trim(),
								textAreaModConsegna.getText().trim(),
								fasciaOraria,
								PercorsiImmagini
							);
							annuncioRegaloFrame.setVisible(true);
							annuncioRegaloFrame.setLocationRelativeTo(null);
						} else if (sourceButton == JVendita) {
							AnnuncioVendita annuncioVenditaFrame = new AnnuncioVendita(
								UtenteLoggato, OggettoAnnuncio,
								textfieldTitolo.getText().trim(),
								textAreaDescrizione.getText().trim(),
								textAreaModConsegna.getText().trim(),
								fasciaOraria,
								PercorsiImmagini
							);
							annuncioVenditaFrame.setVisible(true);
							annuncioVenditaFrame.setLocationRelativeTo(null);
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
		        scegliImg.setMultiSelectionEnabled(true); // Abilita selezione multipla

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
