import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class EcouteurFocus implements FocusListener {
    GEstionProfils gp;
    EcouteurFocus(GEstionProfils gp)
    {
        this.gp=gp;
    }
    @Override
    public void focusGained(FocusEvent e) {
        if ( e.getSource()==gp.tf_nom && gp.tf_nom.getText().equalsIgnoreCase("saisir votre nom"))
        {
            gp.tf_nom.setText("");
        }
        if ( e.getSource()==gp.tf_prenom && gp.tf_prenom.getText().equalsIgnoreCase("saisir votre prenom"))
        {
            gp.tf_prenom.setText("");
        }
        if ( e.getSource()==gp.tf_pseudo && gp.tf_pseudo.getText().equalsIgnoreCase("saisir votre pseudo"))
        {
            gp.tf_pseudo.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (gp.tf_nom.getText().equalsIgnoreCase(""))
        {
            gp.tf_nom.setText("saisir votre nom");
        }
        if (gp.tf_prenom.getText().equalsIgnoreCase(""))
        {
            gp.tf_prenom.setText("saisir votre prenom");
        }
        if (gp.tf_pseudo.getText().equalsIgnoreCase(""))
        {
            gp.tf_pseudo.setText("saisir votre pseudo");
        }
    }
}
