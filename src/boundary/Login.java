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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField textFieldPassword;
	private JTextField textFieldMatricola;
	private MainController controller; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login(MainController Controller) {
		this.controller = Controller; 
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(850, 600));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		// Pannello laterale sinistro
		JPanel panelLaterale = new JPanel();
		panelLaterale.setLayout(null);
		panelLaterale.setBackground(new Color(46, 132, 191));
		panelLaterale.setPreferredSize(new Dimension(350, 0));
		contentPane.add(panelLaterale, BorderLayout.WEST);
		
		// Logo centrato nel pannello laterale
		JLabel lblLogo = new JLabel("");
		lblLogo.setFont(new Font("Verdana", Font.BOLD, 16));
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/icons/iconaUninaSwapPiccola.jpg")));
		lblLogo.setBounds(68, 200, 214, 218);
		panelLaterale.add(lblLogo);
		
		// Bottone indietro
		JButton btnIndietro = new JButton("");
		btnIndietro.setFont(new Font("Verdana", Font.BOLD, 16));
		btnIndietro.setIcon(new ImageIcon(Login.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnIndietro.setBackground(new Color(46, 132, 191));
		btnIndietro.setBounds(15, 15, 50, 50);
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBorderPainted(false);
		panelLaterale.add(btnIndietro);
		
		btnIndietro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	Homepage homepageFrame = new Homepage(controller);
		    	homepageFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		        homepageFrame.setVisible(true);
		    }
		});
		
		btnIndietro.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnIndietro.setBackground(new Color(66, 152, 211));
		        btnIndietro.setContentAreaFilled(true);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnIndietro.setBackground(new Color(46, 132, 191));
		        btnIndietro.setContentAreaFilled(false);
		    }
		});
		
		// Pannello centrale con GridBagLayout
		JPanel panelCentrale = new JPanel();
		panelCentrale.setBackground(Color.WHITE);
		panelCentrale.setLayout(new GridBagLayout());
		contentPane.add(panelCentrale, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;		

		// Titolo Log in
		JLabel lblLogin = new JLabel("Log in");
		lblLogin.setFont(new Font("Verdana", Font.BOLD, 32));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 40, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentrale.add(lblLogin, gbc);
		
		// Label Matricola
		JLabel lblMatricola = new JLabel("Matricola");
		lblMatricola.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 5, 10);
		gbc.anchor = GridBagConstraints.WEST;
		panelCentrale.add(lblMatricola, gbc);
		
		// Campo Matricola
		textFieldMatricola = new JTextField();
		textFieldMatricola.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldMatricola.setPreferredSize(new Dimension(280, 38));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 10, 10, 10);
		panelCentrale.add(textFieldMatricola, gbc);
		
		// Label Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 5, 10);
		gbc.anchor = GridBagConstraints.WEST;
		panelCentrale.add(lblPassword, gbc);
		
		// Campo Password
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		textFieldPassword.setPreferredSize(new Dimension(280, 38));
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 10, 20, 10);
		panelCentrale.add(textFieldPassword, gbc);
		
		// Bottone Accedi
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setForeground(new Color(255, 255, 255));
		btnAccedi.setBackground(new Color(0, 52, 101));
		btnAccedi.setFont(new Font("Verdana", Font.BOLD, 18));
		btnAccedi.setPreferredSize(new Dimension(180, 45));
		btnAccedi.setFocusPainted(false);
		btnAccedi.setBorderPainted(false);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		panelCentrale.add(btnAccedi, gbc);
		getRootPane().setDefaultButton(btnAccedi);
		
		btnAccedi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					effettuaLogin();
			}
		});
		
		btnAccedi.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnAccedi.setBackground(new Color(0, 70, 140));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnAccedi.setBackground(new Color(0, 52, 101));
		    }
		});
	}
	
	private void effettuaLogin() {
        // Recupera i valori dai campi
        String matricola = textFieldMatricola.getText().trim();
        String password = new String(textFieldPassword.getPassword());
        
        
        // Verifica login usando il Controller
        	String loginValido = controller.EffettuaLogin(matricola, password);
        	
        	if (loginValido.equals("Login effettuato con successo")) {
                JOptionPane.showMessageDialog(this, 
                    "Login effettuato con successo!", 
                    "Successo", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                
                // Apri l'interfaccia area utente
                dispose(); // Chiudi la finestra di login
                AreaUtente areaUtente = new AreaUtente(controller);
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