package nl.jorritminkes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class DiskTest {

    @ParameterizedTest(name = "De tegenstander van {0} moet {1} zijn")
    @CsvSource({
            "BLACK, WHITE",
            "WHITE, BLACK",
            "EMPTY, EMPTY"
    })
    @DisplayName("Test getOpponent voor alle kleuren")
    void testGetOpponent(Disk input, Disk expectedOutput) {
        assertEquals(expectedOutput, input.getOpponent());
    }

    @ParameterizedTest
    @EnumSource(Disk.class)
    @DisplayName("Geen enkele getOpponent mag null retourneren")
    void getOpponentNeverReturnsNull(Disk disk) {
        assertNotNull(disk.getOpponent());
    }
}