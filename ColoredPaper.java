import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColoredPaper {

    static int[][] resultArr = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int paper = Integer.parseInt(br.readLine());
        int count = 0;

        for(int i = 0; i < paper; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            mkResultArr(width, height);
        }

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                count += resultArr[i][j];
            }
        }

        System.out.println(count);
    }

    public static void mkResultArr(int width, int height) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                resultArr[height + i][width + j] = 1;
            }
        }
    }
}
