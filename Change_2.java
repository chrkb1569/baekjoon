import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Change_2 {
    private static final int TOTAL_MONEY = 1_000;
    private static int[] moneyArr = {500, 100, 50, 10, 5, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int userInput = Integer.parseInt(br.readLine());

        int change = getChange(TOTAL_MONEY - userInput);

        System.out.println(change);
    }

    private static int getChange(int money) {
        int count = 0;

        for(int index = 0; index < moneyArr.length; index++) {
            int changeCount = money / moneyArr[index];

            money -= moneyArr[index] * changeCount;
            count += changeCount;
        }

        return count;
    }
}