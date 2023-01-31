import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_And_B {

    public static boolean able = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String originString = br.readLine();
        String targetString = br.readLine();

        findCorrelation(originString, targetString);
        printResult();
    }

    public static void findCorrelation(String targetString, String originString) {
        if(targetString.length() == originString.length()) {
            if(targetString.equals(originString)) able = true;
            return;
        }

        char lastChar = originString.charAt(originString.length() - 1);

        if(lastChar == 'A') {
            originString = originString.substring(0, originString.length() - 1);

            findCorrelation(targetString, originString);
        }
        else {
            originString = originString.substring(0, originString.length() - 1);

            StringBuilder sb = new StringBuilder(originString);

            findCorrelation(targetString, sb.reverse().toString());
        }
    }

    public static void printResult() {
        if(able) System.out.println("1");
        else System.out.println("0");
    }
}
