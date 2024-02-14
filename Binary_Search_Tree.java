import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Binary_Search_Tree {
    private static final List<Integer> inputValues = new ArrayList<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";

        while((userInput = br.readLine()) != null) {
            if (userInput.isBlank() || userInput.isEmpty()) break;

            inputValues.add(Integer.parseInt(userInput));
        }

        if(inputValues.isEmpty()) return;

        Node root = new Node(inputValues.get(0));

        mkTree(root);
        printPostfix(root);

        System.out.println(sb);
    }

    private static void mkTree(Node root) {
        for(int index = 1; index < inputValues.size(); index++) {
            int value = inputValues.get(index);

            travelTree(root, value);
        }
    }

    private static void travelTree(Node node, int value) {
        int nodeValue = node.getValue();

        if(value < nodeValue && node.getLeftNode() != null) {
            travelTree(node.getLeftNode(), value);
            return;
        }

        if(value > nodeValue && node.getRightNode() != null) {
            travelTree(node.getRightNode(), value);
            return;
        }

        if(value < nodeValue) {
            node.setLeftNode(value);
            return;
        }

        node.setRightNode(value);
    }

    private static void printPostfix(Node node) {
        if(node.getLeftNode() != null) printPostfix(node.getLeftNode());
        if(node.getRightNode() != null) printPostfix(node.getRightNode());
        sb.append(node.getValue()).append("\n");
    }

    private static class Node {
        private int value;
        private Node leftNode;
        private Node rightNode;

        public Node(int value) {
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
        }

        public int getValue() {
            return this.value;
        }

        public Node getLeftNode() {
            return this.leftNode;
        }

        public Node getRightNode() {
            return this.rightNode;
        }

        public void setLeftNode(int value) {
            this.leftNode = new Node(value);
        }

        public void setRightNode(int value) {
            this.rightNode = new Node(value);
        }
    }
}
