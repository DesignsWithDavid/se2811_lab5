/*
 * Course:     SE 2811
 * Term:       Winter 2019-20
 * Assignment: Lab 4: Decorators
 * Author: Dr. Yoder and David Gonzalez
 * Date: 01/18/20
 */
package gonzalez_salzwedelda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

import java.util.HashMap;
import java.util.Map;


/**
 * This class manages all of the interactions with the UI
 */
public class NetworkCanvasController {
    @FXML
    private Canvas canvas;

    private Map<String, Layer> networks = new HashMap<>();

    /**
     * Responds to the change of toggle-button selection in the UI by printing the corresponding network to the canvas
     * @param actionEvent the event of the toggle-button that was pressed
     */
    @FXML
    private void showNetwork(ActionEvent actionEvent) {
        ToggleButton source = (ToggleButton)actionEvent.getSource();
        String id = source.getId();
        canvas.setHeight(canvas.getScene().getHeight());
        canvas.setWidth(canvas.getScene().getWidth());
        // Clear Canvas: https://stackoverflow.com/q/27203671/1048186
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setLineWidth(3);
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(!networks.containsKey(id)) {
            System.out.println("Warning: Unknown network id:"+id);
        } else {
            Layer network = networks.get(id);
            network.draw(canvas);
        }
    }

    /**
     * Creates the different networks and adds them to the hash map
     */
    @FXML
    private void initialize() {
        networks.put("alexLike",createAlexNet());
        networks.put("inceptionLike",createInception());
        networks.put("surprise", createSurprise());
    }

    /**
     * Creates a network that mimics the Inception network
     * @return network the Inception network
     */
    private Layer createInception() {
        Layer identity = new IdentityLayer(3);
        Layer layer1 = new ConvolutionalLayerDecorator(identity);
        Layer layer2 = new ConvolutionalLayerDecorator(layer1);
        return new ConvolutionalLayerDecorator(layer2);
    }

    /**
     * Creates a network that mimics the AlexNet network
     * @return network the AlexNet network
     */
    private Layer createAlexNet() {
        Layer identity = new IdentityLayer(4);
        Layer layer1 = new ConvolutionalLayerDecorator(identity);
        Layer layer2 = new ConvolutionalLayerDecorator(layer1);
        Layer layer3 = new FullyConnectedLayerDecorator(layer2, 4);
        return new FullyConnectedLayerDecorator(layer3, 3);
    }

    /**
     * Creates a random network that looks like a snowflake
     * @return network the snowflake network
     */
    private Layer createSurprise() {
        Layer identity = new IdentityLayer(3);
        Layer layer1 = new FullyConnectedLayerDecorator(identity, 6);
        Layer layer2 = new FullyConnectedLayerDecorator(layer1, 12);
        Layer layer3 = new FullyConnectedLayerDecorator(layer2, 6);
        return new FullyConnectedLayerDecorator(layer3, 3);
    }





}
