import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakePaper {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int height = 0; height < resultArr.length; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        PaperStatus paperStatus = getPaperStatus(0, 0, N);

        System.out.println(paperStatus.getWhitePaper());
        System.out.println(paperStatus.getBluePaper());
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    // white -> 0 blue -> 1
    private static PaperStatus getPaperStatus(int x, int y, int length) {
        if(length == 1) {
            int color = resultArr[y][x];

            if(color == 1) return new PaperStatus(1, 0);
            return new PaperStatus(0, 1);
        }

        if(checkValidation(x, y, length)) {
            int color = resultArr[y][x];

            if(color == 1) return new PaperStatus(1, 0);
            return new PaperStatus(0, 1);
        }

        PaperStatus leftUp = getPaperStatus(x, y, length / 2);
        PaperStatus rightUp = getPaperStatus(x + length / 2, y, length / 2);
        PaperStatus leftDown = getPaperStatus(x, y + length / 2, length / 2);
        PaperStatus rightDown = getPaperStatus(x + length / 2, y + length / 2, length / 2);

        return new PaperStatus(
                leftUp.getBluePaper() + rightUp.getBluePaper() + leftDown.getBluePaper() + rightDown.getBluePaper(),
                leftUp.getWhitePaper() + rightUp.getWhitePaper() + leftDown.getWhitePaper() + rightDown.getWhitePaper()
                );
    }

    private static boolean checkValidation(int x, int y, int length) {
        int standard = resultArr[y][x];

        for(int height = y; height < y + length; height++) {
            for(int width = x; width < x + length; width++) {
                if(resultArr[height][width] != standard) return false;
            }
        }
        return true;
    }

    private static class PaperStatus {
        private int bluePaper;
        private int whitePaper;

        public PaperStatus(int bluePaper, int whitePaper) {
            this.bluePaper = bluePaper;
            this.whitePaper = whitePaper;
        }

        public int getBluePaper() {
            return this.bluePaper;
        }

        public int getWhitePaper() {
            return this.whitePaper;
        }
    }
}
