import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Calculator {

    private String expression;

    public Calculator(String expression) {
        this.expression = expression.replaceAll("\\s", ""); // Удаляем пробелы при создании экземпляра класса
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private int priority(char ch) {
        if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '+' || ch == '-')
            return 1;
        else
            return 0;
    }

    private boolean isValid() {
        if (expression.isEmpty())
            return false;

        int bracket = 0;

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == '(')
                bracket++;
            else if (currentChar == ')') {
                bracket--;
                if (bracket < 0 || (i < expression.length() - 1 && isDigit(expression.charAt(i + 1))))
                    return false;
            } else if (!isDigit(currentChar) && !isOperator(currentChar))
                return false;
        }

        return bracket == 0;
    }

    private String toPostfix() {
        if (!isValid())
            return null;

        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (isDigit(currentChar)) {
                while (i < expression.length() && isDigit(expression.charAt(i))) {
                    postfix.append(expression.charAt(i));
                    i++;
                }
                postfix.append(' ');
                i--;
            } else if (isOperator(currentChar)) {
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(currentChar))
                    postfix.append(stack.pop()).append(' ');
                stack.push(currentChar);
            } else if (currentChar == '(') {
                stack.push(currentChar);
            } else if (currentChar == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    postfix.append(stack.pop()).append(' ');
                stack.pop();
            }
        }

        while (!stack.isEmpty())
            postfix.append(stack.pop()).append(' ');

        return postfix.toString().trim();
    }

    public double calculate() {
        String postfix = toPostfix();
        if (postfix == null)
            throw new IllegalArgumentException("Invalid expression");

        Deque<Double> stack = new ArrayDeque<>();

        for (int i = 0; i < postfix.length(); i++) {
            char currentChar = postfix.charAt(i);
            if (Character.isDigit(currentChar)) {
                StringBuilder num = new StringBuilder();
                while (i < postfix.length() && Character.isDigit(postfix.charAt(i))) {
                    num.append(postfix.charAt(i));
                    i++;
                }
                stack.push(Double.parseDouble(num.toString()));
                i--;
            } else if (isOperator(currentChar)) {
                double num2 = stack.pop();
                double num1 = stack.pop();
                switch (currentChar) {
                    case '+':
                        stack.push(num1 + num2);
                        break;
                    case '-':
                        stack.push(num1 - num2);
                        break;
                    case '*':
                        stack.push(num1 * num2);
                        break;
                    case '/':
                        stack.push(num1 / num2);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public String toString() {
        return String.valueOf(calculate());
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Calculator that = (Calculator) obj;
        return Objects.equals(expression, that.expression);
    }

    public int hashCode() {
        return Objects.hash(expression);
    }
}
