import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teaching {
    private static int aIndex = 0;
    private static int nIndex = 13;
    private static int tIndex = 19;
    private static int iIndex = 8;
    private static int cIndex = 2;
    private static String[] resultArr;
    private static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int words = Integer.parseInt(st.nextToken());
        int letters = Integer.parseInt(st.nextToken()) - 5;

        if(letters < 0) {
            System.out.println(0);
            return;
        }

        if(letters == 26) {
            System.out.println(words);
            return;
        }

        initResultArr(words);

        for(int i = 0; i < words; i++) {
            resultArr[i] = br.readLine();
        }

        boolean[] visit = new boolean[26];

        visit[aIndex] = true;
        visit[nIndex] = true;
        visit[tIndex] = true;
        visit[iIndex] = true;
        visit[cIndex] = true;

        getPermutation(visit, 0, 0, letters);

        System.out.println(maxValue);
    }

    private static void initResultArr(int length) {
        resultArr = new String[length];
    }

    private static void getPermutation(boolean[] visit, int start, int current, int target) {
        if(current == target) {
            int readableCount = getReadableCount(visit);
            maxValue = Math.max(readableCount, maxValue);
            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, i+1, current + 1, target);
                visit[i] = false;
            }
        }
    }

    private static int getReadableCount(boolean[] visit) {
        int readableCount = 0;

        for(String value : resultArr) {
            if(isReadable(value, visit)) readableCount++;
        }

        return readableCount;
    }

    private static boolean isReadable(String value, boolean[] visit) {
        for(char word : value.toCharArray()) {
            if(!visit[word - 'a']) return false;
        }

        return true;
    }
}