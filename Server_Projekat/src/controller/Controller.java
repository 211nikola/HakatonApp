/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Clan;
import domain.DomainObject;
import domain.Hakaton;
import domain.Mentor;
import domain.Ocena;
import domain.Sudija;
import domain.SudijaHakaton;
import domain.Tim;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import so.AbstractGenericObject;
import so.administrator.VratiAdministratoreSO;
import so.clan.KreirajClanaSO;
import so.clan.ObrisiClanaSO;
import so.clan.VratiClanoveSO;
import so.hakaton.KreirajHakatonSO;
import so.hakaton.VratiHakatoneSO;
import so.mentor.KreirajMentoraSO;
import so.mentor.ObrisiMentoraSO;
import so.mentor.VratiMentoreSO;
import so.ocena.KreirajOcenuSO;
import so.ocena.VratiOceneSO;
import so.sudija.IzmeniSudijuSO;
import so.sudija.KreirajSudijuSO;
import so.sudija.ObrisiSudijuSO;
import so.sudija.VratiSudijeSO;
import so.sudijaHakaton.KreirajSudijaHakatonSO;
import so.sudijaHakaton.ObrisiSudijaHakatonSO;
import so.sudijaHakaton.VratiSudijaHakatonSO;
import so.tim.KreirajTimSO;
import so.tim.ObrisiTimSO;
import so.tim.VratiTimoveSO;

/**
 *
 * @author Nikola
 */
public class Controller {

    private static Controller instance;

    private final Map<String, Object> mapa;

    public Controller() {

        mapa = new HashMap<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }

    public DomainObject login(String user, String pass) throws Exception {
        boolean pom = false;
        DomainObject korisnik = null;
        List<DomainObject> admini = vratiAdministratore();
        System.out.println(admini);
        List<DomainObject> sudije = vratiSudije();
        for (DomainObject domainObject : admini) {
            if (((Administrator) domainObject).getKorisnickoIme().equals(user) && ((Administrator) domainObject).getLozinka().equals(pass)) {
                getMapa().put("active_user", ((Administrator) domainObject));
                korisnik = ((Administrator) domainObject);
                System.out.println(korisnik);
                pom = true;
            }
        }
        if (pom == false) {
            for (DomainObject domainObject : sudije) {
                if (((Sudija) domainObject).getKorisnickoIme().equals(user) && ((Sudija) domainObject).getLozinka().equals(pass)) {
                    getMapa().put("active_user", ((Sudija) domainObject));
                    korisnik = ((Sudija) domainObject);
                }
            }
        }
        return (DomainObject) korisnik;
    }

    public List<DomainObject> vratiSudije() throws Exception {
        AbstractGenericObject so = new VratiSudijeSO();
        so.templateExecute(new Sudija(), "");
        return ((VratiSudijeSO) so).getLista();
    }

    public List<DomainObject> vratiAdministratore() throws Exception {
        AbstractGenericObject so = new VratiAdministratoreSO();
        so.templateExecute(new Administrator(), "");
        return ((VratiAdministratoreSO) so).getLista();
    }

    public List<DomainObject> vratiTimove() throws Exception {
        AbstractGenericObject so = new VratiTimoveSO();
        so.templateExecute(new Tim(), "");
        return ((VratiTimoveSO) so).getLista();
    }

    public List<DomainObject> vratiHakatone() throws Exception {
        AbstractGenericObject so = new VratiHakatoneSO();
        so.templateExecute(new Hakaton(), "");
        return ((VratiHakatoneSO) so).getLista();
    }

    public List<DomainObject> vratiTimoveZaHakaton(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiTimoveSO();
        Hakaton h = (Hakaton) parametar;
        so.templateExecute(new Tim(), " where t.HakatonID=" + h.getHakatonID());
        return ((VratiTimoveSO) so).getLista();
    }

    public List<DomainObject> vratiClanoveZaTim(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiClanoveSO();
        Tim t = (Tim) parametar;
        so.templateExecute(new Clan(), " where c.TimID=" + t.getTimID());
        return ((VratiClanoveSO) so).getLista();
    }

    public DomainObject sacuvajOcenu(Ocena ocena) throws Exception {
        AbstractGenericObject so = new KreirajOcenuSO();
        so.templateExecute(ocena, "");
        return ((KreirajOcenuSO) so).getOcena();
    }

