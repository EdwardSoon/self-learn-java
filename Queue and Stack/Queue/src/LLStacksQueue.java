/* IMPLEMENTATION OF QUEUE USING STACKS
Main concept: since it is always pop and push the top
 enqueue
 -- we can start enqueuing from stackEnqueue (instead of stackDequeue) since the stack FIFO is adhered to the enqueue principle where enqueue at the last
 -- to enqueue to the last element, we can make sure the stackEnqueue is FULL so that we can push to the last
 dequeue
 -- since we can only dequeue from the top, we need to ensure that the dequeueing happens on the firstIn element
 -- in this case, while stackDequeue is empty, we can pop from stackEnqueue and push the element to the stackDequeue so that the order is now reversed where the first element becomes the top
 -- then we can pop from the top
Attribute
1. Stack 1 to serve for enqueue
2. Stack 2 to serve for dequeue
3. size of the queue
Behaviour
1. Stack 1 to only dequeue (pop)
2. Stack 2 to only enqueue (push)
3. find out the size of the queue
 */

/*
T(n) = O(N) for enQueue and Dequeue as there is while loop to transfer all elements
-- it will be O(1) if we just implement by using Array or LinkedList instead of Stacks
S(n) = O(N) as auxiliary space used up for N stack elements
 */

public class LLStacksQueue {
    // attributes
    private LinkedListStack stackDequeue;
    private LinkedListStack stackEnqueue;
    private int size;

    // constructor
    public LLStacksQueue(){
        stackDequeue = new LinkedListStack();
        stackEnqueue = new LinkedListStack();
//        size = stackDequeue.size + stackEnqueue.size; // we cannot add together, as there are other pop and adding involved.
        size = 0;
    }

    public void enQueue(int data){
        while (!stackDequeue.isEmpty()){
            stackEnqueue.push(stackDequeue.pop());
        }
        // as long as the stackDequeue is empty, we can push data to stackEnqueue, because it adheres to the principle of queue
        stackEnqueue.push(data);
        size ++;
    }

    public void deQueue(){
        //base case
        if(size == 0){
            System.out.println("The queue is underflow");
            return;
        }

        while (!stackEnqueue.isEmpty()){
            stackDequeue.push(stackEnqueue.pop());
        }
        stackDequeue.pop();
        size --;
    }

    public void printQ(){
        if(stackEnqueue.isEmpty()){
            stackDequeue.printStack();
        }
        else if(stackDequeue.isEmpty()){
            stackEnqueue.printStack();
        }
    }


    public static void main(String[] args){
        LLStacksQueue q = new LLStacksQueue();
        q.deQueue();
        q.enQueue(3);
        q.enQueue(10);
        q.deQueue();
        q.enQueue(8);
        q.enQueue(9);
        q.deQueue();

        q.printQ();
        System.out.println(q.size);

    }
}
