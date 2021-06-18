package Constants;

public class Config {
    public interface BEGINNER_CONFIG {
        int SIDE = 9;
        int MINES = 10;
        int MOVES = (SIDE * SIDE) - MINES;
    }

    public interface INTERMEDIATE_CONFIG {
        int SIDE = 16;
        int MINES = 40;
        int MOVES = (SIDE * SIDE) - MINES;
    }

    public interface EXPERT_CONFIG {
        int SIDE = 25;
        int MINES = 99;
        int MOVES = (SIDE * SIDE) - MINES;
    }
}
