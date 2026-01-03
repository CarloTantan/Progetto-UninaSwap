package boundary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import mainController.MainController;
import java.util.ArrayList;

//BOUNDARY - Interfaccia per visualizzare la lista degli annunci
public class ListaAnnunci extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel panelCards;
    private JComboBox<String> comboBoxTipologia;
    private JComboBox<String> comboBoxCategoria;
    private JTextField txtRicerca;
    private MainController controller;
    private ArrayList<Integer> indiciAnnunciFiltrati;
    private String tipologiaCorrente;
    
    public ListaAnnunci(MainController controller) {
        this.controller = controller;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Lista Annunci");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 700));
        
        inizializzaUI();
        
        // Carica gli annunci di default
        applicaFiltri();
    }

    private void inizializzaUI() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        // Header
        contentPane.add(creaHeader(), BorderLayout.NORTH);
        
        // Pannello centrale con filtri e annunci
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(creaPannelloFiltri(), BorderLayout.NORTH);
        centerContainer.add(creaScrollPane(), BorderLayout.CENTER);
        contentPane.add(centerContainer, BorderLayout.CENTER);
    }

    private JPanel creaHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 134, 192));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.setPreferredSize(new Dimension(0, 80));

        JLabel lblTitolo = new JLabel("Lista Annunci", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitolo.setForeground(Color.WHITE);

        JButton btnUndo = new JButton(new ImageIcon(
                ListaAnnunci.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setBorderPainted(false);
        btnUndo.setFocusPainted(false);
        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.apriAreaUtente();
            }
        });
        
        btnUndo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnUndo.setBackground(new Color(66, 152, 211));
                btnUndo.setContentAreaFilled(true);
            }
            public void mouseExited(MouseEvent evt) {
                btnUndo.setBackground(new Color(45, 134, 192));
                btnUndo.setContentAreaFilled(false);
            }
        });

        header.add(btnUndo, BorderLayout.WEST);
        header.add(lblTitolo, BorderLayout.CENTER);
        
        return header;
    }
   // Crea il pannello con i filtri di ricerca (tipologia, categoria, testo)
    private JPanel creaPannelloFiltri() {
        JPanel filtri = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        filtri.setBackground(Color.WHITE);
        filtri.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblTipologia = new JLabel("Tipologia:");
        lblTipologia.setFont(new Font("Verdana", Font.BOLD, 14));

        String[] tipologie = {"Vendita", "Scambio", "Regalo"};
        comboBoxTipologia = new JComboBox<>(tipologie);
        comboBoxTipologia.setFont(new Font("Verdana", Font.PLAIN, 13));
        comboBoxTipologia.setPreferredSize(new Dimension(150, 35));
        comboBoxTipologia.setBackground(Color.WHITE);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Verdana", Font.BOLD, 14));

        String[] categorie = {"Tutte", "Libri", "Musica", "Sport",
                "Cancelleria", "Vestiti", "Elettronica", "Giochi", "Casa", "Altro"};
        comboBoxCategoria = new JComboBox<>(categorie);
        comboBoxCategoria.setFont(new Font("Verdana", Font.PLAIN, 13));
        comboBoxCategoria.setPreferredSize(new Dimension(150, 35));
        comboBoxCategoria.setBackground(Color.WHITE);

        JLabel lblRicerca = new JLabel("Cerca:");
        lblRicerca.setFont(new Font("Verdana", Font.BOLD, 14));
        
        txtRicerca = new JTextField(20);
        txtRicerca.setFont(new Font("Verdana", Font.PLAIN, 14));

        JButton btnVisualizza = new JButton("Visualizza");
        btnVisualizza.setFont(new Font("Verdana", Font.BOLD, 14));
        btnVisualizza.setBackground(new Color(0, 52, 101));
        btnVisualizza.setForeground(Color.WHITE);
        btnVisualizza.setPreferredSize(new Dimension(120, 35));
        btnVisualizza.setFocusPainted(false);
        btnVisualizza.setBorderPainted(false);
        btnVisualizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicaFiltri();
            }
        });
        
        btnVisualizza.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnVisualizza.setBackground(new Color(0, 70, 140));
            }
            public void mouseExited(MouseEvent evt) {
                btnVisualizza.setBackground(new Color(0, 52, 101));
            }
        });

        filtri.add(lblTipologia);
        filtri.add(comboBoxTipologia);
        filtri.add(Box.createHorizontalStrut(10));
        filtri.add(lblCategoria);
        filtri.add(comboBoxCategoria);
        filtri.add(Box.createHorizontalStrut(10));
        filtri.add(lblRicerca);
        filtri.add(txtRicerca);
        filtri.add(Box.createHorizontalStrut(10));
        filtri.add(btnVisualizza);

        return filtri;
    }

    private JScrollPane creaScrollPane() {
        panelCards = new JPanel(new GridLayout(0, 3, 15, 15));
        panelCards.setBackground(Color.WHITE);
        panelCards.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(panelCards);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        
        return scrollPane;
    }

    // ==================== APPLICA FILTRI E CARICA ANNUNCI ====================
    
    /**
     * Raccoglie i filtri dall'UI e delega al controller il caricamento
     */
    private void applicaFiltri() {
        String tipologia = (String) comboBoxTipologia.getSelectedItem();
        String categoria = (String) comboBoxCategoria.getSelectedItem();
        String ricerca = txtRicerca.getText().trim();

        // Normalizza la categoria per il controller
        if (categoria.equals("Tutte")) {
            categoria = "Seleziona una categoria";
        }

        // Salva la tipologia corrente
        tipologiaCorrente = tipologia;

        // Delega al controller
        String risultato = controller.caricaAnnunci(tipologia, categoria, ricerca);
        
        // Gestisce il risultato
        if (risultato.startsWith("ERRORE:")) {
            JOptionPane.showMessageDialog(this,
                risultato.substring(7), 
                "Errore", 
                JOptionPane.ERROR_MESSAGE
            );
            mostraAnnunciVuoti();
        } else {
            // Recupera il numero di annunci caricati
            int numAnnunci = controller.getNumeroAnnunciCaricati(tipologiaCorrente);
            
            // Crea la lista degli indici
            indiciAnnunciFiltrati = new ArrayList<>();
            for (int i = 0; i < numAnnunci; i++) {
                indiciAnnunciFiltrati.add(i);
            }
            
            mostraAnnunci();
        }
    }

    /**
     * Mostra gli annunci caricati
     */
    private void mostraAnnunci() {
        panelCards.removeAll();
        
        if (indiciAnnunciFiltrati == null || indiciAnnunciFiltrati.isEmpty()) {
            mostraAnnunciVuoti();
        } else {
            for (int indice : indiciAnnunciFiltrati) {
                panelCards.add(creaCardAnnuncio(indice));
            }
        }
        
        panelCards.revalidate();
        panelCards.repaint();
    }

    /**
     * Mostra un messaggio quando non ci sono annunci
     */
    private void mostraAnnunciVuoti() {
        panelCards.removeAll();
        
        JLabel lblNoAnnunci = new JLabel("Nessun annuncio trovato");
        lblNoAnnunci.setFont(new Font("Verdana", Font.ITALIC, 16));
        lblNoAnnunci.setForeground(Color.GRAY);
        lblNoAnnunci.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.add(Box.createVerticalStrut(100));
        wrapperPanel.add(lblNoAnnunci);
        
        panelCards.setLayout(new BorderLayout());
        panelCards.add(wrapperPanel, BorderLayout.CENTER);
        
        panelCards.revalidate();
        panelCards.repaint();
        
        // Ripristina il layout
        panelCards.setLayout(new GridLayout(0, 3, 15, 15));
    }

    // ==================== CREA CARD ANNUNCIO ====================
    
    /**
     * Crea una card per un annuncio recuperando tutti i dati dal controller
     */
    private JPanel creaCardAnnuncio(int indice) {
        // Recupera tutti i dati tramite controller
        int idAnnuncio = controller.getIdAnnuncioByIndex(tipologiaCorrente, indice);
        String matricolaVenditore = controller.getMatricolaVenditoreAnnuncio(tipologiaCorrente, indice);
        String titolo = controller.getTitoloAnnuncio(tipologiaCorrente, indice);
        String descrizione = controller.getDescrizioneAnnuncio(tipologiaCorrente, indice);
        String categoria = controller.getCategoriaAnnuncio(tipologiaCorrente, indice);
        String stato = controller.getStatoAnnuncioString(tipologiaCorrente, indice);
        String extraInfo = controller.getInfoExtraAnnuncio(tipologiaCorrente, indice);
        
        // Crea la card
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Carosello foto
        card.add(creaCaroselloFoto(idAnnuncio), BorderLayout.NORTH);
        
        // Info annuncio
        card.add(creaInfoPanel(idAnnuncio, matricolaVenditore, titolo, 
                               descrizione, extraInfo, stato), BorderLayout.CENTER);
        
        return card;
    }

    /**
     * Crea il carosello di foto per un annuncio
     */
    private JPanel creaCaroselloFoto(int idAnnuncio) {
        JPanel panelFoto = new JPanel(new BorderLayout());
        panelFoto.setPreferredSize(new Dimension(250, 200));
        panelFoto.setBackground(Color.LIGHT_GRAY);
        
        // Recupera le foto dal controller
        ArrayList<String> foto = controller.getFotoAnnuncio(idAnnuncio);
        
        if (foto == null || foto.isEmpty()) {
            JLabel lblNoFoto = new JLabel("Nessuna foto disponibile", SwingConstants.CENTER);
            lblNoFoto.setFont(new Font("Verdana", Font.ITALIC, 12));
            lblNoFoto.setForeground(Color.GRAY);
            panelFoto.add(lblNoFoto, BorderLayout.CENTER);
            return panelFoto;
        }
        
        // Label per mostrare l'immagine corrente
        JLabel lblImmagine = new JLabel();
        lblImmagine.setHorizontalAlignment(SwingConstants.CENTER);
        lblImmagine.setVerticalAlignment(SwingConstants.CENTER);
        
        // Array per tenere traccia dell'indice corrente
        final int[] indiceCorrente = {0};
        
        // Carica la prima immagine tramite controller
        ImageIcon primaFoto = controller.caricaImmagine(foto.get(0), 250, 200);
        if (primaFoto != null) {
            lblImmagine.setIcon(primaFoto);
        }
        
        panelFoto.add(lblImmagine, BorderLayout.CENTER);
        
        // Se c'è più di una foto, aggiungi i bottoni di navigazione
        if (foto.size() > 1) {
            JPanel panelControlli = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            panelControlli.setOpaque(false);
            
            JLabel lblIndice = new JLabel((indiceCorrente[0] + 1) + "/" + foto.size());
            lblIndice.setFont(new Font("Verdana", Font.PLAIN, 11));
            lblIndice.setForeground(Color.WHITE);
            
            JButton btnPrev = new JButton("<");
            btnPrev.setFont(new Font("Verdana", Font.BOLD, 12));
            btnPrev.setPreferredSize(new Dimension(50, 25));
            btnPrev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    indiceCorrente[0] = (indiceCorrente[0] - 1 + foto.size()) % foto.size();
                    ImageIcon nuovaFoto = controller.caricaImmagine(foto.get(indiceCorrente[0]), 250, 200);
                    if (nuovaFoto != null) {
                        lblImmagine.setIcon(nuovaFoto);
                    }
                    lblIndice.setText((indiceCorrente[0] + 1) + "/" + foto.size());
                }
            });
            
            JButton btnNext = new JButton(">");
            btnNext.setFont(new Font("Verdana", Font.BOLD, 12));
            btnNext.setPreferredSize(new Dimension(50, 25));
            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    indiceCorrente[0] = (indiceCorrente[0] + 1) % foto.size();
                    ImageIcon nuovaFoto = controller.caricaImmagine(foto.get(indiceCorrente[0]), 250, 200);
                    if (nuovaFoto != null) {
                        lblImmagine.setIcon(nuovaFoto);
                    }
                    lblIndice.setText((indiceCorrente[0] + 1) + "/" + foto.size());
                }
            });
            
            panelControlli.add(btnPrev);
            panelControlli.add(lblIndice);
            panelControlli.add(btnNext);
            
            panelFoto.add(panelControlli, BorderLayout.SOUTH);
        }
        
        return panelFoto;
    }

    /**
     * Crea il pannello con le informazioni dell'annuncio
     */
    private JPanel creaInfoPanel(int idAnnuncio, String matricolaVenditore, 
                                 String titolo, String descrizione, 
                                 String extraInfo, String stato) {
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);

        // Titolo
        JLabel lblTitolo = new JLabel(titolo);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 15));
        lblTitolo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Descrizione
        JTextArea txtDescrizione = new JTextArea(descrizione);
        txtDescrizione.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtDescrizione.setOpaque(false);
        txtDescrizione.setLineWrap(true);
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setEditable(false);
        txtDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtDescrizione.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // Info extra (prezzo/oggetto/motivo)
        JLabel lblExtra = new JLabel(extraInfo);
        lblExtra.setFont(new Font("Verdana", Font.BOLD, 13));
        lblExtra.setForeground(new Color(0, 102, 204));
        lblExtra.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Info venditore
        JPanel venditorePanel = creaInfoVenditore(matricolaVenditore);
        venditorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Bottone offerta
        JButton btnOfferta = creaBottoneOfferta(idAnnuncio, matricolaVenditore, stato);

        // Assembla
        info.add(lblTitolo);
        info.add(Box.createVerticalStrut(8));
        info.add(txtDescrizione);
        info.add(Box.createVerticalStrut(10));
        info.add(lblExtra);
        info.add(Box.createVerticalStrut(10));
        info.add(venditorePanel);
        info.add(Box.createVerticalStrut(15));
        info.add(btnOfferta);

        return info;
    }

    /**
     * Crea il pannello con le informazioni del venditore
     */
    private JPanel creaInfoVenditore(String matricolaVenditore) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        
        if (matricolaVenditore == null || matricolaVenditore.trim().isEmpty()) {
            JLabel lblErrore = new JLabel("Venditore: N/D");
            lblErrore.setFont(new Font("Verdana", Font.PLAIN, 12));
            panel.add(lblErrore);
            return panel;
        }
        
        // Recupera i dati del venditore dal controller
        String nominativo = controller.getNominativoVenditore(matricolaVenditore);
        double media = controller.getValutazioneMediaVenditore(matricolaVenditore);
        int numRecensioni = controller.getNumeroRecensioniVenditore(matricolaVenditore);
        
        // Label venditore
        JLabel lblVenditore = new JLabel("<html><b>Venditore:</b> " + 
            (nominativo != null ? nominativo : "N/D") + "</html>");
        lblVenditore.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblVenditore.setForeground(new Color(80, 80, 80));
        panel.add(lblVenditore);
        
        // Rating (se disponibile)
        if (numRecensioni > 0) {
            try {
                ImageIcon iconaStella = new ImageIcon(
                    getClass().getResource("/icons/icons8-stella-32.png"));
                Image imgScalata = iconaStella.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                JLabel lblStella = new JLabel(new ImageIcon(imgScalata));
                panel.add(lblStella);
                
                JLabel lblRating = new JLabel(String.format("%.1f", media));
                lblRating.setFont(new Font("Verdana", Font.PLAIN, 11));
                lblRating.setForeground(new Color(100, 100, 100));
                panel.add(lblRating);
            } catch (Exception e) {
                System.err.println("Errore nel caricamento icona stella: " + e.getMessage());
            }
        }
        
        // Link per visualizzare le recensioni
        if (numRecensioni > 0) {
            JLabel lblLink = new JLabel("(" + numRecensioni + " recensioni)");
            lblLink.setFont(new Font("Verdana", Font.PLAIN, 11));
            lblLink.setForeground(new Color(0, 102, 204));
            lblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblLink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controller.apriVisualizzaRecensioniVenditore(matricolaVenditore);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblLink.setText("<html><u>(" + numRecensioni + " recensioni)</u></html>");
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    lblLink.setText("(" + numRecensioni + " recensioni)");
                }
            });
            panel.add(lblLink);
        }
        
        return panel;
    }

    /**
     * Crea il bottone per inviare l'offerta
     */
    private JButton creaBottoneOfferta(int idAnnuncio, String matricolaVenditore, String stato) {
        boolean chiuso = stato.equalsIgnoreCase("Chiuso");
        
        JButton btn = new JButton(chiuso ? "Chiuso" : "Invia Offerta");
        btn.setFont(new Font("Verdana", Font.BOLD, 14));
        btn.setBackground(chiuso ? Color.GRAY : new Color(0, 52, 101));
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setEnabled(!chiuso);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 35));
        
        if (!chiuso) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestisciClickOfferta(idAnnuncio, matricolaVenditore, stato);
                }
            });
            
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    btn.setBackground(new Color(0, 70, 140));
                }
                public void mouseExited(MouseEvent evt) {
                    btn.setBackground(new Color(0, 52, 101));
                }
            });
        }

        return btn;
    }

    /**
     * Gestisce il click sul bottone offerta
     */
    private void gestisciClickOfferta(int idAnnuncio, String matricolaVenditore, String stato) {
        // Verifica tramite controller se l'offerta può essere fatta
        String errore = controller.verificaOffertaPossibile(idAnnuncio, matricolaVenditore, stato);
        
        if (errore != null) {
            JOptionPane.showMessageDialog(this,
                errore,
                "Impossibile inviare offerta",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Apri la schermata di offerta appropriata
        switch (tipologiaCorrente) {
            case "Vendita":
                controller.apriOffertaVendita(idAnnuncio);
                break;
            case "Scambio":
                controller.apriOffertaScambio(idAnnuncio);
                break;
            case "Regalo":
                controller.apriOffertaRegalo(idAnnuncio);
                break;
            default:
                JOptionPane.showMessageDialog(this,
                    "Tipologia annuncio non riconosciuta",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE
                );
        }
    }
}