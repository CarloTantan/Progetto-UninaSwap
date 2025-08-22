package ProgettoUninaSwap;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JTree;
import java.awt.Toolkit;

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
	public Annuncio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Annuncio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Crea la tua inserzione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 638);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JTextField textfieldTitolo = new JTextField();
		textfieldTitolo.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		textfieldTitolo.setForeground(new Color(255, 255, 255));
		textfieldTitolo.setBackground(new Color(0, 52, 102));
		textfieldTitolo.setBounds(251, 198, 203, 22);
		contentPane.add(textfieldTitolo);
		
		JTextField textAreaModConsegna = new JTextField();
		textAreaModConsegna.setFont(new Font("Verdana", Font.BOLD, 16));
		textAreaModConsegna.setForeground(new Color(255, 255, 255));
		textAreaModConsegna.setBackground(new Color(0, 52, 102));
		textAreaModConsegna.setBounds(251, 399, 203, 22);
		contentPane.add(textAreaModConsegna);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(36, 194, 179, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrizione");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(36, 266, 170, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fascia Oraria");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_2.setBounds(36, 350, 135, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Modalità di consegna");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_3.setBounds(36, 398, 205, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria oggetto");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_5.setBounds(36, 453, 179, 22);
		contentPane.add(lblNewLabel_5);
		
		JButton JScambio = new JButton("Scambio");
		JScambio.setHorizontalAlignment(SwingConstants.RIGHT);
		JScambio.setForeground(new Color(255, 255, 255));
		JScambio.setToolTipText("");
		JScambio.setBackground(new Color(0, 52, 102));
		JScambio.setFont(new Font("Verdana", Font.BOLD, 16));
		JScambio.setBounds(36, 514, 122, 56);
		contentPane.add(JScambio);
		JScambio.setFocusPainted(false);
		JScambio.setBorderPainted(false);
	
		
		JButton JRegalo = new JButton("Regalo");
		JRegalo.setHorizontalAlignment(SwingConstants.RIGHT);
		JRegalo.setForeground(new Color(255, 255, 255));
		JRegalo.setBackground(new Color(0, 52, 102));
		JRegalo.setFont(new Font("Verdana", Font.BOLD, 16));
		JRegalo.setBounds(187, 514, 122, 56);
		contentPane.add(JRegalo);
		JRegalo.setFocusPainted(false);
		JRegalo.setBorderPainted(false);
		
		JButton JVendita = new JButton("Vendita");
		JVendita.setHorizontalAlignment(SwingConstants.RIGHT);
		JVendita.setForeground(new Color(255, 255, 255));
		JVendita.setBackground(new Color(0, 52, 102));
		JVendita.setFont(new Font("Verdana", Font.BOLD, 16));
		JVendita.setBounds(342, 514, 135, 56);
		contentPane.add(JVendita);
		JVendita.setFocusPainted(false);
		JVendita.setBorderPainted(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 536, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton ButtonAnnulla = new JButton("");
		ButtonAnnulla.setBounds(0, 0, 46, 58);
		panel.add(ButtonAnnulla);
		ButtonAnnulla.setBackground(new Color(45, 134, 192));
		ButtonAnnulla.setIcon(new ImageIcon(Annuncio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		ButtonAnnulla.setFocusPainted(false);
		ButtonAnnulla.setBorderPainted(false);
		
		
		ButtonAnnulla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				AreaUtente utenteFrame = new AreaUtente(); 
				utenteFrame.setVisible(true);
			}
		}); 
		
		JLabel lblNewLabel_6 = new JLabel("Annuncio");
		lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_6.setBounds(234, 0, 106, 58);
		panel.add(lblNewLabel_6);
		
		JLabel LabelImg = new JLabel("");
		LabelImg.setFont(new Font("Verdana", Font.BOLD, 16));
		LabelImg.setForeground(new Color(255, 255, 255));
		LabelImg.setLabelFor(this);
		LabelImg.setBackground(new Color(0, 52, 102));
		LabelImg.setBounds(251, 77, 203, 77);
		contentPane.add(LabelImg);
		
		JButton ButtonImgOggetto = new JButton("Oggetto");
		ButtonImgOggetto.setForeground(new Color(255, 255, 255));
		ButtonImgOggetto.setBackground(new Color(0, 52, 102));
		ButtonImgOggetto.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonImgOggetto.addActionListener(new ActionListener() {
			@Override 
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
		ButtonImgOggetto.setBounds(21, 77, 194, 36);
		contentPane.add(ButtonImgOggetto);
		ButtonImgOggetto.setFocusPainted(false);
		ButtonImgOggetto.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(251, 242, 203, 77);
		contentPane.add(scrollPane);
		
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setForeground(new Color(255, 255, 255));
		textAreaDescrizione.setBackground(new Color(0, 52, 102));
		textAreaDescrizione.setFont(new Font("Verdana", Font.BOLD, 16));
		textAreaDescrizione.setLineWrap(true);  
		textAreaDescrizione.setWrapStyleWord(true);
		textAreaDescrizione.setRows(4);
		textAreaDescrizione.setColumns(20);
		scrollPane.setViewportView(textAreaDescrizione);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		String[] categorie= {"Seleziona una categoria", "Libri", "Musica", "Sport", "Altro"};
		JComboBox comboBoxCategoria = new JComboBox<>(categorie);
		comboBoxCategoria.setForeground(new Color(255, 255, 255));
		comboBoxCategoria.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxCategoria.setBackground(new Color(0, 52, 102));
		comboBoxCategoria.setBounds(251, 447, 203, 28);
		contentPane.add(comboBoxCategoria);
		
		String[] fasciaOraria = {"Seleziona una fascia oraria", "8:00 - 10:30", "11:00 - 13:30","15:00 - 17:30", "18:00 - 20:30"};
		JComboBox comboBoxFasciaOraria = new JComboBox<>(fasciaOraria);
		comboBoxFasciaOraria.setForeground(new Color(255, 255, 255));
		comboBoxFasciaOraria.setBackground(new Color(0, 52, 102));
		comboBoxFasciaOraria.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxFasciaOraria.setBounds(251, 347, 203, 22);
		contentPane.add(comboBoxFasciaOraria);

		JScambio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						LabelImg.getIcon()==null 
					) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else if (comboBoxCategoria.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "Seleziona un punteggio", "Punteggio mancante", JOptionPane.WARNING_MESSAGE);
				}{
					setVisible(false);
					AnnuncioScambio annuncioScambioFrame = new AnnuncioScambio();
					annuncioScambioFrame.setVisible(true);
					annuncioScambioFrame.setLocationRelativeTo(null);
				}
			}
		});
		
		JRegalo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						LabelImg.getIcon()==null 				
					) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					AnnuncioRegalo annuncioRegaloFrame = new AnnuncioRegalo();
					annuncioRegaloFrame.setVisible(true);
					annuncioRegaloFrame.setLocationRelativeTo(null);
				}
				
			}
		});
		
		JVendita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldTitolo.getText().trim().isEmpty() || 
						textAreaDescrizione.getText().trim().isEmpty() || 
						textAreaModConsegna.getText().trim().isEmpty() || 
						LabelImg.getIcon()==null 
					) {
					JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori", "Campi mancanti", JOptionPane.WARNING_MESSAGE);
				} else {
					setVisible(false);
					AnnuncioVendita annuncioVenditaFrame = new AnnuncioVendita();
					annuncioVenditaFrame.setVisible(true);
					annuncioVenditaFrame.setLocationRelativeTo(null);
				}
			}
		});
	}
}
