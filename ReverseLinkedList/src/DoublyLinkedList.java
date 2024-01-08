public class DoublyLinkedList {
    Node head;
    Node tail;
    class Node{
        int data;
        Node next;
        Node prev;

        Node (int d){
            data = d;
            next = null;
            prev = null;
        }
    }

    public void insert(int data){
        Node new_node = new Node(data);

        if(head == null){
            head = new_node;
            tail = new_node;
            return;
        }
        // else then define last Node then traverse
        Node last = head;
        while (last.next != null){
            last = last.next;
        }
        last.next = new_node;
        new_node.prev = last; // this has higher accuracy and cleaner (vs last.next.prev = last;) as it directly appoint new_node.prev = last;
        tail = new_node;
    }

    public void reverseMyCode(){
        Node currNode = head;
        Node prevNode = null;
        Node nextNode = null;
        tail = head;

        while (currNode != null){
            nextNode = currNode.next;
            currNode.next = prevNode;
            currNode.prev = nextNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        head = prevNode;
    }

    public void reverse(){
        Node temp = null;
        Node current = head;

        while (current != null){
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; // to traverse to next node
        }

        if(temp != null){       // if temp is null then cant access its prev
            head = temp.prev;   // this is the end node which to be head now after reversing
        }

    }

    public void printList(int order){    // order == 0 asc ; order == 1 desc
        if(head == null){
            System.out.println("Nothing in the list");
            return;
        }

        Node temp;
        if (order == 0){
            System.out.print("List asc: ");
            temp = head;
            while (temp != null){
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
        else if (order == 1) {
            System.out.print("List desc: ");
            temp = tail;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.prev;
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        DoublyLinkedList l = new DoublyLinkedList();
        l.insert(9);
        l.insert(8);
        l.insert(7);
        l.insert(6);
        l.insert(5);
        l.printList(0);
//        System.out.println("tail:" + l.tail.data + "; head:" + l.head.data );
        l.reverseMyCode();
        l.printList(0);
        l.printList(1);
        System.out.println("tail:" + l.tail.data + "; head:" + l.head.data );

    }


}
