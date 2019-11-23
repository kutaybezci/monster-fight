package com.kutaybezci.monsterFight;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kutay.bezci
 */
public class Utils {

    public static <T> void setField(JTextComponent textField, String name, T t, BiConsumer<T, String> setter) {
        textField.requestFocus();
        if (StringUtils.isBlank(textField.getText())) {
            throw new RuntimeException(Translate.getInstance().translateFormat("errEmptyField", name));
        }
        setter.accept(t, textField.getText());
    }

    public static <T> void setIntegerField(JTextField textField, String name, T t, BiConsumer<T, Integer> setter) {
        textField.requestFocus();
        if (StringUtils.isBlank(textField.getText())) {
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

    public static synchronized void playSound(String url) {
        if(Session.getInstance().isSilent()){
            return;
        }
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Utils.class.getResourceAsStream(url));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
            }
        }
        ).start();
    }
}
