package boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.OffertaDAO;
import entity.Annuncio_entity;
import entity.Utente_entity;

import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JTextField;

public class OffertaScambio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private int IdAnnuncioScelto;
	private OffertaDAO offertaDAO;
	private JTextField textFieldOggettoProposto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OffertaScambio frame = new OffertaScambio();
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
	public OffertaScambio(Utente_entity UtenteLoggato, int IdAnnuncioScelto) {
		this.UtenteLoggato = UtenteLoggato;
		this.IdAnnuncioScelto = IdAnnuncioScelto;
		offertaDAO = new OffertaDAO();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaScambio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Scambio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 768, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblOffertaScambio = new JLabel("Offerta Scambio");
		lblOffertaScambio.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOffertaScambio.setBounds(292, 22, 215, 42);
		panel.add(lblOffertaScambio);
		
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
		btnUndo.setIcon(new ImageIcon(OffertaScambio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 59, 54);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inviaOffertaScambio();
			}
		});
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 16));
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setForeground(new Color(255, 255, 255));
		btnConferma.setBounds(295, 346, 147, 54);
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		contentPane.add(btnConferma);
		
		JLabel lblNewLabel = new JLabel("Oggetto proposto");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(93, 131, 169, 34);
		contentPane.add(lblNewLabel);
		
		textFieldOggettoProposto = new JTextField();
		textFieldOggettoProposto.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldOggettoProposto.setColumns(10);
		textFieldOggettoProposto.setBounds(276, 131, 166, 36);
		contentPane.add(textFieldOggettoProposto);
		
	
	}
	
	public void inviaOffertaScambio() {
		
		String OggettoProposto = textFieldOggettoProposto.getText().trim();
		
		if (OggettoProposto.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
	                "Inserisci un oggetto che vuoi proporre.", 
	                "Errore", 
	                JOptionPane.ERROR_MESSAGE);
	            return;
		}
		
		String MatricolaAcquirente = UtenteLoggato.getMatricola();
		
		try {
			
			boolean OffertaValida = offertaDAO.inserimentoOffertaScambio(OggettoProposto, MatricolaAcquirente, IdAnnuncioScelto);
			
			if (OffertaValida) {
				JOptionPane.showMessageDialog(null, "Offerta inviata ", null, JOptionPane.INFORMATION_MESSAGE);
				 setVisible(false);
				 ListaAnnunci ListaAnnunciFrame = new ListaAnnunci(UtenteLoggato);
				ListaAnnunciFrame.setVisible(true);
				ListaAnnunciFrame.setLocationRelativeTo(null);
			} else {
	            JOptionPane.showMessageDialog(this, 
	                    "Problema nell'invio dell'offerta", 
	                    "Errore invio offerta", 
	                    JOptionPane.ERROR_MESSAGE);
			
			}
			        
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, 
					"Errore nell'inserimento dell'offerta: " 
					+ e.getMessage(), "Errore", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}
