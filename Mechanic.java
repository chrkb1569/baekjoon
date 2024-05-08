import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mechanic {
    private static boolean[] resultArr = new boolean[1001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        mkResultArr(st);

        int count = 0;

        for(int index = 1; index < resultArr.length; index++) {
            if(!resultArr[index]) continue;
            if(index + L - 1 >= resultArr.length) continue;

            count++;

            for(int start = index; start < index + L; start++) {
                resultArr[start] = false;
            }
        }

        for(int index = resultArr.length-1; index >= 0; index--) {
            if(!resultArr[index]) continue;
            if(index - L + 1 < 0) continue;

            count++;

            for(int start = index; start >= index - L + 1; start--) {
                resultArr[start] = false;
            }
        }

        System.out.println(count);
    }

    private static void mkResultArr(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            int index = Integer.parseInt(st.nextToken());
            resultArr[index] = true;
        }
    }
}
