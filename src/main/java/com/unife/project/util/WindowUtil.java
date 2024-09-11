package com.unife.project.util;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowUtil {
    
    //imposta anche la posizione della finestra al centro dello schermo
    public static void setWindowSize(Stage stage, Scene scene) {
        if (stage != null) {            
            stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
            stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);
            stage.centerOnScreen();
            stage.setFullScreen(false); 
            stage.setWidth(1080);
            stage.setHeight(720);

            
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F11) {
                    stage.setFullScreen(!stage.isFullScreen());
                }
            });
        }
    }

    //imposta anche la posizione della finestra al centro dello schermo
    public static void setWindow(Stage stage, Scene scene, String title) {
        if (stage != null) {
            stage.setScene(scene);
            stage.setTitle(title);
            //stage.setMaximized(true);
            stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
            stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);
            stage.centerOnScreen();
            stage.setFullScreen(true);
            stage.setFullScreen(false);            
            stage.setWidth(1080);
            stage.setHeight(720);
            //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F11"));
            
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F11) {
                    stage.setFullScreen(!stage.isFullScreen());
                }
            });
        }
    }


}
