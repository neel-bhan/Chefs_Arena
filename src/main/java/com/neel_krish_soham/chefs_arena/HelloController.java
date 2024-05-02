package com.neel_krish_soham.chefs_arena;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import java.io.IOException;
import java.util.HashMap;

public class HelloController {

    @FXML
    private Circle playerCircle;

    @FXML
    private Pane mainPane;

    private Person player = new Person();

    private static double targetX;
    private static double targetY;
    private static final double CELL_WIDTH = 213.33;
    private static final double CELL_HEIGHT = 120;

    public OrderManager orderManager1 = new OrderManager();
    public OrderManager orderManager2 = new OrderManager();
    public OrderManager orderManager3 = new OrderManager();

    private Stage stage; // Reference to the stage

    public void initStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void playgame() {
        try {
            orderManager1.generateOrder();
            orderManager2.generateOrder();
            orderManager3.generateOrder();
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


    private void displayImageAtGridPosition(String imageName, int row, int col) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/" + imageName)));
        imageView.setX(col * CELL_WIDTH);
        imageView.setY(row * CELL_HEIGHT);
        imageView.setFitWidth(CELL_WIDTH);
        imageView.setFitHeight(CELL_HEIGHT);
        mainPane.getChildren().add(imageView);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> mainPane.getChildren().remove(imageView));
        pause.play();
    }


    public void drinks() {
        setPositionFromGrid(0, 1);
        if (player.hand.isEmpty()) {
            player.hand.add("drinks");
        } else {
            displayImageAtGridPosition("cross.png", 1, 0);
        }
    }


    public void grill1() {
        setPositionFromGrid(2, 0);
        if (!player.grill1 && !player.hand.isEmpty() && player.hand.get(0) == "meat") {
            player.grill1 = true;
            player.hand.remove(0);
        } else {
            displayImageAtGridPosition("cross.png", 2, 0);
        }
    }

    public void grill2() {
        setPositionFromGrid(3, 0);
        if (!player.grill2 && !player.hand.isEmpty() && player.hand.get(0) == "meat") {
            player.grill2 = true;
            player.hand.remove(0);
        } else if (player.grill2 && !player.hand.isEmpty() && player.hand.get(0) == "noodles") {
            player.hand.add("ramen");
        } else {
            displayImageAtGridPosition("cross.png", 3, 0);
        }
    }

    public void meat() {
        setPositionFromGrid(4, 0);
        if (player.hand.isEmpty()) {
            player.hand.add("meat");
        } else {
            displayImageAtGridPosition("cross.png", 4, 0);
        }
    }

    public void tofu() {
        setPositionFromGrid(4, 0);
        if (player.hand.isEmpty()) {
            player.hand.add("tofu");
        } else {
            displayImageAtGridPosition("cross.png", 4, 0);
        }
    }

    public void noodles() {
        setPositionFromGrid(5, 1);
        if (player.hand.isEmpty()) {
            player.hand.add("noodles");
        } else {
            displayImageAtGridPosition("cross.png", 5, 1);
        }
    }

    public void trash() {
        setPositionFromGrid(5, 2);
        player.hand.remove(0);
    }

    public void tray1() {
        setPositionFromGrid(5, 3);
    }

    public void tray2() {
        setPositionFromGrid(5, 4);

    }

    public void sushi1() {
        setPositionFromGrid(4, 5);
        if (player.hand.get(0) == "rice") {
            player.hand.remove(0);
            player.hand.add("sushi1");
        } else {
            displayImageAtGridPosition("cross.png", 4, 5);
        }
    }

    public void sushi2() {
        setPositionFromGrid(3, 5);
        if (player.hand.get(0) == "rice") {
            player.hand.remove(0);
            player.hand.add("sushi2");
        } else {
            displayImageAtGridPosition("cross.png", 3, 5);
        }
    }

    public void rice() {
        setPositionFromGrid(2, 5);
        if (player.hand.isEmpty()) {
            player.hand.add("rice");
        } else {
            displayImageAtGridPosition("cross.png", 2, 5);
        }
    }

    public void icecream() {
        setPositionFromGrid(1, 5);
        if (player.hand.isEmpty()) {
            player.hand.add("icecream");
        } else {
            displayImageAtGridPosition("cross.png", 1, 5);
        }
    }

    public void customer1() {

        System.out.println(orderManager1.orders.toString());
        if(!player.hand.isEmpty()) {
            int orderIndex = 0;
            String firstItemInHand = player.hand.get(0);

            // Check and complete the item in the order.
            if (orderManager1.getOrders().containsKey(firstItemInHand) && !orderManager1.getOrders().get(firstItemInHand)) {
                orderManager1.getOrders().put(firstItemInHand, true);
                player.hand.remove(0);
            } else {
                displayImageAtGridPosition("cross.png", 0, 1);
            }

            if (orderManager1.isOrderComplete()) {
                System.out.println("complete order");
                orderManager1.orders = new HashMap<String, Boolean>();
                orderManager1.generateOrder();
            }
        }
        else
            displayImageAtGridPosition("cross.png", 0, 1);


    }

    public void customer2() {

        int orderIndex = 1;
        String firstItemInHand = player.hand.get(0);


        if (orderManager1.getOrders().containsKey(firstItemInHand) && !orderManager1.getOrders().get(firstItemInHand)) {
            orderManager1.getOrders().put(firstItemInHand, true);
            player.hand.remove(0);
        } else {
            displayImageAtGridPosition("cross.png", 0, 2);
        }
    }

    public void customer3() {

        int orderIndex = 2;
        String firstItemInHand = player.hand.get(0);

        // Check and complete the item in the order.
        if (orderManager1.getOrders().containsKey(firstItemInHand) && !orderManager1.getOrders().get(firstItemInHand)) {
            orderManager1.getOrders().put(firstItemInHand, true);
            player.hand.remove(0);
        } else {
            displayImageAtGridPosition("cross.png", 0, 4);
        }
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
