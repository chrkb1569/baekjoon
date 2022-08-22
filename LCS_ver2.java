import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS_ver2 {

    static char[] value1;
    static char[] value2;

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        value1 = br.readLine().toCharArray();
        value2 = br.readLine().toCharArray();

        arr = new int[value1.length + 1][value2.length + 1];

        for(int i = 1; i <= value1.length; i++) {
            for(int j = 1; j <= value2.length; j++) {
                if(value1[i - 1] == value2[j - 1]) {
                    arr[i][j] = arr[i-1][j-1] + 1;
                }

                else {
                    arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
                }
            }
        }
        System.out.println(arr[value1.length][value2.length]);
    }
}
