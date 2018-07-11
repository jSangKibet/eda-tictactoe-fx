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
import javafx.scene.layout.VBox;

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
        difficulties.getItems().addAll("Dumb");
        difficulties.setValue("Dumb");
    }

    @FXML
    public void play() {
        try {
            // set difficulty
            Board.DIFFICULTY = difficulties.getValue();
            // save this view for back purposes
            if (Board.MAIN == null) {
                Board.MAIN = difficulties.getScene().getRoot();
            }
            // load view
            FXMLLoader loader = new FXMLLoader();
            VBox board = loader.load(getClass().getResourceAsStream("/eda/tictactoe/gui/view/board.fxml"));
            // show view
            difficulties.getScene().setRoot(board);
        } catch (IOException e) {
            // handle IOException
            throw new RuntimeException(e);
        }
    }
}
