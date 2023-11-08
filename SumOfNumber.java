import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SumOfNumber {
    public static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int length = Integer.parseInt(infoArr[0]);
        long value = Long.parseLong(infoArr[1]);

        infoArr = br.readLine().split(" ");

        initResultArr(length);
        mkResultArr(infoArr);

        int result = getResult(value);

        System.out.println(result);
    }

    public static void initResultArr(int length) {
        resultArr = new int[length];
    }

    public static void mkResultArr(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            resultArr[i] = Integer.parseInt(arr[i]);
        }
    }

    public static int getResult(long value) {
        int count = 0;

        for(int i = 0; i < resultArr.length; i++) {
            long sum = 0;

            for(int j = i; j < resultArr.length; j++) {
                sum += resultArr[j];

                if(sum == value) count++;
                if(sum > value) break;
            }
        }

        return count;
    }
}
