package ProgettoUninaSwap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class InserimentoRecensione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserimentoRecensione frame = new InserimentoRecensione();
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
	public InserimentoRecensione() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoRecensione.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Scrivi la tua recensione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(46, 132, 191));
		panel.setBounds(0, 0, 577, 64);
		contentPane.add(panel);
		
		JButton btnIndietro = new JButton("");
		btnIndietro.setFont(new Font("Verdana", Font.BOLD, 16));
		btnIndietro.setIcon(new ImageIcon(InserimentoRecensione.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBorderPainted(false);
		btnIndietro.setBackground(new Color(46, 132, 191));
		btnIndietro.setBounds(10, 10, 39, 44);
		panel.add(btnIndietro);
		btnIndietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AreaUtente areaUtenteFrame = new AreaUtente();
				areaUtenteFrame.setVisible(true);
			}
		});
		
		JLabel lblInserisciUnaRecensione = new JLabel("Lascia una recensione");
		lblInserisciUnaRecensione.setFont(new Font("Verdana", Font.BOLD, 20));
		lblInserisciUnaRecensione.setBounds(210, 4, 275, 54);
		panel.add(lblInserisciUnaRecensione);
		
		JLabel lblPunteggio = new JLabel("Punteggio");
		lblPunteggio.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblPunteggio.setBounds(107, 91, 111, 26);
		contentPane.add(lblPunteggio);
		
		String[] punteggi= {"Seleziona punteggio", "1", "2", "3", "4", "5"};
		JComboBox comboBoxPunteggi = new JComboBox<>(punteggi);
		comboBoxPunteggi.setForeground(new Color(0, 0, 0));
		comboBoxPunteggi.setBackground(new Color(255, 255, 255));
		comboBoxPunteggi.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxPunteggi.setBounds(228, 92, 233, 26);
		contentPane.add(comboBoxPunteggi);
		
		JLabel lblCommento = new JLabel("Commento");
		lblCommento.setFont(new Font("Verdana Pro Black", Font.BOLD, 16));
		lblCommento.setBounds(107, 146, 111, 26);
		contentPane.add(lblCommento);
		
		JTextArea textAreaCommento = new JTextArea();
		textAreaCommento.setForeground(new Color(0, 0, 0));
		textAreaCommento.setRows(8);
		textAreaCommento.setColumns(90);
		textAreaCommento.setWrapStyleWord(true);
		textAreaCommento.setLineWrap(true);
		textAreaCommento.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		textAreaCommento.setBounds(228, 145, 233, 151);
		contentPane.add(textAreaCommento);
		
		JScrollPane scrollPane= new JScrollPane(textAreaCommento);
		scrollPane.setBounds(228, 150, 233, 116);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane);
        
        JButton btnInvia = new JButton("Invia");
        btnInvia.setFocusPainted(false);
        btnInvia.setBorderPainted(false);
        btnInvia.setForeground(new Color(255, 255, 255));
        btnInvia.setBackground(new Color(0, 52, 101));
        btnInvia.setFont(new Font("Verdana", Font.BOLD, 16));
        btnInvia.setBounds(228, 290, 233, 50);
        contentPane.add(btnInvia);
        
        btnInvia.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				
				if (comboBoxPunteggi.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "Seleziona un punteggio", "Punteggio mancante", JOptionPane.WARNING_MESSAGE);
				} else {
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Recensione inviata con successo", "Recensione inviata", JOptionPane.INFORMATION_MESSAGE);
				AreaUtente areaUtenteFrame = new AreaUtente();
				areaUtenteFrame.setVisible(true);
				}
			}
		});

        
	}
}
