package Utils;

import Constants.*;

public class Minesweeper {

    public Grid grid;
    int movesDone = 0;

    boolean firstMove = true;
    boolean gameOver = false;

    Difficulty difficulty;

    public void restart() {
       initialize();
    }

    private void initialize() throws IllegalArgumentException {
        switch (this.difficulty) {
            case BEGINNER -> this.grid = new Grid(Config.BEGINNER_CONFIG.SIDE, Config.BEGINNER_CONFIG.MINES, Config.BEGINNER_CONFIG.MOVES);
            case INTERMEDIATE -> this.grid = new Grid(Config.INTERMEDIATE_CONFIG.SIDE, Config.INTERMEDIATE_CONFIG.MINES,Config.INTERMEDIATE_CONFIG.MOVES);
            case EXPERT -> this.grid = new Grid(Config.EXPERT_CONFIG.SIDE, Config.EXPERT_CONFIG.MINES, Config.EXPERT_CONFIG.MOVES);
            default -> throw new IllegalArgumentException(Errors.InvalidDifficulty);
        }

        this.firstMove = true;
        this.gameOver = false;
    }

    public Minesweeper(Difficulty difficulty) {
        this.difficulty = difficulty;

        initialize();
    }

    public boolean makeMove(int x, int y) {
        if (!this.gameOver) {
            togglePosition(x, y);

            grid.print(this.gameOver);
        } else {
            System.out.println(Errors.InvalidMoveGameOver);
        }
        return this.gameOver;
    }

    private void togglePosition(int x, int y) {
        boolean isMine = this.grid.isMine(x,y);

        if (isMine) {
            if (firstMove) {
                firstMove = false;
                replaceMine(x, y);
            } else {
                System.out.println(Errors.MineMoveGameOver);

                this.gameOver = true;
                return;
            }
        }

        turnAdjacent(x, y);
    }

    private void turnAdjacent(int x, int y) {

        if (this.grid.steps[x][y] != '-') {
            return;
        }

        int mineCount = countMines(x, y);
        this.movesDone++;

        this.grid.steps[x][y] = (char) (mineCount + '0');

        if (this.grid.movesComplete(this.movesDone)){
            this.gameOver = true;
            System.out.println(Trace.GameWon);

            return;
        }

        if (mineCount == 0) {
            for(int i=0; i< AdjacentCell.size; i++) {

                int row = x + AdjacentCell.Row[i];
                int column = y + AdjacentCell.Column[i];

                if (this.grid.isValid(row, column) && !this.grid.isMine(row, column)) {
                    turnAdjacent(row, column);
                }
            }
        }
    }

    private void replaceMine(int x, int y) {
        for(int iterateRow = 0;iterateRow < this.grid.side; iterateRow++) {
            for (int iterateColumn = 0; iterateColumn < this.grid.side; iterateColumn++) {
                if(this.grid.steps[iterateRow][iterateColumn] == '-') {
                    this.grid.steps[iterateRow][iterateColumn] = '*';
                    this.grid.steps[x][y] = '-';
                    return;
                }
            }
        }
    }

    private int countMines(int x, int y) {
        int mineCount = 0;
        for(int i=0; i < AdjacentCell.size; i++) {
            int row = x + AdjacentCell.Row[i];
            int column = y + AdjacentCell.Column[i];

            if (this.grid.isValid(row, column) && this.grid.isMine(row, column)) {
                mineCount++;
            }
        }

        return mineCount;
    }
}
