import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            String command = br.readLine();
            int length = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[,]");
            int errorCount = 0;

            Deque<String> queue = new LinkedList<>();

            boolean isLeft = true;
            boolean isRight = false;

            outer : for(int j = 0; j < length; j++) {
                if(st.hasMoreTokens()) {
                    queue.addFirst(st.nextToken());
                }
                else {
                    System.out.println("error");
                    errorCount++;
                    break outer;
                }
            }

            if(errorCount > 0) {
                continue;
            }

            outer : for(int w = 0; w < command.length(); w++) {
                if(command.charAt(w) == 'R') {
                    isLeft = !isLeft;
                    isRight = !isRight;
                }
                else if(command.charAt(w) == 'D') {
                    if(!queue.isEmpty()) {
                        if(isLeft) {
                            queue.removeLast();
                        }
                        else if(isRight) {
                            queue.removeFirst();
                        }
                    }
                    else {
                        System.out.println("error");
                        errorCount++;
                        break outer;
                    }

                }
                else {
                    System.out.println("error");
                    errorCount++;
                    break outer;
                }
            }

            if(errorCount > 0) {
                continue;
            }

            if(isLeft) {
                System.out.print("[");

                for(Iterator<String> itr = queue.descendingIterator(); itr.hasNext();) {

                    String value = itr.next();

                    if(itr.hasNext()) {
                        System.out.print(value + ",");
                    }
                    else {
                        System.out.print(value);
                    }

                }

                System.out.println("]");
            }
            else if(isRight) {
                System.out.print("[");

                for(Iterator<String> itr = queue.iterator(); itr.hasNext();) {
                    String value = itr.next();

                    if(itr.hasNext()) {
                        System.out.print(value + ",");
                    }
                    else {
                        System.out.print(value);
                    }
                }

                System.out.println("]");
            }

            errorCount = 0;
        }
    }
}
