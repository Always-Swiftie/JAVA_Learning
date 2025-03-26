import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📚 图书管理系统");
            System.out.println("1. 添加书籍");
            System.out.println("2. 删除书籍");
            System.out.println("3. 修改书籍");
            System.out.println("4. 查看所有书籍");
            System.out.println("5. 退出");
            System.out.print("请选择操作: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("输入书籍 ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // 清除缓冲区
                    System.out.print("输入书名: ");
                    String title = scanner.nextLine();
                    System.out.print("输入作者: ");
                    String author = scanner.nextLine();
                    System.out.print("输入价格: ");
                    double price = scanner.nextDouble();
                    library.addBook(new Book(id, title, author, price));
                    System.out.println("✅ 书籍已添加!");
                }
                case 2 -> {
                    System.out.print("输入要删除的书籍 ID: ");
                    int id = scanner.nextInt();
                    library.removeBook(id);
                    System.out.println("✅ 书籍已删除!");
                }
                case 3 -> {
                    System.out.print("输入要修改的书籍 ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("输入新书名: ");
                    String title = scanner.nextLine();
                    System.out.print("输入新作者: ");
                    String author = scanner.nextLine();
                    System.out.print("输入新价格: ");
                    double price = scanner.nextDouble();
                    library.updateBook(id, title, author, price);
                    System.out.println("✅ 书籍已修改!");
                }
                case 4 -> {
                    System.out.println("\n📖 所有书籍:");
                    for (Book book : library.getAllBooks()) {
                        System.out.println(book);
                    }
                }
                case 5 -> {
                    System.out.println("📌 退出系统...");
                    return;
                }
                default -> System.out.println("❌ 请输入正确的选项!");
            }
        }
    }
}
