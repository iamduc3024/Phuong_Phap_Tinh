import java.util.Scanner;

public class lagrange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] x = new double[n];
        double[] f = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
            f[i] = scanner.nextDouble();
        }
        double input = scanner.nextDouble();
        double[] L = new double[n];
        for (int i = 0; i < n; i++) {
            L[i] = 1.0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    L[i] *= ((input - x[j]) / (x[i] - x[j]));
                }
            }
        }
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += f[i] * L[i];
        }
        System.out.println(result);
    }
}
