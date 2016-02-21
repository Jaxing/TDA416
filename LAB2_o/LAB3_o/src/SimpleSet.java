interface SimpleSet<E extends Comparable<? super E>> {
 int size();
 boolean add(E x);
 boolean remove(E x);
 boolean contains(E x);
}
