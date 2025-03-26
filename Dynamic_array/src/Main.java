import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        DynamicArray dynamicArray=new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.foreach(System.out::println);
        dynamicArray.remove(2);
        dynamicArray.foreach(System.out::println);
    }
}