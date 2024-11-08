import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurAjoutEtudiant implements ActionListener {
    EtudiantForm e;
    public EcouteurAjoutEtudiant(EtudiantForm e) {
this.e=e;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        //get the values from form
        int cin=Integer.parseInt(e.tfcin.getText());
        String nom=e.tfnom.getText();
        String prenom=e.tfprenom.getText();
        double moyenne=Double.parseDouble(e.tfmoyenne.getText());

        int a=e.model.ajoutEtudiant(cin,nom,prenom,moyenne);
        if (a>0){
            JOptionPane.showMessageDialog(e,"ajout avec succes");

        }
        else JOptionPane.showMessageDialog(e,"echec d'ajout");
    }
}
