#include <stdio.h>
#include <mpi.h>

// MPI version of pi
int main (int argc, char **argv) {
	static int num_steps = 100000;
	int i, my_id, num_procs, my_steps;
	double sum = 0.0;
	double x, pi;

	double step = 1.0/num_steps;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &my_id);
	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	
	my_steps = num_steps/num_procs;
	for (i = my_id*my_steps; i < (my_id + 1)*my_steps; i ++) {
		x = (i - 0.5)*step;
		sum += 4/(1.0 + x*x);
	}
	
	sum *= step;

	// (inbuff, outbuff, count, MPI_type, OP, dest, COMM)
	MPI_Reduce(&sum, &pi, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

	MPI_Finalize();

	printf("pi = %lf\n", pi);	
}
