/*
 * Course:     SE 2811
 * Term:       Winter 2019-20
 * Assignment: Lab 4: Decorators
 * Author: David Gonzalez
 * Date: 01/18/20
 */
package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;


public class IdentityLayer implements Layer{
    private int outputSize;
    private int inputSize;

    /**
     * Constructor
     * @param size the number of nodes in the identity layer
     */
    public IdentityLayer(int size){
        outputSize = size;
        inputSize = size;
    }

    /**
     * Returns the output size of the layer
     * @return output size
     */
    @Override
    public int outputSize() {
        return outputSize;
    }

    /**
     * Returns the input size of the wrapped layer
     * @return input size of the wrapped layer
     */
    @Override
    public int inputSize() {
        return inputSize;
    }

    /**
     * Extrapolates the required variables to call the other draw method
     * @param canvas the canvas to be drawn on
     */
    @Override
    public void draw(Canvas canvas) {
        double xPosition = canvas.getWidth() - canvas.getWidth()/(numLayers() + 2);
        draw(canvas, maxNodes(), numLayers(), xPosition);
    }

    /**
     * Draw nodes and their connections of a single layer based on the input parameters
     * @param canvas the canvas to be drawn on
     * @param maxNodes the number of nodes in the largest layer of the network
     * @param numLayers the number of layers in the network
     * @param xPosition the current x-position where the layer of nodes is to be drawn
     */
    @Override
    public void draw(Canvas canvas, int maxNodes, int numLayers, double xPosition) {
        double height = canvas.getHeight();
        double radius = height/(maxNodes*5);
        double interval = height / (outputSize() + 2);

        for (int i = 1; i <= outputSize; i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*interval)- radius,2* radius,2* radius);
        }

    }

    /**
     * Calculates the number of layers of the network
     * @return the number of layers
     */
    @Override
    public int numLayers() {
        return 1;
    }

    /**
     * Calculates the number of nodes in the biggest layer
     * @return the number of nodes in the biggest layer
     */
    @Override
    public int maxNodes() {
        return outputSize;
    }
}
