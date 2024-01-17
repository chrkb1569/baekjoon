import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N_And_M_6 {
    private static int[] resultArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(M);
        mkResultArr(st);

        boolean[] visitArr = new boolean[M];
        int[] valueArr = new int[N];

        getPermutation(visitArr, valueArr, 0, 0, N);

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(resultArr);
    }

    private static void getPermutation(boolean[] visit, int[] arr, int start, int current, int target) {
        if(current == target) {
            mkResultString(arr);
            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[current] = resultArr[i];
                getPermutation(visit, arr, i+1, current + 1, target);
                visit[i] = false;
            }
        }
    }

    private static void mkResultString(int[] arr) {
        for(int value : arr) {
            sb.append(value).append(" ");
        }
        sb.append("\n");
    }
}
