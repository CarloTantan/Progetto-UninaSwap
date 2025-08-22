package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class StoricoOfferte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoricoOfferte frame = new StoricoOfferte();
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
	public StoricoOfferte() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StoricoOfferte.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Le tue offerte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 748, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		btnUndo.setIcon(new ImageIcon(StoricoOfferte.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 49, 65);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false); 
		panel.add(btnUndo);
		
		JLabel lblStoricoOfferte = new JLabel("Storico Offerte");
		lblStoricoOfferte.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblStoricoOfferte.setBounds(306, 29, 178, 46);
		panel.add(lblStoricoOfferte);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnModifica.setBackground(new Color(0, 52, 101));
		btnModifica.setForeground(new Color(255, 255, 255));
		btnModifica.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		btnModifica.setBounds(129, 291, 179, 40);
		btnModifica.setFocusPainted(false);
		btnModifica.setBorderPainted(false); 
		contentPane.add(btnModifica);
		
		JButton btnRitira = new JButton("Ritira");
		btnRitira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int scelta= JOptionPane.showConfirmDialog(null, "Conferma"," Vuoi ritirare l'offerta?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE );
				if(scelta==JOptionPane.YES_OPTION)	{
				
		JOptionPane.showMessageDialog(null, "Ritiro avvenuto con successo", null, JOptionPane.INFORMATION_MESSAGE);
		setVisible(false);
		AreaUtente AreaUtenteFrame = new AreaUtente();
		AreaUtenteFrame.setVisible(true);
				}
				else if(scelta==JOptionPane.NO_OPTION) {
			
				JOptionPane.showMessageDialog(null, "Ritiro non avvenuto", null, JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente();
				AreaUtenteFrame.setVisible(true);
				}
			}
		});
		btnRitira.setForeground(Color.WHITE);
		btnRitira.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		btnRitira.setBackground(new Color(0, 52, 101));
		btnRitira.setBounds(463, 291, 179, 40);
		btnRitira.setFocusPainted(false);
		btnRitira.setBorderPainted(false); 
		contentPane.add(btnRitira);
	}

}
