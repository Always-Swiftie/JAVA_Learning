public class minHeap {
    int[] array;
    int size;

    public minHeap(int capacity){
        this.array=new int[capacity];
    }

    public minHeap(int[] array){
        this.array=array;
        this.size=array.length;
        heapify();
    }
    //建堆
    //如何找到最后一个非叶子节点索引？ index=size/2-1
    private void heapify(){
        for(int i=size/2-1;i>=0;i--){
            down(i);
        }
    }

    public int poll(){//获取并删除堆顶元素
        int top=array[0];
        swap(0,size-1);
        size--;//删除最后一个元素
        down(0);
        return top;
    }

    public int poll(int index){//删除指定位置
        if(index>=size){
            throw new IllegalStateException("Out of boundary!");
        }
        int value=array[index];
        swap(index,size-1);
        size--;
        down(index);
        return value;
    }

    public int peek(){
        if(array.length==0){
            throw new IllegalStateException("Empty heap!");
        }
        return array[0];
    }

    private void swap(int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    private void down(int parent){
        int leftChild=parent*2+1;
        int rightChild=leftChild+1;
        int min=parent;
        if(leftChild<size&&array[leftChild]<array[min]){
            min=leftChild;
        }
        if(rightChild<size&&array[rightChild]<array[min]){
            min=rightChild;
        }
        if(min!=parent){
            swap(min,parent);
            down(min);
        }
    }
    //替换堆顶元素
    public void replace(int replaced){
        array[0]=replaced;
        down(0);
    }

    public boolean offer(int offered){
        if(size==array.length){
            return false;
        }
        up(offered);
        size++;
        return true;
    }
    private void up(int offered){
        int child=size;
        while (child>0){
            int parent=(child-1)/2;
            if(array[parent]>offered){
                array[child]=array[parent];
            }else{
                break;
            }
            child=parent;
        }
        array[child]=offered;//可以理解为，在数组末尾新添加了一个元素（实际上并没有），这个元素添加后可能影响了大顶堆的性质，所以要上浮寻找它的合理位置，由于是大顶堆，所以父结点一定比孩子大，假如当前元素比它的父结点大，那就让child当前指向的节点变为父结点的值（注意原来父结点的值不变）
    }

    public static int findKthLargest(int[] nums, int k) {
        minHeap heap=new minHeap(k);
        for(int i=0;i<k;i++){
            heap.offer(nums[i]);
        }
        for(int i=k;i<nums.length;i++){
            if(nums[i]<=heap.peek()){
                continue;
            }else{
                heap.replace(nums[i]);
            }
        }
        return heap.peek();
    }
    public boolean isFull(){
        return size==array.length;
    }
}