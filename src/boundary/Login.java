package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.LoginDAO;
import entity.Utente_entity;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField textFieldPassword;
	private JTextField textFieldMatricola;
	private LoginDAO loginDAO;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		loginDAO = new LoginDAO();
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Pannello laterale sinistro - altezza aumentata per coprire tutto lo schermo
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(46, 132, 191));
		panel.setBounds(0, 0, 350, 1080);
		contentPane.add(panel);
		
		// Logo centrato nel pannello laterale
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/icons/iconaUninaSwapPiccola.jpg")));
		lblNewLabel_1.setBounds(68, 200, 214, 218);
		panel.add(lblNewLabel_1);
		
		// Bottone indietro
		JButton btnIndietro = new JButton("");
		btnIndietro.setFont(new Font("Verdana", Font.BOLD, 16));
		btnIndietro.setIcon(new ImageIcon(Login.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnIndietro.setBackground(new Color(46, 132, 191));
		btnIndietro.setBounds(15, 15, 50, 50);
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
		
		// Coordinate del form - centrato rispetto allo schermo

		// Titolo Log in
		JLabel lblLogin = new JLabel("Log in");
		lblLogin.setToolTipText("");
		lblLogin.setFont(new Font("Verdana", Font.BOLD, 32));
		lblLogin.setBounds(600, 280 - 100, 150, 40);
		contentPane.add(lblLogin);
		
		// Label Matricola
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Verdana", Font.BOLD, 18));
		lblMatricola.setBounds(600, 280, 150, 30);
		contentPane.add(lblMatricola);
		
		// Campo Matricola
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldMatricola.setColumns(10);
		textFieldMatricola.setBounds(600, 280 + 35, 280, 38);
		contentPane.add(textFieldMatricola);
		
		// Label Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPassword.setBounds(600, 280 + 95, 150, 30);
		contentPane.add(lblPassword);
		
		// Campo Password
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(600, 280 + 130, 280, 38);
		contentPane.add(textFieldPassword);
		
		// Bottone Accedi
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setForeground(new Color(255, 255, 255));
		btnAccedi.setBackground(new Color(0, 52, 101));
		btnAccedi.setFont(new Font("Verdana", Font.BOLD, 18));
		btnAccedi.setBounds(600 + 50, 280 + 200, 180, 45);
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
					effettuaLogin();
				
				}
			}
		});
	}
	
	private void effettuaLogin() {
        // Recupera i valori dai campi
        String matricola = textFieldMatricola.getText().trim();
        String password = new String(textFieldPassword.getPassword());
        
        // Validazione base
        if (matricola.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Inserisci matricola e password", 
                "Errore", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verifica login usando il DAO
        boolean loginValido = loginDAO.verificaLogin(matricola, password);
        
        if (loginValido) {
            JOptionPane.showMessageDialog(this, 
                "Login effettuato con successo!", 
                "Successo", 
                JOptionPane.INFORMATION_MESSAGE);
            
            Utente_entity UtenteLoggato = loginDAO.getUtente(matricola);
            
            // Apri l'interfaccia area utente
            this.dispose(); // Chiudi la finestra di login
            AreaUtente areaUtente = new AreaUtente(UtenteLoggato);
            areaUtente.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Matricola o password non corretti", 
                "Errore Login", 
                JOptionPane.ERROR_MESSAGE);

            textFieldPassword.setText("");// Pulisci il campo password
        }
	}
}