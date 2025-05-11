import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindDomino {

    private static final int[][] resultArr = new int[8][7];
    private static final boolean[][] visitArr = new boolean[8][7];
    private static final boolean[][] dominoArr = new boolean[7][7];

    private static int MATCHED_COUNT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int height = 0; height < 8; height++) {
            String userInput = br.readLine();

            for(int width = 0; width < 7; width++) {
                resultArr[height][width] = userInput.charAt(width) - '0';
            }
        }

        findMatchedCount(0, 0);

        System.out.println(MATCHED_COUNT);
    }

    private static void findMatchedCount(int height, int width) {
        if(height == 8) {
            MATCHED_COUNT++;
            return;
        }

        if(width == 7) {
            findMatchedCount(height + 1, 0);
            return;
        }

        if(!visitArr[height][width]) {
            visitArr[height][width] = true;
            int currentValue = resultArr[height][width];

            // 도미노를 가로로 놓는 경우
            placeDominoToRight(height, width, currentValue);

            // 도미노를 세로로 놓는 경우
            placeDominoToDown(height, width, currentValue);

            visitArr[height][width] = false;

            return;
        }

        findMatchedCount(height, width + 1);
    }

    private static void placeDominoToRight(int currentHeight, int currentWidth, int currentValue) {
        int nextWidth = currentWidth + 1;

        if(nextWidth >= 7 || currentHeight >= 8) return;
        if(visitArr[currentHeight][nextWidth]) return;

        int nextValue = resultArr[currentHeight][nextWidth];

        if(dominoArr[currentValue][nextValue] || dominoArr[nextValue][currentValue]) return;

        dominoArr[currentValue][nextValue] = true;
        dominoArr[nextValue][currentValue] = true;
        visitArr[currentHeight][nextWidth] = true;

        findMatchedCount(currentHeight, nextWidth);

        dominoArr[currentValue][nextValue] = false;
        dominoArr[nextValue][currentValue] = false;
        visitArr[currentHeight][nextWidth] = false;
    }

    private static void placeDominoToDown(int currentHeight, int currentWidth, int currentValue) {
        int nextHeight = currentHeight + 1;

        if(nextHeight >= 8 || currentWidth >= 7) return;
        if(visitArr[nextHeight][currentWidth]) return;

        int nextValue = resultArr[nextHeight][currentWidth];

        if(dominoArr[currentValue][nextValue] || dominoArr[nextValue][currentValue]) return;

        dominoArr[currentValue][nextValue] = true;
        dominoArr[nextValue][currentValue] = true;
        visitArr[nextHeight][currentWidth] = true;

        findMatchedCount(currentHeight, currentWidth + 1);

        dominoArr[currentValue][nextValue] = false;
        dominoArr[nextValue][currentValue] = false;
        visitArr[nextHeight][currentWidth] = false;
    }
}