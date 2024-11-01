import java.sql.*;

public class TestDB {
    public static void main(String[] args) {

EtudiantDAO etd=new EtudiantDAO();
String req="select * from etudiant";
ResultSet rs=etd.selectEtd(req);
//result set = t-uples voir tlf
etd.afficheResultSet(rs);
// inport jdbc
//config connexion
 //class .forname("")
        //connexion con
        //statement st =conn.sta ...
        //st ....
        //rsmd donne info sur les champs les cololonnes
        //rs t-uples

    }
}
