/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class Administrator implements Serializable, DomainObject {

    private int administratorID;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private String mail;
    private String tipAdministratora;

    public Administrator() {
    }

    public Administrator(int administratorID, String korisnickoIme, String lozinka, String ime, String prezime, String mail, String tipAdministratora) {
        this.administratorID = administratorID;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.tipAdministratora = tipAdministratora;
    }

    public String getTipAdministratora() {
        return tipAdministratora;
    }

    public void setTipAdministratora(String tipAdministratora) {
        this.tipAdministratora = tipAdministratora;
    }

    public int getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "administrator";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "AdministratorID,KorisnickoIme,Lozinka,Ime,Prezime,Mail,TipAdministratora";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getAdministratorID() + "', '" + getKorisnickoIme() + "', '" + getLozinka() + "', '" + getIme() + "', '" + getPrezime() + "', " + getMail() + ", '" + getTipAdministratora() + "'";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int adminID = rs.getInt("AdministratorID");
            String username = rs.getString("KorisnickoIme");
            String pass = rs.getString("Lozinka");
            String name = rs.getString("Ime");
            String lastname = rs.getString("Prezime");
            String email = rs.getString("Mail");
            String adminType = rs.getString("TipAdministratora");
            Administrator a = new Administrator(adminID, username, pass, name, lastname, email, adminType);
            lista.add(a);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return "";
    }

    @Override
    public String getKeyName() {
        return "AdministratorID";
    }

    @Override
    public String getKeyValue() {
        return administratorID + "";
    }

    @Override
    public String getUpdateStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getKeyNameAndValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
