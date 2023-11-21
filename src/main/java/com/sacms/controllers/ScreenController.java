package com.sacms.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class ScreenController {
    private static final HashMap<String, URL> screenMap = new HashMap<>();
    private static Stage main = null;

    public ScreenController(Stage main) {
        ScreenController.main = main;
    }

    public void addScreen(String name, URL resource){
        screenMap.put(name, resource);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name){
        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(screenMap.get(name))));
            main.setScene(scene);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Program crashed! Cannot open window.").showAndWait();
            throw new RuntimeException(e); // kills the program
        }
    }
}
