import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RemoteController {
    private static boolean[] buttonArr = new boolean[10];
    private static int channelGap = Integer.MAX_VALUE;
    private static int bestChannel = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int channel = Integer.parseInt(br.readLine());
        int brokenButton = Integer.parseInt(br.readLine());

        Arrays.fill(buttonArr, true);

        if(brokenButton != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            initButtonArr(st);
        }

        for(int i = 1; i <= 6; i++) {
            int[] resultArr = new int[i];
            getPermutation(resultArr, 0, i, channel);
        }

        printResult(channel);
    }

    private static void initButtonArr(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            int index = Integer.parseInt(st.nextToken());
            buttonArr[index] = false;
        }
    }

    private static void getPermutation(int[] resultArr, int current, int target, int targetValue) {
        if(current == target) {
            int channel = convertToValue(resultArr, target);
            int gap = Math.abs(targetValue - channel);

            if(gap < channelGap) {
                channelGap = gap;
                bestChannel = channel;
            }

            return;
        }

        for(int i = 0; i < buttonArr.length; i++) {
            if(buttonArr[i]) {
                resultArr[current] = i;
                getPermutation(resultArr, current + 1, target, targetValue);
            }
        }
    }

    private static int convertToValue(int[] resultArr, int length) {
        int div = (int)Math.pow(10, length - 1);
        int result = 0;

        for(int i = 0; i < resultArr.length; i++) {
            result += (div * resultArr[i]);
            div /= 10;
        }

        return result;
    }

    private static void printResult(int targetChannel) {
        if(targetChannel == 100) {
            System.out.println(0);
            return;
        }

        int length = getChannelLength();

        int gapFromTarget = Math.abs(targetChannel - 100);
        int gapFromBest = Math.abs(bestChannel - targetChannel) + length;

        System.out.println(bestChannel + " " + gapFromTarget + " " + gapFromBest);

        System.out.println(Math.min(gapFromBest, gapFromTarget));
    }

    private static int getChannelLength() {
        int div = 100_000;
        int value = bestChannel / div;
        int length = 6;

        while(value <= 0) {
            div /= 10;
            if(div == 0) return 1;
            length--;
            value = bestChannel / div;
        }

        return length;
    }
}