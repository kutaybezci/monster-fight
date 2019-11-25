package com.kutaybezci.monsterFight;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author kutay.bezci
 */
public class Utils {

    public static <T> void setField(JTextComponent textField, String name, T t, BiConsumer<T, String> setter) {
        textField.requestFocus();
        if (isBlank(textField.getText())) {
            throw new RuntimeException(Translate.getInstance().translateFormat("errEmptyField", name));
        }
        setter.accept(t, textField.getText());
    }

    public static <T> void setIntegerField(JTextField textField, String name, T t, BiConsumer<T, Integer> setter) {
        textField.requestFocus();
        if (isBlank(textField.getText())) {
            throw new RuntimeException(Translate.getInstance().translateFormat("errEmptyField", name));
        }
        try {
            Integer intText = Integer.valueOf(textField.getText());
            setter.accept(t, intText);
        } catch (NumberFormatException ex) {
            throw new RuntimeException(Translate.getInstance().translateFormat("errNotNumeric", name));
        }
    }

    public static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
    }

    /*public static void playSound(String url) {
        if (Session.getInstance().isSilent()) {
            return;
        }
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Utils.class.getClassLoader().getResourceAsStream(url));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();
    }*/
    public static boolean equals(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.equals(s2);

    }

    public static boolean isBlank(String s1) {
        return s1 == null || "".equals(s1.trim());
    }

    public static File getCurrentPath(String path, boolean fileTrueFolderFalse) throws IOException {
        File file = new File(System.getProperty("user.dir"), path);
        if (!file.exists()) {
            if (fileTrueFolderFalse) {
                file.createNewFile();
            } else {
                file.mkdir();
            }
        }
        return file;
    }

}
