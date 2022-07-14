import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Calculator{
    private List<Integer> stack = new LinkedList<>();

    public void Pop() {
        stack.remove(stack.size()-1);
    }

    public void Push(int item) {
        stack.add(item);
    }

    public int Get_Sum() {
        int sum = 0;

        for(Iterator<Integer> itr = stack.iterator(); itr.hasNext();) {
            sum += itr.next();
        }

        return sum;
    }
}

public class Using_Stack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Calculator cal = new Calculator();

        int N = Integer.parseInt(br.readLine());
        int sum;

        for(int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());

            if(number == 0) {
                cal.Pop();
            }
            else {
                cal.Push(number);
            }
        }

        sum = cal.Get_Sum();

        bw.write(sum + "\n");
        bw.flush();
        bw.close();
        br.close();

    }
}