/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import ui.form.FrmAdminIzborSudije;
import ui.form.FrmAdminUnosHakatona;

/**
 *
 * @author Nikola
 */
public class ControllerFrmAdminIzborSudije {

    private FrmAdminIzborSudije frmAdminIzborSudije;
    private final Map<String, Object> map;

    public ControllerFrmAdminIzborSudije() {
        this.map = new HashMap<>();
    }

    void otvoriFrmAdminIzborSudije(FrmAdminUnosHakatona frmUnosHakatona) {
        frmAdminIzborSudije = new FrmAdminIzborSudije(frmUnosHakatona, true);
    }

}
