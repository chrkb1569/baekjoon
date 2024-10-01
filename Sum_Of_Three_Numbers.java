import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sum_Of_Three_Numbers {
    private static int[] resultArr;
    private static int[] valueArr;
    private static int MAX_VALUE = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 원소의 갯수

        initArr(N);

        for(int element = 0; element < N; element++) {
            int elementValue = Integer.parseInt(br.readLine());

            resultArr[element] = elementValue;
        }

        Arrays.sort(resultArr);

        mkValueArr();

        Arrays.sort(valueArr);

        for(int index = resultArr.length - 1; index >= 0; index--) {
            int X = resultArr[index];

            for(int z = 0; z < resultArr.length; z++) {
                int value = resultArr[z];

                if(!searchNumber(X - value)) continue;

                MAX_VALUE = Math.max(MAX_VALUE, X);
                break;
            }
        }

        System.out.println(MAX_VALUE);
    }

    private static void initArr(int length) {
        resultArr = new int[length];
        valueArr = new int[length * length];
    }

    private static void mkValueArr() {
        int index = 0;

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length; j++) {
                valueArr[index++] = resultArr[i] + resultArr[j];
            }
        }
    }

    private static boolean searchNumber(int number) {
        int low = 0;
        int high = valueArr.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            int stdValue = valueArr[mid];

            if(stdValue == number) return true;
            if(number < stdValue) {
                high = mid - 1;
                continue;
            }

            low = mid + 1;
        }

        return false;
    }
}
