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
public class Tim implements Serializable, DomainObject {

    private int timID;
    private String naziv;
    private Administrator administrator;
    private Mentor mentor;
    private List<Clan> clanovi;
    private List<Ocena> ocene;
    private Sudija sudija;
    private Hakaton hakaton;

    public Tim() {
    }

    public Tim(int timID, String naziv, Administrator administrator, Mentor mentor, List<Clan> clanovi, List<Ocena> ocene, Sudija sudija, Hakaton hakaton) {
        this.timID = timID;
        this.naziv = naziv;
        this.administrator = administrator;
        this.mentor = mentor;
        this.clanovi = clanovi;
        this.ocene = ocene;
        this.sudija = sudija;
        this.hakaton = hakaton;
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }

    public void setClanovi(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }

    public int getTimID() {
        return timID;
    }

    public void setTimID(int timID) {
        this.timID = timID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public List<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(List<Ocena> ocene) {
        this.ocene = ocene;
    }

    public Sudija getSudija() {
        return sudija;
    }

    public void setSudija(Sudija sudija) {
        this.sudija = sudija;
    }

    @Override
    public String getTableName() {
        return "tim";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "Naziv,AdministratorID,MentorID,HakatonID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getNaziv() + "', '"
                + getAdministrator().getAdministratorID() + "', '"
                + getMentor().getMentorID() + "', '"
                + getHakaton().getHakatonID() + "'";

    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            //Administrator
//            int adminID = rs.getInt("a.AdministratorID");
//            String username = rs.getString("a.KorisnickoIme");
//            String pass = rs.getString("a.Lozinka");
//            String name = rs.getString("a.Ime");
//            String lastname = rs.getString("a.Prezime");
//            String email = rs.getString("a.Mail");
//            String adminType = rs.getString("a.TipAdministratora");
//            Administrator a = new Administrator(adminID, username, pass, name, lastname, email, adminType);
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

            //tim
            int timID = rs.getInt("t.TimID");
            String naziv = rs.getString("t.Naziv");
            int adminIDt = rs.getInt("t.AdministratorID");
            int mentorIDt = rs.getInt("t.MentorID");
            int hakatonIDt = rs.getInt("t.HakatonID");

            //hakaton
            int hakatonID = rs.getInt("h.HakatonID");
            String nazivv = rs.getString("h.Naziv");
            java.sql.Date datum = rs.getDate("h.Datum");

            Hakaton h = new Hakaton(hakatonID, nazivv, datum, a2);

            Tim t = new Tim(timID, naziv, a2, m, new ArrayList<>(), new ArrayList<>(), new Sudija(), h);
            lista.add(t);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " t join Administrator a on t.AdministratorID=a.AdministratorID "
                + "join mentor m on t.MentorID=m.MentorID "
                + "join hakaton h on t.HakatonID=h.HakatonID "
                + "join administrator am on m.AdministratorID=am.AdministratorID " + str;
    }

    @Override
    public String getKeyName() {
        return "TimID";
    }

    @Override
    public String getKeyValue() {
        return timID + "";
    }

    @Override
    public String getUpdateStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getKeyNameAndValues() {
        return " TimID=" + timID;
    }

    public Hakaton getHakaton() {
        return hakaton;
    }

    public void setHakaton(Hakaton hakaton) {
        this.hakaton = hakaton;
    }

}
