import java.util.*;

//使用一个ArrayList实现大顶堆
public class Priority_Maxheap<T extends Comparable<T>> {
    private List<T> heap;

    public Priority_Maxheap() {
        this.heap =new ArrayList<>();
    }

    private int parent(int i){
        return (i-1)/2;//获取父结点索引
    }
    private int leftChild(int i){
        return 2*i+1;
    }
    private int rightChild(int i){
        return 2*i+2;
    }
    private void swap(int i,int j){
        T temp=this.heap.get(i);
        this.heap.set(i,heap.get((j)));
        this.heap.set(j,temp);
    }
    public void offer(T val){
        this.heap.add(val);
        goUp(heap.size()-1);
    }
    private void goUp(int index) {
        while (index > 0 && heap.get(parent(index)).compareTo(heap.get(index)) < 0) {
            //如果当前插入值比它的父节点要大，则一直上浮
            swap(index, parent(index));
            index = parent(index);
        }
    }
    public T poll(){
        if(heap.isEmpty()){
            throw new IllegalStateException("Empty head!");
        }
        T max= heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        goDown(0);
        return max;
    }

    private void goDown(int index){
        int maxIndex=index;
        int left=leftChild(index);
        int right=rightChild(index);
        if(left<heap.size()&&heap.get(left).compareTo(heap.get(maxIndex))>0){
            maxIndex=left;
        }
        if(right<heap.size()&&heap.get(right).compareTo(heap.get(maxIndex))<0){
            maxIndex=right;
        }
        if(index!=maxIndex){
            swap(index,maxIndex);
            goDown(maxIndex);
        }
    }

    public T peek(){
        if(heap.isEmpty()){
            throw  new IllegalStateException("Empty heap!");
        }
        return heap.get(0);
    }
    public boolean isEmpty(){
        return heap.isEmpty();
    }
    public int size(){
        return heap.size();
    }

    public Priority_Maxheap(T[] arr) {
        heap = new ArrayList<>();
        for (T num : arr) heap.add(num);
        for (int i = (heap.size() - 2) / 2; i >= 0; i--) {
            goDown(i);
        }
    }
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        Priority_Maxheap<T> maxHeap = new Priority_Maxheap<>(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.poll();
        }
    }



}

