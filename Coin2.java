import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Coin2 {
    public static long[] resultArr;
    public static int[] coinArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int coins = Integer.parseInt(infoArr[0]);
        int total = Integer.parseInt(infoArr[1]);

        initCoinArr(coins);
        initResultArr(total);

        for(int i = 0; i < coins; i++) {
            coinArr[i] = Integer.parseInt(br.readLine());
        }

        mkResultArr();

        System.out.println(resultArr[total]);
    }

    public static void mkResultArr() {
        for(int i = 0; i < coinArr.length; i++) {
            int coin = coinArr[i];

            for(int j = coin; j < resultArr.length; j++) {
                resultArr[j] += resultArr[j-coin];
            }
        }
    }

    public static void initResultArr(int length) {
        resultArr = new long[length + 1];
        resultArr[0] = 1;
    }

    public static void initCoinArr(int coin) {
        coinArr = new int[coin];
    }
}
