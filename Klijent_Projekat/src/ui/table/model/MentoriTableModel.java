/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.table.model;

import domain.Mentor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class MentoriTableModel extends AbstractTableModel {

    List<Mentor> lista;
    private final String[] columnNames = new String[]{"Ime", "Prezime", "Mail", "Profesija"};
    private final Class[] columnClass = new Class[]{String.class, String.class, String.class, String.class};

    public MentoriTableModel(List<Mentor> lista) {
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
        Mentor m = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getIme();
            case 1:
                return m.getPrezime();
            case 2:
                return m.getMail();
            case 3:
                return m.getProfesija();
            default:
                return "n/a";
        }
    }

    public void addMentor(Mentor s) {
        lista.add(s);
        fireTableDataChanged();
    }

    public Mentor getMentor(int row) {
        return lista.get(row);
    }

    public void removeMentor(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public List<Mentor> getLista() {
        return lista;
    }

    public void setLista(List<Mentor> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

}
