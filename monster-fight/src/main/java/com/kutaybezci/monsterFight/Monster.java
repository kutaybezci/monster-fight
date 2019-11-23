package com.kutaybezci.monsterFight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kutay.bezci
 */
public class Monster implements Serializable{
    private String name;
    private String description;
    private String password;
    private int health;
    private int playerHealth;
    private int maxActiveQuestion;
    private String secretMessage;
    private List<Question> questions=new ArrayList<>();

    public List<Question> getQuestions() {
        return this.questions;
    }

    public int getPlayerHealth() {
        return this.playerHealth;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxActiveQuestion() {
        return this.maxActiveQuestion;
    }

    public String getSecretMessage() {
        return this.secretMessage;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setMaxActiveQuestion(int maxActiveQuestion) {
        this.maxActiveQuestion=maxActiveQuestion;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth=playerHealth;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions=questions;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setHealth(int health) {
        this.health=health;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setSecretMessage(String secretMessage) {
        this.secretMessage=secretMessage;
    }

    public void setPassword(String password){
        this.password=password;
    }
    
    public String getDescription() {
        return this.description;
    }
}
