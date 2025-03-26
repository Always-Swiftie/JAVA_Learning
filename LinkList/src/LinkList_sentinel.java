import java.util.Iterator;
import java.util.function.Consumer;

public class LinkList_sentinel implements Iterable<Integer>
{
    private Node head=new Node(-1,null);//头节点

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
        public  Node(int value, Node next){
            this.value=value;
            this.next=next;
        }
    }

    public void addFirst(int value){
        insert(0,value);
    }
    private Node findLast(){
            Node current=head;
            while (current.next!=null){
                current=current.next;
            }
            return current;
        }


    public void addLast(int value){
        Node last=findLast();
        last.next=new Node(value,null);
    }

    public void travel(){//普通迭代遍历
        Node current=head.next;
        while (current!=null){
            System.out.println(current.value);
            current=current.next;
        }
    }

    public void travel2(Consumer<Integer> consumer){
        Node current=head.next;
        while (current!=null){
            consumer.accept(current.value);
            current=current.next;
        }
    }

    public void travel3(Consumer<Integer> consumer){
        for(Node current = head.next; current!=null; current=current.next){
            consumer.accept(current.value);
        }
    }

    private Node findNode(int index){
        int i=-1;
        for(Node current = head.next; current!=null; current=current.next,i++){
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

        Node prev=findNode(index-1);//待插入的上一节点
        if(prev==null){
            throw exception(index);
        }
        prev.next=new Node(value,prev.next);
    }

    private void removeFirst(){
        remove(0);
    }

    public void remove(int index){
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
