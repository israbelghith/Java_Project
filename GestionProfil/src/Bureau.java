import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bureau extends JFrame {
    JDesktopPane desktop;// desktop + internalFrame
    JMenuBar menuBar;
    JMenu menuTPSwing;
    JMenu menuTPBase;
    JMenuItem menuItemtp1,menuItemtp2;

    Bureau(){
       this.setTitle("Projet Java");
       this.setSize(1000,800);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//creation
        desktop=new JDesktopPane();
        this.add(desktop);//jfamre border
        menuBar=new JMenuBar();
        menuTPSwing=new JMenu("TP swing");

        menuItemtp1=new JMenuItem("TP 1");
        menuItemtp2=new JMenuItem("TP 2");
        //menuitem
        menuTPSwing.add(menuItemtp1);
        menuTPSwing.add(menuItemtp2);

        menuTPBase=new JMenu("TP BD");

        menuBar.add(menuTPSwing);
        menuBar.add(menuTPBase);
        this.setJMenuBar(menuBar);

//evenement

        //anonyme class
        menuItemtp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//fenetre intern dans destop
           /*     JInternalFrame frame=new JInternalFrame();
                frame.setTitle("fenetre Iterne");
                frame.setSize(400,200);
                frame.setMaximizable(true);
                frame.setIconifiable(true);
                frame.setClosable(true);
                frame.setVisible(true);
                desktop.add(frame);*/
GEstionProfils gp =new GEstionProfils();
gp.setVisible(true);

                gp.setMaximizable(true);
                gp.setIconifiable(true);
                gp.setClosable(true);
                gp.setVisible(true);
                desktop.add(gp);

            }


        });

    }

    public static void main(String[] args) {
        Bureau b =new Bureau();
        b.setVisible(true);
    }
}
