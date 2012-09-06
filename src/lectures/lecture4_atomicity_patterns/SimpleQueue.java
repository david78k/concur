package lectures.lecture4_atomicity_patterns;

interface SimpleQueue<E> {
	void put (E elem);
	E get();
}
