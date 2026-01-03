package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


import mainController.MainController;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
/**
 * Classe che rappresenta la finestra per creare o modificare un'offerta di scambio.
 * Permette agli utenti di proporre un oggetto in cambio di quello presente nell'annuncio.
 * La finestra supporta sia la creazione di nuove offerte di scambio che la modifica
 * di offerte esistenti.
 */
public class OffertaScambio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textAreaOggettoProposto;
    private JButton btnConferma;
    private MainController controller;
    private int idAnnuncioScelto;
    private int idOffertaDaModificare = -1;
    private boolean isModificaMode = false;

    public OffertaScambio(MainController controller) {
        this.controller = controller;
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(OffertaScambio.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setTitle("Scambio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(850, 600));
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        // HEADER:Pannello superiore con colore blu e titolo 
        JPanel header = new JPanel();
        header.setBackground(new Color(45, 134, 192));
        header.setLayout(null);
        header.setPreferredSize(new Dimension(0, 94));
        contentPane.add(header, BorderLayout.NORTH);
        
        JLabel lblOffertaScambio = new JLabel("Offerta Scambio");
        lblOffertaScambio.setFont(new Font("Verdana", Font.BOLD, 24));
        lblOffertaScambio.setForeground(Color.WHITE);
        lblOffertaScambio.setBounds(0, 0, 300, 94);
        lblOffertaScambio.setHorizontalAlignment(JLabel.CENTER);
        header.add(lblOffertaScambio);
        
        // Posizionamento centrato del titolo
        header.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                int headerWidth = header.getWidth();
                lblOffertaScambio.setBounds((headerWidth - 300) / 2, 10, 300, 79);
            }
        });
        
        JButton btnUndo = new JButton("");
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.apriListaAnnunci();
            }
        });
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setIcon(new ImageIcon(OffertaScambio.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
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
        
        // PANNELLO CENTRALE : Pannello contenente il form per l'offerta di scambio
        JPanel panelCentrale = new JPanel();
        panelCentrale.setBackground(Color.WHITE);
        panelCentrale.setLayout(new GridBagLayout());
        contentPane.add(panelCentrale, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Label Oggetto
        JLabel lblNewLabel = new JLabel("Oggetto proposto");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        panelCentrale.add(lblNewLabel, gbc);
        
        // TextArea Oggetto con ScrollPane
        textAreaOggettoProposto = new JTextArea();
        textAreaOggettoProposto.setFont(new Font("Verdana", Font.PLAIN, 16));
        textAreaOggettoProposto.setLineWrap(true);
        textAreaOggettoProposto.setWrapStyleWord(true);
        textAreaOggettoProposto.setRows(2);
        
        JScrollPane scrollPane = new JScrollPane(textAreaOggettoProposto);
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
        btnConferma.setFont(new Font("Verdana", Font.BOLD, 18));
        btnConferma.setBackground(new Color(0, 52, 101));
        btnConferma.setForeground(Color.WHITE);
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

    // ==================== METODI PUBBLICI PER CONFIGURAZIONE ====================
   // Imposta l'ID dell'annuncio per cui si sta facendo l'offerta di scambio.
    public void setIdAnnuncio(int idAnnuncio) {
        this.idAnnuncioScelto = idAnnuncio;
    }
    //Carica i dati di un'offerta di scambio esistente per permetterne la modifica.
    public void caricaOffertaPerModifica(int idOfferta) {
        String risultato = controller.caricaOffertaScambioPerModifica(idOfferta);
        
        if (risultato.startsWith("SUCCESS:")) {
            // Estrae i dati dal risultato
            String dati = risultato.substring(8);
            textAreaOggettoProposto.setText(dati);
            
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

  
  //  Metodo che gestisce la conferma dell'offerta di scambio.
    private void confermaOfferta() {
        String oggettoProposto = textAreaOggettoProposto.getText().trim();
        String risultato;
        
        if (isModificaMode) {
            risultato = controller.aggiornaOffertaScambio(
                oggettoProposto,
                idAnnuncioScelto,
                idOffertaDaModificare
            );
        } else {
            risultato = controller.inviaOffertaScambio(
                oggettoProposto,
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
}