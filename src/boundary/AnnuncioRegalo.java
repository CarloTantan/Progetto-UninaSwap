package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.FotoAnnuncioDAO;
import dao.InserimentoAnnunciDAO;
import entity.Oggetto_entity;
import entity.Utente_entity;
import enumerations.FasciaOraria;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class AnnuncioRegalo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private Oggetto_entity OggettoAnnuncio;
	private String titolo;
	private String descrizione;
	private String modalitaConsegna;
	private FasciaOraria fasciaOraria;
	private ArrayList<String> percorsiImmagini;	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AnnuncioRegalo frame = new AnnuncioRegalo();
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
	public AnnuncioRegalo(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio, String titolo, String descrizione, String modalitaConsegna,
            FasciaOraria fasciaOraria, ArrayList<String> percorsiImmagini) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.modalitaConsegna = modalitaConsegna;
		this.fasciaOraria = fasciaOraria;
		this.percorsiImmagini = percorsiImmagini;		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnuncioRegalo.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Regalo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 789, 55);
		panel.setBackground(new Color(45, 134, 192));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Annuncio di regalo");
		lblNewLabel.setBounds(275, 10, 238, 35);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setBounds(0, 0, 42, 55);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setIcon(new ImageIcon("/icons/icons8-annulla-3d-fluency-32.png"));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(UtenteLoggato,OggettoAnnuncio); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextArea textAreaMotivo = new JTextArea();
		textAreaMotivo.setFont(new Font("Verdana", Font.BOLD, 16));
		textAreaMotivo.setBackground(new Color(0, 52, 102));
		textAreaMotivo.setForeground(new Color(255, 255, 255));
		textAreaMotivo.setLineWrap(true);
		textAreaMotivo.setWrapStyleWord(true);		
		textAreaMotivo.setBounds(310, 110, 183, 39);
		contentPane.add(textAreaMotivo);
		
		JLabel lblNewLabel_1 = new JLabel("Motivo di cessione");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setBounds(111, 114, 200, 35);
		contentPane.add(lblNewLabel_1);
		
		JButton ButtonPubblica = new JButton("Pubblica");
		ButtonPubblica.setBackground(new Color(0, 52, 102));
		ButtonPubblica.setForeground(new Color(255, 255, 255));
		ButtonPubblica.setFont(new Font("Verdana", Font.BOLD, 16)); 
		ButtonPubblica.setBounds(310, 221, 147, 48);
		contentPane.add(ButtonPubblica);
		ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		
		ButtonPubblica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textAreaMotivo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, 
						"Il campo motivo di cessione è obbligatorio", 
						"Campo mancante", 
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					String motivoCessione = textAreaMotivo.getText().trim();
					
					// Inserisci l'annuncio nel database
					InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					int idAnnuncio = annuncioDAO.inserisciAnnuncioRegalo(
						titolo, 
						descrizione, 
						modalitaConsegna, 
						fasciaOraria, 
						motivoCessione, 
						UtenteLoggato.getMatricola(), 
						OggettoAnnuncio.getIdOggetto()
					);
					
					// Inserisci le foto dell'annuncio
					FotoAnnuncioDAO fotoDAO = new FotoAnnuncioDAO();
					for (String percorsoImg : percorsiImmagini) {
						fotoDAO.inserisciFoto(percorsoImg, idAnnuncio);
					}
					
					setVisible(false);
					JOptionPane.showMessageDialog(null, 
						"Pubblicazione avvenuta con successo", 
						"Annuncio pubblicato", 
						JOptionPane.INFORMATION_MESSAGE);
					AreaUtente utenteFrame = new AreaUtente(UtenteLoggato); 
					utenteFrame.setVisible(true);
					utenteFrame.setLocationRelativeTo(null);
					
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, 
						"Errore durante la pubblicazione: " + ex.getMessage(), 
						"Errore Database", 
						JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
	}

}
