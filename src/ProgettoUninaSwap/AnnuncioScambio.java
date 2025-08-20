package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AnnuncioScambio extends Annuncio {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnuncioScambio frame = new AnnuncioScambio();
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
	public AnnuncioScambio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 609, 64);
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
		lblNewLabel.setBounds(217, 23, 172, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextArea textAreaOggettoRichiesto = new JTextArea();
		textAreaOggettoRichiesto.setForeground(new Color(255, 255, 255));
		textAreaOggettoRichiesto.setBackground(new Color(0, 52, 102));
		textAreaOggettoRichiesto.setBackground(new Color(0, 52, 102));
		textAreaOggettoRichiesto.setBounds(324, 93, 168, 22);
		contentPane.add(textAreaOggettoRichiesto);
		
		JLabel lblNewLabel_2 = new JLabel("Oggetto richiesto ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(100, 92, 162, 22);
		contentPane.add(lblNewLabel_2);
		
		JButton ButtonPubblica = new JButton("Pubblica ");
		ButtonPubblica.setBackground(new Color(0, 52, 102));
		ButtonPubblica.setForeground(new Color(255, 255, 255));
		ButtonPubblica.setFont(new Font("Tahoma", Font.BOLD, 16));
		ButtonPubblica.setBounds(221, 163, 152, 40);
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

				}
			}
		});
		
	}
}
