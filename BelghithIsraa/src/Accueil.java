
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * Créer un projet portant votre nom et prenom
 *
 * Connecter votre projet à la base DSjava
 * Dans la base DSjava, exécuter le code sql suivant
 * pour créer la table article et en ajouter quelques données:

 CREATE TABLE `article` (
 `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `Nom` varchar(30) NOT NULL,
 `Quantite` decimal(10,0) NOT NULL,
 `Emplacement` varchar(500) NOT NULL
 ) ;



 INSERT INTO `article` (`id`, `Nom`, `Quantite`, `Emplacement`) VALUES
 (1, 'Ordinateur portable', 20, 'Bureau Informatique'),
 (2, 'Souris sans fil', 50, 'Stock Accessoires'),
 (3, 'Clavier mécanique', 30, 'Bureau Central'),
 (4, 'Disque dur externe 1TB', 15, 'Salle IT'),
 (5, 'Casques audio Bluetooth', 40, 'Stock Audio'),
 (6, 'Projecteur 4K', 10, 'Salle de conférence'),
 (7, 'Écran 27\"', 25, 'Entrepôt C'),
 (8, 'Caméra de surveillance', 35, 'Sécurité'),
 (9, 'Routeur Wi-Fi 6', 20, 'Stock Réseau'),
 (10, 'Chargeur universel', 100, 'Stock Accessoires');

 */

public class Accueil extends JFrame {

    JMenuItem menuArticles,menuPiece;
    JDesktopPane desktop;
    JMenuBar menuBar;


    public Accueil() {
        this.setTitle("Inventaire");
        this.setSize(800,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuBar=new JMenuBar();
        menuArticles=new JMenuItem("Articles");
        menuPiece=new JMenuItem("Piece");

        menuBar.add(menuArticles);
        menuBar.add(menuPiece);


        this.setJMenuBar(menuBar);


        desktop=new JDesktopPane();
        this.add(desktop);


        menuArticles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionStock gs=new GestionStock();
                gs.setVisible(true);
                desktop.add(gs);
            }
        });


    }


    public static void main(String[] args) {
        new Accueil().setVisible(true);
    }

}

