import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lotto {

    public static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");

            int number = Integer.parseInt(st.nextToken());

            if(number == 0) break;

            resultArr = new int[number];
            boolean[] visit = new boolean[number];

            for(int i = 0; i < number; i++) {
                resultArr[i] = Integer.parseInt(st.nextToken());
            }

            printCombination(visit, 6, 0,0);
            System.out.println();
        }
    }

    public static void printCombination(boolean[] visit, int num, int start, int n) {
        if(num == n) {
            for(int i = 0; i < visit.length; i++) {
                if(visit[i]) System.out.print(resultArr[i] + " ");
            }
            System.out.println();

            return;
        }

        for(int i = start; i < resultArr.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                printCombination(visit, num, i, n+1);
                visit[i] = false;
            }
        }
    }
}
