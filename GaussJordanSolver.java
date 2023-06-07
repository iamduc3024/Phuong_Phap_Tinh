public class GaussJordanSolver {

    public static void main(String[] args) {
        // Ma trận A
        double[][] A = {
                {2, 1, -1},
                {-3, -1, 2},
                {-2, 1, 2}
        };

        // Vector b
        double[] b = {8, -11, -3};

        // Giải hệ phương trình
        double[] x = solve(A, b);

        // In nghiệm
        System.out.println("Nghiệm của hệ phương trình:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
        }
    }

    public static double[] solve(double[][] A, double[] b) {
        int n = A.length;

        // Tạo ma trận mở rộng bao gồm ma trận A và vector b
        double[][] augmentedMatrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = A[i][j];
            }
            augmentedMatrix[i][n] = b[i];
        }

        // Áp dụng phương pháp Gauss-Jordan
        for (int pivot = 0; pivot < n; pivot++) {
            // Tìm hàng chứa phần tử chính hợp lệ không bằng 0
            int nonZeroRow = -1;
            for (int i = pivot; i < n; i++) {
                if (augmentedMatrix[i][pivot] != 0) {
                    nonZeroRow = i;
                    break;
                }
            }

            // Hoán đổi hàng
            if (nonZeroRow != -1) {
                double[] temp = augmentedMatrix[pivot];
                augmentedMatrix[pivot] = augmentedMatrix[nonZeroRow];
                augmentedMatrix[nonZeroRow] = temp;
            } else {
                // Hệ phương trình vô nghiệm hoặc có vô số nghiệm
                return null;
            }

            // Chuẩn hóa hàng chứa phần tử chính hợp lệ thành 1
            double pivotValue = augmentedMatrix[pivot][pivot];
            for (int j = pivot; j <= n; j++) {
                augmentedMatrix[pivot][j] /= pivotValue;
            }

            // Loại bỏ phần tử chính hợp lệ khỏi các hàng khác
            for (int i = 0; i < n; i++) {
                if (i != pivot) {
                    double factor = augmentedMatrix[i][pivot];
                    for (int j = pivot; j <= n; j++) {
                        augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];
                    }
                }
            }
        }

        // Tạo mảng nghiệm
        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            solution[i] = augmentedMatrix[i][n];
        }

        return solution;
    }
}