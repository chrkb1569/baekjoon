import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IronStick {

    private final static char OPEN_BLANK = '(';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String userInput = br.readLine();

        int segmentCount = getSegmentCount(userInput);

        System.out.println(segmentCount);
    }

    private static int getSegmentCount(String userInput) {
        char[] originArr = userInput.toCharArray();

        int segment = 0;
        int top = 0;

        for(int index = 0; index < originArr.length; index++) {
            char currentValue = originArr[index];

            if(currentValue == OPEN_BLANK) {
                top++;
                continue;
            }

            char preValue = originArr[index - 1];

            if(preValue == OPEN_BLANK) {
                top--;
                segment += top;
                continue;
            }

            top--;
            segment++;
        }

        return segment;
    }
}