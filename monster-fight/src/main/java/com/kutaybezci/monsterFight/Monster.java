package com.kutaybezci.monsterFight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author kutay.bezci
 */
@Data
public class Monster implements Serializable{
    private String name;
    private String description;
    private String password;
    private int health;
    private int playerHealth;
    private int maxActiveQuestion;
    //private int questionWaitSeconds;
    //private String victoryMessage;
    private String secretMessage;
    private List<Question> questions=new ArrayList<>();
}
