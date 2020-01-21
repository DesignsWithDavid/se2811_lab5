/*
 * Course:     SE 2811
 * Term:       Winter 2019-20
 * Assignment: Lab 4: Decorators
 * Author: Dr. Yoder and _______
 * Date:
 */
package gonzalez_salzwedelda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gonzalez_salzwedelda.DrawingHelp.drawEdge;
import static gonzalez_salzwedelda.DrawingHelp.drawNode;

/**
 * The controller for the main window.
 *
 * Also manages the networks.
 */
public class NetworkCanvasController {
    @FXML
    private Canvas canvas;

    private Map<String, Layer> networks = new HashMap<>();

    @FXML
    private void showNetwork(ActionEvent actionEvent) {
        ToggleButton source = (ToggleButton)actionEvent.getSource();
        String id = source.getId();
        System.out.println("id = " + id);
        // Clear Canvas: https://stackoverflow.com/q/27203671/1048186
        GraphicsContext context = canvas.getGraphicsContext2D();
        System.out.println("canvas.getWidth() = " + canvas.getWidth());
        System.out.println("canvas.getHeight() = " + canvas.getHeight());
        context.setLineWidth(3);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(!networks.containsKey(id)) {
            System.out.println("Warning: Unknown network id:"+id);
        } else {
            Layer network = networks.get(id);
            network.draw(canvas);
        }
    }

    @FXML
    private void initialize() {
        networks.put("alexLike",createAlexNet());
        networks.put("inceptionLike",createInception());
    }

    /**
     * As client code, use the decorator classes to construct the inception-like network,
     * as described in the lab.
     * @return network The network created.
     */
    private Layer createInception() {
        Layer identity = new IdentityLayer(3);
        Layer layer1 = new ConvolutionalLayerDecorator(identity);
        Layer layer2 = new ConvolutionalLayerDecorator(layer1);
        return new ConvolutionalLayerDecorator(layer2);
    }

    /**
     * As client code, use the decorator classes to construct the AlexNet-like network,
     * as described in the lab.
     * @return network The network created.
     */
    private Layer createAlexNet() {
        Layer identity = new IdentityLayer(4);
        Layer layer1 = new ConvolutionalLayerDecorator(identity);
        Layer layer2 = new ConvolutionalLayerDecorator(layer1);
        Layer layer3 = new FullyConnectedLayerDecorator(layer2, 4);
        return new FullyConnectedLayerDecorator(layer3, 3);
    }





}
