package gonzalez_salzwedelda;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingHelp {

    private static final double RADIUS = 40;

    /**
     * Draw a node centered on the given location.
     * @param canvas canvas on which to draw this.
     * @param x center of the node
     * @param y center of the node
     */
    public static void drawNode(Canvas canvas, double x, double y){
        canvas.getGraphicsContext2D().strokeOval(x-RADIUS,y-RADIUS,2*RADIUS,2*RADIUS);
    }

    /**
     * Draw edge between two nodes.
     *
     * @param canvas
     * @param x1 center of first node
     * @param y1
     * @param x2 center of second node
     * @param y2
     */
    public static void drawEdge(Canvas canvas, double x1, double y1, double x2, double y2) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        Point2D p1 = new Point2D(x1, y1);
        Point2D p2 = new Point2D(x2, y2);
        Point2D direction = p2.subtract(p1).normalize();
        Point2D radius = direction.multiply(RADIUS);
        Point2D start = p1.add(radius);
        Point2D end = p2.subtract(radius);
        context.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
