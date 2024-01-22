// https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/

import java.awt.*;
import java.util.ArrayList;

// it seems it is better to have non-static to implement as most methods are object specific based
public class LinkedListG<E>{

    Node<E> head; // declare head Node of the list (as the object of Node and instance field/attributes of LinkedList) (using the class Node down there)

    // sub-class (nested class) --
    // need sub-class because in an object of list it consists of Nodes objects.
    // created as a static class so that main can access it without object
    static class Node<E>{
        E data;
        Node<E> next;  // object of Node for the next Node

        // constructor
        Node(E d){
            data = d;
            next = null; // default the next node is a null -- no need to declare also can, because it hasnt been instantiated so it is null
        }
    }

    // method to insert a node
        /*
         * create a new node with given data
         * if the LinkedList is empty then make a new_node there
         * else traverse to the last node 
         * make a new_node there 
         * return list (since it is LinkedList object to return for the insert method)
         */
    public void insert(E data){  // we shouldn't provide
        // create a new node with given data
        Node<E> new_node = new Node<>(data);
        System.out.println(data);
        // traverse to the last node and add the new_node there
        if (this.head == null){     // if the head Node is null means it hasnt been instantiated anything, else it will have an address (not the data, to access the data it needs to be list.head.data)
            this.head = new_node;   // thus we assigned the instantiated object to the head node, that way head node is in
        }
        else {
            Node<E> last = this.head; // declare a Last Node: this is necessary for traverse, if not we cant identify the last node
            while(last.next != null){ // traverse process
                last = last.next;   // if it is not null then last node will == next node
            }
            last.next = new_node;        // until it is last.next == null then we will have new_node assigned to last.next node (NOT last node, if not it will replace the last node but not inserting to the last.next node)
        }
        
    }

    // print list method
    // a static method thus we need to declare the working Generic Type (as it doesnt infer from Generic Class)
    // (Note that: I purposely changed to T instead of using E, so that we show that this Method Generic declaration is on its own, not inferred by class)
    public static <T> void printList(LinkedListG<T> list){
        Node<T> currNode = list.head; // instantiate the currNode for the current Node

        System.out.print("LinkedList: ");

        while(currNode != null){    // as long as there are Nodes being instantiated then it will print the currNode data
            System.out.print(currNode.data + " ");  // print the current Node data
            currNode = currNode.next;   // to traverse to the next Node
        }
        System.out.println();
    }


    // deletion method : first key occurence will be deleted
    /* 
        1. if meet key == node.data then delete
        2. if it the node to be deleted is head node then replace the next node to the head node (no need to move everything up as they are linked to the next node of the curr node)
        3. if it is middle node to be deleted, then replace the next node to the current node
        4. if it is the last node to be deleted, then made the prev node of its next node to be null 
        5. need the process of traverse to check the conditions
    */
    public void deleteByKey(E key){
        
        Node<E> currNode = this.head;
        Node<E> prev = null;

        // Case 1: head node.data == key
        if (currNode != null && currNode.data.equals(key)){     // use the equals as key is an object now instead of a primitive type
            this.head = currNode.next;
            System.out.println(key + " found and deleted");
            return;
        }

        // Case 2: middle node.data or Last node data== key
        while (currNode != null && !currNode.data.equals(key)){       // while currNode exists AND currNode data != key
            prev = currNode;                // store into previous node so that later can replace it 
            currNode = currNode.next;       // currNode traverse to the next Node
        }
        if (currNode != null){  // ensure currNode is not null that means key is found (as null doesnt have data)
            prev.next = currNode.next; // to replace the linked of previous Node's next to the current Node with the next of the CurrentNode
            System.out.println(key + " found and deleted");
            return;
        }
        // the key is not found
        System.out.println("No key found");
    }


    // Method: Given a ‘position’, delete the node at this position from the linked list.
        /* 
        * same as above deletion method: got three cases
            1. head
            2. middle
            3. last
        * since there it is identified through index, thus we need to get index as parameter and we need to count our own index as it hits then will perform the same operations
        */
    public void deleteByNum(int i){
        Node<E> currNode = this.head;
        Node<E> prev = null;
        int index = 0;

        // Case 1: head node.data == key
        if (currNode != null && index == i){
            this.head = currNode.next;
            System.out.println(i + " position element deleted");
            return;
        }
        
        // Case 2: middle node.data == key or last node.data == key
        while (currNode != null && index != i){       // while currNode exists AND currNode data != key
            prev = currNode;                // store into previous node so that later can replace it 
            currNode = currNode.next;       // currNode traverse to the next Node
            index ++;
        }
        if (currNode != null){  // ensure it is not null, then it must be the index == i
            prev.next = currNode.next; // to replace the linked of previous Node's next to the current Node with the next of the CurrentNode
            System.out.println(i + " position element deleted");
            return;
        }
        
        System.out.println("Index is beyond the bound");
    }

    public static void main(String[] args) throws Exception {
        LinkedListG<String> ll = new LinkedListG<>();
        ll.insert("hi");
        ll.insert("world");
        ll.insert("me");
        printList(ll);
//        ll.deleteByKey("hi");
        LinkedListG.<String>printList(ll);
//        ll.deleteByKey("world");
        printList(ll);
//        ll.deleteByKey("me");
        printList(ll);
        ll.deleteByNum(2);
        printList(ll);


        // System.out.println(LinkedList.insert(ll, 12));
//        insert(ll, 111);
//        insert(ll, 299);
//        LinkedListG.insert(ll, 7123);
//        LinkedListG.insert(ll, 1234)
        
        // LinkedList.insert(ll, 23);
        // System.out.println(ll);
        // if (ll.head != null){
        //     System.out.println("non_empty");
        // }
//        printList(ll);
//
//        deleteByKey(ll, 111);
//        printList(ll);
//
//        deleteByNum(ll, 0);
//        printList(ll);

//        // what is the difference?
//        ArrayList<Integer[]> al = new ArrayList<>();
//        ArrayList<int[]> hi = new ArrayList<>();
//
//        al.add(new Integer[]{2,3,4});
//        hi.add(new int[]{2,3,5});
        
        


    }
}
