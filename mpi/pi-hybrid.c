#include <stdio.h>
#include <mpi.h>
#include <omp.h>

// compute pi using a hybrid MPI/OpenMP
int main(int argc, char **argv) {
	int num_steps = 1000000000;
	int my_id, numprocs, numthreads, threadlevel;
	double x, pi, step, sum = 0.0;
	
	step = 1.0/(double)num_steps;

	MPI_Init_thread(&argc, &argv, MPI_THREAD_FUNNELED, &threadlevel);
	MPI_Comm_rank(MPI_COMM_WORLD, &my_id);
	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);

	if(my_id == 0) {
		#pragma omp parallel
		#pragma omp master
		{ 
			numthreads = omp_get_num_threads();
		}
	}
	
	// int MPI_Bcast(void *buffer, int count, MPI_Datatype datatype, int root, MPI_Comm comm )
	MPI_Bcast(&numthreads, 1, MPI_INT, 0, MPI_COMM_WORLD);
	omp_set_num_threads(numthreads);	
	printf("my_id = %d, numthreads = %d\n", my_id, numthreads);

	#pragma omp parallel for private(x) reduction(+:sum) schedule(static)
	for (int i = my_id; i < num_steps; i += numprocs) {
		x = (i+0.5)*step;
		sum += 4.0*(1+x*x);
	}
	// (inbuf, outbuf, count, MPI_type, OP, dest, COMM)
	MPI_Reduce(&sum, &pi, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

	if(my_id == 0) printf("pi %lf\n", pi);
	MPI_Finalize();
	return 0;
}
