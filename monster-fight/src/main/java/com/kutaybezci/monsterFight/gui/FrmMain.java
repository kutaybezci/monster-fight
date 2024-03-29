package com.kutaybezci.monsterFight.gui;

import com.kutaybezci.monsterFight.Monster;
import com.kutaybezci.monsterFight.Session;
import com.kutaybezci.monsterFight.Translate;
import com.kutaybezci.monsterFight.Utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author kutay.bezci
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
        btnHeroName.setText(Session.getInstance().getPlayerName());
        Translate.getInstance().translate(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHeroName = new javax.swing.JButton();
        btnEditMonster = new javax.swing.JButton();
        btnNewMonster = new javax.swing.JButton();
        btnFight = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        btnSoundOff = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnHeroName.setText("default.name");
        btnHeroName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeroNameActionPerformed(evt);
            }
        });

        btnEditMonster.setText("btnEditMonster");
        btnEditMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMonsterActionPerformed(evt);
            }
        });

        btnNewMonster.setText("btnNewMonster");
        btnNewMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewMonsterActionPerformed(evt);
            }
        });

        btnFight.setText("btnFight");
        btnFight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFightActionPerformed(evt);
            }
        });

        btnAbout.setText("btnAbout");
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });

        btnSoundOff.setText("btnSoundOff");
        btnSoundOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoundOffActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monster.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHeroName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditMonster, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewMonster, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(btnFight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSoundOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHeroName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFight)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditMonster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewMonster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSoundOff)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMonsterActionPerformed
        try {
            Monster monster = chooseMonster();
            if (monster == null) {
                return;
            }
            Translate translate = Translate.getInstance();
            String password = JOptionPane.showInputDialog(this, translate.translateFormat("magMonsterPassword", monster.getName()), translate.translate("titleMonsterPassword"), JOptionPane.OK_CANCEL_OPTION);
            if (!Utils.equals(password, monster.getPassword())) {
                JOptionPane.showMessageDialog(this, translate.translate("magMonsterPasswordNotMatch"), translate.translate("titleError"), JOptionPane.ERROR_MESSAGE);
                return;
            }
            new FrmMonsterEditor(monster).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnEditMonsterActionPerformed

    private Monster chooseMonster() throws Exception {
        JFileChooser fileChooser = new JFileChooser(Utils.getCurrentPath("monsters", false));
        fileChooser.setDialogTitle(Translate.getInstance().translate("titleChooseMonster"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
            encoded = Base64.getDecoder().decode(encoded);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(encoded);
                    ObjectInputStream os = new ObjectInputStream(bis)) {
                return (Monster) os.readObject();
            }
        }
        return null;
    }

    private void btnHeroNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeroNameActionPerformed
        Translate translate = Translate.getInstance();
        String name = JOptionPane.showInputDialog(this, translate.translate("msgNameInput"), translate.translate("titleNameInput"), JOptionPane.INFORMATION_MESSAGE);
        if (!Utils.isBlank(name)) {
            Session session = Session.getInstance();
            session.setPlayerName(name);
            this.btnHeroName.setText(session.getPlayerName());
        }
    }//GEN-LAST:event_btnHeroNameActionPerformed

    private void btnNewMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewMonsterActionPerformed
        new FrmMonsterEditor(new Monster()).setVisible(true);
    }//GEN-LAST:event_btnNewMonsterActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        Translate translate = Translate.getInstance();
        Icon icon = new ImageIcon(getClass().getClassLoader().getResource("monster.png"));
        JOptionPane.showMessageDialog(this, translate.translateFormat("lblAbout"), translate.translate("btnAbout"), JOptionPane.ERROR_MESSAGE, icon);
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnSoundOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoundOffActionPerformed
        Session.getInstance().setSilent(this.btnSoundOff.isSelected());
    }//GEN-LAST:event_btnSoundOffActionPerformed

    private void btnFightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFightActionPerformed
        try {
            Monster monster = chooseMonster();
            if (monster == null) {
                return;
            }
            new FrmFight(monster).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnFightActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Session.getInstance();
        try {
            Utils.getCurrentPath("monsters", false);
        } catch (IOException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Translate translate = Translate.getInstance();
        UIManager.put("OptionPane.cancelButtonText", translate.translate("btnCancel"));
        UIManager.put("OptionPane.noButtonText", translate.translate("btnNo"));
        UIManager.put("OptionPane.okButtonText", translate.translate("btnOk"));
        UIManager.put("OptionPane.yesButtonText", translate.translate("btnYes"));
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnEditMonster;
    private javax.swing.JButton btnFight;
    private javax.swing.JButton btnHeroName;
    private javax.swing.JButton btnNewMonster;
    private javax.swing.JToggleButton btnSoundOff;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
