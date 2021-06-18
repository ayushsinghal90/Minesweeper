package Utils;

import java.util.Random;

public class Grid {
    int side;
    int mines;
    int movesLeft;

    char[][] steps;

    public Grid(int side, int mines, int movesLeft) {
        this.side = side;
        this.mines = mines;
        this.movesLeft = movesLeft;

        this.steps = new char[this.side][this.side];

        initialize();
    }

    public void initialize() {
        populate();
        populateMines();
    }

    private void populateMines() {
        int minesAdded = 0;
        Random random = new Random();

        while(minesAdded < mines) {
            int position = random.nextInt(this.side * this.side);
            int row = position / this.side;
            int column = position % this.side;

            if(this.steps[row][column] != '*') {
                this.steps[row][column] = '*';
                minesAdded++;
            }
        }
    }

    private void populate() {
        for(int iterateRow = 0;iterateRow < this.side; iterateRow++) {
            for (int iterateColumn = 0; iterateColumn < this.side; iterateColumn++) {
                this.steps[iterateRow][iterateColumn] = '-';
            }
        }
    }

    public void print(boolean showMine) {
        for(int iterateRow = 0;iterateRow < this.side; iterateRow++) {
            for (int iterateColumn = 0; iterateColumn < this.side; iterateColumn++) {
                char position = this.steps[iterateRow][iterateColumn];
                if (position == '*' && !showMine) {
                    position = '-';
                }
                System.out.print(position + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public boolean isMine(int x, int y) {
        return this.steps[x][y] == '*';
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < this.side && y >= 0 && y < this.side;
    }

    public boolean movesComplete(int movesDone) {
        return this.movesLeft == movesDone;
    }
}
