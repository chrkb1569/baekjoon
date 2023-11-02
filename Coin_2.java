import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Coin_2 {
    public static int[] coinArr;
    public static long[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int coin = Integer.parseInt(infoArr[0]);
        int length = Integer.parseInt(infoArr[1]);

        initCoinArr(coin);
        initResultArr(length);

        for(int i = 0; i < coin; i++) {
            String coinValue = br.readLine();
            coinArr[i] = Integer.parseInt(coinValue);
        }

        mkResultArr();

        printResult(length);
    }

    public static void printResult(int length) {
        long value = resultArr[length];

        System.out.println(value);
    }

    public static void mkResultArr() {
        for(int i = 1; i < resultArr.length; i++) {
            for(int j = 0; j < coinArr.length; j++) {
                long coinValue = coinArr[j];

                if(i - coinValue < 0) continue;
                if(i % coinValue == 0 && resultArr[i] == -1) {
                    resultArr[i] = i / coinValue;
                    continue;
                }
                if(i % coinValue == 0) resultArr[i] = Math.min(resultArr[i], i / coinValue);

                long value = resultArr[(int)(i - coinValue)];

                if(value == -1) continue;
                if(resultArr[i] == -1) {
                    resultArr[i] = value + 1;
                    continue;
                }

                resultArr[i] = Math.min(resultArr[i], value + 1);
            }
        }
    }

    public static void initResultArr(int length) {
        resultArr = new long[length + 1];

        Arrays.fill(resultArr, -1);

        resultArr[0] = 0;
    }

    public static void initCoinArr(int coin) {
        coinArr = new int[coin];
    }
}
