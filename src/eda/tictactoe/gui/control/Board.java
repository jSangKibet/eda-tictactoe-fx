// controller for game board view
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
package eda.tictactoe.gui.control;

import eda.tictactoe.model.AI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Board implements Initializable {
    // this string is used to set difficulty level
    public static String DIFFICULTY;
    // this object represents the previous view (Main view) for purposes of going back on completion
    public static Parent MAIN;
    // a map of cells and their respective occupants ("X", "O" or " "(space))
    private HashMap<Integer, String> cells;
    // this value represents who will start the game (0-player, 1-computer)
    private int PLAYER1;
    // the AI
    private AI ai;

    // images & view
    private Image xImg;
    private Image oImg;
    private ImageView xFadeImg;

    // view items
    // These StackPanes are visual representations for cells 0-8
    @FXML
    private StackPane zero;
    @FXML
    private StackPane one;
    @FXML
    private StackPane two;
    @FXML
    private StackPane three;
    @FXML
    private StackPane four;
    @FXML
    private StackPane five;
    @FXML
    private StackPane six;
    @FXML
    private StackPane seven;
    @FXML
    private StackPane eight;

    // this map will hold numerical references to the StackPanes above
    private HashMap<Integer, StackPane> cellViews;


    // this method does stuff before the view is shown
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // load game images
        xImg = new Image("/eda/tictactoe/res/img/x.png");
        oImg = new Image("/eda/tictactoe/res/img/o.png");
        xFadeImg = new ImageView("/eda/tictactoe/res/img/x_fade.png");
        xFadeImg.setFitWidth(200);
        xFadeImg.setFitHeight(200);

        // initialize map with spaces
        cells = new HashMap<>();
        cells.put(0, " ");
        cells.put(1, " ");
        cells.put(2, " ");
        cells.put(3, " ");
        cells.put(4, " ");
        cells.put(5, " ");
        cells.put(6, " ");
        cells.put(7, " ");
        cells.put(8, " ");

        // initialize images
        cellViews = new HashMap<>();
        cellViews.put(0, zero);
        cellViews.put(1, one);
        cellViews.put(2, two);
        cellViews.put(3, three);
        cellViews.put(4, four);
        cellViews.put(5, five);
        cellViews.put(6, six);
        cellViews.put(7, seven);
        cellViews.put(8, eight);

        // set listeners for views
        setListeners();

        // get AI according to difficulty, for now it only returns a Dumb AI
        ai = AI.getDifficulty(DIFFICULTY);
        // get starting player
        PLAYER1 = AI.getStarter();

        // show dialog confirming who will begin and start the game
        beginGame();
    }

    // check for game completion by a specific player
    /*
     * the game is completed if the following lines have the same character(String)
     * 0,1,2
     * 3,4,5
     * 6,7,8
     * 0,3,6
     * 1,4,7
     * 2,5,8
     * 0,4,8
     * 6,4,2
     */
    private boolean isCompleted(String player) {
        // 0,1,2
        if (cells.get(0).equals(player) && cells.get(0).equals(cells.get(1)) && cells.get(1).equals(cells.get(2))) {
            return true;
        }
        // and so forth
        if (cells.get(3).equals(player) && cells.get(3).equals(cells.get(4)) && cells.get(4).equals(cells.get(5))) {
            return true;
        }
        if (cells.get(6).equals(player) && cells.get(6).equals(cells.get(7)) && cells.get(7).equals(cells.get(8))) {
            return true;
        }
        if (cells.get(0).equals(player) && cells.get(0).equals(cells.get(3)) && cells.get(3).equals(cells.get(6))) {
            return true;
        }
        if (cells.get(1).equals(player) && cells.get(1).equals(cells.get(4)) && cells.get(4).equals(cells.get(7))) {
            return true;
        }
        if (cells.get(2).equals(player) && cells.get(2).equals(cells.get(5)) && cells.get(5).equals(cells.get(8))) {
            return true;
        }
        if (cells.get(0).equals(player) && cells.get(0).equals(cells.get(4)) && cells.get(4).equals(cells.get(8))) {
            return true;
        }
        return cells.get(6).equals(player) && cells.get(6).equals(cells.get(4)) && cells.get(4).equals(cells.get(2));
    }

    // check if any moves are left
    private boolean checkIfPlayable() {
        // loop through cells
        for (int i = 0; i < 9; i++) {
            // if a space exists
            if (cells.get(i).equals(" ")) {
                // then it is playable
                return true;
            }
        }
        return false;
    }

    // computer move
    private void aiMove() {
        // get position from ai
        int position = ai.getPosition(cells);
        // set "O" in cells map
        cells.put(position, "O");
        // set image on screen
        aiClickCell(cellViews.get(position));
        // check game completion
        if (isCompleted("O")) {
            // end game on completion
            endGame("O");
        } else if (!checkIfPlayable()) {
            // draw
            endGame(" ");
        }
    }

    // process player move
    private void processPlayerMove(int position) {
        // set "X" in cells map
        cells.put(position, "X");
        // check game completion
        if (isCompleted("X")) {
            // end game on completion
            endGame("X");
        } else if (checkIfPlayable()) {
            // let the ai play otherwise
            aiMove();
        } else {
            // draw
            endGame(" ");
        }
    }

    // these methods set the image that appears when a cell is hovered on
    private void startHoverCell(StackPane cell) {
        // check if cell has been clicked i.e. contains an X or O image
        if (!containsImageView(cell)) {
            // add faded x image if not clicked
            cell.getChildren().add(xFadeImg);
        }
    }

    private void stopHoverCell(StackPane cell) {
        // remove faded x image
        cell.getChildren().remove(xFadeImg);
    }

    // when a user clicks an unoccupied a cell
    private void clickCell(StackPane cell) {
        // remove hover image
        cell.getChildren().remove(xFadeImg);
        // add x image
        cell.getChildren().add(getImageView(xImg));
    }

    // when the ai has moved
    private void aiClickCell(StackPane cell) {
        // add o image
        cell.getChildren().add(getImageView(oImg));
    }

    // player move
    private void playerMove(int position) {
        // check if position is unoccupied
        if (cells.get(position).equals(" ")) {
            // set x image on screen
            clickCell(cellViews.get(position));
            // process this move
            processPlayerMove(position);
        }
    }

    // set hover listeners for StackPanes
    private void setListeners() {
        for (Integer i = 0; i < 9; i++) {
            StackPane v = cellViews.get(i);
            int x = i;
            // set hover and click listeners
            v.setOnMouseEntered(event -> startHoverCell(v));
            v.setOnMouseExited(event -> stopHoverCell(v));
            v.setOnMouseClicked(event -> playerMove(x));
        }
    }

    // go back to home
    @FXML
    private void goBack() {
        zero.getScene().setRoot(MAIN);
    }

    // alert to start game
    private void beginGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("TicTacToe");
        String text = "You are player X while the computer is 0\n";
        String text2;
        if (PLAYER1 == 0) {
            text2 = "You will start";
        } else {
            text2 = "The computer will start";
        }
        alert.setContentText(text + "\n" + text2);
        alert.showAndWait();
        // let the ai begin game if it is to start
        if (PLAYER1 == 1) {
            aiMove();
        }
    }

    // alert to finish game
    private void endGame(String player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String text;
        switch (player) {
            case "X":
                text = "You won!";
                break;
            case "O":
                text = "The computer won!";
                break;
            default:
                text = "It's a draw!";
                break;
        }
        alert.setContentText(text);
        alert.showAndWait();
        goBack();
    }

    // get imageview from image
    private ImageView getImageView(Image image) {
        ImageView imageView = new ImageView(image);
        // set width and height
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        return imageView;
    }

    // check if stackpane contains an x or o image
    private boolean containsImageView(StackPane cell) {
        // loop through all imageviews in stack
        for (int i = 0; i < cell.getChildren().size(); i++) {
            // get imageview
            ImageView v = (ImageView) cell.getChildren().get(i);
            // check if image is x or o
            if (v.getImage().equals(xImg) || v.getImage().equals(oImg)) {
                return true;
            }
        }
        return false;
    }
}