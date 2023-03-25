package View;

public class ViewController {
    private Terminal t = new Terminal(80,24);

    public ViewController(){}

    public void setContent(GUIContent content){
        t.addContent(content);
    }

    public void clearContent(GUIContent content){
        t.removeContent(content);
    }

    public void shutDown(){
        t.dispose();
    }
}
