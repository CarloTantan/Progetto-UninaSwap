package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.ListaRecensioniDao;
import dao.RecensioneVenditoreDAO;
import entity.Recensione_entity;
import entity.Utente_entity;

public class VisualizzaRecensioniVenditore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private String matricolaVenditore;
    private Utente_entity utenteLoggato;
    private JPanel panelRecensioni;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VisualizzaRecensioniVenditore frame = new VisualizzaRecensioniVenditore();
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

	    public VisualizzaRecensioniVenditore(Utente_entity utenteLoggato, String matricolaVenditore) {
	        this.utenteLoggato = utenteLoggato;
	        this.matricolaVenditore = matricolaVenditore;

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

	        // Carica i dati
	        caricaDati(lblNome, lblRating);
	    }

	    private void caricaDati(JLabel lblNome, JLabel lblRating) {
	        try {
	            RecensioneVenditoreDAO recensioneDAO = new RecensioneVenditoreDAO();
	            ListaRecensioniDao listaDAO = new ListaRecensioniDao();

	            // Ottieni info venditore
	            String nominativo = recensioneDAO.getNominativoUtente(matricolaVenditore);
	            double media = recensioneDAO.getValutazioneMedia(matricolaVenditore);
	            int numRecensioni = recensioneDAO.getNumeroRecensioni(matricolaVenditore);

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

	            // Carica recensioni
	            ArrayList<Recensione_entity> recensioni = listaDAO.VisualizzaRecensioniRicevute(matricolaVenditore);

	            if (recensioni.isEmpty()) {
	                JLabel lblNoRecensioni = new JLabel("Questo venditore non ha ancora ricevuto recensioni.");
	                lblNoRecensioni.setFont(new Font("Verdana", Font.ITALIC, 14));
	                lblNoRecensioni.setForeground(Color.GRAY);
	                lblNoRecensioni.setAlignmentX(Component.CENTER_ALIGNMENT);
	                panelRecensioni.add(Box.createVerticalStrut(50));
	                panelRecensioni.add(lblNoRecensioni);
	            } else {
	                for (Recensione_entity rec : recensioni) {
	                    panelRecensioni.add(creaCardRecensione(rec, recensioneDAO));
	                    panelRecensioni.add(Box.createVerticalStrut(15));
	                }
	            }

	        } catch (SQLException e) {
	            System.err.println("Errore nel caricamento delle recensioni: " + e.getMessage());
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this,
	                    "Errore nel caricamento delle recensioni: " + e.getMessage(),
	                    "Errore",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private JPanel creaCardRecensione(Recensione_entity rec, RecensioneVenditoreDAO dao) {
	        JPanel card = new JPanel();
	        card.setLayout(new BorderLayout(10, 10));
	        card.setBackground(Color.WHITE);
	        card.setBorder(new CompoundBorder(
	                new LineBorder(new Color(200, 200, 200), 1, true),
	                new EmptyBorder(15, 15, 15, 15)
	        ));
	        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

	        // Header con stelle e data
	        JPanel headerPanel = new JPanel(new BorderLayout());
	        headerPanel.setBackground(Color.WHITE);

	        // Stelle
	        JPanel panelStelle = creaPannelloStelle(rec.getPunteggio());

	        // Data
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        JLabel lblData = new JLabel(sdf.format(rec.getData()));
	        lblData.setFont(new Font("Verdana", Font.PLAIN, 12));
	        lblData.setForeground(Color.GRAY);

	        headerPanel.add(panelStelle, BorderLayout.WEST);
	        headerPanel.add(lblData, BorderLayout.EAST);

	        // ============ PANNELLO CENTRALE (titolo annuncio + commento) ============
	        JPanel centralPanel = new JPanel();
	        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
	        centralPanel.setBackground(Color.WHITE);

	        // Titolo annuncio
	        try {
	            String titoloAnnuncio = dao.getTitoloAnnuncioDaOfferta(rec.getIdOfferta());
	            if (titoloAnnuncio != null) {
	                JLabel lblTitoloAnnuncio = new JLabel("Annuncio: " + titoloAnnuncio);
	                lblTitoloAnnuncio.setFont(new Font("Verdana", Font.BOLD, 13));
	                lblTitoloAnnuncio.setForeground(new Color(0, 52, 104));
	                lblTitoloAnnuncio.setBorder(new EmptyBorder(0, 0, 8, 0));
	                centralPanel.add(lblTitoloAnnuncio);
	            }
	        } catch (SQLException e) {
	            System.err.println("Errore nel recupero titolo annuncio: " + e.getMessage());
	        }

	        // Commento
	        JTextArea txtCommento = new JTextArea(rec.getCommento() != null ? rec.getCommento() : "Nessun commento");
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

	        try {
	            String nomeAcquirente = dao.getNominativoUtente(rec.getMatricolaAcquirente());
	            JLabel lblAcquirente = new JLabel("— " + (nomeAcquirente != null ? nomeAcquirente : "Utente"));
	            lblAcquirente.setFont(new Font("Verdana", Font.ITALIC, 12));
	            lblAcquirente.setForeground(new Color(100, 100, 100));
	            footerPanel.add(lblAcquirente);

	            // Aggiungi matricola acquirente
	            JLabel lblMatricola = new JLabel("(" + rec.getMatricolaAcquirente() + ")");
	            lblMatricola.setFont(new Font("Verdana", Font.PLAIN, 11));
	            lblMatricola.setForeground(new Color(120, 120, 120));
	            footerPanel.add(lblMatricola);
	        } catch (SQLException e) {
	            System.err.println("Errore nel recupero nome acquirente: " + e.getMessage());
	        }

	        card.add(headerPanel, BorderLayout.NORTH);
	        card.add(centralPanel, BorderLayout.CENTER);
	        card.add(footerPanel, BorderLayout.SOUTH);

	        return card;
	    }

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
