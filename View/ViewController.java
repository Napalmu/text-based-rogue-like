package View;

public class ViewController {
    private Terminal t = new Terminal(80,24);

    public ViewController(){}

    public void refresh(){
        t.redraw();
    }

    public void setContent(DrawCommand content){
        t.addContent(content);
    }

    public void clearContent(DrawCommand content){
        t.removeContent(content);
    }

    public void shutDown(){
        t.dispose();
    }
}
