package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class OffertaVendita extends JFrame {
// vendita prezzo casella di testo bottone conferma e indietro
	//scambio oggetto alternativo con immagine come ha fatto sabri e una descrizione casella di testo forse text area e bottone conferma e indietroù
	//regalo messaggio motivazionale perche vuoi questo regalo  conferma e indietro
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OffertaVendita frame = new OffertaVendita();
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
	public OffertaVendita() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaVendita.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Vendita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 463);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 769, 94);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Offerta Vendita");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setBounds(279, 10, 207, 79);
		panel.add(lblNewLabel);
		
		JButton btnUndo = new JButton("");
		btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				 ListaAnnunci ListaAnnunciFrame = new ListaAnnunci();
					ListaAnnunciFrame.setVisible(true);
					ListaAnnunciFrame.setLocationRelativeTo(null);
			}
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(OffertaVendita.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 85, 79);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false); 
		panel.add(btnUndo);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.BOLD, 16));
		textField.setBounds(303, 158, 166, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Proponi un importo");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setBounds(95, 154, 205, 40);
		contentPane.add(lblNewLabel_1);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Offerta inviata ", null, JOptionPane.INFORMATION_MESSAGE);
				 setVisible(false);
				 ListaAnnunci ListaAnnunciFrame = new ListaAnnunci();
				ListaAnnunciFrame.setVisible(true);
				ListaAnnunciFrame.setLocationRelativeTo(null);
			}
		});
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setForeground(new Color(255, 255, 255));
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 16));
		btnConferma.setBounds(280, 294, 219, 36);
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false); 
		contentPane.add(btnConferma);
	}

}
