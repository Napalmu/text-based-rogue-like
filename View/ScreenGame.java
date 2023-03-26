package View;

import Controller.GameController;

public class ScreenGame extends Screen{
    
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
