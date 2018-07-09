// controller for main menu view
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
package eda.tictactoe.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

    // view items
    @FXML
    private ChoiceBox<String> difficulties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // add items to difficulty choices and set default to easy
        difficulties.getItems().addAll("Easy", "Normal", "Hard");
        difficulties.setValue("Easy");
    }

    @FXML
    public void play() {
        try {
            FXMLLoader loader = new FXMLLoader();
            GridPane board = (GridPane) loader.load(getClass().getResourceAsStream("/eda/tictactoe/gui/view/board.fxml"));
            difficulties.getScene().setRoot(board);
            // TODO: Add code to go to play screen while setting difficulties
        } catch (IOException e) {
            // handle IOException
            throw new RuntimeException(e);
        }
    }
}
