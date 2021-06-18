import Constants.Config;
import Constants.Difficulty;
import Utils.Minesweeper;

import java.util.Random;

public class MinesweeperController {

    Minesweeper minesweeper;

    public static void main(String[] args) {
        MinesweeperController minesweeperController = new MinesweeperController();
        minesweeperController.minesweeper = new Minesweeper(Difficulty.BEGINNER);

        minesweeperController.runBeginner();
        minesweeperController.runIntermediate();
        minesweeperController.runExpert();
    }

    private void runBeginner() {
        this.minesweeper = new Minesweeper(Difficulty.BEGINNER);
        this.playGameToWin(Config.BEGINNER_CONFIG.SIDE);
    }

    private void runIntermediate() {
        this.minesweeper = new Minesweeper(Difficulty.INTERMEDIATE);
        this.playGameToWin(Config.INTERMEDIATE_CONFIG.SIDE);
    }

    private void runExpert() {
        this.minesweeper = new Minesweeper(Difficulty.EXPERT);
        this.playGameToWin(Config.EXPERT_CONFIG.SIDE);
    }

    private void playGameToWin(int side) {
        boolean gameOver = false;
        Random random = new Random();

        while (!gameOver) {
            int position = random.nextInt(side * side);
            int row = position / side;
            int column = position % side;
            if (!this.minesweeper.grid.isMine(row, column)) {
                gameOver = this.minesweeper.makeMove(row, column);
            }
        }
    }
}
