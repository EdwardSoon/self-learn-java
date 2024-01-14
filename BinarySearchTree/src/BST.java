/* IMPLEMENTATION OF BINARY SEARCH TREE
Attributes:
 - Node rootNode;
  -- inside each Node:
    --- int data
    --- Node left
    --- Node right
Behaviours:
 - insert
 - delete
 - search / check element existence

Extra: handle same value

 */

public class BST {
    Node root;
    static class Node {    // static: because no matter what BST object is instantiated, it is always in this way
        int data;
        Node left;
        Node right;
        Node (int d){
            data = d;
            left = null;
            right = null;
        }
    }

    /* binaryInsert method
     - for traversing binary from the Root node to the final node
     - for inserting mainly (also possibly used at other method)
     - will return the final Node (for all the recursive function calls to exit)
     */
    private Node binaryInsert(Node curr_node, int data){

        if (data > curr_node.data){
            if (curr_node.right != null){
                return binaryInsert(curr_node.right, data); // return to have an exit for the current method call on stack memory
                // the above means returning the result of binaryInsert(curr_node.right,data) recursively
                // by recursive which eventually will reach the base case and return as `curr_node` for all the recursive function calls returns to exit all function calls
            }
            else{
                return curr_node;   // mostBottom node (final node) is returned
            }
        }
        else {  //  data <= curr_node.data
            if (curr_node.left != null){
                return binaryInsert(curr_node.left, data);
            }
            else{
                return curr_node; // mostBottom node (final node) is returned
            }
        }
//        return curr_node;
    }

    public void insert(int data){
        Node new_node = new Node(data);
        if (this.root == null){
            this.root = new_node;
            return;
        }
        // always start from root to check, as we need to make all the values that are less than root.data to the left subtree, and bigger one to the right subtrees.
        Node currFinalNode = binaryInsert(this.root, data); // division from root to curr final node
        // since the curr_node returned is the final node, we need to place the new node to either its right or left
        if (new_node.data > currFinalNode.data){
            currFinalNode.right = new_node;
        }
        else{ // new_node.data <= currFinalNode.data ; for the consistent approach as above on handling the same value
            currFinalNode.left = new_node;
        }
    }

    private Node binaryFind(Node currNode, int data){
        // base case
        if (data == currNode.data){
            System.out.println(currNode.data + " exists.");
            return currNode;
        }

        if (data > currNode.data){
            if (currNode.right != null){
                return binaryFind(currNode.right, data);
            }
            System.out.println(data + " not found.");
            return currNode;

        }
        else{ // data <= currNode.data
            if (currNode.left != null){
                return binaryFind(currNode.left, data);
            }
            System.out.println(data + " not found.");
            return currNode;
        }

    }

    public void find(int data){
        binaryFind(this.root, data);
    }

    public static void main(String[] args) {
        BST test = new BST();

        test.insert(2);
        test.insert(6);
        test.insert(4);
        test.insert(1);
        test.insert(3);
        test.insert(7);
        test.insert(8);
        test.insert(0);
        test.insert(-1);

//        System.out.println("root:" + test.root.data);
//        System.out.println("r:" + test.root.right.data);
//        System.out.println("rl:" + test.root.right.left.data);
//        System.out.println("rll:" + test.root.right.left.left.data);
//        System.out.println("l:" + test.root.left.data);
//        System.out.println("rr:" + test.root.right.right.data);
//        System.out.println("rrr:" + test.root.right.right.right.data);
//        System.out.println("ll:" + test.root.left.left.data);
        test.find(1);
        test.find(0);
        test.find(-1);
        test.find(2);
        test.find(3);
        test.find(4);
        test.find(5);
        test.find(6);
        test.find(7);
        test.find(8);
        test.find(9);
        test.find(10);

    }
}