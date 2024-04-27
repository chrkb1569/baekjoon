import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DecreasingNumber {
    private static List<Long> resultList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N <= 10) {
            System.out.println(N);
            return;
        }

        if(N > 1022) {
            System.out.println(-1);
            return;
        }

        for(int index = 0; index < 10; index++) {
            mkResultList(index, 1);
        }

        Collections.sort(resultList);

        System.out.println(resultList.get(N));
    }

    private static void mkResultList(long number, int index) {
        if(index > 10) return;

        resultList.add(number);

        for(int add = 0; add < number % 10; add++) {
            mkResultList(number * 10 + add, index + 1);
        }
    }
}
