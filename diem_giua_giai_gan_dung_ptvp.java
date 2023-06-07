import java.util.Scanner;

public class diem_giua_giai_gan_dung_ptvp {
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
            double yTemp = y[i - 1] + h * calculateFunction(a + (i - 1) * h, y[i - 1]) / 2;
            double xTemp = a + (i - 1) * h + h / 2;
            y[i] = y[i - 1] + h * calculateFunction(xTemp, yTemp);
            System.out.println(yTemp + " " + y[i]);
        }
    }
}
