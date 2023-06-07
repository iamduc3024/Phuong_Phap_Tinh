import java.util.Scanner;

public class pp_runge_kutta_giai_hptvp {
    private static double h;

    public static double differentialY1(double x, double y1, double y2) {
        return y2;
    }

    public static double differentialY2(double x, double y1, double y2) {
        return 1 - y1;
    }

    public static double yCorrect(double x) {
        return x + 1 / 3 * Math.pow(x, 3) - 16 / 3;
    }

    public static double y1(double y1, double[] K1, double[] K2) {
        return y1 + h / 2 * (K1[0] + K2[0]);
    }

    public static double y2(double y2, double[] K1, double[] K2) {
        return y2 + h / 2 * (K1[1] + K2[1]);
    }

    public static double[] K1(double x, double y1, double y2) {
        double[] arr = new double[2];
        arr[0] = differentialY1(x, y1, y2);
        arr[1] = differentialY2(x, y1, y2);
        return arr;
    }

    public static double[] K2(double x, double y1, double y2, double[] K1) {
        double[] arr = new double[2];
        arr[0] = differentialY1(x + h, y1 + K1[0] * h, y2 + K1[1] * h);
        arr[1] = differentialY2(x, y1 + K1[0] * h, y2 + K1[1] * h);
        return arr;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextDouble(); // Nhap h
        double x = sc.nextDouble(); // Nhap x la gia tri ban dau
        double y1 = sc.nextDouble(); // Nhap y1 la y1[x]
        double y2 = sc.nextDouble(); // Nhap y2 la y2[x]
        double saiSo = 0;
        for (int i = 1; i <= 10; i++) {
            double[] K1 = K1(x, y1, y2);
            System.out.println("K1= [" + K1[0] + " " + K1[1] + "]");
            double[] K2 = K2(x, y1, y2, K1);
            System.out.println("K2= [" + K2[0] + " " + K2[1] + "]");
            System.out.println("y1(" + (x + h) + ")=" + y1(y1, K1, K2));
            System.out.println("y2(" + (x + h) + ")=" + y2(y2, K1, K2));
            y1 = y1(y1, K1, K2);
            y2 = y2(y2, K1, K2);
            x += h;
            System.out.println();
        }
    }
}
