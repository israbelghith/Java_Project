import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionProduit extends JFrame {
    private DefaultListModel<String> model;
    private JList<String> list;
    private JTextField textField;

    public GestionProduit() {
        // Configuration de la fenêtre principale
        setTitle("Gestion Produit");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création du modèle et de la liste
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setBorder(BorderFactory.createTitledBorder("Liste des Produits"));

        // Champ de saisie pour ajouter des produits
        textField = new JTextField(15); // 15 colonnes

        // Bouton pour ajouter un produit
        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim();
                if (!text.isEmpty()) {
                    model.addElement(text);
                    textField.setText("");
                } else {
                    JOptionPane.showMessageDialog(GestionProduit.this, "Veuillez entrer un produit.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel panel2 = new JPanel(new GridLayout(0, 3));
        panel2.add(addButton);
        panel2.add(exitButton);
        // Panneau pour les boutons et le champ de texte
      /*  JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(addButton);
        panel.add(exitButton);*/
        //GridLayout gl=new GridLayout();
       // gl.add(textField);

        // Ajout des composants à la fenêtre
        add(panel2, BorderLayout.EAST);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionProduit frame = new GestionProduit();
            frame.setVisible(true);
        });
    }
}