/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class Hakaton implements Serializable, DomainObject {

    private int hakatonID;
    private String naziv;
    private Date datum;
    private Administrator admin;

    public Hakaton() {
    }

    public Hakaton(int hakatonID, String naziv, Date datum, Administrator admin) {
        this.hakatonID = hakatonID;
        this.naziv = naziv;
        this.datum = datum;
        this.admin = admin;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public int getHakatonID() {
        return hakatonID;
    }

    public void setHakatonID(int hakatonID) {
        this.hakatonID = hakatonID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Hakaton other = (Hakaton) obj;
        if (this.hakatonID != other.hakatonID) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "hakaton";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "Naziv,Datum,AdministratorID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getNaziv() + "', '" + new java.sql.Date(getDatum().getTime()) + "', '" + getAdmin().getAdministratorID() + "'";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            //administrator
            int adminID = rs.getInt("a.AdministratorID");
            String username = rs.getString("a.KorisnickoIme");
            String pass = rs.getString("a.Lozinka");
            String name = rs.getString("a.Ime");
            String lastname = rs.getString("a.Prezime");
            String email = rs.getString("a.Mail");
            String adminType = rs.getString("a.TipAdministratora");
            Administrator a = new Administrator(adminID, username, pass, name, lastname, email, adminType);

            //Hakaton
            int hakatonID = rs.getInt("h.HakatonID");
            String naziv = rs.getString("h.Naziv");
            java.sql.Date datum = rs.getDate("h.Datum");

            Hakaton h = new Hakaton(hakatonID, naziv, datum, a);

            lista.add(h);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " h join administrator a on h.AdministratorID=a.AdministratorID" + str;
    }

    @Override
    public String getKeyName() {
        return "HakatonID";
    }

    @Override
    public String getKeyValue() {
        return hakatonID + "";
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
