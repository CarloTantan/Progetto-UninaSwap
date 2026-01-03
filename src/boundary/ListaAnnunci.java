package boundary;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import enumerations.StatoAnnuncio;
import mainController.MainController;

import java.util.ArrayList;

/**
 * Classe che rappresenta l'interfaccia per visualizzare la lista degli annunci.
 * Permette di filtrare gli annunci per tipologia (Vendita/Scambio/Regalo),
 * categoria e parola chiave, e di visualizzarli in formato card con carosello di foto.
 */
public class ListaAnnunci extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel panelCards;                      
    private JComboBox<String> comboBoxTipologia;    
    private JComboBox<String> comboBoxCategoria;    
    private MainController controller;              
    private JTextField txtRicerca;                   
    
    public ListaAnnunci(MainController controller) {

        this.controller = controller;

        setTitle("Lista Annunci");
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            ListaAnnunci.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        setMinimumSize(new Dimension(1000, 700));  
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // HEADER: Pannello header blu con titolo e pulsante indietro
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 134, 192));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Lista Annunci", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton btnUndo = new JButton(new ImageIcon(
                ListaAnnunci.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setBorderPainted(false);
        btnUndo.setFocusPainted(false);
        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tornaAreaUtente();
            }
        });

        header.add(btnUndo, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);

        //FILTRI : Pannello con i filtri per tipologia, categoria e ricerca
        JPanel filtri = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        filtri.setBackground(Color.WHITE);

         JLabel lblTipologia = new JLabel("Tipologia:");
        lblTipologia.setFont(new Font("Verdana", Font.BOLD, 14));

        String[] tipologie = {"Seleziona una tipologia", "Vendita", "Scambio", "Regalo"};
        comboBoxTipologia = new JComboBox<>(tipologie);
        comboBoxTipologia.setFont(new Font("Verdana", Font.BOLD, 14));
        comboBoxTipologia.setBackground(new Color(45, 134, 192));
        comboBoxTipologia.setForeground(Color.WHITE);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Verdana", Font.BOLD, 14));

        String[] categorie = {"Seleziona una categoria", "Libri", "Musica", "Sport",
                "Cancelleria", "Vestiti", "Elettronica", "Giochi", "Casa", "Altro"};

        comboBoxCategoria = new JComboBox<>(categorie);
        comboBoxCategoria.setFont(new Font("Verdana", Font.BOLD, 14));
        comboBoxCategoria.setBackground(new Color(45, 134, 192));
        comboBoxCategoria.setForeground(Color.WHITE);

        JLabel lblRicerca = new JLabel("Cerca:");
        lblRicerca.setFont(new Font("Verdana", Font.BOLD, 14));
        
        txtRicerca = new JTextField(20);
        txtRicerca.setFont(new Font("Verdana", Font.PLAIN, 14));

        JButton btnVisualizza = new JButton("Visualizza");
        btnVisualizza.setFont(new Font("Verdana", Font.BOLD, 16));
        btnVisualizza.setBackground(new Color(0, 52, 101));
        btnVisualizza.setForeground(Color.WHITE);
        btnVisualizza.setBorderPainted(false);
        btnVisualizza.setFocusPainted(false);
        btnVisualizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestisciVisualizza();
            }
        });

        filtri.add(lblTipologia);
        filtri.add(comboBoxTipologia);
        filtri.add(lblCategoria);
        filtri.add(comboBoxCategoria);
        filtri.add(lblRicerca);
        filtri.add(txtRicerca);
        filtri.add(btnVisualizza);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(header, BorderLayout.NORTH);
        topPanel.add(filtri, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        panelCards = new JPanel(new GridLayout(0, 3, 15, 15));
        panelCards.setBackground(Color.WHITE);
        panelCards.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(panelCards);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);  
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
     // Gestisce il click sul pulsante Visualizza.
     private void gestisciVisualizza() {
        String tipologia = (String) comboBoxTipologia.getSelectedItem();
        String categoria = (String) comboBoxCategoria.getSelectedItem();
        String ricerca = txtRicerca.getText().trim();

        if (tipologia.equals("Seleziona una tipologia")) {
            JOptionPane.showMessageDialog(this, 
                "Seleziona una tipologia!", 
                "Attenzione", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String risultato = controller.caricaAnnunci(tipologia, categoria, ricerca);
        
        if (risultato.startsWith("ERRORE:")) {
            JOptionPane.showMessageDialog(this,
                risultato.substring(7),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
            risultato,
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);

        aggiornaVisualizzazione(tipologia);
    }

      // Aggiorna la visualizzazione degli annunci in base alla tipologia selezionata.
    private void aggiornaVisualizzazione(String tipologia) {
        if (tipologia.equals("Vendita")) {
            ArrayList<AnnuncioVendita_entity> annunci = controller.getAnnunciVenditaCaricati();
            mostraAnnunciVendita(annunci);
        } else if (tipologia.equals("Scambio")) {
            ArrayList<AnnuncioScambio_entity> annunci = controller.getAnnunciScambioCaricati();
            mostraAnnunciScambio(annunci);
        } else if (tipologia.equals("Regalo")) {
            ArrayList<AnnuncioRegalo_entity> annunci = controller.getAnnunciRegaloCaricati();
            mostraAnnunciRegalo(annunci);
        }
    }
 
     // Mostra gli annunci di vendita creando una card per ciascuno.
     private void mostraAnnunciVendita(ArrayList<AnnuncioVendita_entity> annunci) {
        panelCards.removeAll();  // Rimuove le card precedenti
        
        for (AnnuncioVendita_entity a : annunci) {
            panelCards.add(new AnnuncioCard(
                    a.getIdAnnuncio(),
                    a.getMatricolaVenditore(),
                    a.getTitolo(),
                    a.getDescrizione(),
                    "€ " + String.format("%.2f", a.getPrezzoVendita()),  
                    a.getStatoAnnuncio(),
                    a.getTipologiaCategoria().toString(),
                    "Vendita"
            ));
        }
        
        panelCards.revalidate();
        panelCards.repaint();
    }

        
     // Mostra gli annunci di regalo creando una card per ciascuno.
     private void mostraAnnunciRegalo(ArrayList<AnnuncioRegalo_entity> annunci) {
        panelCards.removeAll();
        
        for (AnnuncioRegalo_entity a : annunci) {
            panelCards.add(new AnnuncioCard(
                    a.getIdAnnuncio(),
                    a.getMatricolaVenditore(),
                    a.getTitolo(),
                    a.getDescrizione(),
                    a.getMotivoCessione(),  
                    a.getStatoAnnuncio(),
                    a.getTipologiaCategoria().toString(),
                    "Regalo"
            ));
        }
        
        panelCards.revalidate();
        panelCards.repaint();
    }


     // Mostra gli annunci di scambio creando una card per ciascuno.
     private void mostraAnnunciScambio(ArrayList<AnnuncioScambio_entity> annunci) {
        panelCards.removeAll();
        
        for (AnnuncioScambio_entity a : annunci) {
            panelCards.add(new AnnuncioCard(
                    a.getIdAnnuncio(),
                    a.getMatricolaVenditore(),
                    a.getTitolo(),
                    a.getDescrizione(),
                    a.getOggettoRichiesto(),  
                    a.getStatoAnnuncio(),
                    a.getTipologiaCategoria().toString(),
                    "Scambio"
            ));
        }
        
        panelCards.revalidate();
        panelCards.repaint();
    }

    

     // Classe interna che rappresenta una singola card di annuncio.
    class AnnuncioCard extends JPanel {
    	
    	private static final long serialVersionUID = 1L;

               public AnnuncioCard(int id, String venditore, String titolo,
                            String descrizione, String extra, StatoAnnuncio stato,
                            String categoria, String tipologia) {

            setBorder(new CompoundBorder(
                    new LineBorder(Color.LIGHT_GRAY, 1, true),
                    new EmptyBorder(10, 10, 10, 10)
            ));
            setLayout(new BorderLayout(10, 10));
            setBackground(Color.WHITE);

            JPanel caroselloPanel = creaCaroselloFoto(id);
            add(caroselloPanel, BorderLayout.NORTH);

            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            info.setBackground(Color.WHITE);

            JLabel lblTitolo = new JLabel(titolo);
            lblTitolo.setFont(new Font("Verdana", Font.BOLD, 15));
            lblTitolo.setAlignmentX(Component.LEFT_ALIGNMENT);

            JTextArea txtDescrizione = new JTextArea(descrizione);
            txtDescrizione.setFont(new Font("Verdana", Font.PLAIN, 12));
            txtDescrizione.setOpaque(false);
            txtDescrizione.setLineWrap(true);
            txtDescrizione.setWrapStyleWord(true);
            txtDescrizione.setEditable(false);
            txtDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblExtra = new JLabel(extra);
            lblExtra.setFont(new Font("Verdana", Font.BOLD, 13));
            lblExtra.setForeground(new Color(0, 102, 204));
            lblExtra.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel venditorePanel = creaInfoVenditore(venditore);
            venditorePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JButton btn = new JButton(stato == StatoAnnuncio.Chiuso ? "Chiuso" : "Invia Offerta");
            btn.setFont(new Font("Verdana", Font.BOLD, 14));
            btn.setBackground(new Color(0, 52, 101));
            btn.setForeground(Color.WHITE);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setEnabled(stato != StatoAnnuncio.Chiuso);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 35));
            if (stato == StatoAnnuncio.Chiuso) {
                btn.setBackground(Color.GRAY);
            }

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    apriOfferta(id, venditore, stato, tipologia);
                }
            });

            info.add(lblTitolo);
            info.add(Box.createVerticalStrut(8));
            info.add(txtDescrizione);
            info.add(Box.createVerticalStrut(10));
            info.add(lblExtra);
            info.add(Box.createVerticalStrut(10));
            info.add(venditorePanel);
            info.add(Box.createVerticalStrut(15));
            info.add(btn);

            add(info, BorderLayout.CENTER);
        }
    }
    
    
    
     // Crea il pannello con le informazioni del venditore.
     
    private JPanel creaInfoVenditore(String matricolaVenditore) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        
        String nominativo = controller.getNominativoVenditore(matricolaVenditore);
        double media = controller.getValutazioneMediaVenditore(matricolaVenditore);
        int numRecensioni = controller.getNumeroRecensioniVenditore(matricolaVenditore);
        
        JLabel lblVenditore = new JLabel(nominativo != null ? nominativo : "Venditore");
        lblVenditore.setFont(new Font("Verdana", Font.BOLD, 12));
        lblVenditore.setForeground(new Color(0, 102, 204));
        lblVenditore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        final String nomeFinale = nominativo != null ? nominativo : "Venditore";
        lblVenditore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblVenditore.setText("<html><u>" + nomeFinale + "</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                lblVenditore.setText(nomeFinale);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            	controller.apriVisualizzaRecensioniVenditore(matricolaVenditore);
            }
        });
        
        panel.add(lblVenditore);
        
        if (numRecensioni > 0) {
            ImageIcon iconaStella = new ImageIcon(
                ListaAnnunci.class.getResource("/icons/icons8-stella-32.png"));
            Image imgScalata = iconaStella.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            JLabel lblStella = new JLabel(new ImageIcon(imgScalata));
            panel.add(lblStella);
            
            JLabel lblRating = new JLabel(String.format("%.1f (%d)", media, numRecensioni));
            lblRating.setFont(new Font("Verdana", Font.PLAIN, 11));
            lblRating.setForeground(new Color(100, 100, 100));
            panel.add(lblRating);
        } else {
            JLabel lblNoRating = new JLabel("(Nessuna recensione)");
            lblNoRating.setFont(new Font("Verdana", Font.ITALIC, 10));
            lblNoRating.setForeground(Color.GRAY);
            panel.add(lblNoRating);
        }
        
        return panel;
    }

     // Crea il carosello delle foto dell'annuncio con frecce di navigazione.
     private JPanel creaCaroselloFoto(int idAnnuncio) {
        JPanel caroselloPanel = new JPanel(new BorderLayout());
        caroselloPanel.setPreferredSize(new Dimension(200, 200));
        caroselloPanel.setBackground(new Color(240, 240, 240));
        
        JLabel lblImmagine = new JLabel();
        lblImmagine.setPreferredSize(new Dimension(200, 170));
        lblImmagine.setHorizontalAlignment(SwingConstants.CENTER);
        lblImmagine.setVerticalAlignment(SwingConstants.CENTER);
        lblImmagine.setOpaque(true);
        lblImmagine.setBackground(new Color(240, 240, 240));
        
        JPanel controlliPanel = new JPanel(new BorderLayout());
        controlliPanel.setBackground(Color.WHITE);
        controlliPanel.setPreferredSize(new Dimension(200, 30));
        
        JLabel lblContatore = new JLabel("", SwingConstants.CENTER);
        lblContatore.setFont(new Font("Verdana", Font.BOLD, 12));
        lblContatore.setForeground(new Color(0, 52, 101));
        
        JButton btnPrev = new JButton(new ImageIcon(
            ListaAnnunci.class.getResource("/icons/icons8-arrow-pointing-left-24.png")));
        btnPrev.setBackground(new Color(45, 134, 192));
        btnPrev.setBorderPainted(false);
        btnPrev.setFocusPainted(false);
        btnPrev.setPreferredSize(new Dimension(50, 30));
        
        JButton btnNext = new JButton(new ImageIcon(
            ListaAnnunci.class.getResource("/icons/icons8-arrow-24.png")));
        btnNext.setBackground(new Color(45, 134, 192));
        btnNext.setBorderPainted(false);
        btnNext.setFocusPainted(false);
        btnNext.setPreferredSize(new Dimension(50, 30));
        
        controlliPanel.add(btnPrev, BorderLayout.WEST);
        controlliPanel.add(lblContatore, BorderLayout.CENTER);
        controlliPanel.add(btnNext, BorderLayout.EAST);
        
        caroselloPanel.add(lblImmagine, BorderLayout.CENTER);
        caroselloPanel.add(controlliPanel, BorderLayout.SOUTH);
        
        ArrayList<String> percorsi = controller.getFotoAnnuncio(idAnnuncio);
        
        if (percorsi != null && !percorsi.isEmpty()) {
            final int[] indiceCorrente = {0};
            
            Runnable aggiornaImmagine = () -> {
                String percorsoFoto = percorsi.get(indiceCorrente[0]);
                ImageIcon iconaFoto = controller.caricaImmagine(percorsoFoto, 200, 170);
                
                if (iconaFoto != null) {
                    lblImmagine.setIcon(iconaFoto);
                    lblImmagine.setText("");
                } else {
                    lblImmagine.setIcon(null);
                    lblImmagine.setText("Immagine non trovata");
                    lblImmagine.setFont(new Font("Verdana", Font.PLAIN, 12));
                }
                
                lblContatore.setText((indiceCorrente[0] + 1) + "/" + percorsi.size());
                
                btnPrev.setEnabled(indiceCorrente[0] > 0);  
                btnNext.setEnabled(indiceCorrente[0] < percorsi.size() - 1);  
            };
            
            aggiornaImmagine.run();
            
            // Azione freccia sinistra
            btnPrev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (indiceCorrente[0] > 0) {
                        indiceCorrente[0]--;
                        aggiornaImmagine.run();
                    }
                }
            });
            
            // Azione freccia destra
            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (indiceCorrente[0] < percorsi.size() - 1) {
                        indiceCorrente[0]++;
                        aggiornaImmagine.run();
                    }
                }
            });
            
            if (percorsi.size() <= 1) {
                btnPrev.setVisible(false);
                btnNext.setVisible(false);
            }
            
        } else {
            lblImmagine.setText("Nessuna foto");
            lblImmagine.setFont(new Font("Verdana", Font.PLAIN, 14));
            btnPrev.setVisible(false);
            btnNext.setVisible(false);
            lblContatore.setVisible(false);
        }
        
        return caroselloPanel;
    }

    
     // Apre la finestra appropriata per fare un'offerta sull'annuncio.
     private void apriOfferta(int idAnnuncio, String matricolaVenditore, StatoAnnuncio stato, String tipologia) {
    	 
    	 String risultato = controller.verificaOfferta(idAnnuncio, matricolaVenditore, stato, tipologia);

    	 if (risultato != null) {
          
    		 JOptionPane.showMessageDialog(this, 
    				 risultato, 
    				 "Errore", 
    				 JOptionPane.WARNING_MESSAGE);
    		 return;
    	 }

        if (tipologia.equals("Scambio")) {
            controller.apriOffertaScambio(idAnnuncio);
        } else if (tipologia.equals("Vendita")) {
            controller.apriOffertaVendita(idAnnuncio);
        } else if (tipologia.equals("Regalo")) {
            controller.apriOffertaRegalo(idAnnuncio);
        }
    }

    private void tornaAreaUtente() {
    	controller.apriAreaUtente();
    }
}