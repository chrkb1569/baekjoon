import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class List_Of_Phone_Number {
    private static String[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            int N = Integer.parseInt(br.readLine());

            initResultArr(N);

            for(int j = 0; j < N; j++) {
                resultArr[j] = br.readLine();
            }

            Arrays.sort(resultArr);

            if(checkValidation()) {
                sb.append("YES").append("\n");
                continue;
            }

            sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new String[length];
    }

    private static boolean checkValidation() {
        for(int index = 0; index < resultArr.length - 1; index++) {
            if(resultArr[index+1].startsWith(resultArr[index])) return false;
        }

        return true;
    }
}