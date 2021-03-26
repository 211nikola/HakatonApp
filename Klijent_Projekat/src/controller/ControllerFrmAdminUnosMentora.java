/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.KomunikacijaSaServerom;
import domain.Administrator;
import domain.Mentor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.form.FrmAdminGlavna;
import ui.form.FrmAdminUnosMentora;

/**
 *
 * @author Nikola
 */
public class ControllerFrmAdminUnosMentora {

    private FrmAdminUnosMentora frmAdminUnosMentora;
    private final Map<String, Object> map;

    public ControllerFrmAdminUnosMentora() {
        this.map = new HashMap<>();
    }

    void otvoriFrmAdminUnosMentora(FrmAdminGlavna frmAdminGlavna) {
        frmAdminUnosMentora = new FrmAdminUnosMentora(frmAdminGlavna, true);
        prepareForm();
        addFormListeners();
        frmAdminUnosMentora.setVisible(true);   
    }

    private void addFormListeners() {

        frmAdminUnosMentora.addBtnSacuvajListener((ActionEvent ae) -> {
            if (validacija()) {
                String ime = frmAdminUnosMentora.getTxtIme().getText();
                String prezime = frmAdminUnosMentora.getTxtPrezime().getText();
                String mail = frmAdminUnosMentora.getTxtMail().getText();
                String profesija = frmAdminUnosMentora.getTxtProfesija().getText();
                Administrator admin = (Administrator) GUIKordinator.getInstance().getMap().get("active_user");
                Mentor m = new Mentor(0, ime, prezime, mail, profesija, admin);
                try {
                    KomunikacijaSaServerom.getInstanca().kreirajMentora(m);
                    izbrisi();
                    //setErrorLabesInvisible();
                    isprazni();
                    //pripremiPanele();

                    JOptionPane.showMessageDialog(frmAdminUnosMentora, "Mentor je uspesno kreiran! ");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAdminUnosMentora, "Sistem ne moze da kreira mentora!");
                    Logger.getLogger(FrmAdminGlavna.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
               // JOptionPane.showMessageDialog(frmAdminUnosMentora, "Sistem ne moze da kreira mentora", "Greska", JOptionPane.ERROR_MESSAGE);

            }
        });

        frmAdminUnosMentora.addBtnOtkaziListener((ActionEvent ae) -> {
            frmAdminUnosMentora.dispose();
        });
    }

    private void prepareForm() {
        frmAdminUnosMentora.setLocationRelativeTo(null);
        izbrisi();
    }

    private void izbrisi() {
        frmAdminUnosMentora.getLblErrorIme().setText("");
        frmAdminUnosMentora.getLblErrorMail().setText("");
        frmAdminUnosMentora.getLblErrorPrezime().setText("");
        frmAdminUnosMentora.getLblErrorProfesija().setText("");

        frmAdminUnosMentora.getLblErrorIme().setForeground(Color.red);
        frmAdminUnosMentora.getLblErrorMail().setForeground(Color.red);
        frmAdminUnosMentora.getLblErrorPrezime().setForeground(Color.red);
        frmAdminUnosMentora.getLblErrorProfesija().setForeground(Color.red);
    }

    private boolean validacija() {
        boolean pom = true;
        izbrisi();
        if (frmAdminUnosMentora.getTxtIme().getText().isEmpty()) {
            frmAdminUnosMentora.getLblErrorIme().setText("Unesite ime!");
            pom = false;
        }
        if (frmAdminUnosMentora.getTxtMail().getText().isEmpty()) {
            frmAdminUnosMentora.getLblErrorMail().setText("Unesite mail!");
            pom = false;
        }
        if (frmAdminUnosMentora.getTxtPrezime().getText().isEmpty()) {
            frmAdminUnosMentora.getLblErrorPrezime().setText("Unesite prezime!");
            pom = false;
        }
        if (frmAdminUnosMentora.getTxtProfesija().getText().isEmpty()) {
            frmAdminUnosMentora.getLblErrorProfesija().setText("Unesite profesiju!");
            pom = false;
        }
        return pom;
    }

    private void isprazni() {
        frmAdminUnosMentora.getTxtIme().setText("");
        frmAdminUnosMentora.getTxtPrezime().setText("");
        frmAdminUnosMentora.getTxtMail().setText("");
        frmAdminUnosMentora.getTxtProfesija().setText("");

    }

}
