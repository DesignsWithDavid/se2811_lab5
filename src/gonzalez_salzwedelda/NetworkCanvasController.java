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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

import java.util.HashMap;
import java.util.Map;

import static gonzalez_salzwedelda.NodeDraw.*;


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
        // Clear Canvas: https://stackoverflow.com/q/27203671/1048186
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setLineWidth(3);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(!networks.containsKey(id)) {
            System.out.println("Warning: Unknown network id:"+id);
        } else {
            Layer network = networks.get(id);
            setDrawingCharacteristics(canvas.getWidth(), canvas.getHeight(), network.numLayers(), network.maxNodes());
            network.draw(canvas);
        }
    }

    @FXML
    private void initialize() {
        networks.put("alexLike",createAlexNet());
        networks.put("inceptionLike",createInception());
        networks.put("surprise", createSurprise());
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

    /**
     * As client code, use the decorator classes to construct the AlexNet-like network,
     * as described in the lab.
     * @return network The network created.
     */
    private Layer createSurprise() {
        Layer identity = new IdentityLayer(2);
        Layer layer1 = new FullyConnectedLayerDecorator(identity, 4);
        return new ConvolutionalLayerDecorator(layer1);
    }





}
