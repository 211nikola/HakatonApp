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
public class Clan implements Serializable, DomainObject {

    private int clanID;
    private Tim tim;
    private String ime;
    private String prezime;
    private String mail;
    private String uloga;

    public Clan() {
    }

    public Clan(int clanID, Tim tim, String ime, String prezime, String mail, String uloga) {
        this.clanID = clanID;
        this.tim = tim;
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.uloga = uloga;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public int getClanID() {
        return clanID;
    }

    public void setClanID(int clanID) {
        this.clanID = clanID;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
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
        int hash = 5;
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
        final Clan other = (Clan) obj;
        return this.clanID == other.clanID;
    }

    @Override
    public String getTableName() {
        return "clan";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "TimID,Ime,Prezime,Mail,Uloga";

    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getTim().getTimID() + "', '"
                + getIme() + "', '" + getPrezime() + "', '"
                + getMail() + "', '" + getUloga() + "'";

    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            //Tim
            //AdministratorMentora
            int adminID2 = rs.getInt("a.AdministratorID");
            String username2 = rs.getString("a.KorisnickoIme");
            String pass2 = rs.getString("a.Lozinka");
            String name2 = rs.getString("a.Ime");
            String lastname2 = rs.getString("a.Prezime");
            String email2 = rs.getString("a.Mail");
            String adminType2 = rs.getString("a.TipAdministratora");
            Administrator a2 = new Administrator(adminID2, username2, pass2, name2, lastname2, email2, adminType2);

            //Mentor
            int mentorID = rs.getInt("m.MentorID");
            String ime = rs.getString("m.Ime");
            String prezime = rs.getString("m.Prezime");
            String mailM = rs.getString("m.Mail");
            String profesija = rs.getString("m.Profesija");
            Mentor m = new Mentor(mentorID, ime, prezime, mailM, profesija, a2);

            //hakaton
            int hakatonID = rs.getInt("h.HakatonID");
            String nazivv = rs.getString("h.Naziv");
            java.sql.Date datum = rs.getDate("h.Datum");
            Hakaton h = new Hakaton(hakatonID, nazivv, datum, a2);

            //tim
            int timID = rs.getInt("t.TimID");
            String naziv = rs.getString("t.Naziv");
            int adminIDt = rs.getInt("t.AdministratorID");
            int mentorIDt = rs.getInt("t.MentorID");
            int hakatonIDt = rs.getInt("t.HakatonID");
            Tim t = new Tim(timID, naziv, a2, m, new ArrayList<>(), new ArrayList<>(), new Sudija(), new Hakaton());

            //clan
            int clanID = rs.getInt("c.ClanID");
            String imeC = rs.getString("c.Ime");
            String prezimeC = rs.getString("c.Prezime");
            String mailC = rs.getString("c.Mail");
            String uloga = rs.getString("c.Uloga");
            Clan c = new Clan(clanID, t, imeC, prezimeC, mailC, uloga);

            lista.add(c);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " c join tim t on t.TimID=c.TimID join Administrator a on t.AdministratorID=a.AdministratorID "
                + "join mentor m on t.MentorID=m.MentorID "
                + "join hakaton h on t.HakatonID=h.HakatonID "
                + "join administrator am on m.AdministratorID=am.AdministratorID " + str;
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
        return " clanID=" + clanID + " AND timID=" + tim.getTimID();
    }

}
