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

public class AnnuncioVendita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnuncioVendita frame = new AnnuncioVendita();
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
	public AnnuncioVendita() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnnuncioVendita.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Vendita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
		setBounds(100, 100, 434, 241);
=======
		setBounds(100, 100, 686, 487);
>>>>>>> branch 'master' of https://github.com/CarloTantan/Progetto-UninaSwap.git
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
<<<<<<< HEAD
		panel.setBounds(0, 0, 420, 55);
=======
		panel.setBounds(0, 0, 672, 55);
>>>>>>> branch 'master' of https://github.com/CarloTantan/Progetto-UninaSwap.git
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Annuncio di vendita");
<<<<<<< HEAD
		lblNewLabel.setBounds(157, 10, 162, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
=======
		lblNewLabel.setBounds(227, 10, 230, 35);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
>>>>>>> branch 'master' of https://github.com/CarloTantan/Progetto-UninaSwap.git
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
				Annuncio annuncioFrame = new Annuncio(); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextArea textAreaPrezzo = new JTextArea();
		textAreaPrezzo.setFont(new Font("Dialog", Font.PLAIN, 16));
		textAreaPrezzo.setForeground(new Color(255, 255, 255));
<<<<<<< HEAD
		textAreaPrezzo.setBackground(new Color(0, 52, 102));
		textAreaPrezzo.setBounds(257, 89, 123, 18);
=======
		textAreaPrezzo.setBackground(Color.WHITE);
		textAreaPrezzo.setBounds(321, 88, 112, 22);
>>>>>>> branch 'master' of https://github.com/CarloTantan/Progetto-UninaSwap.git
		contentPane.add(textAreaPrezzo);
		
<<<<<<< HEAD
		JLabel lblNewLabel_1 = new JLabel("Prezzo oggetto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(70, 89, 163, 19);
=======
		JLabel lblNewLabel_1 = new JLabel("Prezzo");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setBounds(219, 89, 92, 19);
>>>>>>> branch 'master' of https://github.com/CarloTantan/Progetto-UninaSwap.git
		contentPane.add(lblNewLabel_1);
		
		JButton ButtonPubblica = new JButton("Pubblica");
		ButtonPubblica.setForeground(new Color(255, 255, 255));
		ButtonPubblica.setBackground(new Color(0, 52, 102));
<<<<<<< HEAD
		ButtonPubblica.setFont(new Font("Tahoma", Font.BOLD, 16));
		ButtonPubblica.setBounds(170, 141, 123, 37);
=======
		ButtonPubblica.setFont(new Font("Verdana", Font.BOLD, 16));
		ButtonPubblica.setBounds(266, 163, 123, 37);
>>>>>>> branch 'master' of https://github.com/CarloTantan/Progetto-UninaSwap.git
		contentPane.add(ButtonPubblica);
        ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		ButtonPubblica.setFocusPainted(false);
		ButtonPubblica.setBorderPainted(false);
		
		ButtonPubblica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textAreaPrezzo.getText().trim().isEmpty()) {
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
