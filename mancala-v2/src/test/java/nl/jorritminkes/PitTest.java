package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {
    private Pit firstPit;

    @BeforeEach
    void setUp() {
        firstPit = new Pit();
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

        assertEquals(5, firstPit.getOwner().getMancala().getSeeds());
    }

    @Test
    void PlayerOneCanFindItsMancala() {
        Player playerOne = firstPit.getOwner();

        assertEquals(firstPit.getHoleAt(7), playerOne.getMancala());
    }

    @Test
    void PlayerTwoCanFindItsMancala() {
        Player playerTwo = firstPit.getOwner().getOpponent();

        assertEquals(firstPit.getHoleAt(14), playerTwo.getMancala());
    }
}