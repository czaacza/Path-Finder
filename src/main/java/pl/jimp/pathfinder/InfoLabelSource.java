package pl.jimp.pathfinder;

public enum InfoLabelSource {
    GENERATE(415),
    LOAD(572),
    SAVE(730),
    SPLIT(950);

    private int layoutY;

    InfoLabelSource(int layoutY) {
        this.layoutY = layoutY;
    }

    public int getLayoutY() {
        return layoutY;
    }
}
