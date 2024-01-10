/* IMPLEMENTATION OF HASHTABLE: SEPARATE CHAINING
   Characteristics:
   - resizable array (yet to be done)
   - can handle collisions (separate chaining)
   Main Operations:
   - add key-value pairs
   - retrieve through key
   - delete through key or value
   Sub Operations:
   - calculate Load Factor
 */



import java.util.Arrays;
import java.util.NoSuchElementException;


public class HashTable {
    // attributes
    Node[] arrNode;
    int capacity; // array max size

    HashTable(int capacity) {
        this.capacity = capacity;
        arrNode = new Node[this.capacity];
    }

    class Node {
        Integer key;
        Integer data;
        Node next;

        Node(int k, int d) {
            key = k;
            data = d;
            next = null;
        }
    }

    public int hashFunction(int key) {
        return (((key % this.capacity) + this.capacity) % this.capacity);  // to make sure it wont go negative
    }

    public void add(int key, int value) {
        Node new_node = new Node(key, value);
        int index = hashFunction(key);

        if (arrNode[index] == null) {
            arrNode[index] = new_node;  // first node
            return;
        }
        // if there is first node
        Node curr = arrNode[index]; // define currNode for the head of the index; for traverse -- Note: if define above before the first Node being added, then it will result error
        //traverse until .next is null
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new_node; // add new node to .next until it traverse to .next is null
    }

    public Integer get(int key) {
        int index = hashFunction(key);

        if (arrNode[index] == null) {
//            throw new NoSuchElementException();  -- better to use this one
            return null;
        }

        Node curr = arrNode[index];
        while (curr.next != null && !curr.key.equals(key)) {
            curr = curr.next;
        }
        if (curr.key.equals(key)) {
            return curr.data;
        }
        System.out.print("Does not have this key: " + key + " ");
        return null;
    }

    public void deleteByKey(int key) {
        int index = hashFunction(key);
        if (arrNode[index] == null) {
            System.out.println("Nothing to be deleted");
            return;
        }

        Node curr = arrNode[index];
        Node prev = null;
        while (curr.next != null && !curr.key.equals(key)) {
            prev = curr;
            curr = curr.next;
        }
        if (curr.key.equals(key)) {
            prev.next = curr.next;
        }
    }

    // to improve to printing key-value
    public void printHashTable() {
        for (Node node : arrNode) {
            if (node == null) {
                continue;
            }
            Node temp = node;
            while (temp != null) {
                System.out.print(temp.key + "=" + temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        HashTable ht = new HashTable(5);
//        System.out.println(ht.get(14));
        ht.add(14, 140);
        ht.add(12, 120);
        ht.add(15, 150);
        ht.add(20, 200);
        ht.add(0, 10);
        ht.add(4, 40);
        ht.add(2, 30);
        System.out.println(ht.get(14));
        System.out.println(ht.get(15));
        System.out.println(ht.get(20));
        ht.deleteByKey(20);
        System.out.println(ht.get(20));
        ht.deleteByKey(14);
        ht.printHashTable();
    }
}