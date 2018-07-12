// abstract class for all AI
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
package eda.tictactoe.model;

import java.util.HashMap;
import java.util.Random;

public abstract class AI {
    // get an ai based on difficulty(just Dumb for now)
    public static AI getDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return new AIEasy();
            default:
                return new AIDumb();
        }
    }

    // randomly generate a starting player
    public static int getStarter() {
        return new Random().nextInt(2);
    }

    // return the ai's decision
    public abstract Integer getPosition(HashMap<Integer, String> cells);
}
