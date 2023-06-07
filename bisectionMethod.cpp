#include <iostream>
#include <cmath>

using namespace std;

// Giống tìm kiếm nhị phân, ban đầu cho a và b, p0 = (a + b) / 2, sau đó xem dấu của f(a), f(b) và f(p0), nếu f(a) * f(p0) < 0, p1 = (a + p0) / 2, ngược lại... Rồi lại làm như vậy

pair<double, double> v[100]; // v.first để lưu hệ số, v.second để lưu số mũ

void inputEquation(int n) {
    for (int i = 1; i <= n; i++) {
        cin >> v[i].first >> v[i].second;
    }
}

void outputEquation(int n) {
    for (int i = 1; i <= n; i++) {
        cout << v[i].first << " " << v[i].second << endl;
    }
}

// Hàm để tính f(u)
double function(int n, double u) {
    double resultOfFunction = 0;
    for (int i = 1; i <= n; i++) {
        resultOfFunction += (v[i].first * pow(u, v[i].second));
    }
    return resultOfFunction;
}

double bisection(int n, double a, double b, double epsilon) {
    if (function(n, a) * function(n, b) >= 0) {
        return;
    }
    double p = a;
    while (b - a >= epsilon) {
        p = (a + b) / 2;
        if (function(n, p) == 0) {
            break;
        } else if (function(n, a) * function(n, p) < 0) {
            b = p;
        } else {
            a = p;
        }
    }
    return p;
}

int main() {
    int n;
    cin >> n;
    inputEquation(n);
    double epsilon, a, b;
    cin >> epsilon >> a >> b;
    cout << bisection(n, a, b, epsilon);
    return 0;
}