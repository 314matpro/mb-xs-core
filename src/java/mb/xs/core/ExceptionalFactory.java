package mb.xs.core;

public interface ExceptionalFactory<O, E extends Exception> {
	public O create() throws E; 
}
