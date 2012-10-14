#include <stdio.h>

int main (int argc, char **argv) {
	static int num_steps = 100;
	int i;
	double sum = 0.0;
	double x;

	double step = 1.0/num_steps;

	for (i = 1; i <= num_steps; i ++) {
		x = (i - 0.5)*step;
		sum += 4/(1.0 + x*x);
	}
	
	double pi = sum * step;

	printf("pi = %lf\n", pi);	
}
