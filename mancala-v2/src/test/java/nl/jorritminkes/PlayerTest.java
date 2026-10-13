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
        assertNotEquals(null, playerOne);
    }

}