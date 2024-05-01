package com.neel_krish_soham.chefs_arena;

import javafx.scene.shape.Circle;

public class Person {
    private Circle circle;
    private double speed;

    public Person(Circle circle, double speed) {
        this.circle = circle;
        this.speed = speed;
    }

    public void moveLeft() {
        circle.setLayoutX(circle.getLayoutX() - speed);
    }

    public void moveRight() {
        circle.setLayoutX(circle.getLayoutX() + speed);
    }

    public void moveUp() {
        circle.setLayoutY(circle.getLayoutY() - speed);
    }

    public void moveDown() {
        circle.setLayoutY(circle.getLayoutY() + speed);
    }

    // You can add more methods as needed for your game, like boundary checking, collision detection, etc.
}
