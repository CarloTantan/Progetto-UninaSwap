package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AreaUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCreaAnnuncio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaUtente frame = new AreaUtente();
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
	public AreaUtente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\UNINA_4.png"));
		setTitle("AreaUtente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 565);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVisualizzaOfferteRicevute = new JButton("Visualizza Offerte Ricevute");
		btnVisualizzaOfferteRicevute.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-lista-48.png"));
		btnVisualizzaOfferteRicevute.setForeground(new Color(255, 255, 255));
		btnVisualizzaOfferteRicevute.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 15));
		btnVisualizzaOfferteRicevute.setBackground(new Color(0, 52, 104));
		btnVisualizzaOfferteRicevute.setBounds(71, 275, 356, 84);
		btnVisualizzaOfferteRicevute.setFocusPainted(false);
		contentPane.add(btnVisualizzaOfferteRicevute);
		
		JButton btnVisualizzaStoricoOfferte = new JButton("Visualizza Storico Offerte");
		
		btnVisualizzaStoricoOfferte.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-lista-48.png"));
		btnVisualizzaStoricoOfferte.setForeground(Color.WHITE);
		btnVisualizzaStoricoOfferte.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 15));
		btnVisualizzaStoricoOfferte.setBackground(new Color(0, 52, 104));
		btnVisualizzaStoricoOfferte.setBounds(71, 163, 356, 84);
		btnVisualizzaStoricoOfferte.setFocusPainted(false);
		contentPane.add(btnVisualizzaStoricoOfferte);
		
		JButton btnInserisciRecensione = new JButton("Inserisci Recensione");
		btnInserisciRecensione.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-aggiungi-48.png"));
		btnInserisciRecensione.setForeground(Color.WHITE);
		btnInserisciRecensione.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 15));
		btnInserisciRecensione.setBackground(new Color(0, 52, 104));
		btnInserisciRecensione.setBounds(509, 163, 303, 84);
		btnInserisciRecensione.setFocusPainted(false);
		contentPane.add(btnInserisciRecensione);
		
		btnCreaAnnuncio = new JButton("Crea Annuncio");
		btnCreaAnnuncio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Annuncio AnnuncioFrame = new Annuncio();
				AnnuncioFrame.setVisible(true);
				AnnuncioFrame.setLocationRelativeTo(null);
			}
		});
		btnCreaAnnuncio.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-aggiungi-48.png"));
		btnCreaAnnuncio.setForeground(Color.WHITE);
		btnCreaAnnuncio.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 15));
		btnCreaAnnuncio.setBackground(new Color(0, 52, 104));
		btnCreaAnnuncio.setBounds(509, 275, 303, 84);
		btnCreaAnnuncio.setFocusPainted(false);
		contentPane.add(btnCreaAnnuncio);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(50, 132, 188));
		panel.setBounds(0, 0, 897, 102);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\UNINA__4-1.png"));
		lblNewLabel_2.setBounds(180, 10, 182, 106);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Area Utente");
		lblNewLabel.setBounds(392, 44, 152, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD | Font.ITALIC, 25));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(578, 10, 182, 84);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-utente-uomo-cerchiato-96.png"));
		
		JLabel lblNewLabel_3 = new JLabel("Giordano Rossi");
		lblNewLabel_3.setBounds(686, 39, 160, 48);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setBackground(new Color(45, 134, 192));
		lblNewLabel_3.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUndo.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-annulla-3d-fluency-32.png"));
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setBounds(10, 10, 46, 77);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JButton btnVisualizzaRecensioni = new JButton("Visualizza Recensioni");
		btnVisualizzaRecensioni.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-lista-48.png"));
		btnVisualizzaRecensioni.setForeground(Color.WHITE);
		btnVisualizzaRecensioni.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 15));
		btnVisualizzaRecensioni.setFocusPainted(false);
		btnVisualizzaRecensioni.setBackground(new Color(0, 52, 104));
		btnVisualizzaRecensioni.setBounds(71, 388, 356, 84);
		contentPane.add(btnVisualizzaRecensioni);
		
		JButton btnVisualizzaAnnuncio = new JButton("Visualizza Annuncio");
		btnVisualizzaAnnuncio.setIcon(new ImageIcon("C:\\Users\\carlo\\OneDrive\\Desktop\\DISEGNI_APP\\icons8-lista-48.png"));
		btnVisualizzaAnnuncio.setForeground(Color.WHITE);
		btnVisualizzaAnnuncio.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 15));
		btnVisualizzaAnnuncio.setFocusPainted(false);
		btnVisualizzaAnnuncio.setBackground(new Color(0, 52, 104));
		btnVisualizzaAnnuncio.setBounds(509, 388, 303, 84);
		contentPane.add(btnVisualizzaAnnuncio);
	}
}
