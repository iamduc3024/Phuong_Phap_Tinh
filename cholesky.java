import java.util.Scanner;

public class cholesky {
    public static void cholesky(double[][] a, double[][] l, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                if (j == i) {
                    for (int k = 0; k < j; k++) {
                        sum += Math.pow(l[j][k], 2);
                    }
                    l[j][j] = Math.sqrt(a[j][j] - sum);
                } else {
                    for (int k = 0; k < j; k++) {
                        sum += (l[i][k] * l[j][k]);
                    }
                    l[i][j] = (a[i][j] - sum) / l[j][j];
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

    public static double[] solveX(double[][] lt, double[] y, double[] x, int n) {
        for (int i = n - 1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = n - 1; j > i; j--) {
                x[i] -= (x[j] * lt[i][j]);
            }
            x[i] /= lt[i][i];
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                l[i][j] = 0;
            }
        }
        cholesky(a, l, n);
        double[][] lt = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lt[i][j] = l[j][i];
            }
        }
        double[] y = new double[n];
        y = solveY(l, b, y, n);
        double[] x = new double[n];
        x = solveX(lt, y, x, n);
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
