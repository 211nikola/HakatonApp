package communication;

import domain.Clan;
import domain.Hakaton;
import domain.Mentor;
import domain.Ocena;
import domain.Sudija;
import domain.SudijaHakaton;
import domain.Tim;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import util.Operacija;

public class KomunikacijaSaServerom {

    private static KomunikacijaSaServerom instanca;

    Socket socket;

    private KomunikacijaSaServerom() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Klijent ne moze da se poveze na server!");
        }
    }

    public static KomunikacijaSaServerom getInstanca() {
        if (instanca == null) {
            instanca = new KomunikacijaSaServerom();
        }
        return instanca;
    }

    public ServerskiOdgovor primiOdgovor() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void posaljiZahtev(KlijentskiZahtev kz) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(kz);
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaServerom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Tim> vratiTimove() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_TIMOVE);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }

        return (List<Tim>) so.getOdgovor();
    }

    public List<Hakaton> vratiHakatone() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_HAKATONE);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Hakaton>) so.getOdgovor();
    }

    public List<Tim> vratiTimoveZaHakaton(Hakaton h) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setParametar(h);
        kz.setOperacija(Operacija.VRATI_TIMOVE_ZA_HAKATON);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Tim>) so.getOdgovor();
    }

    public List<Clan> vratiClanoveZaTim(Tim tim) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setParametar(tim);
        kz.setOperacija(Operacija.VRATI_CLANOVE_ZA_TIM);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Clan>) so.getOdgovor();
    }

    public void sacuvajOcenu(Ocena o) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.SACUVAJ_OCENE);
        kz.setParametar(o);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
    }

    public List<Ocena> vratiOcenuZaSudijuITim(HashMap mapa) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_OCENU_ZA_SUDIJU_I_TIM);
        kz.setParametar(mapa);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Ocena>) so.getOdgovor();
    }

    public void kreirajSudiju(Sudija s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.KREIRAJ_SUDIJU);
        kz.setParametar(s);
        posaljiZahtev(kz);
        System.out.println(s);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }

    }

    public List<Sudija> vratiSudije() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_SUDIJE);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Sudija>) so.getOdgovor();
    }

    public Hakaton kreirajHakaton(Hakaton h) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.KREIRAJ_HAKATON);
        kz.setParametar(h);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (Hakaton) so.getOdgovor();
    }

    public void kreirajMentora(Mentor m) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.KREIRAJ_MENTORA);
        kz.setParametar(m);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
    }

    public List<Hakaton> vratiHakatone(String s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_HAKATONE_ZA_PRETRAGU);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Hakaton>) so.getOdgovor();
    }

    public List<Sudija> vratiSudije(String s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_SUDIJE_ZA_PRETRAGU);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Sudija>) so.getOdgovor();

    }

    public List<Mentor> vratiMentore() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_MENTORE);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Mentor>) so.getOdgovor();

    }

    public List<Mentor> vratiMentore(String s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_MENTORE_ZA_PRETRAGU);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Mentor>) so.getOdgovor();
    }

    public List<Tim> vratiTimove(String s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_TIMOVE_ZA_PRETRAGU);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Tim>) so.getOdgovor();
    }

    public SudijaHakaton ubaciSudijeUHakaton(HashMap mapa) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.UBACI_SUDIJE_U_HAKATON);
        kz.setParametar(mapa);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (SudijaHakaton) so.getOdgovor();
    }

    public List<Hakaton> vratiHakatoneZaSudiju(Sudija sudija) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_HAKATONE_ZA_SUDIJU);
        kz.setParametar(sudija);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Hakaton>) so.getOdgovor();
    }

    public Sudija obrisiSudiju(Sudija s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.OBRISI_SUDIJU);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (Sudija) so.getOdgovor();
    }

    public List<SudijaHakaton> vratiVeze(Sudija s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_VEZE_SUDIJE_HAKATONA);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<SudijaHakaton>) so.getOdgovor();
    }

    public SudijaHakaton obrisiVezuSaHakatonom(SudijaHakaton s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.OBRISI_VEZE_SUDIJE_HAKATONA);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (SudijaHakaton) so.getOdgovor();
    }

    public Sudija updateSudija(Sudija sudija) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.IZMENI_SUDIJU);
        kz.setParametar(sudija);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (Sudija) so.getOdgovor();
    }

    public List<Clan> vratiClanove() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_CLANOVE);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Clan>) so.getOdgovor();
    }

    public Clan obrisiClana(Clan c) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.OBRISI_CLANA);
        kz.setParametar(c);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (Clan) so.getOdgovor();
    }

    public void sacuvajTim(Tim tim) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.KREIRAJ_TIM);
        kz.setParametar(tim);
        posaljiZahtev(kz);
        System.out.println(tim);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
    }

    public void sacuvajClana(Clan clan) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.KREIRAJ_CLANA);
        kz.setParametar(clan);
        posaljiZahtev(kz);
        System.out.println(clan);
        System.out.println(clan);
        System.out.println(clan);
        System.out.println(clan);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
    }

    public List<Tim> vratiPoslednjiTim() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_POSLEDNJI_TIM);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }

        return (List<Tim>) so.getOdgovor();
    }

    public List<Hakaton> vratiPoslednjiHakaton() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_POSLEDNJI_HAKATON);
        posaljiZahtev(kz);
        kz.setParametar(" order by h.HakatonID desc limit 1");

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }

        return (List<Hakaton>) so.getOdgovor();
    }

    public List<Tim> vratiTimoveZaMentora(Mentor m) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_TIMOVE_ZA_MENTORA);
        kz.setParametar(m);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Tim>) so.getOdgovor();
    }

    public Exception obrisiMentora(Mentor m) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.OBRISI_MENTORA);
        kz.setParametar(m);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return so.getException();
    }

    public Exception obrisiTim(Tim t) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.OBRISI_TIM);
        kz.setParametar(t);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return so.getException();
    }

    public List<Clan> vratiClanoveZaPretragu(String s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_CLANOVE_ZA_PRETRAGU);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Clan>) so.getOdgovor();
    }

    public List<Sudija> vratiSudijuZaUsername(String s) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_SUDIJA_ZA_USERNAME);
        kz.setParametar(s);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Sudija>) so.getOdgovor();
    }

    public List<SudijaHakaton> vratiVeze() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_VEZE_SVE);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<SudijaHakaton>) so.getOdgovor();
    }

    public List<Sudija> vratiSudijeZaHakaton(Hakaton hakaton) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.VRATI_SUDIJE_ZA_HAKATON);
        kz.setParametar(hakaton);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (List<Sudija>) so.getOdgovor();
    }

    public Hakaton kreirajHakaton(Hakaton h, List<Sudija> listaSudija) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacija.KREIRAJ_HAKATON);
        Object[] parametri = {h, listaSudija};
        kz.setParametar(parametri);
        posaljiZahtev(kz);

        ServerskiOdgovor so = primiOdgovor();
        if (so.getException() != null) {
            throw so.getException();
        }
        return (Hakaton) so.getOdgovor();
    }
}
