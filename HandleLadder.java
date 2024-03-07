import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HandleLadder {
    private static int[][] resultArr;
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // width
        int M = Integer.parseInt(st.nextToken()); // ladder
        int H = Integer.parseInt(st.nextToken()); // height

        initResultArr(H, N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st);
        }

        for(int ladder = 0; ladder <= 3; ladder++) {
            if(flag) break;
            getPermutation(0, 0, ladder);
        }

        if(!flag) System.out.println(-1);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st) {
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        resultArr[height - 1][width - 1] = 1;
    }

    private static void getPermutation(int height, int current, int target) {
        if(flag) return;

        if(current == target) {
            if(checkValidation()) {
                flag = true;
                System.out.println(target);
            }
            return;
        }

        for(int h = height; h < resultArr.length; h++) {
            for(int width = 0; width < resultArr[0].length - 1; width++) {
                int value = resultArr[h][width];

                if(value == 1) continue;
                if(resultArr[h][width + 1] == 1) continue;
                if(width - 1 >= 0 && resultArr[h][width - 1] == 1) continue;

                resultArr[h][width] = 1;
                getPermutation(h, current + 1, target);
                resultArr[h][width] = 0;
            }
        }
    }

    private static boolean checkValidation() {
        for(int startNumber = 0; startNumber < resultArr[0].length; startNumber++) {
            int width = startNumber;
            int height = 0;

            while(height <= resultArr.length-1) {
                int currentValue = resultArr[height][width];

                if(currentValue == 1) {
                    height++;
                    width++;
                    continue;
                }

                if(width - 1 >= 0 && resultArr[height][width-1] == 1) {
                    height++;
                    width--;
                    continue;
                }

                height++;
            }

            if(width != startNumber) return false;
        }

        return true;
    }
}