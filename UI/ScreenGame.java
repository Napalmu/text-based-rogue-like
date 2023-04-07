package UI;

import Controller.GameController;
import View.DrawCommand;

class ScreenGame extends Screen{
    
    private String[] screen = new String[]{
        "╔═════════════════════════════════════════════════════════╤════════════════════╗",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "║                                                         │                    ║",
        "╟─────────────────────────────────────────────────────────┴────────────────────╢",
        "║                                                                              ║",
        "║                                                                              ║",
        "║                                                                              ║",
        "║                                                                              ║",
        "║                                                                              ║",
        "║                                                                              ║",
        "║                                                                              ║",
        "╚══════════════════════════════════════════════════════════════════════════════╝",
    };
    public ScreenGame(){
        GameController.view.setContent(new DrawCommand(0, 0, screen));
    }
}
