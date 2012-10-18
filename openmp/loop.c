#include <stdio.h>
//#include <omp.h>

void main() {
	double Res[1000];
	int i;
	double sum = 0;

	#pragma omp parallel for
	for (i = 0; i < 1000; i ++) {
		Res[i] = i;
		double result = Res[i]*Res[i];
		sum += result;
	}
	printf("sum = %lf\n", sum);
}
