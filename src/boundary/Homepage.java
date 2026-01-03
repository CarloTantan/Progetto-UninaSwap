package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainController.MainController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MainController controller; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController controller = new MainController();
					
					Homepage frame = new Homepage(controller);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Apri a schermo intero
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
	public Homepage(MainController controller) {
		this.controller = controller;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Homepage.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1100, 750)); // Dimensione minima per usabilità
		
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
		
		// Logo centrato nel pannello laterale
		GridBagConstraints gbcLogo = new GridBagConstraints();
		gbcLogo.gridx = 0;
		gbcLogo.gridy = 0;
		gbcLogo.anchor = GridBagConstraints.CENTER;
		
		JLabel lblLogo = new JLabel("");
		ImageIcon iconaLogo = (new ImageIcon(Homepage.class.getResource("/icons/iconaUninaSwapGrande.png")));
		Image Logo = iconaLogo.getImage().getScaledInstance(350, 325, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(Logo));
		panelLaterale.add(lblLogo, gbcLogo);
		
		// Pannello centrale 
		JPanel panelCentrale = new JPanel();
		panelCentrale.setBackground(Color.WHITE);
		panelCentrale.setLayout(new GridBagLayout());
		contentPane.add(panelCentrale, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		
		// Titolo
		JLabel lblTitolo = new JLabel("Benvenuto in Unina Swap");
		lblTitolo.setFont(new Font("Verdana", Font.BOLD, 32));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 10, 40, 10);
		panelCentrale.add(lblTitolo, (GridBagConstraints) gbc.clone());
		
		JLabel lblnuovoUtente = new JLabel("Sei un nuovo utente?");
		lblnuovoUtente.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.EAST;
		panelCentrale.add(lblnuovoUtente, (GridBagConstraints) gbc.clone());
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setForeground(Color.WHITE);
		btnRegistrati.setBackground(new Color(46, 132, 191));
		btnRegistrati.setFont(new Font("Verdana", Font.BOLD, 18));
		btnRegistrati.setPreferredSize(new Dimension(180, 45));
		btnRegistrati.setFocusPainted(false);
		btnRegistrati.setBorderPainted(false);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panelCentrale.add(btnRegistrati, (GridBagConstraints) gbc.clone());
		
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apriRegistrazione();
				
			}
		});
		
		btnRegistrati.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnRegistrati.setBackground(new Color(66, 152, 211));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnRegistrati.setBackground(new Color(46, 132, 191));
		    }
		});
		
		JLabel lblGiaRegistrato = new JLabel("Sei già registrato?");
		lblGiaRegistrato.setFont(new Font("Verdana", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		panelCentrale.add(lblGiaRegistrato, (GridBagConstraints) gbc.clone());
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setForeground(Color.WHITE);
		btnAccedi.setBackground(new Color(0, 52, 101));
		btnAccedi.setFont(new Font("Verdana", Font.BOLD, 18));
		btnAccedi.setPreferredSize(new Dimension(180, 45));
		btnAccedi.setFocusPainted(false);
		btnAccedi.setBorderPainted(false);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		panelCentrale.add(btnAccedi, (GridBagConstraints) gbc.clone());
		
		btnAccedi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apriLogin();
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
	
	public void apriRegistrazione() {
		dispose();
		Registrazione registrazioneFrame = new Registrazione(controller);
		registrazioneFrame.setVisible(true);
	}
	
	public void apriLogin() {
		dispose();
		Login loginFrame= new Login(controller);
		loginFrame.setVisible(true);
	}
}
