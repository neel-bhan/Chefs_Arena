package com.neel_krish_soham.chefs_arena;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Circle
        Circle circle = new Circle(50, 150, 20, Color.BLUE);

        // Create a timeline for the animation
        Timeline timeline = new Timeline();

        // Add keyframes to the timeline
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(circle.translateXProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(circle.translateXProperty(), 200))
        );

        // Set the cycle count to indefinite so the animation repeats
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Play the animation
        timeline.play();

        // Create a Group to hold the circle
        Group root = new Group(circle);

        // Create a Scene with the Group as the root node
        Scene scene = new Scene(root, 400, 300);

        // Set the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circle Animation");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
