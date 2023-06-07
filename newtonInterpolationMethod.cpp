#include <iostream>
#include <cmath>

using namespace std;

// px = f0 + (x - x0) * f[0, 1] + (x - x0) * (x - x1) * f[0, 1, 2] + (x - x0) * (x - x1) * (x - x2) * f[0, 1, 2, 3] ...

void inputData(double* x, double* f, int n) {
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> f[i];
    }
}

double** fNewtonConstructor(double* x, double* f, int n) {
    double** fNewton = new double*[n - 1]; // f[0, 1], f[1, 2], ... , f[0, 1, 2], f[1, 2, 3], ..., f[0, 1, 2, 3], ...
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
    return fNewton;
}

int main() {
    int n;
    cin >> n;
    double* x = new double[n]; // Lưu các giá trị xi đề cho
    double* f = new double[n]; // f(xi)
    inputData(x, f, n);
    double** fNewton = fNewtonConstructor(x, f, n);
    double input;
    cin >> input;
    double fx = f[0];
    for (int i = 0; i < n - 1; i++) {
        double temp = 1.0;
        for (int j = i; j >= 0; j--) {
            temp *= (input - x[j]);
        }
        fx += temp * fNewton[i][0];
    }
    cout << fx;
    return 0;
}