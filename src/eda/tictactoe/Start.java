// program execution starts in this class
/*
    List of file contributors below.
    If you alter this file in your branch, add your name below the following.
    0. Joseph Sang
 */
package eda.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    // JavaFX start method

    // Java main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // stage settings
            // title
            primaryStage.setTitle("TicTacToe");
            // icon
            primaryStage.getIcons().add(new Image("/eda/tictactoe/res/img/x.png"));
            // screen size
            primaryStage.setWidth(700);
            primaryStage.setHeight(700);
            // disable resizing(for now)
            primaryStage.setResizable(false);

            // launch program's main screen(called view hereafter)
            // create FXML loader
            FXMLLoader loader = new FXMLLoader();
            // load main view's fxml file
            VBox main = loader.load(Start.class.getResourceAsStream("/eda/tictactoe/gui/view/main.fxml"));
            primaryStage.setScene(new Scene(main));
            primaryStage.show();
        } catch (IOException e) {
            // handle IOException
            throw new RuntimeException(e);
        }
    }
}
