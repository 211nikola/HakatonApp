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
import java.util.Objects;

/**
 *
 * @author Nikola
 */
public class SudijaHakaton implements Serializable, DomainObject {

    private Sudija sudija;
    private Hakaton hakaton;
    private Administrator admin;

    public SudijaHakaton() {
    }

    public SudijaHakaton(Sudija sudija, Hakaton hakaton, Administrator admin) {
        this.sudija = sudija;
        this.hakaton = hakaton;
        this.admin = admin;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public Sudija getSudija() {
        return sudija;
    }

    public void setSudija(Sudija sudija) {
        this.sudija = sudija;
    }

    public Hakaton getHakaton() {
        return hakaton;
    }

    public void setHakaton(Hakaton hakaton) {
        this.hakaton = hakaton;
    }

    @Override
    public String getTableName() {
        return "sudijahakaton";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "SudijaHakaton{" + "sudija=" + sudija + ", hakaton=" + hakaton + ", admin=" + admin + '}';
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
        final SudijaHakaton other = (SudijaHakaton) obj;
        if (!Objects.equals(this.sudija, other.sudija)) {
            return false;
        }
        return Objects.equals(this.admin, other.admin);
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "SudijaID,HakatonID,AdministratorID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getSudija().getSudijaID() + "', " + getHakaton().getHakatonID() + ", '" + getAdmin().getAdministratorID() + "'";

    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            //administrator
            int adminID2 = rs.getInt("a.AdministratorID");
            String username2 = rs.getString("a.KorisnickoIme");
            String pass2 = rs.getString("a.Lozinka");
            String name2 = rs.getString("a.Ime");
            String lastname2 = rs.getString("a.Prezime");
            String email2 = rs.getString("a.Mail");
            String adminType2 = rs.getString("a.TipAdministratora");
            Administrator admin = new Administrator(adminID2, username2, pass2, name2, lastname2, email2, adminType2);

            //hakaton
            int hakatonID = rs.getInt("h.HakatonID");
            String naziv = rs.getString("h.Naziv");
            java.sql.Date datum = rs.getDate("h.Datum");
            Hakaton h = new Hakaton(hakatonID, naziv, datum, admin);

            //sudija
            int sID = rs.getInt("s.SudijaID");
            String username = rs.getString("s.KorisnickoIme");
            String pass = rs.getString("s.Lozinka");
            String name = rs.getString("s.Ime");
            String lastname = rs.getString("s.Prezime");
            String email = rs.getString("s.Mail");
            String profesijaa = rs.getString("s.Profesija");
            String zemljaa = rs.getString("s.Zemlja");
            Sudija s = new Sudija(sID, username, pass, name, lastname, email, profesijaa, zemljaa);

            //Veza
            int sudija_id = rs.getInt("sh.SudijaID");
            int hakaton_id = rs.getInt("sh.HakatonID");
            int admin_id = rs.getInt("sh.AdministratorID");

            SudijaHakaton veza = new SudijaHakaton(s, h, admin);
            lista.add(veza);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " sh join administrator a on sh.AdministratorID=a.AdministratorID join"
                + " sudija s on sh.SudijaID=s.SudijaID join"
                + " hakaton h on sh.HakatonID=h.HakatonID " + str;
    }

    @Override
    public String getKeyName() {
        return "sh.SudijaID ";
    }

    @Override
    public String getKeyValue() {
        return "";
    }

    @Override
    public String getUpdateStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getKeyNameAndValues() {
        return " SudijaID=" + sudija.getSudijaID()
                + " AND HakatonID=" + hakaton.getHakatonID()
                + " AND AdministratorID=" + admin.getAdministratorID();
    }

}
