#include <iostream>
#include <cmath>

using namespace std;

pair<double, double> v[100];

void inputEquation(int n) {
    for (int i = 1; i <= n; i++) {
        cin >> v[i].first >> v[i].second;
    }
}

double function(int n, double u) {
    double resultOfFunction = 0;
    for (int i = 1; i <= n; i++) {
        resultOfFunction += (v[i].first * pow(u, v[i].second));
    }
    return resultOfFunction;
}

double fixedPoint(int n, double p, double epsilon) {
    double pTemp = function(n, p);
    while (abs(pTemp - p) >= epsilon) {
        p = pTemp;
        pTemp = function(n, p);
    }
    return pTemp;
}

int main() {
    int n;
    cin >> n;
    inputEquation(n);
    double p, epsilon;
    cin >> p >> epsilon;
    cout << fixedPoint(n, p, epsilon);
    return 0;
}