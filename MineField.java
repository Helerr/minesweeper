package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MineField {
    final int SIZE;
    final char UNKNOWN = '.';
    final char MINE = 'X';
    final char MARKED = '*';
    final char EMPTY = '/';

    Cell[][] cells;

    List<Cell> minesArray = new ArrayList<>();
    Stack<Cell> stack = new Stack<>();

    public MineField(int size, int mines) {
        this.SIZE = size;
        cells = new Cell[SIZE][SIZE];
        int minesAmount = 0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].row = i;
                cells[i][j].col = j;
            }
        }

        while (minesAmount < mines) {
            Random random = new Random();
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (!cells[row][col].isMine) {
                cells[row][col].isMine = true;
                minesArray.add(cells[row][col]);
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
        System.out.println(" │123456789│\n—│—————————│");
        for (int i = 0; i < cells.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j].isOpened) {
                    if (cells[i][j].isMine) {
                        System.out.print(MINE);
                    } else {
                        int numberOfMine = checkNumberOfMinesAround(i, j);
                        if (numberOfMine == 0) {
                            System.out.print(EMPTY);
                        } else {
                            System.out.print(numberOfMine);
                        }
                    }
                } else if (cells[i][j].isMarked) {
                    System.out.print(MARKED);
                } else {
                    System.out.print(UNKNOWN);
                }
            }
            System.out.println("|");
        }
        System.out.println("—│—————————│");
    }

    public boolean isAllMineMarked() {
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells.length; j++) {
                if (cell[j].isMine && !cell[j].isMarked) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkAllAround(int row, int col) {
        int upShift = 1;
        int downShift = 1;
        int leftShift = 1;
        int rightShift = 1;

        if (row == 0) {
            upShift = 0;
        }
        if (row == cells.length - 1) {
            downShift = 0;
        }
        if (col == 0) {
            leftShift = 0;
        }
        if (col == cells.length - 1) {
            rightShift = 0;
        }
        for (int i = row - upShift; i <= row + downShift; i++) {
            for (int j = col - leftShift; j <= col + rightShift; j++) {
                if (!cells[i][j].isOpened) {
                    if (checkNumberOfMinesAround(i, j) == 0) {
                        cells[i][j].isOpened = true;
                        stack.push(cells[i][j]);

                    } else if (checkNumberOfMinesAround(i, j) > 0) {
                        cells[i][j].isOpened = true;
                    }
                }
            }
        }
    }

    public void openArea(int row, int col) {
        if (checkNumberOfMinesAround(row, col) > 0) {
            cells[row][col].isOpened = true;
        } else {
            cells[row][col].isOpened = true;
            checkAllAround(row, col);

            while (!stack.empty()) {
                Cell nextCell = stack.pop();
                checkAllAround(nextCell.row, nextCell.col);
            }
        }
    }

    public void setAllBombOpened() {
        for (Cell each : minesArray) {
            each.isOpened = true;
        }
    }

    public int markCellAsMine(Cell cell) {
        if (!cell.isOpened) {
            if (!cell.isMarked) {
                cell.isMarked = true;
                return 1;
            } else {
                cell.isMarked = false;
                return -1;
            }
        } else {
            System.out.println("There is a number here!");
            return 0;
        }
    }

    public void checkFirstTurn(int row, int col) {
        if (cells[row][col].isMine) {
            for (int i = 0; i < (SIZE * SIZE); i++) {
                int findingRow = i / cells.length;
                int findingCol = i % cells.length;
                if (findingRow != row || findingCol != col) {
                    if (!cells[findingRow][findingCol].isMine) {
                        cells[findingRow][findingCol].isMine = true;
                        cells[row][col].isMine = false;
                        minesArray.remove(cells[row][col]);
                        minesArray.add(cells[findingRow][findingCol]);
                        break;
                    }
                }
            }
        }
    }
}
