/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.KomunikacijaSaServerom;
import domain.Administrator;
import domain.Clan;
import domain.Hakaton;
import domain.Mentor;
import domain.Sudija;
import domain.Tim;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import ui.form.FrmAdminGlavna;
import ui.form.FrmAdminUnosTima;
import ui.form.FrmTimUnosClana;
import ui.table.model.ClanoviTableModel;

/**
 *
 * @author Nikola
 */
public class ControllerFrmAdminUnosTima {

    private FrmAdminUnosTima frmAdminUnosTima;
    private final Map<String, Object> map;

    public ControllerFrmAdminUnosTima() {
        this.map = new HashMap<>();
    }

    void otvoriFrmUnosTima(FrmAdminGlavna frmAdminGlavna) throws Exception {
        frmAdminUnosTima = new FrmAdminUnosTima(frmAdminGlavna, true);
        prepareForm();
        addFormListeners();
        frmAdminUnosTima.setVisible(true);
    }

    private void prepareForm() throws Exception {
        frmAdminUnosTima.setLocationRelativeTo(null);
        izbrisi();
        napuniCmbHakatoni();
        napuniCmbMentori();
        srediTabelu();
    }

    private void addFormListeners() {

        frmAdminUnosTima.addBtnObrisiClanaActionListener((ActionEvent ae) -> {
            try {
                if (validacijaTabele()) {
                    ClanoviTableModel ctm = (ClanoviTableModel) frmAdminUnosTima.getTblClanovi().getModel();

                    ctm.removeClan(frmAdminUnosTima.getTblClanovi().getSelectedRow());

                    //JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem je obrisao clana!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem ne moze da obrise clana!", "greska", JOptionPane.ERROR_MESSAGE);
            }
        });

        frmAdminUnosTima.addBtnDodajActionListener((ActionEvent ae) -> {
            FrmTimUnosClana f = new FrmTimUnosClana(frmAdminUnosTima, true);
            f.setVisible(true);
        });

        frmAdminUnosTima.addBtnSacuvajListener((ActionEvent ae) -> {
            try {
                if (validacija()) {
                    String naziv = frmAdminUnosTima.getTxtNazivTima().getText();
                    Administrator a = (Administrator) GUIKordinator.getInstance().getMap().get("active_user");
                    Hakaton h = (Hakaton) frmAdminUnosTima.getCmbHakatoni().getSelectedItem();
                    Mentor m = (Mentor) frmAdminUnosTima.getCmbMentori().getSelectedItem();
                    ClanoviTableModel ctm = (ClanoviTableModel) frmAdminUnosTima.getTblClanovi().getModel();
                    List<Clan> clanovi = ctm.getLista();
                    if (h != null && m != null) {
                        Tim tim = new Tim(0, naziv, a, m, clanovi, new ArrayList<>(), new Sudija(), h);
                        KomunikacijaSaServerom.getInstanca().sacuvajTim(tim);
                        JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem je uspesno kreirao tim!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        izbrisi();
                        srediTabelu();
                        isprazni();
                        
                        /*
                        KomunikacijaSaServerom.getInstanca().sacuvajTim(tim);
                        List<Tim> timovi = KomunikacijaSaServerom.getInstanca().vratiPoslednjiTim();
                        Tim zadnjTim = timovi.get(0);
                        clanovi.forEach((clan) -> {
                            clan.setTim(zadnjTim);
                        });
                        clanovi.forEach((clan) -> {
                            try {
                                KomunikacijaSaServerom.getInstanca().sacuvajClana(clan);
                                System.out.println(clan);

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem ne moze da sacuva tim!", "Greska", JOptionPane.ERROR_MESSAGE);

                                Logger.getLogger(ControllerFrmAdminUnosTima.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem je kreirao tim!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                         */
                    } else {
                        JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem ne moze da sacuva tim!", "Greska", JOptionPane.ERROR_MESSAGE);

                    }

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frmAdminUnosTima, "Sistem ne moze da sacuva tim!", "Greska", JOptionPane.ERROR_MESSAGE);

            }
        });

        frmAdminUnosTima.addBtnOtkaziListener((ActionEvent ae) -> {
            frmAdminUnosTima.dispose();
        });
    }

    private void izbrisi() {
        frmAdminUnosTima.getLblErrorNaziv().setText("");
        frmAdminUnosTima.getLblErrorNaziv().setForeground(Color.red);
    }

    private boolean validacija() {
        boolean pom = true;
        izbrisi();
        if (frmAdminUnosTima.getTxtNazivTima().getText().isEmpty()) {
            frmAdminUnosTima.getLblErrorNaziv().setText("Unesite naziv!");
            pom = false;
        }

        return pom;
    }

    private void napuniCmbHakatoni() throws Exception {
        List<Hakaton> hakatoni = KomunikacijaSaServerom.getInstanca().vratiHakatone();
        System.out.println(hakatoni);
        frmAdminUnosTima.getCmbHakatoni().removeAllItems();
        hakatoni.forEach((hakaton) -> {
            frmAdminUnosTima.getCmbHakatoni().addItem(hakaton);
        });
    }

    private void napuniCmbMentori() throws Exception {
        List<Mentor> mentori = KomunikacijaSaServerom.getInstanca().vratiMentore();
        System.out.println(mentori);
        frmAdminUnosTima.getCmbMentori().removeAllItems();
        mentori.forEach((mentor) -> {
            frmAdminUnosTima.getCmbMentori().addItem(mentor);
        });
    }

    private void srediTabelu() throws Exception {
        //List<Clan> clanovi = KomunikacijaSaServerom.getInstanca().vratiClanove();
        //System.out.println(clanovi);
        frmAdminUnosTima.getTblClanovi().setModel(new ClanoviTableModel(new ArrayList<>()));
    }

    private boolean validacijaTabele() {
        return frmAdminUnosTima.getTblClanovi().getSelectedRow() != -1;
    }

    private void isprazni() {
        frmAdminUnosTima.getTxtNazivTima().setText("");
    }

}
