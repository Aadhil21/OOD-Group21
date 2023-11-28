package com.sacms.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class ScreenController {
    private final HashMap<String, URL> screenMap = new HashMap<>(100);
    private Stage stage = null;
    private static ScreenController instance = null;

    public static ScreenController getInstance() {
        if (instance == null) {
            instance = new ScreenController();
        }

        return instance;
    }

    private ScreenController() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addScreen(String name, URL resource){
        screenMap.put(name, resource);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        if (stage == null) {
            throw new RuntimeException("Stage not set!");
        }

        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(screenMap.get(name))));
            stage.setScene(scene);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Program crashed! Cannot open window.").showAndWait();
            throw new RuntimeException(e); // kills the program
        }
    }

    public void activate(String name, boolean isMaximized) {
        activate(name);
        stage.setMaximized(isMaximized);
    }
}
