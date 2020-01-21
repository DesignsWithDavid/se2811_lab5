package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

public class ConvolutionalLayerDecorator extends LayerDecorator{
    public ConvolutionalLayerDecorator(Layer decoratedLayer){
        super(decoratedLayer, decoratedLayer.outputSize());
    }

    @Override
    public void draw(Canvas canvas) {
        super.getDecoratedLayer().draw(canvas);
        //TODO
    }
}
