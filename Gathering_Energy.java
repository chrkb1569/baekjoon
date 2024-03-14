import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gathering_Energy {
    private static int[] resultArr;
    private static long maxValue = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultList(st);

        getPermutation(resultArr, 0, N-2, 0);

        System.out.println(maxValue);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultList(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getPermutation(int[] arr, int current, int target, long value) {
        if(current == target) {
            maxValue = Math.max(maxValue, value);
            return;
        }

        for(int index = 1; index < arr.length-1; index++) {
            long preValue = arr[index-1];
            long postValue = arr[index+1];

            int[] copyArr = removeIndex(arr, index);

            getPermutation(copyArr, current + 1, target, value + (preValue * postValue));
        }
    }

    private static int[] removeIndex(int[] arr, int index) {
        int[] copyArr = new int[arr.length-1];
        int j = 0;

        for(int i = 0; i < arr.length; i++) {
            if(index == i) continue;
            copyArr[j++] = arr[i];
        }

        return copyArr;
    }
}