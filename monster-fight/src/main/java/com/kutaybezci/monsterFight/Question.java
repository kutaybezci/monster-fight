package com.kutaybezci.monsterFight;

import java.io.Serializable;

/**
 *
 * @author kutay.bezci
 */
public class Question implements Serializable{
    private String question;
    private String answer;
    private int screenSeconds;
    private int bonus;
    private int penalty;
    private int bleed;
    
    public String getQuestion(){
        return this.question;
    }
    
    public void setQuestion(String question){
        this.question=question;
    }

    public void setScreenSeconds(int screenSeconds) {
        this.screenSeconds=screenSeconds;
    }

    public void setBonus(int bonus) {
       this.bonus=bonus;
    }

    public void setPenalty(int penalty) {
        this.penalty=penalty;
    }

    public void setBleed(int bleed) {
        this.bleed=bleed;
    }

    public void setAnswer(String answer) {
        this.answer=answer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public int getScreenSeconds() {
        return this.screenSeconds;
    }

    public int getBonus() {
        return this.bonus;
    }

    public int getPenalty() {
        return this.penalty;
    }

    public int getBleed() {
        return this.bleed;
    }
}
