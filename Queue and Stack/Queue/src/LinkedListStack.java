// IMPLEMENTATION OF STACK USING LINKEDLIST

/* LinkedList Stack attributes: 
 * 1. top node (for pop and push): with data and next node
 * 2. size of the stack
 */

/* LinkedList behaviours
 * - push
 * - pop 
 */

public class LinkedListStack {
    // attributes
    NodeS top; 
    int size;

    private class NodeS{
        int data;
        NodeS next;

        NodeS(int data){
            this.data = data;
            this.next = null;
        }
    }

    public LinkedListStack(){       // public so that other classes can access to the instantiation of the object using this class
        this.top = null;
        this.size = 0;
    }


    /* 
     * it seems if we stack it to next and next it is hardly to remove for pop as the latest will not have access to the previous (since it is a singly linkedlist)
     * instead, we make it reversed the connection direction (where next points to first in ones instead of pointing next to last in ones), stack it to previous and previous: new_node.next will be the previous top and new_node will be topped
     */
    public void push(int data){
        NodeS new_node = new NodeS(data);

        if(isEmpty()){
            top = new_node;
            size ++;
            System.out.println(new_node.data + " is added to the stack. Current top is: " + top.data);
            return;
        }

        new_node.next = top;    // new_node.next = previous top
        top = new_node;         // current top = new_node
        size ++; 
        System.out.println(new_node.data + " is added to the stack. Current top is: " + top.data);
    }

    /* 
     * we popped the first element too then move to next (previous element)
     */
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack underflow");
            return Integer.MIN_VALUE;
        }
        int removed = this.peek();
        System.out.println(top.data + " is removed from the stack.");
        // make top to the previous element 
        top = top.next;         // current top to top.next
        size --;
        return removed;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Stack is empty.");
            return Integer.MIN_VALUE;
        }
        return top.data;
    }

    public void printStack(){
        int temp_size = size;
        NodeS temp_top = top;
        
        System.out.print("The stack: ");
        while (temp_size > 0) {
            System.out.print(temp_top.data + " ");
            temp_size -- ;
            temp_top = temp_top.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkedListStack lls = new LinkedListStack();
        lls.push(8);
        lls.push(3);
        lls.push(9);
        lls.pop();
        lls.pop();
        lls.push(5);
        lls.push(10);
        lls.pop();
        System.out.println(lls.peek());
        lls.printStack();
        // lls.pop();
        // lls.pop();
        // lls.pop();
        System.out.println("size: " + lls.size);
    }
}
