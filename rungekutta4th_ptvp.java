import java.util.Scanner;

public class rungekutta4th_ptvp {
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
            double k1 = calculateFunction(a + (i - 1) * h, y[i - 1]);
            double k2 = calculateFunction(a + (i - 1) * h + h / 2, y[i - 1] + k1 * h / 2);
            double k3 = calculateFunction(a + (i - 1) * h + h / 2, y[i - 1] + k2 * h / 2);
            double k4 = calculateFunction(a + (i - 1) * h + h, y[i - 1] + k3 * h);
            y[i] = y[i - 1] + h * (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            System.out.println(y[i]);
        }
    }
}
