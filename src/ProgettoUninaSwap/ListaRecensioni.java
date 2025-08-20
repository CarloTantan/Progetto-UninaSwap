package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaRecensioni extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaRecensioni frame = new ListaRecensioni();
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
	public ListaRecensioni() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(10, 0, 767, 75);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblListaRecensioni = new JLabel("Lista Recensioni");
		lblListaRecensioni.setFont(new Font("Verdana Pro Black", Font.BOLD, 20));
		lblListaRecensioni.setBounds(297, 20, 227, 32);
		panel.add(lblListaRecensioni);
		
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
		btnUndo.setIcon(new ImageIcon(ListaRecensioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(0, 0, 85, 75);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JButton btnRecensioniInviate = new JButton("Recensioni Inviate");
		btnRecensioniInviate.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		btnRecensioniInviate.setBackground(new Color(0, 52, 101));
		btnRecensioniInviate.setForeground(new Color(255, 255, 255));
		btnRecensioniInviate.setBounds(63, 154, 250, 61);
		btnRecensioniInviate.setFocusPainted(false);
		btnRecensioniInviate.setBorderPainted(false);
		contentPane.add(btnRecensioniInviate);
		
		JButton btnRecensioniRicevute = new JButton("Recensioni Ricevute");
		btnRecensioniRicevute.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		btnRecensioniRicevute.setForeground(Color.WHITE);
		btnRecensioniRicevute.setBackground(new Color(0, 52, 101));
		btnRecensioniRicevute.setBounds(457, 154, 263, 61);
		btnRecensioniRicevute.setFocusPainted(false);
		btnRecensioniRicevute.setBorderPainted(false);
		contentPane.add(btnRecensioniRicevute);
	}
}
