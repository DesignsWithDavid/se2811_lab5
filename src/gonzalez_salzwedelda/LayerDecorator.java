package gonzalez_salzwedelda;

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
}
