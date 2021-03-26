/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.KomunikacijaSaServerom;
import domain.Clan;
import domain.Ocena;
import domain.Sudija;
import domain.Tim;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.form.FrmSudijaHakaton;
import ui.form.FrmSudijaTim;
import ui.table.model.ClanoviTableModel;

/**
 *
 * @author Nikola
 */
public class ControllerFrmSudijaTim {

    private FrmSudijaTim fSudijaTim;
    private final Map<String, Object> map;

    public ControllerFrmSudijaTim() {
        map = new HashMap<>();
    }

    void otvoriFrmSudijaTim(FrmSudijaHakaton fsudija) throws Exception {
        fSudijaTim = new FrmSudijaTim(fsudija, true);
        prepareForm();
        addFrmSudijaTimListener();
        fSudijaTim.setVisible(true);

    }

    public void addFrmSudijaTimListener() {

        fSudijaTim.addButtonOtkaziListener((ActionEvent ae) -> {
            fSudijaTim.dispose();
        });

        fSudijaTim.addButtonOceniListener((ActionEvent ae) -> {
            if (validacija()) {
                int dizajn = Integer.parseInt(fSudijaTim.getTxtDizajn().getText());
                int efikasnost = Integer.parseInt(fSudijaTim.getTxtDizajn().getText());
                int slozenost = Integer.parseInt(fSudijaTim.getTxtSlozenost().getText());
                String komentar = fSudijaTim.getTxtKomentar().getText();
                Sudija sudija = (Sudija) GUIKordinator.getInstance().getMap().get("active_user");
                Tim tim = (Tim) GUIKordinator.getInstance().getMap().get("selected_tim");
                Ocena o = new Ocena(slozenost, tim, dizajn, efikasnost, slozenost, komentar, sudija);
                try {
                    KomunikacijaSaServerom.getInstanca().sacuvajOcenu(o);
                    fSudijaTim.getBtnOceni().setEnabled(false);
                    fSudijaTim.getLblOcenjen().setVisible(true);
                    fSudijaTim.getTxtDizajn().setEditable(false);
                    fSudijaTim.getTxtEfikasnost().setEditable(false);
                    fSudijaTim.getTxtSlozenost().setEditable(false);
                    fSudijaTim.getTxtKomentar().setEditable(false);
                    fSudijaTim.getTxtKomentar().setEnabled(false);
                    JOptionPane.showMessageDialog(fSudijaTim, "Uspesno sacuvana Ocena");
                    izbrisi();

                } catch (Exception ex) {
                    Logger.getLogger(FrmSudijaTim.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(fSudijaTim, "Sistem ne moze da kreira ocenu!", "Greska", JOptionPane.ERROR_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(fSudijaTim, "Sistem ne moze da kreira ocenu!", "Greska", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    private void prepareForm() throws Exception {
        fSudijaTim.setLocationRelativeTo(null);
        fillTimData();
        fillTableClanovi();
        preparePanelOcena();
        izbrisi();

    }

    private void fillTableClanovi() throws Exception {
        Tim tim = (Tim) GUIKordinator.getInstance().getMap().get("selected_tim");
        List<Clan> listaClanova = KomunikacijaSaServerom.getInstanca().vratiClanoveZaTim(tim);
        fSudijaTim.getTblClanovi().setModel(new ClanoviTableModel(listaClanova));
        fSudijaTim.getTblClanovi().setEnabled(false);
    }

    private boolean validacija() {
        boolean pom = true;
        izbrisi();
        if (fSudijaTim.getTxtDizajn().getText().isEmpty()) {
            fSudijaTim.getLblErrorDizajn().setText("Unesite ocenu dizajna!");
            pom = false;
        }
        if (fSudijaTim.getTxtEfikasnost().getText().isEmpty()) {
            fSudijaTim.getLblErrprEfikasnost().setText("Unesite ocenu efikasnosti!");
            pom = false;
        }
        if (fSudijaTim.getTxtSlozenost().getText().isEmpty()) {
            fSudijaTim.getLblErrorSlozenost().setText("Unesite ocenu slozenosti!");
            pom = false;
        }

        try {
            int dizajn = Integer.valueOf(fSudijaTim.getTxtDizajn().getText());
            if (dizajn < 1 || dizajn > 5) {
                fSudijaTim.getLblErrorDizajn().setText("Ocena mora biti izmedju 1 i 5 !");
                pom = false;
            }

        } catch (NumberFormatException e) {
            fSudijaTim.getLblErrorDizajn().setText("Pogresan unos");
            pom = false;
        }

        try {
            int efikasnost = Integer.valueOf(fSudijaTim.getTxtEfikasnost().getText());
            if (efikasnost < 1 || efikasnost > 5) {
                fSudijaTim.getLblErrprEfikasnost().setText("Ocena mora biti izmedju 1 i 5 !");
                pom = false;
            }
        } catch (NumberFormatException e) {
            fSudijaTim.getLblErrprEfikasnost().setText("Pogresan unos");
            pom = false;
        }

        try {
            int slozenost = Integer.valueOf(fSudijaTim.getTxtSlozenost().getText());
            if (slozenost < 1 || slozenost > 5) {
                fSudijaTim.getLblErrorSlozenost().setText("Ocena mora biti izmedju 1 i 5 !");
                pom = false;
            }
        } catch (NumberFormatException e) {
            fSudijaTim.getLblErrorSlozenost().setText("Pogresan unos");
            pom = false;
        }

        return pom;
    }

    private void izbrisi() {
        fSudijaTim.getLblErrorDizajn().setText("");
        fSudijaTim.getLblErrorSlozenost().setText("");
        fSudijaTim.getLblErrprEfikasnost().setText("");

        fSudijaTim.getLblErrorDizajn().setForeground(Color.red);
        fSudijaTim.getLblErrorSlozenost().setForeground(Color.red);
        fSudijaTim.getLblErrprEfikasnost().setForeground(Color.red);
    }

    private void fillTimData() {
        Tim t = (Tim) GUIKordinator.getInstance().getMap().get("selected_tim");
        fSudijaTim.getTxtNazivTima().setText(t.getNaziv());
        fSudijaTim.getTxtMentorTima().setText(t.getMentor().getIme() + " " + t.getMentor().getPrezime());
        fSudijaTim.getTxtNazivTima().setEditable(false);
        fSudijaTim.getTxtMentorTima().setEditable(false);
    }

    private void preparePanelOcena() throws Exception {
        Sudija sudija = (Sudija) GUIKordinator.getInstance().getMap().get("active_user");
        Tim tim = (Tim) GUIKordinator.getInstance().getMap().get("selected_tim");
        HashMap<String, Object> mapa = new HashMap<>();
        mapa.put("sudija", sudija);
        mapa.put("tim", tim);
        List<Ocena> ocene = KomunikacijaSaServerom.getInstanca().vratiOcenuZaSudijuITim(mapa);
        if (ocene.isEmpty()) {
            fSudijaTim.getBtnOceni().setEnabled(true);
            fSudijaTim.getLblOcenjen().setVisible(false);
        } else {
            Ocena o = ocene.get(0);
            fSudijaTim.getTxtDizajn().setText(String.valueOf(o.getDizajn()));
            fSudijaTim.getTxtEfikasnost().setText(String.valueOf(o.getEfikasnost()));
            fSudijaTim.getTxtSlozenost().setText(String.valueOf(o.getSlozenost()));
            fSudijaTim.getTxtKomentar().setText(String.valueOf(o.getKomentar()));
            fSudijaTim.getTxtDizajn().setEditable(false);
            fSudijaTim.getTxtEfikasnost().setEditable(false);
            fSudijaTim.getTxtSlozenost().setEditable(false);
            fSudijaTim.getTxtKomentar().setEditable(false);
            fSudijaTim.getBtnOceni().setEnabled(false);
            fSudijaTim.getLblOcenjen().setVisible(true);
            //fSudijaTim.getTxtKomentar().setEnabled(false);
        }
    }

}
