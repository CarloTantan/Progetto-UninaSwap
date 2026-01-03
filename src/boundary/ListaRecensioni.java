package boundary;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import entity.Recensione_entity;
import mainController.MainController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Classe che rappresenta l'interfaccia per visualizzare le recensioni.
 * Permette di vedere sia le recensioni inviate che quelle ricevute dall'utente,
 * con dettagli come punteggio, commento, data e informazioni sull'altra persona coinvolta.
 */
public class ListaRecensioni extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;              
    private JPanel panelRecensioni;          
    private JLabel lblContatore;             
    private MainController controller;        
    
    public ListaRecensioni(MainController controller) {
        this.controller = controller;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                ListaRecensioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Le tue recensioni");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        setMinimumSize(new Dimension(1250, 700)); 
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));  
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        //  HEADER :Pannello header blu con titolo e pulsante indietro
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
        ListaRecensioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(50, 132, 188));
        btnUndo.setPreferredSize(new Dimension(50, 50));
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        btnUndo.setContentAreaFilled(false);

        btnUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnUndo.setBackground(new Color(70, 152, 208));
                btnUndo.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnUndo.setBackground(new Color(50, 132, 188));
                btnUndo.setContentAreaFilled(false);
            }
        });

        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	controller.apriAreaUtente();
            }
        });

        leftPanel.add(btnUndo);
        headerPanel.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(50, 132, 188));
        centerPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel lblTitolo = new JLabel("Le Tue Recensioni");
        lblTitolo.setForeground(Color.WHITE);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblContatore = new JLabel("Seleziona una categoria");
        lblContatore.setForeground(Color.WHITE);
        lblContatore.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblContatore.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(lblTitolo);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(lblContatore);

        headerPanel.add(centerPanel, BorderLayout.CENTER);

        //  CONTAINER PRINCIPALE: Pannello che contiene i pulsanti e l'area recensioni
        JPanel mainContainer = new JPanel();
        mainContainer.setBackground(new Color(245, 247, 250));
        mainContainer.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainContainer.setLayout(new BorderLayout(0, 20));
        contentPane.add(mainContainer, BorderLayout.CENTER);

        // PANNELLO PULSANTI:  Pannello con i due pulsanti per selezionare il tipo di recensioni
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 247, 250));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        mainContainer.add(buttonPanel, BorderLayout.NORTH);

        JButton btnRecensioniInviate = new JButton("Recensioni Inviate");
        btnRecensioniInviate.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnRecensioniInviate.setBackground(new Color(0, 52, 104));
        btnRecensioniInviate.setForeground(Color.WHITE);
        btnRecensioniInviate.setPreferredSize(new Dimension(250, 50));
        btnRecensioniInviate.setFocusPainted(false);
        btnRecensioniInviate.setBorderPainted(false);

        
        btnRecensioniInviate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnRecensioniInviate.setBackground(new Color(0, 70, 140));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnRecensioniInviate.setBackground(new Color(0, 52, 104));
            }
        });

        btnRecensioniInviate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caricaRecensioniInviate();
            }
        });

        buttonPanel.add(btnRecensioniInviate);

        JButton btnRecensioniRicevute = new JButton("Recensioni Ricevute");
        btnRecensioniRicevute.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnRecensioniRicevute.setForeground(Color.WHITE);
        btnRecensioniRicevute.setBackground(new Color(0, 52, 104));
        btnRecensioniRicevute.setPreferredSize(new Dimension(250, 50));
        btnRecensioniRicevute.setFocusPainted(false);
        btnRecensioniRicevute.setBorderPainted(false);

        btnRecensioniRicevute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnRecensioniRicevute.setBackground(new Color(0, 70, 140));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnRecensioniRicevute.setBackground(new Color(0, 52, 104));
            }
        });

        btnRecensioniRicevute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caricaRecensioniRicevute();
            }
        });

        buttonPanel.add(btnRecensioniRicevute);

        //  PANNELLO RECENSIONI : Pannello che conterrà le card delle recensioni
        panelRecensioni = new JPanel();
        panelRecensioni.setLayout(new BoxLayout(panelRecensioni, BoxLayout.Y_AXIS));
        panelRecensioni.setBackground(Color.WHITE);
        panelRecensioni.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(panelRecensioni);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  // Velocità scroll
        scrollPane.setBorder(null);
        mainContainer.add(scrollPane, BorderLayout.CENTER);
    }

    
     // Carica e visualizza le recensioni inviate dall'utente corrente.
     private void caricaRecensioniInviate() {
        
    	 panelRecensioni.removeAll();
        
        ArrayList<Recensione_entity> recensioni = controller.caricaRecensioniInviate();
        
        lblContatore.setText("Recensioni inviate: " + recensioni.size());
        
        if (recensioni.isEmpty()) {
            
        	JLabel lblNoRecensioni = new JLabel("Non hai ancora inviato recensioni");
            lblNoRecensioni.setFont(new Font("Verdana", Font.ITALIC, 14));
            lblNoRecensioni.setForeground(Color.GRAY);
            lblNoRecensioni.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelRecensioni.add(Box.createVerticalStrut(50));
            panelRecensioni.add(lblNoRecensioni);
        } else {
        
        	for (Recensione_entity r : recensioni) {
                panelRecensioni.add(creaCardRecensione(r, true));  // true = inviata
                panelRecensioni.add(Box.createVerticalStrut(15));  // Spaziatura
            }
        }
        
        panelRecensioni.revalidate();
        panelRecensioni.repaint();
    }

    
     // Carica e visualizza le recensioni ricevute dall'utente corrente.
     private void caricaRecensioniRicevute() {
        panelRecensioni.removeAll();
        
        ArrayList<Recensione_entity> recensioni = controller.caricaRecensioniRicevute();
        
        String matricola = controller.getMatricolaUtenteLoggato();
        double valutazioneMedia = controller.getValutazioneMediaVenditore(matricola);
     
        lblContatore.setText("Recensioni ricevute: " + recensioni.size() + 
                             ", Valutazione media: " + String.format("%.2f", valutazioneMedia));
        
        if (recensioni.isEmpty()) {
        
        	JLabel lblNoRecensioni = new JLabel("Non hai ancora ricevuto recensioni");
            lblNoRecensioni.setFont(new Font("Verdana", Font.ITALIC, 14));
            lblNoRecensioni.setForeground(Color.GRAY);
            lblNoRecensioni.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelRecensioni.add(Box.createVerticalStrut(50));
            panelRecensioni.add(lblNoRecensioni);
        } else {
            for (Recensione_entity r : recensioni) {
                panelRecensioni.add(creaCardRecensione(r, false)); 
                panelRecensioni.add(Box.createVerticalStrut(15));  
                }
        }
        
        panelRecensioni.revalidate();
        panelRecensioni.repaint();
    }

    
    // Crea una card per visualizzare una singola recensione.
    private JPanel creaCardRecensione(Recensione_entity rec, boolean isInviata) {
    
    	JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(15, 15, 15, 15)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JPanel panelStelle = creaPannelloStelle(rec.getPunteggio());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JLabel lblData = new JLabel(sdf.format(rec.getData()));
        lblData.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblData.setForeground(Color.GRAY);

        headerPanel.add(panelStelle, BorderLayout.WEST);
        headerPanel.add(lblData, BorderLayout.EAST);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);

        String titoloAnnuncio = controller.getTitoloAnnuncioDaOfferta(rec.getIdOfferta());
        if (titoloAnnuncio != null) {
            JLabel lblTitoloAnnuncio = new JLabel("Annuncio: " + titoloAnnuncio);
            lblTitoloAnnuncio.setFont(new Font("Verdana", Font.BOLD, 13));
            lblTitoloAnnuncio.setForeground(new Color(0, 52, 104));
            lblTitoloAnnuncio.setBorder(new EmptyBorder(0, 0, 8, 0));
            centralPanel.add(lblTitoloAnnuncio);
        }

        JTextArea txtCommento = new JTextArea(rec.getCommento() != null ? rec.getCommento() : "Nessun commento");
        txtCommento.setFont(new Font("Verdana", Font.PLAIN, 13));
        txtCommento.setForeground(Color.DARK_GRAY);
        txtCommento.setLineWrap(true);
        txtCommento.setWrapStyleWord(true);
        txtCommento.setEditable(false);
        txtCommento.setOpaque(false);
        txtCommento.setBorder(new EmptyBorder(5, 0, 10, 0));
        centralPanel.add(txtCommento);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        footerPanel.setBackground(Color.WHITE);

        String matricolaAltraPersona = isInviata ? rec.getMatricolaVenditore() : rec.getMatricolaAcquirente();
        
        String nomeAltraPersona = controller.getNominativoUtente(matricolaAltraPersona);
        String label = isInviata ? "Recensione a: " : "Recensione da: ";
        
        JLabel lblUtente = new JLabel(label + (nomeAltraPersona != null ? nomeAltraPersona : "Utente"));
        lblUtente.setFont(new Font("Verdana", Font.ITALIC, 12));
        lblUtente.setForeground(new Color(100, 100, 100));
        footerPanel.add(lblUtente);

        JLabel lblMatricola = new JLabel("(" + matricolaAltraPersona + ")");
        lblMatricola.setFont(new Font("Verdana", Font.PLAIN, 11));
        lblMatricola.setForeground(new Color(120, 120, 120));
        footerPanel.add(lblMatricola);

        card.add(headerPanel, BorderLayout.NORTH);
        card.add(centralPanel, BorderLayout.CENTER);
        card.add(footerPanel, BorderLayout.SOUTH);

        return card;
    }

    
     // Crea un pannello con 5 stelle per visualizzare il punteggio.
     private JPanel creaPannelloStelle(int punteggio) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        panel.setBackground(Color.WHITE);

        ImageIcon starIconPiena = new ImageIcon(getClass().getResource("/icons/icons8-stella-32.png"));
        ImageIcon starIconVuota = new ImageIcon(getClass().getResource("/icons/icons8-stella-vuota-32.png"));

        Image imgPiena = starIconPiena.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imgVuota = starIconVuota.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        ImageIcon stellaPienaRidimensionata = new ImageIcon(imgPiena);
        ImageIcon stellaVuotaRidimensionata = new ImageIcon(imgVuota);

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