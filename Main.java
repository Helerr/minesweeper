package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        runApp();
    }


    public static void runApp() {
        MineField field = new MineField(9);
        field.populateField();
        int mines = askUserForAmountOfMines();
        if (mines == -1) return;
        field.addMines(mines);
        field.displayField();
    }

    public static int askUserForAmountOfMines () {
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
