package nl.jorritminkes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TransferedTest {
    private Hole firstHole;

    @BeforeEach
    public void setUp() {
        firstHole = new Pit();
    }

    private Hole createBoardWithLayout(int[] layout) {
        assertEquals(Hole.TOTAL_HOLES, layout.length, "Layout must contain 14 holes");
        Hole first = new Pit();
        Hole hole = first;
        for (int seeds : layout) {
            hole.setSeeds(seeds);
            hole = hole.getNextHole();
        }
        return first;
    }

    public void playShortestGame() {
        ((Pit) firstHole.getHoleAt(3)).move();
        ((Pit) firstHole.getHoleAt(6)).move();
        ((Pit) firstHole.getHoleAt(9)).move();
        ((Pit) firstHole.getHoleAt(10)).move();
        ((Pit) firstHole.getHoleAt(1)).move();
        ((Pit) firstHole.getHoleAt(11)).move();
        ((Pit) firstHole.getHoleAt(1)).move();
        ((Pit) firstHole.getHoleAt(12)).move();
        ((Pit) firstHole.getHoleAt(1)).move();
        ((Pit) firstHole.getHoleAt(13)).move();
    }

    @Test
    public void TestPitExistsAfterInitialization() {
        assertNotNull(firstHole, "Pit should not be null");
    }

    @Test
    public void TestNewPitHasFourSeeds() {
        int seeds = firstHole.getSeeds();
        assertEquals(4, seeds);
    }

    @Test
    public void TestHoleFourteenExists() {
        Hole holeFourteen = firstHole.getHoleAt(14);
        assertNotNull(holeFourteen);
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "14,14", "15,1", "16,2"})
    public void TestHoleNumberMatchesPosition(int position, int expectedHoleNumber) {
        int holeNumber = firstHole.getHoleAt(position).getHoleNumber();
        assertEquals(expectedHoleNumber, holeNumber);
    }

    @ParameterizedTest
    @CsvSource({"1,2", "2,3", "14,1", "15,2"})
    public void TestNextHoleNumberMatches(int holeNumber, int expectedNextHoleNumber) {
        int nextHoleNumber = firstHole.getHoleAt(holeNumber).getNextHole().getHoleNumber();
        assertEquals(expectedNextHoleNumber, nextHoleNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,6,12,13})
    public void TestAllPitsHaveFourSeeds(int holeNumber) {
        int seeds = firstHole.getHoleAt(holeNumber).getSeeds();
        assertEquals(4, seeds);
    }

    @Test
    public void TestMoveEmptiesItsPit() {
        Hole hole5 = firstHole.getHoleAt(5);
        ((Pit) hole5).move();
        int seeds = hole5.getSeeds();
        assertEquals(0, seeds);
    }

    @Test
    public void TestMoveIsRejectedOnEmptyHole() {
        int[] testLayout = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole5 = firstHole.getHoleAt(5);
        assertThrows(IllegalStateException.class, () -> {
            ((Pit) hole5).move();
        });
    }

    @Test
    public void TestNextPitGetsOneSeedOnMove() {
        Hole hole2 = firstHole.getHoleAt(2);
        Hole hole3 = firstHole.getHoleAt(3);
        int seedsOnThreeBeforeMove = hole3.getSeeds();
        ((Pit) hole2).move();
        assertEquals(seedsOnThreeBeforeMove + 1, hole3.getSeeds());
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,5", "5,5", "6,4"})
    public void TestAllSeedsArePassedOnWhenMoveDoesNotReachMancala(int holeNumber, int seedsAfterMove) {
        Hole playedHole = firstHole.getHoleAt(1);
        Hole checkedHole = firstHole.getHoleAt(holeNumber);
        ((Pit) playedHole).move();
        int seedsInCheckedHole = checkedHole.getSeeds();
        assertEquals(seedsAfterMove, seedsInCheckedHole);
    }

    @Test
    public void TestHoleSevenIsMancala() {
        Hole holeSeven = firstHole.getHoleAt(7);
        assertInstanceOf(Mancala.class, holeSeven);
    }

    @Test
    public void TestHoleFourteenIsMancala() {
        Hole holeFourteen = firstHole.getHoleAt(14);
        assertInstanceOf(Mancala.class, holeFourteen);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void TestPitsOneToSixBelongToPlayerOne(int holeNumber) {
        Player playerOne = firstHole.getOwner();
        Hole hole = firstHole.getHoleAt(holeNumber);
        assertSame(playerOne, hole.getOwner());
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,10,11,12,13})
    public void TestPitsEightToThirteenBelongToPlayerTwo(int holeNumber) {
        Player playerTwo = firstHole.getOwner().getOpponent();
        Hole hole = firstHole.getHoleAt(holeNumber);
        assertSame(playerTwo, hole.getOwner());
    }

    @Test
    public void TestMancalaReceivesSeedFromOwnPlayer() {
        Hole hole6 = firstHole.getHoleAt(6);
        Hole mancala1 = firstHole.getHoleAt(7);
        ((Pit) hole6).move();
        assertEquals(1, mancala1.getSeeds());
    }

    @Test
    public void TestMancalaReceivesNoSeedFromOtherPlayer() {
        int[] testLayout = {4,4,4,4,4,8,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole mancala2 = firstHole.getHoleAt(14);
        ((Pit) hole6).move();
        assertEquals(0, mancala2.getSeeds());
    }

    @Test
    public void TestMoveSucceedsOnOwnPit() {
        Hole hole5 = firstHole.getHoleAt(5);
        assertDoesNotThrow(() -> {
            ((Pit) hole5).move();
        });
    }

    @Test
    public void TestMoveFailsOnOpponentPit() {
        Hole hole12 = firstHole.getHoleAt(12);
        assertThrows(IllegalStateException.class, () -> {
            ((Pit) hole12).move();
        });
    }

    @Test
    public void TestTurnChangesWhenEndingOnPit() {
        Hole hole1 = firstHole.getHoleAt(1);
        ((Pit) hole1).move();
        Player player1 = hole1.getOwner();
        assertFalse(player1.isToMove());
    }

    @Test
    public void TestTurnStaysWhenEndingInOwnMancala() {
        Hole hole3 = firstHole.getHoleAt(3);
        ((Pit) hole3).move();
        Player player1 = hole3.getOwner();
        assertTrue(player1.isToMove());
    }

    @Test
    public void TestLandingOnEmptyOwnPitAddsToMancala() {
        int[] testLayout = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole1 = firstHole.getHoleAt(1);
        Hole mancala1 = firstHole.getHoleAt(7);
        ((Pit) hole1).move();
        assertEquals(5, mancala1.getSeeds());
    }

    @Test
    public void TestLandingOnEmptyOwnPitEmptiesPit5() {
        int[] testLayout = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole1 = firstHole.getHoleAt(1);
        Hole hole5 = firstHole.getHoleAt(5);
        ((Pit) hole1).move();
        assertEquals(0, hole5.getSeeds());
    }

    @Test
    public void TestLandingOnEmptyOwnPitEmptiesPit9() {
        int[] testLayout = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole1 = firstHole.getHoleAt(1);
        Hole hole9 = firstHole.getHoleAt(9);
        ((Pit) hole1).move();
        assertEquals(0, hole9.getSeeds());
    }

    @Test
    public void TestLandingOnEmptyOpponentPitDoesNotAddToMancala() {
        int[] testLayout = {4,4,4,4,4,4,  0,  0,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole4 = firstHole.getHoleAt(4);
        Hole mancala1 = firstHole.getHoleAt(7);
        ((Pit) hole4).move();
        assertEquals(1, mancala1.getSeeds());
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,5", "3,5", "4,5", "5,0", "6,5", "7,8", "8,0", "9,0", "10,5", "11,5", "12,5", "13,5", "14,0"})
    public void TestSeedsAreDistributedCorrectlyAfterThreeMoves(int holeNumber, int expectedSeeds) {
        Hole firstMoveHole = firstHole.getHoleAt(5);
        Hole secondMoveHole = firstHole.getHoleAt(8);
        Hole thirdMoveHole = firstHole.getHoleAt(1);
        Hole checkHole = firstHole.getHoleAt(holeNumber);
        ((Pit) firstMoveHole).move();
        ((Pit) secondMoveHole).move();
        ((Pit) thirdMoveHole).move();
        assertEquals(expectedSeeds, checkHole.getSeeds(), "Wrong hole: " + holeNumber);
    }

    @ParameterizedTest
    @CsvSource({"1,4", "2,4", "3,4", "4,4", "5,0", "6,5", "7,1", "8,0", "9,6", "10,1", "11,6", "12,6", "13,6", "14,1"})
    public void TestTurnBelongsToCorrectPlayerAfterThreeMoves(int holeNumber, int expectedSeeds) {
        Hole firstMoveHole = firstHole.getHoleAt(5);
        Hole secondMoveHole = firstHole.getHoleAt(10);
        Hole thirdMoveHole = firstHole.getHoleAt(8);
        Hole checkHole = firstHole.getHoleAt(holeNumber);
        ((Pit) firstMoveHole).move();
        ((Pit) secondMoveHole).move();
        ((Pit) thirdMoveHole).move();
        assertEquals(expectedSeeds, checkHole.getSeeds(), "Wrong hole: " + holeNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,10,11,12,13})
    public void TestPlayerTwoFinishedAfterShortestGame(int holeNumber) {
        playShortestGame();
        assertEquals(0, firstHole.getHoleAt(holeNumber).getSeeds());
    }

    @Test
    public void TestGameNotOverYet() {
        Hole testHole = firstHole.getHoleAt(1);
        ((Pit) testHole).move();
        assertFalse(((Pit) testHole).isGameOver());
    }

    @Test
    public void TestGameIsOver() {
        playShortestGame();
        assertTrue(((Pit) firstHole).isGameOver());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,8,9,10,11,12,13})
    public void TestEveryPitIsEmptyAfterGameOver(int holeNumber) {
        Hole hole = firstHole.getHoleAt(holeNumber);
        playShortestGame();
        assertEquals(0, hole.getSeeds());
    }

    @Test
    public void TestMancalasHoldAllSeedsAfterGameOver() {
        Hole mancala1 = firstHole.getHoleAt(7);
        Hole mancala2 = firstHole.getHoleAt(14);
        playShortestGame();
        int totalSeeds = mancala1.getSeeds() + mancala2.getSeeds();
        assertEquals(48, totalSeeds);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,8,9,10,11,12,13})
    public void TestNoMovePossibleAfterGameOver(int holeNumber) {
        Hole hole = firstHole.getHoleAt(holeNumber);
        playShortestGame();
        assertThrows(IllegalStateException.class, () -> {
            ((Pit) hole).move();
        });
    }

    @Test
    public void TestMancalaOneHas41SeedsAfterGameOver() {
        Hole mancala = firstHole.getHoleAt(7);
        playShortestGame();
        assertEquals(41, mancala.getSeeds());
    }

    @Test
    public void TestMancalaTwoHas7SeedsAfterGameOver() {
        Hole mancala = firstHole.getHoleAt(14);
        playShortestGame();
        assertEquals(7, mancala.getSeeds());
    }

    @Test
    public void TestMoveOnMancalaDoesNotWork() {
        Hole mancala = firstHole.getHoleAt(7);
        assertThrows(ClassCastException.class, () -> {
            ((Pit) mancala).move();
        });
    }

    @Test
    public void TestOpponentMancalaIsSkipped() {
        int[] testLayout = {4,4,4,4,4,100,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole opponentMancala = firstHole.getHoleAt(14);
        ((Pit) hole6).move();
        assertEquals(0, opponentMancala.getSeeds());
    }

    @Test
    public void TestOwnMancalaIsNotSkipped() {
        int[] testLayout = {4,4,4,4,4,100,  0,  4,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole ownMancala = firstHole.getHoleAt(7);
        ((Pit) hole6).move();
        assertEquals(8, ownMancala.getSeeds());
    }

    @Test
    public void TestCaptureFailsWhenOppositePitIsEmpty() {
        int[] testLayout = {4,4,4,4,4,13,  0,  0,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole neighborHole = firstHole.getHoleAt(Hole.TOTAL_HOLES - 6);
        ((Pit) hole6).move();
        assertEquals(0, neighborHole.getSeeds());
    }

    @Test
    public void TestSeedDroppedInEmptyPitFailsCapture() {
        int[] testLayout = {4,4,4,4,4,13,  0,  0,4,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole hole6 = firstHole.getHoleAt(6);
        ((Pit) hole6).move();
        assertEquals(0, hole6.getSeeds());
    }

    @Test
    public void TestMancalaStaysTheSameAfterCaptureWithEmptyOppositeSide() {
        int[] testLayout = {4,4,4,4,0,4,  5,  4,0,4,4,4,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole playedHole = firstHole.getHoleAt(1);
        Hole ownMancala = firstHole.getHoleAt(7);

        ((Pit) playedHole).move();

        assertEquals(5, ownMancala.getSeeds());
    }

    @Test
    public void TestGameIsSweptCorrectlyToMancalaAtEndOfPlayer1() {
        int[] testLayout = {1,0,0,0,0,0,  0,  4,4,4,4,27,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole pit1 = firstHole.getHoleAt(1);
        Hole mancala1 = firstHole.getHoleAt(7);

        ((Pit) pit1).move();

        assertEquals(28, mancala1.getSeeds());
    }

    @Test
    public void TestGameIsSweptCorrectlyToMancalaAtEndOfPlayer2() {
        int[] testLayout = {1,0,0,0,0,0,  0,  4,4,4,4,27,4,  0};
        firstHole = createBoardWithLayout(testLayout);

        Hole pit1 = firstHole.getHoleAt(1);
        Hole mancala2 = firstHole.getHoleAt(14);

        ((Pit) pit1).move();

        assertEquals(20, mancala2.getSeeds());
    }

    // There is (not yet) a getWinner(): the winner is whoever has the most seeds in their mancala.
    @Test
    public void TestPlayerOneWinsTheShortestGame() {
        playShortestGame();

        Hole mancala1 = firstHole.getHoleAt(7);
        Hole mancala2 = firstHole.getHoleAt(14);

        assertTrue(mancala1.getSeeds() > mancala2.getSeeds());
    }

    @Test
    public void TestPlayerTwoWinsGivenGame() {
        int[] testLayout = {1,0,0,0,0,0,  0,  4,12,4,4,19,4,  0};
        firstHole = createBoardWithLayout(testLayout);
        Hole pit1 = firstHole.getHoleAt(1);
        Hole mancala1 = firstHole.getHoleAt(7);
        Hole mancala2 = firstHole.getHoleAt(14);

        ((Pit) pit1).move();

        assertTrue(mancala2.getSeeds() > mancala1.getSeeds());
    }

    @Test
    public void TestLandOnEmptyPitWithEmptyNeighborPitOwnCheck() {
        int[] testLayout = {1,0,0,0,0,0,  0,  47,0,0,0,0,0,  0};
        firstHole = createBoardWithLayout(testLayout);
        Hole pit1 = firstHole.getHoleAt(1);
        Hole pit2 = firstHole.getHoleAt(2);

        ((Pit) pit1).move();

        assertEquals(1, pit2.getSeeds());
    }

    @Test
    public void TestLandOnEmptyPitWithEmptyNeighborPitNeighborCheck() {
        int[] testLayout = {1,0,0,0,0,0,  0,  47,0,0,0,0,0,  0};
        firstHole = createBoardWithLayout(testLayout);
        Hole pit1 = firstHole.getHoleAt(1);
        Hole neighborOfPit2 = firstHole.getHoleAt(Hole.TOTAL_HOLES - 2);

        ((Pit) pit1).move();

        assertEquals(0, neighborOfPit2.getSeeds());
    }

}