import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class GoodNumber {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        Arrays.sort(resultArr);

        int goodCount = getGoodCount();

        System.out.println(goodCount);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static int getGoodCount() {
        int count = 0;

        for(int index = 0; index < resultArr.length; index++) {
            int left = 0;
            int right = resultArr.length-1;

            int targetNumber = resultArr[index];

            while(true) {
                if(left == index) left++;
                if(right == index) right--;

                if(left >= right) break;

                int lowerNumber = resultArr[left];
                int highNumber = resultArr[right];

                if(lowerNumber + highNumber > targetNumber) {
                    right--;
                    continue;
                }

                if(lowerNumber + highNumber < targetNumber) {
                    left++;
                    continue;
                }

                count++;
                break;
            }
        }

        return count;
    }
}
