import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DNA_Password {
    private static char[] resultArr;
    private static int[] alphabetArr = new int[4];
    private static int[] windowArr = new int[4];
    private static int resultCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        initResultArr(S);

        String inputValue = br.readLine();

        st = new StringTokenizer(br.readLine(), " ");

        mkResultArr(inputValue);
        mkAlphabetArr(st);

        mkWindowArr(P);

        getResultCount(P);

        System.out.println(resultCount);
    }

    private static void initResultArr(int length) {
        resultArr = new char[length];
    }

    private static void mkResultArr(String value) {
        char[] charArr = value.toCharArray();

        for(int index = 0; index < charArr.length; index++) {
            resultArr[index] = charArr[index];
        }
    }

    private static void mkAlphabetArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            alphabetArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkWindowArr(int length) {
        for(int index = 0; index < length; index++) {
            int convertedIndex = convertToIndex(resultArr[index]);
            windowArr[convertedIndex]++;
        }
    }

    private static void getResultCount(int targetNumber) {
        for(int start = 0; start <= resultArr.length - targetNumber; start++) {
            boolean flag = false;

            for(int i = 0; i < 4; i++) {
                if(windowArr[i] < alphabetArr[i]) {
                    flag = true;
                    break;
                }
            }

            if(!flag) resultCount++;
            if(start == resultArr.length - targetNumber) break;

            windowArr[convertToIndex(resultArr[start])]--;
            windowArr[convertToIndex(resultArr[start + targetNumber])]++;
        }
    }

    private static int convertToIndex(char value) {
        if(value == 'A') return 0;
        if(value == 'C') return 1;
        if(value == 'G') return 2;
        return 3;
    }
}