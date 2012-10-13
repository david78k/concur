#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

#define TAG1 1 // message tag for proc 0 to proc 1
#define TAG2 2 // message tag for proc 1 to proc 0
#define BUFFER_COUNT 100 // number of items in a message

int main (int argc, char **argv) {
	//int tag1 = 1;
	//int tag2 = 2;
	int num_procs;
	int ID;

	long buffer[BUFFER_COUNT];

	MPI_Status stat;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);

	if(num_procs != 2) 
		MPI_Abort(MPI_COMM_WORLD, EXIT_FAILURE);

	for(long i = 0; i < BUFFER_COUNT; i ++)
		buffer[i] = i;

	if(ID == 0) { // proc 0
		MPI_Send(buffer, BUFFER_COUNT, MPI_LONG, 1, TAG1, MPI_COMM_WORLD);
		printf("Proc0: message sent\n");
		MPI_Recv(buffer, BUFFER_COUNT, MPI_LONG, 1, TAG2, MPI_COMM_WORLD, &stat);
		printf("Proc0: message recv\n");
	} else {
		MPI_Recv(buffer, BUFFER_COUNT, MPI_LONG, 0, TAG1, MPI_COMM_WORLD, &stat);
		printf("Proc1: message recv\n");
		MPI_Send(buffer, BUFFER_COUNT, MPI_LONG, 0, TAG2, MPI_COMM_WORLD);
		printf("Proc1: message sent\n");
	}
	
	MPI_Finalize();

	return EXIT_SUCCESS;
}
