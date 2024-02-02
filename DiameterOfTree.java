import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class DiameterOfTree {
    private static Node[] resultArr;
    private static boolean[] visitArr;
    private static int maxWeight = Integer.MIN_VALUE;
    private static int maxIndex = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        initVisitArr(N);

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultArr[from].getLink().add(new Link(to, weight));
            resultArr[to].getLink().add(new Link(from, weight));
        }

        visitArr[1] = true;
        dfs(1, 0);

        initVisitArr(N);

        maxWeight = Integer.MIN_VALUE;
        visitArr[maxIndex] = true;
        dfs(maxIndex, 0);

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new Node[length + 1];

        for(int i = 1; i < resultArr.length; i++) {
            resultArr[i] = new Node(i);
        }
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length + 1];
    }

    private static int dfs(int index, int weight) {
        for(Link link : resultArr[index].getLink()) {
            int to = link.getDestination();
            int cost = link.getWeight();

            if(visitArr[to]) continue;

            visitArr[to] = true;

            int weightValue = dfs(to, weight + cost);

            if(weightValue >= maxWeight) {
                maxWeight = weightValue;
                maxIndex = to;
            }
        }

        return weight;
    }

    private static void printResult() {
        if(resultArr.length == 2) {
            System.out.println(0);
            return;
        }
        System.out.println(maxWeight);
    }

    private static class Node {
        private int number;
        private List<Link> link = new LinkedList<>();

        public Node(int number) {
            this.number = number;
        }

        public int getNumber() {
            return this.number;
        }

        public List<Link> getLink() {
            return this.link;
        }
    }

    private static class Link {
        private int destination;
        private int weight;

        public Link(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return this.destination;
        }

        public int getWeight() {
            return this.weight;
        }
    }
}
