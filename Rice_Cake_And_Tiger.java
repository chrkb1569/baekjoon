import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rice_Cake_And_Tiger {

    private static int[][] resultArr;
    private static boolean[][] visitArr;
    private static int[] answerArr;
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 떡을 팔아야 하는 날의 수 (1 <= N <= 1000)

        initResultArr(N);
        initVisitArr(N);
        initAnswerArr(N);

        for(int day = 0; day < N; day++) {
            st = new StringTokenizer(br.readLine(), " ");

            mkResultArr(day, st);
        }

        dfs(0, 0);

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][];
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length][10];
    }

    private static void initAnswerArr(int length) {
        answerArr = new int[length];
    }

    private static void mkResultArr(int day, StringTokenizer st) {
        int length = Integer.parseInt(st.nextToken());

        resultArr[day] = new int[length];

        for(int index = 0; index < resultArr[day].length; index++) {
            resultArr[day][index] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dfs(int day, int preValue) {
        if(flag) return;
        if(day == resultArr.length) {
            flag = true;
            return;
        }

        if(visitArr[day][preValue]) return;

        visitArr[day][preValue] = true;

        for(int index = 0; index < resultArr[day].length; index++) {
            int value = resultArr[day][index];

            if(value == preValue) continue;

            answerArr[day] = value;
            dfs(day + 1, value);

            if(flag) return;
        }
    }

    private static void printResult() {
        if(!flag) {
            System.out.println("-1");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for(int value : answerArr) {
            sb.append(value).append("\n");
        }

        System.out.println(sb);
    }
}
