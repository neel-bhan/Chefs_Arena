package com.neel_krish_soham.chefs_arena;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.paint.ImagePattern;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class HelloController {

    public HelloController(){
        System.out.println("HelloController instance created at " + System.currentTimeMillis());
    }

    @FXML
    private Circle playerCircle;

    @FXML
    private Pane mainPane;

    private final Person player = new Person();

    public static int money_counter =0;


    private static double targetX;
    private static double targetY;
    private static final double CELL_WIDTH = 213.33;
    private static final double CELL_HEIGHT = 120;
    private ImageView cookedMeatImageView1;
    private ImageView cookedMeatImageView2;
    Map<String, Integer> priceMap = new HashMap<>() {{
        put("drinks", 2);
        put("sushi1", 10);
        put("sushi2", 10);
        put("ramen",15);
        put("tofu", 4);
        put("icecream", 3);
    }};

    @FXML
    private ProgressIndicator pi1;
    @FXML
    private ProgressIndicator pi2;
    @FXML
    private ProgressIndicator pi3;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;

    @FXML
    private Text money;



    public static OrderManager orderManager1;
    public static OrderManager orderManager2;
    public static OrderManager orderManager3;
    @FXML
    private ImageView trayview1;
    @FXML
    private ImageView trayview2;

    private boolean riceCookerInUse = false;
    private boolean riceCookerReady = false;
    private boolean riceCookerInUse2 = false;
    private boolean riceCookerReady2 = false;

    private ImageView cookedRiceImageView;
    private ImageView cookedRiceImageView2; // Store the ImageView of the cooked rice


    @FXML
    public void initialize() {
        // Initialize OrderManager instances here after all FXML components are ready
        orderManager1 = new OrderManager(1, pi1);
        orderManager2 = new OrderManager(2, pi2);
        orderManager3 = new OrderManager(3, pi3);
        orderManager1.setImageViews(image1, image2, image3);
        orderManager2.setImageViews(image4, image5, image6);
        orderManager3.setImageViews(image7, image8, image9);

        orderManager1.image1.setOnMouseClicked(_ -> customer1());
        orderManager1.image2.setOnMouseClicked(_ -> customer1());
        orderManager1.image3.setOnMouseClicked(_ -> customer1());

        orderManager2.image1.setOnMouseClicked(_ -> customer2());
        orderManager2.image2.setOnMouseClicked(_ -> customer2());
        orderManager2.image3.setOnMouseClicked(_ -> customer2());

        orderManager3.image1.setOnMouseClicked(_ -> customer3());
        orderManager3.image2.setOnMouseClicked(_ -> customer3());
        orderManager3.image3.setOnMouseClicked(_ -> customer3());

        trayview1.setOnMouseClicked(_ -> tray1());
        trayview2.setOnMouseClicked(_ -> tray2());

    }
    public void pickUpItem(String itemName) {
        setPlayerImage(itemName + ".png");  // Update the circle's image


    }

    public void dropItem() {
        playerCircle.setFill(Color.TRANSPARENT);  // Clear the circle's fill if using ImagePattern

    }


    public void setPlayerImage(String imageName) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/" + imageName)));
        playerCircle.setFill(new ImagePattern(image));
    }




    public void drinks() {
        setPositionFromGrid(0, 1);

        if (player.hand.isEmpty()) {
            player.hand.add("drinks");
            pickUpItem("drinks");
        } else {
            displayImageAtGridPosition(1, 0);
        }
    }

    public void grill1() {
        setPositionFromGrid(2, 0);
        if (!player.grill1 && !player.hand.isEmpty() && player.hand.getFirst().equals("meat")) {
            displayImageAtGrill(1, 2);
            player.grill1 = true;
            player.hand.removeFirst();
            dropItem();
        } else if (player.grill1 && player.grill1Ready&& !player.hand.isEmpty() && Objects.equals(player.hand.getFirst(), "noodles")) {
            pickUpMeatFromGrill1();
        } else {
            displayImageAtGridPosition(2, 0);
        }
    }

    public void grill2() {
        setPositionFromGrid(3, 0);
        if (!player.grill2 && !player.hand.isEmpty() && player.hand.getFirst().equals("meat")) {
            displayImageAtGrill(2, 3);
            player.grill2 = true;
            player.hand.removeFirst();
            dropItem();
        } else if (player.grill2 && player.grill2Ready && !player.hand.isEmpty() && Objects.equals(player.hand.getFirst(), "noodles")) {
            pickUpMeatFromGrill2();
        } else {
            displayImageAtGridPosition(3, 0);
        }
    }

    private void pickUpMeatFromGrill1() {
        if (player.grill1Ready) {
            mainPane.getChildren().remove(cookedMeatImageView1);
            if(player.hand.isEmpty())
                player.hand.add("ramen");
            else
                player.hand.set(0, "ramen");


            pickUpItem("ramen");


            cookedMeatImageView1 = null;
            player.grill1Ready = false;
            player.grill1 = false;
        } else {
            displayImageAtGridPosition(2, 0);  // Show error or feedback
        }
    }




    private void pickUpMeatFromGrill2() {
        if (player.grill2Ready) {
            mainPane.getChildren().remove(cookedMeatImageView2);
            cookedMeatImageView2 = null;
            if(!player.hand.isEmpty() && player.hand.getFirst().equals("noodles")) {
                player.hand.removeFirst();
                player.hand.add("ramen");
                pickUpItem("ramen");
            }

            player.grill2Ready = false;
            player.grill2 = false;
        } else {
            displayImageAtGridPosition(3, 0);  // Show error or feedback
        }
    }

    public void meat() {
        setPositionFromGrid(4, 0);
        if (player.hand.isEmpty()) {
            player.hand.add("meat");
            pickUpItem("meat");
        } else {
            displayImageAtGridPosition(4, 0);
        }
    }

    public void tofu() {
        setPositionFromGrid(4, 0);

        if (player.hand.isEmpty()) {
            player.hand.add("tofu");
            pickUpItem("tofu");
        } else {
            displayImageAtGridPosition(5, 0);
        }
    }

    public void noodles() {
        setPositionFromGrid(5, 1);

        if (player.hand.isEmpty()) {
            player.hand.add("noodles");
            pickUpItem("noodles");
        } else {
            displayImageAtGridPosition(5, 1);
        }
    }

    public void trash() {
        setPositionFromGrid(5, 2);

        if(!player.hand.isEmpty()) {
            player.hand.removeFirst();
            dropItem();
        }
        }


    public void tray1() {
        setPositionFromGrid(5, 3);

        if(player.tray1 == null && !player.hand.isEmpty())
        {
            String imagePath = "images/" + player.hand.getFirst() + ".png";
            player.tray1 = player.hand.removeFirst();

            InputStream stream = getClass().getResourceAsStream(imagePath);
            Image image = null;
            if (stream != null) {
                image = new Image(stream);
            }
            trayview1.setImage(image);
            dropItem();
        } else if (player.tray1 != null && player.hand.isEmpty()) {
            player.hand.add(player.tray1);
            pickUpItem(player.tray1);
            trayview1.setImage(null);
            player.tray1 = null;

        }
    }

    public void tray2() {
        setPositionFromGrid(5, 4);


        if(player.tray2 == null && !player.hand.isEmpty())
        {
            String imagePath = "images/" + player.hand.getFirst() + ".png";
            player.tray2 = player.hand.removeFirst();

            InputStream stream = getClass().getResourceAsStream(imagePath);
            Image image = null;
            if (stream != null) {
                image = new Image(stream);
            }
            trayview2.setImage(image);
            dropItem();
        } else if (player.tray2 != null && player.hand.isEmpty()) {
            player.hand.add(player.tray2);
            pickUpItem(player.tray2);
            trayview2.setImage(null);
            player.tray2 = null;

        }
    }

    public void sushi1() {
        setPositionFromGrid(4, 5);

        if (!player.hand.isEmpty() && Objects.equals(player.hand.getFirst(), "rice")) {
            player.hand.removeFirst();
            player.hand.add("sushi2");
            pickUpItem("sushi2");
        } else {
            displayImageAtGridPosition(4, 5);
        }
    }

    public void sushi2() {
        setPositionFromGrid(3, 5);

        if (!player.hand.isEmpty() && Objects.equals(player.hand.getFirst(), "rice")) {
            player.hand.removeFirst();
            player.hand.add("sushi1");
            pickUpItem("sushi1");
        } else {
            displayImageAtGridPosition(3, 5);
        }
    }
    @FXML
    public void rice2() {
        System.out.println("rice2");
        int row = 1;
        int col = 5;
        setPositionFromGrid(row, col);
        if (!riceCookerInUse2 && !riceCookerReady2) {
            // Start cooking rice
            ImageView rawRiceImageView2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/raw_rice.png"))));
            rawRiceImageView2.setX(col * CELL_WIDTH+CELL_WIDTH/10 -9);
            rawRiceImageView2.setY(row * CELL_HEIGHT+9);
            rawRiceImageView2.setFitWidth(CELL_HEIGHT);
            rawRiceImageView2.setFitHeight(CELL_HEIGHT);
            rawRiceImageView2.setOnMouseClicked(_ -> rice2());


            mainPane.getChildren().add(rawRiceImageView2);

            riceCookerInUse2 = true;

            PauseTransition riceCookingPause = getPauseTransition(rawRiceImageView2, col, row);
            riceCookingPause.play();
        } else if (riceCookerReady2 && player.hand.isEmpty()) {
            // Pick up cooked rice
            mainPane.getChildren().remove(cookedRiceImageView2 );
            cookedRiceImageView2  = null;
            player.hand.addFirst("rice");
            pickUpItem("cooked_rice");

            riceCookerReady2 = false;
            riceCookerInUse2 = false;
        } else {
            // Display an error if rice is still cooking and not ready to pick up
            displayImageAtGridPosition(row, col);
        }
        if(!player.hand.isEmpty()) System.out.println(player.hand.getFirst());
    }

    private @NotNull PauseTransition getPauseTransition(ImageView rawRiceImageView2, int col, int row) {
        PauseTransition riceCookingPause = new PauseTransition(Duration.seconds(5));
        riceCookingPause.setOnFinished(_ -> {
            mainPane.getChildren().remove(rawRiceImageView2);

            cookedRiceImageView2  = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/cooked_rice.png"))));

            cookedRiceImageView2 .setX(col * CELL_WIDTH+CELL_WIDTH/10 -9);
            cookedRiceImageView2 .setY(row * CELL_HEIGHT+9);
            cookedRiceImageView2 .setFitWidth(CELL_HEIGHT);
            cookedRiceImageView2 .setFitHeight(CELL_HEIGHT);
            cookedRiceImageView2 .setOnMouseClicked(_ -> rice2());
            mainPane.getChildren().add(cookedRiceImageView2 );

            riceCookerReady2 = true;
        });
        return riceCookingPause;
    }

    @FXML
    public void rice() {
        System.out.print("rice");
        int row = 2;
        int col = 5;
        setPositionFromGrid(row, col);
        if (!riceCookerInUse && !riceCookerReady) {
            // Start cooking rice
            ImageView rawRiceImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/raw_rice.png"))));
            rawRiceImageView.setX(col * CELL_WIDTH+CELL_WIDTH/10 -9);
            rawRiceImageView.setY(row * CELL_HEIGHT-9);
            rawRiceImageView.setFitWidth(CELL_HEIGHT);
            rawRiceImageView.setFitHeight(CELL_HEIGHT);
            rawRiceImageView.setOnMouseClicked(_ -> rice());


            mainPane.getChildren().add(rawRiceImageView);

            riceCookerInUse = true;

            PauseTransition riceCookingPause = getTransition(rawRiceImageView, col, row);
            riceCookingPause.play();
        } else if (riceCookerReady && player.hand.isEmpty()) {
            // Pick up cooked rice
            mainPane.getChildren().remove(cookedRiceImageView);
            cookedRiceImageView = null;
            player.hand.addFirst("rice");
            pickUpItem("cooked_rice");

            riceCookerReady = false;
            riceCookerInUse = false;
        } else {
            // Display an error if rice is still cooking and not ready to pick up
            displayImageAtGridPosition(row, col);
        }
        if(!player.hand.isEmpty()) System.out.println(player.hand.getFirst());
    }

    private @NotNull PauseTransition getTransition(ImageView rawRiceImageView, int col, int row) {
        PauseTransition riceCookingPause = new PauseTransition(Duration.seconds(5));
        riceCookingPause.setOnFinished(_ -> {
            mainPane.getChildren().remove(rawRiceImageView);

            cookedRiceImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/cooked_rice.png"))));

            cookedRiceImageView.setX(col * CELL_WIDTH + CELL_WIDTH/10 -9);
            cookedRiceImageView.setY(row * CELL_HEIGHT-9);
            cookedRiceImageView.setFitWidth(CELL_HEIGHT);
            cookedRiceImageView.setFitHeight(CELL_HEIGHT);
            cookedRiceImageView.setOnMouseClicked(_ -> rice());
            mainPane.getChildren().add(cookedRiceImageView);

            riceCookerReady = true;
        });
        return riceCookingPause;
    }


    public void customer1() {
        if (!player.hand.isEmpty()) {
            String firstItemInHand = player.hand.getFirst();

            // Check and complete the item in the order.
            if (orderManager1.getOrders().containsKey(firstItemInHand) && !orderManager1.getOrders().get(firstItemInHand)) {
                orderManager1.getOrders().put(firstItemInHand, true);
                money_counter += priceMap.get(firstItemInHand);
                money.setText("$" + money_counter);
                int index = orderManager1.orders_list.get(firstItemInHand);
                setimages(image1, image2, image3, index);
            } else {
                displayImageAtGridPosition(0, 1);
            }

            if (orderManager1.isOrderComplete()) {
                System.out.println("complete order");
                orderManager1.onOrderExpired();
            }
        } else {
            displayImageAtGridPosition(0, 1);
            if(!player.hand.isEmpty()) System.out.println(player.hand.getFirst());
        }
    }


    public void customer2() {
        if (!player.hand.isEmpty()) {
            String firstItemInHand = player.hand.getFirst();

            // Check and complete the item in the order.
            if (orderManager2.getOrders().containsKey(firstItemInHand) && !orderManager2.getOrders().get(firstItemInHand)) {
                set(firstItemInHand, orderManager2, image4, image5, image6);
            } else {
                displayImageAtGridPosition(0, 2);
                if(!player.hand.isEmpty()) System.out.println(player.hand.getFirst());// Ensure the position reflects this customer's unique UI space.
            }

            if (orderManager2.isOrderComplete()) {
                System.out.println("complete order");
                orderManager2.onOrderExpired();
            }
        } else {
            displayImageAtGridPosition(0, 2);  // Handle cases where player's hand is empty.
        }
    }

    public void customer3() {
        if (!player.hand.isEmpty()) {
            String firstItemInHand = player.hand.getFirst();

            // Check and complete the item in the order.
            if (orderManager3.getOrders().containsKey(firstItemInHand) && !orderManager3.getOrders().get(firstItemInHand)) {
                set(firstItemInHand, orderManager3, image7, image8, image9);
            } else {
                displayImageAtGridPosition(0, 3);
                if(!player.hand.isEmpty()) System.out.println(player.hand.getFirst());// Ensure the position reflects this customer's unique UI space.
            }

            if (orderManager3.isOrderComplete()) {
                System.out.println("complete order");
                orderManager3.onOrderExpired();
            }
        } else {
            displayImageAtGridPosition(0, 3);  // Handle cases where player's hand is empty.
        }
    }

    private void set(String firstItemInHand, OrderManager orderManager3, ImageView image7, ImageView image8, ImageView image9) {
        orderManager3.getOrders().put(firstItemInHand, true);
        int index = orderManager3.orders_list.get(firstItemInHand);
        money_counter += priceMap.get(firstItemInHand);
        money.setText("$" + money_counter);
        setimages(image7, image8, image9, index);
    }

    private void setimages(ImageView image7, ImageView image8, ImageView image9, int index) {
        if(index == 0)
        {
            image7.setImage(null);
        }
        if(index == 1)
        {
            image8.setImage(null);
        }
        if(index == 2)
        {
            image9.setImage(null);
        }
        player.hand.removeFirst();
        dropItem();
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

    private void displayImageAtGridPosition(int row, int col) {
        ImageView imageView;
        imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/" + "cross.png"))));
        imageView.setX(col * CELL_WIDTH);
        imageView.setY(row * CELL_HEIGHT);
        imageView.setFitWidth(CELL_WIDTH);
        imageView.setFitHeight(CELL_HEIGHT);
        mainPane.getChildren().add(imageView);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(_ -> mainPane.getChildren().remove(imageView));
        pause.play();
    }

    private void displayImageAtGrill(int grillNumber, int row) {
        ImageView initialImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/" + "meat.png"))));
        initialImageView.setX(0 * CELL_WIDTH+30);
        initialImageView.setY(row * CELL_HEIGHT);
        initialImageView.setFitWidth(CELL_WIDTH);
        initialImageView.setFitHeight(CELL_HEIGHT);
        mainPane.getChildren().add(initialImageView);


        PauseTransition initialPause = new PauseTransition(Duration.seconds(4));
        initialPause.setOnFinished(_ -> {
            mainPane.getChildren().remove(initialImageView);

            ImageView cookedMeatImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/neel_krish_soham/chefs_arena/images/" + "cooked_meat.png"))));
            cookedMeatImageView.setX(0 * CELL_WIDTH);
            cookedMeatImageView.setY(row * CELL_HEIGHT);
            cookedMeatImageView.setFitWidth(CELL_WIDTH);
            cookedMeatImageView.setFitHeight(CELL_HEIGHT);
            if(grillNumber == 1)
                cookedMeatImageView.setOnMouseClicked(_ -> grill1());
            else
                cookedMeatImageView.setOnMouseClicked(_ -> grill2());
            mainPane.getChildren().add(cookedMeatImageView);

            if (grillNumber == 1) {
                player.grill1Ready = true;
                cookedMeatImageView1 = cookedMeatImageView;
            } else if (grillNumber == 2) {
                player.grill2Ready = true;
                cookedMeatImageView2 = cookedMeatImageView;
            }
        });
        initialPause.play();
    }


}
