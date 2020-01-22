package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;


public class FullyConnectedLayerDecorator extends LayerDecorator {
    public FullyConnectedLayerDecorator(Layer decoratedLayer, int outputSize) {
        super(decoratedLayer, outputSize);
    }


    @Override
    public void draw(Canvas canvas, int maxNodes, int numLayers, double xPosition) {
        double height = canvas.getHeight();
        double width = canvas.getWidth();
        double radius = height/(maxNodes*5);
        double inputInterval = height / (getDecoratedLayer().outputSize() + 2);
        double outputInterval = height / (outputSize() + 2);

        for (int i = 1; i <= outputSize(); i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*outputInterval)- radius,2* radius,2* radius);
        }

        for (int i = 1; i <= getDecoratedLayer().outputSize(); i++){
            for (int j = 1; j <= outputSize(); j++){
                drawLine(canvas, xPosition-width/(numLayers), (i*inputInterval), xPosition, (j*outputInterval), radius);
            }
        }

        super.getDecoratedLayer().draw(canvas, maxNodes, numLayers, xPosition - width/(numLayers));
    }



}
