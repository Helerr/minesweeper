package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private static final Scanner input = new Scanner(System.in);
    MineField field;
    Result result = new Result(this);
    int markedMines = 0;
    boolean isFirstTurn = true;
    Scanner scanner = new Scanner(System.in);


    public  void runApp() {
        int mines = askUserForAmountOfMines();
        if (mines == -1) return;
        int mineFieldSize = 9;
        field = new MineField(mineFieldSize, mines);

        field.displayField();

        boolean isExploded = false;

        while (result.isNotGameOver()) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int col = Integer.parseInt(scanner.next()) - 1;
            int row = Integer.parseInt(scanner.next()) - 1;
            boolean commandMarkFree = scanner.next().equals("free");

            if (commandMarkFree) {
                if (isFirstTurn) {
                    field.checkFirstTurn(row, col);
                    isFirstTurn = false;
                }
                isExploded = result.checkExplodes(field.cells[row][col]);
                if (isExploded) {
                    field.displayField();
                    break;
                } else {
                    field.openArea(row, col);
                }
            } else {
                markedMines += field.markCellAsMine(field.cells[row][col]);
            }
            field.displayField();
        }
        if (isExploded) {
            System.out.println("You stepped on a mine and failed!");
        } else {
            System.out.println("Congratulations! You found all mines!");
        }
    }

    public int askUserForAmountOfMines() {
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
