package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.OggettoDAO;
import entity.Oggetto_entity;
import entity.Utente_entity;
import enumerations.TipologiaCategoria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Oggetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	private JTextField textFieldNomeOggetto;
	private JTextField textFieldDescrizioneOggetto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Oggetto frame = new Oggetto();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Oggetto(Utente_entity UtenteLoggato) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Oggetto.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
		setTitle("Inserimento Oggetto");
		this.UtenteLoggato = UtenteLoggato;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeOggetto = new JLabel("Nome");
		lblNomeOggetto.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNomeOggetto.setBounds(105, 121, 188, 19);
		contentPane.add(lblNomeOggetto);
		
		JLabel lblDescrizioneOggetto = new JLabel("Descrizione");
		lblDescrizioneOggetto.setFont(new Font("Verdana", Font.BOLD, 16));
		lblDescrizioneOggetto.setBounds(105, 156, 188, 19);
		contentPane.add(lblDescrizioneOggetto);
		
		textFieldNomeOggetto = new JTextField();
		textFieldNomeOggetto.setBounds(310, 115, 160, 25);
		contentPane.add(textFieldNomeOggetto);
		textFieldNomeOggetto.setColumns(10);
		
		textFieldDescrizioneOggetto = new JTextField();
		textFieldDescrizioneOggetto.setColumns(10);
		textFieldDescrizioneOggetto.setBounds(309, 150, 161, 47);
		contentPane.add(textFieldDescrizioneOggetto);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 134, 192));
		panel.setBounds(0, 0, 870, 81);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci il tuo oggetto per pubblicare l'annuncio");
		lblNewLabel.setBounds(157, 10, 502, 61);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JButton btnUndo = new JButton("");
		btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
				AreaUtenteFrame.setVisible(true);
				AreaUtenteFrame.setLocationRelativeTo(null);
			}
		});
		
		
		btnUndo.setIcon(new ImageIcon(AreaUtente.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
		btnUndo.setBackground(new Color(45, 134, 192));
		btnUndo.setBounds(0, 0, 46, 81);
		btnUndo.setFocusPainted(false);
		btnUndo.setBorderPainted(false);
		panel.add(btnUndo);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("Categoria oggetto");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setBounds(105, 217, 179, 22);
		contentPane.add(lblNewLabel_5);
		
		JComboBox<String> comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.addItem("Seleziona una categoria");
		for (TipologiaCategoria categoria : TipologiaCategoria.values()) {
		    comboBoxCategoria.addItem(categoria.getNome());
		}
		comboBoxCategoria.setForeground(new Color(255, 255, 255));
		comboBoxCategoria.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxCategoria.setBackground(new Color(0, 52, 102));
		comboBoxCategoria.setBounds(310, 207, 203, 28);
		contentPane.add(comboBoxCategoria);
		
		JButton btnContinua = new JButton("Continua");
		btnContinua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nome = textFieldNomeOggetto.getText().trim();
		        String descrizione = textFieldDescrizioneOggetto.getText().trim();
		        String categoriaSelezionata = (String) comboBoxCategoria.getSelectedItem();
		        
		        // Validazione input
		        if (nome.isEmpty() || descrizione.isEmpty()) {
		            JOptionPane.showMessageDialog(null, 
		                "Nome e descrizione sono obbligatori", 
		                "Campi mancanti", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        if (categoriaSelezionata == null || categoriaSelezionata.equals("Seleziona una categoria")) {
		            JOptionPane.showMessageDialog(null, 
		                "Seleziona una categoria valida", 
		                "Categoria non valida", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        try {
		            // Ottieni l'ID dalla categoria selezionata
		            TipologiaCategoria categoria = TipologiaCategoria.fromNome(categoriaSelezionata);
		            int idCategoria = categoria.getId();
		            
		            OggettoDAO oggettoDAO = new OggettoDAO();
		            int idOggetto = oggettoDAO.inserisciOggetto(nome, descrizione, idCategoria);
		            
		            	            
		            // Crea l'entità oggetto
		            Oggetto_entity oggetto = new Oggetto_entity(idOggetto, nome, descrizione, idCategoria);
		            
		            // Passa all'interfaccia Annuncio
		            setVisible(false);
		            Annuncio AnnuncioFrame = new Annuncio(UtenteLoggato, oggetto);
		            AnnuncioFrame.setVisible(true);
		            AnnuncioFrame.setLocationRelativeTo(null);
		            
		        } catch (IllegalArgumentException ex) {
		            JOptionPane.showMessageDialog(null, 
		                "Categoria non valida", 
		                "Errore", 
		                JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, 
		                "Errore durante l'inserimento dell'oggetto: " + ex.getMessage(), 
		                "Errore Database", 
		                JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		
		
		btnContinua.setForeground(new Color(255, 255, 255));
		btnContinua.setFont(new Font("Verdana", Font.BOLD, 16));
		btnContinua.setBackground(new Color(0, 52, 102));
		btnContinua.setFocusPainted(false);
		btnContinua.setBorderPainted(false);
		btnContinua.setBounds(545, 318, 161, 63);
		contentPane.add(btnContinua);

	}
}
