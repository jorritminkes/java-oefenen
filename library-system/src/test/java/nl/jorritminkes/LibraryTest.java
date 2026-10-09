package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    public void bookGetsAdded() {
        library.addBook("Title", "Author");

        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void memberGetsAdded() {
        library.addMember("Test");

        assertEquals(1, library.getMembers().size());
    }
}