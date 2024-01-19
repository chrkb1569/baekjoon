import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class LCS_2 {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstInput = br.readLine();
        String secondInput = br.readLine();

        initResultArr(firstInput.length(), secondInput.length());
        mkResultArr(firstInput, secondInput);

        int length = resultArr[firstInput.length()][secondInput.length()];

        printResult(secondInput, secondInput.length(), firstInput.length(), length);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height+1][width+1];
    }

    private static void mkResultArr(String v1, String v2) {
        for(int i = 1; i < resultArr.length; i++) {
            for(int j = 1; j < resultArr[0].length; j++) {
                if(v1.charAt(i-1) == v2.charAt(j-1)) {
                    resultArr[i][j] = resultArr[i-1][j-1] + 1;
                    continue;
                }

                resultArr[i][j] = Math.max(resultArr[i-1][j], resultArr[i][j-1]);
            }
        }
    }

    private static void printResult(String value, int x, int y, int length) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> resultStack = new Stack<>();

        while(x > 0 && y > 0) {
            if(resultArr[y][x] == resultArr[y-1][x]) {
                y--;
                continue;
            }
            if(resultArr[y][x] == resultArr[y][x-1]) {
                x--;
                continue;
            }
            resultStack.push(value.charAt(x-1));
            x--;
            y--;
        }

        while(!resultStack.isEmpty()) {
            sb.append(resultStack.pop());
        }

        System.out.println(length);
        System.out.println(sb);
    }
}
