import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleTableModel extends AbstractTableModel {
    ArrayList<Object[]> data=new ArrayList<Object[]>();

    ResultSetMetaData rsmd;
    ArticleDAO dao;
    public ArticleTableModel(ResultSet rs, ArticleDAO dao) {
        this.dao=dao;
        //chargement driver
        try {
            rsmd=rs.getMetaData();
            while(rs.next()){
                Object[] t= new Object[rsmd.getColumnCount()];
                for (int i = 0; i <t.length ; i++) {

                    t[i]=rs.getObject((i+1));

                }
                data.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        //return nb ligne


        return data.size();//data qui contient tout le data  nb ligne
    }

    @Override
    public int getColumnCount() {
        //return nbr col

        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        //return true tout tab est modifiable
        //seuelemt moy case
       if(getColumnName(columnIndex).equalsIgnoreCase("Quantite"))
        {
            return true;
        }


        else if(getColumnName(columnIndex).equalsIgnoreCase("Emplacement"))
        {
            return true;
        }

        else  return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //return les val de chaques cellules
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Object[] ligne=data.get(rowIndex);
        int id =Integer.parseInt(ligne[columnNameToIndex("Id")]+"");
        String nom=ligne[columnNameToIndex("Nom")]+"";

        double qte= Double.parseDouble(ligne[columnNameToIndex("Quantite")]+"");
        String empl=ligne[columnNameToIndex("Emplacement")]+"";
       if(columnIndex==columnNameToIndex("Quantite"))
        {
            qte =Double.parseDouble(aValue+"");
        }
        else if(columnIndex==columnNameToIndex("Emplacement"))
        {
            empl =aValue+"";
        }
        int a=dao.updateArticle(id,nom,qte,empl);
        if(a>0)
        {
            data.get(rowIndex)[columnIndex]=aValue;
        }
    }


    //ajout dans la partie dans la base et  interface
   /* public int ajoutEtudiant(int cin, String nom, String prenom, double moyenne) {
        int a=0;
        EtudiantDAO dao=new EtudiantDAO();
        a=dao.insertEtudiant2(cin,nom,prenom,moyenne);
        if (a>0)
        {
            data.add(new Object[]{cin,nom,prenom,moyenne});
            //rerunder sur écran
            fireTableDataChanged();
        }
        return a;
    }*/



    //rowAtPoint => indice de ligne
    public int supprimerEtudiant(int rowAtPoint) {
        int a=0;
        int indice=columnNameToIndex("id");
        if(indice>=0){
            int id=Integer.parseInt(data.get(rowAtPoint)[indice]+"");
            a=dao.deleteArticle(id);
            if(a>0){
                data.remove(rowAtPoint);
                fireTableDataChanged();
            }
        }

        return a;
    }

    //récuperer selon le nom du champs et reourner son index dans le tab
    int columnNameToIndex(String columnname)
    {
        for (int i = 0; i < getColumnCount(); i++) {
            if(getColumnName(i).equalsIgnoreCase(columnname))
            {
                return i;
            }
        }
        return -1;//else
    }
}
