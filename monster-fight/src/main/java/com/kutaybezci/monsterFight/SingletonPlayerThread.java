package com.kutaybezci.monsterFight;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @author Kutay Bezci
 */
public class SingletonPlayerThread extends Thread {

    public enum GameSounds {
        HIT("ah.wav"), GAIN("picuv.wav"), WIN("yehu.wav"), LOST("vahvah.wav");
        String name;

        GameSounds(String resourcePath) {
            this.name = resourcePath;
        }
    }

    private ConcurrentLinkedQueue<GameSounds> playQueue = new ConcurrentLinkedQueue<>();

    private SingletonPlayerThread() {
        start();
    }

    public static SingletonPlayerThread getInstance() {
        return SingletonPlayerThreadHolder.INSTANCE;
    }

    private static class SingletonPlayerThreadHolder {

        private static final SingletonPlayerThread INSTANCE = new SingletonPlayerThread();
    }

    public void play(GameSounds gameSounds, boolean empty) {
        if (empty) {
            this.playQueue.clear();
        }
        this.playQueue.add(gameSounds);
    }

    public void run() {
        while (true) {
            try {
                if (!this.playQueue.isEmpty()) {
                    GameSounds gameSounds = playQueue.poll();
                    try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(gameSounds.name);
                            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream)) {
                        DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
                        Clip clip = (Clip) AudioSystem.getLine(info);
                        clip.open(audioStream);
                        clip.start();

                    }
                }
                sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

}
