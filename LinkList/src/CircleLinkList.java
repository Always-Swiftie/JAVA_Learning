import java.util.Iterator;

public class CircleLinkList implements Iterable<Integer> {


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node current=sentinel.next;
            @Override
            public boolean hasNext() {
                return current!=sentinel;
            }

            @Override
            public Integer next() {
                int value=current.value;
                current=current.next;
                return value;
            }
        };
    }

    private static class Node{
        Node prev;
        int value;
        Node next;
        public Node(Node prev,int value,Node next) {
            this.prev=prev;
            this.value=value;
            this.next=next;
        }

    }
    private Node sentinel=new Node(null,-1,null);
    public CircleLinkList() {
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
    }

    public void addFirst(int value){
        Node a=sentinel;
        Node b=sentinel.next;
        Node added=new Node(a,value,b);
        a.next=added;
        b.prev=added;//实际上我们没有改变b.next=sentinel,sentinel.prev=b
    }

    public void addLast(int value){
        Node a=sentinel.prev;//The last node
        Node b=sentinel;
        Node added=new Node(a,value,b);
        a.next=added;
        b.prev=added;
    }

    public void removeFirst(){
        Node removed=sentinel.next;//First node in the list
        if(removed==sentinel){
            throw new IllegalArgumentException("illegal!");
        }
        Node a=removed.prev;//sentinel
        Node b=removed.next;
        a.next=b;
        b.prev=a;
    }

    public void removeLast(){
        Node removed=sentinel.prev;
        if(removed==sentinel){
            throw new IllegalArgumentException("illegal location!");
        }
        Node a=removed.prev;
        Node b=sentinel;
        a.next=sentinel;
        b.prev=a;
    }

    public void removeByValue(int value){
            Node removed=findNode(value);
            if(removed==null){
                throw new IllegalArgumentException("Not found!");
            }
            Node a=removed.prev;
            Node b=removed.next;
            a.next=b;
            b.prev=a;
    }

    private Node findNode(int value){
        Node current=sentinel.next;
        while (current!=sentinel){
            if(current.value==value){
                return current;
            }
            current=current.next;
        }
        return null;
    }
}
