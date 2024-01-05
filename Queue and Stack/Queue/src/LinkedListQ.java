//https://www.geeksforgeeks.org/queue-linked-list-implementation/

/* 
 * IMPLEMENTATION QUEUE USING LINKEDLIST   
 * A linkedlist queue must have 
 *  - front node
 *  - rear node 
 *  - a size of the queue to keep track the size
 */


public class LinkedListQ{
    // attributes
    NodeQ front;   // front pointer for dequeue
    NodeQ rear;    // rear pointer for enqueue
    int size;

    private class NodeQ{        // to encapsulate so that others will not accidentally access the unnecessary class
        int data; 
        NodeQ next;

        NodeQ(int d){
            this.data = d;
            this.next = null;
        }
    }

    // constructor 
    LinkedListQ(){
        front = null; // even if we dont initialise here, it is null by default already, but to be clear and for good code practice we do it here.
        rear = null;
        size = 0;
    }

    /* ENQUEUE: enqueue at the last node
       My pseudocode:
     * 1. if front is null then add the data to front 
     * 2. if front is not null and queue only has 1 element (when front node == rear node) then rear = newnode and front.next = rear
     * 3. if front is not null and queue has 2 or more elements, then assign the previous rear.next to new_node and assign the rear itself to be the new_node , so that the previous rear.next is pointing to the current rear
     * Note: there is no overflow problem as it is linkedlist
     * 
     * 
     */
    public void enqueueMyCode(int data){
        NodeQ new_node = new NodeQ(data); // define a new node

        if(front == null){
            rear = new_node;
            front = new_node;
            return;
        }        
        else if(front.next == null){   // front != null and front.next == null (where rear == front)
            rear = new_node;
            front.next = rear;
        }
        else if(front.next != null){
            rear.next = new_node;   // current rear.next should point to new node
            rear = rear.next;        // then rear itself becomes the new node, so that the previous rear is pointing to the current rear
        }
        size++;
    }
    /*  [THIS IS BETTER] ENQUEUE: enqueue at the last node 
     *  Real pseudocode:
       1. Base case: if front is null then add the node to the front and rear
       2. if front is not null then rear.next points to the new node then rear = rear.next/new_node 
        2.1 with 1 element in the queue, since rear and front stores the same address of the node, that means they are essentially the same: 
            2.1.1 if i appoint rear.next to the new_node, effectively front.next will points to the new_node too for second element, since currently front node and rear node are accessing to the same address of object.
     */
    public void enqueue(int data){
        NodeQ new_node = new NodeQ(data); // define a new node
        // base case
        if(front == null){          // at queue is empty time, front and rear = new_node
            front = new_node;           
            rear = new_node;         // they are pointing to the same address now
        }        
        else{       // add queue at the end of the queue
            rear.next = new_node;    // current last node.next to point at new_node
            rear = rear.next;        // make last node to be the new_node (or rear = new_node; it is the same, as essentially it will equal to the same memory address/object)
        }
        size ++;
    }
 

    /* DEQUEUE: dequeue the first node
     * 1. if front is not null then make front node = front.next 
     *  1.1 (didnt thought of this) if front becomes null after dequeue then rear should be null too (if not rear will have data even all dequeued)
     * 2. if front is null then print underflow
     */
    public void dequeue(){
        if (front == null){
            System.out.println("The queue is underflow");  // chose this because we allow that mistake and wanna continue despite the mistake
            // throw new NoSuchElementException("The queue is underflow");  // this will throw an error while compilation and end the exexcution directly
            return;
        }
        else{
            front = front.next;
            size--;
        }

        // 1.1 if dequeued then front == null then rear assigned to be null
        if(front == null){
            rear = null;    
        }
        
    }

    // isEMPTY: check if it is empty

    public boolean isEmpty(){
        return front==null;
    }
    
    // front(): return the front node's data
    public int front(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        return front.data;
    }

    // rear(): return the front node's data
    public int rear(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        return rear.data;
    }

    public void printQ(){
        if(isEmpty()){      // added this to handle if queue is empty, it wont print
            System.out.println("The queue is empty");
            return;
        }
        System.out.print("QueueList: ");
        NodeQ temp_node = front;
    
        while(temp_node != null){
            System.out.print(temp_node.data + " "); // instead of using comma, we can use space for better readability
            temp_node = temp_node.next;
        }
        System.out.println();
    }

    

    public static void main(String[] args){
        LinkedListQ list = new LinkedListQ();
        // list.front; // this doesnt work, as the attribute defined in the class is not initialised with any value, thus we need a front() method to find out the data of front node
        // System.out.println(list.front());
        list.enqueue(9);
        // System.out.println(list.front);
        // System.out.println(list.front.data);
        // System.out.println(list.front.next);
        // System.out.println(list.rear.data);
        list.enqueue(4);
        // System.out.println(list.front.data);
        // System.out.println(list.front.next);
        // System.out.println(list.rear.data);
        list.enqueue(7);
        // System.out.println(list.front.data);
        // System.out.println(list.front.next);
        // System.out.println(list.rear.data);
        // System.out.println(list.rear());
        // list.enqueue(3);
        // System.out.println(list.front());
        // System.out.println(list.rear());
        // list.enqueue(2);
        // System.out.println(list.front());
        // System.out.println(list.rear());
        // list.printQ();
        list.dequeue();
        list.dequeue();
        list.dequeue();
        // System.out.println(list.front.data);
        // System.out.println(list.front.next);
        System.out.println(list.rear());
        // list.dequeue();
        // list.printQ();
        // System.out.println(list.size);

    }
}