#include <stdio.h>

void main() {
	static long num_steps = 100000;
	double sum = 0;
	int i;
	double delta = 1.0/num_steps;

	for (i = 1; i <= num_steps; i++){
		double x = (i - 0.5)*delta;
		sum += 4/(1 + x*x);	
	}	
	double pi = delta * sum;
	printf("pi = %lf\n", pi);
}
