package com.unife.project.util;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowUtil {
    
    //imposta anche la posizione della finestra al centro dello schermo
    public static void setWindow(Stage stage, Scene scene, String title) {
        if (stage != null) {
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setMaximized(true);
            stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
            stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);
            stage.centerOnScreen();
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Press F11 to exit fullscreen");
            //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F11"));
            
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F11) {
                    stage.setFullScreen(!stage.isFullScreen());
                }
            });
        }
    }
}
