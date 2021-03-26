/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.table.model;

import domain.Sudija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class SudijeTableModel extends AbstractTableModel {

    private List<Sudija> lista;
    private final String[] columnNames = new String[]{"Ime", "Prezime", "Mail", "Zemlja"};
    private final Class[] columnClass = new Class[]{String.class, String.class, String.class, String.class};

    public SudijeTableModel(List<Sudija> lista) {
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
        Sudija s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIme();
            case 1:
                return s.getPrezime();
            case 2:
                return s.getMail();
            case 3:
                return s.getZemlja();
            default:
                return "n/a";
        }
    }

    public void addSudija(Sudija s) {
        lista.add(s);
        fireTableDataChanged();
    }

    public Sudija getSudija(int row) {
        return lista.get(row);
    }

    public void removeSudija(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public List<Sudija> getLista() {
        return lista;
    }

    public void setLista(List<Sudija> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

}