    public List<DomainObject> vratiOcenuZaSudijuITim(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiOceneSO();
        HashMap mapa = (HashMap) parametar;
        Sudija s = (Sudija) mapa.get("sudija");
        Tim t = (Tim) mapa.get("tim");
        so.templateExecute(new Ocena(), " where o.TimID=" + t.getTimID() + " AND o.SudijaID=" + s.getSudijaID());
        return ((VratiOceneSO) so).getLista();
    }

    public DomainObject sacuvajSudiju(Object parametar) throws Exception {
        AbstractGenericObject so = new KreirajSudijuSO();
        so.templateExecute(parametar, "");
        System.out.println(parametar);
        return ((KreirajSudijuSO) so).getSudija();
    }

    public DomainObject kreirajHakaton(Object parametar) throws Exception {
        AbstractGenericObject so = new KreirajHakatonSO();
        so.templateExecute(parametar, "");
        return ((KreirajHakatonSO) so).gethaHakaton();

    }

    public DomainObject kreirajMentora(Object parametar) throws Exception {
        AbstractGenericObject so = new KreirajMentoraSO();
        so.templateExecute(parametar, "");
        return ((KreirajMentoraSO) so).geMentor();
    }

    public List<DomainObject> vratiHakatoneZaPretragu(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiHakatoneSO();
        String s = (String) parametar;
        so.templateExecute(new Hakaton(), " where h.Naziv LIKE '%" + s + "%'");
        return ((VratiHakatoneSO) so).getLista();
    }

    public List<DomainObject> vratiPoslednjiHakaton(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiHakatoneSO();
        String s = (String) parametar;
        so.templateExecute(new Hakaton(), " ORDER BY h.HakatonID DESC LIMIT 1");
        return ((VratiHakatoneSO) so).getLista();
    }

    public List<DomainObject> vratiPoslenjiTim(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiTimoveSO();
        String s = (String) parametar;
        so.templateExecute(new Tim(), " ORDER BY TimID DESC LIMIT 1");
        return ((VratiTimoveSO) so).getLista();
    }

    public List<DomainObject> vratiSudijeZaPretragu(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiSudijeSO();
        String s = (String) parametar;
        so.templateExecute(new Sudija(), " where s.Ime LIKE '%" + s
                + "%' OR s.Prezime LIKE '%" + s
                + "%'" + " OR s.Mail LIKE '%" + s
                + "%'" + " OR s.Zemlja LIKE '%" + s + "%'");
        return ((VratiSudijeSO) so).getLista();
    }

    public List<DomainObject> vratiMentore() throws Exception {
        AbstractGenericObject so = new VratiMentoreSO();
        so.templateExecute(new Mentor(), "");
        return ((VratiMentoreSO) so).getLista();
    }

    public List<DomainObject> vratiMentoreZaPretragu(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiMentoreSO();
        String s = (String) parametar;
        so.templateExecute(new Mentor(), " where m.Ime LIKE '%" + s
                + "%' OR m.Prezime LIKE '%" + s
                + "%'" + " OR m.Mail LIKE '%" + s
                + "%'" + " OR m.Profesija LIKE '%" + s + "%'");
        return ((VratiMentoreSO) so).getLista();
    }

    public List<DomainObject> vratiTimoveZaPretragu(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiTimoveSO();
        String s = (String) parametar;
        so.templateExecute(new Tim(), " where t.Naziv LIKE '%" + s + "%'");
        return ((VratiTimoveSO) so).getLista();
    }

    public DomainObject kreirajSudijaHakaton(Object parametar) throws Exception {
        AbstractGenericObject so = new KreirajSudijaHakatonSO();
        HashMap mapa = (HashMap) parametar;
        Sudija s = (Sudija) mapa.get("sudija");
        Administrator a = (Administrator) mapa.get("admin");
        Hakaton h = (Hakaton) mapa.get("hakaton");
        SudijaHakaton veza = new SudijaHakaton(s, h, a);
        so.templateExecute(veza, "");
        return ((KreirajSudijaHakatonSO) so).getSudijaHakaton();
    }

    public List<DomainObject> vratiHakatoneZaSudiju(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiHakatoneSO();
        Sudija s = (Sudija) parametar;
        so.templateExecute(new Hakaton(), " where sh.SudijaID=" + s.getSudijaID());
        return ((VratiHakatoneSO) so).getLista();
    }

