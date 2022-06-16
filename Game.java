package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private static final Scanner input = new Scanner(System.in);

    public static void runApp() {
        int mines = askUserForAmountOfMines();
        if (mines == -1) return;
        MineField field = new MineField(9, mines);

        field.displayField();
    }

    public static int askUserForAmountOfMines() {
        System.out.println("How many mines do you want on this field?");
        int mines = -1;
        try {
            mines = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid number entered.");
        }
        return mines;
    }
}
