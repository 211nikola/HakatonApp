/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import controller.Controller;
import domain.DomainObject;
import domain.Ocena;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Nikola
 */
public class ObradaKlijentskogZahteva extends Thread {

    private final Socket socket;

    public ObradaKlijentskogZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        KlijentskiZahtev kz = null;
        ServerskiOdgovor so = null;
        while (!socket.isClosed()) {
            try {
                kz = primiZahtev();
                switch (kz.getOperacija()) {
                    case Operacija.LOGIN:
                        so = operacijaLogIn(kz.getParametar());
                        break;
                    case Operacija.VRATI_TIMOVE:
                        so = operacijaVratiTimove();
                        break;
                    case Operacija.VRATI_HAKATONE:
                        so = operacijaVratiHakatone();
                        break;
                    case Operacija.VRATI_TIMOVE_ZA_HAKATON:
                        so = operacijaVratiTimoveZaHakaton(kz.getParametar());
                        break;
                    case Operacija.VRATI_CLANOVE_ZA_TIM:
                        so = operacijaVratiClanoveZaTim(kz.getParametar());
                        break;
                    case Operacija.SACUVAJ_OCENE:
                        so = operacijaSacuvajOcenu(kz.getParametar());
                        break;
                    case Operacija.VRATI_OCENU_ZA_SUDIJU_I_TIM:
                        so = operacijaVratiOcenuZaSudijuITim(kz.getParametar());
                        break;
                    case Operacija.KREIRAJ_SUDIJU:
                        so = operacijaKreirajSudiju(kz.getParametar());
                        break;
                    case Operacija.VRATI_SUDIJE:
                        so = operacijaVratiSudije();
                        break;
                    case Operacija.KREIRAJ_HAKATON:
                        so = operacijaKreirajHakaton(kz.getParametar());
                        break;
                    case Operacija.KREIRAJ_MENTORA:
                        so = operacijaKreirajMentora(kz.getParametar());
                        break;
                    case Operacija.VRATI_HAKATONE_ZA_PRETRAGU:
                        so = operacijaVratiHakatoneZaPretragu(kz.getParametar());
                        break;
                    case Operacija.VRATI_SUDIJE_ZA_PRETRAGU:
                        so = operacijaVratiSudijeZaPretragu(kz.getParametar());
                        break;
                    case Operacija.VRATI_MENTORE:
                        so = operacijaVratiMentore();
                        break;
                    case Operacija.VRATI_MENTORE_ZA_PRETRAGU:
                        so = operacijaVratiMentoreZaPretragu(kz.getParametar());
                        break;
                    case Operacija.VRATI_TIMOVE_ZA_PRETRAGU:
                        so = operacijaVratiTimoveZaPretragu(kz.getParametar());
                        break;
                    case Operacija.UBACI_SUDIJE_U_HAKATON:
                        so = operacijaKreirajSudijaHakaton(kz.getParametar());
                        break;
                    case Operacija.VRATI_HAKATONE_ZA_SUDIJU:
                        so = operacijaVratiHakatoneZaSudiju(kz.getParametar());
                        break;
                    case Operacija.VRATI_VEZE_SUDIJE_HAKATONA:
                        so = operacijaVratiVeze(kz.getParametar());
                        break;
                    case Operacija.OBRISI_VEZE_SUDIJE_HAKATONA:
                        so = operacijaObrisiVeze(kz.getParametar());
                        break;
                    case Operacija.OBRISI_SUDIJU:
                        so = operacijaObrisiSudiju(kz.getParametar());
                        break;
                    case Operacija.IZMENI_SUDIJU:
                        so = operacijaSudijaUpdate(kz.getParametar());
                        break;
                    case Operacija.VRATI_CLANOVE:
                        so = operacijaVratiClanove();
                        break;
                    case Operacija.OBRISI_CLANA:
                        so = operacijaObrisiClana(kz.getParametar());
                        break;
                    case Operacija.KREIRAJ_TIM:
                        so = operacijaKreirajTim(kz.getParametar());
                        break;
                    case Operacija.KREIRAJ_CLANA:
                        so = operacijaKreirajClana(kz.getParametar());
                        break;
                    case Operacija.VRATI_POSLEDNJI_TIM:
                        so = operacijaVratiPoslenjiTim();
                        break;
                    case Operacija.VRATI_POSLEDNJI_HAKATON:
                        so = operacijaVratiPoslednjiHakaton();
                        break;
                    case Operacija.VRATI_TIMOVE_ZA_MENTORA:
                        so = operacijaVratiTimoveZaMentora(kz.getParametar());
                        break;
                    case Operacija.OBRISI_MENTORA:
                        so = operacijaObrisiMentora(kz.getParametar());
                        break;
                    case Operacija.OBRISI_TIM:
                        so = operacijaObrisiTim(kz.getParametar());
                        break;
                    case Operacija.VRATI_CLANOVE_ZA_PRETRAGU:
                        so = operacijaVratiClanoveZaPretragu(kz.getParametar());
                        break;
                    case Operacija.VRATI_SUDIJA_ZA_USERNAME:
                        so = operacijaVratiSudijuZaUsername(kz.getParametar());
                        break;
                    case Operacija.VRATI_VEZE_SVE:
                        so = operacijaVratiVezeSve();
                        break;
                    case Operacija.VRATI_SUDIJE_ZA_HAKATON:
                        so = operacijaVratiSudijeZaHakaton(kz.getParametar());
                        break;
                    default:
                        break;
                }
                posaljiOdgovor(so);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    private KlijentskiZahtev primiZahtev() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        KlijentskiZahtev kz = (KlijentskiZahtev) ois.readObject();
        return kz;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(so);
    }

    private ServerskiOdgovor operacijaLogIn(Object parametar) {
        ServerskiOdgovor so = null;
        Map<String, String> mapa = (Map<String, String>) parametar;
        String user = mapa.get("user");
        String pass = mapa.get("pass");
        try {
            so = new ServerskiOdgovor();
            DomainObject korisnik = Controller.getInstance().login(user, pass);
            so.setOdgovor(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            so.setException(e);
        }
        return so;
    }

    public void saljiKraj() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(new KlijentskiZahtev(Operacija.GASENJE, null));
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    private ServerskiOdgovor operacijaVratiTimove() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> timovi = Controller.getInstance().vratiTimove();
        so.setOdgovor(timovi);
        return so;
    }

    private ServerskiOdgovor operacijaVratiHakatone() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> hakatoni = Controller.getInstance().vratiHakatone();
        so.setOdgovor(hakatoni);
        return so;
    }

