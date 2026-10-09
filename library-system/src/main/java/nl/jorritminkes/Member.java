package nl.jorritminkes;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final int id;
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }


}
