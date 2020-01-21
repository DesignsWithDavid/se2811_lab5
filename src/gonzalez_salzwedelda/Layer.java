package gonzalez_salzwedelda;

import javafx.scene.canvas.Canvas;

public interface Layer {
    public int outputSize();

    public int inputSize();

    public void draw(Canvas canvas);

    public int numLayers();

}
