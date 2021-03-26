/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import ui.form.FrmSudijaHakaton;
import util.Operacija;

/**
 *
 * @author Nikola
 */
public class OsluskivacSudija extends Thread {

    FrmSudijaHakaton fmain;

    public OsluskivacSudija() {
    }

    public OsluskivacSudija(FrmSudijaHakaton fmain) {
        this.fmain = fmain;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 9000);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                KlijentskiZahtev kz = (KlijentskiZahtev) ois.readObject();
                if (kz.getOperacija() == Operacija.GASENJE) {
                    fmain.dispose();
                    return;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(OsluskivacAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
