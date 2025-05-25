import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FamousPrincess {

    private static final char[][] resultArr = new char[5][5];

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private static int PRINCESS_COUNT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int height = 0; height < 5; height++) {
            String userInput = br.readLine();
            mkResultArr(height, userInput);
        }

        List<int[]> indexList = new ArrayList<>();
        List<int[]> selectedList = new ArrayList<>();

        for(int height = 0; height < 5; height++) {
            for(int width = 0; width < 5; width++) {
                indexList.add(new int[]{height, width});
            }
        }

        getPermutation(indexList, selectedList, 0, 0);

        System.out.println(PRINCESS_COUNT);
    }

    private static void mkResultArr(int height, String userInput) {
        resultArr[height] = userInput.toCharArray();
    }

    private static void getPermutation(List<int[]> indexList, List<int[]> selectedList, int start, int count) {
        if(count == 7) {
            if(!checkValidation(selectedList)) return;
            PRINCESS_COUNT++;
            return;
        }

        for(int index = start; index < indexList.size(); index++) {
            selectedList.add(indexList.get(index));
            getPermutation(indexList, selectedList, index + 1, count + 1);
            selectedList.remove(selectedList.size() - 1);
        }
    }

    private static boolean checkValidation(List<int[]> selectedList) {
        int sCount = 0;
        boolean[][] visitArr = new boolean[5][5];

        for(int[] arr : selectedList) {
            int y = arr[0];
            int x = arr[1];

            if(visitArr[y][x]) return false;
            if(resultArr[y][x] == 'S') sCount++;
            visitArr[y][x] = true;
        }

        if(sCount < 4) return false;
        return isUnion(selectedList.get(0), visitArr);
    }

    private static boolean isUnion(int[] start, boolean[][] indexArr) {
        Queue<int[]> resultQueue = new LinkedList<>();
        boolean[][] visitArr = new boolean[5][5];

        int count = 1;

        int startY = start[0];
        int startX = start[1];

        visitArr[startY][startX] = true;
        resultQueue.add(start);

        while(!resultQueue.isEmpty()) {
            int[] currentInfo = resultQueue.poll();

            int y = currentInfo[0];
            int x = currentInfo[1];

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || xValue >= 5) continue;
                if(yValue < 0 || yValue >= 5) continue;
                if(!indexArr[yValue][xValue]) continue;
                if(visitArr[yValue][xValue]) continue;

                resultQueue.add(new int[]{yValue, xValue});
                count++;
                visitArr[yValue][xValue] = true;
            }
        }

        return count == 7;
    }
}