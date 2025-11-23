package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Annunci_OfferteDAO;
import dao.ListaAnnunciDAO;
import entity.Annuncio_entity;
import entity.Offerta_entity;
import entity.Transazione_entity;
import entity.Utente_entity;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AnnunciPubblicati extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Utente_entity UtenteLoggato;
    private JTable tabellaAnnunci;
    private JTable tabellaOfferta;
    private ArrayList<Annuncio_entity> listaAnnunci;
    private ArrayList<Offerta_entity> listaOfferte;
    private JScrollPane scrollPaneAnnunci;
    private JScrollPane scrollPaneOfferte;
    private JButton btnVisualizzaOfferte;
    private JButton btnTornaAnnunci;
    private JButton btnAccetta;
    private JButton btnRifiuta;
	    
	    
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OfferteRicevute frame = new OfferteRicevute();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AnnunciPubblicati(Utente_entity UtenteLoggato) {
		this.UtenteLoggato = UtenteLoggato;
		this.listaAnnunci = new ArrayList<>();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnunciPubblicati.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Annunci Pubblicati");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 134, 192));
        panel.setBounds(10, 0, 1160, 68);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Annunci Pubblicati");
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(320, 5, 250, 53);
        panel.add(lblNewLabel_1);
        
        JButton btnUndo = new JButton("");
        btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
                AreaUtenteFrame.setVisible(true);
                AreaUtenteFrame.setLocationRelativeTo(null);
            }
        });
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setIcon(new ImageIcon(
            AnnunciPubblicati.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBounds(0, 0, 95, 68);
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        panel.add(btnUndo);
		
        DefaultTableModel modelTabellaAnnunci = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Titolo", "Descrizione", "Fascia Oraria", "ModalitàConsegna",
                    "Stato Annuncio", "Data Pubblicazione", "Categoria", "Offerte"}
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            tabellaAnnunci = new JTable(modelTabellaAnnunci);
            tabellaAnnunci.setBackground(Color.WHITE);
            tabellaAnnunci.setFont(new Font("Verdana", Font.PLAIN, 12));
            tabellaAnnunci.setRowHeight(25);
            tabellaAnnunci.getTableHeader().setReorderingAllowed(false);
			
			scrollPaneAnnunci = new JScrollPane(tabellaAnnunci);
	        scrollPaneAnnunci.setBounds(20, 90, 856, 300);
	        contentPane.add(scrollPaneAnnunci);

			
			
			   // Creazione modello tabella
			DefaultTableModel modelTabellaOfferte = new DefaultTableModel(
		            new Object[][]{},
		            new String[]{"Stato", "Matricola Acquirente", "Tipologia"}
		        ) {
		            @Override
		            public boolean isCellEditable(int row, int column) {
		                return false;
		            }
		        };
		        
		        tabellaOfferta = new JTable(modelTabellaOfferte);
		        tabellaOfferta.setBackground(Color.WHITE);
		        tabellaOfferta.setFont(new Font("Verdana", Font.PLAIN, 12));
		        tabellaOfferta.setRowHeight(25);
		        tabellaOfferta.getTableHeader().setReorderingAllowed(false);
	        
	        scrollPaneOfferte = new JScrollPane(tabellaOfferta);
	        scrollPaneOfferte.setBounds(20, 90, 856, 300);
	        scrollPaneOfferte.setVisible(false);  // Nascosta inizialmente
	        contentPane.add(scrollPaneOfferte);
	        
	        btnVisualizzaOfferte = new JButton("Visualizza Offerte");
	        btnVisualizzaOfferte.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                SelezionaAnnuncio();
	            }
	        });
	        btnVisualizzaOfferte.setFont(new Font("Verdana", Font.BOLD, 16));
	        btnVisualizzaOfferte.setBackground(new Color(0, 52, 101));
	        btnVisualizzaOfferte.setForeground(new Color(255, 255, 255));
	        btnVisualizzaOfferte.setBounds(470, 470, 250, 50);
	        btnVisualizzaOfferte.setFocusPainted(false);
	        btnVisualizzaOfferte.setBorderPainted(false);
	        contentPane.add(btnVisualizzaOfferte);
	        
	        btnTornaAnnunci = new JButton("Torna agli Annunci");
	        btnTornaAnnunci.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                mostraAnnunci();
	            }
	        });
	        btnTornaAnnunci.setFont(new Font("Verdana", Font.BOLD, 16));
	        btnTornaAnnunci.setBackground(new Color(0, 52, 101));
	        btnTornaAnnunci.setForeground(new Color(255, 255, 255));
	        btnTornaAnnunci.setBounds(330, 410, 230, 44);
	        btnTornaAnnunci.setFocusPainted(false);
	        btnTornaAnnunci.setBorderPainted(false);
	        btnTornaAnnunci.setVisible(false);  // Nascosto inizialmente
	        contentPane.add(btnTornaAnnunci);
		
	        btnAccetta = new JButton("Accetta");
	        btnAccetta.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                accettaOfferta();
	            }
	        });
	        btnAccetta.setFont(new Font("Verdana", Font.BOLD, 16));
	        btnAccetta.setBackground(new Color(0, 52, 101));
	        btnAccetta.setForeground(new Color(255, 255, 255));
	        btnAccetta.setBounds(180, 410, 153, 44);
	        btnAccetta.setFocusPainted(false);
	        btnAccetta.setBorderPainted(false);
	        btnAccetta.setVisible(false);  // Nascosto inizialmente
	        contentPane.add(btnAccetta);
		
	        btnRifiuta = new JButton("Rifiuta");
	        btnRifiuta.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                rifiutaOfferta();
	            }
	        });
	        btnRifiuta.setForeground(Color.WHITE);
	        btnRifiuta.setFont(new Font("Verdana", Font.BOLD, 16));
	        btnRifiuta.setBackground(new Color(0, 52, 101));
	        btnRifiuta.setBounds(550, 410, 153, 44);
	        btnRifiuta.setFocusPainted(false);
	        btnRifiuta.setBorderPainted(false);
	        btnRifiuta.setVisible(false);  // Nascosto inizialmente
	        contentPane.add(btnRifiuta);
	        
	        caricaAnnunci();
	}
	
	
	
	
	
	private void caricaAnnunci() {
		String matricola= UtenteLoggato.getMatricola();
	    try {
	    	
	    	Annunci_OfferteDAO selectAnnunci = new Annunci_OfferteDAO();
	        
	        // Chiama il metodo sull'istanza
	    	listaAnnunci = selectAnnunci.getAnnunci(matricola);

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        
	        for (Annuncio_entity A : listaAnnunci) {
                model.addRow(new Object[]{
                    A.getTitolo(),
                    A.getDescrizione(),
                    A.getFasciaOraria(),
                    A.getModalitàConsegna(),
                    A.getStatoAnnuncio(),
                    A.getDataPubblicazione(),
                    A.getTipologiaCategoria(),
                    A.getVisualizzaOfferte() ? "Sì" : "No"
                });
            }

	       	} catch (SQLException e) {
	            System.err.println("Errore durante il caricamento degli Annunci: " + e.getMessage());
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this,
	                "Errore nel caricamento dei dati: " + e.getMessage(),
	                "Errore",
	                JOptionPane.ERROR_MESSAGE);
	        }
	    }
	
	
	
	private void SelezionaAnnuncio() {
        int selectedRow = tabellaAnnunci.getSelectedRow();
        
        // Validazione: verifica che sia selezionata una riga
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un annuncio dalla tabella!",
                "Nessuna selezione",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Recupera i dati dalla riga selezionata
        Annuncio_entity AnnuncioSelezionato = listaAnnunci.get(selectedRow);
        
     // Verifica se ci sono offerte
        if (!AnnuncioSelezionato.getVisualizzaOfferte()) {
            JOptionPane.showMessageDialog(this,
                "Non ci sono offerte per questo annuncio",
                "Nessuna offerta",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        caricaOfferte(AnnuncioSelezionato.getIdAnnuncio());

      
    }
	
	private void caricaOfferte(int IdAnnuncio) {
	    try {
	    	
	    	Annunci_OfferteDAO selectOfferte = new Annunci_OfferteDAO();
	        
	        // Chiama il metodo sull'istanza
	    	listaOfferte = selectOfferte.getOfferte(IdAnnuncio);

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaOfferta.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (Offerta_entity O : listaOfferte) {
	            model.addRow(new Object[]{
	                O.getStatoOfferta(),
	                O.getMatricolaAcquirente(),
	                O.getTipologiaOfferta()
	               });
	        }
	        
	        mostraOfferte();

	        if (listaOfferte.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Nessuna offerta trovata",
                    "Informazione",
                    JOptionPane.INFORMATION_MESSAGE);
            }

	    } catch (SQLException e) {
            System.err.println("Errore durante il caricamento delle offerte: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento delle offerte: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
	
	// ========== METODO PER MOSTRARE LA VISTA OFFERTE ==========
    private void mostraOfferte() {
        scrollPaneAnnunci.setVisible(false);
        btnVisualizzaOfferte.setVisible(false);
        
        scrollPaneOfferte.setVisible(true);
        btnTornaAnnunci.setVisible(true);
        btnAccetta.setVisible(true);
        btnRifiuta.setVisible(true);
    }
	
	// ========== METODO PER TORNARE ALLA VISTA ANNUNCI ==========
    private void mostraAnnunci() {
        scrollPaneOfferte.setVisible(false);
        btnTornaAnnunci.setVisible(false);
        btnAccetta.setVisible(false);
        btnRifiuta.setVisible(false);
        
        scrollPaneAnnunci.setVisible(true);
        btnVisualizzaOfferte.setVisible(true);
    }

    // ========== METODO PER ACCETTARE UN'OFFERTA ==========
    private void accettaOfferta() {
        int selectedRow = tabellaOfferta.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un'offerta dalla tabella!",
                "Nessuna selezione",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Offerta_entity offertaSelezionata = listaOfferte.get(selectedRow);
        
        int conferma = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler accettare questa offerta?\n" +
                "L'annuncio verrà chiuso e tutte le altre offerte verranno rifiutate.",
                "Conferma accettazione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (conferma != JOptionPane.YES_OPTION) {
                return;
            }
        
            try {
                Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
                // ✅ Ora non serve più passare la matricola del venditore!
                boolean successo = dao.accettaOfferta(offertaSelezionata.getIdOfferta());
                
                if (successo) {
                    JOptionPane.showMessageDialog(this,
                        "Offerta accettata con successo!\n" +
                        "L'annuncio è stato chiuso automaticamente.",
                        "Offerta accettata",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    mostraAnnunci();
                    caricaAnnunci();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Errore: offerta non trovata o già gestita",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (SQLException e) {
                System.err.println("Errore durante l'accettazione dell'offerta: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                    "Errore durante l'accettazione: " + e.getMessage(),
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

    // ========== METODO PER RIFIUTARE UN'OFFERTA ==========
    private void rifiutaOfferta() {
        int selectedRow = tabellaOfferta.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un'offerta dalla tabella!",
                "Nessuna selezione",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Offerta_entity offertaSelezionata = listaOfferte.get(selectedRow);
        
        // Conferma prima di rifiutare
        int conferma = JOptionPane.showConfirmDialog(this,
            "Sei sicuro di voler rifiutare questa offerta?",
            "Conferma rifiuto",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (conferma != JOptionPane.YES_OPTION) {
            return;
        }
        
        try {
            Annunci_OfferteDAO dao = new Annunci_OfferteDAO();
            boolean successo = dao.rifiutaOfferta(offertaSelezionata.getIdOfferta());
            
            if (successo) {
                JOptionPane.showMessageDialog(this,
                    "Offerta rifiutata con successo",
                    "Offerta rifiutata",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Rimuovi l'offerta dalla lista e dalla tabella
                listaOfferte.remove(selectedRow);
                DefaultTableModel model = (DefaultTableModel) tabellaOfferta.getModel();
                model.removeRow(selectedRow);
                
                // Se non ci sono più offerte, torna agli annunci
                if (listaOfferte.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Non ci sono più offerte da visualizzare",
                        "Informazione",
                        JOptionPane.INFORMATION_MESSAGE);
                    mostraAnnunci();
                    caricaAnnunci();
                }
                
            } else {
                JOptionPane.showMessageDialog(this,
                    "Errore: offerta non trovata o già gestita",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            System.err.println("Errore durante il rifiuto dell'offerta: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore durante il rifiuto: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
