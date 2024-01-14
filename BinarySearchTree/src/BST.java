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

    // for traversing binary from the Root node to the final node
    // can be applied for all other operations
    // will return the final Node
    private Node binaryDivision(Node curr_node, int data){

        if (data > curr_node.data){
            if (curr_node.right != null){
                return binaryDivision(curr_node.right, data);
                // the above means returning the result of binaryDivision(curr_node.right,data) recursively
                // by recursive which eventually will reach the base case and return as `curr_node` for all the recursive function calls returns to exit all function calls
            }
            else{
                return curr_node;
            }
        }
        else {  //  data <= curr_node.data
            if (curr_node.left != null){
                return binaryDivision(curr_node.left, data);
            }
            else{
                return curr_node;
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
        Node currFinalNode = binaryDivision(this.root, data); // division from root to curr final node
        // since the curr_node returned is the final node, we need to place the new node to either its right or left
        if (new_node.data > currFinalNode.data){
            currFinalNode.right = new_node;
        }
        else{ // new_node.data <= currFinalNode.data ; for the consistent approach as above on handling the same value
            currFinalNode.left = new_node;
        }
    }

//    public void find(int data){
//        // base case
//        if (data == this.root.data){
//            System.out.println(this.root.data + " exists.");
//            return;
//        }
//
//        Node currFinalNode = binaryDivision(this.root, data);
//
//        if (data > temp.data){
//
//        }
//
//        else if (data < temp.data){
//
//        }
//
//    }

    public static void main(String[] args) {
        BST test = new BST();

        test.insert(2);
        test.insert(6);
        test.insert(4);
//        test.insert(1);
//        test.insert(3);
//        test.insert(7);
//        test.insert(8);
//        test.insert(0);

        System.out.println("root:" + test.root.data);
        System.out.println("r:" + test.root.right.data);
//        System.out.println("rl:" + test.root.right.left.data);
//        System.out.println("rll:" + test.root.right.left.left.data);
//        System.out.println("l:" + test.root.left.data);
//        System.out.println("rr:" + test.root.right.right.data);
//        System.out.println("rrr:" + test.root.right.right.right.data);
//        System.out.println("ll:" + test.root.left.left.data);

    }
}