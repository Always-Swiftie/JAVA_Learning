import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
                CircleLinkList circleLinkList=new CircleLinkList();
                circleLinkList.addFirst(1);
                circleLinkList.addFirst(2);
                circleLinkList.addFirst(3);
                circleLinkList.addFirst(4);
                circleLinkList.forEach(value-> System.out.print(value+"->"));
                circleLinkList.addLast(88);
                circleLinkList.addLast(99);
                circleLinkList.addLast(77);
                circleLinkList.addLast(66);
                System.out.print('\n');
                circleLinkList.forEach(value-> System.out.print(value+"->"));
                circleLinkList.removeFirst();
                circleLinkList.removeFirst();
                System.out.print('\n');
                circleLinkList.forEach(value-> System.out.print(value+"->"));
                circleLinkList.removeLast();
                System.out.print('\n');
                circleLinkList.forEach(value-> System.out.print(value+"->"));
                circleLinkList.removeByValue(88);
                System.out.print('\n');
                circleLinkList.forEach(value-> System.out.print(value+"->"));
    }
}