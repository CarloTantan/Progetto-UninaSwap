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
	private JTable table;
	private JTable tabellaAnnunci;
	 private DefaultTableModel modelTabella;
	   private JTable tabellaOfferta;
	    private ArrayList<Annuncio_entity> AnnunciPubblicati;
	    private Offerta_entity Annuncipubb;
	    
	    
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnunciPubblicati.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Annunci Pubblicati");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 406);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultTableModel modelTabellaAnnunci = new DefaultTableModel(
			    new Object[][]{},
			    new String[]{"Titolo", "Descrizione", "FasciaOraria", "ModalitàConsegna", 
			                 "StatoAnnuncio", "DataPubblicazione", "Extra", "Categoria"}
			) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			tabellaAnnunci = new JTable(modelTabella);
			tabellaAnnunci.setBackground(Color.WHITE);
			tabellaAnnunci.getTableHeader().setReorderingAllowed(false);

			JScrollPane scrollPane = new JScrollPane(tabellaAnnunci);
			scrollPane.setBounds(39, 230, 1106, 238);
			contentPane.add(scrollPane);
			caricaAnnunci();
			
			   // Creazione modello tabella
			DefaultTableModel    modelTabellaOfferte = new DefaultTableModel(
	            new Object[][]{},
	            new String[]{ "Stato ", "Matricola Acquirente", "Tipologia"} ) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        tabellaOfferta = new JTable(modelTabella);
	        tabellaOfferta.setBackground(Color.WHITE);
	        tabellaOfferta.getTableHeader().setReorderingAllowed(false);
	        
	        JScrollPane scrollPaneO = new JScrollPane(tabellaOfferta);
	        scrollPane.setBounds(39, 230, 1106, 238);
	        contentPane.add(scrollPane);	     

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(10, 0, 729, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Annunci Pubblicati");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_1.setBounds(268, 5, 219, 53);
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
		btnUndo.setIcon(new ImageIcon(AnnunciPubblicati.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(0, 0, 95, 68);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JButton btnAccetta = new JButton("Accetta");
		btnAccetta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Offerta accettata con successo", "Offerta accettata ", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnAccetta.setFont(new Font("Verdana", Font.BOLD, 16));
		btnAccetta.setBackground(new Color(0, 52, 101));
		btnAccetta.setForeground(new Color(255, 255, 255));
		btnAccetta.setBounds(136, 245, 153, 44);
		btnAccetta.setFocusPainted(false);
		btnAccetta.setBorderPainted(false);
		contentPane.add(btnAccetta);
		
		JButton btnRifiuta = new JButton("Rifiuta");
		btnRifiuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Offerta Rifiutata con successo", "Offerta Rifiutata ", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnRifiuta.setForeground(Color.WHITE);
		btnRifiuta.setFont(new Font("Verdana", Font.BOLD, 16));
		btnRifiuta.setBackground(new Color(0, 52, 101));
		btnRifiuta.setBounds(443, 245, 153, 44);
		btnRifiuta.setFocusPainted(false);
		btnRifiuta.setBorderPainted(false); 
		contentPane.add(btnRifiuta);
	}
	
	
	
	
	
	private void caricaAnnunci() {
		String matricola= UtenteLoggato.getMatricola();
	    try {
	    	
	    	Annunci_OfferteDAO selectAnnunci = new Annunci_OfferteDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<Annuncio_entity> Annunci = selectAnnunci.getAnnunci(matricola);

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	                "Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Categoria","SelezioneOfferte"
	            });

	        // Aggiungi ogni utente come riga nella tabella
	        for (Annuncio_entity A : Annunci) {
	            model.addRow(new Object[]{
	                A.getTitolo(),
	                A.getDescrizione(),
	                A.getFasciaOraria(),
	                A.getModalitàConsegna(),
	                A.getStatoAnnuncio(),
	                A.getDataPubblicazione(),
	                A.getTipologiaCategoria() ,
	                A.getVisualizzaOfferte() ? "Sì" : "No"
	            });
	        }

	        JOptionPane.showMessageDialog(this,
	            "Caricati " + Annunci.size() + " Annunci",
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
	
	
	
	
	
	
	
	private void SelezionaAnnuncio() {
        int selectedRow = tabellaAnnunci.getSelectedRow();
        
        // Validazione: verifica che sia selezionata una riga
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un ' annuncio!",
                "Errore",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Recupera i dati dalla riga selezionata
        Annuncio_entity AnnuncioSelezionato = AnnunciPubblicati.get(selectedRow);
        
        // Verifica se la recensione è già stata inserita
        if (AnnuncioSelezionato.getVisualizzaOfferte()) {
        	CaricaOfferte();
        }

      
    }
	
	private void CaricaOfferte() {
		int idAnnuncio= Annuncipubb.getIdAnnuncio();
	    try {
	    	
	    	Annunci_OfferteDAO selectOfferte = new Annunci_OfferteDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<Offerta_entity> Offerte = selectOfferte.getOfferte(idAnnuncio);

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaOfferta.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	                "Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Categoria","SelezioneOfferte"
	            });

	        // Aggiungi ogni utente come riga nella tabella
	        for (Offerta_entity O : Offerte) {
	            model.addRow(new Object[]{
	                O.getStatoOfferta(),
	                O.getMatricolaAcquirente(),
	                O.getTipologiaOfferta()
	               });
	        }

	        JOptionPane.showMessageDialog(this,
	            "Caricati " + Offerte.size() + " Annunci",
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
