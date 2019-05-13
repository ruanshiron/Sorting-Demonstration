package visual;

import algo.Bubble;
import algo.Bucket;
import algo.Merge;
import algo.Selection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import algo.Algorithm;
import element.Common;
import element.Element;
import element.ElementArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private JFXRadioButton columnRadioButton;

    @FXML
    private JFXRadioButton boxRadioButton;

    private ElementArray array;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ComboBox custom init
        Algorithm [] algos = {Bubble.getInstance(), Bucket.getInstance(), Merge.getInstance(), Selection.getInstance()};
        Objects.requireNonNull(algos);
        ObservableList<Algorithm> algoList = FXCollections.observableArrayList(Arrays.asList(algos));
        comboBox.setItems(algoList);

        // Slider setting
        numberSlider.setMax(21);
        numberSlider.setMin(1);
        numberSlider.setMajorTickUnit(5);
        numberSlider.setMinorTickCount(5);

        speedSlider.setMax(5);
        speedSlider.setMin(1);

        //Radio Button
        columnRadioButton.setSelected(true);

        columnRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Common.ELEMENT_TYPE = Element.Type.COLUMN;
                boxRadioButton.setSelected(false);
            } else {
                boxRadioButton.setSelected(true);
            }
        });

        boxRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Common.ELEMENT_TYPE = Element.Type.BOX;
                columnRadioButton.setSelected(false);
            } else {
                columnRadioButton.setSelected(true);
            }
        });

        // Customize
        comboBox.getSelectionModel().select(0);
        numberSlider.setValue(9);
        speedSlider.setValue(4);
        stepLabel.setText("");
    }

    public void handleResetClicked() {
        if (array != null) {
            fatherPane.getChildren().removeAll(array.getAllShape());
            array.steps.stop();
            array = null;
        }


        int numberOfElement = (int)numberSlider.getValue();
        int speed = (int)speedSlider.getValue();

        Common.DURATION = Common.DURATION_MAX / speed;

        array = new ElementArray(numberOfElement);

        array.steps.setLabel(stepLabel);

        fatherPane.getChildren().addAll(array.getAllShape());

        comboBox.getValue().sort(array);

        pressedResetState();
    }

    public void handleBackwardClicked () {
        if (array == null) return;

        array.steps.backward();
    }

    public void handleForwardClicked() {
        if (array == null) return;
        array.steps.forward();
    }

    public void handlePlayClicked() {
        if (array == null) return;

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

    public void minimize(ActionEvent e ) {
        ((Stage)((JFXButton)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    public void close() {
        System.exit(0);
    }
}
