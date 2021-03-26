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
public class Ocena implements Serializable, DomainObject {

    private int ocenaID;
    private Tim tim;
    private int dizajn;
    private int efikasnost;
    private int slozenost;
    private String komentar;
    private Sudija sudija;

    public Ocena() {
    }

    public Ocena(int ocenaID, Tim tim, int dizajn, int efikasnost, int slozenost, String komentar, Sudija sudija) {
        this.ocenaID = ocenaID;
        this.tim = tim;
        this.dizajn = dizajn;
        this.efikasnost = efikasnost;
        this.slozenost = slozenost;
        this.komentar = komentar;
        this.sudija = sudija;
    }

    public Sudija getSudija() {
        return sudija;
    }

    public void setSudija(Sudija sudija) {
        this.sudija = sudija;
    }

    public int getOcenaID() {
        return ocenaID;
    }

    public void setOcenaID(int ocenaID) {
        this.ocenaID = ocenaID;
    }

    public int getDizajn() {
        return dizajn;
    }

    public void setDizajn(int dizajn) {
        this.dizajn = dizajn;
    }

    public int getEfikasnost() {
        return efikasnost;
    }

    public void setEfikasnost(int efikasnost) {
        this.efikasnost = efikasnost;
    }

    public int getSlozenost() {
        return slozenost;
    }

    public void setSlozenost(int slozenost) {
        this.slozenost = slozenost;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    @Override
    public String getTableName() {
        return "ocena";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "TimID, Dizajn, Efikasnost, Slozenost, Komentar, SudijaID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + getTim().getTimID() + "', " + dizajn + ", '" + efikasnost + "', '" + slozenost + "', '" + komentar + "', '" + sudija.getSudijaID() + "'";

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
            Tim t = new Tim(timID, naziv, a2, m, new ArrayList<>(), new ArrayList<>(), new Sudija(), new Hakaton());

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

            //Ocena
            int ocenaID = rs.getInt("OcenaID");
            int dizajn = rs.getInt("Dizajn");
            int efikasnost = rs.getInt("Efikasnost");
            int slozenost = rs.getInt("Slozenost");
            String komentar = rs.getString("Komentar");
            Ocena o = new Ocena(ocenaID, t, dizajn, efikasnost, slozenost, komentar, s);

//            Double ocena = rs.getDouble("konacna_ocena");
//            String opis = rs.getString("opis");
//
//            //Korisnik
//            String korisnickoImeKorisnika = rs.getString("korisnicko_ime");
//            String sifraKorisnika = rs.getString("korisnicko_ime");
//            String imePrezimeKorisnika = rs.getString("ime_prezime");
//            String emailKorisnika = rs.getString("email");
//            String jmbgKorisnika = rs.getString("jmbg");
//
//            Korisnik korisnik = new Korisnik(korisnickoImeKorisnika, sifraKorisnika, imePrezimeKorisnika, emailKorisnika, jmbgKorisnika, new ArrayList<>());
//
//            Ocena o = new Ocena(ocena, opis, knjiga, korisnik);
            lista.add(o);
        }
        return lista;
    }

    @Override
    public String getConstraints(String str) {
        return " o join sudija s on o.SudijaID=s.SudijaID "
                + "join tim t on o.TimID=t.TimID "
                + "join mentor m on t.MentorID=m.MentorID "
                + "join Administrator a on t.AdministratorID=a.AdministratorID " + str;
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
        return " OcenaID=" + ocenaID + " AND TimID=" + tim.getTimID();
    }

}
