import java.io.*;
import java.util.*;

public class LibraryManager {
    private List<Book> books = new ArrayList<>();
    private static final String FILE_PATH = "books.dat"; // 数据持久化文件

    public LibraryManager() { loadBooks(); }

    // 添加书籍
    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    // 删除书籍
    public void removeBook(int id) {
        books.removeIf(book -> book.getId() == id);
        saveBooks();
    }

    // 修改书籍
    public void updateBook(int id, String title, String author, double price) {
        for (Book book : books) {
            if (book.getId() == id) {
                books.set(books.indexOf(book), new Book(id, title, author, price));
                saveBooks();
                break;
            }
        }
    }

    // 查询所有书籍
    public List<Book> getAllBooks() {
        return books;
    }

    // 持久化：保存书籍数据
    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取书籍数据
    private void loadBooks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

