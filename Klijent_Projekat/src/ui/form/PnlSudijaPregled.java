/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.form;

import communication.KomunikacijaSaServerom;
import controller.GUIKordinator;
import domain.Sudija;
import domain.SudijaHakaton;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.table.model.SudijeTableModel;

/**
 *
 * @author Nikola
 */
public class PnlSudijaPregled extends javax.swing.JPanel {

    /**
     * Creates new form PnlSudijaPregled
     */
    public PnlSudijaPregled() throws Exception {
        initComponents();
        preparePanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPregled = new javax.swing.JTable();
        btnDetalji = new javax.swing.JButton();
        txtFilter = new javax.swing.JTextField();
        btnPretraga = new javax.swing.JButton();
        lblErrorPretraga = new javax.swing.JLabel();
        btnIzmena = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Pregled sudija"));
        setToolTipText("");

        tblPregled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPregled);

        btnDetalji.setText("Detalji");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        btnPretraga.setText("Pretraga");
        btnPretraga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretragaActionPerformed(evt);
            }
        });

        btnIzmena.setText("Izmeni");
        btnIzmena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmenaActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        jLabel1.setText("Pretrazi:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIzmena, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDetalji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErrorPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzmena)
                    .addComponent(btnObrisi))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPretraga))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(btnDetalji)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed
        // TODO add your handling code here:

        try {
            if (validacijaTabele()) {
                SudijeTableModel stm = (SudijeTableModel) tblPregled.getModel();
                Sudija s = stm.getSudija(tblPregled.getSelectedRow());
                GUIKordinator.getInstance().getMap().put("izabrani_sudija", s);
                FrmAdminDetaljiSudija fDetalji = new FrmAdminDetaljiSudija((FrmAdminGlavna) SwingUtilities.getWindowAncestor(this), true, ModFormeDetalji.SHOW);
                fDetalji.setVisible(true);
            } else {

                JOptionPane.showMessageDialog(this, "Izaberite sudiju!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                SudijeTableModel stm = (SudijeTableModel) tblPregled.getModel();
                if (!stm.getLista().isEmpty()) {
                    tblPregled.setRowSelectionInterval(0, 0);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da prikaze detalje o sudiji", "Greska", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnPretragaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretragaActionPerformed
        // TODO add your handling code here:

        if (validacija()) {
            try {
                String s = "" + txtFilter.getText();
                List<Sudija> lista = KomunikacijaSaServerom.getInstanca().vratiSudije(s);
                tblPregled.setModel(new SudijeTableModel(lista));

                izbrisi();
                //isprazni();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da izvrsi pretragu!", "Greska", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(PnlHakatonPregled.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPretragaActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        // TODO add your handling code here:
        try {
            if (validacijaTabele()) {

                int input = JOptionPane.showConfirmDialog(null,
                        "Da li ste sigurni da zelite da obrisete sudiju?",
                        "Potvrda brisanja", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (input) {
                    case 0:
                        SudijeTableModel stm = (SudijeTableModel) tblPregled.getModel();
                        Sudija s = stm.getSudija(tblPregled.getSelectedRow());
                        List<SudijaHakaton> veze = KomunikacijaSaServerom.getInstanca().vratiVeze(s);
//                        for (SudijaHakaton sudijaHakaton : veze) {
//                            SudijaHakaton obrisanaVeza = KomunikacijaSaServerom.getInstanca().obrisiVezuSaHakatonom(sudijaHakaton);
//                            System.out.println(obrisanaVeza);
//                        }
                        //List<Ocena> oceneSudije = KomunikacijaSaServerom.getInstanca().vratiocene
                        Sudija obrisaniSudija = KomunikacijaSaServerom.getInstanca().obrisiSudiju(s);
                        System.out.println(obrisaniSudija);
                        JOptionPane.showMessageDialog(this, "Sistem je obrisao sudiju!");
                        String ss = "" + txtFilter.getText();
                        List<Sudija> lista = KomunikacijaSaServerom.getInstanca().vratiSudije(ss);
                        tblPregled.setModel(new SudijeTableModel(lista));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;

                }

            } else {
                JOptionPane.showMessageDialog(this, "Izaberite sudiju!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception ex) {
            Logger.getLogger(PnlSudijaPregled.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise sudiju!");

        }

    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnIzmenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmenaActionPerformed
        // TODO add your handling code here:
        try {
            if (validacijaTabele()) {
                SudijeTableModel stm = (SudijeTableModel) tblPregled.getModel();
                Sudija s = stm.getSudija(tblPregled.getSelectedRow());
                GUIKordinator.getInstance().getMap().put("izabrani_sudija", s);
                FrmAdminDetaljiSudija fDetalji = new FrmAdminDetaljiSudija((FrmAdminGlavna) SwingUtilities.getWindowAncestor(this), true, ModFormeDetalji.EDIT);
                fDetalji.setVisible(true);
            } else {

                JOptionPane.showMessageDialog(this, "Izaberite sudiju!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

                tblPregled.setRowSelectionInterval(0, 0);
            }
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_btnIzmenaActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            // TODO add your handling code here:
            String s = "" + txtFilter.getText();
            List<Sudija> lista = KomunikacijaSaServerom.getInstanca().vratiSudije(s);
            tblPregled.setModel(new SudijeTableModel(lista));
        } catch (Exception ex) {
            Logger.getLogger(PnlSudijaPregled.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnIzmena;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPretraga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrorPretraga;
    private javax.swing.JTable tblPregled;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnDetalji() {
        return btnDetalji;
    }

    public void setBtnDetalji(javax.swing.JButton btnDetalji) {
        this.btnDetalji = btnDetalji;
    }

    public javax.swing.JButton getBtnPretraga() {
        return btnPretraga;
    }

    public void setBtnPretraga(javax.swing.JButton btnPretraga) {
        this.btnPretraga = btnPretraga;
    }

    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public javax.swing.JTable getTblPregled() {
        return tblPregled;
    }

    public void setTblPregled(javax.swing.JTable tblPregled) {
        this.tblPregled = tblPregled;
    }

    public javax.swing.JTextField getTxtFilter() {
        return txtFilter;
    }

    public void setTxtFilter(javax.swing.JTextField txtFilter) {
        this.txtFilter = txtFilter;
    }

    private void preparePanel() throws Exception {
        srediTabelu();
    }

    private void srediTabelu() throws Exception {
        List<Sudija> listaSudija = KomunikacijaSaServerom.getInstanca().vratiSudije();
        tblPregled.setModel(new SudijeTableModel(listaSudija));

    }

    private boolean validacija() {
        boolean pom = true;
        izbrisi();
        if (txtFilter.getText().isEmpty()) {
            lblErrorPretraga.setText("Unesite pretragu!");
            pom = false;
        }
        return true;
    }

    private void izbrisi() {
        lblErrorPretraga.setText("");
        lblErrorPretraga.setForeground(Color.red);
    }

    private void isprazni() {
        txtFilter.setText("");
    }

    private boolean validacijaTabele() {
        int selectedRow = tblPregled.getSelectedRow();
        return selectedRow != -1;
    }
}
