import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public class ListNode implements Comparable<ListNode> {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public int compareTo(ListNode o) {
            return this.val-o.val;
        }
    }

    public class minHeap<T extends Comparable<T>> {
        private List<T> heap;

        public minHeap() {
            this.heap = new ArrayList<>();
        }

        public void offer(T val) {
            this.heap.add(val);
            siftUp(heap.size() - 1);
        }

        public T pop() {
            if (heap.isEmpty()) {
                return null;
            }
            T min = heap.getFirst();
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0);
            return min;
        }

        public T peek() {
            return heap.isEmpty() ? null : heap.getFirst();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        private void swap(int i, int j) {
            T temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        private void siftUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap.get(index).compareTo(heap.get(parent)) >= 0) {
                    break;//在小顶堆中子节点一定比父结点大
                }
                swap(index, parent);
                index = parent;
            }
        }

        private void siftDown(int index) {
            int size = heap.size();
            while (index * 2 + 1 < size) {
                int leftChild = index * 2 + 1;
                int rightChild = index * 2 + 2;
                int min = leftChild;//先把最小值的指针放在最大的元素（左孩子）
                if (rightChild < size && heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                    min = rightChild;
                }
                if (heap.get(index).compareTo(heap.get(min)) <= 0) {
                    break;
                }
                swap(index, min);
                index = min;
            }
        }

        public ListNode mergeKLists(ListNode[] lists) {
            minHeap<ListNode> minHeap = new minHeap<>();
            for (ListNode head : lists) {
                if (head != null) {
                    minHeap.offer(head);
                }
            }
            ListNode sentinel = new ListNode();
            ListNode cur = sentinel;
            while (!minHeap.isEmpty()) {
                ListNode minnode = minHeap.pop();
                cur.next = minnode;
                cur = cur.next;
                if (minnode.next != null) {
                    minHeap.offer(minnode.next);
                }
            }
            return sentinel.next;

        }

    }
}