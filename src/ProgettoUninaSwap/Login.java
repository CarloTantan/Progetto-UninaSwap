package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPassword;
	private JTextField textFieldMatricola;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Log in");
		lblLogin.setToolTipText("");
		lblLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		lblLogin.setBounds(370, 32, 57, 22);
		contentPane.add(lblLogin);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 234, 373);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\marku\\Downloads\\IMG-20250718-WA00372.jpg"));
		lblNewLabel_1.setBounds(10, 52, 214, 218);
		panel.add(lblNewLabel_1);
		
		JButton btnIndietro = new JButton("");
		btnIndietro.setIcon(new ImageIcon("C:\\Users\\marku\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		btnIndietro.setBackground(new Color(30, 144, 255));
		btnIndietro.setBounds(10, 10, 39, 32);
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBorderPainted(false);
		panel.add(btnIndietro);
		
		btnIndietro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	Homepage homepageFrame = new Homepage();
		        homepageFrame.setVisible(true);
		    }
		});
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		lblPassword.setBounds(278, 90, 74, 22);
		contentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(417, 122, 96, 18);
		contentPane.add(textFieldPassword);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		lblMatricola.setBounds(278, 120, 137, 22);
		contentPane.add(lblMatricola);
		
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textFieldMatricola.setColumns(10);
		textFieldMatricola.setBounds(417, 92, 96, 18);
		contentPane.add(textFieldMatricola);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		btnAccedi.setBounds(310, 170, 172, 35);
		btnAccedi.setFocusPainted(false);
		btnAccedi.setBorderPainted(false);
		contentPane.add(btnAccedi);
		
		btnAccedi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldMatricola.getText().trim().isEmpty() ||
						textFieldPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
				}
			}
		});
	}

}
