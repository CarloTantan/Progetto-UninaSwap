package boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mainController.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class AnnunciPubblicati extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private MainController controller;
    private JPanel panelAnnunci;
    private JPanel panelOfferte;
    private JLabel lblTitolo;
    private int annuncioCorrenteId = -1;
	    
	/**
	 * Create the frame.
	 */
	public AnnunciPubblicati(MainController controller) {
        this.controller = controller;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            AnnunciPubblicati.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Annunci Pubblicati");
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1200, 600));
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        // ===== HEADER =====
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(45, 134, 192));
        panelHeader.setPreferredSize(new Dimension(0, 80));
        panelHeader.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPane.add(panelHeader, BorderLayout.NORTH);
        panelHeader.setLayout(new BorderLayout());
        
        JButton btnUndo = new JButton(new ImageIcon(
            AnnunciPubblicati.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
        btnUndo.addActionListener(e -> {
            dispose();
            AreaUtente AreaUtenteFrame = new AreaUtente(controller);
            AreaUtenteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            AreaUtenteFrame.setVisible(true);
        });
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setBorderPainted(false);
        btnUndo.setFocusPainted(false);
        panelHeader.add(btnUndo, BorderLayout.WEST);
        
        btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUndo.setBackground(new Color(65, 154, 212));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUndo.setBackground(new Color(45, 134, 192));
            }
        });
        
        lblTitolo = new JLabel("I Tuoi Annunci Pubblicati", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitolo.setForeground(Color.WHITE);
        panelHeader.add(lblTitolo, BorderLayout.CENTER);
        
        // ===== PANEL CENTRALE CON CARDLAYOUT =====
        JPanel panelCentrale = new JPanel(new CardLayout());
        panelCentrale.setBackground(Color.WHITE);
        contentPane.add(panelCentrale, BorderLayout.CENTER);
        
        // Card Annunci
        panelAnnunci = new JPanel();
        panelAnnunci.setLayout(new BoxLayout(panelAnnunci, BoxLayout.Y_AXIS));
        panelAnnunci.setBackground(Color.WHITE);
        panelAnnunci.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JScrollPane scrollPaneAnnunci = new JScrollPane(panelAnnunci);
        scrollPaneAnnunci.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneAnnunci.setBorder(null);
        
        // Card Offerte
        panelOfferte = new JPanel();
        panelOfferte.setLayout(new BoxLayout(panelOfferte, BoxLayout.Y_AXIS));
        panelOfferte.setBackground(Color.WHITE);
        panelOfferte.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JScrollPane scrollPaneOfferte = new JScrollPane(panelOfferte);
        scrollPaneOfferte.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneOfferte.setBorder(null);
        
        panelCentrale.add(scrollPaneAnnunci, "ANNUNCI");
        panelCentrale.add(scrollPaneOfferte, "OFFERTE");
        
        mostraAnnunci();
    }
    

	private void mostraAnnunci() {
	    panelAnnunci.removeAll();
	    lblTitolo.setText("I Tuoi Annunci Pubblicati");
	    
	    int numeroAnnunci = controller.getNumeroAnnunciPubblicati();
	    
	    if (numeroAnnunci == 0) {
	        JLabel lblNoAnnunci = new JLabel("Non hai ancora pubblicato annunci");
	        lblNoAnnunci.setFont(new Font("Verdana", Font.ITALIC, 16));
	        lblNoAnnunci.setForeground(Color.GRAY);
	        lblNoAnnunci.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panelAnnunci.add(Box.createVerticalStrut(100));
	        panelAnnunci.add(lblNoAnnunci);
	    } else {
	        for (int i = 0; i < numeroAnnunci; i++) {
	            panelAnnunci.add(creaCardAnnuncio(i));
	            panelAnnunci.add(Box.createVerticalStrut(15));
	        }
	    }
	    
	    CardLayout cl = (CardLayout) ((JPanel) contentPane.getComponent(1)).getLayout();
	    cl.show((JPanel) contentPane.getComponent(1), "ANNUNCI");
	    
	    panelAnnunci.revalidate();
	    panelAnnunci.repaint();
	}

    
	private JPanel creaCardAnnuncio(int index) {
	    JPanel card = new JPanel(new BorderLayout(15, 15));
	    card.setBackground(Color.WHITE);
	    card.setBorder(new CompoundBorder(
	        new LineBorder(new Color(200, 200, 200), 1, true),
	        new EmptyBorder(20, 20, 20, 20)
	    ));
	    card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 280));
	    
	    // ===== PANNELLO SINISTRO =====
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	    leftPanel.setBackground(Color.WHITE);
	    
	    // Titolo
	    JLabel lblTitoloAnnuncio = new JLabel(controller.getTitoloAnnuncioPubblicato(index));
	    lblTitoloAnnuncio.setFont(new Font("Verdana", Font.BOLD, 16));
	    lblTitoloAnnuncio.setAlignmentX(Component.LEFT_ALIGNMENT);
	    leftPanel.add(lblTitoloAnnuncio);
	    leftPanel.add(Box.createVerticalStrut(10));
	    
	    // Descrizione completa
	    JLabel lblDescrizione = new JLabel("<html><div style='width:500px;'>" + 
	        controller.getDescrizioneAnnuncioPubblicato(index) + "</div></html>");
	    lblDescrizione.setFont(new Font("Verdana", Font.PLAIN, 12));
	    lblDescrizione.setForeground(new Color(80, 80, 80));
	    lblDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);
	    leftPanel.add(lblDescrizione);
	    leftPanel.add(Box.createVerticalStrut(12));
	    
	    // Info aggiuntive
	    JPanel infoPanel = new JPanel();
	    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	    infoPanel.setBackground(Color.WHITE);
	    infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    JLabel lblCategoria = new JLabel("Categoria: " + controller.getCategoriaAnnuncioPubblicato(index));
	    lblCategoria.setFont(new Font("Verdana", Font.PLAIN, 11));
	    lblCategoria.setForeground(new Color(100, 100, 100));
	    lblCategoria.setAlignmentX(Component.LEFT_ALIGNMENT);
	    infoPanel.add(lblCategoria);
	    
	    JLabel lblModalita = new JLabel("Modalità di Consegna: " + controller.getModalitaConsegnaAnnuncioPubblicato(index));
	    lblModalita.setFont(new Font("Verdana", Font.PLAIN, 11));
	    lblModalita.setForeground(new Color(100, 100, 100));
	    lblModalita.setAlignmentX(Component.LEFT_ALIGNMENT);
	    infoPanel.add(lblModalita);
	    
	    JLabel lblFascia = new JLabel("Fascia oraria: " + controller.getFasciaOrariaAnnuncioPubblicato(index));
	    lblFascia.setFont(new Font("Verdana", Font.PLAIN, 11));
	    lblFascia.setForeground(new Color(100, 100, 100));
	    lblFascia.setAlignmentX(Component.LEFT_ALIGNMENT);
	    infoPanel.add(lblFascia);
	    
	    JLabel lblData = new JLabel("Pubblicato il: " + controller.getDataPubblicazioneAnnuncio(index));
	    lblData.setFont(new Font("Verdana", Font.PLAIN, 11));
	    lblData.setForeground(new Color(100, 100, 100));
	    lblData.setAlignmentX(Component.LEFT_ALIGNMENT);
	    infoPanel.add(lblData);
	    
	    leftPanel.add(infoPanel);
	    
	    // ===== PANNELLO DESTRO =====
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
	    rightPanel.setBackground(Color.WHITE);
	    rightPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
	    
	    // Badge Stato
	    String stato = controller.getStatoAnnuncioPubblicato(index);
	    JLabel lblStato = new JLabel(stato);
	    lblStato.setFont(new Font("Verdana", Font.BOLD, 13));
	    lblStato.setForeground(Color.WHITE);
	    lblStato.setOpaque(true);
	    lblStato.setBorder(new EmptyBorder(8, 15, 8, 15));
	    lblStato.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    Color coloreStato = stato.equalsIgnoreCase("Attivo")
	        ? new Color(40, 167, 69)
	        : new Color(108, 117, 125);
	    lblStato.setBackground(coloreStato);
	    
	    rightPanel.add(lblStato);
	    rightPanel.add(Box.createVerticalStrut(15));
	    
	    // Indicatore offerte
	    boolean hasOfferte = controller.hasNuoveOfferteAnnuncio(index);
	    if (hasOfferte) {
	        JPanel offerteIndicator = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	        offerteIndicator.setBackground(Color.WHITE);
	        offerteIndicator.setMaximumSize(new Dimension(200, 30));
	        
	        JLabel lblTestoOfferte = new JLabel("Nuove offerte");
	        lblTestoOfferte.setFont(new Font("Verdana", Font.BOLD, 12));
	        lblTestoOfferte.setForeground(new Color(255, 193, 7));
	        
	        offerteIndicator.add(lblTestoOfferte);
	        
	        rightPanel.add(offerteIndicator);
	        rightPanel.add(Box.createVerticalStrut(10));
	    }
	    
	    // Bottone Visualizza Offerte
	    JButton btnVisualizzaOfferte = new JButton("Visualizza Offerte");
	    btnVisualizzaOfferte.setFont(new Font("Verdana", Font.BOLD, 12));
	    btnVisualizzaOfferte.setBackground(new Color(0, 52, 101));
	    btnVisualizzaOfferte.setForeground(Color.WHITE);
	    btnVisualizzaOfferte.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnVisualizzaOfferte.setMaximumSize(new Dimension(180, 40));
	    btnVisualizzaOfferte.setFocusPainted(false);
	    btnVisualizzaOfferte.setBorderPainted(false);
	    btnVisualizzaOfferte.setEnabled(hasOfferte);
	    
	    if (!hasOfferte) {
	        btnVisualizzaOfferte.setBackground(Color.GRAY);
	        btnVisualizzaOfferte.setText("Nessuna offerta");
	    } else {
	        btnVisualizzaOfferte.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                btnVisualizzaOfferte.setBackground(new Color(0, 70, 140));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                btnVisualizzaOfferte.setBackground(new Color(0, 52, 101));
	            }
	        });
	    }
	    
	    int idAnnuncio = controller.getIdAnnuncioPubblicato(index);
	    btnVisualizzaOfferte.addActionListener(e -> {
	        annuncioCorrenteId = idAnnuncio;
	        caricaOfferte(idAnnuncio);
	    });
	    
	    rightPanel.add(btnVisualizzaOfferte);
	    
	    card.add(leftPanel, BorderLayout.CENTER);
	    card.add(rightPanel, BorderLayout.EAST);
	    
	    return card;
	}
    
	private void caricaOfferte(int idAnnuncio) {
	    mostraOfferte();

	    if (controller.getNumeroOfferteRicevute(annuncioCorrenteId) == 0) {
	        JOptionPane.showMessageDialog(this,
	            "Nessuna offerta trovata",
	            "Informazione",
	            JOptionPane.INFORMATION_MESSAGE);
	        mostraAnnunci();
	    }
	}

	private void mostraOfferte() {
	    panelOfferte.removeAll();
	    lblTitolo.setText("Offerte Ricevute");
	    
	    int numeroOfferte = controller.getNumeroOfferteRicevute(annuncioCorrenteId);
	    
	    if (numeroOfferte == 0) {
	        JLabel lblNoOfferte = new JLabel("Nessuna offerta in attesa");
	        lblNoOfferte.setFont(new Font("Verdana", Font.ITALIC, 16));
	        lblNoOfferte.setForeground(Color.GRAY);
	        lblNoOfferte.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panelOfferte.add(Box.createVerticalStrut(100));
	        panelOfferte.add(lblNoOfferte);
	    } else {
	        // Pannello info annuncio corrente
	        JPanel infoAnnuncioPanel = creaInfoAnnuncioCorrente();
	        infoAnnuncioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        panelOfferte.add(infoAnnuncioPanel);
	        panelOfferte.add(Box.createVerticalStrut(20));
	        
	        for (int i = 0; i < numeroOfferte; i++) {
	            panelOfferte.add(creaCardOfferta(i));
	            panelOfferte.add(Box.createVerticalStrut(15));
	        }
	    }
	    
	    CardLayout cl = (CardLayout) ((JPanel) contentPane.getComponent(1)).getLayout();
	    cl.show((JPanel) contentPane.getComponent(1), "OFFERTE");
	    
	    panelOfferte.revalidate();
	    panelOfferte.repaint();
	}
    
	private JPanel creaInfoAnnuncioCorrente() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setBackground(new Color(240, 248, 255));
	    panel.setBorder(new CompoundBorder(
	        new LineBorder(new Color(0, 123, 255), 2, true),
	        new EmptyBorder(15, 20, 15, 20)
	    ));
	    panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
	    
	    // Trova l'indice dell'annuncio corrente
	    int indexAnnuncio = -1;
	    for (int i = 0; i < controller.getNumeroAnnunciPubblicati(); i++) {
	        if (controller.getIdAnnuncioPubblicato(i) == annuncioCorrenteId) {
	            indexAnnuncio = i;
	            break;
	        }
	    }
	    
	    if (indexAnnuncio >= 0) {
	        JLabel lblTitolo = new JLabel("Annuncio: " + controller.getTitoloAnnuncioPubblicato(indexAnnuncio));
	        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 14));
	        lblTitolo.setAlignmentX(Component.LEFT_ALIGNMENT);
	        panel.add(lblTitolo);
	        panel.add(Box.createVerticalStrut(5));
	        
	        JLabel lblDescrizione = new JLabel("<html>" + controller.getDescrizioneAnnuncioPubblicato(indexAnnuncio) + "</html>");
	        lblDescrizione.setFont(new Font("Verdana", Font.PLAIN, 12));
	        lblDescrizione.setForeground(new Color(80, 80, 80));
	        lblDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);
	        panel.add(lblDescrizione);
	    }
	    
	    // Aggiungi il bottone "Torna agli Annunci"
	    panel.add(Box.createVerticalStrut(10));
	    
	    JButton btnTornaAnnunci = new JButton("Torna agli Annunci");
	    btnTornaAnnunci.setFont(new Font("Verdana", Font.BOLD, 11));
	    btnTornaAnnunci.setBackground(new Color(108, 117, 125));
	    btnTornaAnnunci.setForeground(Color.WHITE);
	    btnTornaAnnunci.setAlignmentX(Component.LEFT_ALIGNMENT);
	    btnTornaAnnunci.setMaximumSize(new Dimension(180, 30));
	    btnTornaAnnunci.setFocusPainted(false);
	    btnTornaAnnunci.setBorderPainted(false);
	    btnTornaAnnunci.addActionListener(e -> mostraAnnunci());
	    
	    btnTornaAnnunci.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnTornaAnnunci.setBackground(new Color(90, 98, 104));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnTornaAnnunci.setBackground(new Color(108, 117, 125));
	        }
	    });
	    
	    panel.add(btnTornaAnnunci);
	    
	    return panel;
	}
    
	private JPanel creaCardOfferta(int index) {
	    JPanel card = new JPanel(new BorderLayout(15, 15));
	    card.setBackground(Color.WHITE);
	    card.setBorder(new CompoundBorder(
	        new LineBorder(new Color(200, 200, 200), 1, true),
	        new EmptyBorder(20, 20, 20, 20)
	    ));
	    card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
	    
	    String tipologia = controller.getTipologiaOffertaRicevuta(annuncioCorrenteId, index);
	    String matricolaAcquirente = controller.getMatricolaAcquirenteOfferta(annuncioCorrenteId, index);
	    int idOfferta = controller.getIdOffertaRicevuta(annuncioCorrenteId, index);
	    
	    // ===== PANNELLO SINISTRO =====
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	    leftPanel.setBackground(Color.WHITE);
	    
	    // Badge Tipologia
	    JLabel lblTipologia = new JLabel(tipologia);
	    lblTipologia.setFont(new Font("Verdana", Font.BOLD, 12));
	    lblTipologia.setForeground(Color.WHITE);
	    lblTipologia.setOpaque(true);
	    lblTipologia.setBorder(new EmptyBorder(5, 10, 5, 10));
	    lblTipologia.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    Color coloreTipologia = switch(tipologia.toLowerCase()) {
	        case "vendita" -> new Color(56, 209, 97);
	        case "scambio" -> new Color(108, 67, 232);
	        case "regalo" -> new Color(209, 56, 56);
	        default -> new Color(108, 117, 125);
	    };
	    lblTipologia.setBackground(coloreTipologia);
	    
	    leftPanel.add(lblTipologia);
	    leftPanel.add(Box.createVerticalStrut(12));
	    
	    // Info acquirente
	    JPanel acquistentePanel = creaInfoAcquirente(matricolaAcquirente);
	    acquistentePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    leftPanel.add(acquistentePanel);
	    leftPanel.add(Box.createVerticalStrut(10));
	    
	    // Dettaglio offerta in base alla tipologia
	    JPanel dettaglioPanel = new JPanel();
	    dettaglioPanel.setLayout(new BoxLayout(dettaglioPanel, BoxLayout.Y_AXIS));
	    dettaglioPanel.setBackground(Color.WHITE);
	    dettaglioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    if (tipologia.equalsIgnoreCase("vendita")) {
	        String importo = controller.getDettaglioOffertaVendita(annuncioCorrenteId, index);
	        if (importo != null && !importo.isEmpty()) {
	            JLabel lblImporto = new JLabel("Importo proposto: €" + importo);
	            lblImporto.setFont(new Font("Verdana", Font.BOLD, 13));
	            lblImporto.setForeground(new Color(40, 167, 69));
	            lblImporto.setAlignmentX(Component.LEFT_ALIGNMENT);
	            dettaglioPanel.add(lblImporto);
	        }
	    } else if (tipologia.equalsIgnoreCase("scambio")) {
	        String oggetto = controller.getDettaglioOffertaScambio(annuncioCorrenteId, index);
	        if (oggetto != null && !oggetto.isEmpty()) {
	            JLabel lblOggetto = new JLabel("Oggetto proposto: " + oggetto);
	            lblOggetto.setFont(new Font("Verdana", Font.PLAIN, 12));
	            lblOggetto.setForeground(new Color(80, 80, 80));
	            lblOggetto.setAlignmentX(Component.LEFT_ALIGNMENT);
	            dettaglioPanel.add(lblOggetto);
	        }
	    } else if (tipologia.equalsIgnoreCase("regalo")) {
	        String messaggio = controller.getDettaglioOffertaRegalo(annuncioCorrenteId, index);
	        if (messaggio != null && !messaggio.isEmpty()) {
	            JLabel lblMessaggio = new JLabel("<html><i>\"" + messaggio + "\"</i></html>");
	            lblMessaggio.setFont(new Font("Verdana", Font.ITALIC, 11));
	            lblMessaggio.setForeground(new Color(100, 100, 100));
	            lblMessaggio.setAlignmentX(Component.LEFT_ALIGNMENT);
	            dettaglioPanel.add(lblMessaggio);
	        }
	    }
	    
	    leftPanel.add(dettaglioPanel);
	    
	    // ===== PANNELLO DESTRO =====
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
	    rightPanel.setBackground(Color.WHITE);
	    rightPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
	    
	    // Bottoni azione
	    JButton btnAccetta = new JButton("Accetta");
	    btnAccetta.setFont(new Font("Verdana", Font.BOLD, 12));
	    btnAccetta.setBackground(new Color(40, 167, 69));
	    btnAccetta.setForeground(Color.WHITE);
	    btnAccetta.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnAccetta.setMaximumSize(new Dimension(140, 40));
	    btnAccetta.setFocusPainted(false);
	    btnAccetta.setBorderPainted(false);
	    btnAccetta.addActionListener(e -> accettaOfferta(idOfferta));
	    
	    btnAccetta.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnAccetta.setBackground(new Color(33, 136, 56));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnAccetta.setBackground(new Color(40, 167, 69));
	        }
	    });
	    
	    JButton btnRifiuta = new JButton("Rifiuta");
	    btnRifiuta.setFont(new Font("Verdana", Font.BOLD, 12));
	    btnRifiuta.setBackground(new Color(220, 53, 69));
	    btnRifiuta.setForeground(Color.WHITE);
	    btnRifiuta.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnRifiuta.setMaximumSize(new Dimension(140, 40));
	    btnRifiuta.setFocusPainted(false);
	    btnRifiuta.setBorderPainted(false);
	    btnRifiuta.addActionListener(e -> rifiutaOfferta(idOfferta));
	    
	    btnRifiuta.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btnRifiuta.setBackground(new Color(200, 35, 51));
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btnRifiuta.setBackground(new Color(220, 53, 69));
	        }
	    });
	    
	    rightPanel.add(btnAccetta);
	    rightPanel.add(Box.createVerticalStrut(10));
	    rightPanel.add(btnRifiuta);
	    
	    card.add(leftPanel, BorderLayout.CENTER);
	    card.add(rightPanel, BorderLayout.EAST);
	    
	    return card;
	}
    
    private JPanel creaInfoAcquirente(String matricolaAcquirente) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        
        try {
            String nominativo = controller.getNominativoUtente(matricolaAcquirente);
            
            JLabel lblAcquirente = new JLabel("<html><b>Acquirente:</b> " + 
                (nominativo != null ? nominativo : "N/D") + "</html>");
            lblAcquirente.setFont(new Font("Verdana", Font.PLAIN, 13));
            lblAcquirente.setForeground(new Color(60, 60, 60));
            panel.add(lblAcquirente);
            
        } catch (Exception e) {
            JLabel lblErrore = new JLabel("Acquirente: N/D");
            lblErrore.setFont(new Font("Verdana", Font.PLAIN, 12));
            panel.add(lblErrore);
        }
        
        return panel;
    }
    
    private void accettaOfferta(int idOfferta) {
        int conferma = JOptionPane.showConfirmDialog(this,
            "Sei sicuro di voler accettare questa offerta?\n" +
            "L'annuncio verrà chiuso e tutte le altre offerte verranno rifiutate.",
            "Conferma accettazione",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (conferma != JOptionPane.YES_OPTION) {
            return;
        }
        
        String risultato = controller.accettaOffertaRicevuta(idOfferta);
        
        if (risultato.startsWith("SUCCESS:")) {
            JOptionPane.showMessageDialog(this,
                "Offerta accettata con successo!\n" +
                "L'annuncio è stato chiuso automaticamente.",
                "Offerta accettata",
                JOptionPane.INFORMATION_MESSAGE);
            
            mostraAnnunci();
        } else {
            JOptionPane.showMessageDialog(this,
                risultato,
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void rifiutaOfferta(int idOfferta) {
        int conferma = JOptionPane.showConfirmDialog(this,
            "Sei sicuro di voler rifiutare questa offerta?",
            "Conferma rifiuto",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (conferma != JOptionPane.YES_OPTION) {
            return;
        }
        
        String risultato = controller.rifiutaOffertaRicevuta(idOfferta);
        
        if (risultato.startsWith("SUCCESS:")) {
            JOptionPane.showMessageDialog(this,
                "Offerta rifiutata con successo",
                "Offerta rifiutata",
                JOptionPane.INFORMATION_MESSAGE);
            
            caricaOfferte(annuncioCorrenteId);
            
            int numeroOfferteRimaste = controller.getNumeroOfferteRicevute(annuncioCorrenteId);
            if (numeroOfferteRimaste == 0) {
                JOptionPane.showMessageDialog(this,
                    "Non ci sono più offerte da visualizzare",
                    "Informazione",
                    JOptionPane.INFORMATION_MESSAGE);
                mostraAnnunci();
            }
            
        } else {
            JOptionPane.showMessageDialog(this,
                risultato,
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}