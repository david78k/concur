#include <stdio.h>

int main(int argc, char *argv[]) {
    const int N = 100000;
    int i, a[N], sum = 0;
 
    #pragma omp parallel for
    for (i = 0; i < N; i++) {
        a[i] = 2 * i;
	sum += a[i];
    }
 
    printf("sum = %d\n", sum);

    return 0;
}
