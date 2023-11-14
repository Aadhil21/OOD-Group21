package com.sacms.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main = null;

    public ScreenController(Scene main) {
        ScreenController.main = main;
    }

    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}
