package com.neel_krish_soham.chefs_arena;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuControl {
    private Stage stage; // Reference to the stage

    public void initStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void playgame() {
        try {
            // Load the game scene
            Pane gamePane = FXMLLoader.load(getClass().getResource("game-view.fxml"));
            Scene gameScene = new Scene(gamePane, 1280, 720);
            stage.setScene(gameScene); // Set the game scene on the current stage
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions, maybe show an error message
        }
    }

    @FXML
    protected void openTutorial() {

        try {
            // Load the tutorial scene
            Pane tutorialPane = FXMLLoader.load(getClass().getResource("tutorial-view.fxml"));
            Scene tutorialScene = new Scene(tutorialPane, 1280, 720);
            stage.setScene(tutorialScene); // Set the tutorial scene on the current stage
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
}
