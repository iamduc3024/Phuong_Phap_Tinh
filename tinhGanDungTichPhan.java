public class tinhGanDungTichPhan {
    public static double calculateFunction(double x) {
        return Math.pow(Math.E, - x * x) / Math.pow(2 * Math.PI, -0.5);
    }
    public static void main(String[] args) {
        double a = 0;
        double b = 1;
        int n = 50;
        double h = (b - a) / n;
        double fHinhThang = calculateFunction(a) + calculateFunction(b);
        for (int i = 1; i <= n - 1; i++) {
            fHinhThang += 2 * calculateFunction(i * h);
        }
        double tichPhanTheoCongThucHinhThang = h * fHinhThang / 2;
        if (n % 2 == 0) {
            double fSimpson = calculateFunction(a) + calculateFunction(b);
            for (int i = 1; i <= n / 2; i++) {
                fSimpson += 4 * calculateFunction((2 * i - 1) * h);
            }
            for (int i = 1; i <= n / 2 - 1; i++) {
                fSimpson += 2 * calculateFunction(2 * i * h);
            }
            double tichPhanTheoCongThucSimpson = h * fSimpson / 3;
        }
    }
}
