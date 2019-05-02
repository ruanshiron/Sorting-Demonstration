package visual;

import algos.Bubble;
import algos.Bucket;
import algos.Merge;
import algos.Selection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.Algorithm;
import core.Element;
import core.ElementArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane fatherPane;

    @FXML
    private JFXComboBox<Algorithm> comboBox;

    @FXML
    private JFXTextField numberTextField;

    @FXML
    private JFXTextField speedTextField;

    @FXML
    private JFXButton resetButton;

    private ElementArray array;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ComboBox custom init
        Algorithm [] algos = {Bubble.getInstance(), Bucket.getInstance(), Merge.getInstance(), Selection.getInstance()};
        Objects.requireNonNull(algos);
        ObservableList<Algorithm> algoList = FXCollections.observableArrayList(Arrays.asList(algos));
        comboBox.setItems(algoList);

        // TextField only allow Integer Type
        numberTextField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!"0123456789".contains(event.getCharacter())) {
                    event.consume();
                }
            }
        });

        speedTextField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!"0123456789".contains(event.getCharacter())) {
                    event.consume();
                }
            }
        });
    }

    public void handlePlayClicked() {
        if (comboBox.getValue() instanceof Algorithm) {
            comboBox.getValue().sort(array);
        }

        array.getAnimation().play();
    }

    public void handleResetClicked() {
        if (array != null)
            fatherPane.getChildren().removeAll(array.getAll());

        int NoE = Integer.parseInt(numberTextField.getText());
        int Speed = Integer.parseInt(speedTextField.getText());

        array = new ElementArray(NoE);
        fatherPane.getChildren().addAll(array.getAll());

        System.out.println(NoE);
        System.out.println(array.length());
    }
}
