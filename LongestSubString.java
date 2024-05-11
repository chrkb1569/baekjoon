import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LongestSubString {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstString = br.readLine();
        String secondString = br.readLine();

        System.out.println(getSubStringLength(firstString, secondString));
    }

    private static int getSubStringLength(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();

        int[][] resultArr = new int[length1 + 1][length2 + 1];
        int length = 0;

        for(int height = 1; height <= length1; height++) {
            for(int width = 1; width <= length2; width++) {
                if(str1.charAt(height-1) == str2.charAt(width-1)) {
                    resultArr[height][width] = resultArr[height-1][width-1] + 1;
                }

                length = Math.max(length, resultArr[height][width]);
            }
        }

        return length;
    }
}