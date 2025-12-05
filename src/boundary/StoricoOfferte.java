package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.RecensioneVenditoreDAO;
import dao.StoricoOfferteDAO;
import dao.TransazioniDAO;
import entity.Offerta_entity;
import entity.Transazione_entity;
import entity.Utente_entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.FlowLayout;

public class StoricoOfferte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private JPanel panelOfferte;
	private JComboBox<String> comboBoxStato;
	private JComboBox<String> comboBoxTipologia;
	private ArrayList<Offerta_entity> offerteCaricate;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StoricoOfferte frame = new StoricoOfferte();
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
	public StoricoOfferte(Utente_entity UtenteLoggato) {
		this.UtenteLoggato = UtenteLoggato;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				StoricoOfferte.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Le tue offerte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1000, 700));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 247, 250));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		// ---------------- HEADER -----------------
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(new Color(45, 134, 192));
		header.setBorder(new EmptyBorder(10, 20, 10, 20));
		header.setPreferredSize(new Dimension(0, 80));
		
		JLabel lblStoricoOfferte = new JLabel("Le Tue Offerte", SwingConstants.CENTER);
		lblStoricoOfferte.setFont(new Font("Verdana", Font.BOLD, 24));
		lblStoricoOfferte.setForeground(Color.WHITE);
		
		JButton btnUndo = new JButton(new ImageIcon(
				StoricoOfferte.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
		btnUndo.addActionListener(e -> {
			setVisible(false);
			AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
			AreaUtenteFrame.setVisible(true);
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setBorderPainted(false);
		btnUndo.setFocusPainted(false);
		
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
		
		header.add(btnUndo, BorderLayout.WEST);
		header.add(lblStoricoOfferte, BorderLayout.CENTER);
		contentPane.add(header, BorderLayout.NORTH);
		
		// ---------------- PANNELLO FILTRI -----------------
		JPanel filtriPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
		filtriPanel.setBackground(Color.WHITE);
		filtriPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		JLabel lblStato = new JLabel("Stato:");
		lblStato.setFont(new Font("Verdana", Font.BOLD, 14));
		
		String[] stati = {"Tutte", "In Attesa", "Accettata", "Rifiutata"};
		comboBoxStato = new JComboBox<>(stati);
		comboBoxStato.setFont(new Font("Verdana", Font.PLAIN, 13));
		comboBoxStato.setPreferredSize(new Dimension(150, 35));
		comboBoxStato.setBackground(Color.WHITE);
		
		JLabel lblTipologia = new JLabel("Tipologia:");
		lblTipologia.setFont(new Font("Verdana", Font.BOLD, 14));
		
		String[] tipologie = {"Tutte", "Vendita", "Scambio", "Regalo"};
		comboBoxTipologia = new JComboBox<>(tipologie);
		comboBoxTipologia.setFont(new Font("Verdana", Font.PLAIN, 13));
		comboBoxTipologia.setPreferredSize(new Dimension(150, 35));
		comboBoxTipologia.setBackground(Color.WHITE);
		
		JButton btnFiltra = new JButton("Filtra");
		btnFiltra.setFont(new Font("Verdana", Font.BOLD, 14));
		btnFiltra.setBackground(new Color(0, 52, 101));
		btnFiltra.setForeground(Color.WHITE);
		btnFiltra.setPreferredSize(new Dimension(120, 35));
		btnFiltra.setFocusPainted(false);
		btnFiltra.setBorderPainted(false);
		btnFiltra.addActionListener(e -> applicaFiltri());
		
		btnFiltra.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnFiltra.setBackground(new Color(0, 70, 140));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnFiltra.setBackground(new Color(0, 52, 101));
			}
		});
		
		filtriPanel.add(lblStato);
		filtriPanel.add(comboBoxStato);
		filtriPanel.add(Box.createHorizontalStrut(10));
		filtriPanel.add(lblTipologia);
		filtriPanel.add(comboBoxTipologia);
		filtriPanel.add(Box.createHorizontalStrut(10));
		filtriPanel.add(btnFiltra);
		
		// ---------------- PANNELLO OFFERTE (CARDS) -----------------
		panelOfferte = new JPanel();
		panelOfferte.setLayout(new BoxLayout(panelOfferte, BoxLayout.Y_AXIS));
		panelOfferte.setBackground(Color.WHITE);
		panelOfferte.setBorder(new EmptyBorder(20, 30, 20, 30));
		
		JScrollPane scrollPane = new JScrollPane(panelOfferte);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setBorder(null);
		
		// Panel contenitore
		JPanel centerContainer = new JPanel(new BorderLayout());
		centerContainer.add(filtriPanel, BorderLayout.NORTH);
		centerContainer.add(scrollPane, BorderLayout.CENTER);
		
		contentPane.add(centerContainer, BorderLayout.CENTER);
		
		caricaOfferte();
	}
	
	private void caricaOfferte() {
		try {
			StoricoOfferteDAO dao = new StoricoOfferteDAO();
			offerteCaricate = dao.getOfferte(UtenteLoggato.getMatricola());
			mostraOfferte(offerteCaricate);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Errore nel caricamento delle offerte: " + e.getMessage(),
				"Errore",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void applicaFiltri() {
		if (offerteCaricate == null) return;
		
		String statoSelezionato = (String) comboBoxStato.getSelectedItem();
		String tipologiaSelezionata = (String) comboBoxTipologia.getSelectedItem();
		
		ArrayList<Offerta_entity> offerteFiltrate = new ArrayList<>();
		
		for (Offerta_entity offerta : offerteCaricate) {
			boolean matchStato = statoSelezionato.equals("Tutte") || 
								 offerta.getStatoOfferta().equalsIgnoreCase(statoSelezionato);
			boolean matchTipologia = tipologiaSelezionata.equals("Tutte") || 
									 offerta.getTipologiaOfferta().equalsIgnoreCase(tipologiaSelezionata);
			
			if (matchStato && matchTipologia) {
				offerteFiltrate.add(offerta);
			}
		}
		
		mostraOfferte(offerteFiltrate);
	}
	
	private void mostraOfferte(ArrayList<Offerta_entity> offerte) {
		panelOfferte.removeAll();
		
		if (offerte.isEmpty()) {
			JLabel lblNoOfferte = new JLabel("Nessuna offerta trovata");
			lblNoOfferte.setFont(new Font("Verdana", Font.ITALIC, 16));
			lblNoOfferte.setForeground(Color.GRAY);
			lblNoOfferte.setAlignmentX(Component.CENTER_ALIGNMENT);
			panelOfferte.add(Box.createVerticalStrut(100));
			panelOfferte.add(lblNoOfferte);
		} else {
			for (Offerta_entity offerta : offerte) {
				try {
					panelOfferte.add(creaCardOfferta(offerta));
					panelOfferte.add(Box.createVerticalStrut(15));
				} catch (SQLException e) {
					System.err.println("Errore creazione card: " + e.getMessage());
				}
			}
		}
		
		panelOfferte.revalidate();
		panelOfferte.repaint();
	}
	
	private JPanel creaCardOfferta(Offerta_entity offerta) throws SQLException {
		JPanel card = new JPanel(new BorderLayout(15, 15));
		card.setBackground(Color.WHITE);
		card.setBorder(new CompoundBorder(
			new LineBorder(new Color(200, 200, 200), 1, true),
			new EmptyBorder(20, 20, 20, 20)
		));
		card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		
		// ---------------- PANNELLO SINISTRO (Info Offerta) -----------------
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.WHITE);
		
		// Tipologia con badge
		JPanel tipologiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		tipologiaPanel.setBackground(Color.WHITE);
		JLabel lblTipologia = new JLabel(offerta.getTipologiaOfferta());
		lblTipologia.setFont(new Font("Verdana", Font.BOLD, 12));
		lblTipologia.setForeground(Color.WHITE);
		lblTipologia.setOpaque(true);
		lblTipologia.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		Color coloreTipologia = switch(offerta.getTipologiaOfferta().toLowerCase()) {
			case "vendita" -> new Color(56, 209, 97);
			case "scambio" -> new Color(108, 67, 232);
			case "regalo" -> new Color(209, 56, 56);
			default -> new Color(108, 117, 125);
		};
		lblTipologia.setBackground(coloreTipologia);
		tipologiaPanel.add(lblTipologia);
		
		leftPanel.add(tipologiaPanel);
		leftPanel.add(Box.createVerticalStrut(10));
		
		// Dettagli dell'offerta
		StoricoOfferteDAO dao = new StoricoOfferteDAO();
		String dettaglioOfferta = dao.getDettaglioOfferta(offerta.getIdOfferta());
		
		if (dettaglioOfferta != null && !dettaglioOfferta.isEmpty()) {
			String labelDettaglio = switch(offerta.getTipologiaOfferta().toLowerCase()) {
				case "vendita" -> "Prezzo proposto:";
				case "scambio" -> "Oggetto proposto:";
				case "regalo" -> "Messaggio motivazionale:";
				default -> "Dettaglio:";
			};
			
			JLabel lblDettaglio = new JLabel("<html><b>" + labelDettaglio + "</b> " + dettaglioOfferta + "</html>");
			lblDettaglio.setFont(new Font("Verdana", Font.PLAIN, 13));
			lblDettaglio.setAlignmentX(Component.LEFT_ALIGNMENT);
			leftPanel.add(lblDettaglio);
			leftPanel.add(Box.createVerticalStrut(8));
		}
		
		// Info annuncio e venditore
		String titoloAnnuncio = dao.getTitoloAnnuncio(offerta.getIdOfferta());
		if (titoloAnnuncio != null) {
			JLabel lblAnnuncio = new JLabel("<html><b>Annuncio:</b> " + titoloAnnuncio + "</html>");
			lblAnnuncio.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblAnnuncio.setForeground(new Color(80, 80, 80));
			lblAnnuncio.setAlignmentX(Component.LEFT_ALIGNMENT);
			leftPanel.add(lblAnnuncio);
			leftPanel.add(Box.createVerticalStrut(5));
		}
		
		// Info venditore con rating
		JPanel venditorePanel = creaInfoVenditore(offerta.getMatricolaAcquirente());
		venditorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftPanel.add(venditorePanel);
		
		// ---------------- PANNELLO DESTRO (Stato e Azioni) -----------------
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		
		// Badge stato
		JLabel lblStato = new JLabel(offerta.getStatoOfferta());
		lblStato.setFont(new Font("Verdana", Font.BOLD, 13));
		lblStato.setForeground(Color.WHITE);
		lblStato.setOpaque(true);
		lblStato.setBorder(new EmptyBorder(8, 15, 8, 15));
		lblStato.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Color coloreStato = switch(offerta.getStatoOfferta().toLowerCase()) {
			case "in attesa" -> new Color(255, 193, 7);
			case "accettata" -> new Color(40, 167, 69);
			case "rifiutata" -> new Color(220, 53, 69);
			default -> new Color(108, 117, 125);
		};
		lblStato.setBackground(coloreStato);
		
		rightPanel.add(lblStato);
		rightPanel.add(Box.createVerticalStrut(20));
		
		// Bottoni azione
		boolean inAttesa = offerta.getStatoOfferta().equalsIgnoreCase("In Attesa");
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setFont(new Font("Verdana", Font.BOLD, 12));
		btnModifica.setBackground(new Color(0, 52, 101));
		btnModifica.setForeground(Color.WHITE);
		btnModifica.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnModifica.setMaximumSize(new Dimension(140, 35));
		btnModifica.setFocusPainted(false);
		btnModifica.setBorderPainted(false);
		btnModifica.setEnabled(inAttesa);
		btnModifica.addActionListener(e -> modificaOfferta(offerta));
		
		if (!inAttesa) {
			btnModifica.setBackground(Color.GRAY);
		} else {
			btnModifica.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					btnModifica.setBackground(new Color(0, 70, 140));
				}
				public void mouseExited(java.awt.event.MouseEvent evt) {
					btnModifica.setBackground(new Color(0, 52, 101));
				}
			});
		}
		
		JButton btnRitira = new JButton("Ritira");
		btnRitira.setFont(new Font("Verdana", Font.BOLD, 12));
		btnRitira.setBackground(new Color(220, 53, 69));
		btnRitira.setForeground(Color.WHITE);
		btnRitira.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRitira.setMaximumSize(new Dimension(140, 35));
		btnRitira.setFocusPainted(false);
		btnRitira.setBorderPainted(false);
		btnRitira.setEnabled(inAttesa);
		btnRitira.addActionListener(e -> ritiraOfferta(offerta));
		
		if (!inAttesa) {
			btnRitira.setBackground(Color.GRAY);
		} else {
			btnRitira.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					btnRitira.setBackground(new Color(200, 35, 51));
				}
				public void mouseExited(java.awt.event.MouseEvent evt) {
					btnRitira.setBackground(new Color(220, 53, 69));
				}
			});
		}
		
		rightPanel.add(btnModifica);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(btnRitira);
		
		card.add(leftPanel, BorderLayout.CENTER);
		card.add(rightPanel, BorderLayout.EAST);
		
		return card;
	}
	
	private JPanel creaInfoVenditore(String matricolaVenditore) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panel.setBackground(Color.WHITE);
		
		try {
			RecensioneVenditoreDAO dao = new RecensioneVenditoreDAO();
			String nominativo = dao.getNominativoUtente(matricolaVenditore);
			double media = dao.getValutazioneMedia(matricolaVenditore);
			int numRecensioni = dao.getNumeroRecensioni(matricolaVenditore);
			
			JLabel lblVenditore = new JLabel("<html><b>Venditore:</b> " + 
				(nominativo != null ? nominativo : "N/D") + "</html>");
			lblVenditore.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVenditore.setForeground(new Color(80, 80, 80));
			panel.add(lblVenditore);
			
			if (numRecensioni > 0) {
				ImageIcon iconaStella = new ImageIcon(
					StoricoOfferte.class.getResource("/icons/icons8-stella-32.png"));
				Image imgScalata = iconaStella.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH);
				JLabel lblStella = new JLabel(new ImageIcon(imgScalata));
				panel.add(lblStella);
				
				JLabel lblRating = new JLabel(String.format("%.1f", media));
				lblRating.setFont(new Font("Verdana", Font.PLAIN, 11));
				lblRating.setForeground(new Color(100, 100, 100));
				panel.add(lblRating);
			}
			
		} catch (Exception e) {
			JLabel lblErrore = new JLabel("Venditore: N/D");
			lblErrore.setFont(new Font("Verdana", Font.PLAIN, 12));
			panel.add(lblErrore);
		}
		
		return panel;
	}
	
	private void modificaOfferta(Offerta_entity offerta) {
		try {
			StoricoOfferteDAO dao = new StoricoOfferteDAO();
			int idAnnuncio = dao.getIdAnnuncioFromOfferta(offerta.getIdOfferta());
			
			if (idAnnuncio == -1) {
				JOptionPane.showMessageDialog(this, 
					"Annuncio non trovato", 
					"Errore", 
					JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String tipologia = offerta.getTipologiaOfferta();
			
			if (tipologia.equalsIgnoreCase("Regalo")) {
				OffertaRegalo frame = new OffertaRegalo(UtenteLoggato, idAnnuncio);
				frame.caricaOffertaPerModifica(offerta.getIdOfferta());
				frame.setVisible(true);
				this.setVisible(false);
			} else if (tipologia.equalsIgnoreCase("Scambio")) {
				OffertaScambio frame = new OffertaScambio(UtenteLoggato, idAnnuncio);
				frame.caricaOffertaPerModifica(offerta.getIdOfferta());
				frame.setVisible(true);
				this.setVisible(false);
			} else if (tipologia.equalsIgnoreCase("Vendita")) {
				OffertaVendita frame = new OffertaVendita(UtenteLoggato, idAnnuncio);
				frame.caricaOffertaPerModifica(offerta.getIdOfferta());
				frame.setVisible(true);
				this.setVisible(false);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, 
				"Errore: " + ex.getMessage(), 
				"Errore", 
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void ritiraOfferta(Offerta_entity offerta) {
		int scelta = JOptionPane.showConfirmDialog(this, 
			"Vuoi davvero ritirare questa offerta?\nQuesta azione non può essere annullata.", 
			"Conferma ritiro", 
			JOptionPane.YES_NO_OPTION, 
			JOptionPane.WARNING_MESSAGE);
		
		if (scelta == JOptionPane.YES_OPTION) {
			try {
				StoricoOfferteDAO dao = new StoricoOfferteDAO();
				boolean eliminato = dao.DeleteOfferte(offerta.getIdOfferta());
				
				if (eliminato) {
					JOptionPane.showMessageDialog(this,
						"Offerta ritirata con successo",
						"Successo",
						JOptionPane.INFORMATION_MESSAGE);
					caricaOfferte();
				}
			} catch (SQLException e) {
				String errorMessage = e.getMessage();
				if (errorMessage != null && errorMessage.contains("Impossibile ritirare un'offerta accettata")) {
					JOptionPane.showMessageDialog(this,
						"Non puoi ritirare un'offerta già accettata",
						"Operazione non consentita",
						JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
						"Errore nell'eliminazione: " + e.getMessage(),
						"Errore",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}