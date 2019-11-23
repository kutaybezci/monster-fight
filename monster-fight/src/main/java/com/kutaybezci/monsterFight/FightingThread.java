package com.kutaybezci.monsterFight;

import com.kutaybezci.monsterFight.gui.FrmFight;
import com.kutaybezci.monsterFight.gui.FrmQuestion;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import lombok.Synchronized;

/**
 *
 * @author kutay.bezci
 */
public class FightingThread extends Thread {

    private final FrmFight frmFight;
    private final Monster monster;
    private volatile int playerHealth;
    private volatile int monsterHealth;
    private volatile int activeQuestionCount;
    private final Queue<Question> questions;
    private final List<FrmQuestion> activeQuestion = new ArrayList<>();
    //private long lastQuestionAsked;

    public FightingThread(FrmFight frmFight) {
        this.frmFight = frmFight;
        this.monster = this.frmFight.getMonster();
        Collections.shuffle(this.monster.getQuestions());
        questions = new LinkedList<>(this.monster.getQuestions());
        this.activeQuestionCount = 0;
        this.playerHealth = this.monster.getPlayerHealth();
        this.monsterHealth = this.monster.getHealth();
    }

    @Override
    public void run() {
        while (continueFighting()) {
            this.frmFight.paintHealth(this.monsterHealth, this.playerHealth, this.activeQuestionCount);
            if (canAsk()) {
                ask();
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.frmFight.paintHealth(this.monsterHealth, this.playerHealth, this.activeQuestionCount);
        activeQuestion.forEach(t-> t.dispose());

        Translate translate=Translate.getInstance();
        if (this.playerHealth > this.monsterHealth) {
            Icon icon = new ImageIcon(getClass().getClassLoader().getResource("winner.png"));
            String message=translate.translateFormat("msgVictory",this.monster.getName());
            Utils.playSound("eloctro-win.wav");
            JOptionPane.showMessageDialog(this.frmFight, message, translate.translate("titleVictory"), JOptionPane.ERROR_MESSAGE, icon);
        } else {
            Icon icon = new ImageIcon(getClass().getClassLoader().getResource("reaper.png"));
            String message=translate.translateFormat("msgBeaten",this.monster.getSecretMessage());
            Utils.playSound("evillaughter.wav");
            JOptionPane.showMessageDialog(this.frmFight, message, translate.translate("titleBeaten"), JOptionPane.ERROR_MESSAGE, icon);
        }
    }

    private boolean continueFighting() {
        return this.playerHealth > 0 && this.monsterHealth > 0 && questions.size() > 0;
    }

    /*private boolean isMonsterReady() {
        return (System.currentTimeMillis() - this.lastQuestionAsked) > (monster.getQuestionWaitSeconds() * 1000);
    }*/

    private boolean isScreenReady() {
        return this.activeQuestionCount < this.monster.getMaxActiveQuestion();
    }

    private boolean canAsk() {
        return /*isMonsterReady() &&*/ isScreenReady() && this.questions.size() > 0;
    }

    @Synchronized
    public void incrementActiveQuestionCount(int i) {
        System.out.println(String.format("A:%d %d", this.activeQuestionCount, i));
        this.activeQuestionCount += i;
    }

    @Synchronized
    public void incrementPlayerHealth(int i) {
        this.playerHealth += i;
    }

    @Synchronized
    public void incrementMonsterHealth(int i) {
        this.monsterHealth += i;
    }

    private void ask() {
        Question question = this.questions.poll();
        FrmQuestion frmQuestion = new FrmQuestion(question, this);
        activeQuestion.add(frmQuestion);
        Random r = new Random();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int x = r.nextInt(d.width - frmQuestion.getWidth());
        int y = r.nextInt(d.height - frmQuestion.getHeight());
        frmQuestion.setLocation(x, y);
        incrementActiveQuestionCount(1);
        frmQuestion.setVisible(true);
    }
}
