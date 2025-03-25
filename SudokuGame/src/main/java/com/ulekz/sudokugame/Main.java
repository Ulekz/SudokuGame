package com.ulekz.sudokugame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        SudokuUI ui = new SudokuUI();

        Scene scene = new Scene(ui.createContent(), 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("¡¡Sudoku Game!!");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
