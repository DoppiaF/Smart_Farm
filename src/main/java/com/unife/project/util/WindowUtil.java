package com.unife.project.util;

import javafx.stage.Stage;

public class WindowUtil {
    
    public static void setWindowSize(Stage stage) {
        if (stage != null) {
            stage.setWidth(800);
            stage.setHeight(600);
        }
    }
}
