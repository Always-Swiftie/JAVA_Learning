public class Main {
    public static void main(String[] args) {
        BTree tree = new BTree(2);

        tree.put(1);
        tree.put(2);
        tree.put(3);
        tree.put(4);
        tree.put(5);
        tree.put(6);
        tree.put(7);
        tree.put(8);
        tree.put(9);

        System.out.println("Before Deletion:");
        for (int i = 1; i <= 9; i++) {
            System.out.println("Contains " + i + "? " + tree.contains(i));
        }

        tree.remove(5);
        tree.remove(6);
        tree.remove(7);

        System.out.println("After Deletion:");
        for (int i = 1; i <= 9; i++) {
            System.out.println("Contains " + i + "? " + tree.contains(i));
        }
        }

    }