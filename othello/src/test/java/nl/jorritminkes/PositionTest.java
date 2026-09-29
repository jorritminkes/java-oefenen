package nl.jorritminkes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @ParameterizedTest(name = "Positie ({0},{1}) vergelijken met ({2},{3}) verwacht gelijk={4}")
    @CsvSource({
            "0, 0, 0, 0, true",
            "7, 7, 7, 7, true",
            "3, 4, 3, 4, true",
            "3, 4, 4, 3, false",
            "0, 0, 0, 1, false",
            "1, 0, 0, 0, false",
            "-1, -1, -1, -1, true" // puur theoretische test voor de class
    })
    @DisplayName("Uitgebreide parameter sweep voor equals methode")
    void testEqualsParameterized(int r1, int c1, int r2, int c2, boolean expected) {
        Position p1 = new Position(r1, c1);
        Position p2 = new Position(r2, c2);

        assertEquals(expected, p1.equals(p2));
    }

    @Test
    @DisplayName("Test equals tegen null en andere object types")
    void testEqualsEdgeCases() {
        Position p = new Position(3, 4);
        assertNotEquals(null, p);
        assertNotEquals("Een String", p);
    }
}