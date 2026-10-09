package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBook("TestTitle", "TestAuthor");
        library.addMember("TestName");
    }

    @Test
    public void bookGetsAdded() {
        library.addBook("Title", "Author");

        assertNotEquals(0, library.getBooks().size());
    }

    @Test
    public void memberGetsRegistered() {
        library.addMember("Test");

        assertNotEquals(0, library.getMembers().size());
    }

    @Test
    public void getBookFromHashMap() {
        Book book = library.getBooks().get(1);

        assertNotNull(book);
    }

    @Test
    public void getBookIdFromHashMap() {
        Book book = library.getBooks().get(1);

        assertEquals(1, book.getId());
    }

    @Test
    public void borrowingBookSwitchesAvailability() {
        Book book = library.getBooks().get(1);
        Member member = library.getMembers().get(1);

        library.borrowBook(book, member);

        assertFalse(book.isAvailable());
    }

    @Test
    public void borrowingBookAddsToMemberBorrowedList() {
        Book book = library.getBooks().get(1);
        Member member = library.getMembers().get(1);

        library.borrowBook(book, member);

        assertTrue(member.getBorrowedBooks().contains(book));
    }





}