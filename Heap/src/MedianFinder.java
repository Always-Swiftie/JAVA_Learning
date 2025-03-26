import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> left;//大顶堆
    PriorityQueue<Integer> right;//小顶堆
    public MedianFinder() {
        this.left=new PriorityQueue<>(Collections.reverseOrder());
        this.right=new PriorityQueue<>();
    }
    public void addNum(int num) {
        if(left.size()==right.size()){
            right.offer(num);
            left.offer(right.poll());
        }else{
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if(left.size()==right.size()){
            return (left.peek()+right.peek())/2.0;
        }else {
            return left.peek();
        }
    }
}
