package element;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ColumnElement extends Element {
    public ColumnElement(int value, int index) {
        super(value, index);

        shape = new VBox(Common.HEIGHT);
        shape.setPrefWidth(Common.WIDTH);
        shape.setPrefHeight(value * Common.HEIGHT);

        rectangle = new Rectangle(Common.WIDTH, value * Common.HEIGHT);

        label = new Label(value + "");
        label.setFont(Font.font(12));

        ((VBox)shape).setAlignment(Pos.BOTTOM_CENTER);
        shape.getChildren().addAll(rectangle, label);
    }

    @Override
    public void setStateColor(State state) {
        switch (state) {
            case DONE:
                rectangle.setFill(Color.PURPLE);
                break;
            case SWAP:
                rectangle.setFill(Color.BLUE);
                break;
            case COMPARE:
                rectangle.setFill(Color.YELLOWGREEN);
                break;
            case BUCKET:
                rectangle.setFill(Color.GREEN);
                break;
            default:
                rectangle.setFill(Color.BLACK);
                break;
        }


    }
}
