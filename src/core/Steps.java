package core;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;

public class Steps {
    private LinkedList<Step> steps;

    private Step currentStep;

    public boolean isPlaying = false;

    public Label label;

    Steps () {
        steps = new LinkedList<>();
    }

    public void add(Step step) {
        if (steps.size() == 0) {
            step.setNext(null);
            step.setPrevious(null);

            currentStep = step;
            steps.add(step);
            return;
        }

        step.setPrevious(steps.getLast());
        step.setNext(null);
        steps.getLast().setNext(step);
        steps.add(step);

    }

    public void addSwapStep(Element node1, Element node2) {

        // Swap Animation
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByX(Common.DISTANCE * (node1.getIndex() - node2.getIndex()));
        tt1.setNode(node1);

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));
        tt2.setByX(Common.DISTANCE * (- node1.getIndex() + node2.getIndex()));
        tt2.setNode(node2);

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);


        // Swap Reverse
        TranslateTransition tt3 = new TranslateTransition();
        tt3.setDuration(Duration.seconds(Common.DURATION));
        tt3.setByX(- Common.DISTANCE * (node1.getIndex() - node2.getIndex()));
        tt3.setNode(node1);

        TranslateTransition tt4 = new TranslateTransition();
        tt4.setDuration(Duration.seconds(Common.DURATION));
        tt4.setByX(- Common.DISTANCE * (- node1.getIndex() + node2.getIndex()));
        tt4.setNode(node2);

        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().addAll(tt3, tt4);

        // Step init

        SwapStep s = new SwapStep(pt, pt2, "SWAP    [" + node1.getIndex() + "] & ["+ node2.getIndex() + "]");
        s.setNodes(node1, node2);

        add(s);
    }

    public void addCompareStep(Element node1, Element node2) {

        FillTransition ft1 = new FillTransition();
        ft1.setShape(node1);
        ft1.setDuration(Duration.seconds(Common.DURATION));
        ft1.setToValue(Color.YELLOWGREEN);

        FillTransition ft2 = new FillTransition();
        ft2.setShape(node2);
        ft2.setDuration(Duration.seconds(Common.DURATION));
        ft2.setToValue(Color.YELLOWGREEN);

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(ft1, ft2);

        CompareStep s = new CompareStep(pt, pt, "COMPARE ["+ node1.getIndex() + "] & ["+ node2.getIndex() + "]");
        s.setNodes(node1, node2);

        add(s);
    }

    public void addBucketStep(Animation a, Animation r, Element node1) {
        Step s = new BucketStep(a, r, "BUCKET");
        s.setNodes(node1, node1);

        add(s);
    }

    public void play() {
        if (isPlaying) return;

        isPlaying = true;

        if (currentStep == null) return;

        currentStep.play(() -> {
            label.setText(currentStep.toString());

            currentStep = currentStep.getNext();
        });
    }

    public void pause() {
        if (!isPlaying) return;

        isPlaying = false;

        if (currentStep == null) return;

        currentStep.playOne(() -> {
            label.setText(currentStep.toString());
            currentStep = currentStep.getNext();
        });
    }

    public void forward() {
        isPlaying = false;

        if (currentStep == null) return;

        currentStep.playOne(() -> {
            label.setText(currentStep.toString());

            currentStep = currentStep.getNext();
        });
    }

    public void backward() {
        isPlaying = false;

        if (currentStep == null) {
            currentStep = steps.getLast();
            currentStep.reverse(() -> { });
            return;
        }

        if (currentStep.getPrevious() ==  null) return;

        currentStep.getPrevious().reverse(() -> {
            label.setText(currentStep.getPrevious().toString());
            currentStep = currentStep.getPrevious();
        });

    }

    public void stop() {
        pause();

        if (currentStep != null) {

            currentStep.stop();
            currentStep.reverseElementState();

            if (currentStep.getPrevious() != null)
                currentStep.getPrevious().reverseElementState();
        } else {
            steps.getLast().reverseElementState();
        }

        currentStep = steps.getFirst();
    }
}
