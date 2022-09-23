import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game {
    static long winRate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long totalGame = Long.parseLong(st.nextToken());
        long winGame = Long.parseLong(st.nextToken());

        winRate = (winGame * 100) / totalGame;

        if(winRate >= 99) {
            System.out.println("-1");
        }
        else {
            long minValue = 0;
            long maxValue = totalGame;

            long midValue = 0, result = 0;
            
            while(minValue <= maxValue) {
                midValue = (minValue + maxValue) / 2;

                long value = ((winGame + midValue) * 100) / (totalGame + midValue);

                if(value == winRate) {
                    minValue = midValue + 1;
                }
                else {
                    result = midValue;
                    maxValue = midValue - 1;
                }
            }

            System.out.println(result);
        }
    }
}
