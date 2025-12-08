package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import entity.OffertaScambio_entity;
import mainController.MainController;

import java.awt.Toolkit;
import javax.swing.JTextField;

public class OffertaScambio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int IdAnnuncioScelto;
	private JTextField textFieldOggettoProposto;
	private int IdOffertaDaModificare = -1;
	private boolean isModificaMode = false;
	private JButton btnConferma;
	private MainController controller;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OffertaScambio frame = new OffertaScambio();
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

	public OffertaScambio( MainController controller) {
		this.controller = controller;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaScambio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Scambio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(850, 600));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		// ---------------- HEADER -----------------
		JPanel header = new JPanel();
		header.setBackground(new Color(45, 134, 192));
		header.setLayout(null);
		header.setPreferredSize(new Dimension(0, 94));
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblOffertaScambio = new JLabel("Offerta Scambio");
		lblOffertaScambio.setFont(new Font("Verdana", Font.BOLD, 24));
		lblOffertaScambio.setForeground(Color.WHITE);
		lblOffertaScambio.setBounds(0, 0, 300, 94);
		lblOffertaScambio.setHorizontalAlignment(JLabel.CENTER);
		header.add(lblOffertaScambio);
		
		// Posizionamento centrato del titolo
		header.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				int headerWidth = header.getWidth();
				lblOffertaScambio.setBounds((headerWidth - 300) / 2, 10, 300, 79);
			}
		});
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(controller);
				ListaAnnunciFrame.setVisible(true);
			}
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(OffertaScambio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(15, 15, 50, 50);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		header.add(btnUndo);
		
		btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnUndo.setBackground(new Color(66, 152, 211));
				btnUndo.setContentAreaFilled(true);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnUndo.setBackground(new Color(45, 134, 192));
				btnUndo.setContentAreaFilled(false);
			}
		});
		
		// ---------------- PANNELLO CENTRALE CON GRIDBAGLAYOUT -----------------
		JPanel panelCentrale = new JPanel();
		panelCentrale.setBackground(Color.WHITE);
		panelCentrale.setLayout(new GridBagLayout());
		contentPane.add(panelCentrale, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Label Oggetto
		JLabel lblNewLabel = new JLabel("Oggetto proposto");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 5, 10);
		gbc.anchor = GridBagConstraints.WEST;
		panelCentrale.add(lblNewLabel, gbc);
		
		// Campo Oggetto
		textFieldOggettoProposto = new JTextField();
		textFieldOggettoProposto.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldOggettoProposto.setPreferredSize(new Dimension(280, 38));
		textFieldOggettoProposto.setColumns(10);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 10, 20, 10);
		panelCentrale.add(textFieldOggettoProposto, gbc);
		
		// Bottone Conferma
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inviaOffertaScambio();
			}
		});
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 18));
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setForeground(Color.WHITE);
		btnConferma.setPreferredSize(new Dimension(180, 45));
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentrale.add(btnConferma, gbc);
		getRootPane().setDefaultButton(btnConferma);
		
		btnConferma.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnConferma.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnConferma.setBackground(new Color(0, 52, 101));
			}
		});
	}
	public void inviaOffertaScambio() {
	    // Recupera i dati dall'interfaccia
	    String oggettoProposto = textFieldOggettoProposto.getText().trim();
	    String matricolaAcquirente = controller.getMatricolaUtenteLoggato();
	    
	    String risultato;
	    
	    if (isModificaMode) {
	        // Aggiorna offerta esistente
	        risultato = controller.AggiornaOffertaScambio(
	            oggettoProposto,
	            matricolaAcquirente,
	            IdAnnuncioScelto,
	            IdOffertaDaModificare
	        );
	    } else {
	        // Inserisci nuova offerta
	        risultato = controller.InviaOffertaScambio(  // ✅ CORRETTO
	            oggettoProposto,
	            matricolaAcquirente,
	            IdAnnuncioScelto
	        );
	    }
	    
	    // Gestisce il risultato
	    if (risultato.equals("Offerta inviata con successo") || 
	        risultato.equals("Offerta aggiornata con successo")) {
	        
	        String messaggio = isModificaMode ? "Offerta aggiornata" : "Offerta inviata";
	        JOptionPane.showMessageDialog(this, 
	            messaggio, 
	            "Successo", 
	            JOptionPane.INFORMATION_MESSAGE);
	        
	        setVisible(false);
	        ListaAnnunci listaAnnunciFrame = new ListaAnnunci(controller);
	        listaAnnunciFrame.setVisible(true);
	    } else {
	        // Mostra l'errore restituito dal controller
	        JOptionPane.showMessageDialog(this,
	            risultato,
	            "Errore",
	            JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void caricaOffertaPerModifica(int idOfferta) {
	    // Chiama il controller per caricare l'offerta
	    OffertaScambio_entity offerta = controller.CaricaOffertaScambio(idOfferta);
	    
	    if (offerta != null) {
	        // Popola l'interfaccia con i dati dell'offerta
	        textFieldOggettoProposto.setText(offerta.getOggettoProposto());
	        IdOffertaDaModificare = idOfferta;
	        isModificaMode = true;
	        btnConferma.setText("Aggiorna");
	    } else {
	        JOptionPane.showMessageDialog(this, 
	            "Offerta non trovata o errore nel caricamento", 
	            "Errore", 
	            JOptionPane.ERROR_MESSAGE);
	    }
	}
}