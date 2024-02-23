import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Switch {
    private static boolean[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        int student = Integer.parseInt(br.readLine());

        for(int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int type = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            processCommand(type, number);
        }

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new boolean[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            if(value == 0) {
                resultArr[index++] = false;
                continue;
            }

            resultArr[index++] = true;
        }
    }

    private static void processCommand(int type, int number) {
        if(type == 1) {
            processMale(number);
            return;
        }
        processFemale(number);
    }

    private static void processMale(int number) {
        for(int index = 0; index < resultArr.length; index++) {
            if((index + 1) % number == 0) resultArr[index] = !resultArr[index];
        }
    }

    private static void processFemale(int number) {
        int leftIndex = number - 1;
        int rightIndex = number - 1;
        int gap = 0;

        for(int index = 1; ;index++) {
            if(leftIndex - index < 0 || rightIndex + index >= resultArr.length) break;
            if(resultArr[leftIndex - index] != resultArr[rightIndex + index]) break;
            gap = index;
        }

        for(int index = leftIndex - gap; index <= rightIndex + gap; index++) {
            resultArr[index] = !resultArr[index];
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(int index = 0; index < resultArr.length; index++) {
            if(resultArr[index]) {
                sb.append(1).append(" ");
            }
            else sb.append(0).append(" ");

            if((index + 1) % 20 == 0) sb.append("\n");
        }

        System.out.println(sb);
    }
}
