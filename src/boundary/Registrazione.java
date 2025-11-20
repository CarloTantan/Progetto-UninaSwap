package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.RegistrazioneDAO;

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
import java.awt.Toolkit;

public class Registrazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Registrazione;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldMatricola;
	private JTextField textFieldEmail;
	private JPasswordField textFieldPassword;
	private JTextField textFieldTelefono;
	private JPasswordField textFieldConfermaPassword;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registrazione.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 670, 440);
		Registrazione = new JPanel();
		Registrazione.setBackground(Color.WHITE);
		Registrazione.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Registrazione);
		Registrazione.setLayout(null);
		
		JLabel lblRegistra = new JLabel("Registrazione Nuovo Utente");
		lblRegistra.setFont(new Font("Verdana", Font.BOLD, 20));
		lblRegistra.setBounds(252, 10, 346, 35);
		Registrazione.add(lblRegistra);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNome.setBounds(272, 64, 326, 22);
		Registrazione.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldNome.setBounds(444, 64, 154, 22);
		Registrazione.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCognome.setBounds(272, 96, 326, 22);
		Registrazione.add(lblCognome);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(444, 96, 154, 22);
		Registrazione.add(textFieldCognome);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Verdana", Font.BOLD, 16));
		lblEmail.setBounds(272, 193, 326, 22);
		Registrazione.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 16));
		lblPassword.setBounds(272, 225, 326, 22);
		Registrazione.add(lblPassword);
		
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Verdana", Font.BOLD, 16));
		lblMatricola.setBounds(272, 128, 326, 22);
		Registrazione.add(lblMatricola);
		
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldMatricola.setColumns(10);
		textFieldMatricola.setBounds(444, 128, 154, 22);
		Registrazione.add(textFieldMatricola);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(444, 193, 154, 22);
		Registrazione.add(textFieldEmail);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(444, 225, 154, 22);
		Registrazione.add(textFieldPassword);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTelefono.setBounds(272, 160, 326, 22);
		Registrazione.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(444, 160, 154, 22);
		Registrazione.add(textFieldTelefono);
		
		JLabel lblConfermaPassword = new JLabel("Conferma Password");
		lblConfermaPassword.setFont(new Font("Verdana", Font.BOLD, 16));
		lblConfermaPassword.setBounds(252, 257, 326, 22);
		Registrazione.add(lblConfermaPassword);
		
		textFieldConfermaPassword = new JPasswordField();
		textFieldConfermaPassword.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldConfermaPassword.setColumns(10);
		textFieldConfermaPassword.setBounds(444, 259, 154, 22);
		Registrazione.add(textFieldConfermaPassword);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setForeground(Color.WHITE);
		btnRegistrati.setBackground(new Color(46, 132, 191));
		btnRegistrati.setFont(new Font("Verdana", Font.BOLD, 16));
		btnRegistrati.setBounds(349, 313, 249, 35);
		btnRegistrati.setFocusPainted(false);
		btnRegistrati.setBorderPainted(false);
		Registrazione.add(btnRegistrati);
		getRootPane().setDefaultButton(btnRegistrati);
		
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldNome.getText().trim().isEmpty() ||
			            textFieldCognome.getText().trim().isEmpty() ||
			            textFieldMatricola.getText().trim().isEmpty() ||
			            textFieldEmail.getText().trim().isEmpty() ||
			            textFieldPassword.getText().trim().isEmpty() ||
			            textFieldTelefono.getText().trim().isEmpty() ||
			            textFieldConfermaPassword.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				
				} else if (!textFieldPassword.getText().equals(textFieldConfermaPassword.getText())) {
					JOptionPane.showMessageDialog(null, "Le password non corrispondono", "Errore di conferma password", JOptionPane.WARNING_MESSAGE);
				}else {
					registrazioneUtente();
					
				}
		}});
		
		

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(46, 132, 191));
		panel.setBounds(0, 0, 234, 403);
		Registrazione.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
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
	
	private void registrazioneUtente() {
		String nome = textFieldNome.getText().trim();
		String cognome = textFieldCognome.getText().trim();
		String matricola = textFieldMatricola.getText().trim();
		String telefono = textFieldTelefono.getText().trim();
		String email = textFieldEmail.getText().trim();
        String password = new String(textFieldPassword.getPassword());
        
        RegistrazioneDAO registrazioneDAO = new RegistrazioneDAO();
        boolean registrazione = registrazioneDAO.effettuaRegistrazione(nome, cognome, matricola, telefono, email, password);
        
        if (registrazione) {
            JOptionPane.showMessageDialog(this, 
                "Registrazione effettuata con successo!", 
                "Successo", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Apri l'interfaccia area utente
            this.dispose(); // Chiudi la finestra di registrazione
            Homepage homepageFrame = new Homepage();
			homepageFrame.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Email o matricola già in uso.", 
                "Errore Registrazione", 
                JOptionPane.ERROR_MESSAGE);

            textFieldPassword.setText("");// Pulisci il campo password
            textFieldConfermaPassword.setText("");
        }
        
	}
}
