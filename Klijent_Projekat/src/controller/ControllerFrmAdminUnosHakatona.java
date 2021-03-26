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
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.form.FrmAdminGlavna;
import ui.form.FrmAdminUnosHakatona;
import ui.table.model.SudijeTableModel;

/**
 *
 * @author Nikola
 */
public class ControllerFrmAdminUnosHakatona {

    private FrmAdminUnosHakatona frmAdminUnosHakatona;
    private final Map<String, Object> map;

    public ControllerFrmAdminUnosHakatona() {
        this.map = new HashMap<>();
    }

    void otvoriFrmAdminUnosHakatona(FrmAdminGlavna frmAdminGlavna) throws Exception {
        frmAdminUnosHakatona = new FrmAdminUnosHakatona(frmAdminGlavna, true);
        prepareForm();
        addFrmListeners();
        frmAdminUnosHakatona.setVisible(true);
    }

    private void prepareForm() throws Exception {
        frmAdminUnosHakatona.setLocationRelativeTo(null);
        izbrisi();
        pripremiPanele();
    }

    private void addFrmListeners() {
        frmAdminUnosHakatona.addBtnIzbaciSudijuListener((ActionEvent ae) -> {
            SudijeTableModel stm = (SudijeTableModel) frmAdminUnosHakatona.getTblSudija().getModel();
            int selectedRow = frmAdminUnosHakatona.getTblSudija().getSelectedRow();
            try {
                frmAdminUnosHakatona.getTblSudija().setRowSelectionInterval(0, 0);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frmAdminUnosHakatona, "Trenutno nema sudija!");
                return;
            }
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frmAdminUnosHakatona, "Izaberite sudiju");

            } else {
                try {
                    Sudija s = stm.getSudija(selectedRow);
                    SudijeTableModel stm2 = (SudijeTableModel) frmAdminUnosHakatona.getTblIzborSudije().getModel();
                    stm2.addSudija(s);
                    stm.removeClan(selectedRow);

                } catch (Exception ex) {
                    Logger.getLogger(ControllerFrmSudijaHakaton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmAdminUnosHakatona.addBtnSacuvajActionListener((ActionEvent ae) -> {

            
                if (validacija()) {
                    String nazivHakatona = frmAdminUnosHakatona.getTxtNazivHakatona().getText();
                    Date datum = frmAdminUnosHakatona.getDateChooserDatum().getSelectedDate().getTime();
                    SudijeTableModel stm = (SudijeTableModel) frmAdminUnosHakatona.getTblSudija().getModel();
                    List<Sudija> listaSudija = stm.getLista();
                    Administrator admin = (Administrator) GUIKordinator.getInstance().getMap().get("active_user");
                    Hakaton h = new Hakaton(0, nazivHakatona, datum, admin);
                    try {
                        Hakaton kreiraHakaton = KomunikacijaSaServerom.getInstanca().kreirajHakaton(h, listaSudija);
                        /*
                    Hakaton kreiraniHakaton = KomunikacijaSaServerom.getInstanca().kreirajHakaton(h);
                    //System.out.println(kreiraniHakaton);
                    //List<Hakaton> lista = KomunikacijaSaServerom.getInstanca().vratiHakatone("ORDER BY HakatonID DESC LIMIT 1");
                    //Hakaton kreiraniHakaton = lista.get(0);
                    //System.out.println(kreiraniHakaton.getHakatonID());
                    HashMap<String, Object> mapica = new HashMap<>();
                    //mapica.put("sudija", kreiraniHakaton);
                    mapica.put("admin", admin);
                    List<Hakaton> lista = KomunikacijaSaServerom.getInstanca().vratiPoslednjiHakaton();
                    Hakaton poslednji = lista.get(0);
                    mapica.put("hakaton", poslednji);

                    for (Sudija sudija : listaSudija) {
                        mapica.put("sudija", sudija);
                        SudijaHakaton veza = KomunikacijaSaServerom.getInstanca().ubaciSudijeUHakaton(mapica);
                        System.out.println(veza);
                    }
                         */
                        izbrisi();
                        //setErrorLabesInvisible();
                        isprazni();
                        pripremiPanele();

                        JOptionPane.showMessageDialog(frmAdminUnosHakatona, "Hakaton je uspesno kreiran! ");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmAdminUnosHakatona, "Sistem ne moze da kreira hakaton!");
                        Logger.getLogger(FrmAdminGlavna.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            
        });

        frmAdminUnosHakatona.addBtnOtkaziActionListener((ActionEvent ae) -> {
            frmAdminUnosHakatona.dispose();
        });

        frmAdminUnosHakatona.addBtnDodajSudijuListener((ActionEvent ae) -> {
            SudijeTableModel stm = (SudijeTableModel) frmAdminUnosHakatona.getTblIzborSudije().getModel();
            int selectedRow = frmAdminUnosHakatona.getTblIzborSudije().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frmAdminUnosHakatona, "Izaberite sudiju");
                if (!stm.getLista().isEmpty()) {
                    frmAdminUnosHakatona.getTblIzborSudije().setRowSelectionInterval(0, 0);
                }
            } else {
                try {
                    Sudija s = stm.getSudija(selectedRow);
                    SudijeTableModel stm2 = (SudijeTableModel) frmAdminUnosHakatona.getTblSudija().getModel();
                    stm2.addSudija(s);
                    stm.removeClan(selectedRow);
                    //GUIKordinator.getInstance().getMap().put("selected_tim", t);
                    //System.out.println(t);
                    /*JDialog frame = new FRezervacija();
                    frame.setVisible(true);*/
                    //GUIKordinator.getInstance().otvoriFrmSudijaTim(fsudijaHakaton);
                } catch (Exception ex) {
                    Logger.getLogger(ControllerFrmSudijaHakaton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void pripremiPanele() throws Exception {
        srediTabeluSudija();
        srediTabeluIzborSudija();
        srediDateChooser();

    }

    private void izbrisi() {
        frmAdminUnosHakatona.getLblErrorNaziv().setText("");
        frmAdminUnosHakatona.getLblErrorNaziv().setForeground(Color.red);
    }

    private boolean validacija() {
        boolean pom = true;
        izbrisi();
        if (frmAdminUnosHakatona.getTxtNazivHakatona().getText().isEmpty()) {
            frmAdminUnosHakatona.getLblErrorNaziv().setText("Unesite naziv hakatona!");
            pom = false;
        }
        return pom;
    }

    private void isprazni() {
        frmAdminUnosHakatona.getTxtNazivHakatona().setText("");

    }

    private void srediTabeluSudija() {
        frmAdminUnosHakatona.getTblSudija().setModel(new SudijeTableModel(new ArrayList<>()));
    }

    private void srediTabeluIzborSudija() throws Exception {
        List<Sudija> sveSudije = KomunikacijaSaServerom.getInstanca().vratiSudije("");
        List<SudijaHakaton> veze = KomunikacijaSaServerom.getInstanca().vratiVeze();
        List<Sudija> zauzeteSudije = new ArrayList<>();

        veze.forEach((sudijaHakaton) -> {
            zauzeteSudije.add(sudijaHakaton.getSudija());
        });

        List<Sudija> validneSudije = new ArrayList<>();
        zauzeteSudije.stream().filter((sudija) -> (sveSudije.contains(sudija))).forEachOrdered((sudija) -> {
            sveSudije.remove(sudija);
        });

        List<Sudija> listaSudija = KomunikacijaSaServerom.getInstanca().vratiSudije();
        frmAdminUnosHakatona.getTblIzborSudije().setModel(new SudijeTableModel(sveSudije));
    }

    private void srediDateChooser() {
        frmAdminUnosHakatona.getDateChooserDatum().setSelectedDate(Calendar.getInstance());
        frmAdminUnosHakatona.getDateChooserDatum().setMinDate(Calendar.getInstance());
    }
}
