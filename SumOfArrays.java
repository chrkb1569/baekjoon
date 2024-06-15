import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfArrays {
    private static int[] arrA;
    private static int[] arrB;
    private static long[] sumArrA;
    private static long[] sumArrB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initArrA(n);
        mkArrA(st);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initArrB(m);
        mkArrB(st);

        Arrays.sort(sumArrA);
        Arrays.sort(sumArrB);

        System.out.println(getCount(T));
    }

    private static void initArrA(int length) {
        arrA = new int[length];
        sumArrA = new long[length * (length + 1) / 2];
    }

    private static void initArrB(int length) {
        arrB = new int[length];
        sumArrB = new long[length * (length + 1) / 2];
    }

    private static void mkArrA(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            arrA[index++] = Integer.parseInt(st.nextToken());
        }

        for(int arrIndex = 1; arrIndex < arrA.length; arrIndex++) {
            arrA[arrIndex] += arrA[arrIndex - 1];
        }

        int sumArrIndex = 0;

        for(int arrIndex = 0; arrIndex < arrA.length; arrIndex++) {
            int value = arrA[arrIndex];

            for(int nextIndex = arrIndex; nextIndex < arrA.length; nextIndex++) {
                int nextValue = arrA[nextIndex];

                if(nextIndex > arrIndex) {
                    sumArrA[sumArrIndex++] = nextValue - value;
                    continue;
                }

                sumArrA[sumArrIndex++] = nextValue;
            }
        }
    }

    private static void mkArrB(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            arrB[index++] = Integer.parseInt(st.nextToken());
        }

        for(int arrIndex = 1; arrIndex < arrB.length; arrIndex++) {
            arrB[arrIndex] += arrB[arrIndex - 1];
        }

        int sumArrIndex = 0;

        for(int arrIndex = 0; arrIndex < arrB.length; arrIndex++) {
            int value = arrB[arrIndex];

            for(int nextIndex = arrIndex; nextIndex < arrB.length; nextIndex++) {
                int nextValue = arrB[nextIndex];

                if(nextIndex > arrIndex) {
                    sumArrB[sumArrIndex++] = nextValue - value;
                    continue;
                }

                sumArrB[sumArrIndex++] = nextValue;
            }
        }
    }

    private static long getCount(int T) {
        long count = 0;
        int left = 0;
        int right = sumArrB.length-1;

        while(left < sumArrA.length && right >= 0) {
            long leftValue = sumArrA[left];
            long rightValue = sumArrB[right];

            long sum = leftValue + rightValue;

            if(sum == T) {
                long aCount = 1;
                long bCount = 1;

                while(left + 1 < sumArrA.length && sumArrA[left + 1] == leftValue) {
                    left++;
                    aCount++;
                }

                while(right - 1 >= 0 && sumArrB[right - 1] == rightValue) {
                    right--;
                    bCount++;
                }

                count += (aCount * bCount);
                left++;
                right--;
                continue;
            }

            if(sum < T) {
                left++;
                continue;
            }

            if(sum > T) {
                right--;
            }
        }

        return count;
    }
}
