package com.neel_krish_soham.chefs_arena;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


import java.io.IOException;

public class HelloController {

    @FXML
    private Circle playerCircle;

    private static double targetX;
    private static double targetY;
    private static final double CELL_WIDTH = 213.33;
    private static final double CELL_HEIGHT = 120;

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

            // Set the game scene on the current stage
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, maybe show an error message
        }
    }
    public void setPositionFromGrid(int row, int col) {
        if (col == 0) {
            // General case for any row, column 0, moves to the same row, column 1
            calculateCenter(row, 1);
        } else {
            switch (row) {
                case 0:
                    if (col >= 1 && col <= 4) calculateCenter(1, col);
                    break;
                case 1:
                    if (col == 5) calculateCenter(1, 4);
                    break;
                case 2:
                    if (col == 5) calculateCenter(2, 4);
                    break;
                case 3:
                    if (col == 5) calculateCenter(3, 4);
                    break;
                case 4:
                    if (col == 5) calculateCenter(4, 4);
                    break;
                case 5:
                    if (col >= 1 && col <= 4) calculateCenter(4, col);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid grid position");
            }
        }
    }

    public void drinks()
    {
        setPositionFromGrid(0, 1);
    }
    public void grill()
    {
        setPositionFromGrid(3, 0);
    }
    public void meat()
    {
        setPositionFromGrid(4, 0);
    }
    public void tofu()
    {
        setPositionFromGrid(4, 0);
    }
    public void ramen()
    {
        setPositionFromGrid(5, 1);
    }
    public void trash()
    {
        setPositionFromGrid(5, 2);
    }
    public void tray1(){
        setPositionFromGrid(5, 3);
    }
    public void tray2(){
        setPositionFromGrid(5, 4);
    }
    public void sushi1()
    {
        setPositionFromGrid(4, 5);
    }
    public void sushi2()
    {
        setPositionFromGrid(3, 5);
    }
    public void sushiroller()
    {
        setPositionFromGrid(2, 5);
    }
    public void icecream()
    {
        setPositionFromGrid(1, 5);
    }



    @FXML
    protected void move() {

        // Calculate deltas
        double deltaX = targetX - playerCircle.getLayoutX() - playerCircle.getTranslateX();
        double deltaY = targetY - playerCircle.getLayoutY() - playerCircle.getTranslateY();

        // Create the timeline and add keyframes
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(playerCircle.translateXProperty(), playerCircle.getTranslateX()),
                        new KeyValue(playerCircle.translateYProperty(), playerCircle.getTranslateY())),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(playerCircle.translateXProperty(), playerCircle.getTranslateX() + deltaX),
                        new KeyValue(playerCircle.translateYProperty(), playerCircle.getTranslateY() + deltaY))
        );
        timeline.play();
    }


    private void calculateCenter(int row, int col) {
        targetX = col * CELL_WIDTH + CELL_WIDTH / 2;
        targetY = row * CELL_HEIGHT + CELL_HEIGHT / 2;
        move();

    }


}
