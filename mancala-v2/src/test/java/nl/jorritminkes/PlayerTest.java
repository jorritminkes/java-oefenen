package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player playerOne;

    @BeforeEach
    void setUp() {
        playerOne = new Player();
    }


    @Test
    void playerOneExists() {
        assertInstanceOf(Player.class, playerOne);
    }

    @Test
    void PlayerHasOpponent() {
        assertNotNull(playerOne.getOpponent());
    }

    @Test
    void switchTurnWorks() {
        boolean turnPlayer = playerOne.isToMove();

        playerOne.switchMove();

        assertNotEquals(turnPlayer, playerOne.isToMove());
    }

    @Test
    void switchTurnOpponentWorks() {
        boolean turnOpponent = playerOne.getOpponent().isToMove();

        playerOne.getOpponent().switchMove();

        assertNotEquals(turnOpponent, playerOne.getOpponent().isToMove());
    }

    @Test
    void newPlayerIsToMove() {
        assertTrue(playerOne.isToMove());
    }

    @Test
    void opponentOfPlayerIsNotToMove() {
        assertFalse(playerOne.getOpponent().isToMove());
    }

    @Test
    void newPlayerHasOpponent() {
        assertNotNull(playerOne.getOpponent());
    }

    @Test
    void opponentOfOpponentIsPlayer() {
        assertSame(playerOne, playerOne.getOpponent().getOpponent());
    }

    @Test
    void playerCreatedWithOneIsToMove() {
        Player player = new Player(1);

        assertTrue(player.isToMove());
    }

    @Test
    void playerCreatedWithTwoIsNotToMove() {
        Player player = new Player(2);

        assertFalse(player.isToMove());
    }

    @Test
    void opponentOfPlayerCreatedWithOneIsNotToMove() {
        Player player = new Player(1);

        assertFalse(player.getOpponent().isToMove());
    }

    @Test
    void opponentOfPlayerCreatedWithTwoIsToMove() {
        Player player = new Player(2);

        assertTrue(player.getOpponent().isToMove());
    }

    @Test
    void switchMoveAlsoSwitchesOpponent() {
        boolean turnOpponent = playerOne.getOpponent().isToMove();

        playerOne.switchMove();

        assertNotEquals(turnOpponent, playerOne.getOpponent().isToMove());
    }

    @Test
    void switchMoveOnOpponentAlsoSwitchesPlayer() {
        boolean turnPlayer = playerOne.isToMove();

        playerOne.getOpponent().switchMove();

        assertNotEquals(turnPlayer, playerOne.isToMove());
    }

    @Test
    void switchMoveLeavesExactlyOnePlayerToMove() {
        playerOne.switchMove();

        assertNotEquals(playerOne.isToMove(), playerOne.getOpponent().isToMove());
    }

    @Test
    void switchMoveTwiceRestoresTurn() {
        boolean turnPlayer = playerOne.isToMove();

        playerOne.switchMove();
        playerOne.switchMove();

        assertEquals(turnPlayer, playerOne.isToMove());
    }

    @Test
    void newPlayerHasNoMancala() {
        Player player = new Player();

        assertNull(player.getMancala());
    }

    @Test
    void setMancalaStoresMancala() {
        Mancala mancala = new Mancala(new Pit(), new Player(), 14, 0);

        playerOne.setMancala(mancala);

        assertSame(mancala, playerOne.getMancala());
    }
}