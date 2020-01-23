/*
 * Course:     SE 2811
 * Term:       Winter 2019-20
 * Assignment: Lab 4: Decorators
 * Author: David Gonzalez
 * Date: 01/18/20
 */
package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;


public class ConvolutionalLayerDecorator extends LayerDecorator{
    /**
     * Constructor
     * @param decoratedLayer the layer being decorated
     */
    public ConvolutionalLayerDecorator(Layer decoratedLayer){
        super(decoratedLayer, decoratedLayer.outputSize());
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
        double width = canvas.getWidth();
        double radius = height/(maxNodes*5);
        double interval = height / (outputSize() + 2);

        drawNodes(canvas, xPosition, interval, radius);

        for (int i = 1; i <= outputSize(); i++){
            drawLine(canvas,xPosition,(i*interval), xPosition-width/(numLayers),(i*interval), radius);
        }

        super.getDecoratedLayer().draw(canvas, maxNodes, numLayers, xPosition - width/(numLayers));
    }


}
