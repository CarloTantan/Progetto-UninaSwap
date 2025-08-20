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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Registrazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Registrazione;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldMatricola;
	private JTextField textFieldEmail;
	private JPasswordField PasswordPassword;
	private JTextField textFieldTelefono;
	private JPasswordField PasswordConfermaPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrazione frame = new Registrazione();
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
	public Registrazione() {
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 670, 440);
		Registrazione = new JPanel();
		Registrazione.setBackground(Color.WHITE);
		Registrazione.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Registrazione);
		Registrazione.setLayout(null);
		
		JLabel lblRegistra = new JLabel("Registrazione Nuovo Utente");
		lblRegistra.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegistra.setBounds(295, 10, 280, 35);
		Registrazione.add(lblRegistra);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNome.setBounds(272, 64, 57, 22);
		Registrazione.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldNome.setBounds(444, 64, 139, 22);
		Registrazione.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCognome.setBounds(272, 96, 74, 22);
		Registrazione.add(lblCognome);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(444, 96, 139, 22);
		Registrazione.add(textFieldCognome);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEmail.setBounds(272, 193, 74, 22);
		Registrazione.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setBounds(272, 225, 74, 22);
		Registrazione.add(lblPassword);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMatricola.setBounds(272, 128, 74, 22);
		Registrazione.add(lblMatricola);
		
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldMatricola.setColumns(10);
		textFieldMatricola.setBounds(444, 128, 139, 22);
		Registrazione.add(textFieldMatricola);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(444, 193, 139, 22);
		Registrazione.add(textFieldEmail);
		
		PasswordPassword = new JPasswordField();
		PasswordPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		PasswordPassword.setColumns(10);
		PasswordPassword.setBounds(444, 225, 139, 22);
		Registrazione.add(PasswordPassword);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTelefono.setBounds(272, 160, 74, 22);
		Registrazione.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(444, 160, 139, 22);
		Registrazione.add(textFieldTelefono);
		
		JLabel lblConfermaPassword = new JLabel("Conferma Password");
		lblConfermaPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblConfermaPassword.setBounds(272, 259, 162, 22);
		Registrazione.add(lblConfermaPassword);
		
		PasswordConfermaPassword = new JPasswordField();
		PasswordConfermaPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		PasswordConfermaPassword.setColumns(10);
		PasswordConfermaPassword.setBounds(444, 259, 139, 22);
		Registrazione.add(PasswordConfermaPassword);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setForeground(Color.WHITE);
		btnRegistrati.setBackground(new Color(46, 132, 191));
		btnRegistrati.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		btnRegistrati.setBounds(349, 313, 172, 35);
		btnRegistrati.setFocusPainted(false);
		btnRegistrati.setBorderPainted(false);
		Registrazione.add(btnRegistrati);
		
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldNome.getText().trim().isEmpty() ||
			            textFieldCognome.getText().trim().isEmpty() ||
			            textFieldMatricola.getText().trim().isEmpty() ||
			            textFieldEmail.getText().trim().isEmpty() ||
			            PasswordPassword.getText().trim().isEmpty() ||
			            textFieldTelefono.getText().trim().isEmpty() ||
			            PasswordConfermaPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else if (!textFieldEmail.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
					JOptionPane.showMessageDialog(null, "Inserisci un indirizzo E-mail valido", "Indirizzo E-mail non valido", JOptionPane.WARNING_MESSAGE);
				
				} else if (!PasswordPassword.getText().equals(PasswordConfermaPassword.getText())) {
					JOptionPane.showMessageDialog(null, "Le password non corrispondono", "Errore di conferma password", JOptionPane.WARNING_MESSAGE);
				}else {
					setVisible(false); JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo", "Utente Registrato", JOptionPane.INFORMATION_MESSAGE);
					Homepage homepageFrame = new Homepage();
					homepageFrame.setVisible(true);
				}
		}});
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(46, 132, 191));
		panel.setBounds(0, 0, 234, 403);
		Registrazione.add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Registrazione.class.getResource("/icons/iconaUninaSwapPiccola.jpg")));
		lblNewLabel.setBounds(10, 52, 214, 218);
		panel.add(lblNewLabel);
		
		JButton btnIndietro = new JButton("");
		btnIndietro.setBackground(new Color(46, 132, 191));
		btnIndietro.setIcon(new ImageIcon(Registrazione.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
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
		

	}
}
