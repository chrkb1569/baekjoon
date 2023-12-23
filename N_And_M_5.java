import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N_And_M_5 {
    private static StringBuilder sb = new StringBuilder();
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int length = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());

        initResultArr(length);

        st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        Arrays.sort(resultArr);

        int[] valueArr = new int[quantity];
        boolean[] visitArr = new boolean[length];

        getPermutation(valueArr, visitArr, 0, quantity);

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

    private static void getPermutation(int[] arr, boolean[] visit, int current, int target) {
        if(current == target) {
            for(int value : arr) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[current] = resultArr[i];
                getPermutation(arr, visit, current + 1, target);
                visit[i] = false;
            }
        }
    }
}
