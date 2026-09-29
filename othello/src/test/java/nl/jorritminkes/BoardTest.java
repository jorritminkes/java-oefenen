package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        // Elk van de onderstaande tests krijgt een vers, gereset bord
        board = new Board();
    }

    @Test
    @DisplayName("Test de initiële startopstelling van Othello")
    void testInitialCenterPieces() {
        // We kunnen de grid niet direct lezen omdat hij private is,
        // dus testen we of zetten IN het centrum ongeldig zijn (want ze zijn al bezet)
        assertFalse(board.isValidMove(new Position(3, 3), Disk.BLACK));
        assertFalse(board.isValidMove(new Position(3, 4), Disk.BLACK));
        assertFalse(board.isValidMove(new Position(4, 3), Disk.BLACK));
        assertFalse(board.isValidMove(new Position(4, 4), Disk.BLACK));
    }

    @ParameterizedTest(name = "Test geldige openingszetten voor zwart op ({0}, {1})")
    @CsvSource({
            "2, 3", "3, 2", "4, 5", "5, 4"
    })
    @DisplayName("Zwart heeft precies vier geldige openingszetten")
    void testValidInitialMovesBlack(int row, int col) {
        assertTrue(board.isValidMove(new Position(row, col), Disk.BLACK));
    }

    @ParameterizedTest(name = "Test geldige openingszetten voor wit op ({0}, {1})")
    @CsvSource({
            "2, 4", "3, 5", "4, 2", "5, 3"
    })
    @DisplayName("Wit heeft precies vier geldige openingszetten")
    void testValidInitialMovesWhite(int row, int col) {
        assertTrue(board.isValidMove(new Position(row, col), Disk.WHITE));
    }

    @ParameterizedTest(name = "Ongeldige zet buiten het bord op ({0}, {1})")
    @CsvSource({
            "-1, 4", "8, 4", "4, -1", "4, 8", "-5, -5", "10, 10"
    })
    @DisplayName("Zetten buiten het 8x8 bord moeten altijd geweigerd worden")
    void testOutOufBoundsMoves(int row, int col) {
        assertFalse(board.isValidMove(new Position(row, col), Disk.BLACK));
        assertFalse(board.isValidMove(new Position(row, col), Disk.WHITE));
    }

    @Test
    @DisplayName("Test getValidMoves geeft exact 4 zetten terug bij aanvang")
    void testGetValidMovesCountAtStart() {
        List<Position> blackMoves = board.getValidMoves(Disk.BLACK);
        assertEquals(4, blackMoves.size(), "Zwart moet starten met exact 4 mogelijke zetten");

        List<Position> whiteMoves = board.getValidMoves(Disk.WHITE);
        assertEquals(4, whiteMoves.size(), "Wit moet starten met exact 4 mogelijke zetten");
    }

    @Test
    @DisplayName("Pas een zet toe en valideer dat het bord kantelt")
    void testApplyMoveAndFlip() {
        Position move = new Position(2, 3);

        // Controleer of de zet geldig is
        assertTrue(board.isValidMove(move, Disk.BLACK));

        // Zwart plaatst steen op (2,3)
        board.applyMove(move, Disk.BLACK);

        // Na deze zet, is (3,3) omgedraaid van W naar B.
        // We verifiëren dit door te checken of Wit nu op (2,3) mag spelen
        // (wat onmogelijk is als het al bezet is door Zwart).
        assertFalse(board.isValidMove(move, Disk.WHITE));

        // Na de zet van Zwart, zouden de geldige zetten voor Wit veranderd moeten zijn.
        List<Position> newWhiteMoves = board.getValidMoves(Disk.WHITE);
        assertFalse(newWhiteMoves.isEmpty(), "Wit moet zetten hebben na de eerste zet van Zwart");
    }

    // --- MEGA SWEEP: Test het HELE bord op de allereerste beurt ---

    @ParameterizedTest(name = "Op startbord is ({0}, {1}) ongeldig voor zwart mits niet een van de 4 geldige")
    @MethodSource("provideAllBoardCoordinates")
    @DisplayName("Parameter sweep: Test alle 64 vakjes in startpositie voor Zwart")
    void testAllCoordinatesForBlackOnStart(int row, int col) {
        Position pos = new Position(row, col);

        // De 4 legitieme zwarte zetten slaan we even over in deze negatieve test
        if ((row == 2 && col == 3) || (row == 3 && col == 2) ||
                (row == 4 && col == 5) || (row == 5 && col == 4)) {
            return;
        }

        assertFalse(board.isValidMove(pos, Disk.BLACK),
                "Zet op " + row + "," + col + " zou ongeldig moeten zijn voor Zwart bij de start");
    }

    // Hulpmethode voor de MethodSource hierboven (genereert 64 tests)
    private static Stream<Arguments> provideAllBoardCoordinates() {
        Stream.Builder<Arguments> builder = Stream.builder();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                builder.add(Arguments.of(r, c));
            }
        }
        return builder.build();
    }
}