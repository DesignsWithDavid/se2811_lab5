package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

import static gonzalez_salzwedelda.NodeDraw.*;

public class ConvolutionalLayerDecorator extends LayerDecorator{
    public ConvolutionalLayerDecorator(Layer decoratedLayer){
        super(decoratedLayer, decoratedLayer.outputSize());
    }

    @Override
    public void draw(Canvas canvas) {
        super.getDecoratedLayer().draw(canvas);
        directConnectNodes(canvas, outputSize());
        drawNodes(canvas, outputSize());
    }
}
