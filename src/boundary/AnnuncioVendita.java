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
import java.util.List;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Toolkit;

public class AnnuncioVendita extends JFrame {

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
//					AnnuncioVendita frame = new AnnuncioVendita();
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
	public AnnuncioVendita(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio, String titolo, String descrizione, String modalitaConsegna,
            FasciaOraria fasciaOraria, ArrayList<String> percorsiImmagini) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.modalitaConsegna = modalitaConsegna;
		this.fasciaOraria = fasciaOraria;
		this.percorsiImmagini = percorsiImmagini;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnuncioVendita.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Vendita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 672, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Annuncio di vendita");
		lblNewLabel.setBounds(227, 10, 230, 35);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setIcon(new ImageIcon("C:\\Users\\sabri\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		ButtonAnnulla.setBounds(0, 0, 51, 55);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);

		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(UtenteLoggato, OggettoAnnuncio); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextField textFieldPrezzo = new JTextField();
		textFieldPrezzo.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldPrezzo.setForeground(new Color(255, 255, 255));
		textFieldPrezzo.setBackground(new Color(0, 52, 102));
		textFieldPrezzo.setBounds(321, 88, 112, 22);
		contentPane.add(textFieldPrezzo);
		
		JLabel lblNewLabel_1 = new JLabel("Prezzo (€)");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setBounds(219, 89, 92, 19);
		contentPane.add(lblNewLabel_1);
		
		JButton ButtonPubblica = new JButton("Pubblica");
		ButtonPubblica.setForeground(new Color(255, 255, 255));
		ButtonPubblica.setBackground(new Color(0, 52, 102));
		ButtonPubblica.setFont(new Font("Verdana", Font.BOLD, 16));
		ButtonPubblica.setBounds(266, 163, 123, 37);
		contentPane.add(ButtonPubblica);
        ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		
		ButtonPubblica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldPrezzo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, 
						"Il campo prezzo è obbligatorio", 
						"Campo mancante", 
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					float prezzo = Float.parseFloat(textFieldPrezzo.getText().trim());
					
					if (prezzo <= 0) {
						JOptionPane.showMessageDialog(null, 
							"Il prezzo deve essere maggiore di zero", 
							"Prezzo non valido", 
							JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Inserisci l'annuncio nel database
					InserimentoAnnunciDAO annuncioDAO = new InserimentoAnnunciDAO();
					int idAnnuncio = annuncioDAO.inserisciAnnuncioVendita(
						titolo, 
						descrizione, 
						modalitaConsegna, 
						fasciaOraria, 
						prezzo, 
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
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, 
						"Inserisci un prezzo valido", 
						"Formato non valido", 
						JOptionPane.ERROR_MESSAGE);
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
