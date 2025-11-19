package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class ListaTransazioni extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaTransazioni frame = new ListaTransazioni();
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
	public ListaTransazioni() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaTransazioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 446, 58);
		panel.setBackground(new Color(70, 171, 225));

		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnUndo = new JButton("");
		btnUndo.setBounds(0, 0, 64, 58);
		btnUndo.setIcon(new ImageIcon(ListaTransazioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		btnUndo.setBackground(new Color(45, 134, 192));
		panel.add(btnUndo);
		
		JLabel lblNewLabel = new JLabel("Transazioni avvenute");
		lblNewLabel.setBounds(116, 10, 214, 25);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

	}
}
