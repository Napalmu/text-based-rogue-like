package game.view;
import game.controller.GameController;


 class DrawMovingStructure extends DrawCommand{

    //visiona mahdollistaa ehkä useampiosaisen kokonaisuuden siirtäminen yhtenäisenä, 
    //siksi vähän kummallinen/keskeneräinen rakenne vielä
    DrawCommand drawCommand;
     DrawMovingStructure(int x, int y, DrawCommand drawCommand) {
        super(x, y);
        this.drawCommand = new DrawCommand(drawCommand.GetX() + this.GetX(), drawCommand.GetY() +  this.GetY(), drawCommand.GetContent());
        
    }

    @Override
    void Activate(){
        GameController.view.setContent(this.drawCommand);
    }

     void setContent(String... content){
        drawCommand.setContent(content);
    }
    
     void setXY(int x, int y){
        SetX(x);
        SetY(y);
        drawCommand.SetX(this.GetX());
        drawCommand.SetY(this.GetY());
        GameController.view.refresh();
    }
}
