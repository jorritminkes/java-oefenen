package nl.jorritminkes;

import java.util.HashMap;
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
}
