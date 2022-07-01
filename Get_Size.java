import java.io.*;
import java.util.*;

public class Get_Size {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2]; // 첫 번째는 몸무게 두 번째는 키

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            int rank = 1;

            for(int j = 0; j < N; j++) {
                if(i == j ) {
                    continue;
                }

                else {
                    if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
                        rank++;
                    }
                }
            }

            bw.write(rank + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}