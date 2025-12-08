package boundary;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.RecensioneVenditoreDAO;
import dao.TransazioniDAO;
import entity.Transazione_entity;
import mainController.MainController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ListaTransazioni extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelTransazioni;
    private ArrayList<Transazione_entity> ListaTransazioni;
    private MainController controller;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ListaTransazioni frame = new ListaTransazioni();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public ListaTransazioni(MainController controller) {
        this.controller = controller;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            ListaTransazioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Lista Transazioni");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1100, 600));
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // ============ HEADER ============
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 132, 188));
        headerPanel.setPreferredSize(new Dimension(0, 100));
        headerPanel.setLayout(new BorderLayout(10, 0));
        contentPane.add(headerPanel, BorderLayout.NORTH);

        // Pannello sinistro con pulsante back
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(50, 132, 188));
        leftPanel.setPreferredSize(new Dimension(100, 100));
        leftPanel.setBorder(new EmptyBorder(25, 15, 0, 0));
        
        JButton btnUndo = new JButton("");
        btnUndo.setIcon(new ImageIcon(
            ListaTransazioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(50, 132, 188));
        btnUndo.setPreferredSize(new Dimension(50, 50));
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        btnUndo.setContentAreaFilled(false);
        
        btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUndo.setBackground(new Color(70, 152, 208));
                btnUndo.setContentAreaFilled(true);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUndo.setBackground(new Color(50, 132, 188));
                btnUndo.setContentAreaFilled(false);
            }
        });
        
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tornaAreaUtente();
            }
        });
        
        leftPanel.add(btnUndo);
        headerPanel.add(leftPanel, BorderLayout.WEST);

        // Pannello centrale con titolo
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(50, 132, 188));
        centerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        JLabel lblTitolo = new JLabel("Transazioni Completate");
        lblTitolo.setForeground(Color.WHITE);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblTitolo);
        headerPanel.add(centerPanel, BorderLayout.CENTER);

        // ============ CONTAINER PRINCIPALE ============
        JPanel mainContainer = new JPanel();
        mainContainer.setBackground(new Color(245, 247, 250));
        mainContainer.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainContainer.setLayout(new BorderLayout());
        contentPane.add(mainContainer, BorderLayout.CENTER);

        // ============ PANNELLO TRANSAZIONI (CARDS) ============
        panelTransazioni = new JPanel();
        panelTransazioni.setLayout(new BoxLayout(panelTransazioni, BoxLayout.Y_AXIS));
        panelTransazioni.setBackground(Color.WHITE);
        panelTransazioni.setBorder(new EmptyBorder(20, 30, 20, 30));

        JScrollPane scrollPane = new JScrollPane(panelTransazioni);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        mainContainer.add(scrollPane, BorderLayout.CENTER);
        
        // Carica le transazioni
        caricaTransazioni();
    }
    
    private void caricaTransazioni() {
        try {
            TransazioniDAO TransazioniDao = new TransazioniDAO();
            ListaTransazioni = TransazioniDao.getTransazioni(controller.getMatricolaUtenteLoggato());

            if (ListaTransazioni.isEmpty()) {
                JLabel lblNoTransazioni = new JLabel("Non hai ancora completato transazioni");
                lblNoTransazioni.setFont(new Font("Verdana", Font.ITALIC, 16));
                lblNoTransazioni.setForeground(Color.GRAY);
                lblNoTransazioni.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelTransazioni.add(Box.createVerticalStrut(100));
                panelTransazioni.add(lblNoTransazioni);
            } else {
                for (Transazione_entity t : ListaTransazioni) {
                    panelTransazioni.add(creaCardTransazione(t));
                    panelTransazioni.add(Box.createVerticalStrut(15));
                }
            }

            panelTransazioni.revalidate();
            panelTransazioni.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento delle transazioni",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JPanel creaCardTransazione(Transazione_entity transazione) {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        // ============ PANNELLO SINISTRO (Info Transazione) ============
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        // Titolo annuncio
        JLabel lblTitolo = new JLabel(transazione.getTitoloAnnuncio());
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 16));
        lblTitolo.setForeground(new Color(0, 52, 104));
        lblTitolo.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(lblTitolo);
        leftPanel.add(Box.createVerticalStrut(10));

        // Info venditore con rating
        JPanel venditorePanel = creaInfoVenditore(transazione.getMatricolaVenditore());
        venditorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(venditorePanel);
        leftPanel.add(Box.createVerticalStrut(8));

        // Solo matricola venditore
        JLabel lblMatricola = new JLabel(
            "<html><b>Matricola Venditore:</b> " + transazione.getMatricolaVenditore() + "</html>");
        lblMatricola.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblMatricola.setForeground(new Color(100, 100, 100));
        lblMatricola.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(lblMatricola);

        // ============ PANNELLO DESTRO (Pulsante Recensione) ============
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        rightPanel.add(Box.createVerticalGlue());

        // Pulsante recensione
        JButton btnRecensione = new JButton(
            transazione.hasRecensione() ? "Recensione inserita" : "Inserisci Recensione");
        btnRecensione.setFont(new Font("Verdana", Font.BOLD, 13));
        btnRecensione.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRecensione.setMaximumSize(new Dimension(200, 45));
        btnRecensione.setFocusPainted(false);
        btnRecensione.setBorderPainted(false);
        
        if (transazione.hasRecensione()) {
            // Recensione già inserita - bottone verde non cliccabile con testo bianco
            btnRecensione.setBackground(new Color(32, 105, 61));
            btnRecensione.setForeground(Color.WHITE);
            btnRecensione.setEnabled(false);
            
        } else {
            // Recensione da inserire - bottone blu cliccabile con testo bianco
            btnRecensione.setBackground(new Color(0, 52, 104));
            btnRecensione.setForeground(Color.WHITE);
            btnRecensione.setEnabled(true);
            
            btnRecensione.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnRecensione.setBackground(new Color(0, 70, 140));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnRecensione.setBackground(new Color(0, 52, 104));
                }
            });
            
            btnRecensione.addActionListener(e -> apriInserimentoRecensione(transazione));
        }
        
        rightPanel.add(btnRecensione);
        rightPanel.add(Box.createVerticalGlue());

        card.add(leftPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }

    private JPanel creaInfoVenditore(String matricolaVenditore) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        
        try {
            RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
            String nominativo = dao.getNominativoUtente(matricolaVenditore);
            double media = dao.getValutazioneMedia(matricolaVenditore);
            int numRecensioni = dao.getNumeroRecensioni(matricolaVenditore);
            
            JLabel lblVenditore = new JLabel("<html><b>Venditore:</b> " + 
                (nominativo != null ? nominativo : "N/D") + "</html>");
            lblVenditore.setFont(new Font("Verdana", Font.PLAIN, 13));
            lblVenditore.setForeground(new Color(60, 60, 60));
            panel.add(lblVenditore);
            
            if (numRecensioni > 0) {
                ImageIcon iconaStella = new ImageIcon(
                    ListaTransazioni.class.getResource("/icons/icons8-stella-32.png"));
                Image imgScalata = iconaStella.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                JLabel lblStella = new JLabel(new ImageIcon(imgScalata));
                panel.add(lblStella);
                
                JLabel lblRating = new JLabel(String.format("%.1f", media));
                lblRating.setFont(new Font("Verdana", Font.BOLD, 12));
                lblRating.setForeground(new Color(255, 165, 0));
                panel.add(lblRating);
                
                JLabel lblNumRecensioni = new JLabel("(" + numRecensioni + ")");
                lblNumRecensioni.setFont(new Font("Verdana", Font.PLAIN, 11));
                lblNumRecensioni.setForeground(new Color(120, 120, 120));
                panel.add(lblNumRecensioni);
            }
            
        } catch (Exception e) {
            JLabel lblErrore = new JLabel("Venditore: N/D");
            lblErrore.setFont(new Font("Verdana", Font.PLAIN, 13));
            panel.add(lblErrore);
        }
        
        return panel;
    }
    
    private void apriInserimentoRecensione(Transazione_entity transazione) {
        this.dispose();
        InserimentoRecensione recensioneFrame = new InserimentoRecensione(
            transazione.getMatricolaAcquirente(),
            transazione.getMatricolaVenditore(),
            transazione.getIdOfferta(),
            controller
        );
        recensioneFrame.setVisible(true);
    }
    
    private void tornaAreaUtente() {
        this.dispose();
        AreaUtente areaUtenteFrame = new AreaUtente(controller);
        areaUtenteFrame.setVisible(true);
    }
}