package element;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class BoxElement extends Element {
    public BoxElement(int value, int index) {
        super(value, index);

        shape = new StackPane();
        shape.setPrefWidth(Common.WIDTH);
        shape.setPrefHeight(Common.WIDTH);

        rectangle = new Rectangle(Common.WIDTH, Common.WIDTH);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);

        label = new Label(value + "");
        label.setFont(Font.font(12));

        shape.getChildren().addAll(rectangle, label);
    }

    @Override
    public void setStateColor(State state) {
        Color color;

        switch (state) {
            case DONE:
                color = Color.PURPLE;
                break;
            case SWAP:
                color = Color.BLUE;
                break;
            case COMPARE:
                color = Color.YELLOWGREEN;
                break;
            case BUCKET:
                color = Color.GREEN;
                break;
            default:
                color = Color.BLACK;
                break;
        }

        rectangle.setStroke(color);
    }
}
