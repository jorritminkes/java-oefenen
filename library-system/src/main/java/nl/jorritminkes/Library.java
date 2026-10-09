package nl.jorritminkes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    private int nextBookId = 1;
    private int nextMemberId = 1;

    public void addBook(String title, String author) {
        int id = nextBookId++;
        Book book = new Book(id, title, author);
        books.put(id, book);
    }

    public void addMember(String name) {
        int id = nextMemberId++;
        Member member = new Member(id, name);
        members.put(id, member);
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Integer, Member> getMembers() {
        return members;
    }

    public void borrowBook(int bookId, int memberId) {
        Book book = getBookFromBookId(bookId);
        Member member = getMemberFromMemberId(memberId);

        if (!book.isAvailable()) {
            return;
        }

        if (member.getBorrowedBooks().size() >= 3) {
            throw new IllegalStateException("Member cannot borrow more than 3 books");
        }

        book.switchAvailable();
        member.addBorrowedBook(book);
    }

    public void returnBook(int bookId, int memberId) {
        Book book = getBookFromBookId(bookId);
        Member member = getMemberFromMemberId(memberId);

        if (!member.getBorrowedBooks().contains(book)) {
            throw new IllegalStateException("Member has not borrowed the book");
        }

        member.getBorrowedBooks().remove(book);
        book.switchAvailable();
    }

    public Book getBookFromBookId(int bookId) {
        return books.get(bookId);
    }

    public Member getMemberFromMemberId(int memberId) {
        return members.get(memberId);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        return availableBooks;
    }
}
