import java.sql.*;
public class TestDAO {
    Connection conn=null;
    Statement st=null;

    public TestDAO()
    {
        try {
            Class.forName(Config.nomDriver);
            System.out.println("okkkk connexion");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur driver"+e.getMessage());
        }
        try {
            conn= DriverManager.getConnection(Config.url,Config.username,Config.pwd);
            System.out.println("connected for test ");
            st=conn.createStatement();

        } catch (SQLException e) {
            System.out.println("errr"+e.getMessage());
        }
    }

    ResultSet selectProduit(String req){
        ResultSet rs=null;

        try {
            rs=st.executeQuery(req);
System.out.println("data here");
        } catch (SQLException e) {
            System.out.println("err de selection"+e.getMessage());
        }
        return rs;
    }

    int insertProduit(int reference,String nom, int qte,double prix){
        int a=0;
        String req="insert into etudiant values(?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(req);
            ps.setInt(1,reference);
            ps.setString(2,nom);
            ps.setInt(3,qte);
            ps.setDouble(4,prix);
            a=ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return a;
    }
    public int updateProduit(int reference, String nom,int qte, double prix) {
        int a=0;
        String req="update produit set  nom=?, qte =?, prix=? where reference=?";
        try {
            PreparedStatement ps= conn.prepareStatement(req);
            ps.setString(1,nom);
            ps.setInt(2,qte);
            ps.setDouble(3,prix);
            ps.setInt(4,reference);

            a=ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return a;
    }

    int deleteProduit(int ref){
        String  req="delete from produit where reference=?";
        PreparedStatement ps= null;
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1,ref);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }

    }
}
