import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class EcouteurCheckBox implements ItemListener {
    GestionStock gs;

    public EcouteurCheckBox(GestionStock gs){
     this.gs=gs;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

        String req2="select * from article where Quantite <10 ";
       // this.gs.dao=new ArticleDAO();
        ResultSet rs2=this.gs.dao.selectArticle(req2);
        //NB BD =>dao
        //jtable =>model
        this.gs.model =new ArticleTableModel(rs2,this.gs.dao);

        this.gs.jt=new JTable(this.gs.model);
        this.gs.add(new JScrollPane(this.gs.jt), BorderLayout.CENTER);

    }
}
