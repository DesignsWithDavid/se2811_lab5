package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

import static gonzalez_salzwedelda.DrawingHelp.drawEdge;
import static gonzalez_salzwedelda.DrawingHelp.drawNode;

public class IdentityLayer implements Layer{
    private int outputSize;
    private int inputSize = 0;

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
        double height = canvas.getHeight();
        double offset = 120;
        double spacing = (height-offset*2)/(inputSize);


        drawNode(canvas, offset, spacing);

        for (int i = 1; i <= inputSize; i++){
            drawNode(canvas, offset, (i+1)*spacing);
            drawEdge(canvas, offset, i*spacing, offset, (i+1)*spacing);
        }

    }

    @Override
    public int numLayers() {
        return 1;
    }
}
