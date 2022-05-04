package pl.jimp.pathfinder;

public enum InfoLabelSource {
    GENERATE(415),
    LOAD(590),
    SAVE(720),
    SPLIT(955);

    private int layoutY;

    InfoLabelSource(int layoutY) {
        this.layoutY = layoutY;
    }

    public int getLayoutY() {
        return layoutY;
    }
}
