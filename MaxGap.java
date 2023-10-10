import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MaxGap {
    public static StringBuilder sb = new StringBuilder();
    public static int[] resultArr;
    public static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visit;

        int length = Integer.parseInt(br.readLine());
        String[] valueArr = br.readLine().split(" ");

        visit = new boolean[length];
        initResultArr(length);
        mkResultArr(valueArr);

        getPermutation(visit, 0, length, "");

        System.out.println(maxValue);
    }

    public static void getPermutation(boolean[] visit, int now, int target, String result) {
        if(now == target) {
            String[] valueArr = result.split(" ");
            getMaxValue(valueArr);
            return;
        }

        for(int i = 0; i < resultArr.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, now + 1, target, result + resultArr[i] + " ");
                visit[i] = false;
            }
        }
    }

    public static void getMaxValue(String[] valueArr) {
        int sum = 0;

        for(int i = 1; i < valueArr.length; i++) {
            int num1 = Integer.parseInt(valueArr[i - 1]);
            int num2 = Integer.parseInt(valueArr[i]);
            sum += Math.abs(num1 - num2);
        }

        maxValue = Math.max(maxValue, sum);
    }

    public static void mkResultArr(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            resultArr[i] = Integer.parseInt(arr[i]);
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length];
    }
}
