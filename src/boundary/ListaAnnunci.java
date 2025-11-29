package boundary;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import dao.ListaAnnunciDAO;
import entity.*;
import enumerations.StatoAnnuncio;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaAnnunci extends JFrame {

    private JPanel panelCards;
    private Utente_entity UtenteLoggato;
    private JComboBox<String> comboBoxTipologia;
    private JComboBox<String> comboBoxCategoria;

    public ListaAnnunci(Utente_entity UtenteLoggato) {

        this.UtenteLoggato = UtenteLoggato;

        setTitle("Lista Annunci");
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")
        ));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            AreaUtente areaUtenteFrame = new AreaUtente(UtenteLoggato);
            areaUtenteFrame.setVisible(true);
            areaUtenteFrame.setLocationRelativeTo(null);
        });

        header.add(btnUndo, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);

        mainPanel.add(header, BorderLayout.NORTH);

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
        filtri.add(btnVisualizza);

        // ---------------- CARDS (al centro) -----------------
        panelCards = new JPanel(new GridLayout(0, 3, 15, 15));
        panelCards.setBackground(Color.WHITE);
        panelCards.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel contenitore che include cards E filtri
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(panelCards, BorderLayout.CENTER);
        centerPanel.add(filtri, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ---- FINALIZZAZIONE WINDOW ----
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    // -------------------- LOGICA -------------------

    private void gestisciVisualizza() {
        String t = (String) comboBoxTipologia.getSelectedItem();
        String c = (String) comboBoxCategoria.getSelectedItem();

        if (t.equals("Seleziona una tipologia")) {
            JOptionPane.showMessageDialog(this, "Seleziona una tipologia!", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ListaAnnunciDAO dao = new ListaAnnunciDAO();
            int count = 0;

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

            JOptionPane.showMessageDialog(this,
                "Caricati " + count + " Annunci",
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

            // Immagine
            JLabel img = new JLabel("📷 Foto", SwingConstants.CENTER);
            img.setPreferredSize(new Dimension(200, 150));
            img.setOpaque(true);
            img.setBackground(new Color(240, 240, 240));
            img.setFont(new Font("Verdana", Font.PLAIN, 14));

            add(img, BorderLayout.NORTH);

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
            info.add(Box.createVerticalStrut(15));
            info.add(btn);

            add(info, BorderLayout.CENTER);
        }
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
            OffertaScambio offertaScambioFrame = new OffertaScambio(UtenteLoggato, idAnnuncio);
            offertaScambioFrame.setVisible(true);
            offertaScambioFrame.setLocationRelativeTo(null);
        } else if (tipologia.equals("Vendita")) {
            OffertaVendita offertaVenditaFrame = new OffertaVendita(UtenteLoggato, idAnnuncio);
            offertaVenditaFrame.setVisible(true);
            offertaVenditaFrame.setLocationRelativeTo(null);
        } else if (tipologia.equals("Regalo")) {
            OffertaRegalo offertaRegaloFrame = new OffertaRegalo(UtenteLoggato, idAnnuncio);
            offertaRegaloFrame.setVisible(true);
            offertaRegaloFrame.setLocationRelativeTo(null);
        }
    }
}