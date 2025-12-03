package boundary;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ReportDAO;
import entity.Utente_entity;

public class Report extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Utente_entity UtenteLoggato;
    private JTable tabellaOfferte;
    private JTable tabellaPrezzi;

    public Report(Utente_entity UtenteLoggato) {
        this.UtenteLoggato = UtenteLoggato;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ETICHETTA TABELLA OFFERTE
        JLabel lblOfferte = new JLabel("Riepilogo Offerte");
        lblOfferte.setBounds(63, 50, 200, 25);
        lblOfferte.setFont(lblOfferte.getFont().deriveFont(16f));
        contentPane.add(lblOfferte);

        // TABELLA 1: OFFERTE
        DefaultTableModel modelTabella = new DefaultTableModel(
    		    new Object[][]{},
    		    new String[]{"Offerte  Totali", "Offerte Regalo Inviata", "Offerte Scambio Inviata", "Offerte Vendita Inviata","Offerte Vendita Accettata","Offerte Regalo Accettata","Offerte Scambio Accettata"}
    		) {
    		    @Override
    		    public boolean isCellEditable(int row, int column) {
    		        return false;
    		    }
    		};

    		tabellaOfferte = new JTable(modelTabella);
    		tabellaOfferte.setBackground(Color.WHITE);
    		tabellaOfferte.getTableHeader().setReorderingAllowed(false);

    	

        JScrollPane scrollPaneOfferte = new JScrollPane(tabellaOfferte);
        scrollPaneOfferte.setBounds(63, 85, 900, 80);
        contentPane.add(scrollPaneOfferte);

        // ETICHETTA TABELLA PREZZI
        JLabel lblPrezzi = new JLabel("Statistiche Prezzi Annunci");
        lblPrezzi.setBounds(63, 200, 250, 25);
        lblPrezzi.setFont(lblPrezzi.getFont().deriveFont(16f));
        contentPane.add(lblPrezzi);

        // TABELLA 2: PREZZI
        DefaultTableModel modelPrezzi = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Prezzo Minimo", "Prezzo Massimo", "Prezzo Medio"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabellaPrezzi = new JTable(modelPrezzi);
        tabellaPrezzi.setBackground(Color.WHITE);
        tabellaPrezzi.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPanePrezzi = new JScrollPane(tabellaPrezzi);
        scrollPanePrezzi.setBounds(63, 235, 600, 80);
        contentPane.add(scrollPanePrezzi);

        // Carica i dati
        caricaOfferte();
        caricaPrezzi();
    }

   

    private void caricaPrezzi() {
    	String matricola = UtenteLoggato.getMatricola();
    	
        try {
            ReportDAO reportDAO = new ReportDAO();
            
            // Ottieni i dati dei prezzi
            String[] prezzi = reportDAO.getPrezziAnnunci(matricola);
            
            // Prendi il modello della tabella
            DefaultTableModel model = (DefaultTableModel) tabellaPrezzi.getModel();
            model.setRowCount(0);
            
            // Aggiungi una riga
            model.addRow(new Object[]{
                prezzi[0],  // Prezzo Minimo
                prezzi[1],  // Prezzo Massimo
                prezzi[2]   // Prezzo Medio
            });
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento dei prezzi: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento dei prezzi: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void caricaOfferte() {
        String matricola = UtenteLoggato.getMatricola();
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            
            // Ottieni i dati delle offerte
            int offerteTotali = reportDAO.VisualizzaOfferteTotali(matricola);
            int offerteRegalo = reportDAO.VisualizzaOfferteRegalo(matricola);
    		int offerteScambio = reportDAO.VisualizzaOfferteScambio(matricola);
    		int offerteVendita = reportDAO.VisualizzaOfferteVendita(matricola);
    		int offerteRegaloAccettata = reportDAO.VisualizzaOfferteRegaloAccettata(matricola);
    		int offertaScambioAccettata = reportDAO.VisualizzaOfferteScambioAccettata(matricola);
    		int offerteVenditaAccettata = reportDAO.VisualizzaOfferteVenditaAccettata(matricola);
            
            // Prendi il modello della tabella
            DefaultTableModel model = (DefaultTableModel) tabellaOfferte.getModel();
            model.setRowCount(0);
            
            // Aggiungi una riga
            model.addRow(new Object[]{
                offerteTotali, 
                 offerteRegalo,
                 offerteScambio,
        		offerteVendita,
            	offerteRegaloAccettata,
        		offertaScambioAccettata,
        		offerteVenditaAccettata
        		
            });
            
        } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle offerte: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento delle offerte: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
   
}


