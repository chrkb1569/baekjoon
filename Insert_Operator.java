import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Insert_Operator {

    static StringBuilder tool = new StringBuilder();
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int number = Integer.parseInt(br.readLine());

        int[] arr = new int[number];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < number; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");

        char[] operator = sb.append("+".repeat(Integer.parseInt(st.nextToken())))
                .append("-".repeat(Integer.parseInt(st.nextToken())))
                .append("*".repeat(Integer.parseInt(st.nextToken())))
                .append("/".repeat(Integer.parseInt(st.nextToken())))
                .toString().toCharArray();

        boolean[] visit = new boolean[operator.length];

        getOperator(arr, visit, operator, 0, 0, operator.length);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    public static void getOperator(int[] value, boolean[] visit, char[] operator, int start, int r, int count) {
        if (count == r) {
            String operatorSet = tool.toString();
            int sum = 0;
            int num1 = value[0];

            for(int i = 0; i < operatorSet.length(); i++) {
                int num2 = value[i+1];
                char oper = operatorSet.charAt(i);

                switch(oper) {
                    case '+':
                        sum = (num1 + num2);
                        break;

                    case '-':
                        sum = (num1 - num2);
                        break;

                    case '*':
                        sum = (num1 * num2);
                        break;

                    case '/':
                        sum = (num1 / num2);
                        break;
                }
                num1 = sum;
            }

            if(sum <= minValue) {
                minValue = sum;
            }

            if(sum >= maxValue) {
                maxValue = sum;
            }

            return;
        }

        for(int i = start; i < operator.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                tool.append(operator[i]);
                getOperator(value, visit, operator, 0, r+1, count);
                tool.deleteCharAt(tool.length()-1);
                visit[i] = false;
            }

        }
    }
}
