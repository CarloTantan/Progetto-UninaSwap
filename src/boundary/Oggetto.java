package boundary;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import enumerations.TipologiaCategoria;
import mainController.MainController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che rappresenta la finestra per l'inserimento di un nuovo oggetto.
 * Permette agli utenti di specificare le informazioni base di un oggetto
 * (nome, descrizione e categoria) prima di creare un annuncio.
 * Questa è la prima fase del processo di creazione di un annuncio nell'applicazione.
 */
public class Oggetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeOggetto;
	private JTextArea textAreaDescrizioneOggetto;
	private JComboBox<String> comboBoxCategoria;
	private MainController controller;

	public Oggetto(MainController controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Oggetto.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Inserimento Oggetto");
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(900, 700));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 247, 250));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		// HEADER PANEL :Pannello superiore con titolo e bottone indietro
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(45, 134, 192));
		headerPanel.setPreferredSize(new Dimension(0, 80));
		headerPanel.setLayout(new BorderLayout());
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JButton btnUndo = new JButton("");
		btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        controller.apriAreaUtente();
			}
		});
		btnUndo.setIcon(new ImageIcon(AreaUtente.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setPreferredSize(new Dimension(60, 80));
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(45, 134, 192));
		leftPanel.add(btnUndo);
		headerPanel.add(leftPanel, BorderLayout.WEST);
		
		JLabel lblTitolo = new JLabel("Inserisci il tuo oggetto");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
		JPanel centerHeaderPanel = new JPanel();
		centerHeaderPanel.setBackground(new Color(45, 134, 192));
		centerHeaderPanel.setBorder(new EmptyBorder(25, 0, 0, 0));
		centerHeaderPanel.add(lblTitolo);
		headerPanel.add(centerHeaderPanel, BorderLayout.CENTER);
		
		// MAIN CONTENT PANEL: Pannello principale contenente il form per l'oggetto
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(245, 247, 250));
		mainPanel.setBorder(new EmptyBorder(60, 100, 60, 100));
		mainPanel.setLayout(new GridBagLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Nome Oggetto
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel lblNomeOggetto = new JLabel("Nome");
		lblNomeOggetto.setFont(new Font("Verdana", Font.BOLD, 18));
		mainPanel.add(lblNomeOggetto, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		textFieldNomeOggetto = new JTextField();
		textFieldNomeOggetto.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldNomeOggetto.setPreferredSize(new Dimension(350, 40));
		mainPanel.add(textFieldNomeOggetto, gbc);
		
		// Descrizione Oggetto
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		JLabel lblDescrizioneOggetto = new JLabel("Descrizione");
		lblDescrizioneOggetto.setFont(new Font("Verdana", Font.BOLD, 18));
		mainPanel.add(lblDescrizioneOggetto, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.BOTH;
		textAreaDescrizioneOggetto = new JTextArea();
		textAreaDescrizioneOggetto.setFont(new Font("Verdana", Font.PLAIN, 14));
		textAreaDescrizioneOggetto.setLineWrap(true);
		textAreaDescrizioneOggetto.setWrapStyleWord(true);
		textAreaDescrizioneOggetto.setRows(5);
		JScrollPane scrollPane = new JScrollPane(textAreaDescrizioneOggetto);
		scrollPane.setPreferredSize(new Dimension(350, 120));
		mainPanel.add(scrollPane, gbc);
		
		// Categoria
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel lblCategoria = new JLabel("Categoria oggetto");
		lblCategoria.setFont(new Font("Verdana", Font.BOLD, 18));
		mainPanel.add(lblCategoria, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.addItem("Seleziona una categoria");
		for (TipologiaCategoria categoria : TipologiaCategoria.values()) {
		    comboBoxCategoria.addItem(categoria.getNome());
		}
		comboBoxCategoria.setFont(new Font("Verdana", Font.PLAIN, 14));
		comboBoxCategoria.setPreferredSize(new Dimension(350, 40));
		mainPanel.add(comboBoxCategoria, gbc);
		
		// Bottone Continua
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 15, 15, 15);
		JButton btnContinua = new JButton("Continua");
		btnContinua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	inserisciOggetto();
		    }
		});
		btnContinua.setForeground(Color.WHITE);
		btnContinua.setFont(new Font("Verdana", Font.BOLD, 18));
		btnContinua.setBackground(new Color(0, 52, 102));
		btnContinua.setFocusPainted(false);
		btnContinua.setBorderPainted(false);
		btnContinua.setPreferredSize(new Dimension(200, 50));
		mainPanel.add(btnContinua, gbc);
		
		// Hover effect
		btnContinua.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnContinua.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(MouseEvent evt) {
				btnContinua.setBackground(new Color(0, 52, 102));
			}
		});
	}
	 //Metodo che gestisce l'inserimento di un nuovo oggetto.
	private void inserisciOggetto() {
	    // Recupera i valori dai campi (responsabilità del Boundary)
	    String nome = textFieldNomeOggetto.getText().trim();
	    String descrizione = textAreaDescrizioneOggetto.getText().trim();
	    String categoriaSelezionata = (String) comboBoxCategoria.getSelectedItem();
	    
	    // Chiama il controller per inserire l'oggetto (Control gestisce la logica)
	    String risultato = controller.InserisciOggetto(nome, descrizione, categoriaSelezionata);
	    
	    // Gestisci il risultato (responsabilità del Boundary - mostrare feedback)
	    if (risultato.equals("Oggetto inserito con successo")) {
	        JOptionPane.showMessageDialog(this, 
	            "Oggetto inserito con successo!", 
	            "Successo", 
	            JOptionPane.INFORMATION_MESSAGE);
	        
	        // Imposta l'oggetto per l'annuncio
	        controller.impostaOggettoPerAnnuncio(controller.getUltimoOggettoCreato());
	       
	        // Passa alla schermata successiva
	        controller.apriCreazioneAnnuncio();
	        
	    } else {
	        // Mostra l'errore restituito dal controller
	        JOptionPane.showMessageDialog(this, 
	            risultato, 
	            "Errore", 
	            JOptionPane.ERROR_MESSAGE);
	    }
	}
}