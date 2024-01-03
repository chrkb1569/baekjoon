import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimumCost {
    private static int[] resultArr;
    private static List<ArrayList<Node>> resultList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int nodes = Integer.parseInt(br.readLine());
        int links = Integer.parseInt(br.readLine());

        initResultList(nodes);
        initResultArr(nodes);

        for(int link = 0; link < links; link++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultList.get(from).add(new Node(to, weight));
        }

        st = new StringTokenizer(br.readLine(), " ");

        int startIndex = Integer.parseInt(st.nextToken());
        int destinationIndex = Integer.parseInt(st.nextToken());

        resultArr[startIndex] = 0;

        dijkstra(startIndex);

        System.out.println(resultArr[destinationIndex]);
    }

    private static void initResultList(int length) {
        for(int i = 0; i <= length; i++) {
            resultList.add(new ArrayList<>());
        }
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];

        Arrays.fill(resultArr, Integer.MAX_VALUE);
    }

    private static void dijkstra(int startIndex) {
        PriorityQueue<Node> resultQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
        resultQueue.offer(new Node(startIndex, 0));

        while(!resultQueue.isEmpty()) {
            Node currentNode = resultQueue.poll();

            int nextIndex = currentNode.getNextIndex();
            int weight = currentNode.getWeight();

            if(resultArr[nextIndex] < weight) continue;

            for(int i = 0; i < resultList.get(nextIndex).size(); i++) {
                Node nextNode = resultList.get(nextIndex).get(i);

                if(resultArr[nextNode.getNextIndex()] > weight + nextNode.getWeight()) {
                    resultArr[nextNode.getNextIndex()] = weight + nextNode.getWeight();
                    resultQueue.offer(new Node(nextNode.getNextIndex(), resultArr[nextNode.getNextIndex()]));
                }
            }
        }
    }

    private static class Node {
        private int nextIndex;
        private int weight;

        public Node(int nextIndex, int weight) {
            this.nextIndex = nextIndex;
            this.weight = weight;
        }

        public int getNextIndex() {
            return this.nextIndex;
        }

        public int getWeight() {
            return this.weight;
        }
    }
}
