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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
	private DefaultTableModel modelTabella;
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1250, 700));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 247, 250));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Header Panel
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
		btnUndo.setIcon(new ImageIcon(ListaRecensioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBackground(new Color(50, 132, 188));
		btnUndo.setPreferredSize(new Dimension(50, 50));
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		btnUndo.setContentAreaFilled(false);
		
		// Effetto hover
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
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
				AreaUtenteFrame.setVisible(true);
			}
		});
		
		leftPanel.add(btnUndo);
		headerPanel.add(leftPanel, BorderLayout.WEST);

		// Pannello centrale con titolo
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(50, 132, 188));
		centerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		JLabel lblTitolo = new JLabel("Lista Recensioni");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Verdana", Font.BOLD, 24));
		centerPanel.add(lblTitolo);
		headerPanel.add(centerPanel, BorderLayout.CENTER);

		// Container principale
		JPanel mainContainer = new JPanel();
		mainContainer.setBackground(new Color(245, 247, 250));
		mainContainer.setBorder(new EmptyBorder(30, 50, 30, 50));
		mainContainer.setLayout(new BorderLayout(0, 20));
		contentPane.add(mainContainer, BorderLayout.CENTER);

		// Pannello pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(245, 247, 250));
		buttonPanel.setLayout(new GridBagLayout());
		mainContainer.add(buttonPanel, BorderLayout.NORTH);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 15, 10, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		// Pulsante Recensioni Inviate
		JButton btnRecensioniInviate = new JButton("Recensioni Inviate");
		btnRecensioniInviate.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnRecensioniInviate.setBackground(new Color(0, 52, 104));
		btnRecensioniInviate.setForeground(Color.WHITE);
		btnRecensioniInviate.setPreferredSize(new Dimension(250, 50));
		btnRecensioniInviate.setFocusPainted(false);
		btnRecensioniInviate.setBorderPainted(false);
		
		// Effetto hover
		btnRecensioniInviate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRecensioniInviate.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRecensioniInviate.setBackground(new Color(0, 52, 104));
			}
		});
		
		btnRecensioniInviate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetRecensioniInviate();
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(btnRecensioniInviate, gbc);

		// Pulsante Recensioni Ricevute
		JButton btnRecensioniRicevute = new JButton("Recensioni Ricevute");
		btnRecensioniRicevute.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnRecensioniRicevute.setForeground(Color.WHITE);
		btnRecensioniRicevute.setBackground(new Color(0, 52, 104));
		btnRecensioniRicevute.setPreferredSize(new Dimension(250, 50));
		btnRecensioniRicevute.setFocusPainted(false);
		btnRecensioniRicevute.setBorderPainted(false);
		
		// Effetto hover
		btnRecensioniRicevute.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRecensioniRicevute.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRecensioniRicevute.setBackground(new Color(0, 52, 104));
			}
		});
		
		btnRecensioniRicevute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetRecensioniRicevute();
			}
		});
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonPanel.add(btnRecensioniRicevute, gbc);

		// Creazione modello tabella
		modelTabella = new DefaultTableModel(
			new Object[][]{},
			new String[]{"Commento", "Punteggio", "Data", "Matricola Acquirente", "Matricola Venditore"}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Creazione tabella
		tabellaRecensione = new JTable(modelTabella);
		tabellaRecensione.setBackground(Color.WHITE);
		tabellaRecensione.setFont(new Font("Verdana", Font.PLAIN, 13));
		tabellaRecensione.setRowHeight(30);
		tabellaRecensione.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		tabellaRecensione.getTableHeader().setBackground(new Color(0, 52, 104));
		tabellaRecensione.getTableHeader().setForeground(Color.WHITE);
		tabellaRecensione.getTableHeader().setReorderingAllowed(false);
		tabellaRecensione.setSelectionBackground(new Color(70, 152, 208));
		tabellaRecensione.setSelectionForeground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(tabellaRecensione);
		scrollPane.setBorder(null);
		mainContainer.add(scrollPane, BorderLayout.CENTER);
	}

	private void GetRecensioniInviate() {
		String matricola = UtenteLoggato.getMatricola();
		try {
			// Pulisci la tabella PRIMA di caricare i nuovi dati
			modelTabella.setRowCount(0);
			
			// Crea un'istanza di ListaRecensioniDao
			ListaRecensioniDao selectRecensioni = new ListaRecensioniDao();
			
			// Chiama il metodo sull'istanza
			ArrayList<Recensione_entity> Recensioni = selectRecensioni.VisualizzaRecensioniInviate(matricola);

			// Aggiungi ogni recensione come riga nella tabella
			for (Recensione_entity R : Recensioni) {
				modelTabella.addRow(new Object[]{
					R.getCommento(),
					R.getPunteggio(),
					R.getData(),
					R.getMatricolaAcquirente(),
					R.getMatricolaVenditore()
				});
			}

			if (Recensioni.size() == 0) {
				JOptionPane.showMessageDialog(this,
					"Non hai ancora inviato recensioni",
					"Nessuna recensione",
					JOptionPane.INFORMATION_MESSAGE);
			}

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
		String matricola = UtenteLoggato.getMatricola();
		try {
			// Pulisci la tabella PRIMA di caricare i nuovi dati
			modelTabella.setRowCount(0);
			
			// Crea un'istanza di ListaRecensioniDao
			ListaRecensioniDao selectRecensioni = new ListaRecensioniDao();
			
			// Chiama il metodo sull'istanza
			ArrayList<Recensione_entity> Recensioni = selectRecensioni.VisualizzaRecensioniRicevute(matricola);

			// Aggiungi ogni recensione come riga nella tabella
			for (Recensione_entity R : Recensioni) {
				modelTabella.addRow(new Object[]{
					R.getCommento(),
					R.getPunteggio(),
					R.getData(),
					R.getMatricolaAcquirente(),
					R.getMatricolaVenditore()
				});
			}

			if (Recensioni.size() == 0) {
				JOptionPane.showMessageDialog(this,
					"Non hai ancora ricevuto recensioni",
					"Nessuna recensione",
					JOptionPane.INFORMATION_MESSAGE);
			}

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



