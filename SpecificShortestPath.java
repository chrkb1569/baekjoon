import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SpecificShortestPath {
    private static final int LIMIT_VALUE = 200_000_000;
    private static List<Node>[] nodeArr;
    private static boolean[] visitArr;
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        initNodeArr(node);
        initArr(node);

        for(int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeArr[from].add(new Node(to, weight));
            nodeArr[to].add(new Node(from, weight));
        }

        st = new StringTokenizer(br.readLine(), " ");

        int spot1 = Integer.parseInt(st.nextToken());
        int spot2 = Integer.parseInt(st.nextToken());

        int result = getResult(spot1, spot2);

        System.out.println(result);
    }

    private static void initNodeArr(int length) {
        nodeArr = new ArrayList[length + 1];

        for(int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new ArrayList<>();
        }
    }

    private static void initArr(int length) {
        resultArr = new int[length + 1];
        visitArr = new boolean[length + 1];
    }

    private static int getResult(int spot1, int spot2) {
        int result1 = 0;
        int result2 = 0;

        result1 += getShortestDistance(1, spot1);
        result1 += getShortestDistance(spot1, spot2);
        result1 += getShortestDistance(spot2, nodeArr.length - 1);

        result2 += getShortestDistance(1, spot2);
        result2 += getShortestDistance(spot2, spot1);
        result2 += getShortestDistance(spot1, nodeArr.length - 1);

        if(result1 >= LIMIT_VALUE && result2 >= LIMIT_VALUE) return -1;
        return Math.min(result1, result2);
    }

    private static int getShortestDistance(int start, int destination) {
        PriorityQueue<Node> resultQueue = new PriorityQueue<>();
        resultQueue.offer(new Node(start, 0));

        Arrays.fill(resultArr, LIMIT_VALUE);
        Arrays.fill(visitArr, false);

        resultArr[start] = 0;

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int index = node.getTo();

            if(visitArr[index]) continue;

            visitArr[index] = true;
            int weight = node.getWeight();

            for(Node nextNode : nodeArr[index]) {
                int nextIndex = nextNode.getTo();
                int nextWeight = nextNode.getWeight();

                if(visitArr[nextIndex]) continue;
                if(resultArr[nextIndex] > weight + nextWeight) {
                    resultArr[nextIndex] = weight + nextWeight;
                    resultQueue.offer(new Node(nextIndex, resultArr[nextIndex]));
                }
            }
        }

        return resultArr[destination];
    }

    private static class Node implements Comparable<Node> {
        private int to;
        private int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return this.to;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.getWeight();
        }
    }
}
