/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.KomunikacijaSaServerom;
import domain.Administrator;
import domain.DomainObject;
import domain.Sudija;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import ui.form.FrmLogin;
import util.Operacija;

/**
 *
 * @author Nikola
 */
public class ControllerFrmLogin {

    private FrmLogin flogin;
    private final Map<String, Object> map;

    public ControllerFrmLogin() {
        map = new HashMap<>();
    }

    public void otvoriLogin() {
        flogin = new FrmLogin();
        addListenerFLogin();
        flogin.setLocationRelativeTo(null);
        flogin.setVisible(true);
    }

    private void addListenerFLogin() {
        flogin.addButtonOtkaziActionListener((ActionEvent ae) -> {
            flogin.dispose();
        });
        flogin.addButtonLoginActionListener((ActionEvent ae) -> {
            try {
                if (validate(flogin.getTxtUsername(), flogin.getTxtPassword())) {
                    DomainObject n = logIn(flogin.getTxtUsername().getText(), String.valueOf(flogin.getTxtPassword().getPassword()));
                    if (n != null && n instanceof Sudija) {

                        JOptionPane.showMessageDialog(flogin, "Uspesno prijavljivanje!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        GUIKordinator.getInstance().otvoriFrmSudija();
                        flogin.dispose();
                    } else if (n != null && n instanceof Administrator) {
                        JOptionPane.showMessageDialog(flogin, "Uspesno prijavljivanje!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        GUIKordinator.getInstance().otvoriFrmAdministrator();
                        flogin.dispose();
                    } else {
                        JOptionPane.showMessageDialog(flogin, "Neuspesno prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
                    }

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(flogin, "Sistem ne moze da izvrsi prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    private boolean validate(JTextField jtxtUsername, JPasswordField jpass) throws Exception {

        flogin.getLblErrorKorisnickoIme().setText("");
        flogin.getLblErrorLozinka().setText("");

        flogin.getLblErrorLozinka().setForeground(Color.red);
        flogin.getLblErrorKorisnickoIme().setForeground(Color.red);

        if (jtxtUsername.getText().isEmpty()) {
            flogin.getLblErrorKorisnickoIme().setText("Username is empty!");
            return false;
        }
        if (String.valueOf(jpass.getPassword()).isEmpty()) {
            flogin.getLblErrorLozinka().setText("Password is empty!");
            return false;
        }
        return true;
    }

    public DomainObject logIn(String user, String password) throws IOException, ClassNotFoundException, Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.LOGIN);

        Map<String, String> mapa = new HashMap<>();
        mapa.put("user", user);
        mapa.put("pass", password);
        kz.setParametar(mapa);

        KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        GUIKordinator.getInstance().getMap().put("active_user", so.getOdgovor());
        //map.put("active_user",  so.getOdgovor());
        return (DomainObject) so.getOdgovor();
    }

}
