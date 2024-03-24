import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Get_Minimum_Cost_2 {
    private static List<List<Node>> resultList = new ArrayList<>();
    private static boolean[] visitArr;
    private static int[] weightArr;
    private static int[] preArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // city
        int M = Integer.parseInt(br.readLine()); // bus

        initResultList(N);
        initWeightArr(N);
        initVisitArr(N);
        initPreArr(N);

        for(int bus = 0; bus < M; bus++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultList.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine(), " ");

        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        findShortestPath(startCity);
        printResult(endCity);
    }

    private static void initResultList(int city) {
        for(int index = 0; index <= city; index++) {
            resultList.add(new ArrayList<>());
        }
    }

    private static void initWeightArr(int length) {
        weightArr = new int[length + 1];

        Arrays.fill(weightArr, Integer.MAX_VALUE);
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length + 1];
    }

    private static void initPreArr(int length) {
        preArr = new int[length + 1];
    }

    private static void findShortestPath(int startNumber) {
        PriorityQueue<Node> resultQueue = new PriorityQueue<>();
        resultQueue.add(new Node(startNumber, 0));

        weightArr[startNumber] = 0;
        preArr[startNumber] = 0;

        while(!resultQueue.isEmpty()) {
            Node currentNode = resultQueue.poll();

            int currentNumber = currentNode.getTo();
            int currentWeight = currentNode.getWeight();

            if(visitArr[currentNumber]) continue;

            visitArr[currentNumber] = true;

            for(Node nextNode : resultList.get(currentNumber)) {
                if(weightArr[nextNode.getTo()] > currentWeight + nextNode.weight) {
                    weightArr[nextNode.getTo()] = currentWeight + nextNode.weight;
                    resultQueue.add(new Node(nextNode.getTo(), weightArr[nextNode.getTo()]));
                    preArr[nextNode.getTo()] = currentNumber;
                }
            }
        }
    }

    private static void printResult(int endNumber) {
        System.out.println(weightArr[endNumber]);

        List<Integer> resultList = new ArrayList<>();

        int current = endNumber;

        while(current != 0) {
            resultList.add(current);
            current = preArr[current];
        }

        System.out.println(resultList.size());

        for(int index = resultList.size() - 1; index >= 0; index--) {
            System.out.print(resultList.get(index) + " ");
        }

        System.out.println();
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
