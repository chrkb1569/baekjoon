import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sticker {
    public static StringBuilder sb = new StringBuilder();
    public static int[][] resultArr;
    public static int[][] pathArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            int length = Integer.parseInt(br.readLine());

            String[] firstArr = br.readLine().split(" ");
            String[] secondArr = br.readLine().split(" ");

            initArr(length);
            mkResultArr(firstArr, secondArr);
            mkPathArr();
        }

        System.out.println(sb);
    }

    public static void mkPathArr() {
        for(int i = 0; i < resultArr[0].length; i++) {
            pathArr[0][i] += resultArr[0][i];
            pathArr[1][i] += resultArr[1][i];

            int firstValue = pathArr[0][i];
            int secondValue = pathArr[1][i];

            if(i + 2 < resultArr[0].length) {
                pathArr[0][i+2] = Math.max(pathArr[0][i+2], firstValue);
                pathArr[1][i+2] = Math.max(pathArr[1][i+2], firstValue);
                pathArr[0][i+2] = Math.max(pathArr[0][i+2], secondValue);
                pathArr[1][i+2] = Math.max(pathArr[1][i+2], secondValue);
            }

            if(i + 1 < resultArr[0].length) {
                pathArr[1][i+1] = Math.max(pathArr[1][i+1], firstValue);
                pathArr[0][i+1] = Math.max(pathArr[0][i+1], secondValue);
            }
        }

        sb.append(Math.max(pathArr[0][resultArr[0].length - 1], pathArr[1][resultArr[0].length - 1])).append("\n");
    }

    public static void mkResultArr(String[] first, String[] second) {
        for(int i = 0; i < resultArr[0].length; i++) {
            resultArr[0][i] = Integer.parseInt(first[i]);
            resultArr[1][i] = Integer.parseInt(second[i]);
        }
    }

    public static void initArr(int length) {
        resultArr = new int[2][length];
        pathArr = new int[2][length];
    }
}

//2
//5
//50 10 100 20 40
//30 50 70 10 60
//7
//10 30 10 50 100 20 40
//20 40 30 50 60 20 80

// 260
// 290

// 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
// 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다.
// 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다.
// 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
