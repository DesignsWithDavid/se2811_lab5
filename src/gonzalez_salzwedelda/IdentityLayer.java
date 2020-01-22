package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;


public class IdentityLayer implements Layer{
    private int outputSize;
    private int inputSize;

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
        double xPosition = canvas.getWidth() - canvas.getWidth()/(numLayers() + 2);
        draw(canvas, maxNodes(), numLayers(), xPosition);
    }

    @Override
    public void draw(Canvas canvas, int maxNodes, int numLayers, double xPosition) {
        double height = canvas.getHeight();
        double radius = height/(maxNodes*5);
        double interval = height / (outputSize() + 2);

        for (int i = 1; i <= outputSize; i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*interval)- radius,2* radius,2* radius);
        }

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
