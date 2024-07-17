/* IMPLEMENTATION OF STACK USING TWO ARRAY QUEUES
My Idea: to push as the same way because Queue also has enqueue, but the pop here is the problem, it needs to be the last enqueued element
 -- we implemented one MainQueue for pushing element in as usual
    -- this has no issue, we just push as usual.
 -- another PopQueue, mainly used for popping latest element
    -- since dequeue is to dequeue the first element, we need to transfer all mainQueue element to leave only the latest element to be the first element of a queue then only perform dequeue, so that it achieves pop
Note:
    * unlike two stacks: transferring one by one will reverse order of elements;
    * transferring one by one element between two queues will not reverse order of element, it will just be the same due to how they first in and first out

Attributes
1. create two queues: one queue responsible for push (main queue) , another responsible for pop (popQueue)
2. size of the stack
3. capacity for creating arrayQueues
Behaviour
1. push
2. pop
 */

// https://www.geeksforgeeks.org/implement-stack-using-queue/



public class ArrayQueuesStack {
    private ArrayQueue queueMain;   // for my idea
    private ArrayQueue queuePop;    // for my idea
    private int size;
    private final int capacity;
    private ArrayQueue q1, q2;  // from reference idea -- better idea

    public ArrayQueuesStack(int capacity){
        this.capacity = capacity;
        queueMain = new ArrayQueue(this.capacity);
        queuePop = new ArrayQueue(this.capacity);
        size = 0;
        q1 = new ArrayQueue(this.capacity);
        q2 = new ArrayQueue(this.capacity);
    }

    public void pUsh(int data){
        q1.enqueue(data);   // since q1 is always empty, then it will be having the latest element front (the sequence will be like stack)

        // transfer the previous added data to the back (so that it will not get dequeued first)
        while(!q2.isEmpty()){
            q1.enqueue(q2.dequeue());
        }

        // swap queue to be able to repeat the process
        // this swap queue makes the last in first out possible
        // for every added data, it will be in a way where it is last in
        ArrayQueue temp = q2;
        q2 = q1;
        q1 = temp;
        // at here, the main queue becomes q2
    }

    public void pOp(){
        if(q2.isEmpty()){
            System.out.println("The stack is underflow.");
            return;
        }
        q2.dequeue();
    }

    public void pUshMyCode(int data){
        queueMain.enqueue(data);
        size ++;
    }

    public void pOpMyCode(){
        if (queueMain.isEmpty()){   // queueMain is enough as we dont enqueue in queuePop
            System.out.println("The stack is underflow");
        }

        else {
            while (!queueMain.isEmpty()){
                //base case
                if(queueMain.size == 1){ // to pop the latest element
                    queueMain.dequeue();
                    size --;
                    break;
                }
                queuePop.enqueue(queueMain.dequeue());
            }

            // transfer back to queueMain after popping
            // NOTE: More efficient idea for this transferring back is to just SWAP the queues, since the sequence is the same after transferring
            while (!queuePop.isEmpty()){
                queueMain.enqueue(queuePop.dequeue());
            }
        }
    }

    public void printQue(){
        if(q2.isEmpty()){
            System.out.println("Stack has nothing to be printed.");
            return;
        }
        q2.printQueue();
    }

    public void printQ() {
        if(queueMain.isEmpty()){
            System.out.println("Stack has nothing to be printed.");
            return;
        }

        queueMain.printQueue(); // print queue1 as all elements at queue 1 now
    }
    public static void main(String[] args){
        ArrayQueuesStack s = new ArrayQueuesStack(100);
//        System.out.println(s.queueMain.size);
        s.pUshMyCode(10);
        s.printQ();
//        System.out.println(s.queueMain.size);
        s.pUshMyCode(8);
        s.printQ();
//        System.out.println(s.queueMain.size);
        s.pUshMyCode(5);
        s.printQ(); //work as expected
//        System.out.println(s.size);
        s.pOpMyCode();
//        System.out.println(s.size);
        s.printQ();
        s.pUshMyCode(2);
        s.printQ();
        s.pUshMyCode(1);
        s.printQ();
        s.pOpMyCode(); // pop 1
        s.printQ();
        s.pOpMyCode(); // pop 2
        s.printQ();
        s.pOpMyCode(); // pop 8
        s.pOpMyCode(); // pop 10
        s.pOpMyCode(); // underflow
        s.printQ();
        s.pUshMyCode(1000);
        s.printQ();

        System.out.println();

        ArrayQueuesStack t = new ArrayQueuesStack(100);
        t.pUsh(10);
        t.printQue();
        t.pUsh(8);
        t.printQue();
        t.pUsh(5);
        t.printQue();
        t.pOp();
        t.printQue();
        t.pUsh(2);
        t.printQue();
        t.pUsh(1);
        t.printQue();
        t.pOp(); // pop 1
        t.printQue();
        t.pOp(); // pop 2
        t.printQue();
        t.pOp(); // pop 8
        t.pOp(); // pop 10
        t.pOp(); // underflow
        t.printQue();
        t.pUsh(1000);
        t.printQue();
    }
}
