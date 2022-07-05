import java.io.*;
import java.util.StringTokenizer;

public class Repaint_the_Chess {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height, length, result;
        int sum = 0, sum2 = 0, min = Integer.MAX_VALUE;

        height = Integer.parseInt(st.nextToken());
        length = Integer.parseInt(st.nextToken());

        String[] arr = new String[height];

        for(int i = 0; i < height; i++) {
            arr[i] = br.readLine();
        }

        for(int i = 0; i <= height-8; i++) {
            for(int j = 0; j <=length-8; j++) {
                String str1 = arr[i].substring(j, j+8);
                String str2 = arr[i+1].substring(j, j+8);
                String str3 = arr[i+2].substring(j, j+8);
                String str4 = arr[i+3].substring(j, j+8);
                String str5 = arr[i+4].substring(j, j+8);
                String str6 = arr[i+5].substring(j, j+8);
                String str7 = arr[i+6].substring(j, j+8);
                String str8 = arr[i+7].substring(j, j+8);

                sum += check_even(str1);
                sum += check_odd(str2);
                sum += check_even(str3);
                sum += check_odd(str4);
                sum += check_even(str5);
                sum += check_odd(str6);
                sum += check_even(str7);
                sum += check_odd(str8);

                sum2 += check_even_2(str1);
                sum2 += check_odd_2(str2);
                sum2 += check_even_2(str3);
                sum2 += check_odd_2(str4);
                sum2 += check_even_2(str5);
                sum2 += check_odd_2(str6);
                sum2 += check_even_2(str7);
                sum2 += check_odd_2(str8);

                if(sum <= sum2) {
                    result = sum;
                }
                else result = sum2;

                if(result <= min) {
                    min = result;
                }
                sum = 0;
                sum2 = 0;
            }
        }

        System.out.println(min);
    }

    public static int check_odd(String test) {
        String str = "BWBWBWBW";

        int not_correct = 0;

        for(int i = 0; i < str.length(); i++) {
            if(test.charAt(i) + 0 == str.charAt(i) + 0) {
                continue;
            }
            else not_correct++;
        }

        return not_correct;
    }

    public static int check_even(String test) {
        String str = "WBWBWBWB";
        int not_correct = 0;

        for(int i = 0; i < str.length(); i++) {
            if(test.charAt(i) == str.charAt(i)) {
                continue;
            }
            else not_correct++;
        }

        return not_correct;
    }

    public static int check_odd_2(String test) {
        String str = "WBWBWBWB";

        int not_correct = 0;

        for(int i = 0; i < str.length(); i++) {
            if(test.charAt(i) + 0 == str.charAt(i) + 0) {
                continue;
            }
            else not_correct++;
        }

        return not_correct;
    }

    public static int check_even_2(String test) {
        String str = "BWBWBWBW";
        int not_correct = 0;

        for(int i = 0; i < str.length(); i++) {
            if(test.charAt(i) == str.charAt(i)) {
                continue;
            }
            else not_correct++;
        }

        return not_correct;
    }
}
/*8 8









*/