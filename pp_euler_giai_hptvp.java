import java.util.Scanner;

public class pp_euler_giai_hptvp {
    public static double pt1(double z1, double z2, double z3, double z4, double z5) {
        return z2;
    }

    public static double pt2(double z1, double z2, double z3, double z4, double z5) {
        return z3;
    }

    public static double pt3(double z1, double z2, double z3, double z4, double z5) {
        return -8 * z4 - 2 * z2 -5 * z3;
    }

    public static double pt4(double z1, double z2, double z3, double z4, double z5) {
        return z5;
    }

    public static double ptr1(double y1, double y2) {
        return y2;
    }

    public static double ptr2(double y1, double y2) {
        return 1 - y1;
    }

    public static double pt5(double z1, double z2, double z3, double z4, double z5) {
        return 2 - z2 - 2 * z1 * z4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        int n = scanner.nextInt();
        double h = (b - a) / n;
        double[] z1 = new double[n + 1];
        double[] z2 = new double[n + 1];
        double[] z3 = new double[n + 1];
        double[] z4 = new double[n + 1];
        double[] z5 = new double[n + 1];
        z1[0] = scanner.nextDouble();
        z2[0] = scanner.nextDouble();
        z3[0] = scanner.nextDouble();
        z4[0] = scanner.nextDouble();
        z5[0] = scanner.nextDouble();
        for (int i = 1; i <= n; i++) {
            z1[i] = z1[i - 1] + h * pt1(z1[i - 1], z2[i - 1], z3[i - 1], z4[i - 1], z5[i - 1]);
            z2[i] = z2[i - 1] + h * pt2(z1[i - 1], z2[i - 1], z3[i - 1], z4[i - 1], z5[i - 1]);
            z3[i] = z3[i - 1] + h * pt3(z1[i - 1], z2[i - 1], z3[i - 1], z4[i - 1], z5[i - 1]);
            z4[i] = z4[i - 1] + h * pt4(z1[i - 1], z2[i - 1], z3[i - 1], z4[i - 1], z5[i - 1]);
            z5[i] = z5[i - 1] + h * pt5(z1[i - 1], z2[i - 1], z3[i - 1], z4[i - 1], z5[i - 1]);
        }
    }
}
