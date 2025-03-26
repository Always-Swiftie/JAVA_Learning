public interface Queue<E> {

        boolean offer(E value);//add element from last

        E poll();//remove and get first element

        E peek();//get first element

        boolean isEmpty();

        boolean isFull();
}
