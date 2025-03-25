package com.ulekz.sudokugame;

public class SudokuBoard {

    private final int[][] board = new int[9][9];
    private final boolean[][] locked = new boolean[9][9]; // Para saber que celdas estan bloqueadas

public boolean isValidMove(int row, int col, int number) {
    if (locked[row][col]) return false;

    for (int i = 0; i < 9; i++) {
        if (board[row][i] == number || board[i][col] == number) return false;
    }

    int boxRow = (row / 3) * 3;
    int boxCol = (col / 9) * 3;

    for (int r = boxRow; r < boxRow + 3; r++) {
        for (int c = boxCol; c < boxCol + 3; c++) {
            if (board[r][c] == number) return false;
        }
    }

    return true;
}

public void setCell(int row, int col, int number) {
    board[row][col] = number;
}

public int getCell(int row, int col) {
    return board[row][col];
}

public boolean isLocked(int row, int col) {
    return locked[row][col];
}

    public void generateInitialBoard() {
        int[][] preset = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9},
        };

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int val = preset[row][col];
                board[row][col] = val;
                if (val != 0) {
                    locked[row][col] = true;
                }
            }
        }
    }

public boolean isCompleteAndValid() {
    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            int number = board[row][col];
            if (number == 0) return false;

            // Temporalmente borramos la celda actual para validar sin contarla dos veces
            board[row][col] = 0;
            boolean valid = isValidMove(row, col, number);
            board[row][col] = number;

            if (!valid) return false;
        }
    }
    return true;
}
}
