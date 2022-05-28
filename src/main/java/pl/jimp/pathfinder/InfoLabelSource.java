package pl.jimp.pathfinder;

public enum InfoLabelSource {
    GENERATE(355),
    LOAD(492),
    SAVE(625),
    SPLIT(770);

    private int layoutY;

    InfoLabelSource(int layoutY) {
        this.layoutY = layoutY;
    }

    public int getLayoutY() {
        return layoutY;
    }
}
