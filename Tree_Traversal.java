import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Tree_Traversal {
    public static Map<String, Node> nodeMap = new HashMap<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        mkNodeMap(testCase);

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkNodeGraph(st);
        }

        printTraversal();
    }

    public static void printTraversal() {
        Node root = nodeMap.get("A");

        processPreOrder(root);
        sb.append("\n");
        processInOrder(root);
        sb.append("\n");
        processPostOrder(root);

        System.out.println(sb);
    }

    public static void processPreOrder(Node parent) {
        sb.append(parent.getValue());

        if(checkLeftNodeValidation(parent)) processPreOrder(parent.getLeftNode());
        if(checkRightNodeValidation(parent)) processPreOrder(parent.getRightNode());
    }

    public static void processInOrder(Node parent) {
        if(checkLeftNodeValidation(parent)) processInOrder(parent.getLeftNode());
        sb.append(parent.getValue());
        if(checkRightNodeValidation(parent)) processInOrder(parent.getRightNode());
    }

    public static void processPostOrder(Node parent) {
        if(checkLeftNodeValidation(parent)) processPostOrder(parent.getLeftNode());
        if(checkRightNodeValidation(parent)) processPostOrder(parent.getRightNode());
        sb.append(parent.getValue());
    }

    public static boolean checkLeftNodeValidation(Node parent) {
        return (parent.getLeftNode() != null) && (!parent.getLeftNode().getValue().equals("."));
    }

    public static boolean checkRightNodeValidation(Node parent) {
        return (parent.getRightNode() != null) && (!parent.getRightNode().getValue().equals("."));
    }

    public static void mkNodeGraph(StringTokenizer st) {
        Node parent = nodeMap.get(st.nextToken());
        Node leftNode = nodeMap.get(st.nextToken());
        Node rightNode = nodeMap.get(st.nextToken());

        parent.setLeftNode(leftNode);
        parent.setRightNode(rightNode);
    }

    public static void mkNodeMap(int length) {
        for(int i = 0; i < length; i++) {
            String key = String.valueOf((char)('A' + i));
            nodeMap.put(key, new Node(key));
        }
    }

    public static class Node {
        private String value;
        private Node leftNode;
        private Node rightNode;

        public Node(String value) {
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
        }

        public String getValue() {
            return this.value;
        }

        public Node getLeftNode() {
            return this.leftNode;
        }

        public Node getRightNode() {
            return this.rightNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }
}