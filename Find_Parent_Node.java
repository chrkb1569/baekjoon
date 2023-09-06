import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Find_Parent_Node {
    public static Node[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        initResultArr(testCase);

        for(int i = 1; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            resultArr[start].getLink().add(end);
            resultArr[end].getLink().add(start);
        }

        mkTree();
        printResult(sb);
    }

    public static void printResult(StringBuilder sb) {
        for(int i = 2; i < resultArr.length; i++) {
            sb.append(resultArr[i].getParent()).append("\n");
        }

        System.out.println(sb);
    }

    public static void mkTree() {
        Queue<Integer> resultQueue = new LinkedList<>();
        resultQueue.offer(1);

        while(!resultQueue.isEmpty()) {
            int nodeNumber = resultQueue.poll();

            Node node = resultArr[nodeNumber];
            int depth = node.getDepth();

            for(int value : node.getLink()) {
                Node nodeValue = resultArr[value];

                if(nodeValue.getDepth() <= depth) continue;

                nodeValue.setParent(nodeNumber);
                nodeValue.setDepth(depth + 1);
                resultQueue.offer(value);
            }
        }
    }

    public static void initResultArr(int length) {
        resultArr = new Node[length + 1];

        for(int i = 1; i < resultArr.length; i++) {
            resultArr[i] = new Node(i);
        }

        resultArr[1].setDepth(0);
    }

    public static class Node {
        private int number;
        private int depth;
        private int parent;
        private List<Integer> link = new LinkedList<>();

        public Node(int number) {
            this.number = number;
            this.parent = number;
            this.depth = Integer.MAX_VALUE;
        }

        public int getNumber() {
            return this.number;
        }

        public int getDepth() {
            return this.depth;
        }

        public int getParent() {
            return this.parent;
        }

        public List<Integer> getLink() {
            return this.link;
        }

        public void setDepth(int depth) {
            this.depth = Math.min(this.depth, depth);
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
    }
}
