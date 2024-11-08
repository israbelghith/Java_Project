//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.*;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ProduitForm extends JFrame {
    ProduitTableModel model;
    JTable jt;
    JLabel lbreferance;
    JLabel lbnom;
    JLabel lbquantite;
    JLabel lbprix;
    JTextField tfreferance;
    JTextField tfnom;
    JTextField tfquantite;
    JTextField tfprix;
    JButton btnajout;
    JButton btnmodifier;
    TestDAO dao;

    ProduitForm() {
        this.setTitle("Edition des produit");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(3);
        this.lbreferance = new JLabel("referance");
        this.lbnom = new JLabel("Nom");
        this.lbquantite = new JLabel("quantite");
        this.lbprix = new JLabel("prix");
        this.tfreferance = new JTextField(10);
        this.tfnom = new JTextField(10);
        this.tfquantite = new JTextField(10);
        this.tfprix = new JTextField(10);
        this.btnajout = new JButton("Ajout");
        this.btnajout = new JButton("Ajout");
        JPanel pn = new JPanel();
        pn.add(this.lbreferance);
        pn.add(this.tfreferance);
        pn.add(this.lbnom);
        pn.add(this.tfnom);
        pn.add(this.lbquantite);
        pn.add(this.tfquantite);
        pn.add(this.lbprix);
        pn.add(this.tfprix);
        pn.add(this.btnajout);
        this.add(pn, "North");
        this.btnajout.addActionListener(new EcouteurAjoutProduit(this));
        String req = "select * from produit";
        this.dao = new TestDAO();
        ResultSet rs = this.dao.selectProduit(req);
        model = new ProduitTableModel(rs, this.dao);
        jt = new JTable(model);
        this.add(new JScrollPane(this.jt), BorderLayout.CENTER);
        this.jt.addMouseListener(new DeleteProduit(this));
    }
    public static void main(String[] args) {
        new ProduitForm().setVisible(true);
    }
}
