package seminar4;

public class BinTree<V extends Comparable<V>> {

    private Node root;
    private Node nullNode;

    private class Node {
        private V value;
        private Node left;
        private Node right;
        private Node parent;
        private boolean isRed;

        private Node(V value) {
            this.value = value;
            this.isRed = true;
        }


    }

    public boolean contains(V value) {

        Node node = root;

        while (node != null) {
            if (node.value.equals(value))
                return true;
            if (node.value.compareTo(value) > 0)
                node = node.left;
            else
                node = node.right;
        }
        return false;
    }

    public boolean add(V value) {

        Node newNode = new Node(value);
        newNode.left = nullNode;
        newNode.right = nullNode;

        if (root == null) {
            root = newNode;
            return true;
        }
        ;

        addNode(root, newNode, value);

        return true;
    };

    private void addNode(Node currentNode, Node newNode, V value) {

        if (currentNode.value.compareTo(value) < 0) {
            if (currentNode.left != nullNode)
                addNode(currentNode.left, newNode, value);
            else
                currentNode.left = newNode;
        } else {
            if (currentNode.right != nullNode)
                addNode(currentNode.right, newNode, value);
            else
                currentNode.right = newNode;
        }
        ;

        balanceRedBlackTree(currentNode);


    }

    ;

    private void balanceRedBlackTree(Node currentNode) {
        if (currentNode.right.isRed && currentNode.left.isRed) {
            currentNode.right.isRed = false;
            currentNode.left.isRed = false;
            currentNode.isRed = true;
        } else if (currentNode.left.isRed && currentNode.left.left.isRed) {
            rightTurn(currentNode);
        } else if (currentNode.right.isRed) {
            leftTurn(currentNode);
        }
    }

    ;

    private void rightTurn(Node currentNode) {

    }

    ;

    private void leftTurn(Node currentNode) {

    }

    ;
}


