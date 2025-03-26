public class Main {
    public static void main(String[] args) {
        BSTree2<Integer,String> Tree=new BSTree2<>();
        AVLTree tree=new AVLTree();
        tree.put(1,"this is 1");
        tree.put(2,"this is 2");
        tree.put(3,"this is 3");
        System.out.println(tree.get(2));
        tree.remove(3);
        tree.remove(2);
        System.out.println(tree.get(3));
        System.out.println(tree.get(2));
        tree.
    }
}