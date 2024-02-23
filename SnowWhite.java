import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SnowWhite {
    private static StringBuilder sb = new StringBuilder();
    private static int[] resultArr = new int[9];
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int height = 0; height < 9; height++) {
            resultArr[height] = Integer.parseInt(br.readLine());
        }

        boolean[] visitArr = new boolean[9];

        getPermutation(visitArr, 0, 7);
    }

    private static void getPermutation(boolean[] visitArr, int current, int target) {
        if(flag) return;
        if(current == target) {
            int sum = getSum(visitArr);

            if(sum != 100) return;

            flag = true;

            for(int index = 0; index < visitArr.length; index++) {
                if(visitArr[index]) sb.append(resultArr[index]).append("\n");
            }

            System.out.println(sb);

            return;
        }

        for(int index = 0; index < visitArr.length; index++) {
            if(!visitArr[index]) {
                visitArr[index] = true;
                getPermutation(visitArr, current + 1, target);
                visitArr[index] = false;
            }
        }
    }

    private static int getSum(boolean[] visitArr) {
        int sum = 0;

        for(int i = 0; i < visitArr.length; i++) {
            if(visitArr[i]) sum += resultArr[i];
        }

        return sum;
    }
}
