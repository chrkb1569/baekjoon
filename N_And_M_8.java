import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N_And_M_8 {
    private static StringBuilder sb = new StringBuilder();
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int length = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(length);
        mkResultArr(st);

        Arrays.sort(resultArr);

        int[] valueArr = new int[quantity];

        getPermutation(valueArr, 0, 0, quantity);

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
    }

    private static void getPermutation(int[] arr, int start, int current, int target) {
        if(current == target) {
            mkResultString(arr);
            return;
        }

        for(int i = start; i < resultArr.length; i++) {
            arr[current] = resultArr[i];
            getPermutation(arr, i, current + 1, target);
        }
    }

    private static void mkResultString(int[] arr) {
        for(int value : arr) {
            sb.append(value).append(" ");
        }
        sb.append("\n");
    }
}
