import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurLabel  extends MouseAdapter {
    GEstionProfils gp ;
    EcouteurLabel(GEstionProfils gp)
    {
        this.gp=gp;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==gp.lb_nom)
        {
            gp.lb_nom.setForeground(Color.red);
        }
        else if (e.getSource()==gp.lb_prenom)
        {
            gp.lb_prenom.setForeground(Color.red);
        }
        else if (e.getSource()==gp.lb_pseudo)
        {
            gp.lb_pseudo.setForeground(Color.red);
        }
        //checking the textfield
        if (e.getSource()==gp.tf_nom)
        {
            gp.lb_help.setText("HELP : NOM");
        }

        else if (e.getSource()==gp.tf_prenom)
        {
            gp.lb_help.setText("HELP : PRENOM");
        }
        else if (e.getSource()==gp.tf_pseudo)
        {
            gp.lb_help.setText("HELP : PSEUDO");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gp.lb_nom.setForeground(null);
        gp.lb_prenom.setForeground(null);
        gp.lb_pseudo.setForeground(null);
        gp.lb_help.setText("HELP");
    }
    //cursor text field prenom pseudo nom .. class externe + tp home
}
