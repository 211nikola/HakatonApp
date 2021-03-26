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
public class Mentor implements Serializable, DomainObject {

    private int mentorID;
    private String ime;
    private String prezime;
    private String mail;
    private String profesija;
    private Administrator admin;

    public Mentor() {
    }

    public Mentor(int mentorID, String ime, String prezime, String mail, String profesija, Administrator admin) {
        this.mentorID = mentorID;
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.profesija = profesija;
        this.admin = admin;
    }

    public String getProfesija() {
        return profesija;
    }

    public void setProfesija(String profesija) {
        this.profesija = profesija;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
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
        final Mentor other = (Mentor) obj;
        return this.mentorID == other.mentorID;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    @Override
    public String getTableName() {
        return "mentor";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "Ime,Prezime,Mail,Profesija,AdministratorID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getIme() + "', '" + getPrezime() + "', '" + getMail() + "', '" + getProfesija() + "', '" + getAdmin().getAdministratorID() + "'";

    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            //Administrator
            int adminID = rs.getInt("a.AdministratorID");
            String username = rs.getString("a.KorisnickoIme");
            String pass = rs.getString("a.Lozinka");
            String name = rs.getString("a.Ime");
            String lastname = rs.getString("a.Prezime");
            String email = rs.getString("a.Mail");
            String adminType = rs.getString("a.TipAdministratora");
            Administrator a = new Administrator(adminID, username, pass, name, lastname, email, adminType);

            //mentor
            int mentorID = rs.getInt("m.MentorID");
            String ime = rs.getString("m.Ime");
            String prezime = rs.getString("m.Prezime");
            String mailM = rs.getString("m.Mail");
            String profesija = rs.getString("m.Profesija");
            Mentor m = new Mentor(mentorID, ime, prezime, mailM, profesija, a);

            lista.add(m);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " m join administrator a on m.AdministratorID=a.AdministratorID " + str;
    }

    @Override
    public String getKeyName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getKeyValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getKeyNameAndValues() {
        return " MentorID=" + mentorID;
    }

}
