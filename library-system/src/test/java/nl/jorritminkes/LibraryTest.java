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

    @Test
    public void maxThreeBooksPerMember() {
        for (int i = 1; i <= 3; i++) {
            library.addBook("Title" + i, "Author" + i);
        }

        Book book1 = library.getBooks().get(1);
        Book book2 = library.getBooks().get(2);
        Book book3 = library.getBooks().get(3);
        Member member = library.getMembers().get(1);

        library.borrowBook(book1, member);
        library.borrowBook(book2, member);
        library.borrowBook(book3, member);

        assertThrows(IllegalStateException.class,
                () -> library.borrowBook(library.getBooks().get(4), member));
    }






}