package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListaAnnunci extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaAnnunci frame = new ListaAnnunci();
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
	public ListaAnnunci() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ricerca annuncio");
		setBounds(100, 100, 835, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		String[] tipologie= {"Seleziona una tipologia", "Vendita", "Scambio", "Regalo"};
		JComboBox comboBoxTipologia = new JComboBox(tipologie);
		comboBoxTipologia.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		comboBoxTipologia.setBounds(106, 175, 258, 34);
		comboBoxTipologia.setBackground(new Color(45, 134, 192));
		contentPane.add(comboBoxTipologia);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 811, 98);
		panel.setBackground(new Color(45, 134, 192));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista Annunci");
		lblNewLabel.setBounds(333, 43, 179, 31);
		lblNewLabel.setFont(new Font("Verdana Pro Black", Font.BOLD, 21));
		panel.add(lblNewLabel);
		
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
		btnUndo.setIcon(new ImageIcon(ListaAnnunci.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBounds(10, 10, 47, 78);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(ListaAnnunci.class.getResource("/icons/icons8-lista-48.png")));
		lblNewLabel_1.setBounds(657, 10, 45, 78);
		panel.add(lblNewLabel_1);
		

		
		String[] categorie= {"Seleziona una categoria", "Libri", "Musica", "Sport", "Altro"};
		JComboBox comboBoxCategoria = new JComboBox(categorie);
		comboBoxCategoria.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));

		comboBoxCategoria.setBackground(new Color(45, 134, 192));
		comboBoxCategoria.setBounds(526, 175, 258, 34);
		contentPane.add(comboBoxCategoria);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblTipologia.setBounds(136, 141, 143, 27);
		contentPane.add(lblTipologia);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblCategoria.setBounds(555, 141, 143, 27);
		contentPane.add(lblCategoria);
		
		JButton btnInviaOfferta = new JButton("Invia Offerta\r\n");
		btnInviaOfferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					String scelta=(String) comboBoxTipologia.getSelectedItem();
					
				if(scelta.equals("Scambio"))
						{
					setVisible(false);
					OffertaScambio OffertaScambioFrame = new OffertaScambio();
					OffertaScambioFrame.setVisible(true);
					OffertaScambioFrame.setLocationRelativeTo(null);
						}else if (scelta.equals("Vendita")) {
							setVisible(false);
							OffertaVendita OffertaVenditaFrame = new OffertaVendita();
							OffertaVenditaFrame.setVisible(true);
							OffertaVenditaFrame.setLocationRelativeTo(null);
							
							
						}else if (scelta.equals("Regalo")) {
							setVisible(false);
							OffertaRegalo OffertaRegaloFrame = new OffertaRegalo();
							OffertaRegaloFrame.setVisible(true);
							OffertaRegaloFrame.setLocationRelativeTo(null);
							
						}
				
				
				
			}
		});
		btnInviaOfferta.setForeground(new Color(255, 255, 255));
		btnInviaOfferta.setBackground(new Color(0, 52, 101));
		btnInviaOfferta.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		btnInviaOfferta.setBounds(100, 404, 209, 62);
		btnInviaOfferta.setFocusPainted(false);
		btnInviaOfferta.setBorderPainted(false);
		
		contentPane.add(btnInviaOfferta);
		
		JButton btnVisualizza = new JButton("Visualizza ");
		btnVisualizza.setForeground(Color.WHITE);
		btnVisualizza.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		btnVisualizza.setBackground(new Color(0, 52, 101));
		btnVisualizza.setBounds(520, 404, 209, 62);
		btnVisualizza.setFocusPainted(false);
		btnVisualizza.setBorderPainted(false);
		
		contentPane.add(btnVisualizza);
		
	
	}

}
