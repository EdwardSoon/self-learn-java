// IMPLEMENTATION OF STACK USING ARRAY

/* ArrayStack attributes: 
 * 1. Array
 * 2. size of stack 
 * 3. top == only have access to the top
 * 4. capacity of the array to be initiated
 */

/* ArrayStack behaviours: 
 * - pop() = take out the first element
 * - push() = insert element to the first element
 */


import java.util.HashMap;
public class ArrayStack {
    // attributes
    private int[] stack;
    private int top;
    private int size;
    private final int capacity;

    // constructor method
    ArrayStack(int capacity){
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.top = -1;
        this.size = 0;
    }

    public void push(int data){
        if(isFull()){
            System.out.println("The stack is overflow");
            return;
        }
        top = (top+1)%capacity; // to keep going back around the index given the array's capacity
        stack[top] = data;
        System.out.println(stack[top] + " is added to the stack.");
        size ++;
    }

    public void pop(){
        if(isEmpty()){
            System.out.println("The stack is underflow. Nothing to be popped.");
            return;
        }
        System.out.println(stack[top] + " is removed.");
        top = ((((top-1)%capacity)+capacity)%capacity); // to keep going back around the index given the array's capacity even if it is negative
        size --;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public boolean isFull(){
        return this.size == this.capacity;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Stack is empty");
            return Integer.MIN_VALUE;
        }
        return stack[top];
    }


    public void printStack(){
        if(isEmpty()){
            System.out.println("The stack is empty");
            return;
        }
        int temp_size = size;
        int temp_top = top;
        System.out.print("The stack: ");
        while (temp_size > 0) {
            System.out.print(stack[temp_top] + " ");
            temp_size --;
            temp_top = ((((temp_top-1)%capacity)+capacity)%capacity);
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        ArrayStack s = new ArrayStack(3);

        s.push(4);
        // System.out.println(s.top);
        s.push(2);
        // System.out.println(s.top);
        s.push(6);
        // System.out.println(s.top);
        // s.push(10);
        s.pop();
        s.pop();
        // s.pop();
        // System.out.println(s.top + " " + s.size + " " + s.capacity);
        s.push(8);
        s.push(10);
        s.push(99);
        s.push(13);
        s.printStack();
        s.pop();
        // s.pop();
        // s.pop();
        System.out.println(s.peek());

    }
}
