package boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class OffertaScambio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OffertaScambio frame = new OffertaScambio();
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
	public OffertaScambio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaScambio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Scambio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelImg = new JLabel("");
		LabelImg.setBackground(new Color(0, 52, 102));
		LabelImg.setBounds(295, 84, 183, 99);
		contentPane.add(LabelImg);
		
		
		JButton ButtonImgOggetto = new JButton("Oggetto");
		ButtonImgOggetto.setForeground(new Color(255, 255, 255));
		ButtonImgOggetto.setBackground(new Color(0, 52, 101));
		ButtonImgOggetto.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonImgOggetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser scegliImg = new JFileChooser();
		        scegliImg.setDialogTitle("Seleziona immagine");
		        scegliImg.setFileFilter(new FileNameExtensionFilter("Immagini PNG", "png"));

		        if (scegliImg.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		            ImageIcon iconaImg = new ImageIcon(scegliImg.getSelectedFile().getAbsolutePath());
		            Image img = iconaImg.getImage();
		            LabelImg.setIcon(new ImageIcon(img));
		        }
			}
			
		});
		ButtonImgOggetto.setFont(new Font("Verdana", Font.BOLD, 16));
		ButtonImgOggetto.setBounds(123, 122, 105, 37);
		ButtonImgOggetto.setFocusPainted(false);
		ButtonImgOggetto.setBorderPainted(false);
		contentPane.add(ButtonImgOggetto);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 768, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblOffertaScambio = new JLabel("Offerta Scambio");
		lblOffertaScambio.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOffertaScambio.setBounds(292, 22, 215, 42);
		panel.add(lblOffertaScambio);
		
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
		btnUndo.setIcon(new ImageIcon(OffertaScambio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 59, 54);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
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
		btnConferma.setFont(new Font("Verdana", Font.BOLD, 16));
		btnConferma.setBackground(new Color(0, 52, 101));
		btnConferma.setForeground(new Color(255, 255, 255));
		btnConferma.setBounds(295, 346, 147, 54);
		btnConferma.setFocusPainted(false);
		btnConferma.setBorderPainted(false);
		contentPane.add(btnConferma);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.BOLD, 16));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(0, 52, 101));
		textArea.setBounds(276, 206, 193, 99);
		contentPane.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Descrizione");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(309, 178, 129, 18);
		contentPane.add(lblNewLabel);
		
	
		
		
		
	}
}
