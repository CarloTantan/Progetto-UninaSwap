package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OfferteRicevute extends JFrame {

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
					OfferteRicevute frame = new OfferteRicevute();
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
	public OfferteRicevute() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 406);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(10, 0, 729, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Offerte Ricevute");
		lblNewLabel_1.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(268, 5, 181, 53);
		panel.add(lblNewLabel_1);
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente();
				AreaUtenteFrame.setVisible(true);
				AreaUtenteFrame.setLocationRelativeTo(null);
			}
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(OfferteRicevute.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(0, 0, 95, 68);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		textField = new JTextField();
		textField.setBounds(263, 146, 183, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Titolo Annuncio");
		lblNewLabel.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblNewLabel.setBounds(92, 137, 210, 44);
		contentPane.add(lblNewLabel);
		
		JButton btnAccetta = new JButton("Accetta");
		btnAccetta.setFont(new Font("Verdana Pro Black", Font.BOLD, 17));
		btnAccetta.setBackground(new Color(0, 52, 101));
		btnAccetta.setForeground(new Color(255, 255, 255));
		btnAccetta.setBounds(136, 245, 153, 44);
		btnAccetta.setFocusPainted(false);
		btnAccetta.setBorderPainted(false);
		contentPane.add(btnAccetta);
		
		JButton btnRifiuta = new JButton("Rifiuta");
		btnRifiuta.setForeground(Color.WHITE);
		btnRifiuta.setFont(new Font("Verdana Pro Black", Font.BOLD, 17));
		btnRifiuta.setBackground(new Color(0, 52, 101));
		btnRifiuta.setBounds(443, 245, 153, 44);
		btnRifiuta.setFocusPainted(false);
		btnRifiuta.setBorderPainted(false); 
		contentPane.add(btnRifiuta);
	}
}
