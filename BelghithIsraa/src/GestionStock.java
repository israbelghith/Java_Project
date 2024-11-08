import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class GestionStock extends JInternalFrame
{
    JTable jt;
    JCheckBox cb_critique;
    ArticleDAO dao;
    ArticleTableModel model;
    GestionStock(){
        this.setSize(700,600);
        this.setTitle("Gestion de stock");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        cb_critique=new JCheckBox("Stock critique");
        JPanel pr=new JPanel();
        pr.add(cb_critique);

        this.setLayout(new BorderLayout());
        this.add(pr,BorderLayout.NORTH);


        //show data
        String req="select * from article";
        dao=new ArticleDAO();
        ResultSet rs=dao.selectArticle(req);
        //NB BD =>dao
        //jtable =>model
        model =new ArticleTableModel(rs,dao);

        jt=new JTable(model);
        this.add(new JScrollPane(jt),BorderLayout.CENTER);

        /**
         * afficher les données de la base dans le jtable
         * (7points)
         *
         * les champs quantité et emplacement sont editable et
         * modifiable dans l'interface et dans la base  (6point)
         */


        /**
         * Evenement
         */

        cb_critique.addItemListener(new EcouteurCheckBox(this));
    /*{

            @Override
            public void itemStateChanged(ItemEvent e) {
                /**
                 * si le checkbox est coché, afficher dans le jtable
                 * seulement les articles dont la quantité est inferieur à 10
                 * (4 points)
                 *
                 * rendre cette evenement dans une classe externe
                 * (3points)
                 *

            }
        });*/
    }
}
