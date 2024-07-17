/* IMPLEMENTATION OF HASHTABLE: SEPARATE CHAINING
   Characteristics:
   - resizable array
   - can handle collisions (separate chaining)
   Main Operations:
   - add key-value pairs
   - retrieve through key
   - delete through key or value
   Sub Operations:
   - calculate Load Factor
 */


import java.awt.desktop.SystemEventListener;

public class HashTableSC {
    // attributes
    Node[] arrNode;
    int capacity; // array max size

    HashTableSC(int capacity) {
        this.capacity = capacity;
        arrNode = new Node[this.capacity];
    }

    static class Node {
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
//        System.out.println("The current Load Factor: " + loadFactor(this));  // optional to keep track of the load factor
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

        //base case
        if (arrNode[index].key.equals(key)){
            arrNode[index] = arrNode[index].next;
            return;
        }

        Node prev = arrNode[index];
        Node curr = prev.next;

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
            if(node == null){
                System.out.println("null");
                continue;
            }
            while (node != null) {
                System.out.print(node.key + "=" + node.data + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    public static double loadFactor(HashTableSC ht){
        double count = 0;
        double arrLen = ht.arrNode.length;
        for (Node node: ht.arrNode){
            while(node != null){
                count ++;
                node = node.next;
            }
        }
        return count / arrLen;
    }

    // we will need to rehash no matter the capacity grows or shrinks because we want them to more uniformly allocate
    // previous capacity might not have a good uniform hashing
    public void resize(int newCapacity){
        Node[] newArr = new Node[newCapacity];
        this.capacity = newCapacity; // to use the new hash function, capacity needs to be changed first
        int newIndex;
        for (Node node : this.arrNode){
            while (node != null){
                newIndex = hashFunction(node.key);  // rehashing for every key-value pairs
                Node next = null;
                // add the same way as Separating Chain (the way that linkedlist will be added)
                if(newArr[newIndex] == null){
                    newArr[newIndex] = node;
                    next = node.next;
                    newArr[newIndex].next = null;      // disconnect the next node, rehashing might allocate to different index
                }
                else{
                    Node temp = newArr[newIndex];
                    while(temp.next != null){
                        temp = temp.next;
                    }
                    temp.next = node;
                }
                node = next;
            }
        }
        this.arrNode = newArr;          // to let every instantiated instance to be able to access new resized Array
    }


    public static void main(String[] args) {
        HashTableSC ht = new HashTableSC(5);
//        System.out.println(ht.get(14));
        ht.add(14, 140);
        ht.add(12, 120);
        ht.add(15, 150);
        ht.add(20, 200);
        ht.add(0, 10);
        ht.add(4, 40);
        ht.add(2, 30);
//        System.out.println(ht.get(14));
//        System.out.println(ht.get(15));
//        System.out.println(ht.get(20));
        ht.deleteByKey(20);
//        System.out.println(ht.get(20));
//        ht.deleteByKey(14);
        ht.printHashTable();
//        System.out.println();
//        System.out.println("After resize:");
//        System.out.println(loadFactor(ht));

//        ht.resize(2);
//        ht.printHashTable();

    }
}