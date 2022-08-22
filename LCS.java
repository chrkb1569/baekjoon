import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {

    static char[] value1;
    static char[] value2;
    static Integer[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        value1 = br.readLine().toCharArray();
        value2 = br.readLine().toCharArray();

        arr = new Integer[value1.length][value2.length];

        System.out.println(getResult(value1.length-1, value2.length-1));
    }

    public static int getResult(int x, int y) {

        if(x == -1 || y == -1) {
            return 0;
        }

        if(arr[x][y] == null) {
            arr[x][y] = 0;

            if(value1[x] == value2[y]) {
                arr[x][y] = getResult(x-1, y-1) + 1;
            }

            else {
                arr[x][y] = Math.max(getResult(x-1, y), getResult(x, y-1));
            }
        }

        return arr[x][y];
    }
}