    private ServerskiOdgovor operacijaVratiTimoveZaHakaton(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> timovi = Controller.getInstance().vratiTimoveZaHakaton(parametar);
        so.setOdgovor(timovi);
        return so;
    }

    private ServerskiOdgovor operacijaVratiClanoveZaTim(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> clanovi = Controller.getInstance().vratiClanoveZaTim(parametar);
        so.setOdgovor(clanovi);
        return so;
    }

    private ServerskiOdgovor operacijaSacuvajOcenu(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();

        //Ocena ocena = Controller.getInstance().sacuvajOcenu((Ocena) parametar);
        DomainObject domainObject = Controller.getInstance().sacuvajOcenu((Ocena) parametar);
        so.setOdgovor(domainObject);

        return so;
    }

    private ServerskiOdgovor operacijaVratiOcenuZaSudijuITim(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> ocene = Controller.getInstance().vratiOcenuZaSudijuITim(parametar);
        so.setOdgovor(ocene);
        return so;
    }

    private ServerskiOdgovor operacijaKreirajSudiju(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject sudija = Controller.getInstance().sacuvajSudiju(parametar);
        so.setOdgovor(sudija);
        return so;
    }

    private ServerskiOdgovor operacijaVratiSudije() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> listaSudija = Controller.getInstance().vratiSudije();
        so.setOdgovor(listaSudija);
        return so;
    }

    private ServerskiOdgovor operacijaKreirajHakaton(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject hakaton = Controller.getInstance().kreirajHakaton(parametar);
        so.setOdgovor(hakaton);
        return so;
    }

    private ServerskiOdgovor operacijaKreirajMentora(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject mentor = Controller.getInstance().kreirajMentora(parametar);
        so.setOdgovor(mentor);
        return so;
    }

    private ServerskiOdgovor operacijaVratiHakatoneZaPretragu(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> hakatoni = Controller.getInstance().vratiHakatoneZaPretragu(parametar);
        so.setOdgovor(hakatoni);
        return so;
    }

    private ServerskiOdgovor operacijaVratiSudijeZaPretragu(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> sudije = Controller.getInstance().vratiSudijeZaPretragu(parametar);
        so.setOdgovor(sudije);
        return so;
    }

    private ServerskiOdgovor operacijaVratiMentore() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> mentori = Controller.getInstance().vratiMentore();
        so.setOdgovor(mentori);
        return so;
    }

    private ServerskiOdgovor operacijaVratiMentoreZaPretragu(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> mentori = Controller.getInstance().vratiMentoreZaPretragu(parametar);
        so.setOdgovor(mentori);
        return so;
    }

    private ServerskiOdgovor operacijaVratiTimoveZaPretragu(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> timovi = Controller.getInstance().vratiTimoveZaPretragu(parametar);
        so.setOdgovor(timovi);
        return so;
    }

    private ServerskiOdgovor operacijaKreirajSudijaHakaton(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject veza = Controller.getInstance().kreirajSudijaHakaton(parametar);
        so.setOdgovor(veza);
        return so;
    }

    private ServerskiOdgovor operacijaVratiHakatoneZaSudiju(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> hakatoni = Controller.getInstance().vratiHakatoneZaSudiju(parametar);
        so.setOdgovor(hakatoni);
        return so;
    }

    private ServerskiOdgovor operacijaVratiVeze(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> veze = Controller.getInstance().vratiVeze(parametar);
        so.setOdgovor(veze);
        return so;
    }

    private ServerskiOdgovor operacijaObrisiVeze(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject veza = (DomainObject) Controller.getInstance().obrisiVezu(parametar);
        so.setOdgovor(veza);
        return so;
    }

    private ServerskiOdgovor operacijaObrisiSudiju(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject sudija = Controller.getInstance().obrisiSudiju(parametar);
        so.setOdgovor(sudija);
        return so;
    }

    private ServerskiOdgovor operacijaSudijaUpdate(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject sudija = Controller.getInstance().updateSudija(parametar);
        so.setOdgovor(sudija);
        return so;
    }

    private ServerskiOdgovor operacijaVratiClanove() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> clanovi = Controller.getInstance().vratiClanove();
        so.setOdgovor(clanovi);
        return so;
    }

    private ServerskiOdgovor operacijaObrisiClana(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject clan = (DomainObject) Controller.getInstance().obrisiClana(parametar);
        so.setOdgovor(clan);
        return so;
    }

    private ServerskiOdgovor operacijaKreirajTim(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject tim = Controller.getInstance().kreirajTim(parametar);
        so.setOdgovor(tim);
        return so;
    }

    private ServerskiOdgovor operacijaKreirajClana(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject clan = Controller.getInstance().kreirajClana(parametar);
        so.setOdgovor(clan);
        return so;
    }

    private ServerskiOdgovor operacijaVratiPoslenjiTim() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> timovi = Controller.getInstance().vratiPoslenjiTim("");
        so.setOdgovor(timovi);
        return so;
    }

    private ServerskiOdgovor operacijaVratiPoslednjiHakaton() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> hakatoni = Controller.getInstance().vratiPoslednjiHakaton("");
        so.setOdgovor(hakatoni);
        return so;
    }

    private ServerskiOdgovor operacijaVratiTimoveZaMentora(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> timovi = Controller.getInstance().vratiTimoveZaMentora(parametar);
        so.setOdgovor(timovi);
        return so;
    }

    private ServerskiOdgovor operacijaObrisiMentora(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        //DomainObject mentor = (DomainObject) Controller.getInstance().obrisiMentora(parametar);
        DomainObject mentor = Controller.getInstance().obrisiMentora(parametar);

        so.setOdgovor(mentor);
        return so;
    }

    private ServerskiOdgovor operacijaObrisiTim(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        DomainObject mentor = Controller.getInstance().obrisiTim(parametar);

        so.setOdgovor(mentor);
        return so;
    }

    private ServerskiOdgovor operacijaVratiClanoveZaPretragu(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> clanovi = Controller.getInstance().vratiClanoveZaPretragu(parametar);
        so.setOdgovor(clanovi);
        return so;
    }

    private ServerskiOdgovor operacijaVratiSudijuZaUsername(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> sudija = Controller.getInstance().vratiSudijuZaUsername(parametar);
        so.setOdgovor(sudija);
        return so;
    }

    private ServerskiOdgovor operacijaVratiVezeSve() throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> veze = Controller.getInstance().vratiVezeSve();
        so.setOdgovor(veze);
        return so;
    }

    private ServerskiOdgovor operacijaVratiSudijeZaHakaton(Object parametar) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<DomainObject> sudije = Controller.getInstance().vratiSudijeZaHakaton(parametar);
        so.setOdgovor(sudije);
        return so;
    }

}
