package com.kutaybezci.monsterFight;

import static java.lang.Thread.sleep;
import javax.swing.JFrame;

/**
 *
 * @author kutay.bezci
 */
public abstract class TimedFrame extends JFrame {

    private static final long serialVersionUID = -4988913447484610491L;
    private int screenTime;
    private long startedAt;

    public int getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(int screenTime) {
        this.screenTime = screenTime;
    }

    public abstract void refreshRemaining();

    public abstract void timerEvent();

    public TimedFrame(int remainingSeconds) {
        this.screenTime = remainingSeconds;
    }

    public final void startTimer() {
        new Thread(() -> {
            setStartedAt(System.currentTimeMillis());
            while (getRemainingSeconds() >= 0) {
                refreshRemaining();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            timerEvent();
            dispose();
        }).start();
    }

    public long getStartedAt() {
        return startedAt;
    }

    public int getRemainingSeconds() {
        return this.getScreenTime()-(int) (System.currentTimeMillis() - this.getStartedAt()) / 1000;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }
}
