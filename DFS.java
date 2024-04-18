import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DFS {
    private static List<PriorityQueue<Integer>> resultList = new ArrayList<>();
    private static boolean[] visitArr;
    private static int[] weightArr;
    private static int count = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // node
        int M = Integer.parseInt(st.nextToken()); // edge
        int R = Integer.parseInt(st.nextToken()); // startNode

        initResultList(N);
        initArr(N);

        for(int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()); // edge point
            int v = Integer.parseInt(st.nextToken()); // edge point

            resultList.get(u).add(v);
            resultList.get(v).add(u);
        }

        visitArr[R] = true;

        dfs(R);

        printResult();
    }

    private static void initResultList(int length) {
        resultList = new ArrayList<>(length + 1);

        for(int index = 0; index <= length; index++) {
            resultList.add(new PriorityQueue<>());
        }
    }

    private static void initArr(int length) {
        visitArr = new boolean[length + 1];
        weightArr = new int[length + 1];
    }

    private static void dfs(int number) {
        weightArr[number] = count++;

        while(!resultList.get(number).isEmpty()) {
            int nextNumber = resultList.get(number).poll();

            if(visitArr[nextNumber]) continue;

            visitArr[nextNumber] = true;
            dfs(nextNumber);
        }
    }

    private static void printResult() {
        StringBuffer sb = new StringBuffer();

        for(int index = 1; index < weightArr.length; index++) {
            sb.append(weightArr[index]).append("\n");
        }

        System.out.println(sb);
    }
}
/*
5 6 1
1 4
1 2
2 3
2 4
3 4
5 1
 */