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

    /* binaryInsertMyCode method
     - for traversing binary from the Root node to the final node
     - for inserting mainly (also possibly used at other method)
     - will return the final Node (for all the recursive function calls to exit)
     */
    /* Things learned: (refer to insert() vs insetMyCode() )
      - if (curr_node.right/left != null) can turn into curr_node == null as base case instead, since the right/left will be curr_node on the next call
      - can utilise how every call of functions returned is actually very useful, instead of just the final return results to replace all the calls' results (which is what i did here)
      In short, chatgpt says both are equally efficient, but mine is more readable.
     */
    private Node binaryInsertMyCode(Node curr_node, int data){

        if (data > curr_node.data){
            if (curr_node.right != null){
                return binaryInsertMyCode(curr_node.right, data); // return to have an exit for the current method call on stack memory
                // the above means returning the result of binaryInsertMyCode(curr_node.right,data) recursively
                // by recursive which eventually will reach the base case and return as `curr_node` for all the recursive function calls returns to exit all function calls
            }
            else{
                return curr_node;   // mostBottom node (final node) is returned
            }
        }
        else {  //  data <= curr_node.data
            if (curr_node.left != null){
                return binaryInsertMyCode(curr_node.left, data);
            }
            else{
                return curr_node; // mostBottom node (final node) is returned
            }
        }
//        return curr_node;
    }

    public void insertMyCode(int data){
        Node new_node = new Node(data);
        if (this.root == null){
            this.root = new_node;
            return;
        }
        // always start from root to check, as we need to make all the values that are less than root.data to the left subtree, and bigger one to the right subtrees.
        Node currFinalNode = binaryInsertMyCode(this.root, data); // division from root to curr final node
        // since the curr_node returned is the final node, we need to place the new node to either its right or left
        if (new_node.data > currFinalNode.data){
            currFinalNode.right = new_node;
        }
        else{ // new_node.data <= currFinalNode.data ; for the consistent approach as above on handling the same value
            currFinalNode.left = new_node;
        }
    }

    private Node insertBinary(Node currNode, int data){
        Node new_node = new Node(data);

        if (currNode == null){
            return new_node; // return new_node when reached node == null -- so this is the final node
        }

        if (data > currNode.data){
            currNode.right = insertBinary(currNode.right, data);  // currNode.right calls own method to get its value in the next call. If the node.right is null then we will assign new_node; If node.right already existed, then we will return node.right, (before that we will call recursively if we havent find the node that is null).
        }
        else if (data < currNode.data){
            currNode.left = insertBinary(currNode.left, data);
        }
        return currNode;   // if currNode exists, then returned the original assigned value
        // it will still return this.root node if there exists this.root OR it returns back to node.right/left for node.right/left if it already existed
    }

    public void insert(int data) {
        this.root = insertBinary(this.root, data);  // thus here we can assign rootNode to the returned value
    }

    /* binaryFind
     - traversing the node from the input Node by using the data input
     - to find the target data, if found then return the Node (not necessarily final Node)
     - if non-exist then return final Node
     */
    private Node binaryFind(Node currNode, int data){
        // base case
        if (data == currNode.data){
//            System.out.println(data + " exists.");
            return currNode;
        }

        if (data > currNode.data){
            if (currNode.right != null){
                return binaryFind(currNode.right, data);
            }
//            System.out.println(data + " not found.");
            return null;

        }
        else{ // data <= currNode.data
            if (currNode.left != null){
                return binaryFind(currNode.left, data);
            }
//            System.out.println(data + " not found.");
            return null;
        }

    }

    public boolean find(int data){
        return binaryFind(this.root, data) != null; // this is better than binaryFind(this.root, data).data == data;
    }

    // traverse and having tracked previous node in order to adjust the prev node connection
    private Node binaryDel (Node prevNode, Node currNode, int dataDel){
        if (currNode.data == dataDel){
            if (currNode.right != null && currNode.left != null){
                if(currNode.data > prevNode.data){
                    prevNode.right = currNode.left;
                }
                else{ // currNode.data <= prevNode.data
                    prevNode.left = currNode.left;
                }
                // move the entire subtree to the most bottom of the other subtree
                // in the code below, move the entire toDelNode.right to the bottom of toDelNode.left
                Node last = binaryInsertMyCode(currNode.left, currNode.right.data);
                last.right = currNode.right;
            }
            else if (currNode.right != null){
                if(currNode.data > prevNode.data){
                    prevNode.right = currNode.right;
                }
                else { // currNode.data <= prevNode.data
                    prevNode.left = currNode.right;
                }
            }
            else if (currNode.left != null){
                if(currNode.data > prevNode.data){
                    prevNode.right = currNode.left;
                }
                else { // currNode.data <= prevNode.data
                    prevNode.left = currNode.left;
                }
            }
            else{
                if(currNode.data > prevNode.data){
                    prevNode.right = null;
                }
                else { // currNode.data <= prevNode.data
                    prevNode.left = null;
                }
            }
            return currNode;
        }

        else if (dataDel > currNode.data){
            if (currNode.right != null){
                return binaryDel(currNode, currNode.right, dataDel);
            }
            return null;

        }
        else { //dataDel < currNode.data
            if (currNode.left != null) {
                return binaryDel(currNode, currNode.left, dataDel);
            }
            return null;
        }
    }

    /* delete
     - can use find to try to find if the element is in it from root
     - if it is then do operation as follows:
        -- if the root.data got deleted:
          a) move either left or right to be the root (thus the entire subtree is moved to become main tree)
          b) if moved left up to be root, then the right of the original root to be the current right of the most bottom element (as it is for sure larger than left); if moved right up then vice versa
        -- if middle data got deleted, check both sides, :
          a) if only one side null then pull the non-null one.
          b) if both side non-null:
            b.1) prev.right = toDel.right/left
            b.2) (same like root.data got deleted) then move the entire toDel.left/right to the most bottom of left/right
          c) if both side null, cna pull any
     */
    public void delete(int data){
        // root case
        if (this.root.data == data){
            if(root.right != null && root.left != null){
                // move the entire subtree (right in this case) to the bottom of the chosen connected subtree (in this case: left)
                Node last = binaryInsertMyCode(root.left, root.right.data);  // go to the most bottom by using the first node of the right subtree
                last.right = root.right;  // curr.right.data for sure > last.right.data
                root = root.left;   // root = second element and remove original root
            }
            // either left or right got null
            else if (root.left != null){
                root = root.left;
            }
            else if (root.right != null){ // (root.left == null) || (root.left == null && root.right == null)
                root = root.right;
            }
            else { // both null
                root = null;
            }
            return;
        }

        if (find(data)){
            if (data > root.data){
                binaryDel(root, root.right, data);
            }
            else { // data <= root.data
                binaryDel(root, root.left, data);
            }
        }
        else {
            System.out.println(data + " is not found.");
        }
    }

    public static void main(String[] args) {
        BST test = new BST();

//        test.insertMyCode(2);
//        test.insertMyCode(6);
//        test.insertMyCode(4);
//        test.insertMyCode(1);
//        test.insertMyCode(3);
//        test.insertMyCode(7);
//        test.insertMyCode(8);
//        test.insertMyCode(0);
//        test.insertMyCode(-1);

        test.insert(3);
        test.insert(1);
        test.insert(2);
        test.insert(5);
        test.insert(0);
        test.insert(-1);
        test.insert(7);
        test.insert(6);



        System.out.println("root:" + test.root.data);
        System.out.println("r:" + test.root.right.data + " root:" + test.root.data );
//        System.out.println("rl:" + test.root.right.left.data);
//        System.out.println("rll:" + test.root.right.left.left.data);
        System.out.println("l:" + test.root.left.data);
        System.out.println("rr:" + test.root.right.right.data);
//        System.out.println("rrr:" + test.root.right.right.right.data);
        System.out.println("ll:" + test.root.left.left.data);
        System.out.println("lr:" + test.root.left.right.data);
        System.out.println("lll:" + test.root.left.left.left.data);
        System.out.println("rrl:" + test.root.right.right.left.data);
//        System.out.println(test.find(1));
//        System.out.println(test.find(0));
//        System.out.println(test.find(-1));
//        System.out.println(test.find(2));
//        System.out.println(test.find(3));
//        System.out.println(test.find(4));
//        System.out.println(test.find(5));
//        System.out.println(test.find(6));
//        System.out.println(test.find(7));
//        System.out.println(test.find(8));
//        System.out.println(test.find(9));
//        System.out.println(test.find(10));
//
//        test.delete(3);
//        System.out.println(test.find(2));
//        test.delete(2);
//        System.out.println(test.find(2));

    }
}