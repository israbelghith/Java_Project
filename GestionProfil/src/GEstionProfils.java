import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GEstionProfils extends JInternalFrame {
//tab des données
    ArrayList<Profil> data=new ArrayList<Profil>();


    //declaration des var
    JLabel lb_nom, lb_prenom,lb_pseudo,lb_help;
    JButton btnValider;
    JTextField tf_nom,tf_prenom,tf_pseudo;
    DefaultListModel model;
    GEstionProfils(){
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,600);
this.setTitle("Gestion des Profiles");
        lb_nom =new JLabel("Nom");
        lb_prenom =new JLabel("Prenom");
        lb_pseudo =new JLabel("Pseudo");
        tf_nom=new JTextField(15);
        tf_nom.setText("saisir votre nom");
        tf_prenom=new JTextField(15);
        tf_prenom.setText("saisir votre prenom");
        tf_pseudo=new JTextField(15);
        tf_pseudo.setText("saisir votre pseudo");
btnValider=new JButton("Valider");
        lb_help=new JLabel("HELP");
        JPanel pn=new JPanel();
        pn.setLayout(new FlowLayout());
        pn.add(lb_nom);
        pn.add(tf_nom);
        pn.add(lb_prenom);
        pn.add(tf_prenom);
        pn.add(lb_pseudo);
        pn.add(tf_pseudo);
        pn.add(btnValider);
//BorderLayout bl=new BorderLayout();
this.setLayout(new BorderLayout());
        this.add(pn, BorderLayout.NORTH);
        this.add(lb_help, BorderLayout.SOUTH);
        JSplitPane  jsp=new JSplitPane();
        model=new DefaultListModel();
     /*   model.addElement("elmt1");
        model.addElement("elmt2");*/
        JList jl =new JList(model);

        jl.setPreferredSize(new Dimension(150,0));

        JTabbedPane jtp=new  JTabbedPane();
      //permet d'ajouter tabulation
        // jtp.addTab("tab1",new JPanel());
        //jtp.addTab("tab2",new JPanel());

        this.add(jsp,BorderLayout.CENTER);
        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);

        //anonyme class
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ps=tf_pseudo.getText();
                boolean psExits=false;
                for (int i = 0; i <data.size() ; i++) {
                    if(data.get(i).pseudo.equalsIgnoreCase(ps)){
                        psExits=true;
                        break;
                    }
                }
                //si ps exists
                if(psExits){
                    JOptionPane.showMessageDialog(null,"le pseudo "+ps+" exist déjà","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    data.add(new Profil(tf_nom.getText(),tf_prenom.getText(),tf_pseudo.getText()));
                    model.addElement(tf_pseudo.getText());
                }


            }
        });
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if( e.getClickCount()==2)//double click
                {
                    //chercher profil ayant le pseudo séléctionnée
                    String ps=jl.getSelectedValue()+"";
                    for (int i = 0; i < data.size() ; i++) {
                      if(data.get(i).pseudo.equalsIgnoreCase(ps))
                      {
                          FormPanel p=new FormPanel(data.get(i));
                          jtp.addTab(ps,p);
                          break;
                      }
                    }

                }

                //
                if(e.getButton()==MouseEvent.BUTTON3){
                    JPopupMenu popup=new JPopupMenu();
                    JMenuItem itemmod=new JMenuItem("modifier");
                    JMenuItem itemSup=new JMenuItem("supp");
                    JMenuItem itemSuppTout=new JMenuItem("supp tout");
                    popup.add(itemmod);
                    popup.add(itemSup);
                    popup.add(itemSuppTout);
                    popup.show(jl,e.getX(),e.getY());


                    //event
                    itemSuppTout.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            data.clear();
                            model.clear();
                        }
                    });
                    itemSup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            data.remove(jl.getSelectedIndex());
                            model.remove(jl.getSelectedIndex());
                        }
                    });
// Partie modifiée de GEstionProfils

                    itemmod.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedIndex = jl.getSelectedIndex();
                            if (selectedIndex != -1) {
                                Profil selectedProfil = data.get(selectedIndex);

                                // Ouvrir la fenêtre de modification
                                EcouteurModification dialog = new EcouteurModification(null, selectedProfil);
                                dialog.setVisible(true);

                                // Mettre à jour la liste après la modification
                                Profil updatedProfil = dialog.getUpdatedProfil();
                                model.setElementAt(updatedProfil.pseudo, selectedIndex);
                            }
                        }
                    });

                    //modifier
                   /* fentre nom prenom*
                   * annuler
                   * valider
                   * */
// valider ext ne peut pas ajouter pseudo existant

                }
            }
        });

        //labels
        lb_nom.addMouseListener(new EcouteurLabel(this));
        lb_prenom.addMouseListener(new EcouteurLabel(this));
        lb_pseudo.addMouseListener(new EcouteurLabel(this));

        //textfield
        tf_nom.addMouseListener(new EcouteurLabel(this));
        tf_prenom.addMouseListener(new EcouteurLabel(this));
        tf_pseudo.addMouseListener(new EcouteurLabel(this));
        //ecouteur sur le ocus
        tf_nom.addFocusListener(new EcouteurFocus(this));
        tf_prenom.addFocusListener(new EcouteurFocus(this));
        tf_pseudo.addFocusListener(new EcouteurFocus(this));
//        jlist


        this.setVisible(true);
    }
}
