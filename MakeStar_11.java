import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MakeStar_11 {
    private static char[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        initResultArr(N, 2 * N - 1);
        mkStar(0, N-1, N);

        printResult();
    }

    private static void initResultArr(int height, int width) {
        resultArr = new char[height][width];

        for(int h = 0; h < resultArr.length; h++) {
            for(int w = 0; w < resultArr[0].length; w++) {
                resultArr[h][w] = ' ';
            }
        }
    }

    private static void mkStar(int height, int width, int length) {
        if(length == 3) {
            resultArr[height][width] = '*';

            resultArr[height + 1][width - 1] = '*';
            resultArr[height + 1][width + 1] = '*';

            for(int i = 0; i < 3; i++) {
                resultArr[height + 2][width + i] = '*';
                resultArr[height + 2][width - i] = '*';
            }

            return;
        }

        int value = length / 2;

        mkStar(height, width, value);
        mkStar(height + value, width + value, value);
        mkStar(height + value, width - value, value);
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(char[] arr : resultArr) {
            for(char value : arr) {
                sb.append(value);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
