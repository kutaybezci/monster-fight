package com.kutaybezci.monsterFight;

import lombok.Data;



/**
 *
 * @author kutay.bezci
 */
@Data
public class Question {
    private String question;
    private String answer;
    private int screenSeconds;
    private int bonus;
    private int penalty;
    private int bleed;
}