    public List<DomainObject> vratiVeze(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiSudijaHakatonSO();
        Sudija s = (Sudija) parametar;
        so.templateExecute(new SudijaHakaton(), " where sh.SudijaID=" + s.getSudijaID());
        return ((VratiSudijaHakatonSO) so).getLista();
    }

    public DomainObject obrisiVezu(Object parametar) throws Exception {
        AbstractGenericObject so = new ObrisiSudijaHakatonSO();
        SudijaHakaton s = (SudijaHakaton) parametar;
        so.templateExecute(s, " sh.SudijaID=" + s.getSudija().getSudijaID()
                + " AND sh.AdministratorID=" + s.getAdmin().getAdministratorID()
                + " AND sh.HakatonID=" + s.getHakaton().getHakatonID());
        return ((ObrisiSudijaHakatonSO) so).getSudijaHakaton();

    }

    public DomainObject obrisiSudiju(Object parametar) throws Exception {
        AbstractGenericObject so = new ObrisiSudijuSO();
        so.templateExecute(parametar, "");
        return ((ObrisiSudijuSO) so).getSudija();
    }

    public DomainObject updateSudija(Object parametar) throws Exception {
        AbstractGenericObject so = new IzmeniSudijuSO();
        so.templateExecute(parametar, "");
        return ((IzmeniSudijuSO) so).getSudija();
    }

    public List<DomainObject> vratiClanove() throws Exception {
        AbstractGenericObject so = new VratiClanoveSO();
        so.templateExecute(new Clan(), "");
        return ((VratiClanoveSO) so).getLista();
    }

    public DomainObject obrisiClana(Object parametar) throws Exception {
        AbstractGenericObject so = new ObrisiClanaSO();
        Clan c = (Clan) parametar;
        so.templateExecute(c, "");

        return ((ObrisiClanaSO) so).getClan();

    }

    public DomainObject kreirajTim(Object parametar) throws Exception {
        AbstractGenericObject so = new KreirajTimSO();
        so.templateExecute(parametar, "");
        return ((KreirajTimSO) so).getTim();
    }

    public DomainObject kreirajClana(Object parametar) throws Exception {
        AbstractGenericObject so = new KreirajClanaSO();
        so.templateExecute(parametar, "");
        return ((KreirajClanaSO) so).getClan();
    }

    public List<DomainObject> vratiTimoveZaMentora(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiTimoveSO();
        Mentor m = (Mentor) parametar;
        so.templateExecute(new Tim(), " where m.MentorID=" + m.getMentorID());
        return ((VratiTimoveSO) so).getLista();
    }

    public DomainObject obrisiMentora(Object parametar) throws Exception {
        AbstractGenericObject so = new ObrisiMentoraSO();
        Mentor m = (Mentor) parametar;
        so.templateExecute(m, "");

        return ((ObrisiMentoraSO) so).getMentor();
    }

    public DomainObject obrisiTim(Object parametar) throws Exception {
        AbstractGenericObject so = new ObrisiTimSO();
        Tim t = (Tim) parametar;
        so.templateExecute(t, "");

        return ((ObrisiTimSO) so).geTim();
    }

    public List<DomainObject> vratiClanoveZaPretragu(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiClanoveSO();
        String s = (String) parametar;
        so.templateExecute(new Clan(), " where c.Ime LIKE '%" + s
                + "%' OR c.Prezime LIKE '%" + s
                + "%'" + " OR c.Mail LIKE '%" + s
                + "%'" + " OR c.Uloga LIKE '%" + s + "%'");
        return ((VratiClanoveSO) so).getLista();
    }

    public List<DomainObject> vratiSudijuZaUsername(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiSudijeSO();
        String s = (String) parametar;
        so.templateExecute(new Sudija(), " where s.KorisnickoIme = '" + s + "'");
        return ((VratiSudijeSO) so).getLista();
    }

    public List<DomainObject> vratiVezeSve() throws Exception {
        AbstractGenericObject so = new VratiSudijaHakatonSO();
        so.templateExecute(new SudijaHakaton(), "");
        return ((VratiSudijaHakatonSO) so).getLista();
    }

    public List<DomainObject> vratiSudijeZaHakaton(Object parametar) throws Exception {
        AbstractGenericObject so = new VratiSudijeSO();
        Hakaton h = (Hakaton) parametar;
        so.templateExecute(new Sudija(), " join sudijahakaton sh on s.SudijaID=sh.SudijaID where sh.HakatonID = '" + h.getHakatonID() + "'");
        return ((VratiSudijeSO) so).getLista();
    }
}
