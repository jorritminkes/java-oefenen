package nl.jorritminkes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;
    private Book book;
    private Member member;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBook("TestTitle", "TestAuthor");
        library.addMember("TestName");
        book = library.getBookFromBookId(1);
        member = library.getMemberFromMemberId(1);
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

        library.borrowBook(1, 1);

        assertFalse(book.isAvailable());
    }

    @Test
    public void borrowingBookAddsToMemberBorrowedList() {
        Book book = library.getBooks().get(1);
        Member member = library.getMembers().get(1);

        library.borrowBook(1, 1);

        assertTrue(member.getBorrowedBooks().contains(book));
    }

    @Test
    public void maxThreeBooksPerMember() {
        for (int i = 1; i <= 3; i++) {
            library.addBook("Title" + i, "Author" + i);
        }

        library.borrowBook(1, 1);
        library.borrowBook(2, 1);
        library.borrowBook(3, 1);

        assertThrows(IllegalStateException.class,
                () -> library.borrowBook(4, 1));
    }

    @Test
    public void returnBookRemovesFromMemberList() {
        library.borrowBook(1, 1);
        library.returnBook(1, 1);

        assertEquals(0, member.getBorrowedBooks().size());
    }

    @Test
    public void getBookFromIdReturnsCorrectBook() {
        assertEquals(book, library.getBookFromBookId(1));
    }

    @Test
    public void getMemberFromIdReturnsCorrectMember() {
        assertEquals(member, library.getMemberFromMemberId(1));
    }

    @Test
    public void getAvailableBooksReturnsOneWithoutBorrowed() {
        assertEquals(1, library.getAvailableBooks().size());
    }

    @Test
    public void getAvailableBooksReturnsZeroWhenOneBorrowed() {
        library.borrowBook(1, 1);

        assertEquals(0, library.getAvailableBooks().size());
    }

    @Test
    public void borrowingUnavailableBookDoesNotAddItToAnotherMember() {
        library.addMember("SecondMember");

        library.borrowBook(1, 1);
        library.borrowBook(1, 2);

        assertEquals(1, member.getBorrowedBooks().size());
        assertTrue(library.getMemberFromMemberId(2)
                .getBorrowedBooks().isEmpty());
    }

    @Test
    public void returningBookMakesItAvailableAgain() {
        library.borrowBook(1, 1);

        library.returnBook(1, 1);

        assertTrue(book.isAvailable());
        assertEquals(1, library.getAvailableBooks().size());
    }

    @Test
    public void returningBookNotBorrowedThrowsException() {
        assertThrows(IllegalStateException.class,
                () -> library.returnBook(1, 1));
    }

    @Test
    public void borrowingUnknownBookIsHandled() {
        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook(999, 1));
    }

    @Test
    public void borrowingWithUnknownMemberIsHandled() {
        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook(1, 999));
    }
}