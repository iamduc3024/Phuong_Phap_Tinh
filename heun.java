import java.util.Scanner;

public class heun {
    public static double calculateFunction(double x, double y) {
        // eg: dy / dx = 1 + x ^ 2 + y
        return 1 + Math.pow(x, 2) + y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        int n = scanner.nextInt();
        double h = (b - a) / n;
        double[] y = new double[n + 1];
        y[0] = scanner.nextDouble();
        for (int i = 1; i <= n; i++) {
            double yTemp = y[i - 1] + h * calculateFunction(a + (i - 1) * h, y[i - 1]);
            y[i] = y[i - 1] + h * (calculateFunction(a + (i - 1) * h, y[i - 1]) + calculateFunction(a + i * h, yTemp)) / 2;
            System.out.println(y[i]);
        }
    }
}
