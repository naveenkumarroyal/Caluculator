import java.util.*;

public class Calculator {
    public static int solve(String input) {


        char[] expression = input.toCharArray();

        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> opreration = new Stack<Character>();

        for (int i = 0; i < expression.length; i++) {
            if (expression[i] == ' ') continue;
            if (expression[i] >= '0' && expression[i] <= '9') {

                StringBuffer temp = new StringBuffer();
                while (i < expression.length && expression[i] >= '0' && expression[i] <= '9') temp.append(expression[i++]);
                values.push(Integer.parseInt(temp.toString()));
            }

            else if (expression[i] == '+' || expression[i] == '-' || expression[i] == '*' || expression[i] == '/' ) {
                while (!opreration.empty() && check(expression[i], opreration.peek()))
                    values.push(calculate(opreration.pop(), values.pop(), values.pop()));
                opreration.push(expression[i]);
            }
        }
        while (!opreration.empty()) values.push(calculate(opreration.pop(), values.pop(), values.pop()));
        return values.pop();
    }



    public static boolean check(char opr1, char opr2) {
        int pre_val_1 = 0;
        int pre_val_2 = 0;
        String operation_string = "-+*/";
        char[] operation_array= operation_string.toCharArray();
        for(int i=0; i<=operation_array.length;i++) {
            if (opr1 == operation_array[i]) {
                pre_val_1 = i;
                break;
            }
        }
        for(int i=0; i<=operation_array.length;i++) {
            if (opr2 == operation_array[i]) {
                pre_val_2 = i;
                break;
            }
        }
        if(pre_val_1<pre_val_2){
            return true;
        }
        else {
            return false;
        }
    }


    public static int calculate(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero"); return a / b;
        }
        return 0;
    }


    public static void main(String[] args) {
        String in;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an expression with spaces");
        in = sc.nextLine();
        System.out.println("Answer = " + Calculator.solve(in));
    }
}
