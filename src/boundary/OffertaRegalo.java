package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class OffertaRegalo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OffertaRegalo frame = new OffertaRegalo();
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
	public OffertaRegalo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaRegalo.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Regalo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(10, 0, 801, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnUndo = new JButton("");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				 ListaAnnunci ListaAnnunciFrame = new ListaAnnunci();
					ListaAnnunciFrame.setVisible(true);
					ListaAnnunciFrame.setLocationRelativeTo(null);
			}
		});
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setIcon(new ImageIcon(OffertaRegalo.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 46, 68);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false); 
		panel.add(btnUndo);
		
		JLabel lblOffertaRegalo = new JLabel("Offerta Regalo");
		lblOffertaRegalo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOffertaRegalo.setBounds(285, 39, 228, 34);
		panel.add(lblOffertaRegalo);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.BOLD, 16));
		textArea.setBounds(270, 173, 227, 105);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Motivazione Regalo");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(279, 123, 201, 28);
		contentPane.add(lblNewLabel);
		
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
		btnConferma.setForeground(Color.WHITE);
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 16));
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setBounds(311, 314, 147, 54);
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false); 
		contentPane.add(btnConferma);
	}

}
