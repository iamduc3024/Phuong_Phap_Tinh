import java.util.Scanner;

public class newtonInterpolation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] x = new double[n];
        double[] f = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
            f[i] = scanner.nextDouble();
        }
        double[][] fNewton = new double[n - 1][];
        for (int i = 0; i < n - 1; i++) {
            fNewton[i] = new double[n - i - 1];
        }
        for (int i = 0; i < n - 1; i++) {
            fNewton[0][i] = (f[i + 1] - f[i]) / (x[i + 1] - x[i]);
        }
        for (int i =  1; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                fNewton[i][j] = (fNewton[i - 1][j + 1] - fNewton[i - 1][j]) / (x[i + j + 1] - x[j]);
            }
        }
        double input = scanner.nextDouble();
        double fx = f[0];
        for (int i = 0; i < n - 1; i++) {
            double temp = 1.0;
            for (int j = i; j >= 0; j--) {
                temp *= (input - x[j]);
            }
            fx += temp * fNewton[i][0];
        }
        System.out.println(fx); // Khai trien den pn
    }
}
