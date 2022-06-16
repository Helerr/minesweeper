package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class MineField {
    private String[][] field;

    public MineField(int dimensions) {
        this.field = new String[dimensions][dimensions];
    }

    public void populateField() {
        for (String[] strings : field) {
            Arrays.fill(strings, ".");
        }
    }

    public void displayField() {
        for (String[] strings : field) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    public void addMines(int minesAmount) {
        Random random = new Random();
        int row = random.nextInt(field.length);
        int col = random.nextInt(field.length);
        while (minesAmount > 0) {
            if (currentPositionAlreadyHaveAMine(row, col)) {
                while (currentPositionAlreadyHaveAMine(row, col)) {
                    row = random.nextInt(field.length);
                    col = random.nextInt(field.length);
                }
            }
            field[row][col] = "X";
            row = random.nextInt(field.length);
            col = random.nextInt(field.length);

            minesAmount--;
        }
    }

    private boolean currentPositionAlreadyHaveAMine(int row, int col) {
        if ("X".equals(field[row][col])) {
            return true;
        }
        return false;
    }
}
