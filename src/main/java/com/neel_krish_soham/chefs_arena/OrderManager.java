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
import javafx.scene.Parent;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;




public class OrderManager {
    public HashMap<String, Boolean> orders;
    public HashMap<String, Integer> orders_list;


    public int name;
    public String[] possibleItems = {"drinks", "sushi1", "sushi2", "ramen", "tofu"};
    public  Random rand = new Random();
    private Timer timer;
    private int timeLeft;
    private static int totalOrdersCompleted;
    private Stage stage;

    private int initialTime;
    public ProgressIndicator progressIndicator;// Listener for expiration events
    public static int lives = 3;

    public ImageView image1, image2, image3;


    // Existing properties and methods remain unchanged

    // Method to set image views
    public void setImageViews(ImageView img1, ImageView img2, ImageView img3) {
        this.image1 = img1;
        this.image2 = img2;
        this.image3 = img3;

    }

    public void initStage(Stage stage) {
        this.stage = stage;
    }

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(OrderManager.class.getResource("end-view.fxml"));
        Parent root = fxmlLoader.load();


        MenuControl controller = fxmlLoader.getController();
        controller.initStage(stage); // Pass the stage to the controller



        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
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

    // Call this method whenever you update the orders
    public void updateImageViews() {
        HashMap<String, Integer> new_orders = new HashMap<>();
        List<String> items = new ArrayList<>(orders.keySet());
        ImageView[] views = {image1, image2, image3};

        Platform.runLater(() -> {
            for (int i = 0; i < views.length; i++) {
                if (i < items.size()) {
                    // Using getClass().getResourceAsStream() to fetch the image resource
                    String imagePath = "images/" + items.get(i) + ".png";
                    InputStream stream = getClass().getResourceAsStream(imagePath);
                    if (stream != null) {
                        Image image = new Image(stream);
                        if(views[i] == null)
                        {
                            onOrderExpired();
                            break;
                        }
                            
                        views[i].setImage(image);
                        new_orders.put(items.get(i), i);

                    } else {
                        System.out.println("Image not found: " + imagePath);
                    }
                } else {
                    views[i].setImage(null);  // Clear unused ImageView
                }
            }
        });
        orders_list = new_orders;
    }


    public OrderManager(int name, ProgressIndicator progressIndicator) {
        this.name = name;  // Make sure the name is assigned to the instance variable
        this.progressIndicator = progressIndicator;
        generateOrder();
        System.out.println(this.name + " " + this.orders.toString());  // Debug print
        this.initialTime = calculateTime();
        this.timeLeft = initialTime;
        startTimer();
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();  // Cancel any previous timers
            timer.purge();   // Remove all cancelled tasks
        }
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    double progress = timeLeft / (double) initialTime;
                    Platform.runLater(() -> progressIndicator.setProgress(progress));
                } else {
                    timer.cancel();  // Make sure to cancel the timer
                    timer.purge();   // Clean up the cancelled timer task
                    Platform.runLater(() -> progressIndicator.setProgress(0));
                    System.out.println(name + " order has expired.");
                    lives--;
                    onOrderExpired();
                    if (lives == 0) {
                        Platform.runLater(() -> showEndScreen());
                    }
                }
            }
        }, 1000, 1000);
    }

    private void showEndScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/neel_krish_soham/chefs_arena/end-view.fxml"));
            Parent root = loader.load();

            EndViewController controller = loader.getController();
            controller.setMessage("Game Over! You have no more lives left.");

            Scene scene = new Scene(root, 1280, 720);
            Stage stage = (Stage) progressIndicator.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading end-view.fxml.");
        }
    }
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }







    private int calculateTime() {
        int baseTimePerItem = 25;
        int decrementPerTenOrders = 5; // Significantly increased reduction
        int timeReduction = totalOrdersCompleted / 3 * decrementPerTenOrders; // Reductions occur more frequently, every 3 orders

        int totalItems = orders.size();
        int baseTime = totalItems * baseTimePerItem;
        int finalTime = baseTime - timeReduction;

        return Math.max(finalTime, 10);
    }


    public void generateOrder() {
        HashMap<String, Boolean> newOrder = new HashMap<>();
        HashMap<String, Integer> newOrderList = new HashMap<>();
        int itemsCount = rand.nextInt(3) + 1; // Generates between 1 and 3 items per order.

        for (int i = 0; i < itemsCount; i++) {
            String item = possibleItems[rand.nextInt(possibleItems.length)];

            newOrder.put(item, false); // All items start as not completed.
        }

        this.orders = newOrder;

        updateImageViews();
    }



    public boolean isOrderComplete() {
        return !orders.containsValue(false);
    }


    public HashMap<String, Boolean> getOrders() {
        return orders;
    }

    public void onOrderExpired()  {

        generateOrder();
        System.out.println(this.name + " " + this.orders.toString());  // Debug print
        this.initialTime = calculateTime();
        this.timeLeft = initialTime;
        startTimer();


            // Further logic to handle the expiration, such as resetting the order, etc.
        }
    }
