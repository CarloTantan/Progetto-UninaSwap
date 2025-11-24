package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Oggetto_entity;
import entity.Utente_entity;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Toolkit;

public class AnnuncioScambio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private Oggetto_entity OggettoAnnuncio;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AnnuncioScambio frame = new AnnuncioScambio();
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
	public AnnuncioScambio(Utente_entity UtenteLoggato, Oggetto_entity OggettoAnnuncio) {
		this.OggettoAnnuncio = OggettoAnnuncio;
		this.UtenteLoggato = UtenteLoggato;
		setTitle("Scambio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnuncioScambio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 792, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setIcon(new ImageIcon("C:\\Users\\sabri\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		ButtonAnnulla.setBounds(0, 0, 43, 64);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		
		JLabel lblNewLabel = new JLabel("Annuncio di scambio ");
		lblNewLabel.setBounds(268, 23, 255, 31);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(UtenteLoggato, OggettoAnnuncio); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextArea textAreaOggettoRichiesto = new JTextArea();
		textAreaOggettoRichiesto.setFont(new Font("Verdana", Font.BOLD, 16));
		textAreaOggettoRichiesto.setForeground(new Color(255, 255, 255));
		textAreaOggettoRichiesto.setBackground(Color.WHITE);
		textAreaOggettoRichiesto.setBackground(new Color(0, 52, 102));
		textAreaOggettoRichiesto.setBounds(293, 127, 199, 40);
		contentPane.add(textAreaOggettoRichiesto);
		
		JLabel lblNewLabel_2 = new JLabel("Oggetto richiesto ");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_2.setBounds(93, 128, 162, 22);
		contentPane.add(lblNewLabel_2);
		
		JButton ButtonPubblica = new JButton("Pubblica ");
		ButtonPubblica.setBackground(new Color(0, 52, 102));
		ButtonPubblica.setForeground(new Color(255, 255, 255));
		ButtonPubblica.setFont(new Font("Verdana", Font.BOLD, 16));
		ButtonPubblica.setBounds(293, 256, 167, 53);
		contentPane.add(ButtonPubblica);
		ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		
		ButtonPubblica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textAreaOggettoRichiesto.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Pubblicazione avvenuta con successo", "Annuncio pubblicato", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false); 
					AreaUtente utenteFrame = new AreaUtente(UtenteLoggato); 
					utenteFrame.setVisible(true);
				}
			}
		});
		
	}
}
