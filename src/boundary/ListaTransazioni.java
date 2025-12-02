package boundary;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.TransazioniDAO;
import entity.Transazione_entity;
import entity.Utente_entity;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ListaTransazioni extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabellaTransazioni;
    private DefaultTableModel modelTabella;
    private Utente_entity UtenteLoggato;
    private ArrayList<Transazione_entity> ListaTransazioni;

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
    public ListaTransazioni(Utente_entity UtenteLoggato) {
    	this.UtenteLoggato = UtenteLoggato;
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ListaTransazioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Lista Transazioni");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1100, 500));
        
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Panel superiore (header)
        JPanel headerPanel  = new JPanel();
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
        btnUndo.setIcon(new ImageIcon(ListaTransazioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(50, 132, 188));
        btnUndo.setPreferredSize(new Dimension(50, 50));
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        btnUndo.setContentAreaFilled(false);
        
        // Effetto hover per il pulsante back
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
        JLabel lblTitolo = new JLabel("Transazioni avvenute");
        lblTitolo.setForeground(Color.WHITE);
        lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblTitolo);
        headerPanel.add(centerPanel, BorderLayout.CENTER);

        // Pannello destro con pulsante inserisci recensione
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(50, 132, 188));
        rightPanel.setPreferredSize(new Dimension(250, 100));
        rightPanel.setBorder(new EmptyBorder(25, 0, 0, 15));
        
        JButton btnInserisciRecensione = new JButton("Inserisci recensione");
        btnInserisciRecensione.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnInserisciRecensione.setBackground(new Color(0, 52, 104));
        btnInserisciRecensione.setForeground(Color.WHITE);
        btnInserisciRecensione.setFocusPainted(false);
        btnInserisciRecensione.setBorderPainted(false);
        btnInserisciRecensione.setPreferredSize(new Dimension(200, 45));
        
        // Effetto hover
        btnInserisciRecensione.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInserisciRecensione.setBackground(new Color(0, 70, 140));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInserisciRecensione.setBackground(new Color(0, 52, 104));
            }
        });
        
        btnInserisciRecensione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriInserimentoRecensione();
            }
        });
        
        rightPanel.add(btnInserisciRecensione);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        // Container principale per la tabella con padding
        JPanel mainContainer = new JPanel();
        mainContainer.setBackground(new Color(245, 247, 250));
        mainContainer.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainContainer.setLayout(new BorderLayout());
        contentPane.add(mainContainer, BorderLayout.CENTER);

        // Creazione modello tabella
        modelTabella = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Titolo", "Matricola Acquirente", "Matricola Venditore", "Recensione Inserita"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Creazione tabella
        tabellaTransazioni = new JTable(modelTabella);
        tabellaTransazioni.setBackground(Color.WHITE);
        tabellaTransazioni.setFont(new Font("Verdana", Font.PLAIN, 13));
        tabellaTransazioni.setRowHeight(30);
        tabellaTransazioni.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        tabellaTransazioni.getTableHeader().setBackground(new Color(0, 52, 104));
        tabellaTransazioni.getTableHeader().setForeground(Color.WHITE);
        tabellaTransazioni.getTableHeader().setReorderingAllowed(false);
        tabellaTransazioni.setSelectionBackground(new Color(70, 152, 208));
        tabellaTransazioni.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabellaTransazioni);
        scrollPane.setBorder(null);
        mainContainer.add(scrollPane, BorderLayout.CENTER);
        
        // Carica le transazioni
        caricaTransazioni();
        
        
    }
    
    //carica le transazioni dell'utente loggato
    private void caricaTransazioni() {
        try {
            TransazioniDAO TransazioniDao = new TransazioniDAO();
            ListaTransazioni = TransazioniDao.getTransazioni(UtenteLoggato.getMatricola());

            for (Transazione_entity t : ListaTransazioni) {
                modelTabella.addRow(new Object[]{
                    t.getTitoloAnnuncio(),
                    t.getMatricolaAcquirente(),
                    t.getMatricolaVenditore(),
                    t.hasRecensione() ? "Sì" : "No"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento delle transazioni",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Apre l'interfaccia per inserire una recensione
    private void apriInserimentoRecensione() {
        int selectedRow = tabellaTransazioni.getSelectedRow();
        
        // Validazione: verifica che sia selezionata una riga
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona una transazione!",
                "Errore",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Recupera i dati dalla riga selezionata
        Transazione_entity transazioneSelezionata = ListaTransazioni.get(selectedRow);
        
        // Verifica se la recensione è già stata inserita
        if (transazioneSelezionata.hasRecensione()) {
            JOptionPane.showMessageDialog(this,
                "Hai già inserito una recensione per questa transazione!",
                "Recensione già presente",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Apri l'interfaccia di inserimento recensione
        this.dispose();
        InserimentoRecensione recensioneFrame = new InserimentoRecensione(
            UtenteLoggato,
            transazioneSelezionata.getMatricolaAcquirente(),
            transazioneSelezionata.getMatricolaVenditore(),
            transazioneSelezionata.getIdOfferta()
        );
        recensioneFrame.setVisible(true);
        
    }
    
    //torna all'area utente
    private void tornaAreaUtente() {
        this.dispose();
        AreaUtente areaUtenteFrame = new AreaUtente(UtenteLoggato);
        areaUtenteFrame.setVisible(true);
        
    }
}