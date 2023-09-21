import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindPath {
    public static int[][] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            String[] valueArr = br.readLine().split(" ");
            mkResultArr(i, valueArr);
        }

        finalizeResultArr();

        for(int[] arr : resultArr) {
            for(int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void finalizeResultArr() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length; j++) {
                for(int m = 0; m < resultArr.length; m++) {
                    if(resultArr[j][i] == 1 && resultArr[i][m] == 1) resultArr[j][m] = 1;
                }
            }
        }
    }

    public static void mkResultArr(int height, String[] values) {
        for(int i = 0; i < resultArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(values[i]);
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length][length];
    }
}
