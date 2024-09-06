package com.unife.project.util;

import javafx.stage.Stage;

public class WindowUtil {
    
    //imposta anche la posizione della finestra al centro dello schermo
    public static void setWindowSize(Stage stage) {
        if (stage != null) {
            stage.setWidth(1080);
            stage.setHeight(720);
            stage.centerOnScreen();
        }
    }
}
