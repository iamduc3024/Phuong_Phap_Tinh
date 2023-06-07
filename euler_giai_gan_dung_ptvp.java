import java.util.Scanner;

public class euler_giai_gan_dung_ptvp {
    public static double calculateFunction(double x) {
        // eg: dy / dx = 1 + x ^ 2
        return 1 + Math.pow(x, 2);
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
            y[i] = y[i - 1] + h * calculateFunction(a + (i - 1) * h);
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(y[i]);
        }
    }
}
