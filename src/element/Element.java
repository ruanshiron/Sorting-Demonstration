package element;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Element extends VBox implements Comparable {
    private int value;

    private int index;

    private Rectangle rectangle;

    private Label label;

    public Element(int value) {
        super(3);

        super.setWidth(Common.WIDTH);

        super.setHeight(value * Common.HEIGHT);

        rectangle = new Rectangle(Common.WIDTH, value * Common.HEIGHT);

        label = new Label(value + "");

        this.value = value;

        label.setFont(Font.font(12));

        super.setAlignment(Pos.BOTTOM_CENTER);
        super.getChildren().addAll(rectangle, label);

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

    public void setFill(Color color) {
        rectangle.setFill(color);
    }
}
