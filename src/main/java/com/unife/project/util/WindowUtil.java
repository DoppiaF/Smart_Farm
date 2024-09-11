package com.unife.project.util;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowUtil {
    
    //imposta anche la posizione della finestra al centro dello schermo
    public static void setWindowSize(Stage stage) {
        if (stage != null) {
            stage.setWidth(1920);
            stage.setHeight(1080);
            stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
            stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Press F11 to exit fullscreen");
            stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F11"));
            
        }
    }
}
