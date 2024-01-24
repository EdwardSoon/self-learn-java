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
 - traverse min to max and max to min
 - count leaves nodes
 - count no of nodes of kth layer
 - count

Extra: handle same value

 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BSTG<T extends Comparable<T>> {       // cannot implement with Comparable<Node<T>> here // we will use Comparable as it is natural ordering as we are traversing using currentNode
    // T is an object, thus it will adhere to the Comparable Interface's compareTo(T o) non-static method where it takes object as parameter
    // we can also use data to call the compareTo as data in Node is T type now (an object)
    Node<T> root;
    int leavesCounter;  // no recommended to have because layer are refreshed everytime we use the methods
    int layer;    // no recommended to have because layer are refreshed everytime we use the methods

    public static class Node<T> {    // static: because no matter what BST object is instantiated, it is always in this way
        // <T extends Comparable <T>> to guarantee that T data is in natural order thus can use .compareTo() method
        // <T implements Comparable <T>> doesnt work
        // optional to have extends Comparable <T> under Node
        T data;
        Node<T> left;
        Node<T> right;
        Node (T d){
            data = d;
            left = null;
            right = null;
        }

        // we dont have to define compareTo () method here as it is already defined in all the Generic Classes)
    }

    BSTG(){
        root = null;
        leavesCounter = 0;  // not so prefer to put as counter is not part of the attribute
        layer = 0; // reasonable to put, as it starts from 0 layer if no data at all for the layer
    }



    /* binaryInsertMyCode method
     - for traversing binary from the Root node to the final node
     - for inserting mainly (also possibly used at other method)
     - will return the final Node (for all the recursive function calls to exit)
     */
    /* Things learned: (refer to insert() vs insetMyCode() )
      - always think about how to deal with root case not by another statement but possibly together as a general as root here should be the base case
        -- if (curr_node.right/left != null) can turn into curr_node == null as base case instead, since the right/left will be curr_node on the next call
      - can utilise how every call of functions returned is actually very useful, instead of just the final return results to replace all the calls' results (which is what i did here)
      In short, chatgpt says both are equally efficient, but mine is more readable.
     */
    private Node<T> binaryInsertMyCode(Node<T> curr_node, T data){

        if (data.compareTo(curr_node.data) > 0){
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

    public void insertMyCode(T data){
        Node<T> new_node = new Node<>(data);
        if (this.root == null){
            this.root = new_node;
            return;
        }
        // always start from root to check, as we need to make all the values that are less than root.data to the left subtree, and bigger one to the right subtrees.
        Node<T> currFinalNode = binaryInsertMyCode(this.root, data); // division from root to curr final node
        // since the curr_node returned is the final node, we need to place the new node to either its right or left
        if (new_node.data.compareTo(currFinalNode.data) > 0){
            currFinalNode.right = new_node;
        }
        else{ // new_node.data <= currFinalNode.data ; for the consistent approach as above on handling the same value
            currFinalNode.left = new_node;
        }
    }

    private Node<T> insertBinary(Node<T> currNode, T data){
        Node<T> new_node = new Node<>(data);

        if (currNode == null){
            return new_node; // return new_node when reached node == null -- so this is the final node
        }

        if (data.compareTo(currNode.data) > 0){
            currNode.right = insertBinary(currNode.right, data);  // currNode.right calls own method to get its value in the next call. If the node.right is null then we will assign new_node; If node.right already existed, then we will return node.right, (before that we will call recursively if we havent findMyCode the node that is null).
        }
        else if (data.compareTo(currNode.data) < 0){
            currNode.left = insertBinary(currNode.left, data);
        }
        return currNode;   // if currNode exists, then returned the original assigned value
        // it will still return this.root node if there exists this.root OR it returns back to node.right/left for node.right/left if it already existed
    }

    public void insert(T data) {
        this.root = insertBinary(this.root, data);  // thus here we can assign rootNode to the returned value
    }

    /* binaryFindMyCode
     - traversing the node from the input Node by using the data input
     - to findMyCode the target data, if found then return the Node (not necessarily final Node)
     - if non-exist then return final Node
     */
    private Node<T> binaryFindMyCode2(Node<T> currNode, T data){
        // base case
        if(currNode == null){
            return null;    // not found
        }
        else if (data.compareTo(currNode.data) == 0){
            return currNode;
        }

        if (data.compareTo(currNode.data) > 0){
            return binaryFindMyCode2(currNode.right, data);
        }
        else{ // data <= currNode.data
            return binaryFindMyCode2(currNode.left, data);
        }
    }

    private Node<T> binaryFindMyCode(Node<T> currNode, T data){
        // base case
        if (data.compareTo(currNode.data) == 0){
            return currNode;
        }

        if (data.compareTo(currNode.data) > 0){
            if (currNode.right != null){
            return binaryFindMyCode(currNode.right, data);
            }
            return null;
        }
        else{ // data <= currNode.data
            if (currNode.left != null){
            return binaryFindMyCode(currNode.left, data);
        }
            return null;
        }
    }

    public boolean findMyCode(T data){
        return binaryFindMyCode(this.root, data) != null; // this is better than binaryFindMyCode(this.root, data).data == data;
    }

    // traverse and having tracked previous node in order to adjust the prev node connection
    private Node<T> binaryDelMyCode(Node<T> prevNode, Node<T> currNode, T dataDel){
        if (dataDel.compareTo(currNode.data)==0){
            if (currNode.right != null && currNode.left != null){
                if(currNode.data.compareTo(prevNode.data) > 0){
                    prevNode.right = currNode.left;
                }
                else{ // currNode.data <= prevNode.data
                    prevNode.left = currNode.left;
                }
                // move the entire subtree to the most bottom of the other subtree
                // in the code below, move the entire toDelNode.right to the bottom of toDelNode.left
                Node<T> last = binaryInsertMyCode(currNode.left, currNode.right.data);
                last.right = currNode.right;
            }
            else if (currNode.right != null){
                if(currNode.data.compareTo(prevNode.data) > 0){
                    prevNode.right = currNode.right;
                }
                else { // currNode.data <= prevNode.data
                    prevNode.left = currNode.right;
                }
            }
            else if (currNode.left != null){
                if(currNode.data.compareTo(prevNode.data) > 0){
                    prevNode.right = currNode.left;
                }
                else { // currNode.data <= prevNode.data
                    prevNode.left = currNode.left;
                }
            }
            else{
                if(currNode.data.compareTo(prevNode.data) > 0){
                    prevNode.right = null;
                }
                else { // currNode.data <= prevNode.data
                    prevNode.left = null;
                }
            }
            return currNode;
        }

        else if (dataDel.compareTo(currNode.data)>0){
            if (currNode.right != null){
                return binaryDelMyCode(currNode, currNode.right, dataDel);
            }
            return null;

        }
        else { //dataDel < currNode.data
            if (currNode.left != null) {
                return binaryDelMyCode(currNode, currNode.left, dataDel);
            }
            return null;
        }
    }

    /* delete
     - can use findMyCode to try to findMyCode if the element is in it from root
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
    public void deleteMyCode(T data){
        // root case
        if (this.root.data.compareTo(data) == 0){
            if(root.right != null && root.left != null){
                // move the entire subtree (right in this case) to the bottom of the chosen connected subtree (in this case: left)
                Node<T> last = binaryInsertMyCode(root.left, root.right.data);  // go to the most bottom by using the first node of the right subtree
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

        if (findMyCode(data)){
            if (data.compareTo(root.data)> 0){
                binaryDelMyCode(root, root.right, data);
            }
            else { // data <= root.data
                binaryDelMyCode(root, root.left, data);
            }
        }
        else {
            System.out.println(data + " is not found.");
        }
    }

    /* Lessons learned to be more efficient:
     - i am forced to only solve the left sub-tree because i began with `if(node.left != null);`
       -- i should have begun with the Root node itself, instead of starting with LEFT SUBTREE of Root, it will definitely be hard for right subtree from root later.
       -- setting the base case as `if(node!=null)` which checks for every node, if null then end the function
     - node = node.left; minToMaxLeftMyCode(node); can be simplified to just minToMaxLeftMyCode(node.left)

     */
    private void minToMaxLeftMyCode(Node<T> node){
        if (node.left != null){     // no need while because for every node propagated, it will check if there is left Node since we call the function again
            node = node.left;        // propagates to the left
            minToMaxLeftMyCode(node);         // reverse the order of output when it propagates to the end
            System.out.print(" " + node.data);      // from most left
            while (node.right != null){     // must be while (if cannot cover all cases): there are cases having subtree of many right Nodes, we need to check all of them
                node = node.right;          // if yes then find node.right
                minToMaxLeftMyCode(node);             // since it is new node, then go back to check the left, also wanna make it reversed to correct the order of output (right is always bigger number)
                System.out.print(" " + node.data); // the reversed output from the first call is reversed to the ascending order now to print for the right
            }
//            return; // if we are using while loop for the first condition: exit the current function so it doesn't continue to while within the function to prevent duplicates
        }
    }

    /* traverse from min to max
      - covering all kind of binary search tree (where some left node is >1 at the subtrees some right node is >1)
     */
    public void inOrderMyCode(Node<T> node){
        if (node != null){
            minToMaxLeftMyCode(node);

            System.out.print(" " + node.data);

            while (node.right != null){  // to cover the right subtree
                node = node.right;
                minToMaxLeftMyCode(node);
                System.out.print(" " + node.data);
            }
        }
        System.out.println();
    }

    // [BEST SIMPLIFIED METHOD] traverse from min to max covering all cases
    public void inOrder (Node<T> node){
        if(node != null){
            inOrder(node.left);                 // traverse to most left
            System.out.print(" " + node.data); // print elements in a reverse manner output
            inOrder(node.right);                // for every Node traverse to right and check, if != null then it will result traversing to most left again and print from small to big again
            // for every traversing of right, it is reversing back to the correct order to execute the new function to maintain the correct order as right>left and the more right is bigger
        }
    }

    // [BEST SIMPLIFIED METHOD] traverse from max to min covering all cases
    public void backOrderMyCode(Node<T> node){
        if(node != null){
            backOrderMyCode(node.right);
            System.out.print(" " + node.data);
            backOrderMyCode(node.left);
        }
    }


    /* COUNT OF LEAVES NODES
     -- traversing all the nodes and check their right and left

     Things learned:
      - my method involved having global Counter
        -- while the countLeaves it provided has a cleaner approach where it is not involving global counter
         --- without global counter, we can fix/change our code easier whenever needed.
     */

    public int countLeavesMyCode(Node<T> node) {

        if (node != null){
            countLeavesMyCode(node.left);         // recursively traverse to the most left
            if(node.right == null && node.left == null){ // check each node during the traversal from bottom to top left
                return this.leavesCounter ++;    // exit the current function once leave is found and ++ the leaveCounter
            }
            countLeavesMyCode(node.right);        // recursively check all the nodes' right and their lefts
        }
        return this.leavesCounter;          // this responsible for returning the FINAL value to Main
    }

    public int countLeaves(Node<T> node) {
        if (node == null) {
            return 0; // Base case: no leaves for a null node
        }

        if (node.left == null && node.right == null) {
            return 1; // Node is a leaf
        }
        // traversal of all nodes to check if there are leaves Nodes
        // left/rightLeaves of a subtree get initialised at the last statement return left + right
        int leftLeaves = countLeaves(node.left);        // repeatedly traverse node.left and get the number of nodes in the root left subtree (where there are more left and right subtrees will be summed by the statement below)
        int rightLeaves = countLeaves(node.right);      // repeatedly traverse node.right for each node.left of root (from bottom to top) and traverse each node.right.left too

        return leftLeaves + rightLeaves; // Sum of leaves in left and right subtrees then at last sum left + right for the main tree
        // (e.g: leftLeaves of root are summed by leafLeaves and rightLeaves of the subtrees inside left big subtree of root)
    }

    /* countNodesAtKthLayer, info needed:
     -- need to calculate the nth layer
     -- need to calculate at a particular layer, how many nodes are there

     approach: traversing all until the layer meets k
     */
    /* THINGS LEARNED:  -- better ways at below
     - again, always try to avoid using global variable, that might lead to unintended side effects, especailly when calling this method multiplt times!
     */
    public int countNodesAtKthLayerMyCode(Node<T> node, int k){
        this.layer ++; // root is the first layer AND every time function calls it traversed to a new layer
        if (node == null){
            this.layer --;  // go back one layer is node is null as function will exit which means the latest traverse is invalid
            return 0;
        }
        if (this.layer == k){  // since all node == null is filtered, then this one surely is node != null
            this.layer --;   // go back one layer if node is found as function will exit
            return 1; // nodes at the right layer then we dont have to further proceed to next layer, thus can exit.
        }

        // if node != null and layer !=k
        int leftKLayerNodes = countNodesAtKthLayerMyCode(node.left, k);
        int rightKLayerNodes = countNodesAtKthLayerMyCode(node.right, k);

        this.layer --;          // go back one layer for cases where it is not null but also not the right layer then it finishes execution after checking left and right
        return leftKLayerNodes + rightKLayerNodes;
    }

    public int countNodesAtKthLayer(Node<T> node, int startLayer, int k){
        if (node == null){
            return 0;
        }
        if (startLayer == k){  // since all node == null is filtered, then this one surely is node != null
            return 1; // nodes at the right layer then we dont have to further proceed to next layer, thus can exit.
        }

        // if node != null and layer !=k
        int leftKLayerNodes = countNodesAtKthLayer(node.left, startLayer+1, k);  // with currLayer +1 , it will automatically maintain the layer, without needing me to manually make them layer--, as the layer added simultaneously together with traversing down the ndoe
        int rightKLayerNodes = countNodesAtKthLayer(node.right, startLayer+1, k);

        return leftKLayerNodes + rightKLayerNodes;
    }

    /* CALCULATE THE DEPTH OF THE TREE
     a) can be tracked by inserting the nodes with global variable (but lets avoid that to reduce dependency)
     b) calculate the traverse a bit like above to keep track the height (layer)
     Note: traverse all the path to find the longest path then track that
     */
    public int heightMyCode(Node<T> node, int startHeight){
        if (node == null){ // means prev node is the deepest node
            return (startHeight - 1);  // return the last non-null node's height
        }

        int leftHeight = heightMyCode(node.left, startHeight+1);
        int rightHeight = heightMyCode(node.right, startHeight+1);

        return Math.max(leftHeight, rightHeight); // just always take the max of the height between all the left or right subtrees, eventually it will get to the highest height
    }

    /* DETERMINE IF THE TREE IS COMPLETE, a few potential methods:
      -- having the same height of for all the last second node or last node
      -- nodes at last second layer OR last layer is 2^height
        --- when height == height-1 , check if all the nodes are
      -- Number of Node should be at least = 2^(h+1)-2^h-1
      -- if more than 2^(h+1)-2^h-1, then make sure all the nodes are on the left
      -- if the number of nodes at the h layer != 2^h
        -- then check the number nodes at the h-1 layer == 2^(h-1)
           --- if yes then check all the left if got right filled but no left filled then return false
      Note: traverse all the way to the bottom
     */
    // Note: this code didnt cover all cases: cannot check the last level of nodes are not all filled but filled from left to right
    public boolean completeTreeMyCode(){
        if (countNodesAtKthLayer(this.root,0,heightMyCode(this.root,0)) == Math.pow(2,(heightMyCode(this.root,0)))){
            return true;
        }
        else if (countNodesAtKthLayer(this.root,0, heightMyCode(this.root, 0)-1) == Math.pow(2 ,heightMyCode(this.root,0)-1)) {
            return true;
            }
        else{
            return false;
        }
    }

    /* [BETTER WAY] DETERMINE THE TREE IS COMPLETE
      - plot index for each node according to the order of complete tree since it is okay to leave the last level right side blank
      - as long as the index > count nodes, then it means it is not a complete as it is off the index
      a) we need a countNodes method
      b) then we need an algo to index the order of nodes accordingly from left to right at the same layer
     */
    public int countNodes(Node<T> node){
        if (node == null){
            return 0;
        }
        return (1 + countNodes(node.left) + countNodes(node.right));
    }
    /*  THINGS LEARNED:
        - we can always label index in the way we need to help with the design of algo
         -- e.g: index to label according to the order you want to ensure the tree is complete
     */
    public boolean isComplete(Node<T> node, int index, int countNode){
        if (node == null){      // end case where no more nodes
            return true;
        }
        if (index > countNode){
            return false;
        }
        return (isComplete(node.left, 2*index, countNode)       // repeat node.left and 2*prevIndex for every left traversal
         && isComplete(node.right, 2*index+1, countNode));

    }


    // level order traversal
    // reference from https://www.geeksforgeeks.org/level-order-tree-traversal/
    void printLevelOrder()
    {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            // poll() removes the present head.
            Node<T> tempNode = queue.poll();
            System.out.print(tempNode.data + " ");

            // Enqueue left child
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            // Enqueue right child
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    public static void main(String[] args) {
        BSTG<String> test = new BSTG<>();
//        BSTG test2 = new BSTG();

        test.insertMyCode("hi");
        test.insertMyCode("a");
        test.insertMyCode("b");
//        test.insertMyCode();
        test.insertMyCode("z");
        test.insertMyCode("oo");
        test.insertMyCode("bye");
//        test.insertMyCode(2.3);
        test.printLevelOrder();
//        test.insertMyCode(3);
//        test.insertMyCode(2);
//        test.insertMyCode(5);
//        test.insertMyCode(6);
//        test.insertMyCode(1);
//        test.insertMyCode(7);
//        test.insertMyCode(0);
//        test.insertMyCode(-4);
//        test.insertMyCode(-2);
//        test.insertMyCode(-1);
//        test.insertMyCode(21);
//        test.insertMyCode(19);
//        test.insertMyCode(20);
//        test.insertMyCode(17);
//        test.insertMyCode(24);
//        test.insertMyCode(-5);
//        test.deleteMyCode(10);
//        test.insertMyCode();
//
//        test.inOrderMyCode(test.root);
//        test.inOrder(test.root);
//        System.out.println();
//        test.backOrder(test.root);
//        System.out.println(test.countLeavesMyCode(test.root));
//        System.out.println(test.countLeaves(test.root));
//        System.out.println(test.countNodesAtKthLayerMyCode(test.root, 7));
//        int startLayer = 0;
////        System.out.println(test.countNodesAtKthLayer(test.root, 7, startLayer));
////        System.out.print(test.heightMyCode(test.root, startLayer)); // root is at height=0
////        System.out.print(test.completeTreeMyCode());
////        System.out.println(test.countNodes(test.root));
//        int index = 1; // start from 1 as root is 1
//        System.out.println(test.isComplete(test.root,index,test.countNodes(test.root)));


//        test.insert(3);
//        test.insert(1);
//        test.insert(2);
//        test.insert(5);
//        test.insert(0);
//        test.insert(-1);
//        test.insert(7);
//        test.insert(6);



//        System.out.println("root:" + test.root.data);
//        System.out.println("r:" + test.root.right.data + " root:" + test.root.data );
////        System.out.println("rl:" + test.root.right.left.data);
////        System.out.println("rll:" + test.root.right.left.left.data);
//        System.out.println("l:" + test.root.left.data);
//        System.out.println("rr:" + test.root.right.right.data);
////        System.out.println("rrr:" + test.root.right.right.right.data);
//        System.out.println("ll:" + test.root.left.left.data);
//        System.out.println("lr:" + test.root.left.right.data);
//        System.out.println("lll:" + test.root.left.left.left.data);
//        System.out.println("rrl:" + test.root.right.right.left.data);
//        System.out.println(test.findMyCode(1));
//        System.out.println(test.findMyCode(0));
//        System.out.println(test.findMyCode(-1));
//        System.out.println(test.findMyCode(2));
//        System.out.println(test.findMyCode(3));
//        System.out.println(test.findMyCode(4));
//        System.out.println(test.findMyCode(5));
//        System.out.println(test.findMyCode(6));
//        System.out.println(test.findMyCode(7));
//        System.out.println(test.findMyCode(8));
//        System.out.println(test.findMyCode(9));
//        System.out.println(test.findMyCode(10));

//        test.deleteMyCode(3);
//        System.out.println(test.findMyCode(2));
//        test.deleteMyCode(2);
//        System.out.println(test.findMyCode(2));

    }
}