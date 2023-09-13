import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculate_Date {
    public static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        initResultArr(arr);

        System.out.println(getDateResult());
    }

    public static int getDateResult() {
        int result = 0, earth = 0, sun = 0, moon = 0;

        while(true) {
            earth = result % 15 + 1;
            sun = result % 28 + 1;
            moon = result % 19 + 1;

            result++;

            if(earth == resultArr[0] && sun == resultArr[1] && moon == resultArr[2]) break;
        }

        return result;
    }

    public static void initResultArr(String[] arr) {
        resultArr = new int[3];

        for(int i = 0; i < 3; i++) {
            resultArr[i] = Integer.parseInt(arr[i]);
        }
    }
}

/**
 * (1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19)
 * 예제 입력 1
 * 1 16 16
 * 예제 출력 1
 * 16
 * 예제 입력 2
 * 1 1 1
 * 예제 출력 2
 * 1
 * 예제 입력 3
 * 1 2 3
 * 예제 출력 3
 * 5266
 * 예제 입력 4
 * 15 28 19
 * 예제 출력 4
 * 7980
 */