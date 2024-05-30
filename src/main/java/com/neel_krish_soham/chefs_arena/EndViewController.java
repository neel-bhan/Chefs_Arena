package com.neel_krish_soham.chefs_arena;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class EndViewController {

    @FXML
    private Label messageLabel; // This could also display a message if needed

    @FXML
    private Text text; // Text field for displaying the final score

    @FXML
    public void initialize() {
        // Display the final score in the Text node
        text.setText("$"+HelloController.moneyscounter);
    }

    public void setMessage(String message) {

    }
}
