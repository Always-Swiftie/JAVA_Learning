import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
    MyQueue queue=new MyQueue();


    }
    static class MyQueue {
        Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            s2.push(x);
        }

        public int pop() {
            if(s1.isEmpty()){
                while (!s2.isEmpty()){
                    s1.push(s2.pop());
                }

            }
            return s1.pop();
        }

        public int peek() {
            if(s1.isEmpty()){
                while (!s2.isEmpty()){
                    s1.push(s2.pop());
                }
            }
            return s1.peek();
        }

        public boolean empty() {
            return s1.isEmpty()&& s2.isEmpty();
        }
    }
}