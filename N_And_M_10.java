import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class N_And_M_10 {
    private static Set<String> resultSet = new LinkedHashSet<>();
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initResultArr(N);

        st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        Arrays.sort(resultArr);

        boolean[] visitArr = new boolean[resultArr.length];
        int[] valueArr = new int[M];

        getPermutation(visitArr, valueArr, 0, 0, M);

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getPermutation(boolean[] visit, int[] valueArr, int start, int current, int target) {
        if(current == target) {
            addResult(valueArr);
            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                valueArr[current] = resultArr[i];
                getPermutation(visit, valueArr, i + 1, current + 1, target);
                visit[i] = false;
            }
        }
    }

    private static void addResult(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for(int value : arr) {
            sb.append(value).append(" ");
        }

        resultSet.add(sb.toString());
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(String value : resultSet) {
            sb.append(value).append("\n");
        }

        System.out.println(sb);
    }
}