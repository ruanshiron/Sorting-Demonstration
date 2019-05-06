package core;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Element extends Rectangle implements Comparable {
    private int value;

    private int index;

    private STATE state = STATE.NONE;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Element(int value) {
        super(Common.WIDTH, value * Common.HEIGHT);

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        return - value + ((Element)o).value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void updateState() {
        switch (state) {
            case DONE:
                break;
            case COMPARING:
                break;
            default:
                break;
        }
    }

    enum STATE {
        COMPARING, DONE, NONE
    }
}
