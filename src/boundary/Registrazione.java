package boundary;


import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainController.MainController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che rappresenta la finestra di Registrazione per nuovi utenti.
 * Permette agli studenti di crearsi un account nell'applicazione UninaSwap
 * inserendo i loro dati personali e le credenziali di accesso.
 * Tutti i campi sono obbligatori e vengono validati dal controller.
 */

public class Registrazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldMatricola;
	private JTextField textFieldEmail;
	private JPasswordField textFieldPassword;
	private JTextField textFieldTelefono;
	private JPasswordField textFieldConfermaPassword;
	private MainController controller; 


	public Registrazione(MainController controller) {
		this.controller = controller;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registrazione.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1100, 600));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		// Pannello laterale sinistro
		JPanel panelLaterale = new JPanel();
		panelLaterale.setBackground(new Color(46, 132, 191));
		panelLaterale.setPreferredSize(new Dimension(350, 0));
		panelLaterale.setLayout(new GridBagLayout());
		contentPane.add(panelLaterale, BorderLayout.WEST);

		GridBagConstraints gbcIndietro = new GridBagConstraints();
		gbcIndietro.gridx = 0;
		gbcIndietro.gridy = 0;
		gbcIndietro.anchor = GridBagConstraints.NORTHWEST;
		gbcIndietro.insets = new Insets(15, 15, 0, 0);
		gbcIndietro.weightx = 1.0;
		gbcIndietro.weighty = 0.0;

		// Bottone indietro
		JButton btnIndietro = new JButton("");
		btnIndietro.setBackground(new Color(46, 132, 191));
		btnIndietro.setIcon(new ImageIcon(Registrazione.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnIndietro.setPreferredSize(new Dimension(50, 50));
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBorderPainted(false);
		panelLaterale.add(btnIndietro, gbcIndietro);

		// Logo centrato nel pannello laterale
		GridBagConstraints gbcLogo = new GridBagConstraints();
		gbcLogo.gridx = 0;
		gbcLogo.gridy = 1;
		gbcLogo.anchor = GridBagConstraints.CENTER;
		gbcLogo.weighty = 1.0; // Prende tutto lo spazio restante

		JLabel lblLogo = new JLabel("");
		ImageIcon iconaLogo = (new ImageIcon(Homepage.class.getResource("/icons/iconaUninaSwapGrande.png")));
		Image Logo = iconaLogo.getImage().getScaledInstance(350, 325, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(Logo));
		panelLaterale.add(lblLogo, gbcLogo);
		
		btnIndietro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	tornaIndietro();
		    }
		});
		
		btnIndietro.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnIndietro.setBackground(new Color(66, 152, 211));
		        btnIndietro.setContentAreaFilled(true);
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnIndietro.setBackground(new Color(46, 132, 191));
		        btnIndietro.setContentAreaFilled(false);
		    }
		});
		
		// PANNELLO CENTRALE :Pannello contenente il form di registrazione
		JPanel panelCentrale = new JPanel();
		panelCentrale.setBackground(Color.WHITE);
		panelCentrale.setLayout(new GridBagLayout());
		contentPane.add(panelCentrale, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 10, 8, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Titolo
		JLabel lblRegistra = new JLabel("Registrazione Nuovo Utente");
		lblRegistra.setFont(new Font("Verdana", Font.BOLD, 28));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 30, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentrale.add(lblRegistra, gbc);
		
		// Reset per i campi
		gbc.gridwidth = 1;
		gbc.insets = new Insets(8, 10, 8, 10);
		gbc.anchor = GridBagConstraints.WEST;
		
		// Nome
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCentrale.add(lblNome, gbc);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldNome.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCentrale.add(textFieldNome, gbc);
		
		// Cognome
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelCentrale.add(lblCognome, gbc);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldCognome.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelCentrale.add(textFieldCognome, gbc);
		
		// Matricola
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelCentrale.add(lblMatricola, gbc);
		
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldMatricola.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelCentrale.add(textFieldMatricola, gbc);
		
		// Telefono
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelCentrale.add(lblTelefono, gbc);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldTelefono.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelCentrale.add(textFieldTelefono, gbc);
		
		// Email
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelCentrale.add(lblEmail, gbc);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldEmail.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 5;
		panelCentrale.add(textFieldEmail, gbc);
		
		// Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 6;
		panelCentrale.add(lblPassword, gbc);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldPassword.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 6;
		panelCentrale.add(textFieldPassword, gbc);
		
		// Conferma Password
		JLabel lblConfermaPassword = new JLabel("Conferma Password");
		lblConfermaPassword.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 7;
		panelCentrale.add(lblConfermaPassword, gbc);
		
		textFieldConfermaPassword = new JPasswordField();
		textFieldConfermaPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldConfermaPassword.setPreferredSize(new Dimension(250, 30));
		gbc.gridx = 1;
		gbc.gridy = 7;
		panelCentrale.add(textFieldConfermaPassword, gbc);
		
		// Bottone Registrati
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setForeground(Color.WHITE);
		btnRegistrati.setBackground(new Color(46, 132, 191));
		btnRegistrati.setFont(new Font("Verdana", Font.BOLD, 18));
		btnRegistrati.setPreferredSize(new Dimension(200, 45));
		btnRegistrati.setFocusPainted(false);
		btnRegistrati.setBorderPainted(false);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentrale.add(btnRegistrati, gbc);
		getRootPane().setDefaultButton(btnRegistrati);
		
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					registrazioneUtente();
				
			}
		});
		
		btnRegistrati.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnRegistrati.setBackground(new Color(66, 152, 211));
		    }
		    public void mouseExited(MouseEvent evt) {
		        btnRegistrati.setBackground(new Color(46, 132, 191));
		    }
		});
	}
	//Metodo che gestisce il processo di registrazione di un nuovo utente.
	private void registrazioneUtente() {
		String nome = textFieldNome.getText().trim();
		String cognome = textFieldCognome.getText().trim();
		String matricola = textFieldMatricola.getText().trim();
		String telefono = textFieldTelefono.getText().trim();
		String email = textFieldEmail.getText().trim();
        String password = new String(textFieldPassword.getPassword());
        String confermaPassword = new String(textFieldConfermaPassword.getPassword());
        
        String registrazione = controller.EffettuaRegistrazione(nome, cognome, matricola, telefono, email, password, confermaPassword);
        
        if (registrazione.equals("Registrazione effettuata con successo")) {
            JOptionPane.showMessageDialog(this, registrazione, "Successo", JOptionPane.INFORMATION_MESSAGE);
            
            controller.apriHomepage();
            
        } else {
            JOptionPane.showMessageDialog(this, registrazione,"Impossibile effettuare la Registrazione", JOptionPane.ERROR_MESSAGE);

            textFieldPassword.setText("");
            textFieldConfermaPassword.setText("");
        }
                
	}
	
	// torna alla homepage
	private void tornaIndietro() {
	    controller.apriHomepage();
    }
}