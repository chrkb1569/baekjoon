import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Function_Impl {

    public static StringBuilder sb = new StringBuilder();
    public static int[][][] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        initResultArr();

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1) break;

            sb.append("w(" + a + ", " + b + ", " + c + ") = " + getResult(a, b, c) + "\n");
        }

        System.out.println(sb.toString());
     }

     public static int getResult(int a, int b, int c) {
         if(validateRange(a, b, c) && (resultArr[a][b][c] != 0)) return resultArr[a][b][c];

        if(a <= 0 || b <= 0 || c <= 0) return 1;
        if(a > 20 || b > 20 || c > 20) return resultArr[20][20][20] = getResult(20, 20, 20);


        if(a < b && b < c)
            return resultArr[a][b][c] = getResult(a, b, c-1) + getResult(a, b-1, c-1) - getResult(a, b-1, c);
        return resultArr[a][b][c] = getResult(a-1, b, c) + getResult(a-1, b-1, c)
                + getResult(a-1, b, c-1) - getResult(a-1, b-1, c-1);
     }

     public static boolean validateRange(int a, int b, int c) {
        return (a >= 0 && b >= 0 && c >= 00) && (a < 21 && b < 21 && c < 21);
     }

     public static void initResultArr() {
        resultArr = new int[21][21][21];
     }
}
