package nl.jorritminkes;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook("Dune", "Frank Herbert");
        library.addBook("1984", "George Orwell");
        library.addBook("Foundation", "Isaac Asimov");

        library.addMember("Alice");
        library.addMember("Bob");

        library.borrowBook(1, 1);
        library.borrowBook(2, 1);
        library.borrowBook(3, 2);

        System.out.println("Available books:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getId() + ": " + book.getTitle());
        }

        Member alice = library.getMemberFromMemberId(1);
        System.out.println("\nAlice's borrowed books:");
        for (Book book : alice.getBorrowedBooks()) {
            System.out.println(book.getTitle());
        }

        library.returnBook(1, 1);

        System.out.println("\nAvailable books after returning Dune:");

        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle());
        }
    }
}