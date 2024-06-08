import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindrome_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            String word = br.readLine();

            if(isPalindrome(word)) {
                sb.append(0).append("\n");
                continue;
            }

            if(isPseudoPalindrome(word)) {
                sb.append(1).append("\n");
                continue;
            }

            sb.append(2).append("\n");
        }

        System.out.println(sb);
    }

    private static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length()-1;

        while(left < right) {
            char leftWord = word.charAt(left);
            char rightWord = word.charAt(right);

            if(leftWord != rightWord) return false;

            left++;
            right--;
        }

        return true;
    }

    private static boolean isPseudoPalindrome(String word) {
        int left = 0;
        int right = word.length()-1;

        while(left < right) {
            char leftWord = word.charAt(left);
            char rightWord = word.charAt(right);

            if(leftWord == rightWord) {
                left++;
                right--;
                continue;
            }

            return isPalindrome(word.substring(left, right)) || isPalindrome(word.substring(left + 1, right + 1));
        }

        return true;
    }
}
