/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.KomunikacijaSaServerom;
import domain.Sudija;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import ui.form.FrmAdminGlavna;
import ui.form.FrmAdminUnosSudije;

/**
 *
 * @author Nikola
 */
public class ControllerFrmAdminUnosSudije {

    private FrmAdminUnosSudije frmUnosSudije;
    private final Map<String, Object> map;

    public ControllerFrmAdminUnosSudije() {
        map = new HashMap<>();
    }

    void otvoriFrmUnosSudije(FrmAdminGlavna fadminGlavna) {
        frmUnosSudije = new FrmAdminUnosSudije(fadminGlavna, true);
        prepareForm();
        addFrmListeners();
        frmUnosSudije.setVisible(true);

    }

    private void prepareForm() {
        frmUnosSudije.setLocationRelativeTo(null);
        izbrisi();
        //setErrorLabesInvisible();
        PripremiPanel();
    }

    private void PripremiPanel() {

    }

    private void addFrmListeners() {
        frmUnosSudije.addBtnSacuvajListener((ActionEvent ae) -> {
            try {
                if (validacija()) {
                    String korisnickoIme = frmUnosSudije.getTxtKorisnickoIme().getText();
                    String lozinka = frmUnosSudije.getTxtLozinka().getText();
                    String ime = frmUnosSudije.getTxtIme().getText();
                    String prezime = frmUnosSudije.getTxtPrezime().getText();
                    String profesija = frmUnosSudije.getTxtProfesija().getText();
                    String mail = frmUnosSudije.getTxtMail().getText();
                    String zemlja = frmUnosSudije.getTxtZemlja().getText();
                    Sudija s = new Sudija(0, korisnickoIme, lozinka, ime, prezime, mail, profesija, zemlja);

                    KomunikacijaSaServerom.getInstanca().kreirajSudiju(s);
                    izbrisi();
                    //setErrorLabesInvisible();
                    isprazni();

                    JOptionPane.showMessageDialog(frmUnosSudije, "Sudija je uspesno kreiran! ");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmUnosSudije, "Sistem ne moze da kreira sudiju!", "Greska", JOptionPane.ERROR_MESSAGE);

            }
        });

        frmUnosSudije.addBtnOtkaziActionListener((ActionEvent ae) -> {
            frmUnosSudije.dispose();
        });
    }

    private boolean validacija() throws Exception {
        boolean pom = true;
        izbrisi();
        if (frmUnosSudije.getTxtIme().getText().isEmpty()) {
            frmUnosSudije.getLblErrorIme().setText("Unesite ime!");
            pom = false;
        }
        if (validacijaUsernameUnikatan((frmUnosSudije.getTxtKorisnickoIme().getText()))) {
            frmUnosSudije.getLblErrorKorisnickoIme().setText("Korisnicko ime mora biti jedinstveno!");
            pom = false;
        }
        if (frmUnosSudije.getTxtPrezime().getText().isEmpty()) {
            frmUnosSudije.getLblErrorPrezime().setText("Unesite prezimeime!");
            pom = false;
        }
        if (frmUnosSudije.getTxtKorisnickoIme().getText().isEmpty()) {
            frmUnosSudije.getLblErrorKorisnickoIme().setText("Unesite korisnicko ime!");
            pom = false;
        }
        if (frmUnosSudije.getTxtLozinka().getText().isEmpty()) {
            frmUnosSudije.getLblErrorLozinka().setText("Unesite lozinku!");
            pom = false;
        }
        if (frmUnosSudije.getTxtMail().getText().isEmpty()) {
            frmUnosSudije.getLblErrorMail().setText("Unesite mail!");
            pom = false;
        }
        if (frmUnosSudije.getTxtProfesija().getText().isEmpty()) {
            frmUnosSudije.getLblErrorProfesija().setText("Unesite profesiju!");
            pom = false;
        }
        if (frmUnosSudije.getTxtZemlja().getText().isEmpty()) {
            frmUnosSudije.getLblErrorZemlja().setText("Unesite zemlju!");
            pom = false;
        }

        return pom;
    }

    private void izbrisi() {
        frmUnosSudije.getLblErrorIme().setText("");
        frmUnosSudije.getLblErrorPrezime().setText("");
        frmUnosSudije.getLblErrorKorisnickoIme().setText("");
        frmUnosSudije.getLblErrorLozinka().setText("");
        frmUnosSudije.getLblErrorMail().setText("");
        frmUnosSudije.getLblErrorProfesija().setText("");
        frmUnosSudije.getLblErrorZemlja().setText("");

        frmUnosSudije.getLblErrorIme().setForeground(Color.red);
        frmUnosSudije.getLblErrorKorisnickoIme().setForeground(Color.red);
        frmUnosSudije.getLblErrorLozinka().setForeground(Color.red);
        frmUnosSudije.getLblErrorMail().setForeground(Color.red);
        frmUnosSudije.getLblErrorPrezime().setForeground(Color.red);
        frmUnosSudije.getLblErrorProfesija().setForeground(Color.red);
        frmUnosSudije.getLblErrorZemlja().setForeground(Color.red);

    }

    private void setErrorLabesInvisible() {
        frmUnosSudije.getLblErrorIme().setVisible(false);
        frmUnosSudije.getLblErrorKorisnickoIme().setVisible(false);
        frmUnosSudije.getLblErrorLozinka().setVisible(false);
        frmUnosSudije.getLblErrorMail().setVisible(false);
        frmUnosSudije.getLblErrorPrezime().setVisible(false);
        frmUnosSudije.getLblErrorProfesija().setVisible(false);
        frmUnosSudije.getLblErrorZemlja().setVisible(false);
    }

    private void isprazni() {
        frmUnosSudije.getTxtZemlja().setText("");
        frmUnosSudije.getTxtIme().setText("");
        frmUnosSudije.getTxtKorisnickoIme().setText("");
        frmUnosSudije.getTxtLozinka().setText("");
        frmUnosSudije.getTxtPrezime().setText("");
        frmUnosSudije.getTxtMail().setText("");
        frmUnosSudije.getTxtProfesija().setText("");
    }

    private boolean validacijaUsernameUnikatan(String string) throws Exception {
        String s = "" + frmUnosSudije.getTxtKorisnickoIme().getText();
        List<Sudija> sudija = KomunikacijaSaServerom.getInstanca().vratiSudijuZaUsername(s);
        return !sudija.isEmpty();
    }
}
