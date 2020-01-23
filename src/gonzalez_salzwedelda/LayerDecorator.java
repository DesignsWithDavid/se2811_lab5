/*
 * Course:     SE 2811
 * Term:       Winter 2019-20
 * Assignment: Lab 4: Decorators
 * Author: David Gonzalez
 * Date: 01/18/20
 */
package gonzalez_salzwedelda;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class provides the template for all decorator layers
 */
public abstract class LayerDecorator implements Layer{
    private Layer decoratedLayer;
    private int outputSize;
    private int inputSize;

    /**
     * Constructor
     * @param decoratedLayer the layer to be decorated
     * @param outputSize the number of nodes this new layer will have
     */
    public LayerDecorator(Layer decoratedLayer, int outputSize){
        this.outputSize = outputSize;
        this.inputSize = decoratedLayer.outputSize();
        this.decoratedLayer = decoratedLayer;
    }

    /**
     * Returns the output size of the layer
     * @return output size
     */
    public int outputSize(){
        return outputSize;
    }

    /**
     * Returns the input size of the wrapped layer
     * @return input size of the wrapped layer
     */
    public int inputSize(){
        return decoratedLayer.inputSize();
    }

    /**
     * Calculates the number of layers of the network
     * @return the number of layers
     */
    public int numLayers(){
        return decoratedLayer.numLayers()+1;
    }

    /**
     * Returns the layer being decorated
     * @return the layer being decorated
     */
    public Layer getDecoratedLayer(){
        return decoratedLayer;
    }

    /**
     * Calculates the number of nodes in the biggest layer
     * @return the number of nodes in the biggest layer
     */
    public int maxNodes(){
        return Math.max(decoratedLayer.maxNodes(), outputSize);
    }

    /**
     * Extrapolates the required variables to call the other draw method
     * @param canvas the canvas to be drawn on
     */
    public void draw(Canvas canvas) {
        double xPosition = canvas.getWidth() - canvas.getWidth()/(numLayers() + 2);
        draw(canvas, maxNodes(), numLayers(), xPosition);
    }

    /**
     * Draws the nodes of the layer
     * @param canvas the canvas to draw on
     * @param xPosition the position of the layer of nodes
     * @param interval the distance between nodes
     * @param radius the radius of each node
     */
    public void drawNodes(Canvas canvas, double xPosition, double interval, double radius){
        for (int i = 1; i <= outputSize(); i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*interval)- radius,2* radius,2* radius);
        }
    }

    /**
     * Draws the line between two nodes
     * @param canvas the canvas to draw on
     * @param x1 the x-coordinate of the first node
     * @param y1 the y-coordinate of the first node
     * @param x2 the x-coordinate of the second node
     * @param y2 the y-coordinate of the second node
     * @param nodeRadius
     */
    public void drawLine(Canvas canvas, double x1, double y1, double x2, double y2, double nodeRadius){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Point2D p1 = new Point2D(x1, y1);
        Point2D p2 = new Point2D(x2, y2);
        Point2D direction = p2.subtract(p1).normalize();
        Point2D radius = direction.multiply(nodeRadius);
        Point2D start = p1.add(radius);
        Point2D end = p2.subtract(radius);
        context.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
