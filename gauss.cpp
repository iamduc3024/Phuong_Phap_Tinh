#include <iostream>
#include <cmath>

using namespace std;

void input(double** matrix, double* b, double* x, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> matrix[i][j];
        }
        cin >> b[i];
        x[i] = 0;
    }
}

void gauss(double** matrix, double* b, double* x, int n) {
    for (int k = 0; k < n - 1; k++) {
        int m = k;
        for (int j = k + 1; j < n; j++) {
            if (abs(matrix[m][k]) < abs(matrix[j][k])) {
                m = j;
            }
        }
        if (matrix[m][k] == 0) {
            cout << "No unique solution exists";
            return;
        } else {
            for (int i = 0; i < n; i++) {
                swap(matrix[m][i], matrix[k][i]);
            }
            swap(b[m], b[k]);
        }
        for (int j = k + 1; j < n; j++) {
            double temp = matrix[j][k] * 1.0 / matrix[k][k];
            for (int p = k; p < n; p++) {
                matrix[j][p] -= temp * matrix[k][p];
            }
            b[j] -= temp * b[k];
        }
        if (matrix[n - 1][n - 1] == 0) {
            cout << "No unique solution exists";
            return;
        }
    }
    x[n - 1] = b[n - 1] * 1.0 / matrix[n - 1][n - 1];
    for (int i = n - 2; i >= 0; i--) {
        double sum = 0;
        for (int j = i + 1; j < n; j++) {
            sum += matrix[i][j] * x[j];
        }
        x[i] = (b[i] - sum) * 1.0 / matrix[i][i];
    }
    for (int i = 0; i < n; i++) {
        cout << x[i] << endl;
    }
}

int main() {
    int n;
    cin >> n;
    double **matrix = new double*[n];
    for (int i = 0; i < n; i++) {
        matrix[i] = new double[n];
    }
    double *b = new double[n];
    double *x = new double[n];
    input(matrix, b, x, n);
    gauss(matrix, b, x, n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << b[i] << endl;
    }
    return 0;
}