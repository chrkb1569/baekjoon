import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Piece_Of_Paper {

    private static int[][] resultArr;

    private static int MAX_VALUE = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 가로 크기

        initResultArr(N, M);

        for(int height = 0; height < N; height++) {
           String userInput = br.readLine();
           mkResultArr(height, userInput);
        }

        for(int bit = 0; bit < (1 << (N * M)); bit++) {
            int cost = getCost(bit);
            MAX_VALUE = Math.max(MAX_VALUE, cost);
        }

        System.out.println(MAX_VALUE);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(int height, String userInput) {
        char[] inputArr = userInput.toCharArray();

        for(int index = 0; index < inputArr.length; index++) {
            resultArr[height][index] = inputArr[index] - '0';
        }
    }

    // bit 1 -> 가로, bit 0 -> 세로
    private static int getCost(int bitInfo) {
        int cost = 0;

        // 가로 조각 처리
        for(int height = 0; height < resultArr.length; height++) {
            int weight = 0;

            for(int width = 0; width < resultArr[0].length; width++) {
                int position = resultArr[0].length * height + width;

                if((bitInfo & (1 << position)) != 0) {
                    weight = weight * 10 + resultArr[height][width];
                    continue;
                }

                cost += weight;
                weight = 0;
            }

            cost += weight;
        }

        // 세로 조각 처리
        for(int width = 0; width < resultArr[0].length; width++) {
            int weight = 0;

            for(int height = 0; height < resultArr.length; height++) {
                int position = resultArr[0].length * height + width;

                if((bitInfo & (1 << position)) == 0) {
                    weight = weight * 10 + resultArr[height][width];
                    continue;
                }

                cost += weight;
                weight = 0;
            }

            cost += weight;
        }

        return cost;
    }
}