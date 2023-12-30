package seminar4;

public class RedBlackBinTree<V extends Comparable<V>> {

    private Node root;


    private class Node {
        private final V value;
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

    public void addNode(V value) {

        Node newNode = new Node(value);


        if (root == null) {
            root = newNode;
        } else {

            addNode(root, newNode, value);
        }

        root.isRed = false;

    }


    private void addNode(Node currentNode, Node newNode, V value) {

        if (currentNode.value.compareTo(value) > 0) {//если новое значение меньше значения текущей ноды
            if (currentNode.left != null)
                addNode(currentNode.left, newNode, value);
            else {
                currentNode.left = newNode;
                newNode.parent = currentNode;
            }
        } else {
            if (currentNode.right != null)
                addNode(currentNode.right, newNode, value);
            else {
                currentNode.right = newNode;
                newNode.parent = currentNode;
            }
        }


        balanceRedBlackTree(currentNode);


    }


    private void balanceRedBlackTree(Node currentNode) {
        if (currentNode.right != null && currentNode.right.isRed && currentNode.left != null && currentNode.left.isRed) { // перекраска двух красных
            currentNode.right.isRed = false;
            currentNode.left.isRed = false;
            currentNode.isRed = true;
        } else if (currentNode.left != null && currentNode.left.isRed && currentNode.left.left != null && currentNode.left.left.isRed) {
            rightTurn(currentNode);
        } else if (currentNode.right != null && currentNode.right.isRed) {
            leftTurn(currentNode);
        }
    }


    private void rightTurn(Node currentNode) {

        currentNode.isRed = false;
        currentNode.left.left.isRed = false;
        currentNode.left.isRed = true;

        if (currentNode == root) {
            root = currentNode.left;
            root.parent = null;
        } else {
            Node baseNode = currentNode.parent;
            if (baseNode.left != null && baseNode.left == currentNode) {
                baseNode.left = currentNode.left;
            } else {
                baseNode.right = currentNode.left;
            }
            currentNode.left.parent = baseNode;
        }

        Node tempNode = currentNode.left.right;

        currentNode.left.right = currentNode;
        currentNode.parent = currentNode.left;

        currentNode.left = tempNode;
        if (tempNode != null)
            tempNode.parent = currentNode;
    }


    private void leftTurn(Node currentNode) {

        if (currentNode == root) {
            root = currentNode.right;
            currentNode.parent = root;
            root.parent = null;
        } else {
            Node baseNode = currentNode.parent;

            if (baseNode.left != null && baseNode.left == currentNode) {
                baseNode.left = currentNode.right;
                currentNode.right.parent = baseNode;
            } else {
                baseNode.right = currentNode.right;
                baseNode.right.parent = baseNode;
            }
        }

        Node tempNode = currentNode.right.left;
        currentNode.right.isRed = false;

        currentNode.right.left = currentNode;
        currentNode.parent = currentNode.right;
        currentNode.right = tempNode;
        if (tempNode != null)
            tempNode.parent = currentNode;
        currentNode.isRed = true;

    }


}


