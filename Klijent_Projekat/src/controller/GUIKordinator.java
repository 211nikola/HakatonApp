/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import ui.form.FrmAdminGlavna;
import ui.form.FrmAdminUnosHakatona;
import ui.form.FrmSudijaHakaton;

/**
 *
 * @author Nikola
 */
public class GUIKordinator {

    private static GUIKordinator instance;
    private Map<String, Object> map;
    private ControllerFrmSudijaHakaton controllerFrmSudija;
    private ControllerFrmLogin controllerFrmLogin;
    private ControllerFrmAdminGlavna controllerFrmAdministrator;
    private ControllerFrmSudijaTim controllerFrmSudijaTim;
    private ControllerFrmAdminUnosSudije controllerFrmAdminUnosSudije;
    private ControllerFrmAdminUnosHakatona controllerFrmAdminUnosHakatona;
    private ControllerFrmAdminIzborSudije controllerFrmAdminIzborSudije;
    private ControllerFrmAdminUnosMentora controllerFrmAdminUnosMentora;
    private ControllerFrmAdminUnosTima controllerFrmAdminUnosTima;

    public GUIKordinator() {
        map = new HashMap<>();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> aMap) {
        map = aMap;
    }

    public static GUIKordinator getInstance() {
        if (instance == null) {
            instance = new GUIKordinator();
        }
        return instance;
    }

    public void otvoriFrmSudija() throws Exception {
        if (controllerFrmSudija == null) {
            controllerFrmSudija = new ControllerFrmSudijaHakaton();
        }
        controllerFrmSudija.otvoriFormuSudija();
    }

    public void otvoriFrmLogin() {
        if (controllerFrmLogin == null) {
            controllerFrmLogin = new ControllerFrmLogin();
        }
        controllerFrmLogin.otvoriLogin();
    }

    void otvoriFrmAdministrator() throws Exception {
        if (controllerFrmAdministrator == null) {
            controllerFrmAdministrator = new ControllerFrmAdminGlavna();
        }
        controllerFrmAdministrator.otvoriAdministratorFormu();
    }

    void otvoriFrmSudijaTim(FrmSudijaHakaton fsudija) throws Exception {
        if (controllerFrmSudijaTim == null) {
            controllerFrmSudijaTim = new ControllerFrmSudijaTim();
        }
        controllerFrmSudijaTim.otvoriFrmSudijaTim(fsudija);
    }

    void otvoriFrmUnosSudije(FrmAdminGlavna fadminGlavna) {
        if (controllerFrmAdminUnosSudije == null) {
            controllerFrmAdminUnosSudije = new ControllerFrmAdminUnosSudije();
        }
        controllerFrmAdminUnosSudije.otvoriFrmUnosSudije(fadminGlavna);
    }

    void otvoriFrmAdminUnosHakatona(FrmAdminGlavna frmAdminGlavna) throws Exception {
        if (controllerFrmAdminUnosHakatona == null) {
            controllerFrmAdminUnosHakatona = new ControllerFrmAdminUnosHakatona();
        }
        controllerFrmAdminUnosHakatona.otvoriFrmAdminUnosHakatona(frmAdminGlavna);
    }

    void otvoriFrmAdminIzborSudije(FrmAdminUnosHakatona frmUnosHakatona) {
        if (controllerFrmAdminIzborSudije == null) {
            controllerFrmAdminIzborSudije = new ControllerFrmAdminIzborSudije();
        }
        controllerFrmAdminIzborSudije.otvoriFrmAdminIzborSudije(frmUnosHakatona);
    }

    void otvoriFrmAdminUnosMentora(FrmAdminGlavna frmAdminGlavna) {
        if (controllerFrmAdminUnosMentora == null) {
            controllerFrmAdminUnosMentora = new ControllerFrmAdminUnosMentora();
        }
        controllerFrmAdminUnosMentora.otvoriFrmAdminUnosMentora(frmAdminGlavna);
    }

    void otvoriFrmUnosTima(FrmAdminGlavna fadministrator) throws Exception {
        if (controllerFrmAdminUnosTima == null) {
            controllerFrmAdminUnosTima = new ControllerFrmAdminUnosTima();
        }
        controllerFrmAdminUnosTima.otvoriFrmUnosTima(fadministrator);
    }
}
