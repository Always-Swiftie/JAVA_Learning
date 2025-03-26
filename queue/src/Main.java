import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.Deque;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        String string=new String();
        StringBuilder stringBuilder=new StringBuilder("This is a demo");
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        String str=stringBuilder.toString();
        System.out.println(str);
    }
}