import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Permutation {
    private static StringBuilder sb = new StringBuilder();
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        boolean[] visitArr = new boolean[N];
        int[] valueArr = new int[N];

        getPermutation(visitArr, valueArr, 0, N);

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];

        for(int i = 0; i < resultArr.length; i++) {
            resultArr[i] = i+1;
        }
    }

    private static void getPermutation(boolean[] visit, int[] arr, int current, int target) {
        if(current == target) {
            for(int value : arr) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < resultArr.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[current] = resultArr[i];
                getPermutation(visit, arr, current + 1, target);
                visit[i] = false;
            }
        }
    }
}
