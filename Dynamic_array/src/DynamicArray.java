import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer> {
    private int size = 0;
    private int capacity = 8;
    private int[] array={};
    public void addLast(int element){
        array[size]=element;
        size++;
    }
    public void add(int element,int index){
        //容量检查
        if(size==0){
            array=new int[capacity];
        }
        else if(size==capacity){
            capacity+=capacity>>1;
            int [] newArray=new int[capacity];
            System.arraycopy(array,0,newArray,0,size);
            array=newArray;
        }
        if (index >=0  && index <size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }//同时可以处理尾部插入或者内部插入
        array[index]=element;
        size++;
    }
    public void printArray(){
        for(int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
    }
    public int get(int index){
        if(index>=0&&index<size){
            return array[index];
        }
        return -1;
    }
    public int remove(int index){
        int removed=array[index];
        if(index<size-1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removed;
    }
    public void foreach(Consumer<Integer> consumer){
        for(int i=0;i<size;i++){
            consumer.accept(array[i]);
        }
    }
    public IntStream stream(){
        return IntStream.of(Arrays.copyOfRange(array,0,size));
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i=0;
            @Override
            public boolean hasNext() {//有无下一元素
                return i<size;
            }
            @Override
            public Integer next() {//返回当前元素，然后迭代器移动到下一元素
                return array[i++];
            }
        };


    }
}
