#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

/* Update a distributed field with a local N by N block on
*  each process (held in the array U). The point of this
*  example is to show communication overlapped with computation
*  computation, so code for other functions is not included.
*/
#define N 100 // size of an edge in the square field.
void extern initialize(int, double *);
void extern extract_boundary(int, double *, double *);
void extern update_interior(int, double *);
void extern update_edge(int, double *, double *);

int main(int argc, char **argv) {
	double *U, *B, *inB;
	int i, num_procs, ID, left, right, Nsteps = 100;

	MPI_Status status;
	
	// handles for non-blocking communication
	MPI_Request req_recv, req_send;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	
	// allocate space for the field (U), and te buffers to 
	// send and receive the edges (B, inB)
	U = (double *) malloc (N*N*sizeof(double));	
	B = (double *) malloc (N*sizeof(double));	
	inB = (double *) malloc (N*sizeof(double));	

	// initialize the field and set up a ring communication pattern
	initialize(N, U);
	right = ID + 1;
	if(right > (num_procs - 1)) right = 0;
	left = ID - 1;
	if(left < 0) left = num_procs - 1;
	
	// iteratively update the field U
	for(i = 0; i < Nsteps; i++) {
		// (buf, count, MPI_Datatype, source, tag, comm, request)
		MPI_Irecv(inB, N, MPI_DOUBLE, left, i, MPI_COMM_WORLD, &req_recv);		

		extract_boundary(N, U, B); // copy the edge U into B

		MPI_Isend(B, N, MPI_DOUBLE, right, i, MPI_COMM_WORLD, &req_send);

		update_interior(N, U);
	
		MPI_Wait(&req_recv, &status);
		MPI_Wait(&req_send, &status);
	}

	printf("ID %d: inB = %lf, U = %lf\n", ID, inB, U);

	update_edge(ID, inB, U);
	MPI_Finalize();
	
	return EXIT_SUCCESS;
}

void extern initialize(int n, double *u) {}
void extern extract_boundary(int n, double * row, double * col) {}
void extern update_interior(int n, double *in) {}
void extern update_edge(int id, double * e1, double * e2) {}

