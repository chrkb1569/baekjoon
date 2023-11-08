import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StoneGame {
    public static int[] resultArr;

    public static void main(String[]args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stone = Integer.parseInt(br.readLine());

        initResultArr(stone);
        mkResultArr();
        printResult(stone);
    }

    public static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    public static void mkResultArr() {
        Arrays.fill(resultArr, Integer.MAX_VALUE);
        resultArr[0] = 0;

        for(int i = 0; i < resultArr.length; i++) {
            int value = resultArr[i];

            if(i + 1 < resultArr.length) resultArr[i+1] = Math.min(resultArr[i+1], value + 1);
            if(i + 3 < resultArr.length) resultArr[i+3] = Math.min(resultArr[i+3], value + 1);
        }
    }

    public static void printResult(int index) {
        int value = resultArr[index];

        if(value % 2 == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}