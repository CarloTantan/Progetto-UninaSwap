package boundary;


import javax.swing.JFrame;
import javax.swing.JPanel;
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

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Classe che rappresenta l'interfaccia grafica per la creazione di un annuncio di tipo "Vendita".
 * Questa schermata viene mostrata dopo che l'utente ha compilato i dati generali dell'annuncio
 * e ha scelto di vendere l'oggetto. Qui l'utente inserisce il prezzo di vendita.
 */
public class AnnuncioVendita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MainController controller;
	private JTextField textFieldPrezzo;

	/**
	 * Create the frame.
	 */
	public AnnuncioVendita(MainController controller) {
		this.controller = controller;
		
		// Verifica che ci siano informazioni sull'annuncio nel controller
		if (controller.getOggettoAnnuncio() == null) {
			JOptionPane.showMessageDialog(this,
				"Errore: nessun oggetto selezionato per l'annuncio",
				"Errore",
				JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}
		
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
		
		//  HEADER PANEL: Pannello superiore con sfondo blu
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
				controller.apriCreazioneAnnuncio();
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
		
	//  MAIN CONTENT PANEL : Pannello centrale che contiene il form
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(245, 247, 250));
		mainPanel.setBorder(new EmptyBorder(60, 100, 60, 100));
		mainPanel.setLayout(new GridBagLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// SEZIONE PREZZO 
		// Label per il campo "Prezzo"
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
		textFieldPrezzo = new JTextField();
		textFieldPrezzo.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldPrezzo.setPreferredSize(new Dimension(300, 40));
		mainPanel.add(textFieldPrezzo, gbc);
		
		// 	BOTTONE PUBBLICA 
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
				pubblicaAnnuncioVendita();
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
	/**
	 * Metodo privato che gestisce la pubblicazione dell'annuncio di vendita.
	 */
	private void pubblicaAnnuncioVendita() {
	    // Recupera il prezzo come stringa
	    String prezzoStr = textFieldPrezzo.getText().trim();
	    
	    // Chiama il controller che gestisce validazione e pubblicazione
	    String risultato = controller.PubblicaAnnuncioVendita(prezzoStr);
	    
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