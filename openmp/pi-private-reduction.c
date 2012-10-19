#include <stdio.h>
#include <omp.h>

#define NUM_THREADS 4

void main() {
	static long num_steps = 100000;
	double x, pi = 0, sum = 0;
	double delta = 1.0/num_steps;
	int i, ID, num_threads;

	omp_set_num_threads(NUM_THREADS);

	//num_threads = omp_get_num_threads();

	//#pragma omp parallel for reduction(+:sum) private(x)
	#pragma omp parallel reduction(+:sum) private(x)
	{
		num_threads = omp_get_num_threads();
		ID = omp_get_thread_num();
		#pragma omp for
		for (i = 1; i <= num_steps; i++){
			x = (i - 0.5)*delta;
			sum += 4/(1 + x*x);	
		}	
	}
	pi = sum * delta;
	printf("pi = %lf, num_threads = %d, ID = %d\n", pi, num_threads, ID);
}
