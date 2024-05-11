package com.neel_krish_soham.chefs_arena;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller class for the tutorial view in the application.
 * This class manages the transitions and user interactions within the tutorial phase of the game.
 */
public class TutorialView {
    @FXML
    private Stage stage; // Stage reference
    public ImageView imageView;

    /**
     * Trigger the start of the game by transitioning to the end screen.
     */
    @FXML
    private void startGame() {
        Platform.runLater(() -> showEndScreen());
    }

    /**
     * Loads the end screen view from FXML and sets it on the current stage.
     * It handles loading of the "game-view.fxml" and sets up the scene on the current window.
     */
    private void showEndScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/neel_krish_soham/chefs_arena/game-view.fxml"));
            Parent root = loader.load();

            HelloController controller = loader.getController();

            Scene scene = new Scene(root, 1280, 720);
            Stage stage = (Stage) imageView.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading end-view.fxml.");
        }
    }

    /**
     * Initializes the stage reference.
     * @param stage The main stage of the application to be controlled.
     */
    public void initStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Starts the application on the provided stage by setting the scene and configuration.
     * It loads "end-view.fxml" as the initial scene.
     * @param stage The primary stage for this application.
     * @throws IOException If there is an error loading the FXML.
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrderManager.class.getResource("end-view.fxml"));
        Parent root = fxmlLoader.load();

        MenuControl controller = fxmlLoader.getController();
        stage = (Stage) imageView.getScene().getWindow();
        controller.initStage(stage); // Pass the stage to the controller

        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the gameplay initiation.
     * Loads the "end-view.fxml" as the game scene and sets it on the current stage.
     */
    @FXML
    public void playgame() {
        try {
            // Load the game scene
            Pane gamePane = FXMLLoader.load(getClass().getResource("end-view.fxml"));
            Scene gameScene = new Scene(gamePane, 1280, 720);

            // Set the game scene on the current stage
            stage.setScene(gameScene);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, maybe show an error message
        }
    }
}
