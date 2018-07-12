// Easy AI
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
/*
    This AI does the following
    Attempt to complete available lines with two conecutive 'O's else
    Attempt to add an O to any line with a single O else
    Return a random available position
 */
package eda.tictactoe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AIEasy extends AI {
    // lines in a board
    private ArrayList<Integer[]> lines;

    public AIEasy() {
        // add lines
        lines = new ArrayList<>();
        lines.add(new Integer[]{0, 1, 2});
        lines.add(new Integer[]{3, 4, 5});
        lines.add(new Integer[]{6, 7, 8});
        lines.add(new Integer[]{0, 3, 6});
        lines.add(new Integer[]{1, 4, 7});
        lines.add(new Integer[]{2, 5, 8});
        lines.add(new Integer[]{0, 4, 8});
        lines.add(new Integer[]{6, 4, 3});
    }

    public Integer getPosition(HashMap<Integer, String> cells) {
        // temporary variable
        Integer temporary;

        // loop through hashmap
        for (int i = 0; i < 9; i++) {
            // if an O has been entered
            if (cells.get(i).equals("O")) {
                // get the set of lines passing through the position of the O
                ArrayList<Integer[]> setOfLines = getSetOfLines(i);
                // loop through the set of lines
                for (Integer[] l : setOfLines) {
                    // if a line can be completed
                    temporary = threeAvailable(cells, l);
                    if (temporary > -1) {
                        // return the completing position
                        return temporary;
                    }
                    // if a line can be increased
                    temporary = twoAvailable(cells, l);
                    if (temporary > -1) {
                        // return the incrementing position
                        return temporary;
                    }
                }
            }
        }
        return getRandomPosition(cells);
    }

    private Integer getRandomPosition(HashMap<Integer, String> cells) {
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

    // this method returns a set of lines the position intersects
    private ArrayList<Integer[]> getSetOfLines(Integer position) {
        ArrayList<Integer[]> setOfLines = new ArrayList<>();
        for (Integer[] i : lines) {
            if (i[0].equals(position) || i[1].equals(position) || i[2].equals(position)) {
                setOfLines.add(i);
            }
        }

        return setOfLines;
    }

    // check if a line can be completed
    private Integer threeAvailable(HashMap<Integer, String> cells, Integer[] line) {
        if (cells.get(line[0]).equals("O") && cells.get(line[1]).equals("O") && cells.get(line[2]).equals(" ")) {
            return line[2];
        }
        if (cells.get(line[0]).equals(" ") && cells.get(line[1]).equals("O") && cells.get(line[2]).equals("O")) {
            return line[0];
        }
        return -1;
    }

    // check if a line can be extended
    private Integer twoAvailable(HashMap<Integer, String> cells, Integer[] line) {
        if (cells.get(line[0]).equals("O") && cells.get(line[1]).equals(" ")) {
            return line[1];
        }
        if (cells.get(line[1]).equals("O") && cells.get(line[0]).equals(" ")) {
            return line[0];
        }
        if (cells.get(line[1]).equals("O") && cells.get(line[2]).equals(" ")) {
            return line[2];
        }
        if (cells.get(line[2]).equals("O") && cells.get(line[1]).equals(" ")) {
            return line[1];
        }
        return -1;
    }
}
