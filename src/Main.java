
public class Main {

    Node root;

    public static void main(String[] args){

        Main binaryTree = new Main();

        binaryTree.addNode(5, "Black");
        binaryTree.addNode(8, "White");
        binaryTree.addNode(1, "Pink");
        binaryTree.addNode(4, "Cyan");
        binaryTree.addNode(9, "Purple");
        binaryTree.addNode(8, "Orange");
        binaryTree.addNode(2, "Red");
        binaryTree.addNode(3, "Yellow");
        binaryTree.addNode(6, "Blue");
        binaryTree.addNode(0, "Green");

        System.out.println("In-Order Traversal:");
        binaryTree.inOrderTraversal(binaryTree.root);

        System.out.println("Pre-Order Traversal:");
        binaryTree.preOrderTraversal(binaryTree.root);

        System.out.println("Post-Order Traversal:");
        binaryTree.postOrderTraversal(binaryTree.root);

        System.out.println("reverse-Order Traversal:");
        binaryTree.reverseOrderTraversal(binaryTree.root);

        System.out.println("Finding 3:");
        Node found = binaryTree.findNode(3);
        System.out.println(found);

        System.out.println("Remove 8:");
        boolean deleted = binaryTree.removeNode(8);
        System.out.println(deleted);

        System.out.println("In-Order Traversal:");
        binaryTree.inOrderTraversal(binaryTree.root);
    }

    public void addNode(int key, String value) {

        Node newNode = new Node(key, value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node focus = root;

        while (true) {

            if (newNode.key < focus.key) {

                if (focus.left == null) {
                    focus.left = newNode;
                    break;
                }

                focus = focus.left;
            } else {

                if (focus.right == null) {
                    focus.right = newNode;
                    break;
                }

                focus = focus.right;
            }
        }

    }

    public Node findNode(int key) {

        Node focus = root;

        while (focus.key != key) {

            if (key < focus.key) {

                focus = focus.left;
            } else {

                focus = focus.right;
            }

            if (focus == null) {
                return null;
            }
        }

        return focus;
    }

    public boolean removeNode(int key) {

        Node focus = root;
        Node parent = root;

        boolean leftChild = true;

        // First we need to find the node
        while (focus.key != key) {

            parent = focus;

            if (key < focus.key) {

                leftChild = true;

                focus = focus.left;
            } else {

                leftChild = false;

                focus = focus.right;
            }

            if (focus == null)
                return false;
        }

        // If the node we want to delete has no children
        if (focus.left == null && focus.right == null) {

            if (focus == root) {
                root = null;
            } else if (leftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }

        // If it has one child on the left
        } else if (focus.right == null) {

            if (focus == root) {
                root = focus.left;
            } else if (leftChild) {
                parent.left = focus.left;
            } else {
                parent.right = focus.left;
            }

        // If it has one child on the right
        } else if (focus.left == null) {
            if (focus == root) {
                root = focus.right;
            } else if (leftChild) {
                parent.left = focus.right;
            } else {
                parent.right = focus.right;
            }

        // If it has two children
        } else {

            Node replacement = getReplacementNode(focus);

            if (focus == root) {
                root = replacement;
            } else if (leftChild) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }

            replacement.left = focus.left;
        }

        return true;
    }

    public Node getReplacementNode(Node replaceNode) {

        Node replacementParent = replaceNode;
        Node replacement = replaceNode;

        Node focus = replaceNode.right;

        while (focus != null) {

            replacementParent = replacement;

            replacement = focus;

            focus = focus.left;
        }

        if (replacement != replaceNode.right) {

            replacementParent.left = replacement.right;
            replacement.right = replaceNode.right;
        }

        return replacement;
    }

    // Print keys from lowest to highest
    public void inOrderTraversal(Node focus) {

        if (focus != null) {

            inOrderTraversal(focus.left);

            System.out.println(focus);

            inOrderTraversal(focus.right);
        }

    }

    // Print keys from lowest to highest
    public void reverseOrderTraversal(Node focus) {

        if (focus != null) {

            reverseOrderTraversal(focus.right);

            System.out.println(focus);

            reverseOrderTraversal(focus.left);

        }

    }

    // This will give the effect of the tree growing
    public void preOrderTraversal(Node focus) {

        if (focus != null) {

            System.out.println(focus);

            preOrderTraversal(focus.left);

            preOrderTraversal(focus.right);
        }

    }

    // This will give the effect of the tree growing backwards
    public void postOrderTraversal(Node focus) {

        if (focus != null) {

            postOrderTraversal(focus.left);

            postOrderTraversal(focus.right);

            System.out.println(focus);

        }

    }
}

class Node {

    int key;
    String value;

    Node left;
    Node right;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {

        return value + " = " + key;
    }
}
