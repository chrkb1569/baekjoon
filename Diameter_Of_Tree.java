import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Diameter_Of_Tree {
    private static List<Node>[] resultList;
    private static boolean[] visitArr;
    private static int DIAMETER = 0;
    private static int NODE_NUMBER = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        initResultList(V);

        for(int node = 0; node < V; node++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultList(st);
        }

        visitArr = new boolean[V + 1];
        dfs(1, 0);

        visitArr = new boolean[V + 1];
        dfs(NODE_NUMBER, 0);

        System.out.println(DIAMETER);
    }

    private static void initResultList(int length) {
        resultList = new List[length + 1];

        for(int index = 0; index <= length; index++) {
            resultList[index] = new ArrayList<>();
        }
    }

    private static void mkResultList(StringTokenizer st) {
        int nodeNumber = Integer.parseInt(st.nextToken());

        while(true) {
            int to = Integer.parseInt(st.nextToken());
            if(to == -1) break;

            int weight = Integer.parseInt(st.nextToken());

            resultList[nodeNumber].add(new Node(to, weight));
        }
    }

    private static void dfs(int currentNumber, int weight) {
        if(weight > DIAMETER) {
            DIAMETER = weight;
            NODE_NUMBER = currentNumber;
        }

        visitArr[currentNumber] = true;

        for(Node node : resultList[currentNumber]) {
            int nextNumber = node.getTo();
            int reqWeight = node.getWeight();

            if(visitArr[nextNumber]) continue;

            dfs(nextNumber, weight + reqWeight);
            visitArr[nextNumber] = true;
        }
    }

    private static class Node {
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
    }
}