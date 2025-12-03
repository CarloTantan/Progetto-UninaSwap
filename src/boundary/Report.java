package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import dao.ReportDAO;
import entity.Utente_entity;

public class Report extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Utente_entity UtenteLoggato;
    private JTable tabellaOfferte;
    private JTable tabellaPrezzi;
    private int offerteTotali;
    private int offerteRegalo;
    private int offerteScambio;
    private int offerteVendita;
    private int offerteRegaloAccettata;
    private int offertaScambioAccettata;
    private int offerteVenditaAccettata;
    private String[] prezzi;

    public Report(Utente_entity UtenteLoggato) {
        this.UtenteLoggato = UtenteLoggato;
        setIconImage(Toolkit.getDefaultToolkit().getImage(Report.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Report");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 800));

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 247, 250));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        // ========== HEADER ==========
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
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AreaUtente areaUtenteFrame = new AreaUtente(UtenteLoggato);
                areaUtenteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                areaUtenteFrame.setVisible(true);
            }
        });
        btnUndo.setIcon(new ImageIcon(Report.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
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
        
        leftPanel.add(btnUndo);
        headerPanel.add(leftPanel, BorderLayout.WEST);
        
        // Pannello centrale con titolo
        JPanel centerHeaderPanel = new JPanel();
        centerHeaderPanel.setBackground(new Color(50, 132, 188));
        centerHeaderPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        JLabel lblTitle = new JLabel("Report Statistiche");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        centerHeaderPanel.add(lblTitle);
        headerPanel.add(centerHeaderPanel, BorderLayout.CENTER);
        
        // Pannello destro con info utente
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(50, 132, 188));
        rightPanel.setPreferredSize(new Dimension(300, 100));
        rightPanel.setBorder(new EmptyBorder(10, 0, 0, 15));
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        
        JLabel lblIconaUtente = new JLabel("");
        lblIconaUtente.setIcon(new ImageIcon(Report.class.getResource("/icons/icons8-utente-uomo-cerchiato-96.png")));
        rightPanel.add(lblIconaUtente);
        
        JLabel lblNomeUtente = new JLabel(UtenteLoggato.getNominativo());
        lblNomeUtente.setForeground(Color.WHITE);
        lblNomeUtente.setFont(new Font("Verdana", Font.BOLD, 16));
        rightPanel.add(lblNomeUtente);
        
        headerPanel.add(rightPanel, BorderLayout.EAST);
        
        // ========== CONTENUTO PRINCIPALE ==========
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridBagLayout());
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.weightx = 1.0;

        // ETICHETTA TABELLA OFFERTE
        JLabel lblOfferte = new JLabel("Riepilogo Offerte");
        lblOfferte.setBounds(63, 50, 200, 25);
        lblOfferte.setFont(lblOfferte.getFont().deriveFont(16f));
        contentPane.add(lblOfferte);

        // TABELLA 1: OFFERTE
        DefaultTableModel modelTabella = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Offerte Totali", "Offerte Regalo Inviata", "Offerte Scambio Inviata", 
                            "Offerte Vendita Inviata","Offerte Vendita Accettata",
                            "Offerte Regalo Accettata","Offerte Scambio Accettata"}
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
        
        // GRAFICI - Posizionali sotto le tabelle
        
        // Grafico 1: Torta Offerte Totali per Tipologia
        JLabel lblGraficoTotali = new JLabel("Distribuzione Offerte Inviate per Tipologia");
        lblGraficoTotali.setBounds(63, 350, 350, 25);
        lblGraficoTotali.setFont(lblGraficoTotali.getFont().deriveFont(14f));
        contentPane.add(lblGraficoTotali);
        
        ChartPanel panelloGraficoTotali = creaGraficoOfferteTotali();
        panelloGraficoTotali.setBounds(63, 380, 400, 300);
        contentPane.add(panelloGraficoTotali);
        
        // Grafico 2: Torta Offerte Accettate per Tipologia
        JLabel lblGraficoAccettate = new JLabel("Distribuzione Offerte Accettate per Tipologia");
        lblGraficoAccettate.setBounds(520, 350, 350, 25);
        lblGraficoAccettate.setFont(lblGraficoAccettate.getFont().deriveFont(14f));
        contentPane.add(lblGraficoAccettate);
        
        ChartPanel panelloGraficoAccettate = creaGraficoOfferteAccettate();
        panelloGraficoAccettate.setBounds(520, 380, 400, 300);
        contentPane.add(panelloGraficoAccettate);
        
        // Grafico 3: Barre per i Prezzi
        JLabel lblGraficoPrezzi = new JLabel("Analisi Prezzi");
        lblGraficoPrezzi.setBounds(63, 700, 200, 25);
        lblGraficoPrezzi.setFont(lblGraficoPrezzi.getFont().deriveFont(14f));
        contentPane.add(lblGraficoPrezzi);
        
        ChartPanel panelloGraficoPrezzi = creaGraficoPrezzi();
        panelloGraficoPrezzi.setBounds(63, 730, 600, 300);
        contentPane.add(panelloGraficoPrezzi);
    }

    // Metodo per creare il grafico a torta delle offerte totali
    private ChartPanel creaGraficoOfferteTotali() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        // Aggiungi solo le offerte con valori > 0
        if (offerteRegalo > 0) {
            dataset.setValue("Regalo (" + offerteRegalo + ")", offerteRegalo);
        }
        if (offerteScambio > 0) {
            dataset.setValue("Scambio (" + offerteScambio + ")", offerteScambio);
        }
        if (offerteVendita > 0) {
            dataset.setValue("Vendita (" + offerteVendita + ")", offerteVendita);
        }
        
        JFreeChart grafico = ChartFactory.createPieChart(
            "Offerte Totali: " + offerteTotali,
            dataset,
            true,  // legenda
            true,  // tooltips
            false  // urls
        );
        
        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        return chartPanel;
    }
    
    // Metodo per creare il grafico a torta delle offerte accettate
    private ChartPanel creaGraficoOfferteAccettate() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        int totaleAccettate = offerteRegaloAccettata + offertaScambioAccettata + offerteVenditaAccettata;
        
        // Aggiungi solo le offerte accettate con valori > 0
        if (offerteRegaloAccettata > 0) {
            dataset.setValue("Regalo (" + offerteRegaloAccettata + ")", offerteRegaloAccettata);
        }
        if (offertaScambioAccettata > 0) {
            dataset.setValue("Scambio (" + offertaScambioAccettata + ")", offertaScambioAccettata);
        }
        if (offerteVenditaAccettata > 0) {
            dataset.setValue("Vendita (" + offerteVenditaAccettata + ")", offerteVenditaAccettata);
        }
        
        JFreeChart grafico = ChartFactory.createPieChart(
            "Offerte Accettate: " + totaleAccettate,
            dataset,
            true,
            true,
            false
        );
        
        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        return chartPanel;
    }
    
    // Metodo per creare il grafico a barre dei prezzi
    private ChartPanel creaGraficoPrezzi() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (prezzi != null && !prezzi[0].contains("Non")) {
            // Rimuovi il simbolo € e converti in double per il grafico
            try {
                double min = Double.parseDouble(prezzi[0].replace("€", ""));
                double max = Double.parseDouble(prezzi[1].replace("€", ""));
                double medio = Double.parseDouble(prezzi[2].replace("€", ""));
                
                dataset.addValue(min, "Prezzi", "Minimo");
                dataset.addValue(medio, "Prezzi", "Medio");
                dataset.addValue(max, "Prezzi", "Massimo");
            } catch (NumberFormatException e) {
                // Se c'è un errore nel parsing, crea dataset vuoto
                dataset.addValue(0, "Prezzi", "Nessun Dato");
            }
        } else {
            // Nessun dato disponibile
            dataset.addValue(0, "Prezzi", "Nessun Dato");
        }
        
        JFreeChart grafico = ChartFactory.createBarChart(
            "Statistiche Prezzi Offerte Vendita Accettate",
            "Tipo",
            "Importo (€)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        return chartPanel;
    }

    private void caricaPrezzi() {
        String matricola = UtenteLoggato.getMatricola();
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            
            // Ottieni i dati dei prezzi
            prezzi = reportDAO.getPrezziAnnunci(matricola);
            
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
            
            // Ottieni i dati delle offerte e memorizzali nelle variabili di istanza
            offerteTotali = reportDAO.VisualizzaOfferteTotali(matricola);
            offerteRegalo = reportDAO.VisualizzaOfferteRegalo(matricola);
            offerteScambio = reportDAO.VisualizzaOfferteScambio(matricola);
            offerteVendita = reportDAO.VisualizzaOfferteVendita(matricola);
            offerteRegaloAccettata = reportDAO.VisualizzaOfferteRegaloAccettata(matricola);
            offertaScambioAccettata = reportDAO.VisualizzaOfferteScambioAccettata(matricola);
            offerteVenditaAccettata = reportDAO.VisualizzaOfferteVenditaAccettata(matricola);
            
            // Prendi il modello della tabella
            DefaultTableModel model = (DefaultTableModel) tabellaOfferte.getModel();
            model.setRowCount(0);
            
            // Aggiungi una riga
            model.addRow(new Object[]{
                offerteTotali, 
                offerteRegalo,
                offerteScambio,
                offerteVendita,
                offerteVenditaAccettata,
                offerteRegaloAccettata,
                offertaScambioAccettata
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


