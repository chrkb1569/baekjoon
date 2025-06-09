import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class NetworkConnection {

    private static int[] unionArr;

    private static PriorityQueue<int[]> resultQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수 1 <= N <= 1_000
        int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수 1 <= M <= 100_000

        initUnionArr(N);
        initResultQueue();

        for(int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            resultQueue.add(new int[]{from, to, cost});
        }

        int minCost = getMinCost();

        System.out.println(minCost);
    }

    private static void initUnionArr(int length) {
        unionArr = new int[length + 1];

        for(int index = 0; index < unionArr.length; index++) {
            unionArr[index] = index;
        }
    }

    private static void initResultQueue() {
        resultQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
    }

    private static int getMinCost() {
        int resultCost = 0;

        while(!resultQueue.isEmpty()) {
            int[] link = resultQueue.poll();

            int from = link[0];
            int to = link[1];
            int cost = link[2];

            int parentFrom = findParent(from);
            int parentTo = findParent(to);

            if(parentFrom == parentTo) continue;

            union(from, to);
            resultCost += cost;
        }

        return resultCost;
    }

    private static int findParent(int index) {
        if(unionArr[index] == index) return index;

        unionArr[index] = findParent(unionArr[index]);

        return unionArr[index];
    }

    private static void union(int from, int to) {
        int parentFrom = findParent(from);
        int parentTo = findParent(to);

        unionArr[parentTo] = parentFrom;
    }
}
