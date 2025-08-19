package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class AnnuncioRegalo extends Annuncio {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnuncioRegalo frame = new AnnuncioRegalo();
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
	public AnnuncioRegalo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 437, 55);
		panel.setBackground(new Color(45, 134, 192));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Annuncio di regalo");
		lblNewLabel.setBounds(150, 10, 152, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBounds(0, 0, 40, 55);
		ButtonAnnulla.setIcon(new ImageIcon("C:\\Users\\sabri\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		panel.add(ButtonAnnulla);
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				Annuncio annuncioFrame = new Annuncio(); 
				annuncioFrame.setVisible(true);
			}
		}); 
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(0, 52, 102));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBounds(237, 91, 183, 39);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Motivo di cessione");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(27, 91, 200, 35);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Pubblica");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(167, 168, 118, 39);
		contentPane.add(btnNewButton_1);
	}

}
