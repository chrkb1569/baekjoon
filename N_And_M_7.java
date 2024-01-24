import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N_And_M_7 {
    private static int[] resultArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        int[] valueArr = new int[M];

        getPermutation(valueArr, 0, M);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
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

    private static void getPermutation(int[] arr, int current, int target) {
        if(current == target) {
            mkResultString(arr);
            return;
        }

        for(int i = 0; i < resultArr.length; i++) {
            arr[current] = resultArr[i];
            getPermutation(arr, current + 1, target);
        }
    }

    private static void mkResultString(int[] arr) {
        for(int value : arr) {
            sb.append(value).append(" ");
        }
        sb.append("\n");
    }
}
