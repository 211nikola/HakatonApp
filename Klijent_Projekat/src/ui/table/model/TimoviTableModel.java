package ui.table.model;

import domain.Tim;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TimoviTableModel extends AbstractTableModel {

    List<Tim> lista;
    private final String[] columnNames = new String[]{"Naziv"};
    private final Class[] columnClass = new Class[]{String.class};

    public TimoviTableModel(List<Tim> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tim t = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getNaziv();
            default:
                return "n/a";
        }
    }

    public List<Tim> getLista() {
        return lista;
    }

    public void addTim(Tim tim) {
        lista.add(tim);
        fireTableDataChanged();
    }

    public Tim getTim(int row) {
        return lista.get(row);
    }

    public void removeTim(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }
}
