import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KevinBacon {
    public static long[][] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] valueArr = br.readLine().split(" ");

        int person = Integer.parseInt(valueArr[0]);
        int testCase = Integer.parseInt(valueArr[1]);

        initResultArr(person);

        for(int i = 0; i < testCase; i++) {
            valueArr = br.readLine().split(" ");
            mkResultArr(valueArr);
        }

        fillResultArr();

        System.out.println(getPersonNumber());
    }

    public static int getPersonNumber() {
        int personNumber = 0, minValue = Integer.MAX_VALUE;

        for(int i = 1; i < resultArr.length; i++) {
            int sum = 0;

            for(int j = 1; j < resultArr.length; j++) {
                sum += resultArr[i][j];
            }

            if(sum < minValue) {
                minValue = sum;
                personNumber = i;
            }
        }

        return personNumber;
    }

    public static void fillResultArr() {
        for(int i = 1; i < resultArr.length; i++) {
            for(int j = 1; j < resultArr.length; j++) {
                for(int k = 1; k < resultArr.length; k++) {
                    resultArr[j][k] = Math.min(resultArr[j][k], resultArr[j][i] + resultArr[i][k]);
                }
            }
        }
    }

    public static void mkResultArr(String[] valueArr) {
        int p1 = Integer.parseInt(valueArr[0]);
        int p2 = Integer.parseInt(valueArr[1]);

        resultArr[p1][p2] = 1;
        resultArr[p2][p1] = 1;
    }

    public static void initResultArr(int length) {
        resultArr = new long[length + 1][length + 1];

        for(int i = 1; i < resultArr.length; i++) {
            for(int j = 1; j < resultArr.length; j++) {
                if(i == j) continue;
                resultArr[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}
