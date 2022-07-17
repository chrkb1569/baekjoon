import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

class Stack_2 {
    private List<Character> lst = new LinkedList<>();

    public void push(Character s) {
        lst.add(s);
    }

    public char pop() {
        return lst.remove(lst.size()-1);
    }

    public boolean empty() {
        return lst.isEmpty();
    }
}

public class Is_VPS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
           String str = br.readLine();
           Stack_2 stack = new Stack_2();
           boolean printable = true;

           for(char s : str.toCharArray()) {
               if(s == '(') {
                   stack.push(s);
               }

               else if(s == ')') {
                   if(stack.empty()) {
                       printable = false;
                       break;
                   }
                   else {
                       stack.pop();
                   }
               }
           }

           if(!stack.empty()) {
               printable = false;
           }

           if(printable) {
                System.out.println("YES");
            }
           else {
                System.out.println("NO");
            }
        }
    }
}
