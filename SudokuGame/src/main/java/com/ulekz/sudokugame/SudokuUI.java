package com.ulekz.sudokugame;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.security.Key;

public class SudokuUI {

    private static final int SIZE = 9;
    private final TextField[][] cells = new TextField[SIZE][SIZE];
    private final SudokuBoard board = new SudokuBoard();

    public Parent createContent() {
        board.generateInitialBoard();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cell = new TextField();
                cell.setPrefSize(60, 60);
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-font-size: 18");
                int r = row;
                int c = col;

                int value = board.getCell(row, col);
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setStyle("-fx-background-color: #d0d0d0; -fx-font-size: 18; -fx-font-weight: bold");
                } else {
                    cell.addEventFilter(KeyEvent.KEY_TYPED, e -> {
                        String input = e.getCharacter();
                        if (!input.matches("[1-9]")) {
                            e.consume();
                            return;
                        }

                        int number = Integer.parseInt(input);
                        if (board.isValidMove(r, c, number)) {
                            cell.setStyle("-fx-background-color: #b2f2bb; -fx-font-size: 18;");
                            board.setCell(r, c, number);
                        } else {
                            cell.setStyle("-fx-background-color: #ffa8a8; -fx-font-size: 18;");
                            e.consume();
                        }
                    });
                }

                cells[row][col] = cell;
                grid.add(cell, col, row);
            }
        }

        //boton de comprobar
        Button checkButton = new Button("Comprobar");
        checkButton.setStyle("-fx-font-size: 16;");
        checkButton.setOnAction(e -> {
            boolean result = board.isCompleteAndValid();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado");

            if (result) {
                alert.setHeaderText("¡Felicidades!");
                alert.setContentText("Has completado correctamente el Sudoku.");
            } else {
                alert.setHeaderText(" Aún no está correcto.");
                alert.setContentText("Revisa si hay errores o espacios vacíos");
            }

            alert.showAndWait();
        });

        VBox layout = new VBox(10, grid, checkButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }
}
