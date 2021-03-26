/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.KomunikacijaSaServerom;
import domain.Administrator;
import domain.Hakaton;
import domain.Sudija;
import domain.SudijaHakaton;
import domain.Tim;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import thread.OsluskivacSudija;
import ui.form.FrmSudijaHakaton;
import ui.table.model.TimoviTableModel;

/**
 *
 * @author Nikola
 */
public class ControllerFrmSudijaHakaton {

    private FrmSudijaHakaton fsudijaHakaton;
    private final Map<String, Object> map;

    public ControllerFrmSudijaHakaton() {
        map = new HashMap<>();
    }

    void otvoriFormuSudija() throws Exception {

        fsudijaHakaton = new FrmSudijaHakaton();

        addListenerCmbHakaton();
        addListenerBtnDetalji();

        fsudijaHakaton.setVisible(true);
        Sudija sudija = (Sudija) GUIKordinator.getInstance().getMap().get("active_user");
        fsudijaHakaton.getLblSudija().setText("Prijavljeni sudija : " + postaviImeUsera(sudija));
        prepareForm();
        new OsluskivacSudija(fsudijaHakaton).start();

    }

    public String postaviImeUsera(Object obj) {
        if (obj instanceof Sudija) {
            Sudija s = (Sudija) obj;
            return s.getKorisnickoIme();
        } else if (obj instanceof Administrator) {
            Administrator a = (Administrator) obj;
            return a.getKorisnickoIme();
        }
        return "N/A";
    }

    public void prepareForm() throws Exception {

        List<Tim> listaTimova = KomunikacijaSaServerom.getInstanca().vratiTimove();
        Sudija sudija = (Sudija) GUIKordinator.getInstance().getMap().get("active_user");

        List<SudijaHakaton> listaVeza = KomunikacijaSaServerom.getInstanca().vratiVeze(sudija);
        List<Hakaton> listaHakatona = new ArrayList<>();
        listaVeza.forEach((sudijaHakaton) -> {
            listaHakatona.add(sudijaHakaton.getHakaton());
        });

        if (listaHakatona == null || listaHakatona.isEmpty()) {
            fsudijaHakaton.getTblTimovi().setModel(new TimoviTableModel(new ArrayList<>()));
            JOptionPane.showMessageDialog(fsudijaHakaton, "Sudija ne ucestvuje ni na jednom hakatonu!");
            fsudijaHakaton.getBtnDetalji().setEnabled(false);
            fsudijaHakaton.getCmbHakaton().setEnabled(false);
            return;

        }

        System.out.println(listaHakatona);
        if (!listaTimova.isEmpty()) {
            fillCmbHakaton(listaHakatona);
        }

        Hakaton h = (Hakaton) fsudijaHakaton.getCmbHakaton().getSelectedItem();
        List<Tim> listaTimovaZaHakaton = null;
        if (h != null) {
            listaTimovaZaHakaton = KomunikacijaSaServerom.getInstanca().vratiTimoveZaHakaton(h);
            fsudijaHakaton.getTblTimovi().setModel(new TimoviTableModel(new ArrayList<>()));
            //JOptionPane.showMessageDialog(fsudijaHakaton, "xxxxxxxxxSudija ne ucestvuje ni na jednom hakatonu!");
        }
        if (!listaHakatona.isEmpty()) {
            fillTableTimovi(listaTimovaZaHakaton);
        }

    }

    private void fillTableTimovi(List<Tim> listaTimova) {
        fsudijaHakaton.getTblTimovi().setModel(new TimoviTableModel(listaTimova));
        if (!listaTimova.isEmpty()) {
            fsudijaHakaton.getTblTimovi().setRowSelectionInterval(0, 0);
        }
    }

    private void fillCmbHakaton(List<Hakaton> listaHakatona) {
        fsudijaHakaton.getCmbHakaton().removeAllItems();
        listaHakatona.forEach((hakaton) -> {
            fsudijaHakaton.getCmbHakaton().addItem(hakaton);
        });
    }

    private void addListenerBtnDetalji() {

        fsudijaHakaton.addBtnKrajListener((ActionEvent ae) -> {
            fsudijaHakaton.dispose();
        });

        fsudijaHakaton.addBtnDetaljiListener((ActionEvent ae) -> {
            TimoviTableModel ttm = (TimoviTableModel) fsudijaHakaton.getTblTimovi().getModel();
            int selectedRow = fsudijaHakaton.getTblTimovi().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(fsudijaHakaton, "Na ovom hakatonu nema timova");
            } else {
                try {
                    Tim t = ttm.getTim(selectedRow);
                    GUIKordinator.getInstance().getMap().put("selected_tim", t);
                    System.out.println(t);
                    /*JDialog frame = new FRezervacija();
                    frame.setVisible(true);*/
                    GUIKordinator.getInstance().otvoriFrmSudijaTim(fsudijaHakaton);
                } catch (Exception ex) {
                    Logger.getLogger(ControllerFrmSudijaHakaton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void addListenerCmbHakaton() {

        fsudijaHakaton.addCmbHakatonListener((ItemEvent ie) -> {

            Hakaton ha = (Hakaton) fsudijaHakaton.getCmbHakaton().getSelectedItem();
            if (ha != null) {
                Hakaton h = (Hakaton) fsudijaHakaton.getCmbHakaton().getSelectedItem();
                if (h != null) {
                    try {
                        List<Tim> listaTimovaZaHakaton = KomunikacijaSaServerom.getInstanca().vratiTimoveZaHakaton(h);
                        fillTableTimovi(listaTimovaZaHakaton);
                    } catch (Exception ex) {
                        //Logger.getLogger(ControllerFrmSudijaHakaton.class.getName()).log(Level.SEVERE, null, ex);
                        fsudijaHakaton.getTblTimovi().setModel(new TimoviTableModel(new ArrayList<>()));
                        JOptionPane.showMessageDialog(fsudijaHakaton, "Sudija ne ucestvuje ni na jednom hakatonu!");
                    }
                } else {
                    fsudijaHakaton.getTblTimovi().setModel(new TimoviTableModel(new ArrayList<>()));
                    JOptionPane.showMessageDialog(fsudijaHakaton, "Sudija ne ucestvuje ni na jednom hakatonu!");
                }

            }
        });
    }
}
