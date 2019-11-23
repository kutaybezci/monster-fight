package com.kutaybezci.monsterFight;

import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author kutay.bezci
 */
public class Translate {

    private Properties properties;

    private Translate() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("translate.properties")) {
            this.properties = new Properties();
            this.properties.load(in);
        } catch (IOException ex) {
            throw new RuntimeException("Translate file not found");
        }
    }

    public static Translate getInstance() {
        return TranslateHolder.INSTANCE;
    }

    private static class TranslateHolder {

        private static final Translate INSTANCE = new Translate();
    }

    public String translate(String tobeTranslated) {
        return properties.getProperty(tobeTranslated, tobeTranslated);
    }

    public String translateFormat(String tobeTranslated, Object... arg){
        try{
            return String.format(translate(tobeTranslated), arg);
        }catch(IllegalFormatException ex){
            return tobeTranslated;
        }
    }
    public void translate(JLabel jlabel) {
        jlabel.setText(translate(jlabel.getText()));
    }

    public void translate(JButton jbutton) {
        jbutton.setText(translate(jbutton.getText()));
    }

    public void translate(JTable jtable) {
        Translate translate=Translate.getInstance();
        for (int i = 0; i < jtable.getColumnCount(); i++) {
            TableColumn col=jtable.getTableHeader().getColumnModel().getColumn(i);
            col.setHeaderValue(translate.translate(col.getHeaderValue().toString()));
        }
    }
    
    public void translate(JFrame jframe){
        Utils.getAllComponents(jframe).forEach((c) -> {
            if(c instanceof JLabel){
                translate((JLabel)c);
            }else if(c instanceof JButton){
                translate((JButton) c);
            }else if(c instanceof JTable){
                translate((JTable) c);
            }
        });
        
    }
}
