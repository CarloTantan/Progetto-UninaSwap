package boundary;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import mainController.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
/**
 * Classe che rappresenta la finestra per creare o modificare un'offerta di vendita.
 * Permette agli utenti di proporre un importo monetario per acquistare l'oggetto
 * presente nell'annuncio selezionato.
 * La finestra supporta sia la creazione di nuove offerte di vendita che la modifica
 * di offerte esistenti.
 */
public class OffertaVendita extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textAreaImportoProposto;
    private JButton btnConferma;
    private MainController controller;
    private int idAnnuncioScelto;
    private int idOffertaDaModificare = -1;
    private boolean isModificaMode = false;

    public OffertaVendita(MainController controller) {
        this.controller = controller;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaVendita.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Vendita");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(850, 600));
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
     //  HEADER : Pannello superiore con colore blu e titolo
        JPanel header = new JPanel();
        header.setBackground(new Color(45, 134, 192));
        header.setLayout(null);
        header.setPreferredSize(new Dimension(0, 94));
        contentPane.add(header, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Offerta Vendita ");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(0, 0, 300, 94);
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        header.add(lblNewLabel);
        
        // Posizionamento centrato del titolo
        header.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                int headerWidth = header.getWidth();
                lblNewLabel.setBounds((headerWidth - 300) / 2, 10, 300, 79);
            }
        });
        
        JButton btnUndo = new JButton("");
        btnUndo.setFont(new Font("Verdana", Font.BOLD, 16));
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tornaListaAnnunci();
            }
        });
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setIcon(new ImageIcon(OffertaVendita.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBounds(15, 15, 50, 50);
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false); 
        header.add(btnUndo);
        
        btnUndo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnUndo.setBackground(new Color(66, 152, 211));
                btnUndo.setContentAreaFilled(true);
            }
            public void mouseExited(MouseEvent evt) {
                btnUndo.setBackground(new Color(45, 134, 192));
                btnUndo.setContentAreaFilled(false);
            }
        });
        
        //  PANNELLO CENTRALE :Pannello contenente il form per l'offerta di vendita
        JPanel panelCentrale = new JPanel();
        panelCentrale.setBackground(Color.WHITE);
        panelCentrale.setLayout(new GridBagLayout());
        contentPane.add(panelCentrale, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Label Importo
        JLabel lblNewLabel_1 = new JLabel("Proponi un importo");
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        panelCentrale.add(lblNewLabel_1, gbc);
        
        // TextArea Importo con ScrollPane
        textAreaImportoProposto = new JTextArea();
        textAreaImportoProposto.setFont(new Font("Verdana", Font.PLAIN, 16));
        textAreaImportoProposto.setLineWrap(true);
        textAreaImportoProposto.setWrapStyleWord(true);
        textAreaImportoProposto.setRows(1);
        
        JScrollPane scrollPane = new JScrollPane(textAreaImportoProposto);
        scrollPane.setPreferredSize(new Dimension(250, 40));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 20, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        panelCentrale.add(scrollPane, gbc);
        
        // Bottone Conferma
        btnConferma = new JButton("Conferma");
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confermaOfferta();
            }
        });
        btnConferma.setBackground(new Color(0, 52, 101));
        btnConferma.setForeground(Color.WHITE);
        btnConferma.setFont(new Font("Verdana", Font.BOLD, 18));
        btnConferma.setPreferredSize(new Dimension(180, 45));
        btnConferma.setFocusPainted(false);
        btnConferma.setBorderPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panelCentrale.add(btnConferma, gbc);
        getRootPane().setDefaultButton(btnConferma);
        
        btnConferma.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnConferma.setBackground(new Color(0, 70, 140));
            }
            public void mouseExited(MouseEvent evt) {
                btnConferma.setBackground(new Color(0, 52, 101));
            }
        });
    }

    
    //Imposta l'ID dell'annuncio per cui si sta facendo l'offerta di vendita.
    public void setIdAnnuncio(int idAnnuncio) {
        this.idAnnuncioScelto = idAnnuncio;
    }
    //Carica i dati di un'offerta di vendita esistente per permetterne la modifica.
    public void caricaOffertaPerModifica(int idOfferta) {
        String risultato = controller.caricaOffertaVenditaPerModifica(idOfferta);
        
        if (risultato.startsWith("SUCCESS:")) {
            // Estrae i dati dal risultato
            String dati = risultato.substring(8);
            textAreaImportoProposto.setText(dati);
            
            // Recupera l'ID annuncio dall'offerta
            int idAnnuncio = controller.getIdAnnuncioDaOfferta(idOfferta);
            if (idAnnuncio > 0) {
                this.idAnnuncioScelto = idAnnuncio;
            }
            
            idOffertaDaModificare = idOfferta;
            isModificaMode = true;
            btnConferma.setText("Aggiorna");
        } else {
            JOptionPane.showMessageDialog(this, 
                risultato, 
                "Errore", 
                JOptionPane.ERROR_MESSAGE);
        }
    }


    //Metodo che gestisce la conferma dell'offerta di vendita.
    private void confermaOfferta() {
        String importoPropostoString = textAreaImportoProposto.getText().trim();
        String risultato;
        
        if (isModificaMode) {
            risultato = controller.aggiornaOffertaVendita(
                importoPropostoString,
                idAnnuncioScelto,
                idOffertaDaModificare
            );
        } else {
            risultato = controller.inviaOffertaVendita(
                importoPropostoString,
                idAnnuncioScelto
            );
        }
        
        if (risultato.startsWith("SUCCESS:")) {
            String messaggio = isModificaMode ? "Offerta aggiornata" : "Offerta inviata";
            JOptionPane.showMessageDialog(this, 
                messaggio, 
                "Successo", 
                JOptionPane.INFORMATION_MESSAGE);
            
            controller.apriListaAnnunci();
        } else {
            JOptionPane.showMessageDialog(this,
                risultato,
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void tornaListaAnnunci() {
        controller.apriListaAnnunci();
    }
}