package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.StoricoOfferteDAO;
import dao.TransazioniDAO;
import entity.Offerta_entity;
import entity.Transazione_entity;
import entity.Utente_entity;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class StoricoOfferte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utente_entity UtenteLoggato;
	 private DefaultTableModel modelTabella;
	   private JTable tabellaOfferta;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StoricoOfferte frame = new StoricoOfferte();
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
	public StoricoOfferte(Utente_entity UtenteLoggato) {
		this.UtenteLoggato = UtenteLoggato;
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
		btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
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
		lblStoricoOfferte.setFont(new Font("Verdana", Font.BOLD, 20));
		lblStoricoOfferte.setBounds(288, 29, 217, 46);
		panel.add(lblStoricoOfferte);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnModifica.setBackground(new Color(0, 52, 101));
		btnModifica.setForeground(new Color(255, 255, 255));
		btnModifica.setFont(new Font("Verdana", Font.BOLD, 16));
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
		AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
		AreaUtenteFrame.setVisible(true);
				}
				else if(scelta==JOptionPane.NO_OPTION) {
			
				JOptionPane.showMessageDialog(null, "Ritiro non avvenuto", null, JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				AreaUtente AreaUtenteFrame = new AreaUtente(UtenteLoggato);
				AreaUtenteFrame.setVisible(true);
				}
			}
		});
		btnRitira.setForeground(Color.WHITE);
		btnRitira.setFont(new Font("Verdana", Font.BOLD, 16));
		btnRitira.setBackground(new Color(0, 52, 101));
		btnRitira.setBounds(463, 291, 179, 40);
		btnRitira.setFocusPainted(false);
		btnRitira.setBorderPainted(false); 
		contentPane.add(btnRitira);
	
		  JPanel panelCentrale = new JPanel();
	        panelCentrale.setBackground(Color.WHITE);
	        contentPane.add(panelCentrale, BorderLayout.CENTER);
	        
	        // Creazione modello tabella
	        modelTabella = new DefaultTableModel(
	            new Object[][]{},
	            new String[]{"Id Offerta", "Stato ", "Matricola Acquirente", 
	                        "Id Annuncio", "Tipologia"} ) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };

	        // Creazione tabella
	        tabellaOfferta = new JTable(modelTabella);
	        tabellaOfferta.setBackground(Color.WHITE);
	        tabellaOfferta.getTableHeader().setReorderingAllowed(false);

	        JScrollPane scrollPane = new JScrollPane(tabellaOfferta);
	        scrollPane.setBounds(39, 230, 1106, 238);
	        contentPane.add(scrollPane);
	        caricaOfferte();
	}
	
	 
    private void caricaOfferte() {
        try {
            StoricoOfferteDAO dao = new StoricoOfferteDAO();
            ArrayList<Offerta_entity> lista = dao.getOfferte(UtenteLoggato.getMatricola());

            for (Offerta_entity t : lista) {
                modelTabella.addRow(new Object[]{
                		t.getIdOfferta(),
                    t.getStatoOfferta(),
                    t.getMatricolaAcquirente(),
                         t.getIdAnnuncio(),
                    t.getTipologiaOfferta(),
                   
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Errore nel caricamento delle offerte",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
	
	
	
	
	
	
	
	

}
