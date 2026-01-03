package boundary;


import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Toolkit;
/**
 * Classe che rappresenta l'interfaccia grafica per la creazione di un annuncio di tipo "Scambio".
 * Questa schermata viene mostrata dopo che l'utente ha compilato i dati generali dell'annuncio
 * e ha scelto di scambiare l'oggetto. Qui l'utente specifica quale tipo di oggetto desidera ricevere in cambio.
 */
public class AnnuncioScambio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MainController controller;
	private JTextArea textAreaOggettoRichiesto;
	
	public AnnuncioScambio(MainController controller) {
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
		
		// HEADER PANEL : Pannello superiore con sfondo blu	
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
				controller.apriCreazioneAnnuncio();
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
		
		//  MAIN CONTENT PANEL : Pannello centrale che contiene il form
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(245, 247, 250));
		mainPanel.setBorder(new EmptyBorder(60, 100, 60, 100));
		mainPanel.setLayout(new GridBagLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//  SEZIONE OGGETTO RICHIESTO: Label per il campo "Oggetto richiesto"
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
		textAreaOggettoRichiesto = new JTextArea();
		textAreaOggettoRichiesto.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaOggettoRichiesto.setLineWrap(true);
		textAreaOggettoRichiesto.setWrapStyleWord(true);
		textAreaOggettoRichiesto.setRows(6);
		JScrollPane scrollPane = new JScrollPane(textAreaOggettoRichiesto);
		scrollPane.setPreferredSize(new Dimension(500, 150));
		mainPanel.add(scrollPane, gbc);
		
		// BOTTONE PUBBLICA
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
				pubblicaAnnuncioScambio();
			}
		});
		// Effetto hover sul bottone (cambia colore quando il mouse passa sopra)
		ButtonPubblica.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				ButtonPubblica.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(MouseEvent evt) {
				ButtonPubblica.setBackground(new Color(0, 52, 102));
			}
		});
	}
	
	 // Metodo privato che gestisce la pubblicazione dell'annuncio di scambio.
	private void pubblicaAnnuncioScambio() {
	    // Recupera l'oggetto richiesto dall'interfaccia
	    String oggettoRichiesto = textAreaOggettoRichiesto.getText().trim();
	    
	    // Chiama il controller che gestisce tutto
	    String risultato = controller.PubblicaAnnuncioScambio(oggettoRichiesto);
	    
	    // Gestisce il risultato
	    if (risultato.equals("Annuncio pubblicato con successo")) {
	        JOptionPane.showMessageDialog(this,
	            "Pubblicazione avvenuta con successo",
	            "Annuncio pubblicato",
	            JOptionPane.INFORMATION_MESSAGE);
	        
	        controller.apriAreaUtente();
	    } else {
	        // Mostra l'errore restituito dal controller
	        JOptionPane.showMessageDialog(this,
	            risultato,
	            "Errore",
	            JOptionPane.ERROR_MESSAGE);
	    }
	}
}