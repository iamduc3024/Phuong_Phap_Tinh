import java.util.Scanner;

public class tinhGanDungDaoHam {
    public static double calculateFunction(double x) {
        return 1.0 / Math.pow(2 * Math.PI, -0.5) * Math.pow(Math.E, - x * x / 2);
    }

    public static double approximate_derivative(double x, double h) {
        return (calculateFunction(x - 2 * h) - 8 * calculateFunction(x - h) + 8 * calculateFunction(x + h) - calculateFunction(x + 2 * h)) / (12 * h);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = 0;
        double b = 1.0;
        int n = 50;
        double h = (b - a) / n;
        double[] derivatives = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            double x = i * h;
            double derivative = approximate_derivative(x, h);
            derivatives[i] = derivative;
        }
        for (int i = 0; i <= n; i++) {
            double x = i * h;
            System.out.println("Đạo hàm gần đúng tại " + x + " là: " + derivatives[i]);
        }
    }
}
