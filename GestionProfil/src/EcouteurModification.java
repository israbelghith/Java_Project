import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurModification extends JDialog {

    private JTextField tf_nom, tf_prenom;
    private JButton btnSave, btnCancel;
    private Profil profil;

    // Constructor
    public EcouteurModification(Frame parent, Profil profil) {
       // super(parent, "Modifier Profil", true);
        this.profil = profil;

        setLayout(new GridLayout(4, 2));

        // Créer les champs de texte et les pré-remplir
        tf_nom = new JTextField(profil.nom, 15);
        tf_prenom = new JTextField(profil.prenom, 15);

        btnSave = new JButton("Enregistrer");
        btnCancel = new JButton("Annuler");

        add(new JLabel("Nom:"));
        add(tf_nom);
        add(new JLabel("Prenom:"));
        add(tf_prenom);

        add(btnSave);
        add(btnCancel);

        // Action lors du clic sur Enregistrer
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Met à jour le profil avec les nouvelles données
                profil.nom = tf_nom.getText();
                profil.prenom = tf_prenom.getText();

                dispose(); // Fermer la fenêtre
            }
        });

        // Action lors du clic sur Annuler
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre sans sauvegarder
            }
        });

// au lieu de spécifier une taille fixe pour la fenêtre, Swing calcule la taille idéale en fonction des composants ajoutés, de leur taille et de leur placement.
        pack();
        // positionne la fenêtre (par exemple un JDialog) au centre de la fenêtre parent spécifiée
        setLocationRelativeTo(parent);
    }

    // Récupérer le profil modifié
    public Profil getUpdatedProfil() {
        return profil;
    }
}