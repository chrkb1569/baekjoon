import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class NGE {
    private static Element[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        int[] valueArr = getValueArr();

        printResult(valueArr);
    }

    private static void initResultArr(int length) {
        resultArr = new Element[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            resultArr[index] = new Element(index, value);
            index++;
        }
    }

    private static int[] getValueArr() {
        Stack<Element> resultStack = new Stack<>();
        int[] valueArr = new int[resultArr.length];

        for(int index = 0; index < resultArr.length; index++) {
            Element currentElement = resultArr[index];

            if(resultStack.isEmpty()) {
                resultStack.push(currentElement);
                continue;
            }

            while(resultStack.peek().getValue() < currentElement.getValue()) {
                Element element = resultStack.pop();

                valueArr[element.getIndex()] = currentElement.getValue();

                if(resultStack.isEmpty()) break;
            }

            resultStack.push(currentElement);
        }

        for(Element element : resultStack) {
            valueArr[element.getIndex()] = -1;
        }

        return valueArr;
    }

    private static void printResult(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for(int value : arr) {
            sb.append(value).append(" ");
        }

        System.out.println(sb);
    }

    private static class Element {
        private int index;
        private int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return this.index;
        }

        public int getValue() {
            return this.value;
        }
    }
}
