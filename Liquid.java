import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Liquid {
    private static long[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        Result result = getResult();

        System.out.println(result.getLeftResult() + " " + result.getRightResult());
    }

    private static void initResultArr(int length) {
        resultArr = new long[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static Result getResult() {
        long minGap = Long.MAX_VALUE;
        int leftIndex = 0;
        int rightIndex = resultArr.length-1;

        long leftResult = resultArr[leftIndex];
        long rightResult = resultArr[rightIndex];

        while(leftIndex < rightIndex) {
            long lowValue = resultArr[leftIndex];
            long highValue = resultArr[rightIndex];

            long sum = lowValue + highValue;
            long gap = Math.abs(sum);

            if(gap < minGap) {
                minGap = gap;
                leftResult = lowValue;
                rightResult = highValue;
            }

            if(sum > 0) {
                rightIndex--;
                continue;
            }

            leftIndex++;
        }

        return new Result(leftResult, rightResult);
    }

    private static class Result {
        private long leftResult;
        private long rightResult;

        public Result(long leftResult, long rightResult) {
            this.leftResult = leftResult;
            this.rightResult = rightResult;
        }

        public long getLeftResult() {
            return this.leftResult;
        }

        public long getRightResult() {
            return this.rightResult;
        }
    }
}
