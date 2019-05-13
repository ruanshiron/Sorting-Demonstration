package element;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public abstract class Element implements Comparable {
    private int value;

    private int index;

    Rectangle rectangle;

    Label label;

    Pane shape;


    public Element(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public Pane getShape() {
        return shape;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public int compareTo(Object o) {
        return - value + ((Element)o).value;
    }

    abstract public void setStateColor(State state);

    public enum Type {
        COLUMN, BOX
    }

    public enum State {
        COMPARE, SWAP, DONE, BUCKET, DEFAULT
    }
}
