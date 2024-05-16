import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class AvoidAndSurvive {
    private static String[][] resultArr;
    private static List<int[]> teacherList = new LinkedList<>();
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N, N);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        dfs(0);

        printResult();
    }

    private static void initResultArr(int height, int width) {
        resultArr = new String[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            String value = st.nextToken();
            resultArr[height][width] = value;

            if(value.equals("T")) teacherList.add(new int[]{height, width});

            width++;
        }
    }

    private static void dfs(int count) {
        if(count == 3) {
            if(checkValidation()) flag = true;
            return;
        }

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr.length; width++) {
                if(flag) break;
                String currentValue = resultArr[height][width];

                if(currentValue.equals("X")) {
                    resultArr[height][width] = "O";
                    dfs(count + 1);
                    resultArr[height][width] = "X";
                }
            }
        }
    }

    private static boolean checkValidation() {
        for(int[] arr : teacherList) {
            int x = arr[1];
            int y = arr[0];

            for(int index = x-1; index >= 0; index--) {
                String currentValue = resultArr[y][index];

                if(currentValue.equals("O")) break;
                if(currentValue.equals("S")) return false;
            }

            for(int index = x + 1; index < resultArr.length; index++) {
                String currentValue = resultArr[y][index];

                if(currentValue.equals("O")) break;
                if(currentValue.equals("S")) return false;
            }

            for(int index = y - 1; index >= 0; index--) {
                String currentValue = resultArr[index][x];

                if(currentValue.equals("O")) break;
                if(currentValue.equals("S")) return false;
            }

            for(int index = y + 1; index < resultArr.length; index++) {
                String currentValue = resultArr[index][x];

                if(currentValue.equals("O")) break;
                if(currentValue.equals("S")) return false;
            }
        }

        return true;
    }

    private static void printResult() {
        if(flag) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }
}
