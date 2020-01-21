package gonzalez_salzwedelda;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class NodeDraw {

    private static double radius;
    private static int numLayers;
    private static double xPosition;
    private static double height;
    private static double width;

    public static void setDrawingCharacteristics(double w, double h, int num, int max){
        width = w;
        height = h;
        numLayers = num;
        xPosition = width/(numLayers + 2);
        radius = height/(max*5);
    }

    /**
     * Draw a node centered on the given location.
     * @param canvas canvas on which to draw this.
     * @param numNodes the number of nodes to draw
     */
    public static void drawNodes(Canvas canvas, int numNodes){
        double interval = height / (numNodes + 1);

        for (int i = 1; i <= numNodes; i++){
            canvas.getGraphicsContext2D().strokeOval(xPosition- radius,(i*interval)- radius,2* radius,2* radius);
        }

        xPosition += canvas.getWidth()/(numLayers+1);
    }

    /**
     * Draw edge between two nodes.
     *
     * @param canvas
     * @param numInput number of input nodes
     * @param numOutput number of output nodes
     */
    public static void webConnectNodes(Canvas canvas, int numInput, int numOutput) {
        double height = canvas.getHeight();
        double inputInterval = height / (numInput + 1);
        double outputInterval = height / (numOutput + 1);

        for (int i = 1; i <= numInput; i++){
            for (int j = 1; j <= numOutput; j++){
                drawLine(canvas, xPosition-canvas.getWidth()/(numLayers+1), (i*inputInterval), xPosition,(j*outputInterval));
            }
        }
    }

    /**
     * Draw edge between two nodes.
     *
     * @param canvas
     * @param numNodes number of output nodes
     */
    public static void directConnectNodes(Canvas canvas, int numNodes) {
        double height = canvas.getHeight();
        double interval = height / (numNodes + 1);

        for (int i = 1; i <= numNodes; i++){
            drawLine(canvas,xPosition-canvas.getWidth()/(numLayers+1),(i*interval), xPosition,(i*interval));
        }
    }

    private static void drawLine(Canvas canvas, double x1, double y1, double x2, double y2){
        GraphicsContext context = canvas.getGraphicsContext2D();
        Point2D p1 = new Point2D(x1, y1);
        Point2D p2 = new Point2D(x2, y2);
        Point2D direction = p2.subtract(p1).normalize();
        Point2D radius = direction.multiply(NodeDraw.radius);
        Point2D start = p1.add(radius);
        Point2D end = p2.subtract(radius);
        context.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
