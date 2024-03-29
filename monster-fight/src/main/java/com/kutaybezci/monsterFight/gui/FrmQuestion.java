package com.kutaybezci.monsterFight.gui;

import com.kutaybezci.monsterFight.FightingThread;
import com.kutaybezci.monsterFight.Question;
import com.kutaybezci.monsterFight.SingletonPlayerThread;
import com.kutaybezci.monsterFight.SingletonPlayerThread.GameSounds;
import com.kutaybezci.monsterFight.TimedFrame;
import com.kutaybezci.monsterFight.Translate;
import com.kutaybezci.monsterFight.Utils;

/**
 *
 * @author kutay.bezci
 */
public class FrmQuestion extends TimedFrame {

    private final Question question;
    private final FightingThread fightingThread;
    private boolean answered = false;

    /**
     * Creates new form QuestionFrm
     *
     * @param question
     * @param fightingThread
     */
    public FrmQuestion(Question question, FightingThread fightingThread) {
        super(question.getScreenSeconds());
        initComponents();
        Translate translate = Translate.getInstance();
        translate.translate(btnAnswer);
        this.question = question;
        this.fightingThread = fightingThread;
        this.jTextArea1.setText(this.question.getQuestion());
        this.jProgressBar1.setMinimum(0);
        this.jProgressBar1.setMaximum(this.getScreenTime());
        this.setTitle(String.format("+%d -%d --%d", this.question.getBonus(), this.question.getPenalty(), this.question.getBleed()));
        this.startTimer();
    }

    public void answer() {
        if (answered || this.fightingThread.getStatus() != FightingThread.Status.FIGHT) {
            return;
        }
        answered = true;
        if (Utils.equals(txtAnswer.getText(), question.getAnswer())) {
            this.fightingThread.incrementMonsterHealth(-question.getBonus());
            SingletonPlayerThread.getInstance().play(GameSounds.GAIN, false);
        } else {
            this.fightingThread.incrementPlayerHealth(-question.getPenalty());
            SingletonPlayerThread.getInstance().play(GameSounds.HIT, false);
        }
        this.fightingThread.incrementActiveQuestionCount(-1);

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
        jTextArea1 = new javax.swing.JTextArea();
        txtAnswer = new javax.swing.JTextField();
        btnAnswer = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        txtAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnswerActionPerformed(evt);
            }
        });

        btnAnswer.setText("btnAnswer");
        btnAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAnswer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnswer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswerActionPerformed
        answer();
        this.dispose();
    }//GEN-LAST:event_btnAnswerActionPerformed

    private void txtAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnswerActionPerformed
        answer();
        this.dispose();
    }//GEN-LAST:event_txtAnswerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FrmQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Question question = new Question();
        question.setScreenSeconds(10);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmQuestion(question, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnswer;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtAnswer;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refreshRemaining() {
        int remainingSeconds = this.getRemainingSeconds();
        this.jProgressBar1.setValue(remainingSeconds);
        this.fightingThread.incrementPlayerHealth(-this.question.getBleed());
    }

    @Override
    public void timerEvent() {
        answer();
    }
}
