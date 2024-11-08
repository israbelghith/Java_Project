import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class EtudiantForm extends JFrame {

    EtudiantTableModel model;
   JTable jt;
    JLabel lbcin,lbnom,lbprenom,lbmoyenne;
    JTextField tfcin,tfnom,tfprenom,tfmoyenne;
    JButton btnajout;
    EtudiantDAO dao;

    EtudiantForm()
    {
        this.setTitle("Edition des etudiants");
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lbcin=new JLabel("Cin");
        lbnom=new JLabel("Nom");
        lbprenom=new JLabel("Prenom");
        lbmoyenne=new JLabel("Moyenne");

        tfcin=new JTextField(10);
        tfnom=new JTextField(10);
        tfprenom=new JTextField(10);
        tfmoyenne=new JTextField(10);

        btnajout=new JButton("Ajout");

        JPanel pn=new JPanel();
        pn.add(lbcin);pn.add(tfcin);
        pn.add(lbnom);pn.add(tfnom);
        pn.add(lbprenom);pn.add(tfprenom);
        pn.add(lbmoyenne);pn.add(tfmoyenne);
        pn.add(btnajout);

        this.add(pn, BorderLayout.NORTH);

        String req="select * from etudiant";
        dao=new EtudiantDAO();
        ResultSet rs=dao.selectEtd(req);
        //NB BD =>dao
        //jtable =>model
        model =new EtudiantTableModel(rs,dao);
        jt=new JTable(model);//afii=cher les titres et les données
        //jt tous ce qui est interface
        //jet.setModel(model)
        //tab string et tab objet tab de tab
        //cette ligne est trés important
        this.add(new JScrollPane(jt),BorderLayout.CENTER);

       //event
        btnajout.addActionListener(new EcouteurAjoutEtudiant(this));

        //supprimer
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)//button 3 click droit
                {
                    JPopupMenu popupMenu=new JPopupMenu();
                    JMenuItem itemsup=new JMenuItem("supprimer");
                    popupMenu.add(itemsup);
                    popupMenu.show(jt,e.getX(),e.getY());


                    //action sur itemsupprimer
                    itemsup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                           // int a=model.supprimerEtudiant(jt.getSelectedRow());
                            int a=model.supprimerEtudiant(jt.rowAtPoint(new Point(e.getX(),e.getY())));//rowatpoint où on a souris
                        }
                    });
                }

            }
        });
    }

    public static void main(String[] args) {
        new EtudiantForm().setVisible(true);
    }
}
