package boundary;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import dao.FotoAnnuncioDAO;
import dao.ListaAnnunciDAO;
import dao.RecensioneVenditoreDAO;
import entity.*;
import enumerations.StatoAnnuncio;
import mainController.MainController;

import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaAnnunci extends JFrame {

    private JPanel panelCards;
    private Utente_entity UtenteLoggato;
    private JComboBox<String> comboBoxTipologia;
    private JComboBox<String> comboBoxCategoria;
    private MainController controller;
    private JTextField txtRicerca;

    public ListaAnnunci(MainController controller) {

        this.controller = controller;

        setTitle("Lista Annunci");
        setIconImage(Toolkit.getDefaultToolkit().getImage(ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 700));

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // ---------------- HEADER -----------------
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 134, 192));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Lista Annunci", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton btnUndo = new JButton(new ImageIcon(
                ListaAnnunci.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setBorderPainted(false);
        btnUndo.setFocusPainted(false);
        btnUndo.addActionListener(e -> {
            setVisible(false);
            AreaUtente areaUtenteFrame = new AreaUtente(controller);
            areaUtenteFrame.setVisible(true);
        });

        header.add(btnUndo, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);

        // ---------------- FILTRI -----------------
        JPanel filtri = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        filtri.setBackground(Color.WHITE);

        JLabel lblTipologia = new JLabel("Tipologia:");
        lblTipologia.setFont(new Font("Verdana", Font.BOLD, 14));

        String[] tipologie = {"Seleziona una tipologia", "Vendita", "Scambio", "Regalo"};
        comboBoxTipologia = new JComboBox<>(tipologie);
        comboBoxTipologia.setFont(new Font("Verdana", Font.BOLD, 14));
        comboBoxTipologia.setBackground(new Color(45, 134, 192));
        comboBoxTipologia.setForeground(Color.WHITE);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Verdana", Font.BOLD, 14));

        String[] categorie = {"Seleziona una categoria", "Libri", "Musica", "Sport",
                "Cancelleria", "Vestiti", "Elettronica", "Giochi", "Casa", "Altro"};

        comboBoxCategoria = new JComboBox<>(categorie);
        comboBoxCategoria.setFont(new Font("Verdana", Font.BOLD, 14));
        comboBoxCategoria.setBackground(new Color(45, 134, 192));
        comboBoxCategoria.setForeground(Color.WHITE);

        JLabel lblRicerca = new JLabel("Cerca:");
        lblRicerca.setFont(new Font("Verdana", Font.BOLD, 14));
        
        txtRicerca = new JTextField(20);
        txtRicerca.setFont(new Font("Verdana", Font.PLAIN, 14));

        JButton btnVisualizza = new JButton("Visualizza");
        btnVisualizza.setFont(new Font("Verdana", Font.BOLD, 16));
        btnVisualizza.setBackground(new Color(0, 52, 101));
        btnVisualizza.setForeground(Color.WHITE);
        btnVisualizza.setBorderPainted(false);
        btnVisualizza.setFocusPainted(false);
        btnVisualizza.addActionListener(e -> gestisciVisualizza());

        filtri.add(lblTipologia);
        filtri.add(comboBoxTipologia);
        filtri.add(lblCategoria);
        filtri.add(comboBoxCategoria);
        filtri.add(lblRicerca);
        filtri.add(txtRicerca);
        filtri.add(btnVisualizza);

        // ---------------- PANEL TOP (Header + Filtri) -----------------
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(header, BorderLayout.NORTH);
        topPanel.add(filtri, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // ---------------- CARDS (al centro) -----------------
        panelCards = new JPanel(new GridLayout(0, 3, 15, 15));
        panelCards.setBackground(Color.WHITE);
        panelCards.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(panelCards);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

        // -------------------- LOGICA -------------------

    private void gestisciVisualizza() {
        String t = (String) comboBoxTipologia.getSelectedItem();
        String c = (String) comboBoxCategoria.getSelectedItem();
        String ricerca = txtRicerca.getText().trim();

        if (t.equals("Seleziona una tipologia")) {
            JOptionPane.showMessageDialog(this, "Seleziona una tipologia!", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ListaAnnunciDAO dao = new ListaAnnunciDAO();
            int count = 0;

            // Se c'è testo nella ricerca, usa i metodi di ricerca
            if (!ricerca.isEmpty()) {
                if (t.equals("Vendita")) {
                    ArrayList<AnnuncioVendita_entity> annunci = dao.cercaAnnunciVendita(ricerca, c);
                    count = annunci.size();
                    mostraAnnunciVendita(annunci);
                } else if (t.equals("Scambio")) {
                    ArrayList<AnnuncioScambio_entity> annunci = dao.cercaAnnunciScambio(ricerca, c);
                    count = annunci.size();
                    mostraAnnunciScambio(annunci);
                } else if (t.equals("Regalo")) {
                    ArrayList<AnnuncioRegalo_entity> annunci = dao.cercaAnnunciRegalo(ricerca, c);
                    count = annunci.size();
                    mostraAnnunciRegalo(annunci);
                }
            } else {
                // Altrimenti usa i metodi normali (come prima)
                if (t.equals("Vendita")) {
                    ArrayList<AnnuncioVendita_entity> annunci = c.equals("Seleziona una categoria") 
                        ? dao.getAnnunciVendita() 
                        : dao.getAnnunciVenditaCategoria(c);
                    count = annunci.size();
                    mostraAnnunciVendita(annunci);
                } else if (t.equals("Scambio")) {
                    ArrayList<AnnuncioScambio_entity> annunci = c.equals("Seleziona una categoria") 
                        ? dao.getAnnunciScambio() 
                        : dao.getAnnunciScambioCategoria(c);
                    count = annunci.size();
                    mostraAnnunciScambio(annunci);
                } else if (t.equals("Regalo")) {
                    ArrayList<AnnuncioRegalo_entity> annunci = c.equals("Seleziona una categoria") 
                        ? dao.getAnnunciRegalo() 
                        : dao.getAnnunciRegaloCategoria(c);
                    count = annunci.size();
                    mostraAnnunciRegalo(annunci);
                }
            }

            String messaggioRisultati = ricerca.isEmpty() 
                ? "Caricati " + count + " Annunci"
                : "Trovati " + count + " Annunci per \"" + ricerca + "\"";
                
            JOptionPane.showMessageDialog(this,
                messaggioRisultati,
                "Successo",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento degli Annunci: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento dei dati: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }

        // -------------------- VENDITA --------------------

        private void mostraAnnunciVendita(ArrayList<AnnuncioVendita_entity> annunci) {
            panelCards.removeAll();
            for (AnnuncioVendita_entity a : annunci) {
                panelCards.add(new AnnuncioCard(
                        a.getIdAnnuncio(),
                        a.getMatricolaVenditore(),
                        a.getTitolo(),
                        a.getDescrizione(),
                        "€ " + String.format("%.2f", a.getPrezzoVendita()),
                        a.getStatoAnnuncio(),
                        a.getTipologiaCategoria().toString(),
                        "Vendita"
                ));
            }
            panelCards.revalidate();
            panelCards.repaint();
        }

        // -------------------- REGALO --------------------

        private void mostraAnnunciRegalo(ArrayList<AnnuncioRegalo_entity> annunci) {
            panelCards.removeAll();
            for (AnnuncioRegalo_entity a : annunci) {
                panelCards.add(new AnnuncioCard(
                        a.getIdAnnuncio(),
                        a.getMatricolaVenditore(),
                        a.getTitolo(),
                        a.getDescrizione(),
                        a.getMotivoCessione(),
                        a.getStatoAnnuncio(),
                        a.getTipologiaCategoria().toString(),
                        "Regalo"
                ));
            }
            panelCards.revalidate();
            panelCards.repaint();
        }

        // -------------------- SCAMBIO --------------------

        private void mostraAnnunciScambio(ArrayList<AnnuncioScambio_entity> annunci) {
            panelCards.removeAll();
            for (AnnuncioScambio_entity a : annunci) {
                panelCards.add(new AnnuncioCard(
                        a.getIdAnnuncio(),
                        a.getMatricolaVenditore(),
                        a.getTitolo(),
                        a.getDescrizione(),
                        a.getOggettoRichiesto(),
                        a.getStatoAnnuncio(),
                        a.getTipologiaCategoria().toString(),
                        "Scambio"
                ));
            }
            panelCards.revalidate();
            panelCards.repaint();
        }

        // -------------------- CARD --------------------

        class AnnuncioCard extends JPanel {

            public AnnuncioCard(int id, String venditore, String titolo,
                                String descrizione, String extra, StatoAnnuncio stato,
                                String categoria, String tipologia) {

                setBorder(new CompoundBorder(
                        new LineBorder(Color.LIGHT_GRAY, 1, true),
                        new EmptyBorder(10, 10, 10, 10)
                ));
                setLayout(new BorderLayout(10, 10));
                setBackground(Color.WHITE);

                // Carosello foto
                JPanel caroselloPanel = creaCaroselloFoto(id);
                add(caroselloPanel, BorderLayout.NORTH);

                // Info panel
                JPanel info = new JPanel();
                info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
                info.setBackground(Color.WHITE);

                JLabel lblTitolo = new JLabel(titolo);
                lblTitolo.setFont(new Font("Verdana", Font.BOLD, 15));
                lblTitolo.setAlignmentX(Component.LEFT_ALIGNMENT);

                JTextArea txtDescrizione = new JTextArea(descrizione);
                txtDescrizione.setFont(new Font("Verdana", Font.PLAIN, 12));
                txtDescrizione.setOpaque(false);
                txtDescrizione.setLineWrap(true);
                txtDescrizione.setWrapStyleWord(true);
                txtDescrizione.setEditable(false);
                txtDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel lblExtra = new JLabel(extra);
                lblExtra.setFont(new Font("Verdana", Font.BOLD, 13));
                lblExtra.setForeground(new Color(0, 102, 204));
                lblExtra.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Panel per info venditore
                JPanel venditorePanel = creaInfoVenditore(venditore);
                venditorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                JButton btn = new JButton(stato == StatoAnnuncio.Chiuso ? "Chiuso" : "Invia Offerta");
                btn.setFont(new Font("Verdana", Font.BOLD, 14));
                btn.setBackground(new Color(0, 52, 101));
                btn.setForeground(Color.WHITE);
                btn.setBorderPainted(false);
                btn.setFocusPainted(false);
                btn.setEnabled(stato != StatoAnnuncio.Chiuso);
                btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                btn.setMaximumSize(new Dimension(200, 35));
                
                if (stato == StatoAnnuncio.Chiuso) {
                    btn.setBackground(Color.GRAY);
                }

                btn.addActionListener(e -> apriOfferta(id, venditore, stato, tipologia));

                info.add(lblTitolo);
                info.add(Box.createVerticalStrut(8));
                info.add(txtDescrizione);
                info.add(Box.createVerticalStrut(10));
                info.add(lblExtra);
                info.add(Box.createVerticalStrut(10));
                info.add(venditorePanel);
                info.add(Box.createVerticalStrut(15));
                info.add(btn);

                add(info, BorderLayout.CENTER);
            }
        }
        
        // -------------------- INFO VENDITORE --------------------
        
        private JPanel creaInfoVenditore(String matricolaVenditore) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            panel.setBackground(Color.WHITE);
            
            try {
                RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
                String nominativo = dao.getNominativoUtente(matricolaVenditore);
                double media = dao.getValutazioneMedia(matricolaVenditore);
                int numRecensioni = dao.getNumeroRecensioni(matricolaVenditore);
                
                // Label venditore (cliccabile)
                JLabel lblVenditore = new JLabel(nominativo != null ? nominativo : "Venditore");
                lblVenditore.setFont(new Font("Verdana", Font.BOLD, 12));
                lblVenditore.setForeground(new Color(0, 102, 204));
                lblVenditore.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // Aggiungi sottolineatura al passaggio del mouse
                lblVenditore.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        lblVenditore.setText("<html><u>" + (nominativo != null ? nominativo : "Venditore") + "</u></html>");
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        lblVenditore.setText(nominativo != null ? nominativo : "Venditore");
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Apri finestra recensioni
                        VisualizzaRecensioniVenditore frameRecensioni = 
                            new VisualizzaRecensioniVenditore(UtenteLoggato, matricolaVenditore);
                        frameRecensioni.setVisible(true);
                    }
                });
                
                panel.add(lblVenditore);
                
                // Rating con stellina
                if (numRecensioni > 0) {
                    ImageIcon iconaStella = new ImageIcon(
                        ListaAnnunci.class.getResource("/icons/icons8-stella-32.png"));
                    Image imgScalata = iconaStella.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    JLabel lblStella = new JLabel(new ImageIcon(imgScalata));
                    panel.add(lblStella);
                    
                    JLabel lblRating = new JLabel(String.format("%.1f (%d)", media, numRecensioni));
                    lblRating.setFont(new Font("Verdana", Font.PLAIN, 11));
                    lblRating.setForeground(new Color(100, 100, 100));
                    panel.add(lblRating);
                } else {
                    JLabel lblNoRating = new JLabel("(Nessuna recensione)");
                    lblNoRating.setFont(new Font("Verdana", Font.ITALIC, 10));
                    lblNoRating.setForeground(Color.GRAY);
                    panel.add(lblNoRating);
                }
                
            } catch (SQLException e) {
                System.err.println("Errore nel caricamento info venditore: " + e.getMessage());
                JLabel lblErrore = new JLabel("Venditore");
                lblErrore.setFont(new Font("Verdana", Font.PLAIN, 12));
                panel.add(lblErrore);
            }
            
            return panel;
        }
        
        // -------------------- CAROSELLO FOTO --------------------
        
        private JPanel creaCaroselloFoto(int idAnnuncio) {
            JPanel caroselloPanel = new JPanel(new BorderLayout());
            caroselloPanel.setPreferredSize(new Dimension(200, 200));
            caroselloPanel.setBackground(new Color(240, 240, 240));
            
            // Label per l'immagine
            JLabel lblImmagine = new JLabel();
            lblImmagine.setPreferredSize(new Dimension(200, 170));
            lblImmagine.setHorizontalAlignment(SwingConstants.CENTER);
            lblImmagine.setVerticalAlignment(SwingConstants.CENTER);
            lblImmagine.setOpaque(true);
            lblImmagine.setBackground(new Color(240, 240, 240));
            
            // Panel per i controlli (frecce + contatore)
            JPanel controlliPanel = new JPanel(new BorderLayout());
            controlliPanel.setBackground(Color.WHITE);
            controlliPanel.setPreferredSize(new Dimension(200, 30));
            
            // Label contatore (es: "1/5")
            JLabel lblContatore = new JLabel("", SwingConstants.CENTER);
            lblContatore.setFont(new Font("Verdana", Font.BOLD, 12));
            lblContatore.setForeground(new Color(0, 52, 101));
            
            // Bottone freccia sinistra
            JButton btnPrev = new JButton(new ImageIcon(ListaAnnunci.class.getResource("/icons/icons8-arrow-pointing-left-24.png")));
            btnPrev.setBackground(new Color(45, 134, 192));
            btnPrev.setBorderPainted(false);
            btnPrev.setFocusPainted(false);
            btnPrev.setPreferredSize(new Dimension(50, 30));
            
            // Bottone freccia destra
            JButton btnNext = new JButton(new ImageIcon(ListaAnnunci.class.getResource("/icons/icons8-arrow-24.png")));
            btnNext.setBackground(new Color(45, 134, 192));
            btnNext.setBorderPainted(false);
            btnNext.setFocusPainted(false);
            btnNext.setPreferredSize(new Dimension(50, 30));
            
            controlliPanel.add(btnPrev, BorderLayout.WEST);
            controlliPanel.add(lblContatore, BorderLayout.CENTER);
            controlliPanel.add(btnNext, BorderLayout.EAST);
            
            caroselloPanel.add(lblImmagine, BorderLayout.CENTER);
            caroselloPanel.add(controlliPanel, BorderLayout.SOUTH);
            
            // Carica le foto dal database
            try {
                FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
                ArrayList<String> percorsi = fotoDAO.getFotoByAnnuncio(idAnnuncio);
                
                if (percorsi != null && !percorsi.isEmpty()) {
                    // Array per tenere traccia dell'indice corrente
                    final int[] indiceCorrente = {0};
                    
                    // Metodo per aggiornare l'immagine visualizzata
                    Runnable aggiornaImmagine = () -> {
                        String percorsoFoto = percorsi.get(indiceCorrente[0]);
                        File file = new File(percorsoFoto);
                        
                        if (file.exists()) {
                            ImageIcon originalIcon = new ImageIcon(percorsoFoto);
                            Image scaledImage = originalIcon.getImage().getScaledInstance(
                                200, 170, Image.SCALE_SMOOTH
                            );
                            lblImmagine.setIcon(new ImageIcon(scaledImage));
                            lblImmagine.setText("");
                        } else {
                            lblImmagine.setIcon(null);
                            lblImmagine.setText("Immagine non trovata");
                            lblImmagine.setFont(new Font("Verdana", Font.PLAIN, 12));
                        }
                        
                        // Aggiorna contatore
                        lblContatore.setText((indiceCorrente[0] + 1) + "/" + percorsi.size());
                        
                        // Abilita/disabilita frecce
                        btnPrev.setEnabled(indiceCorrente[0] > 0);
                        btnNext.setEnabled(indiceCorrente[0] < percorsi.size() - 1);
                    };
                    
                    // Mostra la prima immagine
                    aggiornaImmagine.run();
                    
                    // Azione freccia sinistra
                    btnPrev.addActionListener(e -> {
                        if (indiceCorrente[0] > 0) {
                            indiceCorrente[0]--;
                            aggiornaImmagine.run();
                        }
                    });
                    
                    // Azione freccia destra
                    btnNext.addActionListener(e -> {
                        if (indiceCorrente[0] < percorsi.size() - 1) {
                            indiceCorrente[0]++;
                            aggiornaImmagine.run();
                        }
                    });
                    
                    // Se c'è solo una foto, nascondi i controlli
                    if (percorsi.size() <= 1) {
                        btnPrev.setVisible(false);
                        btnNext.setVisible(false);
                    }
                    
                } else {
                    // Nessuna foto disponibile
                    lblImmagine.setText("Nessuna foto");
                    lblImmagine.setFont(new Font("Verdana", Font.PLAIN, 14));
                    btnPrev.setVisible(false);
                    btnNext.setVisible(false);
                    lblContatore.setVisible(false);
                }
                
            } catch (SQLException e) {
                System.err.println("Errore nel caricamento delle foto: " + e.getMessage());
                lblImmagine.setText("Errore caricamento");
                lblImmagine.setFont(new Font("Verdana", Font.PLAIN, 12));
                btnPrev.setVisible(false);
                btnNext.setVisible(false);
                lblContatore.setVisible(false);
            }
            
            return caroselloPanel;
        }

        // -------------------- OFFERTA --------------------

        private void apriOfferta(int idAnnuncio, String matricolaVenditore, StatoAnnuncio stato, String tipologia) {
            
            if (stato == StatoAnnuncio.Chiuso) {
                JOptionPane.showMessageDialog(this,
                        "Annuncio chiuso, non è possibile fare un'offerta.",
                        "Errore",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (matricolaVenditore.equals(UtenteLoggato.getMatricola())) {
                JOptionPane.showMessageDialog(this,
                        "Non puoi fare un'offerta sul tuo annuncio.",
                        "Errore",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            setVisible(false);

            if (tipologia.equals("Scambio")) {
                OffertaScambio offertaScambioFrame = new OffertaScambio(controller);
                offertaScambioFrame.setVisible(true);
            } else if (tipologia.equals("Vendita")) {
                OffertaVendita offertaVenditaFrame = new OffertaVendita(controller);
                offertaVenditaFrame.setVisible(true);
            } else if (tipologia.equals("Regalo")) {
                OffertaRegalo offertaRegaloFrame = new OffertaRegalo(controller);
                offertaRegaloFrame.setVisible(true);
            }
        }
    }