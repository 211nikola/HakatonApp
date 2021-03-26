/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import thread.OsluskivacAdmin;
import ui.form.FrmAdminGlavna;
import ui.form.PnlClanoviPregled;

import ui.form.PnlHakatonPregled;
import ui.form.PnlMentorPregled;
import ui.form.PnlSudijaPregled;
import ui.form.PnlTimoviPregled;

/**
 *
 * @author Nikola
 */
public class ControllerFrmAdminGlavna {

    private FrmAdminGlavna fadministrator;
    private final Map<String, Object> map;
    private final CardLayout cardLayout;
    private JPanel panelPretraga;

    public ControllerFrmAdminGlavna() {
        map = new HashMap<>();
        cardLayout = new CardLayout();

    }

    void otvoriAdministratorFormu() throws Exception {
        fadministrator = new FrmAdminGlavna();
        addFrmAdminListener();
        prepareForm();

        fadministrator.setVisible(true);
        fadministrator.pack();
        new OsluskivacAdmin(fadministrator).start();
    }

    private void prepareForm() throws Exception {
        fadministrator.setLocationRelativeTo(null);
        Administrator admin = (Administrator) GUIKordinator.getInstance().getMap().get("active_user");
        fadministrator.getLblPrijavljeniAdministrator().setText("Prijavljeni administrator: " + admin.getIme());
        PreparePanel();
    }

    private void PreparePanel() throws Exception {

        panelPretraga = fadministrator.getPnlPretraga();
        fadministrator.getPnlPretraga().setLayout(cardLayout);

        PnlHakatonPregled panelHakaton = new PnlHakatonPregled();
        PnlMentorPregled panelMentor = new PnlMentorPregled();
        PnlSudijaPregled panelSudija = new PnlSudijaPregled();
        PnlTimoviPregled panelTim = new PnlTimoviPregled();
        PnlClanoviPregled panelClan = new PnlClanoviPregled();

        fadministrator.getPnlPretraga().add(panelHakaton, "hakaton");
        fadministrator.getPnlPretraga().add(panelMentor, "mentor");
        fadministrator.getPnlPretraga().add(panelSudija, "sudija");
        fadministrator.getPnlPretraga().add(panelTim, "tim");
        fadministrator.getPnlPretraga().add(panelClan, "clan");
    }

    public void addFrmAdminListener() {

        fadministrator.addMIPregledClanovaListener((ActionEvent ae) -> {
            cardLayout.show(panelPretraga, "clan");
        });

        fadministrator.addMiPregledTimovaListener((ActionEvent ae) -> {
            cardLayout.show(panelPretraga, "tim");
        });

        fadministrator.addMiUnosTimaListener((ActionEvent ae) -> {
            try {
                GUIKordinator.getInstance().otvoriFrmUnosTima(fadministrator);
            } catch (Exception ex) {
                Logger.getLogger(ControllerFrmAdminGlavna.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        fadministrator.addMiPregledSuijeActionListener((ActionEvent ae) -> {
            cardLayout.show(panelPretraga, "sudija");
        });

        fadministrator.addMiPregledHakatonaListener((ActionEvent ae) -> {
            cardLayout.show(panelPretraga, "hakaton");
        });

        fadministrator.addMiPregledMentoraActionListener((ActionEvent ae) -> {
            cardLayout.show(panelPretraga, "mentor");
        });

        fadministrator.addMiUnosMentoraActionListener((ActionEvent ae) -> {
            GUIKordinator.getInstance().otvoriFrmAdminUnosMentora(fadministrator);
        });

        fadministrator.addBtnKrajRadaListener((ActionEvent ae) -> {
            fadministrator.dispose();
        });

        fadministrator.addMiUnosSudijeActionListener((ActionEvent ae) -> {
            GUIKordinator.getInstance().otvoriFrmUnosSudije(fadministrator);
        });

        fadministrator.addMiUnosHakatonaListener((ActionEvent ae) -> {
            try {
                GUIKordinator.getInstance().otvoriFrmAdminUnosHakatona(fadministrator);
            } catch (Exception ex) {
                Logger.getLogger(ControllerFrmAdminGlavna.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
