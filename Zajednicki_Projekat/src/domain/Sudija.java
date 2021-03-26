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
public class Sudija implements Serializable, DomainObject {

    private int sudijaID;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private String mail;
    private String profesija;
    private String zemlja;

    public Sudija() {
    }

    public Sudija(int sudijaID, String korisnickoIme, String lozinka, String ime, String prezime, String mail, String profesija, String zemlja) {
        this.sudijaID = sudijaID;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.profesija = profesija;
        this.zemlja = zemlja;
    }

    public String getZemlja() {
        return zemlja;
    }

    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }

    public int getSudijaID() {
        return sudijaID;
    }

    public void setSudijaID(int sudijaID) {
        this.sudijaID = sudijaID;
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

    public String getProfesija() {
        return profesija;
    }

    public void setProfesija(String profesija) {
        this.profesija = profesija;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sudija other = (Sudija) obj;
        return this.sudijaID == other.sudijaID;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "sudija";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "KorisnickoIme,Lozinka,Ime,Prezime,Mail,Profesija,Zemlja";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getKorisnickoIme() + "', '" + getLozinka() + "', '" + getIme() + "', '" + getPrezime() + "', '" + getMail() + "', '" + getProfesija() + "', '" + getZemlja() + "'";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int sID = rs.getInt("s.SudijaID");
            String username = rs.getString("s.KorisnickoIme");
            String pass = rs.getString("s.Lozinka");
            String name = rs.getString("s.Ime");
            String lastname = rs.getString("s.Prezime");
            String email = rs.getString("s.Mail");
            String profesijaa = rs.getString("s.Profesija");
            String zemljaa = rs.getString("s.Zemlja");
            Sudija s = new Sudija(sID, username, pass, name, lastname, email, profesijaa, zemljaa);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " s" + str;
    }

    @Override
    public String getKeyName() {
        return "SudijaID";
    }

    @Override
    public String getKeyValue() {
        return sudijaID + "";
    }

    @Override
    public String getUpdateStatement() {
        return "SudijaID = " + sudijaID
                + ", KorisnickoIme = " + korisnickoIme
                + ", Lozinka = " + lozinka
                + ", Ime = " + ime
                + ", Prezime = " + prezime
                + ", Mail = " + mail
                + ", Profesija = " + profesija
                + ", Zemlja = " + zemlja
                + "";
    }

    @Override
    public String getKeyNameAndValues() {
        return " SudijaID=" + sudijaID;
    }

}
