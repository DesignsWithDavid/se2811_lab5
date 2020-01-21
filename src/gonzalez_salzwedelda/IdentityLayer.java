package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

import static gonzalez_salzwedelda.NodeDraw.*;

public class IdentityLayer implements Layer{
    private int outputSize;
    private int inputSize = 0;

    public IdentityLayer(int size){
        outputSize = size;
        inputSize = size;
    }

    @Override
    public int outputSize() {
        return outputSize;
    }

    @Override
    public int inputSize() {
        return inputSize;
    }

    @Override
    public void draw(Canvas canvas) {
        drawNodes(canvas, outputSize);
    }

    @Override
    public int numLayers() {
        return 1;
    }

    @Override
    public int maxNodes() {
        return outputSize;
    }
}
