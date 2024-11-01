import java.sql.*;

public class EtudiantDAO {
    Connection conn=null;
    Statement st=null;//contient des methode qui exceute des req
    public  EtudiantDAO(){
        //chargement driver
//DAO data access object class intermédiaire entre intf graphieque et bd
        try {
            Class.forName(Config.nomDriver);
            System.out.println("okkkk");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur driver"+e.getMessage());
        }

        // cnx à la base

        //declaration

        try {
            conn= DriverManager.getConnection(Config.url,Config.username,Config.pwd);
            System.out.println("connected 2");
            st=conn.createStatement();

        } catch (SQLException e) {
            System.out.println("errr"+e.getMessage());
        }

    }

    int insertEtudiant(int cin,String nom, String prenom,double moyenne){
        int a=0;
        //execution de req
        String req_insertion="insert into etudiant values("+cin+",'"+nom+"','"+prenom+"',"+moyenne+")";
        if(st != null){
            try {
                 a=st.executeUpdate(req_insertion);
                System.out.println("insertion okkkk");
            } catch (SQLException e) {
                System.out.println("errr insertion "+e.getMessage());
            }
        }
        return a;
    }
    int insertEtudiant2(int cin,String nom, String prenom,double moyenne){
        int a=0;
        String req="insert into etudiant values(?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(req);
            ps.setInt(1,cin);
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setDouble(4,moyenne);
            a=ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return a;
    }

    int deleteEtudiant(int cin){
        String  req="delete from etudiant where cin=?";
        PreparedStatement ps= null;
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1,cin);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }

    }
    ResultSet selectEtd(String req){
       ResultSet rs=null;
       //rs les données
         //       rsmd des info sous forme tab
        try {
            rs=st.executeQuery(req);
           // System.out.println("rs: "+rs.getString(2));
        } catch (SQLException e) {
            System.out.println("err de selection"+e.getMessage());
        }
        return rs;
    }
    void afficheResultSet(ResultSet rs){
        try{
        ResultSetMetaData rsmdrs=rs.getMetaData();
        int nbcol= rsmdrs.getColumnCount();
        for (int i = 0; i <nbcol ; i++) {

        }
        //affichage dans le console
        while (rs.next()){
            //getcolname
            int cin= rs.getInt(1);//for //getobject 
            String nom=rs.getString(2);
            String prenom=rs.getString(3);
            double moy=rs.getDouble(4);
            System.out.println("cin: "+cin+" ,nom "+nom+" ,prenom "+prenom+" ,moy "+moy);
        }}
        catch (SQLException e){
            System.out.println("errr d affichage "+e.getMessage());
        }
    }
//result set est un pointeur vers tout le resultat de memoire
    //metatdata coll les champs (rsmd)


}
