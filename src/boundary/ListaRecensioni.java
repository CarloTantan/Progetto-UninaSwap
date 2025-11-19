package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ListaAnnunciDAO;
import dao.ListaRecensioniDao;
import entity.AnnuncioVendita_entity;
import entity.Recensione_entity;
import entity.Utente_entity;

import java.awt.Color;
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

public class ListaRecensioni extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private JTable tabellaRecensione;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaRecensioni frame = new ListaRecensioni();
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
	public ListaRecensioni(Utente_entity UtenteLoggato) {
		this.UtenteLoggato = UtenteLoggato;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaRecensioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Le tue recensioni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel modelTabella = new DefaultTableModel(
			    new Object[][]{},
			    new String[]{"Commento", "Punteggio", "Data", 
			                 "MatricolaAcquirente", "MatricolaVenditore"}
			) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};

			tabellaRecensione = new JTable(modelTabella);
			tabellaRecensione.setBackground(Color.WHITE);
			tabellaRecensione.getTableHeader().setReorderingAllowed(false);
			
			JScrollPane scrollPane = new JScrollPane(tabellaRecensione);
			scrollPane.setBounds(63, 226, 657, 156);
			contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(10, 0, 767, 75);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblListaRecensioni = new JLabel("Lista Recensioni");
		lblListaRecensioni.setFont(new Font("Verdana", Font.BOLD, 20));
		lblListaRecensioni.setBounds(297, 20, 227, 32);
		panel.add(lblListaRecensioni);
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
				AreaUtenteFrame.setVisible(true);
				AreaUtenteFrame.setLocationRelativeTo(null);
			}
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(ListaRecensioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(0, 0, 85, 75);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JButton btnRecensioniInviate = new JButton("Recensioni Inviate");
		btnRecensioniInviate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			GetRecensioniInviate();
			
			}

			
		});
		btnRecensioniInviate.setFont(new Font("Verdana", Font.BOLD, 16));
		btnRecensioniInviate.setBackground(new Color(0, 52, 101));
		btnRecensioniInviate.setForeground(new Color(255, 255, 255));
		btnRecensioniInviate.setBounds(63, 154, 250, 61);
		btnRecensioniInviate.setFocusPainted(false);
		btnRecensioniInviate.setBorderPainted(false);
		contentPane.add(btnRecensioniInviate);
		
		
		
		JButton btnRecensioniRicevute = new JButton("Recensioni Ricevute");
		btnRecensioniRicevute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			GetRecensioniRicevute();
			
			}

			
		});

		btnRecensioniRicevute.setFont(new Font("Verdana", Font.BOLD, 16));
		btnRecensioniRicevute.setForeground(Color.WHITE);
		btnRecensioniRicevute.setBackground(new Color(0, 52, 101));
		btnRecensioniRicevute.setBounds(457, 154, 263, 61);
		btnRecensioniRicevute.setFocusPainted(false);
		btnRecensioniRicevute.setBorderPainted(false);
		contentPane.add(btnRecensioniRicevute);
	}
	
	
	
	
	
	private void GetRecensioniInviate() {
	String matricola= UtenteLoggato.getMatricola();
		 try {
		    	
		        // Crea un'istanza di SelectAcquirenti
				    ListaRecensioniDao selectRecensioni = new ListaRecensioniDao();
		        
		        // Chiama il metodo sull'istanza
		        ArrayList<Recensione_entity> Recensioni = selectRecensioni.VisualizzaRecensioniInviate(matricola);

		        // Prendi il modello della tabella
		        DefaultTableModel model = (DefaultTableModel) tabellaRecensione.getModel();

		        // Pulisci eventuali righe esistenti
		     

		        // Aggiungi ogni utente come riga nella tabella
		        for (Recensione_entity R : Recensioni) {
		            model.addRow(new Object[]{
		                R.getCommento(),
		                R.getPunteggio(),
		                R.getData(),
		                R.getMatricolaAcquirente(),
		                R.getMatricolaVenditore()
		                });
		        }

		        JOptionPane.showMessageDialog(this,
		            "Caricati " + Recensioni.size() + " Recensioni",
		            "Successo",
		            JOptionPane.INFORMATION_MESSAGE);

		    } catch (SQLException e) {
		        System.err.println("Errore durante il caricamento delle Recensioni: " + e.getMessage());
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(this,
		            "Errore nel caricamento dei dati: " + e.getMessage(),
		            "Errore",
		            JOptionPane.ERROR_MESSAGE);
		    }
	}
	
	private void GetRecensioniRicevute() {
		String matricola= UtenteLoggato.getMatricola();
			 try {
			    	
			        // Crea un'istanza di SelectRecensioni
					    ListaRecensioniDao selectRecensioni = new ListaRecensioniDao();
			        
			        // Chiama il metodo sull'istanza
			        ArrayList<Recensione_entity> Recensioni = selectRecensioni.VisualizzaRecensioniRicevute(matricola);

			        // Prendi il modello della tabella
			        DefaultTableModel model = (DefaultTableModel) tabellaRecensione.getModel();			     

			        // Aggiungi ogni utente come riga nella tabella
			        for (Recensione_entity R : Recensioni) {
			            model.addRow(new Object[]{
			                R.getCommento(),
			                R.getPunteggio(),
			                R.getData(),
			                R.getMatricolaAcquirente(),
			                R.getMatricolaVenditore()
			                });
			        }

			        JOptionPane.showMessageDialog(this,
			            "Caricati " + Recensioni.size() + " Recensioni",
			            "Successo",
			            JOptionPane.INFORMATION_MESSAGE);

			    } catch (SQLException e) {
			        System.err.println("Errore durante il caricamento delle Recensioni: " + e.getMessage());
			        e.printStackTrace();
			        JOptionPane.showMessageDialog(this,
			            "Errore nel caricamento dei dati: " + e.getMessage(),
			            "Errore",
			            JOptionPane.ERROR_MESSAGE);
			    }
		}
		
		
	
	
	
	
}



