import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShortestPath {
    private static int[] resultArr;
    private static List<Node>[] resultList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int startNumber = Integer.parseInt(br.readLine());

        initResultArr(node, startNumber);
        initResultList(node);

        for(int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultList[from].add(new Node(to, weight));
        }

        mkResultArr(startNumber);
        printResult();
    }

    private static void initResultArr(int length, int startNode) {
        resultArr = new int[length + 1];

        Arrays.fill(resultArr, Integer.MAX_VALUE);

        resultArr[startNode] = 0;
    }

    private static void initResultList(int length) {
        resultList = new ArrayList[length + 1];

        for(int i = 0; i <= length; i++) {
            resultList[i] = new ArrayList<>();
        }
    }

    private static void mkResultArr(int start) {
        PriorityQueue<Node> resultQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[resultArr.length];
        resultQueue.offer(new Node(start, 0));

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int to = node.getTo();

            if(visit[to]) continue;
            visit[to] = true;

            int weight = node.getWeight();

            for(Node nextNode : resultList[to]) {
                int nextWeight = nextNode.getWeight();
                int nextTo = nextNode.getTo();

                if(resultArr[nextTo] > weight + nextWeight) {
                    resultArr[nextTo] = weight + nextWeight;
                    resultQueue.offer(new Node(nextTo, weight + nextWeight));
                }
            }
        }
    }

    private static void printResult() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 1; i < resultArr.length; i++) {
            if(resultArr[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(resultArr[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
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
