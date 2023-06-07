#include <iostream>
#include <cmath>
#include <cstring>

using namespace std;

void input(double** a, double* b, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
        cin >> b[i];
    }
}

void output(double** a, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
}

void lu(double** a, double** l, double** u, int n) {
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

double* solveY(double** l, double* b, double* y, int n) {
    for (int i = 0; i < n; i++) {
        y[i] = b[i];
        for (int j = 0; j < i; j++) {
            y[i] -= (y[j] * l[i][j]);
        }
        y[i] /= l[i][i];
    }
    return y;
}

double* solveX(double** u, double* y, double* x, int n) {
    for (int i = n - 1; i >= 0; i--) {
        x[i] = y[i];
        for (int j = n - 1; j > i; j--) {
            x[i] -= (x[j] * u[i][j]);
        }
        x[i] /= u[i][i];
    }
    return x;
}

int main() {
    int n;
    cin >> n;
    double** a = new double*[n];
    for (int i = 0; i < n; i++) {
        a[i] = new double[n];
    }
    double* b = new double[n];
    input(a, b, n);
    double** l = new double*[n];
    for (int i = 0; i < n; i++) {
        l[i] = new double[n];
    }
    double** u = new double*[n];
    for (int i = 0; i < n; i++) {
        u[i] = new double[n];
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            l[i][j] = 0;
            u[i][j] = 0;
        }
    }
    lu(a, l, u, n);
    // output(l, n);
    // cout << endl;
    // output(u, n);
    double* y = new double(n);
    y = solveY(l, b, y, n);
    double* x = new double(n);
    x = solveX(u, y, x, n);
    for (int i = 0; i < n; i++) {
        cout << x[i] << endl;
    }
    return 0;
}