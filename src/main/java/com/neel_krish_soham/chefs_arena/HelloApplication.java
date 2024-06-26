    package com.neel_krish_soham.chefs_arena;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.control.Menu;
    import javafx.stage.Stage;



    import java.io.IOException;

    import javafx.stage.Stage;
    import javafx.scene.Parent;

    public class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            System.out.println("Loading hello-view.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            System.out.println("hello-view.fxml loaded");

            MenuControl controller = fxmlLoader.getController();
            controller.initStage(stage); // Pass the stage to the controller



            Scene scene = new Scene(root, 1280, 720);
            stage.setTitle("Hello!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();



        }



        public static void main(String[] args) {
            launch(args);
        }
    }
