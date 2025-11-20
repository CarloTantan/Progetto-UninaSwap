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
import java.awt.FlowLayout;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ListaTransazioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Panel superiore (header)
        JPanel panelHeader = new JPanel();
        panelHeader.setPreferredSize(new Dimension(getWidth(), 80));
        panelHeader.setBackground(new Color(70, 171, 225));
        panelHeader.setLayout(new BorderLayout(0, 0));
        contentPane.add(panelHeader, BorderLayout.NORTH);

        // Bottone Undo a sinistra
        JButton btnUndo = new JButton("");
        btnUndo.setIcon(new ImageIcon(ListaTransazioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setPreferredSize(new Dimension(80, 80));
        panelHeader.add(btnUndo, BorderLayout.WEST);
        btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornaAreaUtente();
			}
		});

        // Label centrata
        JLabel lblTitolo = new JLabel("Transazioni avvenute");
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(lblTitolo, BorderLayout.CENTER);

        JButton btnInserisciRecensione = new JButton("Inserisci recensione");
        btnInserisciRecensione.setFont(new Font("Verdana", Font.BOLD, 16));
        btnInserisciRecensione.setBackground(new Color(0, 52, 101));
        btnInserisciRecensione.setForeground(Color.WHITE);
        btnInserisciRecensione.setFocusPainted(false);
        btnInserisciRecensione.setBorderPainted(false);
        panelHeader.add(btnInserisciRecensione, BorderLayout.EAST);
        
        btnInserisciRecensione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriInserimentoRecensione();
            }
        });

        // Panel centrale
        JPanel panelCentrale = new JPanel();
        panelCentrale.setBackground(Color.WHITE);
        contentPane.add(panelCentrale, BorderLayout.CENTER);
        
        // Creazione modello tabella
        modelTabella = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Titolo", "Matricola Acquirente", "Matricola Venditore", 
                        "Id Annuncio", "Id Offerta", "Recensione Inserita"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Creazione tabella
        tabellaTransazioni = new JTable(modelTabella);
        tabellaTransazioni.setBackground(Color.WHITE);
        tabellaTransazioni.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabellaTransazioni);
        scrollPane.setBounds(39, 230, 1106, 238);
        contentPane.add(scrollPane);
        
     // Carica le transazioni
        caricaTransazioni();
        
        // Nascondi le colonne ID
        nascondiColonneID();
    }
    
    
     //Carica le transazioni dell'utente dalla base di dati
    
    private void caricaTransazioni() {
        try {
            TransazioniDAO dao = new TransazioniDAO();
            ArrayList<Transazione_entity> lista = dao.getTransazioni(UtenteLoggato.getMatricola());

            for (Transazione_entity t : lista) {
                modelTabella.addRow(new Object[]{
                    t.getTitoloAnnuncio(),
                    t.getMatricolaAcquirente(),
                    t.getMatricolaVenditore(),
                    t.getIdAnnuncio(),
                    t.getIdOfferta(),
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
    
    
     //Nasconde le colonne ID dalla vista della tabella
     
    private void nascondiColonneID() {
        // Nascondi colonna ID Annuncio
        tabellaTransazioni.getColumnModel().getColumn(3).setMinWidth(0);
        tabellaTransazioni.getColumnModel().getColumn(3).setMaxWidth(0);
        tabellaTransazioni.getColumnModel().getColumn(3).setWidth(0);
        tabellaTransazioni.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        // Nascondi colonna ID Offerta
        tabellaTransazioni.getColumnModel().getColumn(4).setMinWidth(0);
        tabellaTransazioni.getColumnModel().getColumn(4).setMaxWidth(0);
        tabellaTransazioni.getColumnModel().getColumn(4).setWidth(0);
        tabellaTransazioni.getColumnModel().getColumn(4).setPreferredWidth(0);
    }
    
    
     //Apre l'interfaccia per inserire una recensione
     
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
        String matricolaAcquirente = (String) tabellaTransazioni.getValueAt(selectedRow, 1);
        String matricolaVenditore = (String) tabellaTransazioni.getValueAt(selectedRow, 2);
        int idAnnuncio = (int) tabellaTransazioni.getValueAt(selectedRow, 3);
        int idOfferta = (int) tabellaTransazioni.getValueAt(selectedRow, 4);
        String hasRecensioneString = (String) tabellaTransazioni.getValueAt(selectedRow, 5);
        boolean hasRecensione = "Sì".equals(hasRecensioneString);
        
        // Verifica se la recensione è già stata inserita
        if (hasRecensione) {
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
            matricolaAcquirente,
            matricolaVenditore,
            idOfferta
        );
        recensioneFrame.setVisible(true);
        recensioneFrame.setLocationRelativeTo(null);
    }
    
    
     //Torna all'Area Utente
     
    private void tornaAreaUtente() {
        this.dispose();
        AreaUtente areaUtenteFrame = new AreaUtente(UtenteLoggato);
        areaUtenteFrame.setVisible(true);
        areaUtenteFrame.setLocationRelativeTo(null);
    }
}