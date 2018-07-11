// Dumb AI
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
package eda.tictactoe.model;

import java.util.HashMap;
import java.util.Random;

public class AIDumb extends AI {
    public Integer getPosition(HashMap<Integer, String> cells) {
        /* this AI returns random unoccupied positions */

        // some variables
        Random random = new Random();
        int position;

        // generate random position from 0-8 while the previous position is occupied
        do {
            position = random.nextInt(9);
        } while (!cells.get(position).equals(" "));

        // return unoccupied position
        return position;
    }
}
