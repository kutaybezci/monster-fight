package com.kutaybezci.monsterFight;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author kutay.bezci
 */
public enum SoundPlayer {
    HIT("ah.wav"), GAIN("picuv.wav"), WIN("yehu.wav"), LOST("vahvah.wav");
    Clip clip;

    SoundPlayer(String resourcePath) {
        try {
            this.clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Utils.class.getClassLoader().getResourceAsStream(resourcePath));
            clip.open(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (Session.getInstance().isSilent()) {
            return;
        }
        new Thread(() -> {
            try {
                if(clip.isRunning()) clip.stop();
                clip.setFramePosition(0);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}


