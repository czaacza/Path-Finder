module pl.jimp.pathfinder {
    requires javafx.controls;
    requires javafx.fxml;
    opens pl.jimp.pathfinder to javafx.fxml;
    exports pl.jimp.pathfinder;
}