package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ListaAnnunciDAO;
import entity.AnnuncioRegalo_entity;
import entity.AnnuncioScambio_entity;
import entity.AnnuncioVendita_entity;
import entity.Annuncio_entity;
import entity.Utente_entity;
import enumerations.StatoAnnuncio;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTable;

public class ListaAnnunci extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable tabellaAnnunci;
	private Utente_entity UtenteLoggato;
	private JComboBox comboBoxTipologia;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaAnnunci frame = new ListaAnnunci();
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
	public ListaAnnunci(Utente_entity UtenteLoggato) {
		this.UtenteLoggato = UtenteLoggato;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ricerca annuncio");
		setBounds(100, 100, 1236, 846);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 6, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		DefaultTableModel modelTabella = new DefaultTableModel(
			    new Object[][]{},
			    new String[]{"MatricolaVenditore","IdAnnuncio", "Titolo", "Descrizione", "FasciaOraria", "ModalitàConsegna", 
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
			
			tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
			tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
			tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
			tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
			tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
			tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

			JScrollPane scrollPane = new JScrollPane(tabellaAnnunci);
			scrollPane.setBounds(39, 230, 1106, 238);
			contentPane.add(scrollPane);
		
		String[] tipologie= {"Seleziona una tipologia", "Vendita", "Scambio", "Regalo"};
		comboBoxTipologia = new JComboBox(tipologie);
		comboBoxTipologia.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxTipologia.setBounds(106, 175, 258, 34);
		comboBoxTipologia.setBackground(new Color(45, 134, 192));
		contentPane.add(comboBoxTipologia);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 811, 98);
		panel.setBackground(new Color(45, 134, 192));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista Annunci");
		lblNewLabel.setBounds(333, 43, 179, 31);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
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
		btnUndo.setIcon(new ImageIcon(ListaAnnunci.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 47, 78);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setIcon(new ImageIcon(ListaAnnunci.class.getResource("/icons/icons8-lista-48.png")));
		lblNewLabel_1.setBounds(657, 10, 45, 78);
		panel.add(lblNewLabel_1);
		

		
		String[] categorie= {"Seleziona una categoria", "Libri", "Musica", "Sport", "Cancelleria",  "Vestiti", "Elettronica", "Giochi", "Casa","Altro"};
		JComboBox comboBoxCategoria = new JComboBox(categorie);
		comboBoxCategoria.setFont(new Font("Verdana", Font.BOLD, 16));

		comboBoxCategoria.setBackground(new Color(45, 134, 192));
		comboBoxCategoria.setBounds(526, 175, 258, 34);
		contentPane.add(comboBoxCategoria);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTipologia.setBounds(136, 141, 143, 27);
		contentPane.add(lblTipologia);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCategoria.setBounds(555, 141, 143, 27);
		contentPane.add(lblCategoria);
		
		JButton btnInviaOfferta = new JButton("Invia Offerta\r\n");
		btnInviaOfferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apriOfferta();
			}
				
				
		});
		
		btnInviaOfferta.setForeground(new Color(255, 255, 255));
		btnInviaOfferta.setBackground(new Color(0, 52, 101));
		btnInviaOfferta.setFont(new Font("Verdana", Font.BOLD, 16));
		btnInviaOfferta.setBounds(82, 571, 209, 62);
		btnInviaOfferta.setFocusPainted(false);
		btnInviaOfferta.setBorderPainted(false);
		
		contentPane.add(btnInviaOfferta);
		
		JButton btnVisualizza = new JButton("Visualizza ");
		btnVisualizza.setForeground(Color.WHITE);
		btnVisualizza.setFont(new Font("Verdana", Font.BOLD, 16));
		btnVisualizza.setBackground(new Color(0, 52, 101));
		btnVisualizza.setBounds(531, 571, 209, 62);
		btnVisualizza.setFocusPainted(false);
		btnVisualizza.setBorderPainted(false);
		
		contentPane.add(btnVisualizza);
		

		
		btnVisualizza.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedTipologia = (String) comboBoxTipologia.getSelectedItem();
		        String selectedCategoria = (String) comboBoxCategoria.getSelectedItem();

		        if (selectedTipologia.equals("Seleziona una tipologia")) {
		            JOptionPane.showMessageDialog(null, "Seleziona una tipologia!");
		            return;
		        }
		        
		        // IF per VENDITA
		        if (selectedTipologia.equals("Vendita")) {
		            if (selectedCategoria.equals("Seleziona una categoria")) {
		                caricaAnnunciVendita();
		            } else {
		                // SWITCH per le categorie di Vendita
		                switch (selectedCategoria) {
		                    case "Libri":
		                        caricaAnnunciVendita_Libri();
		                        break;
		                    case "Musica":
		                        caricaAnnunciVendita_Musica();
		                        break;
		                    case "Sport":
		                        caricaAnnunciVendita_Sport();
		                        break;
		                    case "Cancelleria":
		                        caricaAnnunciVendita_Cancelleria();
		                        break;
		                    case "Vestiti":
		                        caricaAnnunciVendita_Vestiti();
		                        break;
		                    case "Elettronica":
		                        caricaAnnunciVendita_Elettronica();
		                        break;
		                    case "Giochi":
		                        caricaAnnunciVendita_Giochi();
		                        break;
		                    case "Casa":
		                        caricaAnnunciVendita_Casa();
		                        break;
		                    case "Altro":
		                        caricaAnnunciVendita_Altro();
		                        break;
		                }
		            }
		        } 
		        // IF per REGALO
		        else if (selectedTipologia.equals("Regalo")) {
		            if (selectedCategoria.equals("Seleziona una categoria")) {
		                caricaAnnunciRegalo();
		            } else {
		                // SWITCH per le categorie di Regalo
		                switch (selectedCategoria) {
		                    case "Libri":
		                        caricaAnnunciRegalo_Libri();
		                        break;
		                    case "Musica":
		                        caricaAnnunciRegalo_Musica();
		                        break;
		                    case "Sport":
		                        caricaAnnunciRegalo_Sport();
		                        break;
		                    case "Cancelleria":
		                        caricaAnnunciRegalo_Cancelleria();
		                        break;
		                    case "Vestiti":
		                        caricaAnnunciRegalo_Vestiti();
		                        break;
		                    case "Elettronica":
		                        caricaAnnunciRegalo_Elettronica();
		                        break;
		                    case "Giochi":
		                        caricaAnnunciRegalo_Giochi();
		                        break;
		                    case "Casa":
		                        caricaAnnunciRegalo_Casa();
		                        break;
		                    case "Altro":
		                        caricaAnnunciRegalo_Altro();
		                        break;
		                }
		            }
		        } 
		        // IF per SCAMBIO
		        else if (selectedTipologia.equals("Scambio")) {
		            if (selectedCategoria.equals("Seleziona una categoria")) {
		                caricaAnnunciScambio();
		            } else {
		                // SWITCH per le categorie di Scambio
		                switch (selectedCategoria) {
		                    case "Libri":
		                        caricaAnnunciScambio_Libri();
		                        break;
		                    case "Musica":
		                        caricaAnnunciScambio_Musica();
		                        break;
		                    case "Sport":
		                        caricaAnnunciScambio_Sport();
		                        break;
		                    case "Cancelleria":
		                        caricaAnnunciScambio_Cancelleria();
		                        break;
		                    case "Vestiti":
		                        caricaAnnunciScambio_Vestiti();
		                        break;
		                    case "Elettronica":
		                        caricaAnnunciScambio_Elettronica();
		                        break;
		                    case "Giochi":
		                        caricaAnnunciScambio_Giochi();
		                        break;
		                    case "Casa":
		                        caricaAnnunciScambio_Casa();
		                        break;
		                    case "Altro":
		                        caricaAnnunciScambio_Altro();
		                        break;
		                }
		            }
		        } else {
		            caricaAnnunci();
		        }
		    }
		});
	}
	
	private void caricaAnnunci() {
	    try {
	    	
	        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<Annuncio_entity> Annunci = selectAnnunci.getAnnunci();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (Annuncio_entity A : Annunci) {
	            model.addRow(new Object[]{
	            		A.getMatricolaVenditore(),
	            	A.getIdAnnuncio(),	
	                A.getTitolo(),
	                A.getDescrizione(),
	                A.getFasciaOraria(),
	                A.getModalitàConsegna(),
	                A.getStatoAnnuncio(),
	                A.getDataPubblicazione(),
	                A.getTipologiaCategoria() 
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
	
	private void caricaAnnunciRegalo() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Motivo Cessione", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioRegalo_entity A : Annunci) {
	            model.addRow(new Object[]{
	            		A.getMatricolaVenditore(),
	            	A.getIdAnnuncio(),
	                A.getTitolo(),
	                A.getDescrizione(),
	                A.getFasciaOraria(),
	                A.getModalitàConsegna(),
	                A.getStatoAnnuncio(),
	                A.getDataPubblicazione(),
	                A.getMotivoCessione(),
	                A.getTipologiaCategoria() 
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
	
	
	private void caricaAnnunciScambio() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioScambio_entity A : Annunci) {
	            model.addRow(new Object[]{
	            		A.getMatricolaVenditore(),
	            	A.getIdAnnuncio(),	
	                A.getTitolo(),
	                A.getDescrizione(),
	                A.getFasciaOraria(),
	                A.getModalitàConsegna(),
	                A.getStatoAnnuncio(),
	                A.getDataPubblicazione(),
	                A.getOggettoRichiesto(),
	                A.getTipologiaCategoria() 
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
	

	private void caricaAnnunciVendita() {
	    try {
	        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita();

	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
	        model.setRowCount(0); // Pulisci la tabella
	        
	        // Aggiorna le colonne per questo tipo
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	            "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
	        });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	            		Av.getMatricolaVenditore(),
	            	Av.getIdAnnuncio(),
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getFasciaOraria(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoVendita(),
	                Av.getTipologiaCategoria()
	            });
	        }

	        JOptionPane.showMessageDialog(this,
	            "Caricati " + Annunci.size() + " Annunci",
	            "Successo",
	            JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException e) {
	        System.err.println("Errore: " + e.getMessage());
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this,
	            "Errore nel caricamento: " + e.getMessage(),
	            "Errore",
	            JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void caricaAnnunciVendita_Libri() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_libri();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	            		Av.getMatricolaVenditore(),
	            	Av.getIdAnnuncio(),
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getFasciaOraria(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoVendita(),
	                Av.getTipologiaCategoria() 
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
	            "Errore nel caricamento dei dati:  " + e.getMessage(),
	            "Errore",
	            JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	
	
	private void caricaAnnunciVendita_Musica() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Musica();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	            		Av.getMatricolaVenditore(),
	            		Av.getIdAnnuncio(),
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getFasciaOraria(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoVendita(),
	                Av.getTipologiaCategoria() 
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
	

	private void caricaAnnunciVendita_Cancelleria() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Cancelleria();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	            		Av.getMatricolaVenditore(),
	            		Av.getIdAnnuncio(),
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getFasciaOraria(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoVendita(),
	                Av.getTipologiaCategoria() 
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
	
	
//Vestiti	
	private void caricaAnnunciVendita_Vestiti() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Vestiti();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	            		Av.getMatricolaVenditore(),
	            		Av.getIdAnnuncio(),
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getFasciaOraria(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoVendita(),
	                Av.getTipologiaCategoria() 
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
	
		
	//Elettronica

	private void caricaAnnunciVendita_Elettronica() {
	    try {
	    	
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Elettronica();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);
	        model.setColumnIdentifiers(new String[]{
	        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
	                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
	            });
	        
	        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
	        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	            		Av.getMatricolaVenditore(),
	            		Av.getIdAnnuncio(),
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getFasciaOraria(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoVendita(),
	                Av.getTipologiaCategoria() 
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




private void caricaAnnunciVendita_Giochi() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Giochi();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioVendita_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getPrezzoVendita(),
                Av.getTipologiaCategoria() 
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
//Sport
private void caricaAnnunciVendita_Sport() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Sport();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioVendita_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getPrezzoVendita(),
                Av.getTipologiaCategoria() 
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

private void caricaAnnunciVendita_Casa() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Casa();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioVendita_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getPrezzoVendita(),
                Av.getTipologiaCategoria() 
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

private void caricaAnnunciVendita_Altro() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendita_Altro();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioVendita_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getPrezzoVendita(),
                Av.getTipologiaCategoria() 
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
//REGALO



private void caricaAnnunciRegalo_Libri() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_libro();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioRegalo_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getMotivoCessione(),
                Av.getTipologiaCategoria() 
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
            "Errore nel caricamento dei dati:  " + e.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}





private void caricaAnnunciRegalo_Musica() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Musica();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioRegalo_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getMotivoCessione(),
                Av.getTipologiaCategoria() 
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


private void caricaAnnunciRegalo_Cancelleria() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Cancelleria();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioRegalo_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getMotivoCessione(),
                Av.getTipologiaCategoria() 
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


//Vestiti	
private void caricaAnnunciRegalo_Vestiti() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Vestiti();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioRegalo_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getMotivoCessione(),
                Av.getTipologiaCategoria() 
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

	
//Elettronica

private void caricaAnnunciRegalo_Elettronica() {
    try {
    	
        // Crea un'istanza di SelectAcquirenti
		    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        
        // Chiama il metodo sull'istanza
        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Elettronica();

        // Prendi il modello della tabella
        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

        // Pulisci eventuali righe esistenti
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
                "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
            });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        // Aggiungi ogni utente come riga nella tabella
        for (AnnuncioRegalo_entity Av : Annunci) {
            model.addRow(new Object[]{
            		Av.getMatricolaVenditore(),
            		Av.getIdAnnuncio(),
                Av.getTitolo(),
                Av.getDescrizione(),
                Av.getFasciaOraria(),
                Av.getModalitàConsegna(),
                Av.getStatoAnnuncio(),
                Av.getDataPubblicazione(),
                Av.getMotivoCessione(),
                Av.getTipologiaCategoria() 
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




private void caricaAnnunciRegalo_Giochi() {
try {
	
    // Crea un'istanza di SelectAcquirenti
	    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
    
    // Chiama il metodo sull'istanza
    ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Giochi();

    // Prendi il modello della tabella
    DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

    // Pulisci eventuali righe esistenti
    model.setRowCount(0);
    model.setColumnIdentifiers(new String[]{
    		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
        });
    
    tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

    // Aggiungi ogni utente come riga nella tabella
    for (AnnuncioRegalo_entity Av : Annunci) {
        model.addRow(new Object[]{
        		Av.getMatricolaVenditore(),
        		Av.getIdAnnuncio(),
            Av.getTitolo(),
            Av.getDescrizione(),
            Av.getFasciaOraria(),
            Av.getModalitàConsegna(),
            Av.getStatoAnnuncio(),
            Av.getDataPubblicazione(),
            Av.getMotivoCessione(),
            Av.getTipologiaCategoria() 
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
//Sport
private void caricaAnnunciRegalo_Sport() {
try {
	
    // Crea un'istanza di SelectAcquirenti
	    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
    
    // Chiama il metodo sull'istanza
    ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Sport();

    // Prendi il modello della tabella
    DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

    // Pulisci eventuali righe esistenti
    model.setRowCount(0);
    model.setColumnIdentifiers(new String[]{
    		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
        });
    
    tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

    // Aggiungi ogni utente come riga nella tabella
    for (AnnuncioRegalo_entity Av : Annunci) {
        model.addRow(new Object[]{
        		Av.getMatricolaVenditore(),
        		Av.getIdAnnuncio(),
            Av.getTitolo(),
            Av.getDescrizione(),
            Av.getFasciaOraria(),
            Av.getModalitàConsegna(),
            Av.getStatoAnnuncio(),
            Av.getDataPubblicazione(),
            Av.getMotivoCessione(),
            Av.getTipologiaCategoria() 
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

private void caricaAnnunciRegalo_Casa() {
try {
	
    // Crea un'istanza di SelectAcquirenti
	    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
    
    // Chiama il metodo sull'istanza
    ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Casa();

    // Prendi il modello della tabella
    DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

    // Pulisci eventuali righe esistenti
    model.setRowCount(0);
    model.setColumnIdentifiers(new String[]{
    		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
        });
    
    tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

    // Aggiungi ogni utente come riga nella tabella
    for (AnnuncioRegalo_entity Av : Annunci) {
        model.addRow(new Object[]{
        		Av.getMatricolaVenditore(),
        		Av.getIdAnnuncio(),
            Av.getTitolo(),
            Av.getDescrizione(),
            Av.getFasciaOraria(),
            Av.getModalitàConsegna(),
            Av.getStatoAnnuncio(),
            Av.getDataPubblicazione(),
            Av.getMotivoCessione(),
            Av.getTipologiaCategoria() 
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

private void caricaAnnunciRegalo_Altro() {
try {
	
    // Crea un'istanza di SelectAcquirenti
	    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
    
    // Chiama il metodo sull'istanza
    ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo_Altro();

    // Prendi il modello della tabella
    DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();

    // Pulisci eventuali righe esistenti
    model.setRowCount(0);
    model.setColumnIdentifiers(new String[]{
    		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Prezzo Vendita", "Categoria"
        });
    
    tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
    tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

    // Aggiungi ogni utente come riga nella tabella
    for (AnnuncioRegalo_entity Av : Annunci) {
        model.addRow(new Object[]{
        		Av.getMatricolaVenditore(),
        		Av.getIdAnnuncio(),
            Av.getTitolo(),
            Av.getDescrizione(),
            Av.getFasciaOraria(),
            Av.getModalitàConsegna(),
            Av.getStatoAnnuncio(),
            Av.getDataPubblicazione(),
            Av.getMotivoCessione(),
            Av.getTipologiaCategoria() 
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

private void caricaAnnunciScambio_Libri() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_libri();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Musica() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_musica();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Cancelleria() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_cancelleria();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Vestiti() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_vestiti();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Elettronica() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_elettronica();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Giochi() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_Giochi();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Sport() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_Sport();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Casa() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_Casa();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}

private void caricaAnnunciScambio_Altro() {
    try {
        ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio_Altro();

        DefaultTableModel model = (DefaultTableModel) tabellaAnnunci.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
        		"MatricolaVenditore", "IdAnnuncio","Titolo", "Descrizione", "Fascia Oraria", "Modalità Consegna", 
            "Stato Annuncio", "Data Pubblicazione", "Oggetto Richiesto", "Categoria"
        });
        
        tabellaAnnunci.getColumnModel().getColumn(0).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(0).setWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMinWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setMaxWidth(0);
        tabellaAnnunci.getColumnModel().getColumn(1).setWidth(0);

        for (AnnuncioScambio_entity As : Annunci) {
            model.addRow(new Object[]{
            		As.getMatricolaVenditore(),
            		As.getIdAnnuncio(),
                As.getTitolo(),
                As.getDescrizione(),
                As.getFasciaOraria(),
                As.getModalitàConsegna(),
                As.getStatoAnnuncio(),
                As.getDataPubblicazione(),
                As.getOggettoRichiesto(),
                As.getTipologiaCategoria()
            });
        }

        JOptionPane.showMessageDialog(this,
            "Caricati " + Annunci.size() + " Annunci",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        System.err.println("Errore durante il caricamento degli Annunci: " + ex.getMessage());
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Errore nel caricamento dei dati: " + ex.getMessage(),
            "Errore",
            JOptionPane.ERROR_MESSAGE);
    }
}
	public void apriOfferta() {
		int selectedRow = tabellaAnnunci.getSelectedRow();
        
        // Validazione: verifica che sia selezionata una riga
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleziona un annuncio!",
                "Errore",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
		
		String TipologiaScelta = (String) comboBoxTipologia.getSelectedItem();
		int IdAnnuncioScelto = (int) tabellaAnnunci.getValueAt(selectedRow, 1);
		
		StatoAnnuncio StatoAnnuncioScelto = (StatoAnnuncio) tabellaAnnunci.getValueAt(selectedRow, 6);
		String MatricolaVenditore = (String) tabellaAnnunci.getValueAt(selectedRow, 0);
		
		if (StatoAnnuncioScelto.equals(StatoAnnuncio.Chiuso)) {
			JOptionPane.showMessageDialog(this,
	                "Annuncio chiuso, non è possibile fare un'offerta.",
	                "Errore",
	                JOptionPane.WARNING_MESSAGE);
			return;
		} else if (MatricolaVenditore.equals(UtenteLoggato.getMatricola())) {

			JOptionPane.showMessageDialog(this,
	                "Non puoi fare un'offerta sul tuo annuncio.",
	                "Errore",
	                JOptionPane.WARNING_MESSAGE);
		} else {
		
			if(TipologiaScelta.equals("Scambio"))
				{
			setVisible(false);
			OffertaScambio OffertaScambioFrame = new OffertaScambio(UtenteLoggato, IdAnnuncioScelto);
			OffertaScambioFrame.setVisible(true);
			OffertaScambioFrame.setLocationRelativeTo(null);
			
				}else if (TipologiaScelta.equals("Vendita")) {
					setVisible(false);
					OffertaVendita OffertaVenditaFrame = new OffertaVendita(UtenteLoggato, IdAnnuncioScelto);
					OffertaVenditaFrame.setVisible(true);
					OffertaVenditaFrame.setLocationRelativeTo(null);
					
					
				}else if (TipologiaScelta.equals("Regalo")) {
					setVisible(false);
					OffertaRegalo OffertaRegaloFrame = new OffertaRegalo(UtenteLoggato, IdAnnuncioScelto);
					OffertaRegaloFrame.setVisible(true);
					OffertaRegaloFrame.setLocationRelativeTo(null);
					
				}
		}
	}
		
}


