package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;


public class ConvolutionalLayerDecorator extends LayerDecorator{
    public ConvolutionalLayerDecorator(Layer decoratedLayer){
        super(decoratedLayer, decoratedLayer.outputSize());
    }

    @Override
    public void draw(Canvas canvas, int maxNodes, int numLayers, double xPosition) {
        double height = canvas.getHeight();
        double width = canvas.getWidth();
        double radius = height/(maxNodes*5);
        double interval = height / (outputSize() + 2);

        for (int i = 1; i <= outputSize(); i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*interval)- radius,2* radius,2* radius);
        }

        for (int i = 1; i <= outputSize(); i++){
            drawLine(canvas,xPosition,(i*interval), xPosition-width/(numLayers),(i*interval), radius);
        }

        super.getDecoratedLayer().draw(canvas, maxNodes, numLayers, xPosition - width/(numLayers));
    }


}
