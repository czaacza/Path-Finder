package pl.jimp.pathfinder;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class VertexCircle extends Circle {
    private int row;
    private int column;

    public VertexCircle(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        setFill(Paint.valueOf("violet"));
    }
}
