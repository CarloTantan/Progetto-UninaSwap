package ProgettoUninaSwap;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

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
		setBounds(100, 100, 510, 638);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textfieldTitolo = new JTextArea();
		textfieldTitolo.setForeground(new Color(255, 255, 255));
		textfieldTitolo.setBackground(new Color(0, 52, 102));
		textfieldTitolo.setBounds(251, 198, 179, 22);
		contentPane.add(textfieldTitolo);
		
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setForeground(new Color(255, 255, 255));
		textAreaDescrizione.setBackground(new Color(0, 52, 102));
		textAreaDescrizione.setBounds(251, 260, 179, 56);
		contentPane.add(textAreaDescrizione);
		
		JTextArea textAreaFasciaOraria = new JTextArea();
		textAreaFasciaOraria.setForeground(new Color(255, 255, 255));
		textAreaFasciaOraria.setBackground(new Color(0, 52, 102));
		textAreaFasciaOraria.setBounds(251, 346, 179, 22);
		contentPane.add(textAreaFasciaOraria);
		
		JTextArea textAreaModConsegna = new JTextArea();
		textAreaModConsegna.setForeground(new Color(255, 255, 255));
		textAreaModConsegna.setBackground(new Color(0, 52, 102));
		textAreaModConsegna.setBounds(251, 399, 179, 22);
		contentPane.add(textAreaModConsegna);
		
		JTextArea textAreaCategoria = new JTextArea();
		textAreaCategoria.setForeground(new Color(255, 255, 255));
		textAreaCategoria.setBackground(new Color(0, 52, 102));
		textAreaCategoria.setBounds(251, 449, 179, 22);
		contentPane.add(textAreaCategoria);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(36, 194, 85, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(36, 266, 170, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fascia Oraria");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(36, 350, 135, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Modalità di consegna");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(36, 398, 179, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria oggetto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(36, 450, 236, 19);
		contentPane.add(lblNewLabel_5);
		
		JButton JScambio = new JButton("Scambio");
		JScambio.setForeground(new Color(255, 255, 255));
		JScambio.setToolTipText("");
		JScambio.setBackground(new Color(0, 52, 102));
		JScambio.setFont(new Font("Tahoma", Font.BOLD, 16));
		JScambio.setBounds(36, 514, 122, 56);
		contentPane.add(JScambio);
		JScambio.setFocusPainted(false);
		JScambio.setBorderPainted(false);
	
		
		JButton JRegalo = new JButton("Regalo");
		JRegalo.setForeground(new Color(255, 255, 255));
		JRegalo.setBackground(new Color(0, 52, 102));
		JRegalo.setFont(new Font("Tahoma", Font.BOLD, 16));
		JRegalo.setBounds(187, 514, 122, 56);
		contentPane.add(JRegalo);
		JRegalo.setFocusPainted(false);
		JRegalo.setBorderPainted(false);
		
		JButton JVendita = new JButton("Vendita");
		JVendita.setForeground(new Color(255, 255, 255));
		JVendita.setBackground(new Color(0, 52, 102));
		JVendita.setFont(new Font("Tahoma", Font.BOLD, 16));
		JVendita.setBounds(324, 514, 122, 56);
		contentPane.add(JVendita);
		JVendita.setFocusPainted(false);
		JVendita.setBorderPainted(false);
		
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
		lblNewLabel_6.setBounds(196, 0, 81, 58);
		panel.add(lblNewLabel_6);
		
		JLabel LabelImg = new JLabel("");
		LabelImg.setBackground(new Color(0, 52, 102));
		LabelImg.setBounds(251, 77, 177, 77);
		contentPane.add(LabelImg);
		ButtonAnnulla.setBorderPainted(false);
		
		JButton ButtonImgOggetto = new JButton("Oggetto");
		ButtonImgOggetto.setBackground(new Color(255, 255, 255));
		ButtonImgOggetto.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonImgOggetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ButtonImgOggetto.setFont(new Font("Tahoma", Font.BOLD, 16));
		ButtonImgOggetto.setBounds(23, 77, 189, 36);
		contentPane.add(ButtonImgOggetto);
		ButtonImgOggetto.setFocusPainted(false);
		ButtonImgOggetto.setBorderPainted(false);
		
		ButtonImgOggetto.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				JFileChooser scegliImg = new JFileChooser();
		        scegliImg.setDialogTitle("Seleziona immagine");
		        scegliImg.setFileFilter(new FileNameExtensionFilter("Immagini PNG", "png"));

		        if (scegliImg.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		            ImageIcon iconaImg = new ImageIcon(scegliImg.getSelectedFile().getAbsolutePath());
		            Image img = iconaImg.getImage();
		            LabelImg.setIcon(new ImageIcon(img));
		        }
			}
		});
		
		
		
		
		JScambio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaFasciaOraria.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						LabelImg.getIcon()==null ||
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
						LabelImg.getIcon()==null ||						
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
						LabelImg.getIcon()==null ||
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
