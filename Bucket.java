import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bucket {
    private static boolean[][][] resultArr;
    private static PriorityQueue<Integer> resultQueue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        initResultArr(A, B, C);

        dfs(A, B, C, 0, 0, C);

        printResult(C);
    }

    private static void initResultArr(int a, int b, int c) {
        resultArr = new boolean[a + 1][b + 1][c + 1];
    }

    private static void dfs(int A, int B, int C, int currentA, int currentB, int currentC) {
        if(currentA == 0) {
            resultQueue.add(currentC);
        }

        // C -> A
        if(currentA + currentC > A && !resultArr[A][currentB][currentA + currentC - A]) {
            resultArr[A][currentB][currentA + currentC - A] = true;
            dfs(A, B, C, A, currentB, currentA + currentC - A);
        }

        if(currentA + currentC <= A && !resultArr[currentA + currentC][currentB][0]) {
            resultArr[currentA + currentC][currentB][0] = true;
            dfs(A, B, C, currentA + currentC, currentB, 0);
        }

        // C -> B
        if(currentB + currentC <= B && !resultArr[currentA][currentB + currentC][0]) {
            resultArr[currentA][currentB + currentC][0] = true;
            dfs(A, B, C, currentA, currentB + currentC, 0);
        }

        if(currentB + currentC > B && !resultArr[currentA][B][currentB + currentC - B]) {
            resultArr[currentA][B][currentB + currentC - B] = true;
            dfs(A, B, C, currentA, B, currentB + currentC - B);
        }

        // A -> B
        if(currentA + currentB <= B && !resultArr[0][currentA + currentB][currentC]) {
            resultArr[0][currentA + currentB][currentC] = true;
            dfs(A, B, C, 0, currentA + currentB, currentC);
        }

        if(currentA + currentB > B && !resultArr[currentA + currentB - B][B][currentC]) {
            resultArr[currentA + currentB - B][B][currentC] = true;
            dfs(A, B, C, currentA + currentB - B, B, currentC);
        }

        // A -> C
        if(currentA + currentC <= C && !resultArr[0][currentB][currentA + currentC]) {
            resultArr[0][currentB][currentA + currentC] = true;
            dfs(A, B, C, 0, currentB, currentA + currentC);
        }

        if(currentA + currentC > C && !resultArr[currentA + currentC - C][currentB][C]) {
            resultArr[currentA + currentC - C][currentB][C] = true;
            dfs(A, B, C, currentA + currentC - C, currentB, C);
        }

        // B -> A
        if(currentA + currentB <= A && !resultArr[currentA + currentB][0][currentC]) {
            resultArr[currentA + currentB][0][currentC] = true;
            dfs(A, B, C, currentA + currentB, 0, currentC);
        }

        if(currentA + currentB > A && !resultArr[A][currentA + currentB - A][currentC]) {
            resultArr[A][currentA + currentB - A][currentC] = true;
            dfs(A, B, C, A, currentA + currentB - A, currentC);
        }

        // B -> C
        if(currentB + currentC > C && !resultArr[currentA][currentB + currentC - C][C]) {
            resultArr[currentA][currentB + currentC - C][C] = true;
            dfs(A, B, C, currentA, currentB + currentC - C, C);
        }

        if(currentB + currentC <= C && !resultArr[currentA][0][currentB + currentC]) {
            resultArr[currentA][0][currentB + currentC] = true;
            dfs(A, B, C, currentA, 0, currentB + currentC);
        }
    }

    private static void printResult(int length) {
        StringBuffer sb = new StringBuffer();
        boolean[] visitArr = new boolean[length + 1];

        while(!resultQueue.isEmpty()) {
            int index = resultQueue.poll();

            visitArr[index] = true;
        }

        for(int index = 0; index < visitArr.length; index++) {
            if(visitArr[index]) sb.append(index).append(" ");
        }

        System.out.println(sb);
    }
}
