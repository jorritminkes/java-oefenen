package nl.jorritminkes;

public class ComputerPlayer implements Player {
    private static final int[] ORDER = {3, 2, 4, 1, 5, 0, 6};
    private static final int INF = 1_000_000;
    private final int maxDepth;

    public ComputerPlayer(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public int chooseMove(Board board) {
        int bestCol = -1;
        int bestScore = -INF;
        for (int col : ORDER) {
            if (!board.canPlay(col)) continue;
            Board next = board.copy();
            next.makeMove(col);
            int score = -negamax(next, maxDepth - 1, -INF, INF);
            if (score > bestScore) {
                bestScore = score;
                bestCol = col;
            }
        }
        return bestCol;
    }

    private int negamax(Board board, int depth, int alpha, int beta) {
        if (board.isGameOver()) {
            return board.hasWinner() ? -(10_000 + depth) : 0;
        }
        if (depth == 0) return evaluate(board);

        int best = -INF;
        for (int col : ORDER) {
            if (!board.canPlay(col)) continue;
            Board next = board.copy();
            next.makeMove(col);
            best = Math.max(best, -negamax(next, depth - 1, -beta, -alpha));
            alpha = Math.max(alpha, best);
            if (alpha >= beta) break;
        }
        return best;
    }

    private int evaluate(Board board) {
        int me = board.getPlayerToMove();
        int score = 0;

        for (int row = 0; row < 6; row++) {
            int cell = board.getCell(3, row);
            if (cell == me) score += 3;
            else if (cell != 0) score -= 3;
        }

        int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                for (int[] d : directions) {
                    int endCol = col + 3 * d[0];
                    int endRow = row + 3 * d[1];
                    if (endCol < 0 || endCol >= 7 || endRow < 0 || endRow >= 6) continue;

                    int mine = 0;
                    int theirs = 0;
                    for (int i = 0; i < 4; i++) {
                        int cell = board.getCell(col + i * d[0], row + i * d[1]);
                        if (cell == me) mine++;
                        else if (cell != 0) theirs++;
                    }

                    if (theirs == 0) {
                        if (mine == 3) score += 5;
                        else if (mine == 2) score += 2;
                    } else if (mine == 0) {
                        if (theirs == 3) score -= 5;
                        else if (theirs == 2) score -= 2;
                    }
                }
            }
        }
        return score;
    }
}