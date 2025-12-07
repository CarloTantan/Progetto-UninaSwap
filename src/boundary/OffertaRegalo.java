package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dao.OffertaDAO;
import entity.Annuncio_entity;
import entity.OffertaRegalo_entity;
import entity.Utente_entity;
import mainController.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class OffertaRegalo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private int IdAnnuncioScelto;
	private OffertaDAO offertaDAO;
	private JTextArea textAreaMessaggioMotivazionale;
	private int IdOffertaDaModificare = -1;
	private boolean isModificaMode = false;
	private JButton btnConferma;
	private MainController controller;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OffertaRegalo frame = new OffertaRegalo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public OffertaRegalo(Utente_entity UtenteLoggato, int IdAnnuncioScelto, MainController controller) {
		this.IdAnnuncioScelto = IdAnnuncioScelto;
		this.UtenteLoggato = UtenteLoggato;
		this.controller = controller;
		offertaDAO = new OffertaDAO();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaRegalo.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Regalo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(850, 600));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		// ---------------- HEADER -----------------
		JPanel header = new JPanel();
		header.setBackground(new Color(45, 134, 192));
		header.setLayout(null);
		header.setPreferredSize(new Dimension(0, 94));
		contentPane.add(header, BorderLayout.NORTH);
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(UtenteLoggato, controller);
				ListaAnnunciFrame.setVisible(true);
			}
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(OffertaRegalo.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(15, 15, 50, 50);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		header.add(btnUndo);
		
		btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnUndo.setBackground(new Color(66, 152, 211));
				btnUndo.setContentAreaFilled(true);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnUndo.setBackground(new Color(45, 134, 192));
				btnUndo.setContentAreaFilled(false);
			}
		});
		
		JLabel lblOffertaRegalo = new JLabel("Offerta Regalo");
		lblOffertaRegalo.setFont(new Font("Verdana", Font.BOLD, 24));
		lblOffertaRegalo.setForeground(Color.WHITE);
		lblOffertaRegalo.setBounds(0, 0, 300, 94);
		lblOffertaRegalo.setHorizontalAlignment(JLabel.CENTER);
		header.add(lblOffertaRegalo);
		
		// Posizionamento centrato del titolo
		header.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				int headerWidth = header.getWidth();
				lblOffertaRegalo.setBounds((headerWidth - 300) / 2, 10, 300, 79);
			}
		});
		
		// ---------------- PANNELLO CENTRALE CON GRIDBAGLAYOUT -----------------
		JPanel panelCentrale = new JPanel();
		panelCentrale.setBackground(Color.WHITE);
		panelCentrale.setLayout(new GridBagLayout());
		contentPane.add(panelCentrale, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Label Motivazione
		JLabel lblNewLabel = new JLabel("Motivazione");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 5, 10);
		gbc.anchor = GridBagConstraints.WEST;
		panelCentrale.add(lblNewLabel, gbc);
		
		// TextArea Motivazione con ScrollPane
		textAreaMessaggioMotivazionale = new JTextArea();
		textAreaMessaggioMotivazionale.setFont(new Font("Verdana", Font.PLAIN, 16));
		textAreaMessaggioMotivazionale.setLineWrap(true);
		textAreaMessaggioMotivazionale.setWrapStyleWord(true);
		textAreaMessaggioMotivazionale.setRows(6);
		
		JScrollPane scrollPane = new JScrollPane(textAreaMessaggioMotivazionale);
		scrollPane.setPreferredSize(new Dimension(350, 150));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 10, 20, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		panelCentrale.add(scrollPane, gbc);
		
		// Bottone Conferma
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inviaOffertaRegalo();
			}
		});
		btnConferma.setForeground(Color.WHITE);
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 18));
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setPreferredSize(new Dimension(180, 45));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.weighty = 0;
		panelCentrale.add(btnConferma, gbc);
		getRootPane().setDefaultButton(btnConferma);
		
		btnConferma.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnConferma.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnConferma.setBackground(new Color(0, 52, 101));
			}
		});
	}
	
	public void inviaOffertaRegalo() {
		String MessaggioMotivazionale = textAreaMessaggioMotivazionale.getText().trim();
		
		if (MessaggioMotivazionale.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
					"Inserisci una motivazione.", 
					"Errore", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String MatricolaAcquirente = UtenteLoggato.getMatricola();
		
		try {
			boolean OffertaValida;
			
			if (isModificaMode) {
				OffertaValida = offertaDAO.aggiornaOffertaRegalo(
					MessaggioMotivazionale, 
					MatricolaAcquirente, 
					IdAnnuncioScelto, 
					IdOffertaDaModificare
				);
			} else {
				OffertaValida = offertaDAO.inserimentoOffertaRegalo(
					MessaggioMotivazionale, 
					MatricolaAcquirente, 
					IdAnnuncioScelto
				);
			}
			
			if (OffertaValida) {
				String messaggio = isModificaMode ? "Offerta aggiornata" : "Offerta inviata";
				JOptionPane.showMessageDialog(null, messaggio, null, JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(UtenteLoggato, controller);
				ListaAnnunciFrame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, 
						"Problema nell'operazione", 
						"Errore", 
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, 
					"Errore nell'operazione: " + e.getMessage(), 
					"Errore", 
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void caricaOffertaPerModifica(int idOfferta) {
		try {
			OffertaRegalo_entity offerta = offertaDAO.caricaOfferta(idOfferta);
			
			if (offerta != null) {
				textAreaMessaggioMotivazionale.setText(offerta.getMessaggioMotivazionale());
				IdOffertaDaModificare = idOfferta;
				isModificaMode = true;
				btnConferma.setText("Aggiorna");
			} else {
				JOptionPane.showMessageDialog(this, "Offerta non trovata", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Errore nel caricamento: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
}