package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Button;

public class Annuncio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Annuncio frame = new Annuncio();
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
	public Annuncio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(0, 52, 102));
		textArea.setBounds(251, 87, 179, 22);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(new Color(255, 255, 255));
		textArea_1.setBackground(new Color(0, 52, 102));
		textArea_1.setBounds(251, 119, 179, 56);
		contentPane.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setForeground(new Color(255, 255, 255));
		textArea_2.setBackground(new Color(0, 52, 102));
		textArea_2.setBounds(251, 196, 179, 22);
		contentPane.add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setForeground(new Color(255, 255, 255));
		textArea_3.setBackground(new Color(0, 52, 102));
		textArea_3.setBounds(251, 251, 179, 22);
		contentPane.add(textArea_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setForeground(new Color(255, 255, 255));
		textArea_4.setBackground(new Color(0, 52, 102));
		textArea_4.setBounds(251, 306, 179, 22);
		contentPane.add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setForeground(new Color(255, 255, 255));
		textArea_5.setBackground(new Color(0, 52, 102));
		textArea_5.setBounds(251, 355, 179, 22);
		contentPane.add(textArea_5);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(73, 83, 85, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(73, 139, 170, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fascia Oraria");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(73, 202, 135, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Modalità di consegna");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(73, 255, 179, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Oggetto");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(73, 310, 147, 22);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria oggetto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(73, 355, 236, 19);
		contentPane.add(lblNewLabel_5);
		
		JButton JScambio = new JButton("Scambio");
		JScambio.setForeground(new Color(255, 255, 255));
		JScambio.setToolTipText("");
		JScambio.setBackground(new Color(0, 52, 102));
		JScambio.setFont(new Font("Tahoma", Font.BOLD, 16));
		JScambio.setBounds(36, 426, 122, 56);
		contentPane.add(JScambio);
		
		JScambio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AnnuncioScambio annuncioScambioFrame = new AnnuncioScambio(); 
				annuncioScambioFrame.setVisible(true);
			}
		});
		
		JButton JRegalo = new JButton("Regalo");
		JRegalo.setForeground(new Color(255, 255, 255));
		JRegalo.setBackground(new Color(0, 52, 102));
		JRegalo.setFont(new Font("Tahoma", Font.BOLD, 16));
		JRegalo.setBounds(176, 426, 122, 56);
		contentPane.add(JRegalo);
		
		JRegalo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AnnuncioRegalo annuncioRegaloFrame = new AnnuncioRegalo(); 
				annuncioRegaloFrame.setVisible(true);
			}
		});
		
		JButton JVendita = new JButton("Vendita");
		JVendita.setForeground(new Color(255, 255, 255));
		JVendita.setBackground(new Color(0, 52, 102));
		JVendita.setFont(new Font("Tahoma", Font.BOLD, 16));
		JVendita.setBounds(322, 426, 122, 56);
		contentPane.add(JVendita);
		
		JVendita.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				AnnuncioVendita annuncioVenditaFrame = new AnnuncioVendita(); 
				annuncioVenditaFrame.setVisible(true);
			}
		}); 
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 504, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBounds(0, 0, 50, 58);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setBackground(SystemColor.textHighlight);
		ButtonAnnulla.setIcon(new ImageIcon("C:\\Users\\sabri\\Downloads\\icons8-annulla-3d-fluency-32.png"));
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				AreaUtente utenteFrame = new AreaUtente(); 
				utenteFrame.setVisible(true);
			}
		}); 
		
		JLabel lblNewLabel_6 = new JLabel("Annuncio");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(196, 10, 83, 35);
		panel.add(lblNewLabel_6);
	}
}
