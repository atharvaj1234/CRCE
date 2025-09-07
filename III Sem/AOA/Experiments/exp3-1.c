#include <stdio.h>
#include <math.h>

// Function to calculate number of digits in a number
int num_len(double n) {
    int len = 0;
    n = fabs(n); // Handle negative numbers
    if (n < 1) return 1;
    while (n >= 1) {
        n /= 10;
        len++;
    }
    return len;
}

// Recursive Karatsuba function
double karatsuba(double x, double y) {
    // Base case
    if (x < 10 || y < 10)
        return x * y;

    // Find length of both numbers
    int len_x = num_len(x);
    int len_y = num_len(y);
    int n = (len_x > len_y) ? len_x : len_y;

    // Split length into half
    int n_half = n / 2;

    // Power of 10 for splitting
    double power = pow(10, n_half);

    // Split numbers into parts
    double a = floor(x / power);
    double b = fmod(x, power);
    double c = floor(y / power);
    double d = fmod(y, power);

    // Recursive steps
    double ac = karatsuba(a, c);
    double bd = karatsuba(b, d);
    double ad_plus_bc = karatsuba(a + b, c + d) - ac - bd;

    // Combine results
    return ac * pow(10, 2 * n_half) + ad_plus_bc * pow(10, n_half) + bd;
}

int main() {
    double n1, n2, result;

    // Input numbers
    printf("Enter first number: ");
    scanf("%lf", &n1);
    printf("Enter second number: ");
    scanf("%lf", &n2);

    // Perform Karatsuba multiplication
    result = karatsuba(n1, n2);

    // Display the result
    printf("Product: %.0lf\n", result);

    return 0;
}
