package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mainController.MainController;

/**
 * Classe che rappresenta la schermata per visualizzare tutte le recensioni
 * ricevute da un venditore specifico. Mostra il nominativo del venditore,
 * la valutazione media con il numero totale di recensioni, e una lista
 * scrollabile di tutte le recensioni con relativi dettagli.
 */
public class VisualizzaRecensioniVenditore extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel panelRecensioni;
    private MainController controller;

	public VisualizzaRecensioniVenditore(MainController controller) {
	    this.controller = controller;

	    setTitle("Recensioni Venditore");
	    setIconImage(Toolkit.getDefaultToolkit().getImage(
	            VisualizzaRecensioniVenditore.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setMinimumSize(new Dimension(700, 500));

	    JPanel mainPanel = new JPanel(new BorderLayout());
	    setContentPane(mainPanel);

	    // ---------------- HEADER -----------------
	    JPanel header = new JPanel(new BorderLayout());
	    header.setBackground(new Color(45, 134, 192));
	    header.setBorder(new EmptyBorder(15, 20, 15, 20));

	    // Bottone indietro
	    JButton btnUndo = new JButton(new ImageIcon(
	            VisualizzaRecensioniVenditore.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
	    btnUndo.setBackground(new Color(45, 134, 192));
	    btnUndo.setBorderPainted(false);
	    btnUndo.setFocusPainted(false);
	    btnUndo.addActionListener(e -> dispose());

	    // Info venditore
	    JPanel infoPanel = new JPanel();
	    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	    infoPanel.setBackground(new Color(45, 134, 192));

	    JLabel lblNome = new JLabel();
	    lblNome.setFont(new Font("Verdana", Font.BOLD, 22));
	    lblNome.setForeground(Color.WHITE);
	    lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lblRating = new JLabel();
	    lblRating.setFont(new Font("Verdana", Font.PLAIN, 16));
	    lblRating.setForeground(Color.WHITE);
	    lblRating.setAlignmentX(Component.CENTER_ALIGNMENT);

	    infoPanel.add(lblNome);
	    infoPanel.add(Box.createVerticalStrut(5));
	    infoPanel.add(lblRating);

	    header.add(btnUndo, BorderLayout.WEST);
	    header.add(infoPanel, BorderLayout.CENTER);

	    mainPanel.add(header, BorderLayout.NORTH);

	    // ---------------- PANEL RECENSIONI -----------------
	    panelRecensioni = new JPanel();
	    panelRecensioni.setLayout(new BoxLayout(panelRecensioni, BoxLayout.Y_AXIS));
	    panelRecensioni.setBackground(Color.WHITE);
	    panelRecensioni.setBorder(new EmptyBorder(20, 20, 20, 20));

	    JScrollPane scrollPane = new JScrollPane(panelRecensioni);
	    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	    mainPanel.add(scrollPane, BorderLayout.CENTER);

	    // Carica i dati tramite controller
	    caricaDati(lblNome, lblRating);
	}
//Carica le informazioni del venditore (nome, rating, numero recensioni)

	private void caricaDati(JLabel lblNome, JLabel lblRating) {
	    // Ottieni info venditore tramite controller
	    String nominativo = controller.getNominativoVenditoreCorrente();
	    double media = controller.getValutazioneMediaVenditoreCorrente();
	    int numRecensioni = controller.getNumeroRecensioniVenditoreCorrente();

	    lblNome.setText(nominativo != null ? nominativo : "Utente Sconosciuto");
	    
	    if (numRecensioni > 0) {
	        ImageIcon starIcon = new ImageIcon(getClass().getResource("/icons/icons8-stella-32.png"));
	        lblRating.setText(String.format(" %.1f / 5.0 (%d recensioni)", media, numRecensioni));
	        lblRating.setIcon(starIcon);
	        lblRating.setIconTextGap(5);
	    } else {
	        lblRating.setText("Nessuna recensione disponibile");
	        lblRating.setIcon(null);
	    }

	    // Carica recensioni tramite controller (usando gli indici)
	    if (numRecensioni == 0) {
	        JLabel lblNoRecensioni = new JLabel("Questo venditore non ha ancora ricevuto recensioni ");
	        lblNoRecensioni.setFont(new Font("Verdana", Font.ITALIC, 14));
	        lblNoRecensioni.setForeground(Color.GRAY);
	        lblNoRecensioni.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panelRecensioni.add(Box.createVerticalStrut(50));
	        panelRecensioni.add(lblNoRecensioni);
	    } else {
	        for (int i = 0; i < numRecensioni; i++) {
	            panelRecensioni.add(creaCardRecensione(i));
	            panelRecensioni.add(Box.createVerticalStrut(15));
	        }
	    }
	}
// Crea una card (pannello) che rappresenta visivamente una recensione con tutti i suoi dettagli
	//punteggio in stelle, data, commento, annuncio relativo e informazioni sull'acquirente.
	 

	private JPanel creaCardRecensione(int index) {
	    JPanel card = new JPanel();
	    card.setLayout(new BorderLayout(10, 10));
	    card.setBackground(Color.WHITE);
	    card.setBorder(new CompoundBorder(
	            new LineBorder(new Color(200, 200, 200), 1, true),
	            new EmptyBorder(15, 15, 15, 15)
	    ));
	    card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

	    // Recupera i dati tramite controller
	    int punteggio = controller.getPunteggioRecensioneVenditoreCorrente(index);
	    String dataStr = controller.getDataRecensioneVenditoreCorrente(index);
	    String commento = controller.getCommentoRecensioneVenditoreCorrente(index);
	    int idOfferta = controller.getIdOffertaRecensioneVenditoreCorrente(index);
	    String matricolaAcquirente = controller.getMatricolaAcquirenteRecensioneVenditoreCorrente(index);

	    // Header con stelle e data
	    JPanel headerPanel = new JPanel(new BorderLayout());
	    headerPanel.setBackground(Color.WHITE);

	    // Stelle
	    JPanel panelStelle = creaPannelloStelle(punteggio);

	    // Data
	    JLabel lblData = new JLabel(dataStr);
	    lblData.setFont(new Font("Verdana", Font.PLAIN, 12));
	    lblData.setForeground(Color.GRAY);

	    headerPanel.add(panelStelle, BorderLayout.WEST);
	    headerPanel.add(lblData, BorderLayout.EAST);

	    // PANNELLO CENTRALE 
	    JPanel centralPanel = new JPanel();
	    centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
	    centralPanel.setBackground(Color.WHITE);

	    // Titolo annuncio tramite controller
	    String titoloAnnuncio = controller.getTitoloAnnuncioDaOfferta(idOfferta);
	    if (titoloAnnuncio != null) {
	        JLabel lblTitoloAnnuncio = new JLabel("Annuncio: " + titoloAnnuncio);
	        lblTitoloAnnuncio.setFont(new Font("Verdana", Font.BOLD, 13));
	        lblTitoloAnnuncio.setForeground(new Color(0, 52, 104));
	        lblTitoloAnnuncio.setBorder(new EmptyBorder(0, 0, 8, 0));
	        centralPanel.add(lblTitoloAnnuncio);
	    }

	    // Commento
	    JTextArea txtCommento = new JTextArea(commento != null && !commento.isEmpty() ? commento : "Nessun commento");
	    txtCommento.setFont(new Font("Verdana", Font.PLAIN, 13));
	    txtCommento.setForeground(Color.DARK_GRAY);
	    txtCommento.setLineWrap(true);
	    txtCommento.setWrapStyleWord(true);
	    txtCommento.setEditable(false);
	    txtCommento.setOpaque(false);
	    txtCommento.setBorder(new EmptyBorder(5, 0, 10, 0));
	    centralPanel.add(txtCommento);

	    // Footer con nome acquirente + matricola
	    JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
	    footerPanel.setBackground(Color.WHITE);

	    // Nome acquirente tramite controller
	    String nomeAcquirente = controller.getNominativoUtente(matricolaAcquirente);
	    JLabel lblAcquirente = new JLabel("— " + (nomeAcquirente != null ? nomeAcquirente : "Utente"));
	    lblAcquirente.setFont(new Font("Verdana", Font.ITALIC, 12));
	    lblAcquirente.setForeground(new Color(100, 100, 100));
	    footerPanel.add(lblAcquirente);

	    // Aggiungi matricola acquirente
	    JLabel lblMatricola = new JLabel("(" + matricolaAcquirente + ")");
	    lblMatricola.setFont(new Font("Verdana", Font.PLAIN, 11));
	    lblMatricola.setForeground(new Color(120, 120, 120));
	    footerPanel.add(lblMatricola);

	    card.add(headerPanel, BorderLayout.NORTH);
	    card.add(centralPanel, BorderLayout.CENTER);
	    card.add(footerPanel, BorderLayout.SOUTH);

	    return card;
	}
	//Crea un pannello con 5 stelle che rappresentano visivamente il punteggio della recensione.
	private JPanel creaPannelloStelle(int punteggio) {
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
	    panel.setBackground(Color.WHITE);
	    
	    // Carica entrambe le icone
	    ImageIcon starIconPiena = new ImageIcon(getClass().getResource("/icons/icons8-stella-32.png"));
	    ImageIcon starIconVuota = new ImageIcon(getClass().getResource("/icons/icons8-stella-vuota-32.png"));
	    
	    // Ridimensiona entrambe le icone
	    Image imgPiena = starIconPiena.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	    Image imgVuota = starIconVuota.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	    
	    ImageIcon stellaPienaRidimensionata = new ImageIcon(imgPiena);
	    ImageIcon stellaVuotaRidimensionata = new ImageIcon(imgVuota);
	    
	    // Crea 5 stelle
	    for (int i = 0; i < 5; i++) {
	        JLabel lblStella = new JLabel();
	        if (i < punteggio) {
	            lblStella.setIcon(stellaPienaRidimensionata);
	        } else {
	            lblStella.setIcon(stellaVuotaRidimensionata);
	        }
	        panel.add(lblStella);
	    }
	    
	    return panel;
	}
}