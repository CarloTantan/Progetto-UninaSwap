package boundary;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ListaTransazioni extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListaTransazioni frame = new ListaTransazioni();
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
    public ListaTransazioni() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ListaTransazioni.class.getResource("/icons/iconaUninaSwapPiccolissima.jpg")));
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Panel superiore (header)
        JPanel panelHeader = new JPanel();
        panelHeader.setPreferredSize(new Dimension(getWidth(), 80));
        panelHeader.setBackground(new Color(70, 171, 225));
        panelHeader.setLayout(new BorderLayout(0, 0));
        contentPane.add(panelHeader, BorderLayout.NORTH);

        // Bottone Undo a sinistra
        JButton btnUndo = new JButton("");
        btnUndo.setIcon(new ImageIcon(ListaTransazioni.class.getResource("/icons/icons8-annulla-3d-fluency-32.png")));
        btnUndo.setFocusPainted(false);
        btnUndo.setBorderPainted(false);
        btnUndo.setBackground(new Color(45, 134, 192));
        btnUndo.setPreferredSize(new Dimension(80, 80));
        panelHeader.add(btnUndo, BorderLayout.WEST);

        // Label centrata
        JLabel lblTitolo = new JLabel("Transazioni avvenute");
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(lblTitolo, BorderLayout.CENTER);

        // Panel centrale (qui aggiungerai la lista delle transazioni)
        JPanel panelCentrale = new JPanel();
        panelCentrale.setBackground(Color.WHITE);
        contentPane.add(panelCentrale, BorderLayout.CENTER);
    }
}