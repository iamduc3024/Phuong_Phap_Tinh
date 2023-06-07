import java.util.Scanner;

public class doolittle {
    public static void lu(double[][] a, double[][] l, double[][] u, int n) {
        for (int i = 0; i < n; i++) {
            for (int k = i; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += (l[i][j] * 1.0 * u[j][k]);
                }
                u[i][k] = a[i][k] - sum;
            }
            for (int k = i; k < n; k++) {
                if (i == k) {
                    l[i][i] = 1;
                } else {
                    double sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += (l[k][j] * u[j][i]);
                    }
                    l[k][i] = (a[k][i] - sum) * 1.0 / u[i][i];
                }
            }
        }
    }

    public static double[] solveY(double[][] l, double[] b, double[] y, int n) {
        for (int i = 0; i < n; i++) {
            y[i] = b[i];
            for (int j = 0; j < i; j++) {
                y[i] -= (y[j] * l[i][j]);
            }
            y[i] /= l[i][i];
        }
        return y;
    }

    public static double[] solveX(double[][] u, double[] y, double[] x, int n) {
        for (int i = n - 1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = n - 1; j > i; j--) {
                x[i] -= (x[j] * u[i][j]);
            }
            x[i] /= u[i][i];
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] a = new double[n][n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextDouble();
            }
            b[i] = scanner.nextDouble();
        }
        double[][] l = new double[n][n];
        double[][] u = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                l[i][j] = 0;
                u[i][j] = 0;
            }
        }
        lu(a, l, u, n);
        double[] y = new double[n];
        y = solveY(l, b, y, n);
        double[] x = new double[n];
        x = solveX(u, y, x, n);
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
