import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Continuous_Sum_Of_Prime {
    private static int[] valueArr;
    private static boolean[] visitArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        initVisitArr(number);
        mkValueArr(number);

        int resultCount = getResultCount(number);

        System.out.println(resultCount);
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length + 1];

        visitArr[0] = true;
        visitArr[1] = true;
    }

    private static void mkValueArr(int number) {
        List<Integer> primeList = new LinkedList<>();

        for(int i = 2; i <= number; i++) {
            if(visitArr[i]) continue;

            primeList.add(i);

            for(int j = 1; i * j <= number; j++) {
                visitArr[i * j] = true;
            }
        }

        valueArr = primeList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getResultCount(int number) {
        int count = 0;

        for(int i = 0; i < valueArr.length; i++) {
            int sum = 0;

            for(int j = i; j < valueArr.length; j++) {
                sum += valueArr[j];

                if(sum == number) count++;
                if(sum > number) break;
            }
        }

        return count;
    }
}
