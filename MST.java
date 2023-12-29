import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MST {
    private static PriorityQueue<Node> resultQueue = new PriorityQueue<>();
    private static int[] parentArr;
    private static int totalWeight = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int points = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt(st.nextToken());

        initParentArr(points);

        for(int link = 0; link < links; link++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultQueue.offer(new Node(from, to, weight));
        }

        getTotalWeight();

        System.out.println(totalWeight);
    }

    private static void initParentArr(int length) {
        parentArr = new int[length + 1];

        for(int i = 0; i < parentArr.length; i++) {
            parentArr[i] = i;
        }
    }

    private static void getTotalWeight() {
        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int from = node.getFrom();
            int to = node.getTo();
            int weight = node.getWeight();

            int parentFrom = findParent(from);
            int parentTo = findParent(to);

            if(parentFrom == parentTo) continue;

            union(from, to);
            totalWeight += weight;
        }
    }

    private static int findParent(int index) {
        if(parentArr[index] == index) return index;
        parentArr[index] = findParent(parentArr[index]);
        return parentArr[index];
    }

    private static void union(int indexA, int indexB) {
        int parentA = findParent(indexA);
        int parentB = findParent(indexB);

        if(parentA < parentB) {
            parentArr[parentB] = parentA;
            return;
        }

        parentArr[parentA] = parentB;
    }

    private static class Node implements Comparable<Node> {
        private int from;
        private int to;
        private int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return this.from;
        }

        public int getTo() {
            return this.to;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.getWeight() - node.getWeight();
        }
    }
}
