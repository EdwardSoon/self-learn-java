// https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/

// it seems it is better to have non-static to implement as most methods are object specific based
public class LinkedList {

    Node head; // declare head Node of the list (as the object of Node and instance field/attributes of LinkedList) (using the class Node down there)

    // sub-class (nested class) -- need sub-class because in an object of list it consists of Nodes objects.
    // created as a static class so that main can access it without object
    static class Node{
        int data;   
        Node next;  // object of Node for the next Node

        // constructor
        Node(int d){
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
    public static LinkedList insert(LinkedList list , int data){
        // create a new node with given data
        Node new_node = new Node(data);

        // traverse to the last node and add the new_node there
        if (list.head == null){     // if the head Node is null means it hasnt been instantiated anything, else it will have an address (not the data, to access the data it needs to be list.head.data)
            list.head = new_node;   // thus we assigned the instantiated object to the head node, that way head node is in
        }
        else {
            Node last = list.head; // declare a Last Node: this is necessary for traverse, if not we cant identify the last node
            while(last.next != null){ // traverse process
                last = last.next;   // if it is not null then last node will == next node
            }
            last.next = new_node;        // until it is last.next == null then we will have new_node assigned to last.next node (NOT last node, if not it will replace the last node but not inserting to the last.next node)
        }
        
        return list;
    }

    // print list method 
    public static void printList(LinkedList list){
        Node currNode = list.head; // instantiate the currNode for the current Node

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
    public static LinkedList deleteByKey(LinkedList list, int key){ 
        
        Node currNode = list.head;
        Node prev = null; 

        // Case 1: head node.data == key
        if (currNode != null && currNode.data == key){
            list.head = currNode.next;
            System.out.println(key + " found and deleted");
            return list;
        }

        // Case 2: middle node.data == key
        while (currNode != null && currNode.data != key){       // while currNode exists AND currNode data != key
            prev = currNode;                // store into previous node so that later can replace it 
            currNode = currNode.next;       // currNode traverse to the next Node
        }
        if (currNode.next != null && currNode.data == key){  // ensure it is not the last node && key found
            prev.next = currNode.next; // to replace the linked of previous Node's next to the current Node with the next of the CurrentNode
            System.out.println(key + " found and deleted");
            return list;
        }
        
        // Case 3: last node.data == key
        if (currNode.next == null && currNode.data == key){   // last node will have no next node && key found
            prev.next = null;              // thus the previous node's next node replace with null and cut the link
            System.out.println(key + " found and deleted");
            return list;
        }
        
        System.out.println("No key found");
        return list;
    }


    // Method: Given a ‘position’, delete the node at this position from the linked list.
        /* 
        * same as above deletion method: got three cases
            1. head
            2. middle
            3. last
        * since there it is identified through index, thus we need to get index as parameter and we need to count our own index as it hits then will perform the same operations
        */
    public static LinkedList deleteByNum(LinkedList list, int i){
        Node currNode = list.head;
        Node prev = null; 
        int index = 0;

        // Case 1: head node.data == key
        if (currNode != null && index == i){
            list.head = currNode.next;
            System.out.println(i + " position element deleted");
            return list;
        }
        
        // Case 2: middle node.data == key
        while (currNode != null && index != i){       // while currNode exists AND currNode data != key
            prev = currNode;                // store into previous node so that later can replace it 
            currNode = currNode.next;       // currNode traverse to the next Node
            index ++;
        }
        if (currNode != null && currNode.next != null && index == i){  // ensure it is not the last node && key found
            prev.next = currNode.next; // to replace the linked of previous Node's next to the current Node with the next of the CurrentNode
            System.out.println(i + " position element deleted");
            return list;
        }
        
        // Case 3: last node.data == key
        if (currNode != null && currNode.next == null && index == i){   // last node will have no next node && key found
            prev.next = null;              // thus the previous node's next node replace with null and cut the link
            System.out.println(i + " position element deleted");
            return list;
        }
        
        System.out.println("Index is beyond the bound");
        return list;

    }

    public static void main(String[] args) throws Exception {
        LinkedList ll = new LinkedList();
        // System.out.println(ll);

        // System.out.println(LinkedList.insert(ll, 12));
        insert(ll, 111);
        insert(ll, 299);
        LinkedList.insert(ll, 7123);
        LinkedList.insert(ll, 1234);
        // LinkedList.insert(ll, 23);
        // System.out.println(ll);
        // if (ll.head != null){
        //     System.out.println("non_empty");
        // }
        printList(ll);

        deleteByKey(ll, 111);
        printList(ll);

        deleteByNum(ll, 0);
        printList(ll);
    }
}
