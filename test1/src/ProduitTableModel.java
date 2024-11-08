import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduitTableModel extends AbstractTableModel {
    private final ArrayList<Object[]> data = new ArrayList<>();
    private ResultSetMetaData rsmd;
    private final TestDAO dao;

    public ProduitTableModel(ResultSet rs, TestDAO dao) {
        this.dao = dao;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                Object[] row = new Object[rsmd.getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                data.add(row);
            }
            rs.close();  // Fermer le ResultSet après utilisation
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permettre uniquement l'édition de la colonne "prix"
        return getColumnName(columnIndex).equalsIgnoreCase("prix");
    }

    private int columnNameToIndex(String columnName) {
        for (int i = 0; i < getColumnCount(); i++) {
            if (getColumnName(i).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Object[] row = data.get(rowIndex);
        int ref = Integer.parseInt(row[columnNameToIndex("reference")].toString());
        String nom = row[columnNameToIndex("nom")].toString();
        int qte = Integer.parseInt(row[columnNameToIndex("qte")].toString());
        double prix = Double.parseDouble(row[columnNameToIndex("prix")].toString());

        if (columnIndex == columnNameToIndex("prix")) {
            prix = Double.parseDouble(aValue.toString());
        }

        int updateResult = dao.updateProduit(ref, nom, qte, prix);
        if (updateResult > 0) {
            data.get(rowIndex)[columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    public int ajoutProduit(int ref, String nom, int qte, double prix) {
        int insertResult = dao.insertProduit(ref, nom, qte, prix);
        if (insertResult > 0) {
            data.add(new Object[]{ref, nom, qte, prix});
            fireTableDataChanged();
        }
        return insertResult;
    }
}
