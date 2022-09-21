import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tired {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long timeCount = 0, workCount = 0, tiredCount = 0;

        long generatedTired = Long.parseLong(st.nextToken());
        long achieve = Long.parseLong(st.nextToken());
        long rest = Long.parseLong(st.nextToken());
        long burnOut = Long.parseLong(st.nextToken());

        while(timeCount < 24) {
            if (tiredCount + generatedTired <= burnOut) {
                workCount += achieve;
                tiredCount += generatedTired;
                timeCount++;
            }

            else {
                tiredCount -= rest;

                if(tiredCount < 0) {
                    tiredCount = 0;
                }

                timeCount++;
            }
        }

        System.out.println(workCount);
    }
}
