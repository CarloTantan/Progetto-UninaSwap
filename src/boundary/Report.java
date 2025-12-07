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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import dao.ReportDAO;
import entity.Utente_entity;
import mainController.MainController;

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
    private MainController controller;

    public Report(Utente_entity UtenteLoggato, MainController controller) {
        this.UtenteLoggato = UtenteLoggato;
        this.controller = controller;
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
                AreaUtente areaUtenteFrame = new AreaUtente(UtenteLoggato, controller);
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
        
        // Carica i dati PRIMA di creare l'interfaccia
        caricaOfferte();
        caricaPrezzi();
        
        // ========== CONTENUTO PRINCIPALE ==========
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridBagLayout());
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        
        // ========== TABELLA OFFERTE ==========
        gbc.gridy = 0;
        JLabel lblOfferte = new JLabel("Riepilogo Offerte");
        lblOfferte.setFont(new Font("Verdana", Font.BOLD, 18));
        lblOfferte.setForeground(new Color(0, 52, 101));
        mainPanel.add(lblOfferte, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 20, 20);
        
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
        tabellaOfferte.setFont(new Font("Verdana", Font.PLAIN, 12));
        tabellaOfferte.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
        tabellaOfferte.getTableHeader().setBackground(new Color(45, 134, 192));
        tabellaOfferte.getTableHeader().setForeground(Color.WHITE);
        tabellaOfferte.getTableHeader().setReorderingAllowed(false);
        tabellaOfferte.setRowHeight(30);
        
        // Popola la tabella offerte
        DefaultTableModel modelOff = (DefaultTableModel) tabellaOfferte.getModel();
        modelOff.addRow(new Object[]{
            offerteTotali, 
            offerteRegalo,
            offerteScambio,
            offerteVendita,
            offerteVenditaAccettata,
            offerteRegaloAccettata,
            offertaScambioAccettata
        });

        JScrollPane scrollPaneOfferte = new JScrollPane(tabellaOfferte);
        scrollPaneOfferte.setPreferredSize(new Dimension(1100, 80));
        mainPanel.add(scrollPaneOfferte, gbc);

        // ========== TABELLA PREZZI ==========
        gbc.gridy = 2;
        gbc.insets = new Insets(40, 20, 10, 20);
        
        JLabel lblPrezzi = new JLabel("Statistiche Prezzi Annunci");
        lblPrezzi.setFont(new Font("Verdana", Font.BOLD, 18));
        lblPrezzi.setForeground(new Color(0, 52, 101));
        mainPanel.add(lblPrezzi, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 20, 20);
        
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
        tabellaPrezzi.setFont(new Font("Verdana", Font.PLAIN, 12));
        tabellaPrezzi.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
        tabellaPrezzi.getTableHeader().setBackground(new Color(45, 134, 192));
        tabellaPrezzi.getTableHeader().setForeground(Color.WHITE);
        tabellaPrezzi.getTableHeader().setReorderingAllowed(false);
        tabellaPrezzi.setRowHeight(30);
        
        // Popola la tabella prezzi
        DefaultTableModel modelPr = (DefaultTableModel) tabellaPrezzi.getModel();
        modelPr.addRow(new Object[]{
            prezzi[0],
            prezzi[1],
            prezzi[2]
        });

        JScrollPane scrollPanePrezzi = new JScrollPane(tabellaPrezzi);
        scrollPanePrezzi.setPreferredSize(new Dimension(600, 80));
        mainPanel.add(scrollPanePrezzi, gbc);
        
        // ========== GRAFICI ==========
        gbc.gridy = 4;
        gbc.insets = new Insets(40, 20, 10, 20);
        
        JLabel lblGrafici = new JLabel("Analisi Grafiche");
        lblGrafici.setFont(new Font("Verdana", Font.BOLD, 18));
        lblGrafici.setForeground(new Color(0, 52, 101));
        mainPanel.add(lblGrafici, gbc);
        
        // Panel per contenere i tre grafici
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 20, 30, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        
        JPanel graficiPanel = new JPanel(new GridBagLayout());
        graficiPanel.setBackground(Color.WHITE);
        mainPanel.add(graficiPanel, gbc);
        
        GridBagConstraints gbcGrafici = new GridBagConstraints();
        gbcGrafici.fill = GridBagConstraints.BOTH;
        gbcGrafici.insets = new Insets(10, 10, 10, 10);
        gbcGrafici.weightx = 1.0;
        gbcGrafici.weighty = 1.0;
        
        // Grafico 1: Torta Offerte Totali
        gbcGrafici.gridx = 0;
        gbcGrafici.gridy = 0;
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        JLabel lbl1 = new JLabel("Offerte Inviate per Tipologia", JLabel.CENTER);
        lbl1.setFont(new Font("Verdana", Font.BOLD, 14));
        lbl1.setForeground(new Color(0, 52, 101));
        lbl1.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel1.add(lbl1, BorderLayout.NORTH);
        ChartPanel chart1 = creaGraficoOfferteTotali();
        chart1.setPreferredSize(new Dimension(400, 350));
        panel1.add(chart1, BorderLayout.CENTER);
        graficiPanel.add(panel1, gbcGrafici);
        
        // Grafico 2: Torta Offerte Accettate
        gbcGrafici.gridx = 1;
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(Color.WHITE);
        JLabel lbl2 = new JLabel("Offerte Accettate per Tipologia", JLabel.CENTER);
        lbl2.setFont(new Font("Verdana", Font.BOLD, 14));
        lbl2.setForeground(new Color(0, 52, 101));
        lbl2.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel2.add(lbl2, BorderLayout.NORTH);
        ChartPanel chart2 = creaGraficoOfferteAccettate();
        chart2.setPreferredSize(new Dimension(400, 350));
        panel2.add(chart2, BorderLayout.CENTER);
        graficiPanel.add(panel2, gbcGrafici);
        
        // Grafico 3: Barre Prezzi
        gbcGrafici.gridx = 2;
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.WHITE);
        JLabel lbl3 = new JLabel("Analisi Prezzi", JLabel.CENTER);
        lbl3.setFont(new Font("Verdana", Font.BOLD, 14));
        lbl3.setForeground(new Color(0, 52, 101));
        lbl3.setBorder(new EmptyBorder(5, 5, 10, 5));
        panel3.add(lbl3, BorderLayout.NORTH);
        ChartPanel chart3 = creaGraficoPrezzi();
        chart3.setPreferredSize(new Dimension(400, 350));
        panel3.add(chart3, BorderLayout.CENTER);
        graficiPanel.add(panel3, gbcGrafici);
    }

    private ChartPanel creaGraficoOfferteTotali() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
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
            "Totali: " + offerteTotali,
            dataset,
            true,
            true,
            false
        );
        
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setSectionPaint("Regalo (" + offerteRegalo + ")", new Color(209, 56, 56)); // Rosso
        plot.setSectionPaint("Scambio (" + offerteScambio + ")", new Color(108, 67, 232)); // Viola
        plot.setSectionPaint("Vendita (" + offerteVendita + ")", new Color(56, 209, 97)); // Verde        
        
        ChartPanel chartPanel = new ChartPanel(grafico);
        return chartPanel;
    }
    
    private ChartPanel creaGraficoOfferteAccettate() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        int totaleAccettate = offerteRegaloAccettata + offertaScambioAccettata + offerteVenditaAccettata;
        
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
            "Accettate: " + totaleAccettate,
            dataset,
            true,
            true,
            false
        );
        
        ChartPanel chartPanel = new ChartPanel(grafico);
        return chartPanel;
    }
    
    private ChartPanel creaGraficoPrezzi() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (prezzi != null && !prezzi[0].contains("Non")) {
            try {
                double min = Double.parseDouble(prezzi[0].replace("€", ""));
                double max = Double.parseDouble(prezzi[1].replace("€", ""));
                double medio = Double.parseDouble(prezzi[2].replace("€", ""));
                
                dataset.addValue(min, "Prezzi", "Minimo");
                dataset.addValue(medio, "Prezzi", "Medio");
                dataset.addValue(max, "Prezzi", "Massimo");
            } catch (NumberFormatException e) {
                dataset.addValue(0, "Prezzi", "Nessun Dato");
            }
        } else {
            dataset.addValue(0, "Prezzi", "Nessun Dato");
        }
        
        JFreeChart grafico = ChartFactory.createBarChart(
            "Offerte Vendita",
            "Tipo",
            "Importo (€)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        CategoryPlot plot = grafico.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        renderer.setSeriesPaint(0, new Color(50, 132, 188)); // Blu
        
        ChartPanel chartPanel = new ChartPanel(grafico);
        return chartPanel;
    }

    private void caricaPrezzi() {
        String matricola = UtenteLoggato.getMatricola();
        
        try {
            ReportDAO reportDAO = new ReportDAO();
            prezzi = reportDAO.getPrezziAnnunci(matricola);
            
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
            
            offerteTotali = reportDAO.VisualizzaOfferteTotali(matricola);
            offerteRegalo = reportDAO.VisualizzaOfferteRegalo(matricola);
            offerteScambio = reportDAO.VisualizzaOfferteScambio(matricola);
            offerteVendita = reportDAO.VisualizzaOfferteVendita(matricola);
            offerteRegaloAccettata = reportDAO.VisualizzaOfferteRegaloAccettata(matricola);
            offertaScambioAccettata = reportDAO.VisualizzaOfferteScambioAccettata(matricola);
            offerteVenditaAccettata = reportDAO.VisualizzaOfferteVenditaAccettata(matricola);
            
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


