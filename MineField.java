package minesweeper;

import java.util.Random;

public class MineField {
    final int SIZE;
    final char UNKNOWN = '.';
    final char MINE = 'X';

    Cell[][] cells;

    public MineField(int size, int mines) {
        this.SIZE = size;
        cells = new Cell[SIZE][SIZE];
        int minesAmount = 0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell();
            }
        }

        while (minesAmount < mines) {
            Random random = new Random();
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (!cells[row][col].isMine) {
                cells[row][col].isMine = true;
                minesAmount++;
            }
        }
    }

    public int checkNumberOfMinesAround(int row, int col) {
        int result = 0;

        if (cells[row][col].isMine) {
            return -1;
        }
        int up, down, left, right;
        up = down = left = right = 1;


        if (row == 0) up = 0;
        if (row == cells.length - 1) down = 0;
        if (col == 0) left = 0;
        if (col == cells.length - 1) right = 0;

        for (int i = row - up; i <= row + down; i++) {
            for (int j = col - left; j <= col + right; j++) {
                if (cells[i][j].isMine) {
                    result++;
                }
            }
        }
        return result;
    }


    public void displayField() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                int numberOfMine = checkNumberOfMinesAround(i, j);
                if (numberOfMine == -1) {
                    System.out.print(MINE);
                } else if (numberOfMine == 0) {
                    System.out.print(UNKNOWN);
                } else {
                    System.out.print(numberOfMine);
                }
            }
            System.out.println();
        }
    }
}
