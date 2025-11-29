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

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
	public OffertaRegalo(Utente_entity UtenteLoggato, int IdAnnuncioScelto) {
		this.IdAnnuncioScelto = IdAnnuncioScelto;
		this.UtenteLoggato = UtenteLoggato;
		offertaDAO = new OffertaDAO();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaRegalo.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Regalo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(10, 0, 801, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				 ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(UtenteLoggato);
					ListaAnnunciFrame.setVisible(true);
					ListaAnnunciFrame.setLocationRelativeTo(null);
			}
		});
		
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(OffertaRegalo.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 46, 68);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false); 
		panel.add(btnUndo);
		
		JLabel lblOffertaRegalo = new JLabel("Offerta Regalo");
		lblOffertaRegalo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOffertaRegalo.setBounds(285, 39, 228, 34);
		panel.add(lblOffertaRegalo);
		
		textAreaMessaggioMotivazionale = new JTextArea();
		textAreaMessaggioMotivazionale.setFont(new Font("Verdana", Font.BOLD, 16));
		textAreaMessaggioMotivazionale.setLineWrap(true); 
		textAreaMessaggioMotivazionale.setWrapStyleWord(true); 
		
		JScrollPane scrollPane = new JScrollPane(textAreaMessaggioMotivazionale);
		scrollPane.setBounds(270, 173, 227, 105);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Motivazione");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(318, 123, 140, 28);
		contentPane.add(lblNewLabel);
		
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inviaOffertaRegalo();
			}
		});
		
		btnConferma.setForeground(Color.WHITE);
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 16));
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setBounds(311, 314, 147, 54);
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false); 
		contentPane.add(btnConferma);
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
				// UPDATE
				OffertaValida = offertaDAO.aggiornaOffertaRegalo(
					MessaggioMotivazionale, 
					MatricolaAcquirente, 
					IdAnnuncioScelto, 
					IdOffertaDaModificare
				);
				
			} else {
				// INSERT
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
				 ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(UtenteLoggato);
				ListaAnnunciFrame.setVisible(true);
				ListaAnnunciFrame.setLocationRelativeTo(null);
			} else {
	            JOptionPane.showMessageDialog(this, 
	                    "Problema nell'operazione", 
	                    "Errore", 
	                    JOptionPane.ERROR_MESSAGE);
			
			}
			        
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, 
					"Errore nell'operazione: " 
					+ e.getMessage(), "Errore", 
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
	}


	
	
public void caricaOffertaPerModifica(int idOfferta) {
    try {
        OffertaRegalo_entity offerta = offertaDAO.caricaOfferta(idOfferta);
        
        if (offerta != null) {
            // Riempie il campo con il messaggio salvato
            textAreaMessaggioMotivazionale.setText(offerta.getMessaggioMotivazionale());
            
            // Attiva modalità modifica
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
