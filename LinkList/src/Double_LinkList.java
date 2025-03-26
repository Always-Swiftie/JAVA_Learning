import java.util.Iterator;
import java.util.function.Consumer;

public class Double_LinkList implements Iterable<Integer>{
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i=-1;
            Node p=head.next;
            @Override
            public boolean hasNext() {
                return p!=tail;
            }

            @Override
            public Integer next() {
                int value=p.value;
                p=p.next;
                return value;
            }
        };
    }

    static class Node{
        Node prev;
        int value;
        Node next;
        public Node(Node prev,int value,Node next){
            this.prev=prev;
            this.value=value;
            this.next=next;
        }
    }
    private Node head;
    private Node tail;
    public Double_LinkList(){
        head=new Node(null,-1,null);
        tail=new Node(null,-2,null);
        head.next=tail;
        tail.prev=head;
    }
    private Node findNode(int index){
        int i=-1;
        for(Node p=head;p!=tail;p=p.next,i++){
            if(i==index){
                return p;
            }
        }
        return null;
    }

    public void addFirst(int value){
        insert(0,value);
    }

    public void removeFirst(){

    }

    public void addLast(int value){
        Node last=tail.prev;
        Node added=new Node(last,value,tail);
        last.next=added;
        tail.prev=added;
    }

    public void insert(int index,int value){
        Node prev=findNode(index-1);
        if(prev==null){
            throw illegalArgumentException(index);
        }
        Node next=prev.next;
        Node inserted=new Node(prev,value,next);
        prev.next=inserted;
        next.prev=inserted;
    }
    public void removeLast(){
        Node removed=tail.prev;
        if(removed==tail){
            throw illegalArgumentException(0);
        }
        Node prev=removed.prev;
        prev.next=tail;
        tail.prev=prev;

    }
    public void remove(int index){
        Node prev=findNode(index-1);
        if(prev==null){
            throw illegalArgumentException(index);
        }
        Node target=prev.next;
        if(target==tail){
            throw illegalArgumentException(index);
        }
        Node next=target.next;
        prev.next=next;
        next.prev=prev;
    }
    public void travel(Consumer<Integer> consumer){
        Node current=head.next;
        while (current!=tail){
            consumer.accept(current.value);
            current=current.next;
        }
    }
    private IllegalArgumentException illegalArgumentException(int index){
        return new IllegalArgumentException(String.format("[%d] index is illegal!",index));
    }
}
