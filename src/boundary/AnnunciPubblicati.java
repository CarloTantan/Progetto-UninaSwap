package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Annunci_OfferteDAO;
import dao.ListaAnnunciDAO;
import entity.Annuncio_entity;
import entity.Offerta_entity;
import entity.Transazione_entity;
import entity.Utente_entity;
import mainController.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.CardLayout;

public class AnnunciPubblicati extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Utente_entity UtenteLoggato;
    private JTable tabellaAnnunci;
    private JTable tabellaOfferta;
    private ArrayList<Annuncio_entity> listaAnnunci;
    private ArrayList<Offerta_entity> listaOfferte;
    private JScrollPane scrollPaneAnnunci;
    private JScrollPane scrollPaneOfferte;
    private JButton btnVisualizzaOfferte;
    private JButton btnTornaAnnunci;
    private JButton btnAccetta;
    private JButton btnRifiuta;
    private JPanel panelBottoni;
    private JPanel panelBottoniOfferte;
    private JLabel lblTitolo;
    private MainController controller;
	    
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OfferteRicevute frame = new OfferteRicevute();
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
	public AnnunciPubblicati(Utente_entity UtenteLoggato, MainController controller) {
	    this.UtenteLoggato = UtenteLoggato;
	    this.listaAnnunci = new ArrayList<>();
	    this.controller = controller;
	    
	    setIconImage(Toolkit.getDefaultToolkit().getImage(AnnunciPubblicati.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
	    setTitle("Annunci Pubblicati");
	    setBackground(new Color(255, 255, 255));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setMinimumSize(new Dimension(1200, 600));
	    
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(255, 255, 255));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new BorderLayout());
	    
	    // Panel header
	    JPanel panelHeader = new JPanel();
	    panelHeader.setBackground(new Color(45, 134, 192));
	    panelHeader.setPreferredSize(new Dimension(0, 80));
	    contentPane.add(panelHeader, BorderLayout.NORTH);
	    panelHeader.setLayout(new BorderLayout());
	    
	    JButton btnUndo = new JButton("");
	    btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
	    btnUndo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	            AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato, controller);
	            AreaUtenteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	            AreaUtenteFrame.setVisible(true);
	        }
	    });
	    btnUndo.setBackground(new Color(45, 134, 192));
	    btnUndo.setIcon(new ImageIcon(
	        AnnunciPubblicati.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
	    btnUndo.setPreferredSize(new Dimension(95, 80));
	    btnUndo.setFocusPainted(false);
	    btnUndo.setBorderPainted(false);
	    panelHeader.add(btnUndo, BorderLayout.WEST);
	    
	    btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnUndo.setBackground(new Color(65, 154, 212));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnUndo.setBackground(new Color(45, 134, 192));
	        }
	    });
	    
	    JLabel lblTitolo = new JLabel("Annunci Pubblicati");
	    lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
	    lblTitolo.setForeground(Color.WHITE);
	    lblTitolo.setHorizontalAlignment(JLabel.CENTER);
	    panelHeader.add(lblTitolo, BorderLayout.CENTER);
	    
	    // Rendi lblTitolo accessibile ai metodi (aggiungi come variabile di istanza)
	    this.lblTitolo = lblTitolo;
	    
	    // Panel centrale con CardLayout per switchare tra tabelle
	    JPanel panelCentrale = new JPanel();
	    panelCentrale.setBackground(Color.WHITE);
	    panelCentrale.setLayout(new CardLayout());
	    panelCentrale.setBorder(new EmptyBorder(10, 10, 10, 10));
	    contentPane.add(panelCentrale, BorderLayout.CENTER);
	    
	    // ===== CARD 1: ANNUNCI =====
	    JPanel cardAnnunci = new JPanel(new BorderLayout(10, 10));
	    cardAnnunci.setBackground(Color.WHITE);
	    
	    // Tabella Annunci
	    DefaultTableModel modelTabellaAnnunci = new DefaultTableModel(
	            new Object[][]{},
	            new String[]{"Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna",
	                "Stato", "Data", "Categoria"}
	        ) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        
	    tabellaAnnunci = new JTable(modelTabellaAnnunci);
	    tabellaAnnunci.setBackground(Color.WHITE);
	    tabellaAnnunci.setFont(new Font("Verdana", Font.PLAIN, 12));
	    tabellaAnnunci.setRowHeight(30);
	    tabellaAnnunci.getTableHeader().setReorderingAllowed(false);
	    tabellaAnnunci.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
	    
	    scrollPaneAnnunci = new JScrollPane(tabellaAnnunci);
	    cardAnnunci.add(scrollPaneAnnunci, BorderLayout.CENTER);
	    
	    // ===== CARD 2: OFFERTE =====
	    JPanel cardOfferte = new JPanel(new BorderLayout(10, 10));
	    cardOfferte.setBackground(Color.WHITE);
	    
	    // Tabella Offerte
	    DefaultTableModel modelTabellaOfferte = new DefaultTableModel(
	            new Object[][]{},
	            new String[]{"Stato", "Matricola Acquirente", "Tipologia"}
	        ) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        
	    tabellaOfferta = new JTable(modelTabellaOfferte);
	    tabellaOfferta.setBackground(Color.WHITE);
	    tabellaOfferta.setFont(new Font("Verdana", Font.PLAIN, 12));
	    tabellaOfferta.setRowHeight(30);
	    tabellaOfferta.getTableHeader().setReorderingAllowed(false);
	    tabellaOfferta.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
	    
	    scrollPaneOfferte = new JScrollPane(tabellaOfferta);
	    cardOfferte.add(scrollPaneOfferte, BorderLayout.CENTER);
	    
	    // Aggiungi le card al CardLayout
	    panelCentrale.add(cardAnnunci, "ANNUNCI");
	    panelCentrale.add(cardOfferte, "OFFERTE");
	    
	    // Panel contenitore per i bottoni (entrambe le viste)
	    JPanel panelBottoniContainer = new JPanel();
	    panelBottoniContainer.setBackground(Color.WHITE);
	    panelBottoniContainer.setPreferredSize(new Dimension(0, 80));
	    panelBottoniContainer.setLayout(new CardLayout());
	    contentPane.add(panelBottoniContainer, BorderLayout.SOUTH);
	    
	    // Panel bottoni per annunci
	    panelBottoni = new JPanel();
	    panelBottoni.setBackground(Color.WHITE);
	    panelBottoni.setLayout(new GridBagLayout());
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10);
	    
	    btnVisualizzaOfferte = new JButton("Visualizza Offerte");
	    btnVisualizzaOfferte.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            SelezionaAnnuncio();
	        }
	    });
	    btnVisualizzaOfferte.setFont(new Font("Verdana", Font.BOLD, 16));
	    btnVisualizzaOfferte.setBackground(new Color(0, 52, 101));
	    btnVisualizzaOfferte.setForeground(new Color(255, 255, 255));
	    btnVisualizzaOfferte.setPreferredSize(new Dimension(250, 50));
	    btnVisualizzaOfferte.setFocusPainted(false);
	    btnVisualizzaOfferte.setBorderPainted(false);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    panelBottoni.add(btnVisualizzaOfferte, gbc);
	    
	    btnVisualizzaOfferte.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnVisualizzaOfferte.setBackground(new Color(0, 70, 140));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnVisualizzaOfferte.setBackground(new Color(0, 52, 101));
	        }
	    });
	    
	    // Panel bottoni per offerte
	    panelBottoniOfferte = new JPanel();
	    panelBottoniOfferte.setBackground(Color.WHITE);
	    panelBottoniOfferte.setLayout(new GridBagLayout());
	    
	    GridBagConstraints gbcOfferte = new GridBagConstraints();
	    gbcOfferte.insets = new Insets(10, 10, 10, 10);
	    
	    btnAccetta = new JButton("Accetta");
	    btnAccetta.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            accettaOfferta();
	        }
	    });
	    btnAccetta.setFont(new Font("Verdana", Font.BOLD, 16));
	    btnAccetta.setBackground(new Color(0, 52, 101));
	    btnAccetta.setForeground(new Color(255, 255, 255));
	    btnAccetta.setPreferredSize(new Dimension(150, 50));
	    btnAccetta.setFocusPainted(false);
	    btnAccetta.setBorderPainted(false);
	    gbcOfferte.gridx = 0;
	    gbcOfferte.gridy = 0;
	    panelBottoniOfferte.add(btnAccetta, gbcOfferte);
	    
	    btnAccetta.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnAccetta.setBackground(new Color(0, 70, 140));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnAccetta.setBackground(new Color(0, 52, 101));
	        }
	    });
	    
	    btnTornaAnnunci = new JButton("Torna agli Annunci");
	    btnTornaAnnunci.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            mostraAnnunci();
	        }
	    });
	    btnTornaAnnunci.setFont(new Font("Verdana", Font.BOLD, 16));
	    btnTornaAnnunci.setBackground(new Color(0, 52, 101));
	    btnTornaAnnunci.setForeground(new Color(255, 255, 255));
	    btnTornaAnnunci.setPreferredSize(new Dimension(230, 50));
	    btnTornaAnnunci.setFocusPainted(false);
	    btnTornaAnnunci.setBorderPainted(false);
	    gbcOfferte.gridx = 1;
	    gbcOfferte.gridy = 0;
	    panelBottoniOfferte.add(btnTornaAnnunci, gbcOfferte);
	    
	    btnTornaAnnunci.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnTornaAnnunci.setBackground(new Color(0, 70, 140));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnTornaAnnunci.setBackground(new Color(0, 52, 101));
	        }
	    });

	    btnRifiuta = new JButton("Rifiuta");
	    btnRifiuta.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            rifiutaOfferta();
	        }
	    });
	    btnRifiuta.setForeground(Color.WHITE);
	    btnRifiuta.setFont(new Font("Verdana", Font.BOLD, 16));
	    btnRifiuta.setBackground(new Color(0, 52, 101));
	    btnRifiuta.setPreferredSize(new Dimension(150, 50));
	    btnRifiuta.setFocusPainted(false);
	    btnRifiuta.setBorderPainted(false);
	    gbcOfferte.gridx = 2;
	    gbcOfferte.gridy = 0;
	    panelBottoniOfferte.add(btnRifiuta, gbcOfferte);
	    
	    btnRifiuta.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnRifiuta.setBackground(new Color(0, 70, 140));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnRifiuta.setBackground(new Color(0, 52, 101));
	        }
	    });
	    
	    // Aggiungi i panel bottoni al CardLayout
	    panelBottoniContainer.add(panelBottoni, "BOTTONI_ANNUNCI");
	    panelBottoniContainer.add(panelBottoniOfferte, "BOTTONI_OFFERTE");
	    
	    // Carica i dati
	    caricaAnnunci();
	    

	}
	
	private void caricaAnnunci() {
		String matricola = UtenteLoggato.getMatricola();
	    try {
	    	Annunci_OfferteDAO selectAnnunci = new Annunci_OfferteDAO();
	    	listaAnnunci = selectAnnunci.getAnnunci(matricola);

	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
	        model.setRowCount(0);
	        
	        for (Annuncio_entity A : listaAnnunci) {
                model.addRow(new Object[]{
                    A.getTitolo(),
                    A.getDescrizione(),
                    A.getFasciaOraria(),
                    A.getModalitàConsegna(),
                    A.getStatoAnnuncio(),
                    A.getDataPubblicazione(),
                    A.getTipologiaCategoria()
                });
            }

	    } catch (SQLException e) {
            System.err.println("Errore durante il caricamento degli Annunci: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento dei dati: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void SelezionaAnnuncio() {
        int selectedRow = tabellaAnnunci.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un annuncio dalla tabella!",
                "Nessuna selezione",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Annuncio_entity AnnuncioSelezionato = listaAnnunci.get(selectedRow);
        
        if (!AnnuncioSelezionato.getVisualizzaOfferte()) {
            JOptionPane.showMessageDialog(this,
                "Non ci sono offerte per questo annuncio",
                "Nessuna offerta",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        caricaOfferte(AnnuncioSelezionato.getIdAnnuncio());
    }
	
	private void caricaOfferte(int IdAnnuncio) {
	    try {
	    	Annunci_OfferteDAO selectOfferte = new Annunci_OfferteDAO();
	    	listaOfferte = selectOfferte.getOfferte(IdAnnuncio);

	        DefaultTableModel model = (DefaultTableModel) tabellaOfferta.getModel();
	        model.setRowCount(0);

	        for (Offerta_entity O : listaOfferte) {
	            model.addRow(new Object[]{
	                O.getStatoOfferta(),
	                O.getMatricolaAcquirente(),
	                O.getTipologiaOfferta()
	            });
	        }
	        
	        mostraOfferte();

	        if (listaOfferte.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Nessuna offerta trovata",
                    "Informazione",
                    JOptionPane.INFORMATION_MESSAGE);
            }

	    } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle offerte: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento delle offerte: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void mostraOfferte() {
	    // Cambia il titolo
	    lblTitolo.setText("Offerte Ricevute");
	    
	    CardLayout clCentro = (CardLayout) ((JPanel) contentPane.getComponent(1)).getLayout();
	    clCentro.show((JPanel) contentPane.getComponent(1), "OFFERTE");
	    
	    CardLayout clBottoni = (CardLayout) ((JPanel) contentPane.getComponent(2)).getLayout();
	    clBottoni.show((JPanel) contentPane.getComponent(2), "BOTTONI_OFFERTE");
	}

	private void mostraAnnunci() {
	    // Ripristina il titolo originale
	    lblTitolo.setText("Annunci Pubblicati");
	    
	    CardLayout clCentro = (CardLayout) ((JPanel) contentPane.getComponent(1)).getLayout();
	    clCentro.show((JPanel) contentPane.getComponent(1), "ANNUNCI");
	    
	    CardLayout clBottoni = (CardLayout) ((JPanel) contentPane.getComponent(2)).getLayout();
	    clBottoni.show((JPanel) contentPane.getComponent(2), "BOTTONI_ANNUNCI");
	}

    private void accettaOfferta() {
        int selectedRow = tabellaOfferta.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un'offerta dalla tabella!",
                "Nessuna selezione",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Offerta_entity offertaSelezionata = listaOfferte.get(selectedRow);
        
        int conferma = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler accettare questa offerta?\n" +
                "L'annuncio verrà chiuso e tutte le altre offerte verranno rifiutate.",
                "Conferma accettazione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
        if (conferma != JOptionPane.YES_OPTION) {
            return;
        }
        
        try {
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            boolean successo = dao.accettaOfferta(offertaSelezionata.getIdOfferta());
            
            if (successo) {
                JOptionPane.showMessageDialog(this,
                    "Offerta accettata con successo!\n" +
                    "L'annuncio è stato chiuso automaticamente.",
                    "Offerta accettata",
                    JOptionPane.INFORMATION_MESSAGE);
                
                mostraAnnunci();
                caricaAnnunci();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Errore: offerta non trovata o già gestita",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            System.err.println("Errore durante l'accettazione dell'offerta: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore durante l'accettazione: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rifiutaOfferta() {
        int selectedRow = tabellaOfferta.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un'offerta dalla tabella!",
                "Nessuna selezione",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Offerta_entity offertaSelezionata = listaOfferte.get(selectedRow);
        
        int conferma = JOptionPane.showConfirmDialog(this,
            "Sei sicuro di voler rifiutare questa offerta?",
            "Conferma rifiuto",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (conferma != JOptionPane.YES_OPTION) {
            return;
        }
        
        try {
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            boolean successo = dao.rifiutaOfferta(offertaSelezionata.getIdOfferta());
            
            if (successo) {
                JOptionPane.showMessageDialog(this,
                    "Offerta rifiutata con successo",
                    "Offerta rifiutata",
                    JOptionPane.INFORMATION_MESSAGE);
                
                listaOfferte.remove(selectedRow);
                DefaultTableModel model = (DefaultTableModel) tabellaOfferta.getModel();
                model.removeRow(selectedRow);
                
                if (listaOfferte.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Non ci sono più offerte da visualizzare",
                        "Informazione",
                        JOptionPane.INFORMATION_MESSAGE);
                    mostraAnnunci();
                    caricaAnnunci();
                }
                
            } else {
                JOptionPane.showMessageDialog(this,
                    "Errore: offerta non trovata o già gestita",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            System.err.println("Errore durante il rifiuto dell'offerta: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore durante il rifiuto: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}