import java.lang.*;
import java.util.Scanner;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        double result; String code;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Enter the expression: ");
            String str = in.nextLine();

            Calculator exp = new Calculator(str);
            result = exp.calculate();
            System.out.print(str + " = ");
            System.out.println(result);

            System.out.println("Press '0' to exit.");
            code = in.nextLine();
        }
        while (!Objects.equals(code, "0"));
    }
}