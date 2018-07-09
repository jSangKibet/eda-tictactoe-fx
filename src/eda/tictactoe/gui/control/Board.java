// controller for game board view
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
package eda.tictactoe.gui.control;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board {
    // images
    private Image xImg = new Image("/eda/tictactoe/res/img/x.png");
    private Image oImg = new Image("/eda/tictactoe/res/img/o.png");
    private Image xFadeImg = new Image("/eda/tictactoe/res/img/x_fade.png");

    // view items
    // These imageviews stand for cell 0-8
    @FXML
    private ImageView zero;
    @FXML
    private ImageView one;
    @FXML
    private ImageView two;
    @FXML
    private ImageView three;
    @FXML
    private ImageView four;
    @FXML
    private ImageView five;
    @FXML
    private ImageView six;
    @FXML
    private ImageView seven;
    @FXML
    private ImageView eight;

    // these methods set the image that appears when a cell is hovered on
    private void startHoverCell(ImageView cell) {
        cell.setImage(xFadeImg);
    }

    private void stopHoverCell(ImageView cell) {
        cell.setImage(null);
    }

    // these methods call the above methods for each cell
    @FXML
    private void startHoverZero() {
        startHoverCell(zero);
    }

    @FXML
    private void stopHoverZero() {
        stopHoverCell(zero);
    }

    @FXML
    private void startHoverOne() {
        startHoverCell(one);
    }

    @FXML
    private void stopHoverOne() {
        stopHoverCell(one);
    }

    @FXML
    private void startHoverTwo() {
        startHoverCell(two);
    }

    @FXML
    private void stopHoverTwo() {
        stopHoverCell(two);
    }

    @FXML
    private void startHoverThree() {
        startHoverCell(three);
    }

    @FXML
    private void stopHoverThree() {
        stopHoverCell(three);
    }

    @FXML
    private void startHoverFour() {
        startHoverCell(four);
    }

    @FXML
    private void stopHoverFour() {
        stopHoverCell(four);
    }

    @FXML
    private void startHoverFive() {
        startHoverCell(five);
    }

    @FXML
    private void stopHoverFive() {
        stopHoverCell(five);
    }

    @FXML
    private void startHoverSix() {
        startHoverCell(six);
    }

    @FXML
    private void stopHoverSix() {
        stopHoverCell(six);
    }

    @FXML
    private void startHoverSeven() {
        startHoverCell(seven);
    }

    @FXML
    private void stopHoverSeven() {
        stopHoverCell(seven);
    }

    @FXML
    private void startHoverEight() {
        startHoverCell(eight);
    }

    @FXML
    private void stopHoverEight() {
        stopHoverCell(eight);
    }
}