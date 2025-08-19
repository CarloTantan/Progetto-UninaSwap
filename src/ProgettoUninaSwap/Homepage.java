package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage();
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
	public Homepage() {
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblnuovoUtente = new JLabel("Sei un nuovo utente?");
		lblnuovoUtente.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblnuovoUtente.setBounds(265, 107, 130, 31);
		contentPane.add(lblnuovoUtente);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		btnRegistrati.setBounds(405, 105, 122, 36);
		btnRegistrati.setFocusPainted(false);
		btnRegistrati.setBorderPainted(false);
		contentPane.add(btnRegistrati);
		
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Registrazione registrazioneFrame = new Registrazione();
				registrazioneFrame.setVisible(true);
			}
		});
		
		JLabel lblAccediConLe = new JLabel("Sei già registrato?");
		lblAccediConLe.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblAccediConLe.setBounds(265, 177, 130, 36);
		contentPane.add(lblAccediConLe);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		btnAccedi.setBounds(405, 177, 122, 36);
		btnAccedi.setFocusPainted(false);
		btnAccedi.setBorderPainted(false);
		contentPane.add(btnAccedi);
		
		btnAccedi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login loginFrame= new Login();
				loginFrame.setVisible(true);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 234, 369);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\marku\\Downloads\\IMG-20250718-WA00372.jpg"));
		lblNewLabel.setBounds(10, 10, 214, 218);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Benvenuto");
		lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(353, 35, 88, 36);
		contentPane.add(lblNewLabel_1);

	}
}
