package mb.xs.core;

public interface Cache<D> {
	public void set( D data );
	public D get();
}
