import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EmotionalDistance {
    private static String[] resultArr;
    private static int minDistance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine()); // student
            st = new StringTokenizer(br.readLine(), " ");

            if(N > 32) {
                sb.append(0).append("\n");
                continue;
            }

            initResultArr(N);
            mkResultArr(st);

            boolean[] visitArr = new boolean[N];
            minDistance = Integer.MAX_VALUE;

            getPermutation(visitArr, 0, 0, 3);

            sb.append(minDistance).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new String[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = st.nextToken();
        }
    }

    private static void getPermutation(boolean[] visitArr, int start, int current, int target) {
        if(current == target) {
            int distance = getDistance(visitArr);
            minDistance = Math.min(minDistance, distance);
            return;
        }

        for(int index = start; index < visitArr.length; index++) {
            if(!visitArr[index]) {
                visitArr[index] = true;
                getPermutation(visitArr, index + 1, current + 1, target);
                visitArr[index] = false;
            }
        }
    }

    private static int getDistance(boolean[] visitArr) {
        int distance = 0;

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;

        String mbti1 = "";
        String mbti2 = "";
        String mbti3 = "";

        for(int index = 0; index < visitArr.length; index++) {
            if(visitArr[index] && !flag1) {
                mbti1 = resultArr[index];
                flag1 = true;
                continue;
            }

            if(visitArr[index] && !flag2) {
                mbti2 = resultArr[index];
                flag2 = true;
                continue;
            }

            if(visitArr[index] && !flag3) {
                mbti3 = resultArr[index];
                break;
            }
        }

        if(mbti1 == "" || mbti2 == "" || mbti3 == "") return Integer.MAX_VALUE;

        for(int index = 0; index < 4; index++) {
            if(mbti1.charAt(index) != mbti2.charAt(index)) distance++;
            if(mbti1.charAt(index) != mbti3.charAt(index)) distance++;
            if(mbti3.charAt(index) != mbti2.charAt(index)) distance++;
        }

        return distance;
    }
}
