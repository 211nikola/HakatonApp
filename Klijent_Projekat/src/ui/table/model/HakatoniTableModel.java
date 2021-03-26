/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.table.model;

import domain.Hakaton;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class HakatoniTableModel extends AbstractTableModel {

    List<Hakaton> lista;
    private final String[] columnNames = new String[]{"Naziv", "Datum"};
    private final Class[] columnClass = new Class[]{String.class, String.class};

    public HakatoniTableModel(List<Hakaton> lista) {
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
        Hakaton h = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return h.getNaziv();
            case 1:
                return h.getDatum();
            default:
                return "n/a";
        }
    }

    public void addHakaton(Hakaton s) {
        lista.add(s);
        fireTableDataChanged();
    }

    public Hakaton getHakaton(int row) {
        return lista.get(row);
    }

    public void removeHakaton(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public List<Hakaton> getLista() {
        return lista;
    }

    public void setLista(List<Hakaton> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
