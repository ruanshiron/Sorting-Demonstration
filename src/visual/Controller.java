package visual;

import algo.Bubble;
import algo.Bucket;
import algo.Merge;
import algo.Selection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import algo.Algorithm;
import element.Common;
import element.ElementArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
        stepLabel.setText("");
    }

    public void handleResetClicked() {
        if (array != null) {
            fatherPane.getChildren().removeAll(array.getAll());
            array.steps.stop();
            array = null;
        }


        int numberOfElement = (int)numberSlider.getValue();
        int speed = (int)speedSlider.getValue();

        Common.DURATION = Common.DURATION_MAX / speed;

        array = new ElementArray(numberOfElement);

        array.steps.setLabel(stepLabel);

        fatherPane.getChildren().addAll(array.getAll());

        comboBox.getValue().sort(array);

        pressedResetState();
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
            pressedPauseState();
            return;
        }

        pressedPlayState();

        array.steps.play();
    }

    public void handleStopClicked() {
        array.steps.stop();

        pressedPauseState();

        array.reposition();
    }

    private void pressedPauseState() {
        playButton.setText("PLAY");
        backwardButton.setDisable(false);
        forwardButton.setDisable(false);
    }

    private void pressedPlayState() {
        playButton.setText("PAUSE");
        backwardButton.setDisable(true);
        forwardButton.setDisable(true);
    }

    private void pressedResetState() {
        playButton.setText("PLAY");
        backwardButton.setDisable(false);
        forwardButton.setDisable(false);
    }
}
