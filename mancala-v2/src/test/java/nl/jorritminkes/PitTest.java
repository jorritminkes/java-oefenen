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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
    void firstPitCanFindEachPit(int holeNumber) {
        Hole holeAtNumber = firstPit.getHoleAt(holeNumber);

        assertEquals(holeNumber, holeAtNumber.getHoleNumber());
    }
}