package boundary;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mainController.MainController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Classe che rappresenta l'interfaccia per visualizzare la lista delle transazioni completate.
 * Permette di vedere tutte le transazioni dell'utente e di inserire recensioni per quelle
 * che non sono ancora state recensite.
 */
public class ListaTransazioni extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelTransazioni;
    private MainController controller;
    

     
    public ListaTransazioni(MainController controller) {
        this.controller = controller;
        
        inizializzaFrame();
        costruisciInterfaccia();
        caricaTransazioni();
    }
    // Inizializza le proprietà base della finestra (titolo, icona, dimensioni).
    
    private void inizializzaFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            ListaTransazioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Lista Transazioni");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1100, 600));
    }
    
    //Costruisce l'interfaccia grafica principale con header e container delle transazioni.
    
    private void costruisciInterfaccia() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        contentPane.add(creaHeader(), BorderLayout.NORTH);
        contentPane.add(creaMainContainer(), BorderLayout.CENTER);
    }
    
    //Crea il pannello header blu con titolo e pulsante indietro.
    private JPanel creaHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 132, 188));
        headerPanel.setPreferredSize(new Dimension(0, 100));
        headerPanel.setLayout(new BorderLayout(10, 0));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(50, 132, 188));
        leftPanel.setPreferredSize(new Dimension(100, 100));
        leftPanel.setBorder(new EmptyBorder(25, 15, 0, 0));
        
        JButton btnUndo = creaPulsanteBack();
        leftPanel.add(btnUndo);
        headerPanel.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(50, 132, 188));
        centerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        JLabel lblTitolo = new JLabel("Transazioni Completate");
        lblTitolo.setForeground(Color.WHITE);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblTitolo);
        headerPanel.add(centerPanel, BorderLayout.CENTER);

        return headerPanel;
    }
    //Crea il pulsante "Indietro" con icona e effetto hover.
    private JButton creaPulsanteBack() {
        JButton btnUndo = new JButton("");
        btnUndo.setIcon(new ImageIcon(
            ListaTransazioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(50, 132, 188));
        btnUndo.setPreferredSize(new Dimension(50, 50));
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        btnUndo.setContentAreaFilled(false);
        
        btnUndo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnUndo.setBackground(new Color(70, 152, 208));
                btnUndo.setContentAreaFilled(true);
            }
            public void mouseExited(MouseEvent evt) {
                btnUndo.setBackground(new Color(50, 132, 188));
                btnUndo.setContentAreaFilled(false);
            }
        });
        
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	controller.apriAreaUtente();
            }
        });
        
        return btnUndo;
    }
    //Crea il container principale che conterrà la lista scrollabile delle transazioni.
    private JPanel creaMainContainer() {
        JPanel mainContainer = new JPanel();
        mainContainer.setBackground(new Color(245, 247, 250));
        mainContainer.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainContainer.setLayout(new BorderLayout());

        panelTransazioni = new JPanel();
        panelTransazioni.setLayout(new BoxLayout(panelTransazioni, BoxLayout.Y_AXIS));
        panelTransazioni.setBackground(Color.WHITE);
        panelTransazioni.setBorder(new EmptyBorder(20, 30, 20, 30));

        JScrollPane scrollPane = new JScrollPane(panelTransazioni);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        mainContainer.add(scrollPane, BorderLayout.CENTER);
        
        return mainContainer;
    }
    
    // Carica le transazioni dal controller e le visualizza.
    private void caricaTransazioni() {
        try {
            // Chiedi al controller quante transazioni ci sono
            int numeroTransazioni = controller.getNumeroTransazioni();

            if (numeroTransazioni == 0) {
                mostraMessaggioNessunaTransazione();
            } else {
                mostraTransazioni(numeroTransazioni);
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
    
    //Mostra un messaggio quando non ci sono transazioni da visualizzare.
    private void mostraMessaggioNessunaTransazione() {
        JLabel lblNoTransazioni = new JLabel("Non hai ancora completato transazioni");
        lblNoTransazioni.setFont(new Font("Verdana", Font.ITALIC, 16));
        lblNoTransazioni.setForeground(Color.GRAY);
        lblNoTransazioni.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTransazioni.add(Box.createVerticalStrut(100));
        panelTransazioni.add(lblNoTransazioni);
    }
    //Crea e visualizza le card per tutte le transazioni.
    private void mostraTransazioni(int numeroTransazioni) {
        for (int i = 0; i < numeroTransazioni; i++) {
            panelTransazioni.add(creaCardTransazione(i));
            panelTransazioni.add(Box.createVerticalStrut(15));
        }
    }
    //Crea una singola card per una transazione.
    private JPanel creaCardTransazione(int index) {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        // Ottieni i dati tramite il controller usando l'indice
        String titoloAnnuncio = controller.getTitoloAnnuncioTransazione(index);
        String matricolaVenditore = controller.getMatricolaVenditoreTransazione(index);
        String matricolaAcquirente = controller.getMatricolaAcquirenteTransazione(index);
        int idOfferta = controller.getIdOffertaTransazione(index);
        boolean hasRecensione = controller.hasRecensioneTransazione(index);

        JPanel leftPanel = creaInfoTransazione(titoloAnnuncio, matricolaVenditore);
        JPanel rightPanel = creaPannelloRecensione(hasRecensione, matricolaAcquirente, 
                                                    matricolaVenditore, idOfferta);

        card.add(leftPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }
    //Crea il pannello con le informazioni della transazione.
    private JPanel creaInfoTransazione(String titoloAnnuncio, String matricolaVenditore) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        // Titolo annuncio
        JLabel lblTitolo = new JLabel(titoloAnnuncio);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 16));
        lblTitolo.setForeground(new Color(0, 52, 104));
        lblTitolo.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(lblTitolo);
        leftPanel.add(Box.createVerticalStrut(10));

        // Info venditore con rating
        JPanel venditorePanel = creaInfoVenditore(matricolaVenditore);
        venditorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(venditorePanel);
        leftPanel.add(Box.createVerticalStrut(8));

        // Matricola venditore
        JLabel lblMatricola = new JLabel(
            "<html><b>Matricola Venditore:</b> " + matricolaVenditore + "</html>");
        lblMatricola.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblMatricola.setForeground(new Color(100, 100, 100));
        lblMatricola.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(lblMatricola);
        
        return leftPanel;
    }
//Crea il pannello con le informazioni del venditore.
    private JPanel creaInfoVenditore(String matricolaVenditore) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        
        try {
            String nominativo = controller.getNominativoVenditore(matricolaVenditore);
            double media = controller.getValutazioneMediaVenditore(matricolaVenditore);
            int numRecensioni = controller.getNumeroRecensioniVenditore(matricolaVenditore);
            
            JLabel lblVenditore = new JLabel("<html><b>Venditore:</b> " + 
                (nominativo != null ? nominativo : "N/D") + "</html>");
            lblVenditore.setFont(new Font("Verdana", Font.PLAIN, 13));
            lblVenditore.setForeground(new Color(60, 60, 60));
            panel.add(lblVenditore);
            
            if (numRecensioni > 0) {
                aggiungiRatingVisuale(panel, media, numRecensioni);
            }
            
        } catch (Exception e) {
            JLabel lblErrore = new JLabel("Venditore: N/D");
            lblErrore.setFont(new Font("Verdana", Font.PLAIN, 13));
            panel.add(lblErrore);
        }
        
        return panel;
    }
    // Aggiunge la visualizzazione del rating con stellina, media e numero recensioni.
    private void aggiungiRatingVisuale(JPanel panel, double media, int numRecensioni) {
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
    //Crea il pannello destro della card con il pulsante per la recensione.
    private JPanel creaPannelloRecensione(boolean hasRecensione, String matricolaAcquirente,
                                          String matricolaVenditore, int idOfferta) {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        rightPanel.add(Box.createVerticalGlue());

        JButton btnRecensione = creaPulsanteRecensione(hasRecensione, matricolaAcquirente,
                                                       matricolaVenditore, idOfferta);
        rightPanel.add(btnRecensione);
        rightPanel.add(Box.createVerticalGlue());
        
        return rightPanel;
    }
    
    private JButton creaPulsanteRecensione(boolean hasRecensione, String matricolaAcquirente,
                                           String matricolaVenditore, int idOfferta) {
        JButton btnRecensione = new JButton(
            hasRecensione ? "Recensione inserita" : "Inserisci Recensione");
        btnRecensione.setFont(new Font("Verdana", Font.BOLD, 13));
        btnRecensione.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRecensione.setMaximumSize(new Dimension(200, 45));
        btnRecensione.setFocusPainted(false);
        btnRecensione.setBorderPainted(false);
        
        if (hasRecensione) {
            configuraPulsanteRecensioneInserita(btnRecensione);
        } else {
            configuraPulsanteInserisciRecensione(btnRecensione, matricolaAcquirente,
                                                matricolaVenditore, idOfferta);
        }
        
        return btnRecensione;
    }
    //Crea il pulsante per inserire o visualizzare lo stato della recensione
    private void configuraPulsanteRecensioneInserita(JButton btnRecensione) {
        btnRecensione.setBackground(new Color(32, 105, 61));
        btnRecensione.setForeground(Color.WHITE);
        btnRecensione.setEnabled(false);
    }
    //Configura il pulsante quando la recensione è già stata inserita.
    private void configuraPulsanteInserisciRecensione(JButton btnRecensione, String matricolaAcquirente,
                                                     String matricolaVenditore, int idOfferta) {
        btnRecensione.setBackground(new Color(0, 52, 104));
        btnRecensione.setForeground(Color.WHITE);
        btnRecensione.setEnabled(true);
        
        btnRecensione.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnRecensione.setBackground(new Color(0, 70, 140));
            }
            public void mouseExited(MouseEvent evt) {
                btnRecensione.setBackground(new Color(0, 52, 104));
            }
        });
        
        btnRecensione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	apriInserimentoRecensione(matricolaAcquirente,  matricolaVenditore, idOfferta);    
            }
        });
        		
        		
    }
    
    private void apriInserimentoRecensione(String matricolaAcquirente, String matricolaVenditore,
                                          int idOfferta) {
    	controller.apriInserimentoRecensione(matricolaAcquirente, matricolaVenditore, idOfferta);
    }
    
}