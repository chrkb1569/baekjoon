import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Make_Tile {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        mkResultArr();

        System.out.println(resultArr[N]);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void mkResultArr() {
        if(resultArr.length % 2 == 0) return;

        resultArr[0] = 1;

        for(int index = 2; index < resultArr.length; index += 2) {
            resultArr[index] = 3 * resultArr[index - 2];

            for(int otherIndex = index - 4; otherIndex >= 0; otherIndex -= 2) {
                resultArr[index] += 2 * resultArr[otherIndex];
            }
        }
    }
}