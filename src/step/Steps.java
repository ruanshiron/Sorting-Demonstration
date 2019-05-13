package step;

import element.Element;
import javafx.scene.control.Label;
import java.util.LinkedList;

public class Steps {
    private LinkedList<Step> steps;

    private Step currentStep;

    public boolean isPlaying = false;

    private Label label;

    public Steps () {
        steps = new LinkedList<>();
    }

    public void setLabel(Label label) {
        this.label = label;
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

        SwapStep s = new SwapStep(node1, node2);

        add(s);
    }

    public void addCompareStep(Element node1, Element node2) {

        CompareStep s = new CompareStep(node1, node2);
        s.setNodes(node1, node2);

        add(s);
    }

    public void addBucketStep(Element node, int bufferIndex) {
        Step s = new BucketStep(node, bufferIndex);

        add(s);
    }

    public void addBucketStep(int bufferIndex, Element node) {
        Step s = new BucketStep(bufferIndex, node);

        add(s);
    }

    public void addDoneStep(Element node) {
        Step s = new DoneStep(node);

        add(s);
    }

    public void play() {
        if (isPlaying) return;

        isPlaying = true;

        if (currentStep == null) return;

        currentStep.play(() -> {
            if (currentStep.getNext()!=null) label.setText(currentStep.getNext().toString());

            currentStep = currentStep.getNext();
        });

        label.setText(currentStep.toString());
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

        if (steps.size() == 0) return;

        pause();

        label.setText("");

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
