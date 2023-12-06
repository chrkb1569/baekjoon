import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Floyd {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int testCase = Integer.parseInt(br.readLine());

        initArr(N);

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st);
        }

        floydAlgorithm();

        printResult();
    }

    private static void initArr(int length) {
        resultArr = new int[length+1][length+1];

        for(int i = 0; i <= length; i++) {
            Arrays.fill(resultArr[i], 10000000);
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length; j++) {
                if(i == j) resultArr[i][j] = 0;
            }
        }
    }

    private static void mkResultArr(StringTokenizer st) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        resultArr[from][to] = Math.min(weight, resultArr[from][to]);
    }

    private static void floydAlgorithm() {
        for(int m = 0; m < resultArr.length; m++) {
            for(int i = 0; i < resultArr.length; i++) {
                for(int j = 0; j < resultArr.length; j++) {
                    if(i == j || i == m || m == j) continue;

                    int value = resultArr[i][m] + resultArr[m][j];
                    resultArr[i][j] = Math.min(resultArr[i][j], value);
                }
            }
        }
    }

    private static void printResult() {
        for(int i = 1; i < resultArr.length; i++) {
            for(int j = 1; j < resultArr.length; j++) {
                if(resultArr[i][j] == 10000000) {
                    System.out.print("0 ");
                    continue;
                }
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
