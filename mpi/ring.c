/*
 * usage: a.out msg_size num_shifts
 */
#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

#define IS_ODD(x) ((x)%2)

void ring(double *outgoing, double *incoming, int buff_count, int num_procs, int num_shifts, int my_id); 


int main(int argc, char **argv) {
	int ID, num_procs, buff_count, num_shifts, i, buff_size_bytes;
	double *outgoing, *incoming, start, ring_time, max_time;

	MPI_Init(&argc, &argv);	
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	
	if(ID == 0) {
		if(argc != 3) {
			printf("Usage: %s <size of message> <num of shifts>\n", *argv);
			fflush(stdout);
			MPI_Abort(MPI_COMM_WORLD, EXIT_FAILURE);
		}
		buff_count = atoi(*++argv);
		num_shifts = atoi(*++argv);
	}

	// broadcast data from rank 0 process to all other processes
	MPI_Bcast(&buff_count, 1, MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Bcast(&num_shifts, 1, MPI_INT, 0, MPI_COMM_WORLD);

	// allocate space and fill the outgoing and incoming vectors
	buff_size_bytes = buff_count * sizeof(double);
	outgoing = (double *) malloc(buff_size_bytes);
	incoming = (double *) malloc(buff_size_bytes);
	
	for(i = 0; i < buff_count; i++) {
		outgoing[i] = i;
		incoming[i] = -1;
	}
	
	// Do the ring communication tests
	MPI_Barrier(MPI_COMM_WORLD);
	start = MPI_Wtime();
	ring(outgoing, incoming, buff_count, num_procs, num_shifts, ID);
	ring_time = MPI_Wtime() - start;
	MPI_Barrier(MPI_COMM_WORLD);

	// (inbuff, outbuff, count, MPI_type, OP, dest, COMM)
	MPI_Reduce(&ring_time, &max_time, 1, MPI_DOUBLE, MPI_MAX, 0, MPI_COMM_WORLD);
	
	if(ID == 0) {
		printf("Ring test took %f seconds", max_time);
		printf(": shift %d doubles %d times \n", buff_count, num_shifts);
		fflush(stdout);
	}

	MPI_Finalize();
	return 0;
}

// odd processes send/recv and even processes rcv/snd
void ring(double *outgoing, double *incoming, int buff_count, int num_procs, int num_shifts, int my_id) {
	int prev, next, i, tag = 3;
	MPI_Status status;

	next = (my_id + 1)%num_procs;
	prev = (my_id == 0)?(num_procs - 1):(my_id - 1);

	if(IS_ODD(my_id)) {
		for(i = 0; i < num_shifts; i ++) {
			MPI_Send(outgoing, buff_count, MPI_DOUBLE, next, tag, MPI_COMM_WORLD);
			MPI_Recv(incoming, buff_count, MPI_DOUBLE, prev, tag, MPI_COMM_WORLD, &status);
		}
	} else {
		for(i = 0; i < num_shifts; i ++) {
			MPI_Recv(incoming, buff_count, MPI_DOUBLE, prev, tag, MPI_COMM_WORLD, &status);
			MPI_Send(outgoing, buff_count, MPI_DOUBLE, next, tag, MPI_COMM_WORLD);
		}
	}
}
