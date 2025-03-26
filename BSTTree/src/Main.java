public class Main {
    public static void main(String[] args) {
        BTree tree1=new BTree(2);
        tree1.put(1);
        tree1.put(2);
        System.out.println(tree1.contains(1));
        System.out.println(tree1.contains(2));
        System.out.println(tree1.contains(3));
        tree1.put(4);
        tree1.put(5);
        tree1.put(6);
        System.out.println(tree1.contains(4));
    }
}