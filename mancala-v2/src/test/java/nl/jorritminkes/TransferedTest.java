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

    private Hole maakBordMetOpstelling(int[] opstelling) {
        assertEquals(Hole.TOTAL_HOLES, opstelling.length, "Opstelling moet 14 holes bevatten");
        Hole eerste = new Pit();
        Hole hole = eerste;
        for (int seeds : opstelling) {
            hole.setSeeds(seeds);
            hole = hole.getNextHole();
        }
        return eerste;
    }

    public void speelKortsteSpel() {
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
    public void TestPitBestaatNaInitialisatie() {
        assertNotNull(firstHole, "Pit should not be null");
    }

    @Test
    public void TestNieuwePitHeeftVierSeeds() {
        int seeds = firstHole.getSeeds();
        assertEquals(4, seeds);
    }

    @Test
    public void TestHoleVeertienBestaat() {
        Hole holeVeertien = firstHole.getHoleAt(14);
        assertNotNull(holeVeertien);
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "14,14", "15,1", "16,2"})
    public void TestHoleNumberKloptMetPositie(int positie, int verwachtteHoleNumber) {
        int holeNumber = firstHole.getHoleAt(positie).getHoleNumber();
        assertEquals(verwachtteHoleNumber, holeNumber);
    }

    @ParameterizedTest
    @CsvSource({"1,2", "2,3", "14,1", "15,2"})
    public void TestHoleNumberVanVolgendeHoleMatcht(int holeNumber, int verwachtteVolgendeHoleNumber) {
        int volgendeHoleNumber = firstHole.getHoleAt(holeNumber).getNextHole().getHoleNumber();
        assertEquals(verwachtteVolgendeHoleNumber, volgendeHoleNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,6,12,13})
    public void TestHebbenAllePitsVierSeeds(int holeNumber) {
        int seeds = firstHole.getHoleAt(holeNumber).getSeeds();
        assertEquals(4, seeds);
    }

    @Test
    public void TestLeegtEenMoveZijnPit() {
        Hole hole5 = firstHole.getHoleAt(5);
        ((Pit) hole5).move();
        int seeds = hole5.getSeeds();
        assertEquals(0, seeds);
    }

    @Test
    public void TestMoveWordtOvergeslagenBijLegeHole() {
        int[] testOpstelling = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole5 = firstHole.getHoleAt(5);
        assertThrows(IllegalStateException.class, () -> {
            ((Pit) hole5).move();
        });
    }

    @Test
    public void TestVolgendePitKrijgtEenSeedBijMove() {
        Hole hole2 = firstHole.getHoleAt(2);
        Hole hole3 = firstHole.getHoleAt(3);
        int seedsOpDrieVoorMove = hole3.getSeeds();
        ((Pit) hole2).move();
        assertEquals(seedsOpDrieVoorMove + 1, hole3.getSeeds());
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,5", "5,5", "6,4"})
    public void TestAlleSeedsWordenDoorgegevenBijMoveZonderMancala(int holeNumber, int seedsNaMove) {
        Hole gespeeldeHole = firstHole.getHoleAt(1);
        Hole gecontroleerdeHole = firstHole.getHoleAt(holeNumber);
        ((Pit) gespeeldeHole).move();
        int seedsInGecontroleerdeHole = gecontroleerdeHole.getSeeds();
        assertEquals(seedsNaMove, seedsInGecontroleerdeHole);
    }

    @Test
    public void TestHoleZevenIsMancala() {
        Hole holeZeven = firstHole.getHoleAt(7);
        assertInstanceOf(Mancala.class, holeZeven);
    }

    @Test
    public void TestHoleVeertienIsMancala() {
        Hole holeVeertien = firstHole.getHoleAt(14);
        assertInstanceOf(Mancala.class, holeVeertien);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void TestPitsEenTotZesZijnVanPlayerEen(int holeNumber) {
        Player playerEen = firstHole.getOwner();
        Hole hole = firstHole.getHoleAt(holeNumber);
        assertSame(playerEen, hole.getOwner());
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,10,11,12,13})
    public void TestPitsAchtTotDertienZijnVanPlayerTwee(int holeNumber) {
        Player playerTwee = firstHole.getOwner().getOpponent();
        Hole hole = firstHole.getHoleAt(holeNumber);
        assertSame(playerTwee, hole.getOwner());
    }

    @Test
    public void TestMancalaKrijgtSeedVanEigenPlayer() {
        Hole hole6 = firstHole.getHoleAt(6);
        Hole mancala1 = firstHole.getHoleAt(7);
        ((Pit) hole6).move();
        assertEquals(1, mancala1.getSeeds());
    }

    @Test
    public void TestMancalaKrijgtGeenSeedVanAnderePlayer() {
        int[] testOpstelling = {4,4,4,4,4,8,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole mancala2 = firstHole.getHoleAt(14);
        ((Pit) hole6).move();
        assertEquals(0, mancala2.getSeeds());
    }

    @Test
    public void TestMoveLuktBijEigenPit() {
        Hole hole5 = firstHole.getHoleAt(5);
        assertDoesNotThrow(() -> {
            ((Pit) hole5).move();
        });
    }

    @Test
    public void TestMoveMisluktBijPitVanTegenstander() {
        Hole hole12 = firstHole.getHoleAt(12);
        assertThrows(IllegalStateException.class, () -> {
            ((Pit) hole12).move();
        });
    }

    @Test
    public void TestVerandertBeurtBijEindigenOpPit() {
        Hole hole1 = firstHole.getHoleAt(1);
        ((Pit) hole1).move();
        Player player1 = hole1.getOwner();
        assertFalse(player1.isToMove());
    }

    @Test
    public void TestBeurtBlijftGelijkBijEindigenInEigenMancala() {
        Hole hole3 = firstHole.getHoleAt(3);
        ((Pit) hole3).move();
        Player player1 = hole3.getOwner();
        assertTrue(player1.isToMove());
    }

    @Test
    public void TestLandenOpLegeEigenPitMancala() {
        int[] testOpstelling = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole1 = firstHole.getHoleAt(1);
        Hole mancala1 = firstHole.getHoleAt(7);
        ((Pit) hole1).move();
        assertEquals(5, mancala1.getSeeds());
    }

    @Test
    public void TestLandenOpLegeEigenPitPit5Leeg() {
        int[] testOpstelling = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole1 = firstHole.getHoleAt(1);
        Hole hole5 = firstHole.getHoleAt(5);
        ((Pit) hole1).move();
        assertEquals(0, hole5.getSeeds());
    }

    @Test
    public void TestLandenOpLegeEigenPitPit9Leeg() {
        int[] testOpstelling = {4,4,4,4,0,4,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole1 = firstHole.getHoleAt(1);
        Hole hole9 = firstHole.getHoleAt(9);
        ((Pit) hole1).move();
        assertEquals(0, hole9.getSeeds());
    }

    @Test
    public void TestLandenOpTegenstanderLeegVakVoegtNietToeAanMancala() {
        int[] testOpstelling = {4,4,4,4,4,4,  0,  0,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole4 = firstHole.getHoleAt(4);
        Hole mancala1 = firstHole.getHoleAt(7);
        ((Pit) hole4).move();
        assertEquals(1, mancala1.getSeeds());
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,5", "3,5", "4,5", "5,0", "6,5", "7,8", "8,0", "9,0", "10,5", "11,5", "12,5", "13,5", "14,0"})
    public void TestNaDrieMovesZijnSeedsCorrectVerdeeld(int holeNumber, int verwachtteSeeds) {
        Hole eersteMoveHole = firstHole.getHoleAt(5);
        Hole tweedeMoveHole = firstHole.getHoleAt(8);
        Hole derdeMoveHole = firstHole.getHoleAt(1);
        Hole controleHole = firstHole.getHoleAt(holeNumber);
        ((Pit) eersteMoveHole).move();
        ((Pit) tweedeMoveHole).move();
        ((Pit) derdeMoveHole).move();
        assertEquals(verwachtteSeeds, controleHole.getSeeds(), "Foutieve hole: " + holeNumber);
    }

    @ParameterizedTest
    @CsvSource({"1,4", "2,4", "3,4", "4,4", "5,0", "6,5", "7,1", "8,0", "9,6", "10,1", "11,6", "12,6", "13,6", "14,1"})
    public void TestNaDrieMovesIsBeurtBijDeJuistePlayer(int holeNumber, int verwachtteSeeds) {
        Hole eersteMoveHole = firstHole.getHoleAt(5);
        Hole tweedeMoveHole = firstHole.getHoleAt(10);
        Hole derdeMoveHole = firstHole.getHoleAt(8);
        Hole controleHole = firstHole.getHoleAt(holeNumber);
        ((Pit) eersteMoveHole).move();
        ((Pit) tweedeMoveHole).move();
        ((Pit) derdeMoveHole).move();
        assertEquals(verwachtteSeeds, controleHole.getSeeds(), "Foutieve hole: " + holeNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,10,11,12,13})
    public void TestPlayerTweeKlaarNaKortsteSpel(int holeNumber) {
        speelKortsteSpel();
        assertEquals(0, firstHole.getHoleAt(holeNumber).getSeeds());
    }

    @Test
    public void TestSpelNogNietKlaar() {
        Hole testHole = firstHole.getHoleAt(1);
        ((Pit) testHole).move();
        assertFalse(((Pit) testHole).isGameOver());
    }

    @Test
    public void TestSpelKlaar() {
        speelKortsteSpel();
        assertTrue(((Pit) firstHole).isGameOver());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,8,9,10,11,12,13})
    public void TestNaPotKlaarIsElkePitLeeg(int holeNumber) {
        Hole hole = firstHole.getHoleAt(holeNumber);
        speelKortsteSpel();
        assertEquals(0, hole.getSeeds());
    }

    @Test
    public void TestNaPotKlaarHebbenMancalasAlleSeeds() {
        Hole mancala1 = firstHole.getHoleAt(7);
        Hole mancala2 = firstHole.getHoleAt(14);
        speelKortsteSpel();
        int totaleSeeds = mancala1.getSeeds() + mancala2.getSeeds();
        assertEquals(48, totaleSeeds);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,8,9,10,11,12,13})
    public void TestNaPotKlaarGeenMoveMogelijk(int holeNumber) {
        Hole hole = firstHole.getHoleAt(holeNumber);
        speelKortsteSpel();
        assertThrows(IllegalStateException.class, () -> {
            ((Pit) hole).move();
        });
    }

    @Test
    public void TestNaPotKlaarMancalaEenHeeft41Seeds() {
        Hole mancala = firstHole.getHoleAt(7);
        speelKortsteSpel();
        assertEquals(41, mancala.getSeeds());
    }

    @Test
    public void TestNaPotKlaarMancalaTweeHeeft7Seeds() {
        Hole mancala = firstHole.getHoleAt(14);
        speelKortsteSpel();
        assertEquals(7, mancala.getSeeds());
    }

    @Test
    public void TestMoveOpMancalaWerktNiet() {
        Hole mancala = firstHole.getHoleAt(7);
        assertThrows(ClassCastException.class, () -> {
            ((Pit) mancala).move();
        });
    }

    @Test
    public void TestMancalaBuurmanWordtOvergeslagen() {
        int[] testOpstelling = {4,4,4,4,4,100,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole mancalaBuurman = firstHole.getHoleAt(14);
        ((Pit) hole6).move();
        assertEquals(0, mancalaBuurman.getSeeds());
    }

    @Test
    public void TestEigenMancalaWordtNietOvergeslagen() {
        int[] testOpstelling = {4,4,4,4,4,100,  0,  4,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole mancalaEigen = firstHole.getHoleAt(7);
        ((Pit) hole6).move();
        assertEquals(8, mancalaEigen.getSeeds());
    }

    @Test
    public void TestVeroverenMisluktAlsTegenoverliggendePitLeegIs() {
        int[] testOpstelling = {4,4,4,4,4,13,  0,  0,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole6 = firstHole.getHoleAt(6);
        Hole holeBuurman = firstHole.getHoleAt(Hole.TOTAL_HOLES - 6);
        ((Pit) hole6).move();
        assertEquals(0, holeBuurman.getSeeds());
    }

    @Test
    public void TestSeedGegooidInLegePitMisluktVerovering() {
        int[] testOpstelling = {4,4,4,4,4,13,  0,  0,4,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole hole6 = firstHole.getHoleAt(6);
        ((Pit) hole6).move();
        assertEquals(0, hole6.getSeeds());
    }

    @Test
    public void TestBlijftMancalaGelijkNaVeroverenMetLegeOverkant() {
        int[] testOpstelling = {4,4,4,4,0,4,  5,  4,0,4,4,4,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole gespeeldeHole = firstHole.getHoleAt(1);
        Hole eigenMancala = firstHole.getHoleAt(7);

        ((Pit) gespeeldeHole).move();

        assertEquals(5, eigenMancala.getSeeds());
    }

    @Test
    public void TestLeegtSpelCorrectNaarDeMancalaBijEindePlayer1() {
        int[] testOpstelling = {1,0,0,0,0,0,  0,  4,4,4,4,27,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole pit1 = firstHole.getHoleAt(1);
        Hole mancala1 = firstHole.getHoleAt(7);

        ((Pit) pit1).move();

        assertEquals(28, mancala1.getSeeds());
    }

    @Test
    public void TestLeegtSpelCorrectNaarDeMancalaBijEindePlayer2() {
        int[] testOpstelling = {1,0,0,0,0,0,  0,  4,4,4,4,27,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);

        Hole pit1 = firstHole.getHoleAt(1);
        Hole mancala2 = firstHole.getHoleAt(14);

        ((Pit) pit1).move();

        assertEquals(20, mancala2.getSeeds());
    }

    // Er is (nog) geen getWinner(): de winnaar is degene met de meeste seeds in de mancala.
    @Test
    public void TestWintPlayerEenHetKortsteSpel() {
        speelKortsteSpel();

        Hole mancala1 = firstHole.getHoleAt(7);
        Hole mancala2 = firstHole.getHoleAt(14);

        assertTrue(mancala1.getSeeds() > mancala2.getSeeds());
    }

    @Test
    public void TestWintPlayerTweeGegevenSpel() {
        int[] testOpstelling = {1,0,0,0,0,0,  0,  4,12,4,4,19,4,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);
        Hole pit1 = firstHole.getHoleAt(1);
        Hole mancala1 = firstHole.getHoleAt(7);
        Hole mancala2 = firstHole.getHoleAt(14);

        ((Pit) pit1).move();

        assertTrue(mancala2.getSeeds() > mancala1.getSeeds());
    }

    @Test
    public void TestLandOpLegePitMetLegeBuurmanPitEigenCheck() {
        int[] testOpstelling = {1,0,0,0,0,0,  0,  47,0,0,0,0,0,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);
        Hole pit1 = firstHole.getHoleAt(1);
        Hole pit2 = firstHole.getHoleAt(2);

        ((Pit) pit1).move();

        assertEquals(1, pit2.getSeeds());
    }

    @Test
    public void TestLandOpLegePitMetLegeBuurmanPitBuurmanCheck() {
        int[] testOpstelling = {1,0,0,0,0,0,  0,  47,0,0,0,0,0,  0};
        firstHole = maakBordMetOpstelling(testOpstelling);
        Hole pit1 = firstHole.getHoleAt(1);
        Hole buurmanVanPit2 = firstHole.getHoleAt(Hole.TOTAL_HOLES - 2);

        ((Pit) pit1).move();

        assertEquals(0, buurmanVanPit2.getSeeds());
    }

}
