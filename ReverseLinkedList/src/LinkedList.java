public class LinkedList {
    // attribute
    private Node head;

    class Node{
        // attribute
        int data;
        Node next;

        //constructor
        Node(int d){
            data = d;
            next = null;
        }
    }

    public void add(int data){
        Node new_node = new Node(data);
        // base case
        if (head == null) {
            head = new_node;
            return;
        }
        Node last = head;
        while (last.next != null){
            last = last.next;
        }
        last.next = new_node;

    }

    // use stacks ? -- can also
    public void reverse(){
        // base case
        Node curr = head;
        Node prev = null;
        Node next = null;   // set this = null first

        while(curr != null){
            next = curr.next; // initiate next here -- for each new loop, check curr is null or not first, if not then only can assign next to curr.next
            curr.next = prev;
            prev = curr;
            curr = next;
//          next = next.next;  // cannot use this, as at the end it will result NullPointerException error as the last.next = null, and we are trying to access last.next.next which
        }

        head = prev; // reset head to another end (note: it is prev as it traversed in the final loop)
    }

    public static void printList(LinkedList list){
        Node currNode = list.head; // instantiate the currNode for the current Node

        System.out.print("LinkedList: ");

        while(currNode != null){    // as long as there are Nodes being instantiated then it will print the currNode data
            System.out.print(currNode.data + " ");  // print the current Node data
            currNode = currNode.next;   // to traverse to the next Node
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(10);
        ll.add(3);
        ll.add(2);
        ll.add(9);
        ll.add(0);
        ll.add(4);
        printList(ll);
        ll.reverse();
        printList(ll);

    }
}