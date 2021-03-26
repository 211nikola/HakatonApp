/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.form;

import communication.KomunikacijaSaServerom;
import controller.GUIKordinator;
import domain.Mentor;
import java.awt.Color;
import java.awt.Frame;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.table.model.MentoriTableModel;

/**
 *
 * @author Nikola
 */
public class PnlMentorPregled extends javax.swing.JPanel {

    /**
     * Creates new form PnlMentorPregled
     */
    public PnlMentorPregled() throws Exception {
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

        txtFilter = new javax.swing.JTextField();
        btnPretraga = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPregled = new javax.swing.JTable();
        btnDetalji = new javax.swing.JButton();
        lblErrorPretraga = new javax.swing.JLabel();
        btnObrisi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Pregled mentora"));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalji, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorPretraga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFilter)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPretraga))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(lblErrorPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetalji)
                    .addComponent(btnObrisi))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPretragaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretragaActionPerformed
        // TODO add your handling code here:
        if (validacija()) {
            try {
                String s = "" + txtFilter.getText();
                List<Mentor> lista = KomunikacijaSaServerom.getInstanca().vratiMentore(s);
                tblPregled.setModel(new MentoriTableModel(lista));

                izbrisi();
                //isprazni();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da izvrsi pretragu!", "Greska", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(PnlHakatonPregled.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPretragaActionPerformed

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed
        try {
            MentoriTableModel ttm = (MentoriTableModel) tblPregled.getModel();
            if (tblPregled.getSelectedRow() != -1) {
                Mentor m = ttm.getMentor(tblPregled.getSelectedRow());
                GUIKordinator.getInstance().getMap().put("izabrani_mentor", m);
                FrmAdminDetaljiMentor f = new FrmAdminDetaljiMentor((Frame) SwingUtilities.getWindowAncestor(this), true, ModFormeDetalji.SHOW);
                f.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Izaberite mentora!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception ex) {
            Logger.getLogger(PnlMentorPregled.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Sistem ne moze da prikaze detalje o mentoru.", "Greska", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        // TODO add your handling code here:
        try {
            if (validacijaTabele()) {
                String[] opcije = {"Da", "Ne"};
                int input = JOptionPane.showConfirmDialog(null,
                        "Da li ste sigurni da zelite da obrisete mentora?",
                        "Potvrda brisanja", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (input) {
                    case 0:
                        MentoriTableModel mtm = (MentoriTableModel) tblPregled.getModel();
                        Mentor m = mtm.getMentor(tblPregled.getSelectedRow());
                        KomunikacijaSaServerom.getInstanca().obrisiMentora(m);
                        JOptionPane.showMessageDialog(this, "Sistem je obrisao mentora", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;

                }

            } else {
                JOptionPane.showMessageDialog(this, "Morate izabrati mentora", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise mentora", "Uspesno", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            // TODO add your handling code here:
            String s = "" + txtFilter.getText();
            List<Mentor> lista = KomunikacijaSaServerom.getInstanca().vratiMentore(s);
            tblPregled.setModel(new MentoriTableModel(lista));
        } catch (Exception ex) {
            Logger.getLogger(PnlMentorPregled.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_txtFilterKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
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
        List<Mentor> listaMentora = KomunikacijaSaServerom.getInstanca().vratiMentore();
        tblPregled.setModel(new MentoriTableModel(listaMentora));
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
        return tblPregled.getSelectedRow() != -1;
    }

    public static boolean confirmDelete(String msg) {
        String str = JOptionPane.showInputDialog(msg);
        if (str != null && str.toLowerCase().equals("delete")) {
            return true;
        }
        return false;
    }
}
