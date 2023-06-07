import java.util.Scanner;
// px = f0 + s * deltaf0 + s * (s - 1) / 2! * delta^2f0 + s * (s - 1) * (s - 2) / 3! * delta^3f0...

public class noiSuyNewton {
    public static double calculateSinFunction(double x) {
        return Math.sin(x);
    }

    public static double calculateFactorial(int x) {
        if (x == 0) {
            return 1.0;
        } else {
            return x * calculateFactorial(x - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = 0;
        double b = Math.PI / 2;
        int n = 50; // n = 50
        double h = (b - a) / n;
        double[] f = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i] = calculateSinFunction(a + i * h);
        }
        double[][] deltaF = new double[n][];
        for (int i = 0; i < n; i++) {
            deltaF[i] = new double[n - i];
        }
        for (int i = 0; i < n; i++) {
            deltaF[0][i] = f[i + 1] - f[i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                deltaF[i][j] = deltaF[i - 1][j + 1] - deltaF[i - 1][j];
            }
        }
        double x = Math.PI / 7;
        double s = (x - a) / h;
        double px = f[0];
        for (int i = 0; i < n; i++) {
            double temp = 1.0;
            for (int j = i; j >= 0; j--) {
                temp *= (s - j);
            }
            temp /= calculateFactorial(i + 1);
            px += temp * deltaF[i][0];
        }
        System.out.println(px);
    }
}
