import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Count_Of_Paper {

    private static byte[][] resultArr;

    private static int COUNT_OF_MINUS = 0;
    private static int COUNT_OF_ZERO = 0;
    private static int COUNT_OF_PLUS = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int height = 0; height < resultArr.length; height++) {
            st = new StringTokenizer(br.readLine(), " ");

            mkResultArr(height, st);
        }

        dfs(0, 0, N);

        System.out.println(COUNT_OF_MINUS);
        System.out.println(COUNT_OF_ZERO);
        System.out.println(COUNT_OF_PLUS);
    }

    private static void initResultArr(int length) {
        resultArr = new byte[length][length];
    }

    private static void mkResultArr(int height, StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Byte.parseByte(st.nextToken());
        }
    }

    private static void dfs(int height, int width, int length) {
        if(checkValidation(height, width, length)) {
            updateCountByValue(resultArr[height][width]);
            return;
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                dfs(height + i * length / 3, width + j * length / 3, length / 3);
            }
        }
    }

    private static boolean checkValidation(int height, int width, int length) {
        byte std = resultArr[height][width];

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(resultArr[height + i][width + j] != std) return false;
            }
        }

        return true;
    }

    private static void updateCountByValue(byte value) {
        if(value == -1) COUNT_OF_MINUS++;
        if(value == 0) COUNT_OF_ZERO++;
        if(value == 1) COUNT_OF_PLUS++;
    }
}