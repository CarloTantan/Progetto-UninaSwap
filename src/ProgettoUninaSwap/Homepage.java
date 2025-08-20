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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Homepage.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblnuovoUtente = new JLabel("Sei un nuovo utente?");
		lblnuovoUtente.setFont(new Font("Dialog", Font.BOLD, 16));
		lblnuovoUtente.setBounds(258, 108, 179, 34);
		contentPane.add(lblnuovoUtente);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setForeground(Color.WHITE);
		btnRegistrati.setBackground(new Color(46, 132, 191));
		btnRegistrati.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		btnRegistrati.setBounds(441, 106, 122, 36);
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
		lblAccediConLe.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAccediConLe.setBounds(258, 178, 151, 36);
		contentPane.add(lblAccediConLe);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setForeground(Color.WHITE);
		btnAccedi.setBackground(new Color(11, 52, 92));
		btnAccedi.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		btnAccedi.setBounds(441, 178, 122, 36);
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
		panel.setBackground(new Color(46, 132, 191));
		panel.setBounds(0, 0, 234, 373);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Homepage.class.getResource("/icons/iconaUninaSwapPiccola.jpg")));
		lblNewLabel.setBounds(10, 0, 214, 251);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Benvenuto in Unina Swap");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setBounds(289, 35, 245, 36);
		contentPane.add(lblNewLabel_1);

	}
}
