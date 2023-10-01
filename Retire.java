import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Retire {
    public static int[] resultArr;
    public static int[] dayArr;
    public static int[] costArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int length = Integer.parseInt(br.readLine());

        initArr(length);

        for(int i = 1; i <= length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkArr(st, i);
        }

        mkResultArr();

        System.out.println(resultArr[length + 1]);
    }

    public static void mkResultArr() {
        for(int i = 1; i < resultArr.length; i++) {
            if(i >= dayArr.length) {
                resultArr[i] = Math.max(resultArr[i], resultArr[i-1]);
                continue;
            }

            int day = dayArr[i];
            int cost = costArr[i];

            resultArr[i] = Math.max(resultArr[i], resultArr[i-1]);

            if(i + day >= resultArr.length) continue;

            resultArr[i + day] = Math.max(resultArr[i + day], resultArr[i] + cost);
        }
    }

    public static void mkArr(StringTokenizer st, int index) {
        int day = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        dayArr[index] = day;
        costArr[index] = cost;
    }

    public static void initArr(int length) {
        resultArr = new int[length + 2];
        dayArr = new int[length + 1];
        costArr = new int[length + 1];
    }
}

/**
 * 7
 * 3 10
 * 5 20
 * 1 10
 * 1 20
 * 2 15
 * 4 40
 * 2 200
 *
 * 45
 *
 * 10
 * 1 1
 * 1 2
 * 1 3
 * 1 4
 * 1 5
 * 1 6
 * 1 7
 * 1 8
 * 1 9
 * 1 10
 *
 * 55
 *
 * 10
 * 5 10
 * 5 9
 * 5 8
 * 5 7
 * 5 6
 * 5 10
 * 5 9
 * 5 8
 * 5 7
 * 5 6
 *
 * 20
 *
 * 10
 * 5 50
 * 4 40
 * 3 30
 * 2 20
 * 1 10
 * 1 10
 * 2 20
 * 3 30
 * 4 40
 * 5 50
 *
 * 90
 */