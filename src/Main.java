import java.lang.*;
import java.util.Scanner;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String code;
        Scanner in = new Scanner(System.in);
        do {
            double result;
            System.out.print("Enter your expression: ");
            String str = in.nextLine();

            Calculator exp = new Calculator(str);
            result = exp.calculate();
            System.out.print(str);
            System.out.print(" = ");
            System.out.println(result);

            System.out.println("Enter '0' to exit.");
            code = in.nextLine();
        }
        while (!Objects.equals(code, "0"));
    }
}