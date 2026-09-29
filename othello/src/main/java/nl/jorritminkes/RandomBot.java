package nl.jorritminkes;

import java.util.List;
import java.util.Random;

public class RandomBot implements Player {

    public Position getMove(Board board, Disk color) {
        List<Position> validMoves = board.getValidMoves(color);

        if (validMoves.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(validMoves.size());

        return validMoves.get(randomIndex);
    }
}
