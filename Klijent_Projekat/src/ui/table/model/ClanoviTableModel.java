/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.table.model;

import domain.Clan;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class ClanoviTableModel extends AbstractTableModel {

    List<Clan> lista;
    private final String[] columnNames = new String[]{"Ime", "Prezime", "Mail", "Uloga"};
    private final Class[] columnClass = new Class[]{String.class, String.class, String.class, String.class};

    public ClanoviTableModel(List<Clan> lista) {
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
        Clan c = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getIme();
            case 1:
                return c.getPrezime();
            case 2:
                return c.getMail();
            case 3:
                return c.getUloga();
            default:
                return "n/a";
        }
    }

    public void AddClan(Clan c) {
        lista.add(c);
        fireTableDataChanged();
    }

    public Clan getClan(int row) {
        return lista.get(row);
    }

    public void removeClan(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public List<Clan> getLista() {
        return lista;
    }

    public void setLista(List<Clan> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

}
