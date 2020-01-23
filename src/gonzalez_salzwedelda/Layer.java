/*
 * Course:     SE 2811
 * Term:       Winter 2019-20
 * Assignment: Lab 4: Decorators
 * Author: David Gonzalez
 * Date: 01/18/20
 */
package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

/**
 * This interface provides the structure for all node layers
 */
public interface Layer {
    /**
     * Returns the output size of the layer
     * @return output size
     */
    public int outputSize();

    /**
     * Returns the input size of the wrapped layer
     * @return input size of the wrapped layer
     */
    public int inputSize();

    /**
     * Extrapolates the required variables to call the other draw method
     * @param canvas the canvas to be drawn on
     */
    public void draw(Canvas canvas);

    /**
     * Draw nodes and their connections of a single layer based on the input parameters
     * @param canvas the canvas to be drawn on
     * @param maxNodes the number of nodes in the largest layer of the network
     * @param numLayers the number of layers in the network
     * @param xPosition the current x-position where the layer of nodes is to be drawn
     */
    public void draw(Canvas canvas, int maxNodes, int numLayers, double xPosition);

    /**
     * Calculates the number of layers of the network
     * @return the number of layers
     */
    public int numLayers();

    /**
     * Calculates the number of nodes in the biggest layer
     * @return the number of nodes in the biggest layer
     */
    public int maxNodes();

}
