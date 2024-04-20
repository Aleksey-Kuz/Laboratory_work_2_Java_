import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import java.util.Scanner;

public class CalculatorTest {
    @Test
    public void calculate_test1() {
        String str = "101-9*(8-3)/3 + 10";
        double result = 0;
        try {
            Calculator exp = new Calculator(str);
            result = exp.calculate();
        } catch (Exception ex) {
            result = -1;
        }
        assertEquals(96.0, result);
    }

    @Test
    public void calculate_test2() {
        String str = "(3 - 2";
        try {
            Calculator exp = new Calculator(str);
            exp.calculate();
        } catch (Exception ex) {
            assertEquals("Invalid expression", ex.getMessage());
        }
    }

    @Test
    public void calculate_test3() {
        String str = "5 + 13 - 444)";
        try {
            Calculator exp = new Calculator(str);
            exp.calculate();
        } catch (Exception ex) {
            assertEquals("Invalid expression", ex.getMessage());
        }
    }

    @Test
    public void calculate_test4() {
        String str = "13+22)/(8*3";
        try {
            Calculator exp = new Calculator(str);
            exp.calculate();
        } catch (Exception ex) {
            assertEquals("Invalid expression", ex.getMessage());
        }
    }

    @Test
    public void calculate_test5() {
        String str = "(33+ 22)/(14-3)";
        double result = 0;
        try {
            Calculator exp = new Calculator(str);
            result = exp.calculate();
        } catch (Exception ex) {
            result = -1;
        }
        assertEquals(5.0,result);
    }

    @Test
    public void calculate_test6() {
        String str = "445*r+989*(768-3)";
        try {
            Calculator exp = new Calculator(str);
            exp.calculate();
        } catch (Exception ex) {
            assertEquals("Invalid expression", ex.getMessage());
        }
    }
}