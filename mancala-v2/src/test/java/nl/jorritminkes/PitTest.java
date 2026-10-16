package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {
    private Pit firstPit;
    private Player playerOne;
    private Player opponent;

    @BeforeEach
    void setUp() {
        firstPit = new Pit();
        playerOne = firstPit.getOwner();
        opponent = playerOne.getOpponent();
    }

    public void playShortestGame() {
        ((Pit) firstPit.getHoleAt(3)).move();
        ((Pit) firstPit.getHoleAt(6)).move();
        ((Pit) firstPit.getHoleAt(9)).move();
        ((Pit) firstPit.getHoleAt(10)).move();
        ((Pit) firstPit.getHoleAt(1)).move();
        ((Pit) firstPit.getHoleAt(11)).move();
        ((Pit) firstPit.getHoleAt(1)).move();
        ((Pit) firstPit.getHoleAt(12)).move();
        ((Pit) firstPit.getHoleAt(1)).move();
        ((Pit) firstPit.getHoleAt(13)).move();
    }

    @Test
    void firstPitGetsCreated() {
        assertNotNull(firstPit);
    }

    @Test
    void firstPitHasNumberOne() {
        assertEquals(1, firstPit.getHoleNumber());
    }

    @Test
    void firstPitHasClassPit() {
        assertInstanceOf(Pit.class, firstPit);
    }

    @Test
    void secondPitKnowsFirstPit() {
        assertEquals(firstPit, firstPit.getNextHole().getFirstHole());
    }

    @Test
    void firstMancalaKnowsFirstPit() {
        assertEquals(firstPit, firstPit.getNextHole().getNextHole().getNextHole().getNextHole().getNextHole().getNextHole().getFirstHole());
    }

    @Test
    void pitFifteenIsPitOne() {
        assertEquals(firstPit, firstPit.getHoleAt(15));
    }

    @Test
    void pitFifteenIsPitOneHoleNumber() {
        Hole pitFifteen = firstPit.getHoleAt(15);

        assertEquals(firstPit.getHoleNumber(), pitFifteen.getHoleNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
    void firstPitCanFindEachPit(int holeNumber) {
        Hole holeAtNumber = firstPit.getHoleAt(holeNumber);

        assertEquals(holeNumber, holeAtNumber.getHoleNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11 ,12 ,13})
    void pitHasFourSeeds(int pitNumber) {
        assertEquals(4, firstPit.getHoleAt(pitNumber).getSeeds());
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 14})
    void mancalaHasZeroSeeds(int mancalaNumber) {
        assertEquals(0, firstPit.getHoleAt(mancalaNumber).getSeeds());
    }

    @Test
    void moveEmptiesPit() {
        firstPit.move();

        assertEquals(0, firstPit.getSeeds());
    }

    @Test
    void moveAddsSeedToNextPit() {
        Hole secondPit = firstPit.getNextHole();

        firstPit.move();

        assertEquals(5, secondPit.getSeeds());
    }

    @Test
    void moveAddsToOwnMancala() {
        Hole pit6 = firstPit.getHoleAt(6);
        Hole mancala1 = firstPit.getHoleAt(7);

        ((Pit) pit6).move();

        assertEquals(1, mancala1.getSeeds());
    }

    @Test
    void moveDoesNotAddToOtherMancala() {
        Hole mancala2 = firstPit.getHoleAt(14);

        firstPit.setSeeds(15);
        firstPit.move();

        assertEquals(0, mancala2.getSeeds());
    }

    @Test
    void pitWithFourteenSeedsAddsToItself() {
        firstPit.setSeeds(15);
        firstPit.move();

        assertEquals(1, firstPit.getSeeds());
    }

    @Test
    void landOnEmptyPitStealsOwn() {
        Pit targetPit = (Pit) firstPit.getHoleAt(5);

        targetPit.setSeeds(0);
        firstPit.move();

        assertEquals(0, targetPit.getSeeds());
    }

    @Test
    void landOnEmptyPitStealsOthers() {
        Pit targetPit = (Pit) firstPit.getHoleAt(5);
        Pit targetPitNeighbor = targetPit.getNeighborPit();

        targetPit.setSeeds(0);
        firstPit.move();

        assertEquals(0, targetPitNeighbor.getSeeds());
    }

    @Test
    void landOnEmptyPitFillsMancala() {
        Pit targetPit = (Pit) firstPit.getHoleAt(5);

        targetPit.setSeeds(0);
        firstPit.move();

        assertEquals(5, playerOne.getMancala().getSeeds());
    }

    @Test
    void PlayerOneCanFindItsMancala() {
        assertEquals(firstPit.getHoleAt(7), playerOne.getMancala());
    }

    @Test
    void PlayerTwoCanFindItsMancala() {
        assertEquals(firstPit.getHoleAt(14), opponent.getMancala());
    }

    @Test
    void moveWithoutHavingTurn() {
        firstPit.move();

        assertThrows(IllegalStateException.class, () -> {
            firstPit.move();
        }, "Calling move when it is not your turn");
    }

    @Test
    void turnSwitchesToAllowOtherPlayerToMove() {
        firstPit.move();

        assertDoesNotThrow(() -> {
                ((Pit) firstPit.getHoleAt(8)).move();
        }, "Turn switches correctly if player one plays first pit first");
    }

    @Test
    void seedsForPlayerCountsInitialSeedsForPlayerOne() {
        int seedsForPlayerOne = firstPit.seedsForPlayer(playerOne);

        assertEquals(24, seedsForPlayerOne);
    }

    @Test
    void totalSeedsAfterMoveCorrect() {
        ((Pit) firstPit.getHoleAt(6)).move();
        int seedsForPlayerOne = firstPit.seedsForPlayer(playerOne);

        assertEquals(20, seedsForPlayerOne);
    }

    @Test
    void gameNowOverWhenNonEmptyRow() {
        assertFalse(firstPit.isGameOver());
    }

    @Test
    void gameIsOverWhenEmptyRow() {
        for (int holeNumber = 1; holeNumber < 7; holeNumber++) {
            firstPit.getHoleAt(holeNumber).setSeeds(0);
        }

        assertTrue(firstPit.isGameOver());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11 ,12 ,13})
    void shortestGameGivesEmptyBoard(int pitNumber) {
        playShortestGame();
        assertEquals(0, firstPit.getHoleAt(pitNumber).getSeeds());
    }

    @Test
    void mancalasHoldAllSeedsAfterShortestGame() {
        playShortestGame();
        int mancalaOneSeeds = playerOne.getMancala().getSeeds();
        int mancalaTwoSeeds = opponent.getMancala().getSeeds();
        int totalSeeds = mancalaOneSeeds + mancalaTwoSeeds;

        assertEquals(48, totalSeeds);
    }

    @Test
    void landingOnOwnMancalaGrantsAnotherTurn() {
        ((Pit) firstPit.getHoleAt(3)).move();

        assertDoesNotThrow(() -> {
            firstPit.move();
        }, "Landing on own mancala should allow the same player to move again");
    }

    @Test
    void movingEmptyPitThrowsException() {
        Pit emptyPit = (Pit) firstPit.getHoleAt(1);
        emptyPit.setSeeds(0);

        assertThrows(IllegalStateException.class, emptyPit::move,
                "Moving a pit with zero seeds should throw an exception");
    }

    @Test
    void getHoleAtInvalidIndexThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            firstPit.getHoleAt(0);
        }, "Fetching a hole with index 0 or lower should throw an exception");
    }

    @Test
    void seedsForPlayerCountsInitialSeedsForPlayerTwo() {
        int seedsForPlayerTwo = firstPit.seedsForPlayer(opponent);

        assertEquals(24, seedsForPlayerTwo);
    }

    @Test
    void gameIsOverWhenPlayerTwoRowEmpty() {
        for (int holeNumber = 8; holeNumber < 14; holeNumber++) {
            firstPit.getHoleAt(holeNumber).setSeeds(0);
        }

        assertTrue(firstPit.isGameOver(), "Game should be over when player two's row is empty");
    }

    @Test
    void getWinnerReturnsNullWhenGameNotOver() {
        assertNull(firstPit.getWinner(), "Winner should be null while game is ongoing");
    }

    @Test
    void getWinnerReturnsCorrectWinnerWhenGameIsOver() {
        playShortestGame();

        assertEquals(playerOne, firstPit.getWinner(), "Player one wins shortest game");
    }

    @Test
    void getWinnerReturnsNullOnTie() {
        playerOne.getMancala().setSeeds(24);
        opponent.getMancala().setSeeds(24);

        for (int holeNumber = 1; holeNumber < 15; holeNumber++) {
            firstPit.getHoleAt(holeNumber).setSeeds(0);
        }

        assertNull(firstPit.getWinner(), "Winner is null in a tie");
    }
}