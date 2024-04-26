module com.neel_krish_soham.chefs_arena {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens com.neel_krish_soham.chefs_arena to javafx.fxml;
    exports com.neel_krish_soham.chefs_arena;
}