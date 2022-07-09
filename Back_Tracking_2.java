import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_Tracking_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int total = Integer.parseInt(st.nextToken());
        int quantity = Integer.parseInt(st.nextToken());

        int[] values = new int[total];
        boolean[] visit = new boolean[total];
        Generate_Values(total, values);

        combination(values, visit, 0, total, quantity);

    }

    private static void Generate_Values(int total, int[] values) {
        for(int i = 0; i < total; i++) {
            values[i] = i+1;
        }
    }

    public static void combination(int[] values, boolean[] visit, int start, int total, int r) {
        if(r == 0) {
            print(values, visit);
            return;
        }

        for(int i = start; i < total; i++) {
            if(visit[i] != true) {
                visit[i] = true;
                combination(values, visit, i+1, total, r-1);
                visit[i] = false;
            }
        }
    }

    public static void print(int[] values, boolean[] visit) {

        for(int k = 0; k < visit.length; k++) {
            if(visit[k] == true) {
                System.out.print(values[k] + " ");
            }
        }
        System.out.println();
    }
}
