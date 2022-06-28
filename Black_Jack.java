import java.io.*;
import java.util.StringTokenizer;

public class Black_Jack {

    static int[] result;
    static int tool = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        result = new int[N * (N-1) * (N-2) / 6];
        boolean[] visit = new boolean[N];
        int[] values = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        combination(values, visit, 0, N, 3);

        int min = Integer.MAX_VALUE;
        int total = 0;

        for(int i = 0; i < result.length; i++) {
            if((M - result[i] <= min) && (M - result[i] >= 0)) {
                min = M - result[i];
                total = result[i];
            }
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void combination(int[] arr, boolean[] visit, int start, int N, int r) {
        if(r == 0) {
            print(arr, visit, N);
            return;
        }

        for(int i = start; i < N; i++) {
            visit[i] = true;
            combination(arr, visit, i+1, N, r-1);
            visit[i] = false;
        }
    }

    public static void print(int[] arr, boolean[] visit, int N) {
        int sum = 0;
        for(int i = 0; i < N; i++) {
            if(visit[i] == true) {
                sum += arr[i];
            }
        }
        result[tool++] = sum;
    }
}