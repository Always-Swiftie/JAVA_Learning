import java.util.ArrayList;
import java.util.List;
//基于动态数组实现的大顶堆
public class maxHeap_arraylist {
    List<Integer> array;
    int size;

    public maxHeap_arraylist(int capacity){
        this.array=new ArrayList<>(capacity);
        this.size=0;
    }
    public maxHeap_arraylist(int[] origin){
        if(origin.length==0){
            this.array=new ArrayList<>();
            return;
        }
        for(int value:origin){
            array.add(value);
        }
        this.size=array.size();
        heapify();
    }
    public maxHeap_arraylist(ArrayList<Integer> arrlist){
        this.array=arrlist;
    }
    //建堆
    //如何找到最后一个非叶子节点索引？ index=size/2-1
    private void heapify(){
        for(int i=size/2-1;i>=0;i--){
            down(i);
        }
    }

    public int poll(){
        int top=array.get(0);
        swap(0,size-1);
        size--;
        down(0);
        return top;
    }
    public int poll(int index){
        if(index>=size){
            throw new IllegalStateException("Out of boundary!");
        }
        int value=array.get(index);
        swap(index,size-1);
        size--;
        down(index);
        return value;
    }

    public int peek(){
        if(array.isEmpty()){
            throw new IllegalStateException("Empty heap!");
        }
        return array.getFirst();
    }
    private void swap(int i,int j){
        int temp=array.get(i);
        array.set(i,array.get(j));
        array.set(j,temp);
    }
    private void down(int parent){
        int leftChild=parent*2+1;
        int rightChild=leftChild+1;
        int max=parent;
        if(leftChild<size&&array.get(leftChild).compareTo(array.get(max))>0){
            max=leftChild;
        }
        if(rightChild<size&&array.get(rightChild).compareTo(array.get(max))>0){
            max=rightChild;
        }
        if(max!=parent){
            swap(max,parent);
            down(max);
        }
    }

    public void replace(int replaced){
        array.set(0,replaced);
        down(0);
    }

    public boolean offer(int offered){
        array.add(offered);
        up(size);
        size++;
        return true;
    }
    private void up(int child){
        int value = array.get(child);
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (array.get(parent) < value) {
                array.set(child, array.get(parent));
            } else {
                break;
            }
            child = parent;
        }
        array.set(child, value);
    }

    public static ArrayList<Integer> heapsort(ArrayList<Integer> arrayList){
        maxHeap_arraylist heap=new maxHeap_arraylist(arrayList);
        while (heap.size>1){
            heap.swap(0,heap.size-1);
            heap.size--;
            heap.down(0);
        }
        return new ArrayList<>(heap.array);
    }
}
