package gonzalez_salzwedelda;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class LayerDecorator implements Layer{
    private Layer decoratedLayer;
    private int outputSize;
    private int inputSize;

    public LayerDecorator(Layer decoratedLayer, int outputSize){
        this.outputSize = outputSize;
        this.inputSize = decoratedLayer.outputSize();
        this.decoratedLayer = decoratedLayer;
    }

    public int outputSize(){
        return outputSize;
    }

    public int inputSize(){
        return decoratedLayer.inputSize();
    }

    public int numLayers(){
        return decoratedLayer.numLayers()+1;
    }

    public Layer getDecoratedLayer(){
        return decoratedLayer;
    }

    public int maxNodes(){
        return Math.max(decoratedLayer.maxNodes(), outputSize);
    }

    public void draw(Canvas canvas) {
        double xPosition = canvas.getWidth() - canvas.getWidth()/(numLayers() + 2);
        draw(canvas, maxNodes(), numLayers(), xPosition);
    }

    public void drawNodes(Canvas canvas, double xPosition, double interval, double radius){
        for (int i = 1; i <= outputSize(); i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*interval)- radius,2* radius,2* radius);
        }
    }

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
