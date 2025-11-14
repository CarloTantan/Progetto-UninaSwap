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

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class AnnuncioRegalo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnuncioRegalo frame = new AnnuncioRegalo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnnuncioRegalo() {
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
		ButtonAnnulla.setIcon(new ImageIcon("C:\\Users\\sabri\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextArea textAreaMotivo = new JTextArea();
		textAreaMotivo.setFont(new Font("Verdana", Font.BOLD, 16));
		textAreaMotivo.setBackground(new Color(0, 52, 102));
		textAreaMotivo.setForeground(new Color(255, 255, 255));
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
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Pubblicazione avvenuta con successo", "Annuncio pubblicato", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false); 
					AreaUtente utenteFrame = new AreaUtente(); 
					utenteFrame.setVisible(true);
				}
			}
		});
	}

}
