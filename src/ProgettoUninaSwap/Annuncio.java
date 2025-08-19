package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Button;

public class Annuncio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Annuncio frame = new Annuncio();
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
	public Annuncio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textfieldTitolo = new JTextArea();
		textfieldTitolo.setForeground(new Color(255, 255, 255));
		textfieldTitolo.setBackground(new Color(0, 52, 102));
		textfieldTitolo.setBounds(251, 87, 179, 22);
		contentPane.add(textfieldTitolo);
		
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setForeground(new Color(255, 255, 255));
		textAreaDescrizione.setBackground(new Color(0, 52, 102));
		textAreaDescrizione.setBounds(251, 119, 179, 56);
		contentPane.add(textAreaDescrizione);
		
		JTextArea textAreaFasciaOraria = new JTextArea();
		textAreaFasciaOraria.setForeground(new Color(255, 255, 255));
		textAreaFasciaOraria.setBackground(new Color(0, 52, 102));
		textAreaFasciaOraria.setBounds(251, 196, 179, 22);
		contentPane.add(textAreaFasciaOraria);
		
		JTextArea textAreaModConsegna = new JTextArea();
		textAreaModConsegna.setForeground(new Color(255, 255, 255));
		textAreaModConsegna.setBackground(new Color(0, 52, 102));
		textAreaModConsegna.setBounds(251, 251, 179, 22);
		contentPane.add(textAreaModConsegna);
		
		JTextArea textAreaOggetto = new JTextArea();
		textAreaOggetto.setForeground(new Color(255, 255, 255));
		textAreaOggetto.setBackground(new Color(0, 52, 102));
		textAreaOggetto.setBounds(251, 306, 179, 22);
		contentPane.add(textAreaOggetto);
		
		JTextArea textAreaCategoria = new JTextArea();
		textAreaCategoria.setForeground(new Color(255, 255, 255));
		textAreaCategoria.setBackground(new Color(0, 52, 102));
		textAreaCategoria.setBounds(251, 355, 179, 22);
		contentPane.add(textAreaCategoria);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(73, 83, 85, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(73, 139, 170, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fascia Oraria");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(73, 202, 135, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Modalità di consegna");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(73, 255, 179, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Oggetto");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(73, 310, 147, 22);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria oggetto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(73, 355, 236, 19);
		contentPane.add(lblNewLabel_5);
		
		JButton JScambio = new JButton("Scambio");
		JScambio.setForeground(new Color(255, 255, 255));
		JScambio.setToolTipText("");
		JScambio.setBackground(new Color(0, 52, 102));
		JScambio.setFont(new Font("Tahoma", Font.BOLD, 16));
		JScambio.setBounds(36, 426, 122, 56);
		contentPane.add(JScambio);
	
		
		JButton JRegalo = new JButton("Regalo");
		JRegalo.setForeground(new Color(255, 255, 255));
		JRegalo.setBackground(new Color(0, 52, 102));
		JRegalo.setFont(new Font("Tahoma", Font.BOLD, 16));
		JRegalo.setBounds(176, 426, 122, 56);
		contentPane.add(JRegalo);
		
		JButton JVendita = new JButton("Vendita");
		JVendita.setForeground(new Color(255, 255, 255));
		JVendita.setBackground(new Color(0, 52, 102));
		JVendita.setFont(new Font("Tahoma", Font.BOLD, 16));
		JVendita.setBounds(322, 426, 122, 56);
		contentPane.add(JVendita);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 504, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBounds(0, 0, 46, 58);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setIcon(new ImageIcon("C:\\Users\\sabri\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				AreaUtente utenteFrame = new AreaUtente(); 
				utenteFrame.setVisible(true);
			}
		}); 
		
		JLabel lblNewLabel_6 = new JLabel("Annuncio");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(196, 10, 83, 35);
		panel.add(lblNewLabel_6);
		
		JScambio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						textAreaOggetto.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaCategoria.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					AnnuncioScambio annuncioScambioFrame = new AnnuncioScambio();
					annuncioScambioFrame.setVisible(true);
					annuncioScambioFrame.setLocationRelativeTo(null);
				}
			}
		});
		
		JRegalo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						textAreaOggetto.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaCategoria.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					AnnuncioRegalo annuncioRegaloFrame = new AnnuncioRegalo();
					annuncioRegaloFrame.setVisible(true);
					annuncioRegaloFrame.setLocationRelativeTo(null);
				}
				
			}
		});
		
		JVendita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						textAreaOggetto.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaCategoria.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					AnnuncioVendita annuncioVenditaFrame = new AnnuncioVendita();
					annuncioVenditaFrame.setVisible(true);
					annuncioVenditaFrame.setLocationRelativeTo(null);
				}
			}
		});
	}
}
