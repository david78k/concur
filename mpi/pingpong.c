#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

#define TAG1 1 // message tag for proc 0 to proc 1
#define TAG2 2 // message tag for proc 1 to proc 0
#define BUFFER_COUNT 100 // number of items in a message

int main (int argc, char **argv) {
	int tag1 = 1;
	int tag2 = 2;
	int num_procs;
	int ID;

	long buffer[BUFFER_COUNT];

	MPI_Status stat;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	
	return EXIT_SUCCESS;
}
