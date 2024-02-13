import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ManageMoney {
    private static int[] valueArr;
    private static int minValue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initValueArr(N);

        for(int i = 0; i < N; i++) {
            valueArr[i] = Integer.parseInt(br.readLine());
        }

        binarySearch(M);

        System.out.println(minValue);
    }

    private static void initValueArr(int length) {
        valueArr = new int[length];
    }

    private static void binarySearch(int M) {
        int low = Arrays.stream(valueArr).max().getAsInt();
        int high = 1_000_000_000;

        while(low <= high) {
            int mid = (low + high) / 2;
            int withdrawCount = getWithdrawCount(mid);

            if(withdrawCount <= M) {
                minValue = mid;
                high = mid - 1;
                continue;
            }

            low = mid + 1;
        }
    }

    private static int getWithdrawCount(int withdrawCost) {
        int withdrawCount = 1;
        int money = withdrawCost;

        for(int i = 0; i < valueArr.length; i++) {
            int cost = valueArr[i];

            money -= cost;

            if(money < 0) {
                withdrawCount++;
                money = withdrawCost - cost;
            }
        }

        return withdrawCount;
    }
}
