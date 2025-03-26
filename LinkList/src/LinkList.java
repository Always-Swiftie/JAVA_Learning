import java.util.Iterator;
import java.util.function.Consumer;

public class LinkList implements Iterable<Integer>{
    private Node head=null;//头节点

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {//一个匿名内部类
            Node current=head;
            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public Integer next() {//返回当前指向元素的值，并向后移动一位
                int value=current.value;
                current=current.next;
                return value;
            }
        };
    }

    private static class Node{
        int value;
        Node next;
        public  Node(int value,Node next){
            this.value=value;
            this.next=next;
        }
    }

    public void addFirst(int value){
        if(head==null){//若链表为空)
            head=new Node(value,null);
        }else{//若链表飞空
            head=new Node(value,head);//上面一行可以不要
        }
    }
    private Node findLast(){
        if(head==null){
            return null;
        }else{
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }//stop at current.next=null
            return current;
        }
    }

    public void addLast(int value){
        Node last=findLast();
        if(last==null){
            addFirst(value);
            return;
        }
        last.next=new Node(value,null);
    }

    public void travel(){//普通迭代遍历
        Node current=head;
        while (current!=null){
            System.out.println(current.value);
            current=current.next;
        }
    }

    public void travel2(Consumer<Integer> consumer){
        Node current=head;
        while (current!=null){
            consumer.accept(current.value);
            current=current.next;
        }
    }

    public void travel3(Consumer<Integer> consumer){
        for(Node current=head;current!=null;current=current.next){
            consumer.accept(current.value);
        }
    }

    public void travel4(){
            recursive_travel(head);
    }

    private void recursive_travel(Node current){
        if (current == null) {
            return;
        }
        System.out.print(current.value+"->");
        recursive_travel(current.next);
    }
    private Node findNode(int index){
        int i=0;
        for(Node current=head;current!=null;current=current.next,i++){
            if(i==index){
                return current;
            }
        }
        return null;
    }

    public int get_index(int index){
        Node node=findNode(index);
        if(node==null){
            throw exception(index);
        }
        return node.value;
    }

    public void insert(int index,int value){
        if(index==0){
            addFirst(value);
            return;
        }
        Node prev=findNode(index-1);//待插入的上一节点
        if(prev==null){
            throw exception(index);
        }
        prev.next=new Node(value,prev.next);
    }

    private void removeFirst(){
        if(head==null){
            throw exception(0);
        }
        head=head.next;
    }

    public void remove(int index){
        if(index==0){
            removeFirst();
            return;
        }
        Node prev=findNode(index-1);
        if(prev==null){
            throw exception(index);
        }
        Node target=prev.next;
        if(target==null){
            throw exception(index);
        }
        prev.next=target.next;
    }
    protected IllegalArgumentException exception(int index){
        return new IllegalArgumentException(String.format("index[%d] is illegal!\n",index));
    }
}
