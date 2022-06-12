package pl.jimp.pathfinder;

public enum InfoLabelSource {
    GENERATE(364),
    LOAD(503),
    SAVE(637),
    SPLIT(782);

    private int layoutY;

    InfoLabelSource(int layoutY) {
        this.layoutY = layoutY;
    }

    public int getLayoutY() {
        return layoutY;
    }
}
