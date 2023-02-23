import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Integer_Triangle {

    public static int[][] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int length = Integer.parseInt(br.readLine());

        initResultArr(length);

        for(int i = 0; i < resultArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(i, st);
        }

        getResult();

        System.out.println(resultArr[0][0]);
    }

    public static void getResult() {
        for(int i = resultArr.length-1; i >0; i--) {
            for(int j = 1; j < resultArr.length; j++) {
                int num1 = resultArr[i][j-1];
                int num2 = resultArr[i][j];

                resultArr[i-1][j-1] = Math.max(resultArr[i-1][j-1] + num1, resultArr[i-1][j-1] + num2);
            }
        }
    }

    public static void mkResultArr(int index, StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index][numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initResultArr(int value) {
        resultArr = new int[value][value];
    }
}
