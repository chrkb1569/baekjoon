import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Antenna {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        Arrays.sort(resultArr);

        int index = getIndex();

        System.out.println(index);
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

    private static int getIndex() {
        int length = resultArr.length;

        if(length % 2 == 0) return resultArr[length/2-1];
        return resultArr[length/2];
    }
}
