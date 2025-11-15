package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
	private SelectAcquirenti selectAcquirenti;
	private JTable table;
	private JTable tabellaUtenti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaAnnunci frame = new ListaAnnunci();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public ListaAnnunci() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ricerca annuncio");
		setBounds(100, 100, 1236, 846);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		String[] tipologie= {"Seleziona una tipologia", "Vendita", "Scambio", "Regalo"};
		JComboBox comboBoxTipologia = new JComboBox(tipologie);
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
				AreaUtente AreaUtenteFrame = new AreaUtente();
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
		

		
		String[] categorie= {"Seleziona una categoria", "Libri", "Musica", "Sport", "Altro"};
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
				
					
					String scelta=(String) comboBoxTipologia.getSelectedItem();
					
				if(scelta.equals("Scambio"))
						{
					setVisible(false);
					OffertaScambio OffertaScambioFrame = new OffertaScambio();
					OffertaScambioFrame.setVisible(true);
					OffertaScambioFrame.setLocationRelativeTo(null);
						}else if (scelta.equals("Vendita")) {
							setVisible(false);
							OffertaVendita OffertaVenditaFrame = new OffertaVendita();
							OffertaVenditaFrame.setVisible(true);
							OffertaVenditaFrame.setLocationRelativeTo(null);
							
							
						}else if (scelta.equals("Regalo")) {
							setVisible(false);
							OffertaRegalo OffertaRegaloFrame = new OffertaRegalo();
							OffertaRegaloFrame.setVisible(true);
							OffertaRegaloFrame.setLocationRelativeTo(null);
							
						}
				
				
				
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
		
		
		    
		    JScrollPane scrollPane = new JScrollPane(tabellaUtenti);
		    scrollPane.setBounds(39, 230, 1106, 238);
		    contentPane.add(scrollPane);
		
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   String selectedTipologia = (String) comboBoxTipologia.getSelectedItem();

			        
			        if (selectedTipologia != null && !selectedTipologia.equals("Regalo")) {
			        	caricaAnnunciRegalo();
			            
			        }  else if (selectedTipologia != null && !selectedTipologia.equals("Scambio")) {
			        	caricaAnnunciScambio();
			            
			        } else {
			           
			        	caricaAnnunci();
			        }
			}
			});
	}
	
	private void caricaAnnunci() {
	    try {
	    	DefaultTableModel modelTabella = new DefaultTableModel(
			        new Object[][]{},  // nessuna riga iniziale
			        new String[]{ "Titolo", "Descrizione", "ModalitàC", "StatoAnnuncio", "Oggetto",  "CategoriaOggetto", "DataPubblicazione"}  // colonne
			    ) {
			        @Override
			        public boolean isCellEditable(int row, int column) {
			            return false;  // rende la tabella non editabile
			        }
			    };
			    
			    tabellaUtenti = new JTable(modelTabella);
			    tabellaUtenti.setBackground(Color.WHITE);  // cambiato da BLACK a WHITE per leggibilità
			    tabellaUtenti.setBounds(106, 230, 678, 146);
			    contentPane.add(tabellaUtenti);
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<Annuncio_entity> Annunci = selectAnnunci.getAnnunci();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaUtenti.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (Annuncio_entity A : Annunci) {
	            model.addRow(new Object[]{
	                A.getTitolo(),
	                A.getDescrizione(),
	                A.getModalitàConsegna(),
	                A.getStatoAnnuncio(),
	                A.getOggetto(),
	                A.getCategoriaOggetto(),
	                A.getDataPubblicazione()
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
	    	DefaultTableModel modelTabella = new DefaultTableModel(
			        new Object[][]{},  // nessuna riga iniziale
			        new String[]{ "Titolo", "Descrizione", "ModalitàC", "StatoAnnuncio", "Oggetto", "CategoriaOggetto", "DataPubblicazione","MotivoCessione"}  // colonne
			    ) {
			        @Override
			        public boolean isCellEditable(int row, int column) {
			            return false;  // rende la tabella non editabile
			        }
			    };
			    
			    tabellaUtenti = new JTable(modelTabella);
			    tabellaUtenti.setBackground(Color.WHITE);  // cambiato da BLACK a WHITE per leggibilità
			    tabellaUtenti.setBounds(106, 230, 678, 146);
			    contentPane.add(tabellaUtenti);
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioRegalo_entity> Annunci = selectAnnunci.getAnnunciRegalo();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaUtenti.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioRegalo_entity A : Annunci) {
	            model.addRow(new Object[]{
	                A.getTitolo(),
	                A.getDescrizione(),
	                A.getModalitàConsegna(),
	                A.getStatoAnnuncio(),
	                A.getOggetto(),
	                A.getCategoriaOggetto(),
	                A.getDataPubblicazione(),
	                A.getMotivoCessione()
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
	    	DefaultTableModel modelTabella = new DefaultTableModel(
			        new Object[][]{},  // nessuna riga iniziale
			        new String[]{ "Titolo", "Descrizione", "ModalitàC", "StatoAnnuncio", "Oggetto", "CategoriaOggetto", "DataPubblicazione","OggettoRichiesto"}  // colonne
			    ) {
			        @Override
			        public boolean isCellEditable(int row, int column) {
			            return false;  // rende la tabella non editabile
			        }
			    };
			    
			    tabellaUtenti = new JTable(modelTabella);
			    tabellaUtenti.setBackground(Color.WHITE);  // cambiato da BLACK a WHITE per leggibilità
			    tabellaUtenti.setBounds(106, 230, 678, 146);
			    contentPane.add(tabellaUtenti);
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioScambio_entity> Annunci = selectAnnunci.getAnnunciScambio();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaUtenti.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioScambio_entity As : Annunci) {
	            model.addRow(new Object[]{
	                As.getTitolo(),
	                As.getDescrizione(),
	                As.getModalitàConsegna(),
	                As.getStatoAnnuncio(),
	                As.getOggetto(),
	                As.getCategoriaOggetto(),
	                As.getDataPubblicazione(),
	                As.getOggettoRichiesto()
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
	    	DefaultTableModel modelTabella = new DefaultTableModel(
			        new Object[][]{},  // nessuna riga iniziale
			        new String[]{ "Titolo", "Descrizione", "ModalitàC", "StatoAnnuncio", "Oggetto", "CategoriaOggetto", "DataPubblicazione","PrezzoProposto"}  // colonne
			    ) {
			        @Override
			        public boolean isCellEditable(int row, int column) {
			            return false;  // rende la tabella non editabile
			        }
			    };
			    
			    tabellaUtenti = new JTable(modelTabella);
			    tabellaUtenti.setBackground(Color.WHITE);  // cambiato da BLACK a WHITE per leggibilità
			    tabellaUtenti.setBounds(106, 230, 678, 146);
			    contentPane.add(tabellaUtenti);
	        // Crea un'istanza di SelectAcquirenti
			    ListaAnnunciDAO selectAnnunci = new ListaAnnunciDAO();
	        
	        // Chiama il metodo sull'istanza
	        ArrayList<AnnuncioVendita_entity> Annunci = selectAnnunci.getAnnunciVendità();

	        // Prendi il modello della tabella
	        DefaultTableModel model = (DefaultTableModel) tabellaUtenti.getModel();

	        // Pulisci eventuali righe esistenti
	        model.setRowCount(0);

	        // Aggiungi ogni utente come riga nella tabella
	        for (AnnuncioVendita_entity Av : Annunci) {
	            model.addRow(new Object[]{
	                Av.getTitolo(),
	                Av.getDescrizione(),
	                Av.getModalitàConsegna(),
	                Av.getStatoAnnuncio(),
	                Av.getOggetto(),
	                Av.getCategoriaOggetto(),
	                Av.getDataPubblicazione(),
	                Av.getPrezzoProposto()
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
	
	
		
}

