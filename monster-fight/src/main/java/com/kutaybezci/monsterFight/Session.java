package com.kutaybezci.monsterFight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author kutay.bezci
 */
public class Session {

    private String playerName;
    private boolean silent;

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean isSilent() {
        return this.silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
        write();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        write();
    }

    private void write() {
        Properties properties = new Properties();
        properties.setProperty("playerName", this.playerName);
        properties.setProperty("silent", String.valueOf(this.silent));
        File file = new File("monster.config");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                properties.store(fos, "");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private Session() {
        Properties properties = new Properties();
        File file = new File("monster.config");
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                    this.playerName = properties.getProperty("playerName", this.playerName);
                    this.silent = Boolean.valueOf(properties.getProperty("silent", String.valueOf(this.silent)));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.playerName = Translate.getInstance().translate("default.name");
    }

    public static Session getInstance() {
        return SessionHolder.INSTANCE;
    }

    private static class SessionHolder {

        private static final Session INSTANCE = new Session();
    }
}
