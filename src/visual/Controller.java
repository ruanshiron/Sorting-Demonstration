package visual;

import algos.Bubble;
import algos.Bucket;
import algos.Merge;
import algos.Selection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import core.Algorithm;
import core.Common;
import core.Element;
import core.ElementArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
    private JFXSlider numberSlider;

    @FXML
    private JFXSlider speedSlider;

    @FXML
    private JFXButton playButton;

    @FXML
    private JFXButton backwardButton;

    @FXML
    private JFXButton forwardButton;

    @FXML
    private Label stepLabel;

    @FXML
    private JFXButton stopButton;

    private ElementArray array;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ComboBox custom init
        Algorithm [] algos = {Bubble.getInstance(), Bucket.getInstance(), Merge.getInstance(), Selection.getInstance()};
        Objects.requireNonNull(algos);
        ObservableList<Algorithm> algoList = FXCollections.observableArrayList(Arrays.asList(algos));
        comboBox.setItems(algoList);

        // Slider setting
        numberSlider.setMax(30);
        numberSlider.setMin(0);

        speedSlider.setMax(5);
        speedSlider.setMin(1);

        // Customize
        comboBox.getSelectionModel().select(0);
        numberSlider.setValue(9);
        speedSlider.setValue(4);

    }

    public void handleResetClicked() {
        if (array != null)
            fatherPane.getChildren().removeAll(array.getAll());

        int NoE = (int)numberSlider.getValue();
        int Speed = (int)speedSlider.getValue();

        Common.DURATION = Common.DURATION_MAX / Speed;

        array = new ElementArray(NoE);
        array.steps.label = stepLabel;
        fatherPane.getChildren().addAll(array.getAll());

        if (comboBox.getValue() instanceof Algorithm) {
            comboBox.getValue().sort(array);
        }

    }

    public void handleBackwardClicked () {
        array.steps.backward();
    }

    public void handleForwardClicked() {
        array.steps.forward();
    }


    public void handlePlayClicked() {
        if (array.steps.isPlaying) {
            array.steps.pause();
            playButton.setText("PLAY");
            backwardButton.setDisable(false);
            forwardButton.setDisable(false);
            return;
        }

        playButton.setText("PAUSE");
        backwardButton.setDisable(true);
        forwardButton.setDisable(true);
        array.steps.play();
    }

    public void handleStopClicked() {
        array.steps.stop();
        playButton.setText("PLAY");
        backwardButton.setDisable(false);
        forwardButton.setDisable(false);

        array.reposition();
    }
}
