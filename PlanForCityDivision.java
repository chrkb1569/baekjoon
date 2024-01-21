import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PlanForCityDivision {
    private static PriorityQueue<int[]> resultQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
    private static int[] parentArr;
    private static int maxWeight = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initParentArr(N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultQueue.offer(new int[]{from, to, weight});
        }

        int weightSum = linkCity();

        System.out.println(weightSum - maxWeight);
    }

    private static void initParentArr(int length) {
        parentArr = new int[length + 1];

        for(int i = 0; i < parentArr.length; i++) {
            parentArr[i] = i;
        }
    }

    private static int linkCity() {
        int weightSum = 0;

        while(!resultQueue.isEmpty()) {
            int[] arr = resultQueue.poll();

            int from = arr[0];
            int to = arr[1];
            int weight = arr[2];

            int parentA = findParent(from);
            int parentB = findParent(to);

            if(parentA == parentB) continue;

            union(parentA, parentB);
            weightSum += weight;
            maxWeight = Math.max(maxWeight, weight);
        }

        return weightSum;
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
}