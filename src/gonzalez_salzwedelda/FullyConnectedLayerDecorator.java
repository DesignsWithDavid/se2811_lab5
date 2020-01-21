package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

import static gonzalez_salzwedelda.NodeDraw.*;

public class FullyConnectedLayerDecorator extends LayerDecorator {
    public FullyConnectedLayerDecorator(Layer decoratedLayer, int outputSize) {
        super(decoratedLayer, outputSize);
    }

    @Override
    public void draw(Canvas canvas) {
        super.getDecoratedLayer().draw(canvas);
        webConnectNodes(canvas, inputSize(), outputSize());
        drawNodes(canvas, outputSize());
    }
}
