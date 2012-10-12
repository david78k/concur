#include <stdio.h>
#include "mpi.h"

int main(int argc, char **argv) {
	int num_procs;
	int ID;
	
	if (MPI_Init(&argc, &argv)!= MPI_SUCCESS) {
		return;
		//exit(0);
	}
	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	printf("\n Go Gators %d \n", ID);
	MPI_Finalize();
}	
