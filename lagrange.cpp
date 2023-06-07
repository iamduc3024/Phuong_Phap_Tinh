#include <iostream>
#include <cmath>

using namespace std;

// px = f(xi) * Li
// Li = ((x - x0) * (x - x1) * ... * (x - xi - 1) * (x - xi + 1)) / ((xi - x0) * (xi - x1) * ... * (xi - xi -1 ) * (xi - xi + 1) * ...)

void inputData(double* x, double* f, int n) {
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> f[i];
    }
}

double* L(double* x, double input, int n) {
    double* L = new double[n];
    for (int i = 0; i < n; i++) {
        L[i] = 1.0;
        for (int j = 0; j < n; j++) {
            if (i != j) {
                L[i] *= ((input - x[j]) / (x[i] - x[j]));
            }
        }
    }
    return L;
}

int main() {
    int n;
    cin >> n;
    double* x = new double[n];
    double* f = new double[n];
    inputData(x, f, n);
    double input;
    cin >> input;
    double* l = L(x, input, n); 
    double result;
    for (int i = 0; i < n; i++) {
        result += f[i] * l[i];
    }
    cout << result;
}